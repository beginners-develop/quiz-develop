package com.bku.training.quiz.services;

import java.util.List;
import java.util.Optional;

import com.bku.training.quiz.dto.TopicDTO;
import com.bku.training.quiz.exception.IdNotFoundException;
import com.bku.training.quiz.exception.NotFoundException;

/**
 * 
 * @author TuMT
 *
 */
public interface TopicService {
	/**
	 * insert
	 * 
	 * @param topicDTO
	 * @return
	 */
	TopicDTO insert(TopicDTO topicDTO);

	/**
	 * delete
	 * 
	 * @param id
	 * @return
	 */
	String delete(Integer id);

	/**
	 * get all
	 * 
	 * @return
	 */
	List<TopicDTO> getAll() throws NotFoundException;

	/**
	 * find by id
	 * 
	 * @param id
	 * @return
	 */
	public Optional<TopicDTO> findById(Integer id) throws IdNotFoundException;

}
