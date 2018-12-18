package com.stackroute.quizengine.repository;

import com.stackroute.quizengine.model.QuizSubmission;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizSubmissionRepo extends MongoRepository<QuizSubmission, String> {
//        @Query("")
//    List<QuizSubmission> findByConcept(String concept);

}
