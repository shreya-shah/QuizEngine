package com.stackroute.quizengine.service;

import java.util.Optional;

public interface QuizEngineService {

    Optional getQuestion(String quizId);

}
