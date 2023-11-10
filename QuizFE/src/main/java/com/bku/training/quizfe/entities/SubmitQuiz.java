package com.bku.training.quizfe.entities;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Nam Tran
* @project Quiz
 **/
@Data
public class SubmitQuiz {

    private Integer userId;
    private Integer quizId;
    private Map<Integer, Integer> answerOfUser = new HashMap<>();

}
