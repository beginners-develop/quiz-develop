package com.bku.training.quiz.entities.recaptcha;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * @author Nam Tran
 * @project Quiz
 **/

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReCaptchaResponse {

    private boolean success;

    private String hostname;

    private String challenge_ts;

    @JsonProperty("error-codes")
    private String[] errorCodes;

}
