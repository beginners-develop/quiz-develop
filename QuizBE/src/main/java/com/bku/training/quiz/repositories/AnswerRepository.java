package com.bku.training.quiz.repositories;

import com.bku.training.quiz.entities.Answer;
import com.bku.training.quiz.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Nam Tran
 * @project Quiz
 **/
@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {

    Optional<Answer> findByAnswerAndQuestion(String answer, Question question);

    void deleteByIdAndQuestion(Integer id, Question question);

    Answer findByIdAndQuestion(Integer id, Question question);

}
