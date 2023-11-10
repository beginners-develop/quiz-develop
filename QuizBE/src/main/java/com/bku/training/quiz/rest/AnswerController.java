package com.bku.training.quiz.rest;

import com.bku.training.quiz.entities.Answer;
import com.bku.training.quiz.mapper.AnswerMapper;
import com.bku.training.quiz.services.AnswerService;
import com.bku.training.quiz.dto.AnswerDTO;
import com.bku.training.quiz.dto.AnswerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Nam Tran
 * @project Quiz
 **/
@RestController
@RequestMapping("/api/answer")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @Autowired
    private AnswerMapper answerMapper;


    /**
     * Add new Answer
     */
    @PostMapping("/add")
    public ResponseEntity<Answer> saveAnswer(@RequestBody AnswerDTO answerDTO) {
        return new ResponseEntity<>(answerService.addNewAnswer(
                answerMapper.dtoToEntity(answerDTO)), HttpStatus.CREATED);
    }

    /**
     * get All answer
     */
    @GetMapping("/")
    public ResponseEntity<List<AnswerDTO>> getAllAnswer() {
        return new ResponseEntity<>(answerMapper.entitiesToDtoes(
                answerService.getAll()), HttpStatus.OK);
    }

    /**
     * get Answer by Id
     */
    @GetMapping("/{id}")
    public ResponseEntity<AnswerDTO> getAnswerById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(answerMapper.entityToDto(
                answerService.getAnswerById(id)), HttpStatus.OK);
    }

    /**
     * update Answer
     */
    @PutMapping("/update")
    public ResponseEntity<Answer> updateAnswer(@RequestBody AnswerDTO answerDTO) {
        return new ResponseEntity<>(answerService.updateAnswer(
                answerMapper.dtoToEntity(answerDTO)), HttpStatus.OK);
    }

    /**
     * delete Answer by Id
     */
    @DeleteMapping("/delete")
    public void deleteAnswer(@RequestBody AnswerRequest answerRequest) {
        answerService.deleteAnswerByQuestionId(answerRequest);
    }

}
