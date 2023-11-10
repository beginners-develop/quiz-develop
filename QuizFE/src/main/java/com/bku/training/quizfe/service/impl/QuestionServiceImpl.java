package com.bku.training.quizfe.service.impl;

import com.bku.training.quizfe.entities.AnswerRequest;
import com.bku.training.quizfe.entities.LoginResponse;
import com.bku.training.quizfe.entities.QuestionDTO;
import com.bku.training.quizfe.entities.QuestionRequest;
import com.bku.training.quizfe.service.QuestionService;
import com.bku.training.quizfe.utils.HeaderUtils;
import com.bku.training.quizfe.utils.ServerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

/**
 * @author Nam Tran
* @project Quiz
 **/
@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Add new question with answer and topic
     */
    @Override
    public String addNewQuestion(String api, QuestionRequest questionRequest, LoginResponse loginResponse) {
        HttpEntity<QuestionRequest> entity = HeaderUtils.getHeader(questionRequest, loginResponse);
        return restTemplate.exchange(ServerUtils.DOMAIN + api, HttpMethod.POST, entity, String.class).getBody();
    }

    /**
     * Update question with answer and topic
     */
    @Override
    public String updateQuestion(String api, QuestionRequest questionRequest, LoginResponse loginResponse) {
        HttpEntity<QuestionRequest> entity = HeaderUtils.getHeader(questionRequest, loginResponse);
        return restTemplate.exchange(ServerUtils.DOMAIN + api, HttpMethod.PUT, entity, String.class).getBody();
    }

    /**
     * search question by value or topic
     */
    @Override
    public List<QuestionDTO> searchQuestionOrTopic(String api, Map<String, String> mapSearch, LoginResponse loginResponse) {
        HttpEntity<?> entity = HeaderUtils.getHeader(mapSearch, loginResponse);
        return restTemplate.exchange(
                ServerUtils.DOMAIN + api, HttpMethod.POST, entity
                , new ParameterizedTypeReference<List<QuestionDTO>>() {}).getBody();
    }

    /**
     * get list question when user pick up from question Bank
     */
    @Override
    public List<QuestionDTO> getListQuestionByListId(String api, List<Integer> ids, LoginResponse loginResponse) {
        HttpEntity<?> entity = HeaderUtils.getHeader(ids, loginResponse);
        return restTemplate.exchange(
                ServerUtils.DOMAIN + api, HttpMethod.POST, entity,
                new ParameterizedTypeReference<List<QuestionDTO>>() {}).getBody();
    }

    /**
     * Get all list question in DB
     */
    @Override
    public List<QuestionDTO> getListQuestion(String api, QuestionDTO questionDTO, LoginResponse loginResponse) {
        HttpEntity<QuestionDTO> entity = HeaderUtils.getHeader(questionDTO, loginResponse);
        return restTemplate.exchange(
                ServerUtils.DOMAIN + api, HttpMethod.GET, entity,
                new ParameterizedTypeReference<List<QuestionDTO>>() {}).getBody();
    }

    /**
     * delete question by Id from question Bank (remove from DB)
     */
    @Override
    public void deleteQuestion(String api, List<Integer> ids, LoginResponse loginResponse) {
        HttpEntity<?> entity = HeaderUtils.getHeader(ids, loginResponse);
        restTemplate.exchange(ServerUtils.DOMAIN + api, HttpMethod.DELETE, entity, Void.class);
    }

    /**
     * Get question by Id
     */
    @Override
    public QuestionDTO getQuestionDTO(String api, Integer id, LoginResponse loginResponse) {
        HttpEntity<?> entity = HeaderUtils.getHeader(id, loginResponse);
        return restTemplate.exchange(
                ServerUtils.DOMAIN + api, HttpMethod.POST, entity, QuestionDTO.class).getBody();
    }

    /**
     * Delete answer by answer and question id
     */
    @Override
    public void deleteAnswer(String api, AnswerRequest answerRequest, LoginResponse loginResponse) {
        HttpEntity<?> entity = HeaderUtils.getHeader(answerRequest, loginResponse);
        restTemplate.exchange(ServerUtils.DOMAIN + api, HttpMethod.DELETE, entity, Void.class);
    }
}
