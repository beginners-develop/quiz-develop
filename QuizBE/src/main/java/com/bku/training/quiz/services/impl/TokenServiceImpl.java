package com.bku.training.quiz.services.impl;

import com.bku.training.quiz.entities.Member;
import com.bku.training.quiz.entities.Token;
import com.bku.training.quiz.exception.NotFoundException;
import com.bku.training.quiz.repositories.TokenRepository;
import com.bku.training.quiz.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    /**
     * Save token into DB
     */
    @Override
    public void addToken(Token token) {
        tokenRepository.save(token);
    }

    @Override
    public Token getByToken(String token) {
        return tokenRepository.findByToken(token)
                .orElseThrow(() -> new NotFoundException("Token is not found"));
    }

    @Override
    public Token getTokenByMember(Member member) {
        return tokenRepository.findByMember(member);
    }

    @Override
    public void deleteToken(Token token) {
        tokenRepository.delete(token);
    }
}
