package com.bku.training.quiz.rest;

import com.bku.training.quiz.dto.QuizDTO;
import com.bku.training.quiz.dto.SubmitQuiz;
import com.bku.training.quiz.entities.Quiz;
import com.bku.training.quiz.services.QuizDetailsService;
import com.bku.training.quiz.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * @author Nam Tran
 * @project Quiz
 **/
@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @Autowired
    private QuizDetailsService quizDetailsService;

    /**
     * get quiz by id
     */
    @PostMapping("/findQuiz")
    public ResponseEntity<QuizDTO> getQuizById(@RequestBody Integer quizId) {
        return ResponseEntity.ok(quizService.getQuizById(quizId));
    }

    /**
     * add new quiz
     */
    @PostMapping("/add")
    public ResponseEntity<QuizDTO> saveQuiz(@RequestBody QuizDTO requestQuizDTO) {
        QuizDTO quizDTO;
        if (requestQuizDTO.getQuizId() == null) {
            quizDTO = quizService.addNewQuiz(requestQuizDTO);
        } else {
            quizDTO = quizService.updateNewQuiz(requestQuizDTO);
        }
        return ResponseEntity.ok(quizDTO);
    }

    /**
     * delete quiz by id
     */
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteQuiz(@PathVariable("id") Integer id) {
        quizService.deleteQuizById(id);
        return new ResponseEntity<>("DELETE COMPLETED", HttpStatus.OK);
    }

    /**
     * update status for quiz from DRAFT to PUBLISHED (create new Quiz completely)
     */
    @PostMapping("/updateStatus")
    public ResponseEntity<?> updateStatusQuiz(@RequestBody Integer quizId) {
        Quiz quiz = quizService.updateStatusQuiz(quizId);
        return quiz != null ? ResponseEntity.ok("OK")
                : ResponseEntity.badRequest().body("FAILED TO CREATE QUIZ");
    }

    /**
     * Delete all question in QuizDetails when use click button previous step
     */
    @DeleteMapping("/deleteInQuizDetails")
    public void deleteQuestionInQuizDetails(@RequestBody Integer quizId) {
        quizDetailsService.deleteQuizDetailsByQuizId(quizId);
    }

    /**
     * Calculate point when user submitted Quiz
     */
    @PostMapping("/calculatePoint")
    public ResponseEntity<?> calculatePointOfQuiz(@RequestBody SubmitQuiz submitQuiz) {
        return ResponseEntity.ok(quizService.calculatePointOfQuiz(submitQuiz));
    }

}
