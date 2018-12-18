package com.stackroute.quizengine.service;

import com.stackroute.quizengine.model.*;
import com.stackroute.quizengine.repository.QuizSessionRepo;
import com.stackroute.quizengine.repository.QuizSubmissionRepo;
import com.stackroute.quizengine.repository.QuizTopologyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class QuizSessionServiceImpl implements QuizSessionService {


    QuizSessionRepo quizSessionRepo;
    QuizSubmissionRepo quizSubmissionRepo;
    QuizSubmissionService quizSubmissionService;
    QuizTopologyRepo quizTopologyRepo;

    @Autowired
    public QuizSessionServiceImpl(QuizSessionRepo quizSessionRepo, QuizSubmissionRepo quizSubmissionRepo, QuizSubmissionService quizSubmissionService,QuizTopologyRepo quizTopologyRepo) {
        this.quizSessionRepo = quizSessionRepo;
        this.quizSubmissionRepo = quizSubmissionRepo;
        this.quizSubmissionService = quizSubmissionService;
        this.quizTopologyRepo = quizTopologyRepo;
    }


    @Override
    public String createSession(String quizId) {
        QuizSession quizSession = new QuizSession();
        quizSession.setQuizId(quizId);
        quizSessionRepo.save(quizSession);
        return quizSessionRepo.findByQuizId(quizId).getSessionId();
    }

    @Override
    public QuizSession updateSession(String sessionId, String learnerId, String submissionId, List<ConceptQuestion> conceptQuestions) {
        QuizSession quizSession= quizSessionRepo.findById(sessionId).get();
        quizSession.setLearnerId(learnerId);
        quizSession.setSubmissionId(submissionId);
        quizSession.setConceptQuestions(conceptQuestions);
        quizSessionRepo.save(quizSession);
        return quizSession;
    }

    @Override
    public String getSubmissionIdByLearnerIdAndQuizId(String learnerId, String quizId) {
        System.out.println("learner id: "+learnerId+" quizId: "+quizId);
        QuizSession quizSession = quizSessionRepo.findByLearnerIdAndQuizId(learnerId, quizId);
        String submissionId = quizSession.getSubmissionId();
        System.out.println(submissionId);
        return submissionId;
    }

    @Override
    public ConceptQuestion getQuestion(String learnerId, String quizId) {

        QuizSession quizSession = quizSessionRepo.findByLearnerIdAndQuizId(learnerId, quizId);
        String submissionId = quizSession.getSubmissionId();
        System.out.println("submissionId: "+submissionId);
        int assessmentDepth = quizTopologyRepo.findByQuizId(quizId).getAssessmentDepth();
        System.out.println(quizSession.getConceptQuestions());
        ConceptQuestion question = quizSession.getConceptQuestions().get(0);

//        quizSubmissionService.updateTaxonomyConcept(submissionId,question.getQuestionNumber(),question.getTaxonomyLevel().name(),question.getType(),question.getConcept(),question.getConceptQuestion());


        if(question.getType().equals("counterQuestion")){
            if(question.getDepth()<assessmentDepth){
                int conceptQuestionNumber = question.getConceptQuestion();
                QuestionSubmission questionSubmission = quizSubmissionService.getQuestion(submissionId,conceptQuestionNumber);
                System.out.println(submissionId+" : "+conceptQuestionNumber);
                question = quizSession.getConceptQuestions().get(0);
                System.out.println("concept question: "+question);
                if(questionSubmission.getResult()){
                    if(question.getTaxonomyLevel().ordinal() > 0){
                        question.setTaxonomyLevel(ConceptQuestion.TaxonomyLevel.values()[ConceptQuestion.TaxonomyLevel.valueOf(questionSubmission.getTaxonomy()).ordinal()-1]);
                    }
                    else{
                        question.setTaxonomyLevel(ConceptQuestion.TaxonomyLevel.values()[ConceptQuestion.TaxonomyLevel.valueOf(questionSubmission.getTaxonomy()).ordinal()]);
                    }
                }else{
                    System.out.println("Taxonomy level: "+question.getTaxonomyLevel()+" - "+question.getTaxonomyLevel().ordinal());
                    if(question.getTaxonomyLevel().ordinal() < ConceptQuestion.TaxonomyLevel.values().length-1) {
                        question.setTaxonomyLevel(ConceptQuestion.TaxonomyLevel.values()[ConceptQuestion.TaxonomyLevel.valueOf(questionSubmission.getTaxonomy()).ordinal() + 1]);
                    }
                    else{
                        question.setTaxonomyLevel(ConceptQuestion.TaxonomyLevel.values()[ConceptQuestion.TaxonomyLevel.valueOf(questionSubmission.getTaxonomy()).ordinal()]);
                    }
                }
            }
        }

        quizSession.getConceptQuestions().remove(0);

        quizSessionRepo.save(quizSession);

        System.out.println("number of concept questions left: "+quizSession.getConceptQuestions().size());

        return question;


//        if(question.getType().equals("counterQuestion")){
//
//            int conceptQuestionNumber = question.getConceptQuestion();
//            String submissionId = quizSession.getSubmissionId();
//            QuestionSubmission questionSubmission = quizSubmissionService.getQuestion(submissionId, question.getQuestionNumber());
//            if(questionSubmission.isResult()) {
//                String taxonomy = questionSubmission.getTaxonomy();
                //Compare question1's taxonomy and question's taxonomy;
                //If
//                if(question.getTaxonomyLevel().compareTo(ConceptQuestion.TaxonomyLevel.valueOf(taxonomy))<0){
//                    quizSession.getConceptQuestions().remove(0);
//                    quizSessionRepo.save(quizSession);
//                    return question;
//                }
//                else{
//                    quizSession.getConceptQuestions().remove(0);
//                    quizSessionRepo.save(quizSession);
//                    getQuestion(quizId, learnerId);
//                }
//            }
//            else{
//                String taxonomy = question1.getTaxonomy();
//                //Compare question1's taxonomy and question's taxonomy;
//                //If
//                if(question.getTaxonomyLevel().compareTo(ConceptQuestion.TaxonomyLevel.valueOf(taxonomy))>0){
//                    quizSession.getConceptQuestions().remove(0);
//                    quizSessionRepo.save(quizSession);
//                    return question;
//                }
//                else{
//                    quizSession.getConceptQuestions().remove(0);
//                    quizSessionRepo.save(quizSession);
//                    getQuestion(quizId, learnerId);
//                }
//
//            }
//        }
//        else{
//            quizSession.getConceptQuestions().remove(0);
//            quizSessionRepo.save(quizSession);
//            return question;
//        }
    }
}
