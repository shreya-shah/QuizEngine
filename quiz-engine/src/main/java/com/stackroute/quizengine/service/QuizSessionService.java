package com.stackroute.quizengine.service;

import com.stackroute.quizengine.model.ConceptQuestion;
import com.stackroute.quizengine.model.QuizSession;
import com.stackroute.quizengine.model.Topology;

import java.util.List;

public interface QuizSessionService {

    String createSession(String quizId);

    QuizSession updateSession(String sessionId, String learnerId, String submissionId, List<ConceptQuestion> conceptQuestions);

    String getSubmissionIdByLearnerIdAndQuizId(String learnerId, String quizId);

    ConceptQuestion getQuestion(String learnerId, String quizId);

}
