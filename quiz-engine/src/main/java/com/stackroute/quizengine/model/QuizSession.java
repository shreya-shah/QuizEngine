package com.stackroute.quizengine.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Document
public class QuizSession {

    @Id
    String sessionId;
    String learnerId;
    String quizId;
    String submissionId;
    //Map<String, String> userSubmission = new HashMap<>();
    List<ConceptQuestion> conceptQuestions ;

    public QuizSession() {
    }

    public List<ConceptQuestion> getConceptQuestions() {
        return conceptQuestions;
    }

    public void setConceptQuestions(List<ConceptQuestion> conceptQuestions) {
        this.conceptQuestions = conceptQuestions;
    }

    public QuizSession(String sessionId, String learnerId, String quizId, String submissionId, List<ConceptQuestion> conceptQuestions) {
        this.sessionId = sessionId;
        this.learnerId = learnerId;
        this.quizId = quizId;
        this.submissionId = submissionId;
        //this.userSubmission = userSubmission;
        this.conceptQuestions = conceptQuestions;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getLearnerId() {
        return this.learnerId;
    }

    public void setLearnerId(String learnerId) {
        this.learnerId = learnerId;
    }

    public String getQuizId() {
        return this.quizId;
    }

    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }

    public String getSubmissionId() {
        return this.submissionId;
    }

    public void setSubmissionId(String submissionId) {
        this.submissionId = submissionId;
    }

}
