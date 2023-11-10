package com.bku.training.quizfe.controller;

import com.bku.training.quizfe.entities.LoginResponse;
import com.bku.training.quizfe.entities.MemberDTO;
import com.bku.training.quizfe.entities.SubmitQuiz;
import com.bku.training.quizfe.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Nam Tran
* @project Quiz
 **/
@Controller
@RequestMapping("/member")
@SessionAttributes("globalMember")
public class MemberController extends BaseController{

    @Autowired
    private QuizService quizService;

    /**
     * display do Quiz page
     */
    @GetMapping("/doQuiz")
    public ModelAndView showDoQuizPage(@ModelAttribute("globalMember") LoginResponse loginResponse
            , @RequestParam("quizId") Integer quizId) {
        ModelAndView modelAndView = new ModelAndView("member/do-quiz");
        if (loginResponse == null) {
            modelAndView.addObject("memberObject", new MemberDTO());
        } else {
            modelAndView.addObject("memberObject", loginResponse.getMemberDTO());
            modelAndView.addObject("quiz"
                    , quizService.getQuizById("quiz/findQuiz", quizId, loginResponse));
        }
        return modelAndView;
    }

    /**
     * save all answer of user when user submit the Quiz
     * Then calculate the score
     */
    @PostMapping("/submitQuiz")
    @ResponseBody
    public ResponseEntity<?> submitQuiz(@RequestBody SubmitQuiz submitQuiz
            , @SessionAttribute("globalMember") LoginResponse loginResponse) {
        return ResponseEntity.ok(quizService.calculatePointOfQuiz("quiz/calculatePoint", submitQuiz, loginResponse));
    }

    /**
     * display view Quiz page (Quiz is completed)
     */
    @GetMapping("/viewQuiz")
    public ModelAndView showViewQuizPage(@ModelAttribute("globalMember") LoginResponse loginResponse) {
        return new ModelAndView("member/view-quiz");
    }
}
