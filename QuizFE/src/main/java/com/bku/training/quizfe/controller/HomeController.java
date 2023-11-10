package com.bku.training.quizfe.controller;

import com.bku.training.quizfe.entities.LoginResponse;
import com.bku.training.quizfe.entities.MemberDTO;
import com.bku.training.quizfe.entities.QuizDTO;
import com.bku.training.quizfe.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nam Tran
* @project Quiz
 **/
@Controller
@SessionAttributes("globalMember")
public class HomeController extends BaseController{

    @Autowired
    private QuizService quizService;

    /**
     * Go to index page
     */
    @GetMapping("/index")
    public ModelAndView gotoIndex(@ModelAttribute("globalMember") LoginResponse loginResponse) {
        ModelAndView modelAndView = new ModelAndView("index");
        if (loginResponse == null) {
            modelAndView.addObject("memberObject", new MemberDTO());
        } else {
            // Add globalMember into session again
            modelAndView.addObject("globalMember", loginResponse);
            modelAndView.addObject("memberObject", loginResponse.getMemberDTO());
        }
        // total record of quiz published and completed
        modelAndView.addObject("totalPublished"
                , quizService.getListQuizByStatus("public/quiz/findAll?status=PUBLISHED"));
        modelAndView.addObject("totalCompleted"
                , quizService.getListQuizByStatus("public/quiz/findAll?status=COMPLETED"));
        // list quiz published and completed for paging
        modelAndView.addObject("quizzesPublished"
                , quizService.getListQuizByStatus("public/quiz/findAll?status=PUBLISHED&page=" + 0 + "&size=" + 3));
        modelAndView.addObject("quizzesCompleted"
                , quizService.getListQuizByStatus("public/quiz/findAll?status=COMPLETED&page=" + 0 + "&size=" + 3));
        return modelAndView;
    }

    /**
     * Using ajax to paging Quiz
     */
    @GetMapping("/paging")
    @ResponseBody
    public ResponseEntity<?> pagingForQuiz(@RequestParam("status") String quizStatus
            , @RequestParam("page") Integer page
            , @RequestParam("size") Integer size) {
        System.out.println("status " + quizStatus + " : " + "page " + page + " : " + "size " + size);
        return ResponseEntity.ok(quizService.getListQuizByStatus(
                "public/quiz/findAll?status=" + quizStatus + "&page=" + page + "&size=" + size));
    }

    /**
     * Search Quiz, scope global
     */
    @PostMapping("/searchQuiz")
    public ModelAndView searchQuiz(@RequestParam("search") String value
            , @ModelAttribute("globalMember") LoginResponse loginResponse) {
        ModelAndView modelAndView = new ModelAndView("index");
        if (loginResponse == null) {
            modelAndView.addObject("memberObject", new MemberDTO());
        } else {
            modelAndView.addObject("memberObject", loginResponse.getMemberDTO());
        }
        List<QuizDTO> listQuiz = quizService.searchQuiz("public/quiz/search?quizName=" + value);
        List<QuizDTO> quizzesPublished = new ArrayList<>();
        List<QuizDTO> quizzesCompleted = new ArrayList<>();
        // Add list Published or Completed from listQuiz
        listQuiz.forEach(e -> {
            if ("PUBLISHED".equals(e.getQuizStatus())) {
                quizzesPublished.add(e);
            } else if ("COMPLETED".equals(e.getQuizStatus())) {
                quizzesCompleted.add(e);
            }
        });
        // Add list to object
        modelAndView.addObject("totalPublished", quizzesPublished);
        modelAndView.addObject("quizzesPublished", quizzesPublished);
        modelAndView.addObject("totalCompleted", quizzesCompleted);
        modelAndView.addObject("quizzesCompleted", quizzesCompleted);
        // Add this object to invisible Load more button
        modelAndView.addObject("invisible", "invisible");
        return modelAndView;
    }

}
