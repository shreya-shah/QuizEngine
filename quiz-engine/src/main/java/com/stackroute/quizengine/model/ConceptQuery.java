package com.stackroute.quizengine.model;

import org.codehaus.jackson.annotate.JsonAutoDetect;

import java.util.List;

@JsonAutoDetect
public class ConceptQuery{
    String url;
    List<String> roles;
    List<String> proficiencyLevel;
    List<String> skills;
    List<String> concepts;
    int noOfConcepts;
    boolean distinct;
    String sortBy;
    String sortOrder;
    List<ConceptMap> conceptMap;

    public ConceptQuery(String url, List<String> roles, List<String> skills, List<String> concepts,int noOfConcepts,List<String> proficiencyLevel,boolean distinct, String sortBy, String sortOrder, List<ConceptMap> conceptMap) {
        this.url = url;
        this.roles = roles;
        this.skills = skills;
        this.proficiencyLevel = proficiencyLevel;
        this.concepts = concepts;
        this.noOfConcepts = noOfConcepts;
        this.distinct = distinct;
        this.sortBy = sortBy;
        this.sortOrder = sortOrder;
        this.conceptMap = conceptMap;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<String> getProficiencyLevel() {
        return proficiencyLevel;
    }

    public void setProficiencyLevel(List<String> proficiencyLevel) {
        this.proficiencyLevel = proficiencyLevel;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public List<String> getConcepts() {
        return concepts;
    }

    public void setConcepts(List<String> concepts) {
        this.concepts = concepts;
    }

    public int getNoOfConcepts() {
        return noOfConcepts;
    }

    public void setNoOfConcepts(int noOfConcepts) {
        this.noOfConcepts = noOfConcepts;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public List<ConceptMap> getConceptMap() {
        return conceptMap;
    }

    public void setConceptMap(List<ConceptMap> conceptMap) {
        this.conceptMap = conceptMap;
    }

    @Override
    public String toString() {
        return "ConceptQuery{" +
                "url='" + url + '\'' +
                ", roles=" + roles +
                ", skills=" + skills +
                ", proficiencyLevel=" + proficiencyLevel +
                ", concepts=" + concepts +
                ", noOfConcepts=" + noOfConcepts +
                ", distinct=" + distinct +
                ", sortBy='" + sortBy + '\'' +
                ", sortOrder='" + sortOrder + '\'' +
                ", conceptMap=" + conceptMap +
                '}';
    }
}

