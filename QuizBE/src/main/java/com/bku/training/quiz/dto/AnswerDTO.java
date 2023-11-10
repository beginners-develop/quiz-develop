package com.bku.training.quiz.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Nam Tran
 * @project Quiz
 **/
@Getter
@Setter
public class AnswerDTO {

    private Integer id;
    private String answer;
    private boolean correctAnswer;
//    private Question question;


    @Override
    public String toString() {
        return "AnswerDTO{" +
                "answer='" + answer + '\'' +
                ", correctAnswer=" + correctAnswer +
                '}';
    }
}
