package com.bku.training.quiz.controller;

import com.bku.training.quiz.entities.Member;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Nam Tran
 * @project Quiz
 **/
@Controller
public class LoginController {

    /**
     * Go to login Page
     * @return
     */
    @GetMapping(value = {"/", "/login"})
    public ModelAndView loginPage() {
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("userAccount", new Member());
        return modelAndView;
    }

    /**
     * Handle login by Spring security
     * @return
     */
    @PostMapping("/login")
    public String loginProcessing() {
        return "index";
    }

    /**
     * Handle logout by Spring security
     * @return
     */
    @GetMapping("/logoutSuccessful")
    public ModelAndView logoutSuccessful() {
        return new ModelAndView("login");
    }

    @GetMapping("/error/403")
    public String accessDeniedPage() {
        return "error/403";
    }

}
