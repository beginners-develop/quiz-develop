package com.bku.training.quiz.services;

import java.util.List;

import com.bku.training.quiz.dto.SearchDTO;
import com.bku.training.quiz.entities.Question;
import com.bku.training.quiz.dto.QuestionDTO;
import com.bku.training.quiz.dto.QuestionRequest;
import com.bku.training.quiz.exception.NotFoundException;

/**
 * 
 * @author TuMT
 *
 */
public interface QuestionService {
	/**
	 * insert
	 * 
	 * @param questionDTO
	 * @return
	 */
	QuestionDTO insert(QuestionDTO questionDTO) ;

	/**
	 * delete
	 * 
	 * @param id
	 * @return
	 */
	void deleteQuestion(List<Integer> id);

	/**
	 * get all
	 * 
	 * @return
	 */
	List<QuestionDTO> getAll() throws NotFoundException;

	/**
	 * find by id
	 * 
	 * @param id
	 * @return
	 */
	QuestionDTO findById(Integer id);

	Question addNewQuestion(QuestionRequest questionRequest);
	Question updateQuestion(QuestionRequest questionRequest);

	List<QuestionDTO> searchQuestion(SearchDTO searchDTO);
	List<QuestionDTO> getQuestionByIds(List<Integer> ids);

}
