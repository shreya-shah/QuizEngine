package com.stackroute.quizengine.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.List;

@Document
public class QuizSubmission {

    @Id
    private String submissionId;

    @NotNull
    private String learnerId;

    @NotNull
    private String quizId;

    private String sessionId;

    private List<String> role;

    private List<String> skill;

    private List<String> concepts;

    private List<ConceptTaxonomy> conceptTaxonomies;

    private List<QuestionSubmission> questionSubmission;

    private double score;


    public QuizSubmission() {
    }

    public QuizSubmission(String submissionId, @NotNull String learnerId, @NotNull String quizId, String sessionId, List<String> role, List<String> skill, List<String> concepts, List<ConceptTaxonomy> conceptTaxonomy, List<QuestionSubmission> questionSubmission,double score) {
        this.submissionId = submissionId;
        this.learnerId = learnerId;
        this.quizId = quizId;
        this.sessionId = sessionId;
        this.role = role;
        this.skill = skill;
        this.concepts = concepts;
        this.questionSubmission = questionSubmission;
        this.conceptTaxonomies = conceptTaxonomy;
        this.questionSubmission = questionSubmission;
        this.score = score;
    }

    public String getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(String submissionId) {
        this.submissionId = submissionId;
    }

    public String getLearnerId() {
        return learnerId;
    }

    public void setLearnerId(String learnerId) {
        this.learnerId = learnerId;
    }

    public String getQuizId() {
        return quizId;
    }

    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public List<String> getRole() {
        return role;
    }

    public List<ConceptTaxonomy> getConceptTaxonomy() {
        return conceptTaxonomies;
    }

    public void setConceptTaxonomy(List<ConceptTaxonomy> conceptTaxonomy) {
        this.conceptTaxonomies = conceptTaxonomy;
    }

    public List<QuestionSubmission> getQuestionSubmission() {
        return questionSubmission;
    }

    public void setQuestionSubmission(List<QuestionSubmission> questionSubmission) {
        this.questionSubmission = questionSubmission;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }

    public List<String> getSkill() {
        return skill;
    }

    public void setSkill(List<String> skill) {
        this.skill = skill;
    }

    public List<String> getConcepts() {
        return concepts;
    }

    public void setConcepts(List<String> concepts) {
        this.concepts = concepts;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
