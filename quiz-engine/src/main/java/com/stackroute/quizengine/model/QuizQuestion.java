package com.stackroute.quizengine.model;

public class QuizQuestion {
    int questionCode;
    String answer;

    public QuizQuestion() {
    }

    public QuizQuestion(int questionCode, String answer) {
        this.questionCode = questionCode;
        this.answer = answer;
    }

    public int getQuestionCode() {
        return this.questionCode;
    }

    public void setQuestionCode(int questionCode) {
        this.questionCode = questionCode;
    }

    public String getAnswer() {
        return this.answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
