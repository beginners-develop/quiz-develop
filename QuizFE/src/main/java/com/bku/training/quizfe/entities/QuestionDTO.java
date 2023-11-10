package com.bku.training.quizfe.entities;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @author Nam Tran
* @project Quiz
 **/
@Data
public class QuestionDTO {

    private Integer id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    private String question;
    private List<String> topicName;
    private List<AnswerDTO> answerDTOS;

}
