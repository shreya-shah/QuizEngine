package com.stackroute.quizengine.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Document
public class Topology {
    @Id
    private String topologyId;
    private String quizId;
    private double time;
    private int numberOfQuestions;
    private int timePerConceptQuestion;
    private int assessmentDepth;
    private Date builtOn;
    private List<ConceptQuestion> conceptQuestion;
    private QuestionQuery conceptQuestionQuery;
    private ConceptQuery conceptQuery;

    public Topology(String quizId, double time, int numberOfQuestions, int timePerConceptQuestion, int assessmentDepth, Date builtOn, QuestionQuery conceptQuestionQuery, ConceptQuery conceptQuery, List<ConceptQuestion> conceptQuestion) {
        this.topologyId = topologyId;
        this.quizId = quizId;
        this.time = time;
        this.numberOfQuestions = numberOfQuestions;
        this.timePerConceptQuestion = timePerConceptQuestion;
        this.assessmentDepth = assessmentDepth;
        this.builtOn = builtOn;
        this.conceptQuestionQuery = conceptQuestionQuery;
        this.conceptQuery = conceptQuery;
        this.conceptQuestion = conceptQuestion;
    }

    public String getTopologyId() {
        return topologyId;
    }

    public void setTopologyId(String topologyId) {
        this.topologyId = topologyId;
    }

    public String getQuizId() {
        return quizId;
    }

    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public int getTimePerConceptQuestion() {
        return timePerConceptQuestion;
    }

    public void setTimePerConceptQuestion(int timePerConceptQuestion) {
        this.timePerConceptQuestion = timePerConceptQuestion;
    }

    public int getAssessmentDepth() {
        return assessmentDepth;
    }

    public void setAssessmentDepth(int assessmentDepth) {
        this.assessmentDepth = assessmentDepth;
    }

    public Date getBuiltOn() {
        return builtOn;
    }

    public void setBuiltOn(Date builtOn) {
        this.builtOn = builtOn;
    }

    public List<ConceptQuestion> getConceptQuestion() {
        return conceptQuestion;
    }

    public void setConceptQuestion(ArrayList<ConceptQuestion> conceptQuestion) {
        this.conceptQuestion = conceptQuestion;
    }

    public QuestionQuery getConceptQuestionQuery() {
        return conceptQuestionQuery;
    }

    public void setConceptQuestionQuery(QuestionQuery conceptQuestionQuery) {
        this.conceptQuestionQuery = conceptQuestionQuery;
    }

    public ConceptQuery getConceptQuery() {
        return conceptQuery;
    }

    public void setConceptQuery(ConceptQuery conceptQuery) {
        this.conceptQuery = conceptQuery;
    }
}
