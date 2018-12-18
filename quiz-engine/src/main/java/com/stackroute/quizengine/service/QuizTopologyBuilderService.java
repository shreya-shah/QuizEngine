package com.stackroute.quizengine.service;

import com.stackroute.quizengine.model.ConceptMap;
import com.stackroute.quizengine.model.ConceptQuestion;
import com.stackroute.quizengine.model.Quiz;
import com.stackroute.quizengine.model.Topology;

import java.util.List;

public interface QuizTopologyBuilderService {
    Topology createTopology(String quizId);
    Topology getTopologyByQuizId(String quizId);
    List<ConceptQuestion> getConceptQuestionsByQuizId(String quizId);
    Quiz getQuizByQuizId(String quizId);
    List<ConceptMap> getConceptMapByQuiz(Quiz quizId);
}
