package com.bku.training.quizfe.service;

import com.bku.training.quizfe.entities.LoginResponse;
import com.bku.training.quizfe.entities.QuizDTO;
import com.bku.training.quizfe.entities.SubmitQuiz;

import java.util.List;

/**
 * @author Nam Tran
* @project Quiz
 **/
public interface QuizService {

    QuizDTO addNewQuiz(String api, QuizDTO quizDTO, LoginResponse loginResponse);

    String submitQuiz(String api, Integer id, LoginResponse loginResponse);

    List<QuizDTO> getListQuizByStatus(String api);

    void deleteQuestionInQuizDetails(String api, Integer quizId, LoginResponse loginResponse);

    QuizDTO getQuizById(String api, Integer quizId, LoginResponse loginResponse);

    List<QuizDTO> searchQuiz(String api);

    Double calculatePointOfQuiz(String api, SubmitQuiz submitQuiz, LoginResponse loginResponse);

}
