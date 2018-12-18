package com.stackroute.quizengine.model;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

public class Question {

    String questionId;

    private String name;
    private String questionCode;
    private List<String> options;
    private String answer;
    private int score;
    private int approximateTime;
    private String term;
    private String concept;

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    private int questionNumber;

    public Question(){

    }

    public Question(String questionId, @NotNull String name, String questionCode, List<String> options, String answer, int score, int approximateTime, String term, String concept) {
        this.questionId = questionId;
        this.name = name;
        this.questionCode = questionCode;
        this.options = options;
        this.answer = answer;
        this.score = score;
        this.approximateTime = approximateTime;
        this.term = term;
        this.concept = concept;
    }

    public String getQuestionCode() {
        return questionCode;
    }

    public void setQuestionCode(String questionCode) {
        this.questionCode = questionCode;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getApproximateTime() {
        return approximateTime;
    }

    public void setApproximateTime(int approximateTime) {
        this.approximateTime = approximateTime;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

}
