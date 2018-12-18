package com.stackroute.quizengine.model;


public class ConceptMap{
    private String concept;
    private String taxonomy;


    public ConceptMap(String concept, String taxonomy) {
        this.concept = concept;
        this.taxonomy = taxonomy;
    }

    public ConceptMap() {
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

    @Override
    public String toString() {
        return "ConceptMap{" +
                "concept='" + concept + '\'' +
                ", taxonomy='" + taxonomy + '\'' +
                '}';
    }
}
