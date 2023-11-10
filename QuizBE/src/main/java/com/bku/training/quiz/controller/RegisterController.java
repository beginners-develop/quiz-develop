package com.bku.training.quiz.controller;

import com.bku.training.quiz.entities.Member;
import com.bku.training.quiz.entities.Token;
import com.bku.training.quiz.services.EmailService;
import com.bku.training.quiz.services.MemberService;
import com.bku.training.quiz.services.ReCaptchaService;
import com.bku.training.quiz.services.TokenService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * @author Nam Tran
 * @project Quiz
 **/
@Controller
public class RegisterController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private ReCaptchaService reCaptchaService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ModelAndView registerAccount(@ModelAttribute("userAccount") Member inputMember
            , @RequestParam("g-recaptcha-response") String captChaResponse) throws Exception {
        ModelAndView modelAndView = new ModelAndView("login");
        System.out.println(inputMember);
        if (!reCaptchaService.validateReCaptcha(captChaResponse)) {
            modelAndView.addObject("errorMessage", "Invalid reCaptcha");
        } else {
            Member member = memberService.addNewMember(inputMember);
            if (member == null) {
                modelAndView.addObject("errorMessage", "CREATE MEMBER IS FAILED");
            } else {
                Token token = new Token(member);
                tokenService.addToken(token);
                String content = "Demo + token = " + token.getToken() + "<br>" +
                        "<b>http://localhost:8080/quiz/verify-account?token=" + token.getToken() + "</b><br>";
                emailService.sendEmail(member.getEmail(), "CONGRATULATION: REGISTER NEW ACCOUNT SUCCESS", content);
                modelAndView.setViewName("login");
                modelAndView.addObject("successMessage", "CREATE MEMBER IS SUCCESSFUL");
            }
        }
        return modelAndView;
    }

    @GetMapping("/verify-account")
    public ModelAndView confirmRegistration(@RequestParam("token")String requestToken) {
        ModelAndView modelAndView = new ModelAndView("verify-account");
        Token token = tokenService.getByToken(requestToken);
        Member member = memberService.getMemberByEmail(token.getMember().getEmail());
        if (validateToken(token)) {
            member.setActivated(true);
            memberService.updateMember(member);
            modelAndView.addObject("successMessage", "Your account is activated successfully");
        } else {
            tokenService.deleteToken(token);
            memberService.deleteMember(member);
            modelAndView.addObject("errorMessage", "The link is out of date!");
        }
        return modelAndView;
    }

    @PostMapping("/forget-password")
    public ModelAndView doForgetPassword(@RequestParam("email") String email
            , @RequestParam("g-recaptcha-response") String reCaptcha) throws Exception {
        ModelAndView modelAndView = new ModelAndView("login");
        Member member = memberService.getMemberByEmail(email);
        Token token = tokenService.getTokenByMember(member);
        if (member != null) {
            if (token != null) {
                if (reCaptchaService.validateReCaptcha(reCaptcha)) {
                    Token newToken = new Token(member);
                    tokenService.addToken(newToken);
                    String newPassword = RandomString.make(8);
                    member.setPassword(passwordEncoder.encode(newPassword));
                    Member updatedMember = memberService.updateMember(member);
                    String content = "This is your new password <b>" + newPassword + "</b> Please change your password.";
                    emailService.sendEmail(email, "A REQUEST FORGET PASSWORD", content);
                    modelAndView.addObject("successMessage", "Please check your email");
                } else {
                    modelAndView.addObject("errorMessage", "Invalid reCaptcha");
                }
            } else {
                modelAndView.addObject("errorMessage", "Please try again");
            }
        } else {
            modelAndView.addObject("errorMessage", "The email is not correct! Please try again");
        }
        return modelAndView;
    }

    /**
     * Validate expire token
     * Make sure token is always valid
     */
    private Boolean validateToken(Token token) {
        Date now = new Date();
        if (now.before(token.getExpireDate())) {
            return true;
        }
        return false;
    }

}
