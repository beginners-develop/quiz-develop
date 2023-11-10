package com.bku.training.quiz.repositories;

import com.bku.training.quiz.entities.Quiz;
import com.bku.training.quiz.enumeric.QuizStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Nam Tran
 * @project Quiz
 **/
@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {

    List<Quiz> findAllByQuizStatus(QuizStatus quizStatus);

    List<Quiz> findAllByQuizStatus(QuizStatus quizStatus, Pageable pageable);

    List<Quiz> findAllByQuizNameContaining(String quizName);

    List<Quiz> findAllByQuizNameContaining(String quizName, Pageable pageable);

}
