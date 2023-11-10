package com.bku.training.quiz.services;

import com.bku.training.quiz.entities.Member;
import com.bku.training.quiz.entities.Token;

/**
 * @author Nam Tran
 * @project Quiz
 **/
public interface TokenService {

    void addToken(Token token);

    Token getByToken(String token);

    Token getTokenByMember(Member member);

    void deleteToken(Token token);
}
