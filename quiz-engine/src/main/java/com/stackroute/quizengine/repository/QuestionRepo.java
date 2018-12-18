package com.stackroute.quizengine.repository;

import com.stackroute.quizengine.model.QuestionSubmission;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface QuestionRepo extends MongoRepository<QuestionSubmission,String> {

    QuestionSubmission findByQuestionId(long questionId);

    QuestionSubmission findByQuestionNumber(int questionNumber);

}

