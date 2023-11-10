package com.bku.training.quiz.dto;

import lombok.*;

/**
 * @author Nam Tran
 * @project QuizBE
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private MemberDTO memberDTO;

    public JwtResponse(String token, MemberDTO memberDTO) {
        this.token = token;
        this.memberDTO = memberDTO;
    }
}
