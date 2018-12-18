package com.stackroute.quizengine.controller;

import com.stackroute.quizengine.model.*;
import com.stackroute.quizengine.repository.QuizSessionRepo;
import com.stackroute.quizengine.service.QuizEngineService;
import com.stackroute.quizengine.service.QuizQuestionEvaluationService;
import com.stackroute.quizengine.service.QuizSessionService;
import com.stackroute.quizengine.service.QuizSubmissionService;

import com.stackroute.quizengine.service.QuizEngineService;
import com.stackroute.quizengine.service.QuizTopologyBuilderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.xml.ws.Response;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("api/v1")
public class QuizEngineController {

    RestTemplate restTemplate;

    QuizTopologyBuilderService quizTopologyBuilderService;
    QuizQuestionEvaluationService quizQuestionEvaluationService;
    QuizSubmissionService quizSubmissionService;
    QuizSessionService quizSessionService;
    QuizEngineService quizEngineService;

    @Autowired
    public QuizEngineController(RestTemplate restTemplate, QuizSessionService quizSessionService, QuizEngineService quizEngineService, QuizSubmissionService quizSubmissionService, QuizQuestionEvaluationService quizQuestionEvaluationService, QuizTopologyBuilderService quizTopologyBuilderService) {
        this.restTemplate = restTemplate;
        this.quizSessionService = quizSessionService;
        this.quizEngineService = quizEngineService;
        this.quizSubmissionService = quizSubmissionService;
        this.quizQuestionEvaluationService = quizQuestionEvaluationService;
        this.quizTopologyBuilderService = quizTopologyBuilderService;
    }

    @PostMapping("quizEngine/quiz/{quizId}/learner/{learnerId}/startQuiz")
    public ResponseEntity<?> startQuiz(@PathVariable("quizId") String quizId, @PathVariable("learnerId") String learnerId) {

        Topology topology = quizTopologyBuilderService.createTopology(quizId);
        List<String>roles = topology.getConceptQuery().getRoles();
        List<String>skills = topology.getConceptQuery().getSkills();
        List<String>concepts = topology.getConceptQuery().getConcepts();

        System.out.println(concepts);

        String sessionId = quizSessionService.createSession(quizId);

        String submissionId = quizSubmissionService.createQuizSubmission(sessionId, learnerId, quizId, roles, skills, concepts);

        List<ConceptQuestion> conceptQuestions = topology.getConceptQuestion();

        QuizSession quizSession = quizSessionService.updateSession(sessionId, learnerId, submissionId, conceptQuestions);

        return new ResponseEntity<String>("Successfully created a session", HttpStatus.OK);
    }

    @GetMapping("quizEngine/quiz/{quizId}/QuestionSubmission")
    public ResponseEntity<?> getQuestion(@PathVariable("quizId") String quizId) {
        //String questionId = this.topoService.getQuestionId;
        //Optional question = this.restTemplate.getForObject
        Optional question = this.quizEngineService.getQuestion(quizId);
        return new ResponseEntity<Optional>(question, HttpStatus.OK);
    }

//    @PatchMapping("quizEngine/quiz/{quizId}/learner/{learnerId}/question/{questionCode}/submission?answer={selectedAnswer}")
//        public ResponseEntity<?> updateUserAnswer (@PathVariable("quizId") String quizId,
//                @PathVariable("learnerId") String learnerId,
//        @PathVariable("questionId") String questionCode,
//        @RequestParam("selectedAnswer") String selectedAnswer){
//            //
//            //Go to QuizSession and get submissionId for quizId and playerId and then go to evaluation service and get result and
//            //send the result to the quizSubmission along with submissionId
//            String submissionId = quizSessionService.getSubmissionIdByLearnerIdAndQuizId(learnerId, quizId);
//            boolean isCorrect = quizQuestionEvaluationService.evaluateAnswer(Integer.parseInt(questionCode), selectedAnswer);
//            quizSubmissionService.updateQuestionDetailsWithAnswer(submissionId, Integer.parseInt(questionCode), selectedAnswer, isCorrect);
//            return new ResponseEntity<String>("Successfully submitted", HttpStatus.OK);
//        }

    @PatchMapping("quizEngine/quiz/{quizId}/learner/{learnerId}/question/{questionCode}/{questionNumber}/submission")
    public ResponseEntity<?> updateUserAnswer(@PathVariable("quizId") String quizId,
                                              @PathVariable("learnerId") String learnerId,
                                              @PathVariable("questionCode") String questionCode,
                                              @PathVariable("questionNumber") int questionNumber,
                                              @RequestParam("selectedAnswer") String selectedAnswer) {
        //
        //Go to QuizSession and get submissionId for quizId and playerId and then go to evaluation service and get result and
        //send the result to the quizSubmission along with submissionId
        String submissionId = this.quizSessionService.getSubmissionIdByLearnerIdAndQuizId(learnerId, quizId);
        String decodedAns = URLDecoder.decode(selectedAnswer);

        boolean isCorrect = this.quizQuestionEvaluationService.evaluateAnswer(Integer.parseInt(questionCode), decodedAns);
        this.quizSubmissionService.updateQuestionDetailsWithAnswer(submissionId, Integer.parseInt(questionCode), questionNumber, selectedAnswer, isCorrect);

        String url = "http://localhost:8083/api/v1/learner/{learnerId:.+}/";
        Map<String, String> uriParams = new HashMap<String, String>();
        uriParams.put("learnerId", learnerId);

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
                .queryParam("questionCode", questionCode)
                .queryParam("answer",selectedAnswer);

        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("questionCode", questionCode);
        map.add("answer", isCorrect);

        this.restTemplate.postForObject(builder.buildAndExpand(uriParams).toUri(), map, String.class);

        return new ResponseEntity<String>("Successfully submitted", HttpStatus.OK);
    }


//    @PatchMapping("quizEngine/quiz/{quizId}/learner/{learnerId}/question/{questionCode}/{questionNumber}/submission")
//    public ResponseEntity<?> updateUserAnswer (@PathVariable("quizId") String quizId,
//                                               @PathVariable("learnerId") String learnerId,
//                                               @PathVariable("questionCode") String questionCode,
//                                               @PathVariable("questionNumber") int questionNumber,
//                                               @RequestParam("selectedAnswer") String selectedAnswer){
//        //
//        //Go to QuizSession and get submissionId for quizId and playerId and then go to evaluation service and get result and
//        //send the result to the quizSubmission along with submissionId
//        String submissionId = quizSessionService.getSubmissionIdByLearnerIdAndQuizId(learnerId, quizId);
//        String decodedAns = URLDecoder.decode(selectedAnswer);
//
//        boolean isCorrect = quizQuestionEvaluationService.evaluateAnswer(Integer.parseInt(questionCode), decodedAns);
//        quizSubmissionService.updateQuestionDetailsWithAnswer(submissionId,Integer.parseInt(questionCode),questionNumber,selectedAnswer,isCorrect);
//        return new ResponseEntity<String>("Successfully submitted", HttpStatus.OK);
//    }


        @GetMapping("quizEngine/quiz/{quizId}/learner/{learnerId}/question")
        public ResponseEntity<?> getQuestion (@PathVariable("quizId") String quizId,
                @PathVariable("learnerId") String learnerId){


            ConceptQuestion question = quizSessionService.getQuestion(learnerId, quizId);
            System.out.println("question" + question);


            String submissionId = quizSessionService.getSubmissionIdByLearnerIdAndQuizId(learnerId, quizId);

            quizSubmissionService.updateTaxonomyConcept(submissionId, question.getQuestionNumber(), question.getTaxonomyLevel().name(), question.getType(), question.getConcept(), question.getConceptQuestion());

        //Go to semantic services.
            // String url = "http://172.23.238.166:8083/api/v1/question/concept/{conceptName}/learner/{learnerName}";
            //Go to semantic services.
//            String url = "http://172.23.239.120:8083/api/v1/question/concept/{conceptName}/learner/{learnerName}";
            String url = "http://localhost:8083/api/v1/question/concept/{conceptName}/learner/{learnerId:.+}/";

            Map<String, String> uriParams = new HashMap<String, String>();
//        uriParams.put("conceptName", question.getConcept());
            uriParams.put("conceptName", question.getConcept());
            uriParams.put("learnerId", learnerId);

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
                .queryParam("taxonomyLevel", question.getTaxonomyLevel());
//                .queryParam("taxonomyLevel", "Remembering");
//        System.out.println("builder"+builder.buildAndExpand(uriParams));

//        System.out.println("from semantic service: "+this.restTemplate.getForObject(builder.buildAndExpand(uriParams).toUri(), Integer.class));

//        int questionCode = this.restTemplate.getForObject(builder.buildAndExpand(uriParams).toUri(), Integer.class);
        Map<String,Object> result = this.restTemplate.getForObject(builder.buildAndExpand(uriParams).toUri(), Map.class);
        String questionCode = (String)result.get("result");
//        String questionCode = this.restTemplate.getForObject(builder.buildAndExpand(uriParams).toUri(), String.class);
        //Goto QuestionInventory and get Question.
        String url1 = "http://localhost:8082/api/v1/questions/{questionCode}";
        Map<String, String> uriParams1 = new HashMap<String, String>();
        uriParams1.put("questionCode", questionCode);

            //Goto QuestionInventory and get Question.
            //String url1 = "http://172.23.239.65:8080/api/v1/questions/{questionId}";
//            Map<String, Long> uriParams1 = new HashMap<String, Long>();
//            uriParams1.put("questionId", questionId);

            UriComponentsBuilder builder1 = UriComponentsBuilder.fromUriString(url1);

        Question question1 = this.restTemplate.getForObject(builder1.buildAndExpand(uriParams1).toUri(), Question.class);
        question1.setQuestionNumber(question.getQuestionNumber());
        quizSubmissionService.updateQuestionDetails(submissionId, question.getQuestionNumber(),question1.getQuestionId(),questionCode);
        //quizSubmissionService. methodName()
            //quizSubmissionService. methodName()

            //questionId, Submissionid, questionNumber

            return new ResponseEntity<Question>(question1, HttpStatus.OK);

        }


        @GetMapping("quizEngine/quiz/{quizId}/learner/{learnerId}/result")
        public ResponseEntity<?> getResult (@PathVariable("quizId") String quizId,
                @PathVariable("learnerId") String learnerId){
            //Go to session and retrieve the submissionId and fetch the entire object
            String submissionId = quizSessionService.getSubmissionIdByLearnerIdAndQuizId(learnerId, quizId);
            System.out.println(submissionId);
            QuizSubmission result = quizSubmissionService.getSubmissionById(submissionId);
//            System.out.println(result);
            //return null;
            return new ResponseEntity<QuizSubmission>(result, HttpStatus.OK);

        }
    }



