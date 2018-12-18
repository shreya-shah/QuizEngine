package com.stackroute.quizengine.model;

import java.util.List;

public class Section {
    private String sectionName;
    private int maxScore;
    private List<String> concepts;
    private List<String> skills;

    private enum DiffLevel {
        easy, medium, hard
    }

    private DiffLevel difficultyLevel;
    private double weightage;

    public Section() {
    }

    public Section(String sectionName, int maxScore, List<String> concepts, List<String> skills, double weightage, DiffLevel diffLevel) {

        this.sectionName = sectionName;
        this.maxScore = maxScore;
        this.concepts = concepts;
        this.skills = skills;
        this.weightage = weightage;
        this.difficultyLevel = diffLevel;
    }

    public String getSectionName() {
        return this.sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public int getMaxScore() {
        return this.maxScore;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    public List<String> getConcepts() {
        return this.concepts;
    }

    public void setConcepts(List<String> concepts) {
        this.concepts = concepts;
    }

    public List<String> getSkills() {
        return this.skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public DiffLevel getDifficultyLevel() {
        return this.difficultyLevel;
    }

    public void setDifficultyLevel(DiffLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public double getWeightage() {
        return this.weightage;
    }

    public void setWeightage(double weightage) {
        this.weightage = weightage;
    }
}
