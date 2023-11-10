package com.bku.training.quizfe.entities;

import lombok.Data;

/**
 * @author Nam Tran
* @project Quiz
 **/
@Data
public class DeactivateDTO {
    private Integer id;
    private String reCaptcha;

    public DeactivateDTO(Integer id, String reCaptcha) {
        this.id = id;
        this.reCaptcha = reCaptcha;
    }
}
