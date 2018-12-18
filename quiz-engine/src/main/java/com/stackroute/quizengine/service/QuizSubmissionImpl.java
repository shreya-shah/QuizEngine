package com.stackroute.quizengine.service;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

import com.stackroute.quizengine.model.*;
import com.stackroute.quizengine.repository.QuestionRepo;
import com.stackroute.quizengine.repository.QuestionSubmissionRepo;
import com.stackroute.quizengine.repository.QuizSubmissionRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;


@Service
public class QuizSubmissionImpl implements QuizSubmissionService {

    private QuizSubmissionRepo quizSubmissionRepo;
    private QuestionSubmissionRepo questionSubmissionRepo;
    MongoTemplate mongoTemplate;

    @Autowired
    QuizSubmissionImpl(QuizSubmissionRepo quizSubmissionRepo,QuestionSubmissionRepo questionSubmissionRepo,MongoTemplate mongoTemplate)
    {
        this.quizSubmissionRepo = quizSubmissionRepo;
        this.questionSubmissionRepo = questionSubmissionRepo ;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public String createQuizSubmission(String sessionId, String learnerId, String quizId, List<String> roles, List<String> skills, List<String> concepts) {
        QuizSubmission quizSubmission = new QuizSubmission();
        quizSubmission.setLearnerId(learnerId);
        quizSubmission.setSessionId(sessionId);
        quizSubmission.setQuizId(quizId);
        quizSubmission.setConcepts(concepts);
        quizSubmission.setRole(roles);
        quizSubmission.setSkill(skills);
        quizSubmissionRepo.save(quizSubmission);
        return quizSubmission.getSubmissionId();
    }

    @Override
    public QuizSubmission getSubmissionById(String submissionId) {

        QuizSubmission quizSubmission =  quizSubmissionRepo.findById(submissionId).get();
       List<String> concepts= quizSubmission.getConcepts();
        System.out.println(concepts);
//        ConceptTaxonomy conceptTaxonomy = new ConceptTaxonomy();
        List<ConceptTaxonomy> conceptTaxonomies=new ArrayList<>();
       if(concepts!=null){
           for(int i=0;i<concepts.size();i++)
           {
               int totalQuestions=0;
               int correctlyAnswered=0;

               ConceptTaxonomy conceptTaxonomy = new ConceptTaxonomy();
               conceptTaxonomy.setConcept(concepts.get(i));
               System.out.println("concept:  "+concepts.get(i));
               List<QuestionSubmission> questionSubmission = quizSubmission.getQuestionSubmission();
               for(int j=0;j<questionSubmission.size();j++){
                   if(questionSubmission.get(j).getConcept().equals(concepts.get(i))){
                       conceptTaxonomy.setTaxonomy(getTaxonomy(concepts.get(i),submissionId));

                       totalQuestions++;
                       if (questionSubmission.get(j).getResult())
                       {
                           correctlyAnswered++;
                       }
                   }
               }
              // System.out.println("dgjefhrg"+questionSubmission);
                conceptTaxonomy.setTotalQuestions(totalQuestions);
               conceptTaxonomy.setCorrectlyAnswered(correctlyAnswered);
               conceptTaxonomies.add(conceptTaxonomy);
//               quizSubmission.setConceptTaxonomy(conceptTaxonomies);
//               quizSubmissionRepo.save(quizSubmission);
//           quizSubmission.setConceptMaps(concepts.get(i),getTaxonomy(concepts.get(i),submissionId));
           }
           quizSubmission.setConceptTaxonomy(conceptTaxonomies);

           quizSubmissionRepo.save(quizSubmission);

       }
       QuizSubmission quizSubmission1 = quizSubmissionRepo.findById(submissionId).get();
        System.out.println(quizSubmission1);
        return quizSubmission1;
    }

    @Override
    public String getTaxonomy(String concepts, String submissionId) {
        if (quizSubmissionRepo.existsById(submissionId)) {
            List<QuestionSubmission> questionSubmissions = quizSubmissionRepo.findById(submissionId).get().getQuestionSubmission();
            String taxonomy = "Remembering";

            for (int i = 0; i < questionSubmissions.size(); i++) {
                if(questionSubmissions.get(i).getConcept().equals(concepts)){
                    if (ConceptQuestion.TaxonomyLevel.valueOf(taxonomy).compareTo(ConceptQuestion.TaxonomyLevel.valueOf(questionSubmissions.get(i).getTaxonomy())) > 0) {
                        if(ConceptQuestion.TaxonomyLevel.valueOf(questionSubmissions.get(i).getTaxonomy()).ordinal()>0){
                            taxonomy = ConceptQuestion.TaxonomyLevel.values()[ConceptQuestion.TaxonomyLevel.valueOf(questionSubmissions.get(i).getTaxonomy()).ordinal()].name();
                            System.out.println("sdjfnfk"+taxonomy);
                        }else{
                            taxonomy = ConceptQuestion.TaxonomyLevel.values()[ConceptQuestion.TaxonomyLevel.valueOf(questionSubmissions.get(i).getTaxonomy()).ordinal()-1].name();
                        }
                    }
                }
            }

            return taxonomy;


        }
        return null;

    }


    @Override
    public QuizSubmission updateTaxonomyConcept(String submissionId, int questionNumber, String taxonomy, String questionType, String concept,int conceptQuestionNumber) {
        if (quizSubmissionRepo.existsById(submissionId)) {
            QuizSubmission quizSubmission = quizSubmissionRepo.findById(submissionId).get();
            System.out.println("quiz submission: " + quizSubmission);
            List<QuestionSubmission> questionSubmissionList = quizSubmission.getQuestionSubmission();
//            System.out.println(questionSubmissionList);
            if (quizSubmission.getQuestionSubmission() == null) {
                QuestionSubmission questionSubmission = new QuestionSubmission();
                List<QuestionSubmission> questionSubmissions = new ArrayList<>();
                questionSubmission.setQuestionNumber(questionNumber);
                questionSubmission.setTaxonomy(taxonomy);
                questionSubmission.setQuestionType(questionType);
                questionSubmission.setConcept(concept);
                questionSubmission.setConceptQuestionNumber(conceptQuestionNumber);
                questionSubmissions.add(questionSubmission);
                questionSubmissionRepo.save(questionSubmission);
                quizSubmission.setQuestionSubmission(questionSubmissions);
                quizSubmissionRepo.save(quizSubmission);
                return quizSubmission;
            } else {
//                QuestionSubmission questionSubmission = questionSubmissionRepo.findByQuestionNumber(questionNumber);
                List<QuestionSubmission> questionSubmission = quizSubmission.getQuestionSubmission();
                QuestionSubmission q = new QuestionSubmission();
                q.setQuestionNumber(questionNumber);
                q.setTaxonomy(taxonomy);
                q.setQuestionType(questionType);
                q.setConcept(concept);
                q.setConceptQuestionNumber(conceptQuestionNumber);
                questionSubmission.add(q);
                questionSubmissionRepo.save(q);
                quizSubmission.setQuestionSubmission((questionSubmission));
                quizSubmissionRepo.save(quizSubmission);
                return quizSubmission;
            }
        } else {
            return null;
        }
    }

    @Override
    public QuizSubmission updateQuestionDetails(String submissionId, int questionNumber, String questionId,String questionCode) {
        if(quizSubmissionRepo.existsById(submissionId))
        {
            QuizSubmission quizSubmission = quizSubmissionRepo.findById(submissionId).get();
            List<QuestionSubmission> questionSubmissions = quizSubmission.getQuestionSubmission();
            for(QuestionSubmission q : questionSubmissions){
                if(q.getQuestionNumber() == questionNumber) {
                    q.setQuestionId(questionId);
                    q.setQuestionCode(Integer.parseInt(questionCode));
                    questionSubmissionRepo.save(q);

                }
                quizSubmission.setQuestionSubmission(questionSubmissions);
                quizSubmissionRepo.save(quizSubmission);
            }


        }
        return null;
    }

    @Override
    public QuizSubmission updateQuestionDetailsWithAnswer(String submissionId, int questionCode, int questionNumber, String choosenanswer, boolean result) {
        double score = 0;
        if(quizSubmissionRepo.existsById(submissionId))
        {
            QuizSubmission quizSubmission = quizSubmissionRepo.findById(submissionId).get();
            score = quizSubmission.getScore();
            List<QuestionSubmission> questionSubmissions = quizSubmission.getQuestionSubmission();
            for(QuestionSubmission q : questionSubmissions)
            {
                if(q.getQuestionType().equals("conceptQuestion"))
                {
                    if(result)
                        score+=1;
                }else {
                    if(result)
                        score+=.5;
                    else
                        score-=.5;
                }
                quizSubmission.setScore(score);
                if(q.getQuestionNumber() == questionNumber)
                {
                    q.setChosenanswer(choosenanswer);
                    q.setResult(result);
                    questionSubmissionRepo.save(q);
                }
                quizSubmission.setQuestionSubmission(questionSubmissions);
                quizSubmissionRepo.save(quizSubmission);
            }
        }
        return null;
    }

//    @Override
//    public QuizSubmission updateQuestionDetailsWithAnswer(String submissionId, int questionCode, int questionNumber, String choosenanswer, boolean result) {
////        long score = 0
//        if(quizSubmissionRepo.existsById(submissionId))
//        {
//            QuizSubmission quizSubmission = quizSubmissionRepo.findById(submissionId).get();
//            List<QuestionSubmission> questionSubmissions = quizSubmission.getQuestionSubmission();
//            for(QuestionSubmission q : questionSubmissions)
//            {
//                System.out.println("In update answer"+q.getQuestionCode());
//                if(q.getQuestionNumber() == questionNumber)
//                {
//                    q.setChosenanswer(choosenanswer);
//                    q.setResult(result);
//                    questionSubmissionRepo.save(q);
//                }
//                quizSubmission.setQuestionSubmission(questionSubmissions);
//                quizSubmissionRepo.save(quizSubmission);
//            }
//        }
//        return null;
//    }

    @Override
    public QuestionSubmission getQuestion(String submissionId, int questionNumber) {
              if(quizSubmissionRepo.existsById(submissionId))
              {
                  QuizSubmission quizSubmission =  quizSubmissionRepo.findById(submissionId).get();
                  List<QuestionSubmission> questionSubmissions = quizSubmission.getQuestionSubmission();
                  for(QuestionSubmission q : questionSubmissions)
                  {
                      if(q.getQuestionNumber() == questionNumber)
                          return q;

                  }

              }
              return null;
            }

}
