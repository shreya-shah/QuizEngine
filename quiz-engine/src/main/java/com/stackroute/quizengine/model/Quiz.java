package com.stackroute.quizengine.model;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;


public class Quiz {
    @Id
    private String quizId;

    @NotNull
    private String role;
    private ProfiLevel proficiencyLevel;
    private int assessmentDepth;

    public enum ProfiLevel {
        Novice, Beginner, Competent, Proficient, Expert
    }

    private List<Section> sections;
    public Quiz() {
    }

    public Quiz(String quizId, @NotNull String role, ProfiLevel proficiencyLevel, int assessmentDepth) {
        this.quizId = quizId;
        this.role = role;
        this.proficiencyLevel = proficiencyLevel;
        this.assessmentDepth = assessmentDepth;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getQuizId() {
        return this.quizId;
    }

    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }

    public int getAssessmentDepth() {
        return assessmentDepth;
    }

    public void setAssessmentDepth(int assessmentDepth) {
        this.assessmentDepth = assessmentDepth;
    }

    public List<Section> getSections() {
        return this.sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }
    public ProfiLevel getProficiencyLevel() {
        return this.proficiencyLevel;
    }

    public void setProficiencyLevel(ProfiLevel proficiencyLevel) {
        this.proficiencyLevel = proficiencyLevel;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "quizId='" + quizId + '\'' +
                ", role='" + role + '\'' +
                ", proficiencyLevel=" + proficiencyLevel +
                ", assessmentDepth=" + assessmentDepth +
                ", sections=" + sections +
                '}';
    }
}
