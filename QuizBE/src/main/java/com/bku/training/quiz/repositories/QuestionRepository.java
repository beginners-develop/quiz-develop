package com.bku.training.quiz.repositories;

import com.bku.training.quiz.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer>{

    @Query("SELECT q FROM Question q WHERE q.question LIKE %:question%")
    List<Question> findByQuestion(@Param("question") String question);

    @Query("SELECT q FROM Question q JOIN q.topics t WHERE t.topicName LIKE %:topicName% AND q.question LIKE %:question%")
    List<Question> findTopicAndQuestion(@Param("topicName") String topicName, @Param("question") String question);
}