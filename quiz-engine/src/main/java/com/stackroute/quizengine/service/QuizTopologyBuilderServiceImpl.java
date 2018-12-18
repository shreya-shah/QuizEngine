package com.stackroute.quizengine.service;

import com.stackroute.quizengine.model.*;
import com.stackroute.quizengine.repository.QuizTopologyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class QuizTopologyBuilderServiceImpl implements QuizTopologyBuilderService {

    @Autowired
    RestTemplate restTemplate;
 //    String url = "http://172.23.239.65:8082/quiz-inventory-service/api/v1/quiz/{quizId}";
//    String url = "http://172.23.238.168:8082/quiz-inventory-service/api/v1/quiz/{quizId}";
    String url = "http://localhost:8081/api/v1/quiz/{quizId}";
    QuizTopologyRepo quizTopologyRepo;

    @Autowired
    public QuizTopologyBuilderServiceImpl(QuizTopologyRepo quizTopologyRepo) {
        this.quizTopologyRepo = quizTopologyRepo;
    }

    @Override
    public Topology createTopology(String quizId) {
        if(quizTopologyRepo.findByQuizId(quizId) == null){
            Quiz quiz = getQuizByQuizId(quizId);

            ArrayList<String> args = new ArrayList<String>();
            args.add("concept");
            args.add("taxonomylevel");
            args.add("username");

            QuestionQuery conceptQuestionQuery = new QuestionQuery("/conceptQuestion", args);
            QuestionQuery countertQuestionQueryLeft = new QuestionQuery("/counterQuestion", args);
            QuestionQuery counterQuestionQueryRight = new QuestionQuery("/counterQuestion", args);

            ArrayList<String> roles = new ArrayList<>();
            ArrayList<String> proficiencyLevels = new ArrayList<>();
            List<ConceptMap> conceptMaps = getConceptMapByQuiz(quiz);

            if (quiz.getProficiencyLevel() != null) {
                proficiencyLevels.add(quiz.getProficiencyLevel().name());
            }

            roles.add(quiz.getRole());

            ConceptQuery conceptQuery = new ConceptQuery("/competency/role/:roleName/conceptMap/", roles, quiz.getSections().get(0).getSkills(), quiz.getSections().get(0).getConcepts(),quiz.getSections().get(0).getConcepts().size(),proficiencyLevels, true, "taxonomy", "desc", conceptMaps);
            System.out.println("ConceptMaps "+conceptMaps);
            Topology topology = new Topology(quizId, 530, 10, 3, quiz.getAssessmentDepth(), new Date(), conceptQuestionQuery, conceptQuery, getConceptQuestions(conceptMaps,quiz.getAssessmentDepth()));
            quizTopologyRepo.save(topology);
            return topology;
        }

        return quizTopologyRepo.findByQuizId(quizId);
    }

    @Override
    public Topology getTopologyByQuizId(String quizId) {
        Topology topology = quizTopologyRepo.findByQuizId(quizId);
        return topology;
    }

    @Override
    public List<ConceptQuestion> getConceptQuestionsByQuizId(String quizId) {
        Topology topology = quizTopologyRepo.findByQuizId(quizId);
        return topology.getConceptQuestion();
    }

    @Override
    public Quiz getQuizByQuizId(String quizId) {
        Map<String, String> uriParams = new HashMap<String, String>();
        uriParams.put("quizId", quizId);
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
        Quiz quiz = restTemplate.getForObject(builder.buildAndExpand(uriParams).toUri(), Quiz.class);
        return quiz;
    }

    @Override
    public List<ConceptMap> getConceptMapByQuiz(Quiz quiz) {

//        String semanticUrl = "http://172.23.238.166:8083/api/v1/competency/role/{roleName}/conceptMap";
        String semanticUrl = "http://localhost:8083/api/v1/competency/role/{roleName}/conceptMap";
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(semanticUrl)
                .queryParam("proficiencyLevel",quiz.getProficiencyLevel().name())
                .queryParam("skillName",quiz.getSections().get(0).getSkills().get(0));

        Map<String, String> uriParams = new HashMap<String, String>();
        uriParams.put("roleName",quiz.getRole());


        Map<String, Object> response = restTemplate.getForObject(builder.buildAndExpand(uriParams).toUri(), Map.class);
        Collection<LinkedHashMap<String,String>> collection = (Collection<LinkedHashMap<String, String>>) response.get("conceptMap");
        ArrayList<LinkedHashMap<String,String>> list = collection.stream().collect(Collectors.toCollection(ArrayList::new));
        ArrayList<ConceptMap> conceptMaps = new ArrayList<ConceptMap>();

        for(int i=0;i<list.size();i++){
            conceptMaps.add(new ConceptMap(list.get(i).get("concept"),list.get(i).get("taxonomy")));
        }

        return conceptMaps;
    }


    public ArrayList<ConceptQuestion> getConceptQuestions(List<ConceptMap> concept,int assessmentDepth) {
        ArrayList<ConceptQuestion> conceptQuestions = new ArrayList<ConceptQuestion>();
        int j = 1;
        for(int i=0;i<concept.size();i++){
            int conceptQuestionNumber = j;
            conceptQuestions.add(new ConceptQuestion(j,concept.get(i).getConcept(),ConceptQuestion.TaxonomyLevel.valueOf(concept.get(i).getTaxonomy()),"conceptQuestion",i+1,0));
            j++;
            for(int k=1;k<=assessmentDepth;k++){
                conceptQuestions.add(new ConceptQuestion(j,concept.get(i).getConcept(),ConceptQuestion.TaxonomyLevel.valueOf(concept.get(i).getTaxonomy()),"counterQuestion",conceptQuestionNumber,k));
                j++;
            }
        }

        conceptQuestions.sort((c1, c2) -> c1.getDepth()-c2.getDepth());

        return conceptQuestions;
    }
}