package com.bku.training.quiz.services;

import com.bku.training.quiz.entities.Answer;
import com.bku.training.quiz.dto.AnswerRequest;

import java.util.List;

/**
 * @author Nam Tran
 * @project Quiz
 **/
public interface AnswerService {

    Answer addNewAnswer(Answer answer);

    List<Answer> getAll();

    Answer getAnswerById(Integer id);

    Answer updateAnswer(Answer answer);

    void deleteAnswerByQuestionId(AnswerRequest answerRequest);

}
