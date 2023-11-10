package com.bku.training.quiz.rest;

import java.util.List;

import com.bku.training.quiz.dto.SearchDTO;
import com.bku.training.quiz.entities.Question;
import com.bku.training.quiz.services.QuestionService;
import com.bku.training.quiz.dto.QuestionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bku.training.quiz.dto.QuestionDTO;

/**
 * 
 * @author TuMT
 *
 */
@RestController
@RequestMapping(value = "/api/question")
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	/**
	 * create
	 * 
	 * @param
	 * @return
	 */
	@PostMapping(value = "/add")
	public ResponseEntity<?> addQuestion(@RequestBody QuestionRequest questionRequest) {
		Question question = questionService.addNewQuestion(questionRequest);
		if (question == null) {
			return ResponseEntity.badRequest().body("FAILED TO CREATE NEW QUESTION");
		}
		return ResponseEntity.ok("CREATE");
	}

	/**
	 * find all
	 * 
	 * @return
	 */

	@GetMapping(value = "/find")
	public ResponseEntity<List<QuestionDTO>> findAllQuestion() {
		return new ResponseEntity<List<QuestionDTO>>(questionService.getAll(), HttpStatus.OK);
	}

	/**
	 * find by id
	 *
	 */
	@PostMapping(value = "/findById")
	public ResponseEntity<?> findById(@RequestBody Integer id) {
		return ResponseEntity.ok(questionService.findById(id));
	}

	/**
	 * update
	 */
	@PutMapping(value = "/update")
	public ResponseEntity<?> updateQuestion(@RequestBody QuestionRequest questionRequest) {
		Question question = questionService.updateQuestion(questionRequest);
		if (question == null) {
			return ResponseEntity.badRequest().body("FAILED TO UPDATE QUESTION");
		}
		return ResponseEntity.ok("UPDATE");
	}

	/**
	 * delete
	 *
	 */
	@DeleteMapping(value = "/delete")
	public void deleteQuestion(@RequestBody List<Integer> ids) {
		questionService.deleteQuestion(ids);
	}

	/**
	 * search question by value and topic
	 */
	@PostMapping("/search")
	public ResponseEntity<?> search(@RequestBody SearchDTO searchDTO) {
		return ResponseEntity.ok(questionService.searchQuestion(searchDTO));
	}

	/**
	 * pick question from Question Bank by user
	 */
	@PostMapping("/pick-question")
	public ResponseEntity<?> getQuestionByListId(@RequestBody List<Integer> ids) {
		return ResponseEntity.ok(questionService.getQuestionByIds(ids));
	}

}