package com.bku.training.quizfe.service;

import com.bku.training.quizfe.entities.AnswerRequest;
import com.bku.training.quizfe.entities.LoginResponse;
import com.bku.training.quizfe.entities.QuestionDTO;
import com.bku.training.quizfe.entities.QuestionRequest;

import java.util.List;
import java.util.Map;

/**
 * @author Nam Tran
* @project Quiz
 **/

public interface QuestionService {

    String addNewQuestion(String api, QuestionRequest questionRequest, LoginResponse loginResponse);
    String updateQuestion(String api, QuestionRequest questionRequest, LoginResponse loginResponse);
    List<QuestionDTO> searchQuestionOrTopic(String api, Map<String, String> mapSearch, LoginResponse loginResponse);
    List<QuestionDTO> getListQuestionByListId(String api, List<Integer> ids, LoginResponse loginResponse);
    List<QuestionDTO> getListQuestion(String api, QuestionDTO questionDTO, LoginResponse loginResponse);
    void deleteQuestion(String api, List<Integer> ids, LoginResponse loginResponse);
    QuestionDTO getQuestionDTO(String api, Integer id, LoginResponse loginResponse);

    void deleteAnswer(String api, AnswerRequest answerRequest, LoginResponse loginResponse);

}
