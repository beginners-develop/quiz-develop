package com.bku.training.quizfe.entities;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Nam Tran
* @project Quiz
 **/
@Data
public class QuestionRequest {

    private Integer id;

    private Integer questionId;

    private String question;

    private Map<String, String> listAnswer = new HashMap<>();

    private List<String> listTopic;
}
