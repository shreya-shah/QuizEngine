package com.stackroute.quizengine.model;

public class ConceptQuestion {

    public enum TaxonomyLevel{
        Creating, Evaluating, Analyzing, Applying, Understanding, Remembering
    }

    int questionNumber;
    String concept;
    TaxonomyLevel taxonomyLevel;
    String type;
    int conceptQuestion;
    int depth;

    public ConceptQuestion(int questionNumber, String concept, TaxonomyLevel taxonomyLevel, String type, int conceptQuestion, int depth) {
        this.questionNumber = questionNumber;
        this.concept = concept;
        this.taxonomyLevel = taxonomyLevel;
        this.type = type;
        this.conceptQuestion = conceptQuestion;
        this.depth = depth;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public TaxonomyLevel getTaxonomyLevel() {
        return taxonomyLevel;
    }

    public void setTaxonomyLevel(TaxonomyLevel taxonomyLevel) {
        this.taxonomyLevel = taxonomyLevel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getConceptQuestion() {
        return conceptQuestion;
    }

    public void setConceptQuestion(int conceptQuestion) {
        this.conceptQuestion = conceptQuestion;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    @Override
    public String toString() {
        return "ConceptQuestion{" +
                "questionNumber=" + questionNumber +
                ", concept='" + concept + '\'' +
                ", taxonomyLevel=" + taxonomyLevel +
                ", type='" + type + '\'' +
                ", conceptQuestion=" + conceptQuestion +
                ", depth=" + depth +
                '}';
    }
}
