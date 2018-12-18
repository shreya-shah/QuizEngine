package com.stackroute.quizengine.repository;

import com.stackroute.quizengine.model.Topology;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface QuizTopologyRepo extends MongoRepository<Topology,String> {

     Topology findByQuizId(String quizId);
}
