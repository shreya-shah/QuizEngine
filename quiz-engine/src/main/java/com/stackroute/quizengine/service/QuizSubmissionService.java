package com.stackroute.quizengine.service;

import com.stackroute.quizengine.model.QuestionSubmission;
import com.stackroute.quizengine.model.QuizSubmission;

import java.util.List;

public interface QuizSubmissionService  {

    String createQuizSubmission(String sessionId,String learnerId,String quizId, List<String>roles, List<String>skills, List<String> concepts);
    QuizSubmission getSubmissionById(String submissionId);

    QuizSubmission updateTaxonomyConcept(String submissionId, int questionNumber, String taxonomy, String type, String concept,int conceptQuestionNumber);

    QuizSubmission updateQuestionDetails(String submissionId, int questionNumber, String QuestionId,String questionCode);

    QuizSubmission updateQuestionDetailsWithAnswer(String submissionId,int questionCode,int questionNumber,String choosenanswer,boolean result);

    QuestionSubmission getQuestion(String submissionId,int questionNumber);//           quizSubmission.setConceptMaps(concepts.get(i),getTaxonomy(concepts.get(i),submissionId));

    String getTaxonomy(String concepts,String submissionId);

}
