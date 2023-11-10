package com.bku.training.quiz.repositories;

import com.bku.training.quiz.entities.Quiz;
import com.bku.training.quiz.entities.QuizDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Nam Tran
 * @project QuizBE
 **/
@Repository
public interface QuizDetailsRepository extends JpaRepository<QuizDetails, Integer> {

    void deleteByQuiz(Quiz quiz);

}
