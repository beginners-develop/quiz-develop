package com.bku.training.quizfe.entities;

import lombok.Data;

/**
 * @author Nam Tran
* @project Quiz
 **/
@Data
public class AnswerRequest {

    private Integer questionId;
    private Integer answerId;
    public AnswerRequest(Integer questionId, Integer answerId) {
        this.questionId = questionId;
        this.answerId = answerId;
    }
}
