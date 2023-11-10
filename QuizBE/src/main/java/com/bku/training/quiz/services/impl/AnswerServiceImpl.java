package com.bku.training.quiz.services.impl;

import com.bku.training.quiz.entities.Answer;
import com.bku.training.quiz.entities.Question;
import com.bku.training.quiz.exception.NotFoundException;
import com.bku.training.quiz.repositories.AnswerRepository;
import com.bku.training.quiz.repositories.QuestionRepository;
import com.bku.training.quiz.services.AnswerService;
import com.bku.training.quiz.dto.AnswerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Answer addNewAnswer(Answer answer) {
        return answerRepository.save(answer);
    }

    @Override
    public List<Answer> getAll() {
        return answerRepository.findAll();
    }

    @Override
    public Answer getAnswerById(Integer id) {
        return answerRepository.findById(id).orElseThrow(() -> new NullPointerException("Not exist"));
    }

    @Override
    public Answer updateAnswer(Answer answerRequest) {
        Answer answer = answerRepository.findById(answerRequest.getId())
                .orElseThrow(() -> new NullPointerException("Not exist"));
        answer.setAnswer(answerRequest.getAnswer());
        answer.setCorrectAnswer(answerRequest.isCorrectAnswer());
        answer.setQuestion(answerRequest.getQuestion());
        return answerRepository.save(answer);
    }

    /**
     * Delete answer by question id and answer
     */
    @Override
    public void deleteAnswerByQuestionId(AnswerRequest answerRequest) {
        Question question = questionRepository.findById(answerRequest.getQuestionId())
                .orElseThrow(() -> new NotFoundException("Question is not found"));
        answerRepository.deleteByIdAndQuestion(answerRequest.getAnswerId(), question);
    }
}
