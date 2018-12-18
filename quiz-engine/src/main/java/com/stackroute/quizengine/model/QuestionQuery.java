package com.stackroute.quizengine.model;

import java.util.ArrayList;

public class QuestionQuery{
    private String url;
    private ArrayList<String> args;

    public QuestionQuery(String url, ArrayList args) {
        this.url = url;
        this.args = args;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ArrayList getArgs() {
        return args;
    }

    public void setArgs(ArrayList args) {
        this.args = args;
    }

}

