package com.bku.training.quiz.services.impl;

import java.util.*;

import javax.transaction.Transactional;

import com.bku.training.quiz.dto.TopicDTO;
import com.bku.training.quiz.entities.Topic;
import com.bku.training.quiz.exception.IdNotFoundException;
import com.bku.training.quiz.mapper.TopicMapper;
import com.bku.training.quiz.repositories.TopicRepository;
import com.bku.training.quiz.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class TopicServiceImpl implements TopicService {
	/**
	 * a variable
	 */
	@Autowired
	private TopicRepository topicDao;

	@Autowired
	TopicMapper topicMapper;


	/**
	 * insert
	 */
	@Override
	public TopicDTO insert(TopicDTO topicDTO) {

		Topic topic;
		topic = topicMapper.dtoToEntities(topicDTO);
		topicDao.save(topic);
		return topicDTO;
	}

	/**
	 * delete
	 */
	@Override
	public String delete(Integer id) {

		topicDao.deleteById(id);
		return "successful";
	}

	/**
	 * get all
	 */
	@Override
	public List<TopicDTO> getAll() {
		List<Topic> topics = topicDao.findAll();
		List<TopicDTO> topicDTOs = new ArrayList<>();
		for (Topic e: topics) {
			topicDTOs.add(topicMapper.entitiesToDto(e));
		}
		return topicDTOs;
	}

	/**
	 * find by id
	 */
	@Override
	public Optional<TopicDTO> findById(Integer id) {
		Topic topic = topicDao.findById(id).orElse(null);
		Optional<TopicDTO> optionnalTopicDTO = Optional.empty();
		
		if (topic == null) {
			 throw new IdNotFoundException("Invalid id");
		} else {
			TopicDTO topicDTO = topicMapper.entitiesToDto(topic);
			return optionnalTopicDTO.ofNullable(topicDTO);
		}

	}

}
