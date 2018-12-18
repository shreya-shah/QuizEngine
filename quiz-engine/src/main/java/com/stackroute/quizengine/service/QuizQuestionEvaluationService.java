package com.stackroute.quizengine.service;

public interface QuizQuestionEvaluationService {
    Boolean evaluateAnswer(int questionId,String chosenOption);
}
