package com.stackroute.quizengine.model;

public class ConceptTaxonomy {
    private String concept;
    private String taxonomy;
    private int totalQuestions;
    private int correctlyAnswered;

    public ConceptTaxonomy() {
    }

    public ConceptTaxonomy(String concept, String taxonomy, int totalQuestions, int correctlyAnswered) {
        this.concept = concept;
        this.taxonomy = taxonomy;
        this.totalQuestions = totalQuestions;
        this.correctlyAnswered = correctlyAnswered;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public String getTaxonomy() {
        return taxonomy;
    }

    public void setTaxonomy(String taxonomy) {
        this.taxonomy = taxonomy;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public int getCorrectlyAnswered() {
        return correctlyAnswered;
    }

    public void setCorrectlyAnswered(int correctlyAnswered) {
        this.correctlyAnswered = correctlyAnswered;
    }
}
