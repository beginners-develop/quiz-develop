package com.bku.training.quiz.rest;

import java.util.List;
import java.util.Optional;

import com.bku.training.quiz.dto.TopicDTO;
import com.bku.training.quiz.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author TuMT
 *
 */
@RestController
@RequestMapping(value = "/api/topic")
public class TopicController {
	@Autowired
	private TopicService topicService;

	/**
	 * create
	 * 
	 * @param
	 * @return
	 */
	@PostMapping(value = "/add")
	public ResponseEntity<String> addTopic(@RequestBody TopicDTO topicDTO) {
		topicService.insert(topicDTO);
		return new ResponseEntity<>("successful", HttpStatus.OK);
	}

	/**
	 * find all
	 * 
	 * @return
	 */
	@GetMapping(value = "/find")
	public ResponseEntity<List<TopicDTO>> findAllTopic() {
		return new ResponseEntity<List<TopicDTO>>(topicService.getAll(), HttpStatus.OK);
	}

	/**
	 * update
	 * 
	 * @param questionDTO
	 * @return
	 */
	@PutMapping(value = "/update")
	public ResponseEntity<TopicDTO> updateTopic(@RequestBody TopicDTO topicDTOParam) {
		Optional<TopicDTO> topicOptional = topicService.findById(topicDTOParam.getId());
		return topicOptional.map(topicDTO -> new ResponseEntity<>(topicService.insert(topicDTOParam), HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
	}

	/**
	 * find by id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/findbyid")
	public ResponseEntity<TopicDTO> findById(@RequestParam(name = "id") Integer id) {
		Optional<TopicDTO> topicOptional = topicService.findById(id);
		return topicOptional.map(topicDTO -> new ResponseEntity<>(topicDTO, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
	}

	/**
	 * delete
	 * 
	 * @param id
	 */
	@DeleteMapping(value = "/delete")
	public ResponseEntity<String> deleteTopic(@RequestParam(name = "id") Integer id) {
		Optional<TopicDTO> topicOptional = topicService.findById(id);
		return topicOptional.map(topicDTO -> new ResponseEntity<>(topicService.delete(id), HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));

	}

}