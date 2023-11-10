package com.bku.training.quizfe.entities;

import lombok.Data;

/**
 * @author Nam Tran
* @project Quiz
 **/
@Data
public class OwnerDTO {

    private Integer id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;

}
