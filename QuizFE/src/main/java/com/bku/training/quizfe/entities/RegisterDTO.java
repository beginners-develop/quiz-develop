package com.bku.training.quizfe.entities;

import lombok.Data;

import java.util.List;

/**
 * @author Nam Tran
* @project Quiz
 **/
@Data
public class RegisterDTO {

    private String username;
    private String password;
    private String email;
    private String confirmPassword;
    private String reCaptcha;
    private List<String> roles;

}
