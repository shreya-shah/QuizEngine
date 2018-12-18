package com.stackroute.quizengine.service;

import com.stackroute.quizengine.model.QuizQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Service
public class QuizQuestionEvaluationServiceImpl implements QuizQuestionEvaluationService {
    @Autowired
    RestTemplate restTemplate;
    @Value("${questionservice.url}")
    String url;
//    String url="http://172.23.239.124:8080/api/v1/questions/{questionId}";

    @Override
    public Boolean evaluateAnswer(int questionCode, String chosenOption) {
        Map<String, Integer> uriParams = new HashMap<String, Integer>();
        uriParams.put("questionCode", questionCode);
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
        QuizQuestion question = this.restTemplate.getForObject(builder.buildAndExpand(uriParams).toUri(), QuizQuestion.class);
        if (chosenOption.equals(question.getAnswer())) {
            System.out.println("true");
            return true;
        } else {
            return false;
        }
    }
}
