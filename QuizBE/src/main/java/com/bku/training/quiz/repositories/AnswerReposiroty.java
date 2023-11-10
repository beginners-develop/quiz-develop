package com.bku.training.quiz.repositories;

import com.bku.training.quiz.entities.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Nam Tran
 * @project Quiz
 **/
@Repository
public interface AnswerReposiroty extends JpaRepository<Answer, Integer> {
}
