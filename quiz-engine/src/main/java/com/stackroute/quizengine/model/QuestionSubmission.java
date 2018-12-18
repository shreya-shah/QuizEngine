package com.stackroute.quizengine.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class QuestionSubmission {

    @Id
    private String questionId;

    private String chosenanswer;

    private  String questionType;

    private boolean result;

    private String concept;

    private int questionNumber;

    private int questionCode;

    public int getQuestionCode() {
        return questionCode;
    }

    public void setQuestionCode(int questionCode) {
        this.questionCode = questionCode;
    }

    private int conceptQuestionNumber;

    private String taxonomy;

    public QuestionSubmission() {
    }

    public QuestionSubmission(String questionId, String chosenanswer, String questionType, boolean result, String concept, int questionNumber, int conceptQuestionNumber, String taxonomy, int questionCode) {
        this.questionId = questionId;
        this.chosenanswer = chosenanswer;
        this.questionType = questionType;
        this.result = result;
        this.concept = concept;
        this.questionNumber = questionNumber;
        this.conceptQuestionNumber = conceptQuestionNumber;
        this.taxonomy = taxonomy;
        this.questionCode = questionCode;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getChosenanswer() {
        return chosenanswer;
    }

    public void setChosenanswer(String chosenanswer) {
        this.chosenanswer = chosenanswer;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public boolean getResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public int getConceptQuestionNumber() {
        return conceptQuestionNumber;
    }

    public void setConceptQuestionNumber(int conceptQuestionNumber) {
        this.conceptQuestionNumber = conceptQuestionNumber;
    }

    public String getTaxonomy() {
        return taxonomy;
    }

    public void setTaxonomy(String taxonomy) {
        this.taxonomy = taxonomy;
    }

    @Override
    public String toString() {
        return "QuestionSubmission{" +
                "questionId='" + questionId + '\'' +
                ", chosenanswer='" + chosenanswer + '\'' +
                ", questionType='" + questionType + '\'' +
                ", result=" + result +
                ", concept='" + concept + '\'' +
                ", questionNumber=" + questionNumber +
                ", questionCode=" + questionCode +
                ", conceptQuestionNumber=" + conceptQuestionNumber +
                ", taxonomy='" + taxonomy + '\'' +
                '}';
    }
}


