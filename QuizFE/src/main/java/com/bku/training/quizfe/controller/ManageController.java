package com.bku.training.quizfe.controller;

import com.bku.training.quizfe.entities.*;
import com.bku.training.quizfe.service.QuestionService;
import com.bku.training.quizfe.service.QuizService;
import com.bku.training.quizfe.service.TopicService;
import com.bku.training.quizfe.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/manage")
@SessionAttributes("globalMember")
public class ManageController extends BaseController{

    @Autowired
    private TopicService topicService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;

    /**
     * Show create question page
     */
    @GetMapping("/create-question")
    private ModelAndView createQuestionPage(@ModelAttribute("globalMember") LoginResponse loginResponse) {
        ModelAndView modelAndView = new ModelAndView("manage/create-question");
        if (loginResponse == null) {
            modelAndView.addObject("memberObject", new MemberDTO());
        } else {
            modelAndView.addObject("memberObject", loginResponse.getMemberDTO());
            modelAndView.addObject("listTopic", topicService.listTopic("topic/find", new TopicDTO(),loginResponse));
        }
        return modelAndView;
    }

    /**
     * Using ajax to add new question
     */
    @PostMapping("/add-question")
    @ResponseBody
    public String addNewQuestion(@RequestBody QuestionRequest questionRequest
            , @SessionAttribute("globalMember") LoginResponse loginResponse) {
        try {
            // Create new question
            if (questionRequest.getQuestionId() == null) {
                return questionService.addNewQuestion("question/add", questionRequest, loginResponse);
            // Update new question
            } else {
                return questionService.updateQuestion("question/update", questionRequest, loginResponse);
            }
        } catch (HttpStatusCodeException e) {
            return e.getResponseBodyAsString();
        }
    }

    /**
     * show create quiz page
     */
    @GetMapping("/create-quiz")
    private ModelAndView createQuizPage(@ModelAttribute("globalMember") LoginResponse loginResponse
            , @RequestParam(value = "success", required = false) String message) {
        ModelAndView modelAndView = new ModelAndView("manage/create-quiz");
        if (loginResponse == null) {
            modelAndView.addObject("memberObject", new MemberDTO());
        } else {
            modelAndView.addObject("memberObject", loginResponse.getMemberDTO());
            if ("createQuiz".equals(message)) {
                modelAndView.addObject("successMessage", "CREATE NEW QUIZ COMPLETELY");
            }
            modelAndView.addObject("listTopic", topicService.listTopic("topic/find", new TopicDTO(),loginResponse));
            modelAndView.addObject("listQuestion", questionService.getListQuestion("question/find", new QuestionDTO(), loginResponse));
        }
        return modelAndView;
    }


    /**
     * Report page for all quiz
     */
    @GetMapping("/report")
    private String reportPage() {
        return "manage/report";
    }

    /**
     * Using ajax to create new Quiz
     */
    @PostMapping("/add-quiz")
    @ResponseBody
    public ResponseEntity<?> addNewQuiz(@RequestBody QuizDTO quizDTO
            , @SessionAttribute("globalMember") LoginResponse loginResponse) {
        return ResponseEntity.ok(quizService.addNewQuiz("quiz/add", quizDTO, loginResponse));
    }

    /**
     * Change Quiz from DRAFT to PUBLISHED
     */
    @PostMapping("/update-status")
    @ResponseBody
    public ResponseEntity<?> submitQuiz(@RequestBody QuizDTO quizDTO
            , @SessionAttribute("globalMember") LoginResponse loginResponse) {
        return ResponseEntity.ok(quizService.submitQuiz("quiz/updateStatus", quizDTO.getQuizId(), loginResponse));
    }

    /**
     * using ajax to search question in modal
     */
    @GetMapping("/search")
    @ResponseBody
    public ResponseEntity<?> searchQuestion(@RequestParam("search") String search
            , @RequestParam("topic") String topic
            , @SessionAttribute("globalMember") LoginResponse loginResponse) {
        Map<String, String> mapSearch = new HashMap<>();
        mapSearch.put("search", search);
        mapSearch.put("topicName", topic);
        return ResponseEntity.ok(questionService.searchQuestionOrTopic(
                "question/search", mapSearch, loginResponse));
    }

    /**
     * Get list question when user choose from question bank
     */
    @GetMapping("/pick-question")
    @ResponseBody
    public ResponseEntity<?> getQuestion(@RequestParam("questionIds") List<Integer> ids
            , @SessionAttribute("globalMember") LoginResponse loginResponse) {
        return ResponseEntity.ok(questionService.getListQuestionByListId("question/pick-question", ids, loginResponse));
    }

    /**
     * Delete all question when user click previous step
     * (Delete Quiz in quiz_details)
     */
    @DeleteMapping("/delete-quiz")
    @ResponseBody
    public void deleteQuestionInQuizDetails(@RequestParam(value = "quizId", required = false) Integer quizId
            , @SessionAttribute("globalMember") LoginResponse loginResponse) {
        if (quizId != null) {
            quizService.deleteQuestionInQuizDetails("quiz/deleteInQuizDetails", quizId, loginResponse);
        }
    }

    /**
     * Remove question from DB
     */
    @DeleteMapping("/delete-question")
    @ResponseBody
    public void removeQuestion(@RequestParam(value = "questionIds", required = false) List<Integer> ids
            , @SessionAttribute("globalMember") LoginResponse loginResponse) {
        if (ids != null) {
            questionService.deleteQuestion("question/delete", ids, loginResponse);
        }
    }

    /**
     * Update question & answer
     */
    @GetMapping("/findQuestion")
    @ResponseBody
    public ResponseEntity<?> getQuestion(@RequestParam(value = "questionId", required = false) Integer id
            , @SessionAttribute("globalMember") LoginResponse loginResponse) {
        if (id == null) {
            return ResponseEntity.ok(new QuestionDTO());
        }
        return ResponseEntity.ok(questionService.getQuestionDTO("question/findById", id, loginResponse));
    }

    /**
     * Delete answer by question id and answer
     */
    @DeleteMapping("/delete-answer")
    @ResponseBody
    public void deleteAnswer(@RequestParam("questionId") Integer questionId
            , @RequestParam("answerId") Integer answerId
            , @SessionAttribute("globalMember") LoginResponse loginResponse) {
        questionService.deleteAnswer(
                "answer/delete", new AnswerRequest(questionId, answerId), loginResponse);
    }

}
