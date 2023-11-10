package com.bku.training.quiz.services.impl;

import com.bku.training.quiz.entities.Quiz;
import com.bku.training.quiz.exception.NotFoundException;
import com.bku.training.quiz.repositories.QuizDetailsRepository;
import com.bku.training.quiz.repositories.QuizRepository;
import com.bku.training.quiz.services.QuizDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class QuizDetailsServiceImpl implements QuizDetailsService {

    @Autowired
    private QuizDetailsRepository quizDetailsRepository;

    @Autowired
    private QuizRepository quizRepository;

    /**
     * Delete quiz in quizDetails when user click previous button
     */
    @Override
    public void deleteQuizDetailsByQuizId(Integer id) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Quiz is not found"));
        quizDetailsRepository.deleteByQuiz(quiz);
    }
}
