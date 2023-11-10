package com.bku.training.quizfe.entities;

import lombok.Data;

@Data
public class AnswerDTO {

    private Integer id;
    private String answer;
    private boolean correctAnswer;

}
