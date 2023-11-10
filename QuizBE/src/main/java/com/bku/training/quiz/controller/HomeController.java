package com.bku.training.quiz.controller;

import com.bku.training.quiz.entities.Member;
import com.bku.training.quiz.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

/**
 * @author Nam Tran
 * @project Quiz
 **/
@Controller
public class HomeController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/index")
    public ModelAndView indexPage(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("index");
        if (principal == null) {
            modelAndView.addObject("memberObject", new Member());
        } else {
            UserDetails userDetails = (UserDetails) ((Authentication) principal).getPrincipal();
            Member member = memberService.getMemberByUsername(userDetails.getUsername());
            modelAndView.addObject("memberObject", member);
        }
        return modelAndView;
    }

}
