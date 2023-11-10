package com.bku.training.quizfe.service.impl;

import com.bku.training.quizfe.entities.LoginResponse;
import com.bku.training.quizfe.entities.TopicDTO;
import com.bku.training.quizfe.service.TopicService;
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
public class TopicServiceImpl implements TopicService {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Get list topic
     */
    @Override
    public List<TopicDTO> listTopic(String api, TopicDTO topicDTO, LoginResponse loginResponse) {
        HttpEntity<TopicDTO> entity = HeaderUtils.getHeader(topicDTO,loginResponse);
        return restTemplate.exchange(
                ServerUtils.DOMAIN + api, HttpMethod.GET, entity
                , new ParameterizedTypeReference<List<TopicDTO>>() {}).getBody();
    }

}
