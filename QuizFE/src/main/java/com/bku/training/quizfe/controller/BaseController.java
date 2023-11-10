package com.bku.training.quizfe.controller;

import com.bku.training.quizfe.entities.LoginResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;

/**
 * @author Nam Tran
 * @project Quiz
 **/
@Controller
public class BaseController {

    /**
     * init globalMember from session
     */
    @ModelAttribute("globalMember")
    public LoginResponse initMember(HttpSession session) {
        return (LoginResponse) session.getAttribute("globalMember");
    }
}
