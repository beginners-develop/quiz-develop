package com.bku.training.quiz.services.impl;

import java.util.*;

import javax.transaction.Transactional;

import com.bku.training.quiz.dto.SearchDTO;
import com.bku.training.quiz.entities.Answer;
import com.bku.training.quiz.entities.Question;
import com.bku.training.quiz.entities.Topic;
import com.bku.training.quiz.exception.NotFoundException;
import com.bku.training.quiz.mapper.AnswerMapper;
import com.bku.training.quiz.mapper.QuestionMapper;
import com.bku.training.quiz.repositories.AnswerRepository;
import com.bku.training.quiz.repositories.QuestionRepository;
import com.bku.training.quiz.repositories.TopicRepository;
import com.bku.training.quiz.services.QuestionService;
import com.bku.training.quiz.dto.QuestionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bku.training.quiz.dto.QuestionDTO;

@Transactional
@Service
public class QuestionServiceImpl implements QuestionService {
	/**
	 * autowired a variable questionDao
	 */
	@Autowired
	private QuestionRepository questionDao;

	@Autowired
	private QuestionMapper questionMapper;

	@Autowired
	private TopicRepository topicRepository;

	@Autowired
	private AnswerRepository answerRepository;

	@Autowired
	private AnswerMapper answerMapper;

	/**
	 * insert
	 */
	@Override
	public QuestionDTO insert(QuestionDTO questionDTO) {
		// TODO Auto-generated method stub
		Question question = questionMapper.DTOToEntities(questionDTO);
		questionDao.save(question);
		return questionDTO;
	}

	/**
	 * get all
	 */
	@Override
	public List<QuestionDTO> getAll() {
		// TODO Auto-generated method stub
		List<Question> listQuestion = questionDao.findAll();
		List<QuestionDTO> questionDTOs = new ArrayList<QuestionDTO>();
		for (Question e : listQuestion) {
			questionDTOs.add(questionMapper.entitiesToDTO(e));
		}
		return questionDTOs;
	}

	/**
	 * find by id
	 */
	@Override
	public QuestionDTO findById(Integer id) {
		Question question = questionDao.findById(id)
				.orElseThrow(() -> new NotFoundException("Question is not found"));
		QuestionDTO questionDTO = questionMapper.entitiesToDTO(question);
		questionDTO.setAnswerDTOS(answerMapper.entitiesToDtoes(question.getAnswers()));
		return questionDTO;
	}

	/**
	 * delete
	 */
	@Override
	public void deleteQuestion(List<Integer> ids) {
		ids.forEach(e -> questionDao.deleteById(e));

	}

	/**
	 * Add new question with answer and topic
	 */
	@Override
	public Question addNewQuestion(QuestionRequest questionRequest) {
		// Add new question into DB
		Set<Question> questions = new HashSet<>();
		Question question = new Question();
		question.setQuestion(questionRequest.getQuestion());
		questionDao.save(question);
		questions.add(question);

		// Add answers of question into DB
		List<Answer> answers = new ArrayList<>();
		for (Map.Entry<String, String> map : questionRequest.getListAnswer().entrySet()) {
			Answer answer = new Answer();
			answer.setAnswer(map.getKey());
			answer.setCorrectAnswer(Boolean.parseBoolean(map.getValue()));
			answer.setQuestion(question);
			answerRepository.save(answer);
			answers.add(answer);
		}

		// Add question in topics
		Set<Topic> topics = new HashSet<>();
		for (String e: questionRequest.getListTopic()) {
			Topic topic = topicRepository.findByTopicName(e)
					.orElseThrow(() -> new NotFoundException("Topic is not found"));
			topic.setQuestions(questions);
			topicRepository.save(topic);
			topics.add(topic);
		}
		// set list answer and list topic for question
		question.setAnswers(answers);
		question.setTopics(topics);
		return questionDao.save(question);
	}

	/**
	 * update Question
	 */
	@Override
	public Question updateQuestion(QuestionRequest questionRequest) {
		Question question = questionDao.findById(questionRequest.getQuestionId())
				.orElseThrow(() -> new NotFoundException("Question is not found"));
		// Set question
		question.setQuestion(questionRequest.getQuestion());

		// Set topic for question
		Set<Topic> topics = new HashSet<>();
		questionRequest.getListTopic().forEach(e -> {
			Topic topic = topicRepository.findByTopicName(e)
					.orElseThrow(() -> new NotFoundException("Topic is not found"));
			topics.add(topic);
		});
		question.setTopics(topics);

		// Set answer
		List<Answer> answers = new ArrayList<>();
		for (Map.Entry<String, String> map : questionRequest.getListAnswer().entrySet()) {
			Answer answer = answerRepository.findByAnswerAndQuestion(map.getKey(), question)
					.orElseThrow(() -> new NotFoundException("Answer is not exist"));
			answer.setAnswer(map.getKey());
			answer.setCorrectAnswer(Boolean.parseBoolean(map.getValue()));
			answer.setQuestion(question);
			answerRepository.save(answer);
			answers.add(answer);
		}
		question.setAnswers(answers);

		return questionDao.save(question);
	}

	/**
	 * search question by value and topic
	 */
	@Override
	public List<QuestionDTO> searchQuestion(SearchDTO searchDTO) {
		List<Question> questions;
		// search question with all topic
		if (searchDTO.getTopicName().isEmpty()) {
			questions = questionDao.findByQuestion(searchDTO.getSearch());
		} else {
			questions = questionDao.findTopicAndQuestion(searchDTO.getTopicName(), searchDTO.getSearch());
		}
		List<QuestionDTO> listQuestionDto = new ArrayList<>();
		for (Question question : questions) {
			listQuestionDto.add(questionMapper.entitiesToDTO(question));
		}
		return listQuestionDto;
	}

	/**
	 * Get question by list Ids from question Bank
	 */
	@Override
	public List<QuestionDTO> getQuestionByIds(List<Integer> ids) {
		List<QuestionDTO> questionDTOS = new ArrayList<>();
		for (Integer id : ids) {
			questionDTOS.add(questionMapper.entitiesToDTO(questionDao.findById(id)
					.orElseThrow(() -> new NotFoundException("Id " + id + " is not exists"))));
		}
		return questionDTOS;
	}

}
