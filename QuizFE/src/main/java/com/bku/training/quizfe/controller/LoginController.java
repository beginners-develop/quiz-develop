package com.bku.training.quizfe.controller;

import com.bku.training.quizfe.entities.ForgetPasswordDTO;
import com.bku.training.quizfe.entities.LoginResponse;
import com.bku.training.quizfe.entities.RegisterDTO;
import com.bku.training.quizfe.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

/**
 * @author Nam Tran
* @project Quiz
 **/
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * Go to login page
     */
    @GetMapping(value = {"/", "/login"})
    public ModelAndView loginPage(@RequestParam(value = "success", required = false) String message) {
        ModelAndView modelAndView = new ModelAndView("login");
        // Give successMessage for register
        if ("register".equals(message)) {
            modelAndView.addObject("successMessage", "CREATE NEW ACCOUNT SUCCESSFUL");
        // Give successMessage for forget-password successful
        } else if ("forget-password".equals(message)) {
            modelAndView.addObject("successMessage", "NEW PASSWORD SENT TO YOUR EMAIL");
        }
        return modelAndView;
    }

    /**
     * Handle login
     */
    @PostMapping("/login")
    public ModelAndView loginProcessing(@RequestParam("username") String username
            , @RequestParam("password") String password
            , HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("login");
        try {
            LoginResponse loginResponse = loginService.getLoginResponse("auth/login", username, password);
            System.out.println("Login completed!");
            session.setAttribute("globalMember", loginResponse);
            modelAndView.setViewName("redirect:/index");
        } catch (HttpStatusCodeException e) {
            modelAndView.addObject("errorMessage", e.getResponseBodyAsString());
        }
        return modelAndView;
    }

    /**
     * handle logout
     */
    @GetMapping("/logout")
    public ModelAndView handleLogout(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView("redirect:/login");
        HttpSession session = request.getSession(false);
        if (session != null) {
            LoginResponse loginResponse = (LoginResponse) session.getAttribute("globalMember");
            if (loginResponse != null) {
                // Remove session
                session.invalidate();
                redirectAttributes.addFlashAttribute("successMessage", "YOU HAVE BEEN LOGGED OUT");
            }
        }
        return modelAndView;
    }

    /**
     * register new account
     */
    @PostMapping("/register")
    @ResponseBody
    public String registerAccount(@RequestBody RegisterDTO registerDTO) {
        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
            return "Confirm password is not match";
        } else {
            registerDTO.setRoles(Arrays.asList("ROLE_MEMBER"));
            try {
                return loginService.registerAccount("auth/register", registerDTO);
            } catch (HttpStatusCodeException e) {
                return e.getResponseBodyAsString();
            }
        }
    }

    /**
     * handle forget password
     */
    @PostMapping("/forget-password")
    @ResponseBody
    public String forgetPassword(@RequestBody ForgetPasswordDTO forgetPasswordDTO) {
        try {
            return loginService.forgetPassword("auth/forget-password", forgetPasswordDTO);
        } catch (HttpStatusCodeException e) {
            return e.getResponseBodyAsString();
        }
    }

    /**
     *
     * Verify new account with token
     */
    @GetMapping("/verify-account")
    public ModelAndView verifyNewAccount(@RequestParam("token") String requestToken) {
        ModelAndView modelAndView = new ModelAndView("verify-account");
        try {
            String message = loginService.verifyAccount("auth/verify-account", requestToken);
            modelAndView.addObject("successMessage", message);
        } catch (HttpStatusCodeException e) {
            modelAndView.setViewName("error/404");
            modelAndView.addObject("errorMessage", e.getResponseBodyAsString());
        }
        return modelAndView;
    }

}
