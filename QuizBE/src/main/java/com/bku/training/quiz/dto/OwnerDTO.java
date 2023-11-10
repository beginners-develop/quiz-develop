package com.bku.training.quiz.dto;

import lombok.Data;

/**
 * @author Nam Tran
 * @project QuizBE
 **/
@Data
public class OwnerDTO {

    private Integer id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;

}
