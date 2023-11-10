package com.bku.training.quizfe.service.impl;

import com.bku.training.quizfe.entities.LoginResponse;
import com.bku.training.quizfe.entities.QuizDTO;
import com.bku.training.quizfe.entities.SubmitQuiz;
import com.bku.training.quizfe.service.QuizService;
import com.bku.training.quizfe.utils.HeaderUtils;
import com.bku.training.quizfe.utils.ServerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author Nam Tran
* @project Quiz
 **/
@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Add new Quiz
     */
    @Override
    public QuizDTO addNewQuiz(String api, QuizDTO quizDTO, LoginResponse loginResponse) {
        HttpEntity<QuizDTO> entity = HeaderUtils.getHeader(quizDTO, loginResponse);
        return restTemplate.exchange(
                ServerUtils.DOMAIN + api, HttpMethod.POST, entity, QuizDTO.class).getBody();
    }

    /**
     * Update status for new Quiz from DRAFT to PUBLISHED
     */
    @Override
    public String submitQuiz(String api, Integer id, LoginResponse loginResponse) {
        HttpEntity<?> entity = HeaderUtils.getHeader(id, loginResponse);
        return restTemplate.exchange(
                ServerUtils.DOMAIN + api, HttpMethod.POST, entity, String.class).getBody();
    }

    /**
     * get All List quiz by status
     */
    @Override
    public List<QuizDTO> getListQuizByStatus(String api) {
        return restTemplate.exchange(
                ServerUtils.DOMAIN + api, HttpMethod.GET, null
                , new ParameterizedTypeReference<List<QuizDTO>>() {}).getBody();
    }

    /**
     * Delete all question which picked up in Quiz (before create quiz completely)
     * <button previous></button>
     */
    @Override
    public void deleteQuestionInQuizDetails(String api, Integer quizId, LoginResponse loginResponse) {
        HttpEntity<Integer> entity = HeaderUtils.getHeader(quizId, loginResponse);
        restTemplate.exchange(
                ServerUtils.DOMAIN + api, HttpMethod.DELETE, entity, Void.class);
    }

    /**
     * Get Quiz by id
     */
    @Override
    public QuizDTO getQuizById(String api, Integer quizId, LoginResponse loginResponse) {
        HttpEntity<Integer> entity = HeaderUtils.getHeader(quizId, loginResponse);
        return restTemplate.exchange(
                ServerUtils.DOMAIN + api, HttpMethod.POST, entity, QuizDTO.class).getBody();
    }

    /**
     * Search List QuizDTO by value search
     */
    @Override
    public List<QuizDTO> searchQuiz(String api) {
        return restTemplate.exchange(
                ServerUtils.DOMAIN + api, HttpMethod.GET
                , null, new ParameterizedTypeReference<List<QuizDTO>>() {}).getBody();
    }

    /**
     * Calculate point of Quiz
     */
    @Override
    public Double calculatePointOfQuiz(String api, SubmitQuiz submitQuiz, LoginResponse loginResponse) {
        HttpEntity<SubmitQuiz> entity = HeaderUtils.getHeader(submitQuiz, loginResponse);
        return restTemplate.exchange(
                ServerUtils.DOMAIN + api, HttpMethod.POST, entity, Double.class).getBody();
    }
}
