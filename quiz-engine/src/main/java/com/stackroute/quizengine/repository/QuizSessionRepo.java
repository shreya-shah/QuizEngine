package com.stackroute.quizengine.repository;

import com.stackroute.quizengine.model.QuizSession;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface QuizSessionRepo extends MongoRepository<QuizSession, String> {

    public QuizSession findByQuizId(String quizId);

    public QuizSession findByLearnerIdAndQuizId(String learnerId,String quizId);

}
