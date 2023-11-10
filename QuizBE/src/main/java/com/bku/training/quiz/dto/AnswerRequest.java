package com.bku.training.quiz.dto;

import lombok.Data;

/**
 * @author Nam Tran
 * @project QuizBE
 **/
@Data
public class AnswerRequest {

    private Integer questionId;
    private Integer answerId;

}
