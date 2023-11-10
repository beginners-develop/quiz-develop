package com.bku.training.quiz.repositories;

import com.bku.training.quiz.entities.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Nam Tran
 * @project QuizBE
 **/
@Repository
public interface ScoreRepository extends JpaRepository<Score, Integer> {
}
