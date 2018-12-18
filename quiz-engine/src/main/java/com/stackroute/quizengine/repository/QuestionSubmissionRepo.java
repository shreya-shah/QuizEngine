package com.stackroute.quizengine.repository;

import com.stackroute.quizengine.model.QuestionSubmission;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface QuestionSubmissionRepo extends MongoRepository<QuestionSubmission,String> {
    QuestionSubmission findByQuestionId(long questionId);
    QuestionSubmission findByQuestionNumber(int questionNumber);
}