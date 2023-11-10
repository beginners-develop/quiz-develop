package com.bku.training.quiz.dto;

import lombok.Data;

/**
 * @author Nam Tran
 * @project QuizBE
 **/
@Data
public class PasswordDTO {

    private Integer id;

    private String currentPassword;

    private String newPassword;

    private String confirmPassword;

}
