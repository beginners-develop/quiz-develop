package com.bku.training.quizfe.service;

import com.bku.training.quizfe.entities.LoginResponse;
import com.bku.training.quizfe.entities.TopicDTO;

import java.util.List;

/**
 * @author Nam Tran
* @project Quiz
 **/
public interface TopicService {

    List<TopicDTO> listTopic(String api, TopicDTO topicDTO,LoginResponse loginResponse);
}
