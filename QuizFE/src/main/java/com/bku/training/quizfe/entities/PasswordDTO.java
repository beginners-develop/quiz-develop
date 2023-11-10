package com.bku.training.quizfe.entities;

import lombok.Data;

/**
 * @author Nam Tran
* @project Quiz
 **/
@Data
public class PasswordDTO {
    private Integer id;
    private String currentPassword;
    private String newPassword;
    private String confirmPassword;

    public PasswordDTO(Integer id, String currentPassword, String newPassword, String confirmPassword) {
        this.id = id;
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
    }
}
