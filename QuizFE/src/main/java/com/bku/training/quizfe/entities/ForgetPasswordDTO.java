package com.bku.training.quizfe.entities;

import lombok.Data;

/**
 * @author Nam Tran
* @project Quiz
 **/
@Data
public class ForgetPasswordDTO {

    private String email;
    private String reCaptcha;

}
