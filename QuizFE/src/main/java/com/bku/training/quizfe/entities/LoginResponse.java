package com.bku.training.quizfe.entities;

import lombok.Data;

/**
 * @author Nam Tran
* @project Quiz
 **/
@Data
public class LoginResponse {

    private String token;

    private String type;

    private MemberDTO memberDTO;
}
