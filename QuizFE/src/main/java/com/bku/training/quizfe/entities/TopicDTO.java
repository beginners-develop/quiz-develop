package com.bku.training.quizfe.entities;

import lombok.Data;

import java.util.Set;

/**
 * @author Nam Tran
* @project Quiz
 **/
@Data
public class TopicDTO {

    private Integer id;
    private String topicName;
    private Set<QuestionDTO> questions;

}
