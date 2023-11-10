package com.bku.training.quiz.repositories;

import com.bku.training.quiz.entities.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Integer>{

    Optional<Topic> findByTopicName(String topicName);
}