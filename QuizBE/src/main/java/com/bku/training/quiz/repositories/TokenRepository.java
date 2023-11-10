package com.bku.training.quiz.repositories;

import com.bku.training.quiz.entities.Member;
import com.bku.training.quiz.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Nam Tran
 * @project Quiz
 **/
@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {

    Token findByMember(Member member);

    Optional<Token> findByToken(String token);

}
