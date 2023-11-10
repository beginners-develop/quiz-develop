package com.bku.training.quiz.services;

import com.bku.training.quiz.dto.QuizDTO;
import com.bku.training.quiz.dto.SubmitQuiz;
import com.bku.training.quiz.entities.Quiz;

import java.util.List;

/**
 * @author Nam Tran
 * @project Quiz
 **/
public interface QuizService {

    QuizDTO addNewQuiz(QuizDTO requestQuizDTO);

    QuizDTO updateNewQuiz(QuizDTO requestQuizDTO);

    Quiz updateStatusQuiz(Integer id);

    List<QuizDTO> getAllByStatus(String status, Integer page, Integer size);

    List<QuizDTO> getAllQuizByQuizName(String quizName);

    QuizDTO getQuizById(Integer id);

    void deleteQuizById(Integer id);

    Double calculatePointOfQuiz(SubmitQuiz submitQuiz);

}
