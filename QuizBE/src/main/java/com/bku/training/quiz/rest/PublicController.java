package com.bku.training.quiz.rest;

import com.bku.training.quiz.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Nam Tran
 * @project QuizBE
 **/
@RestController
@RequestMapping("/api/public")
public class PublicController {

    @Autowired
    private QuizService quizService;

    /**
     * Get all Quiz for
     */
    @GetMapping("/quiz/findAll")
    public ResponseEntity<?> findAllQuizByStatus(@RequestParam("status") String status
            , @RequestParam(value = "page", required = false) Integer page
            , @RequestParam(value = "size", required = false) Integer size) {
        return ResponseEntity.ok(quizService.getAllByStatus(status, page, size));
    }

    /**
     * search Quiz by quiz name
     */
    @GetMapping("/quiz/search")
    public ResponseEntity<?> searchQuiz(@RequestParam("quizName") String quizName) {
        System.out.println(quizName);
        return ResponseEntity.ok(quizService.getAllQuizByQuizName(quizName));
    }

}
