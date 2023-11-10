package com.bku.training.quiz.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author Nam Tran
 * @project QuizBE
 **/
@Data
public class ForgetPasswordDTO {

    @NotBlank(message = "Email is required")
    @Pattern(regexp = "([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$"
            , message = "Email is not correct format")
    private String email;
    private String reCaptcha;

}
