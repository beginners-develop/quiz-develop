package com.bku.training.quiz.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author Nam Tran
 * @project QuizBE
 **/
@Data
public class QuestionRequest {

    private Integer id;
    private Integer questionId;
    private String question;
    private Map<String, String> listAnswer;
    private List<String> listTopic;

}
