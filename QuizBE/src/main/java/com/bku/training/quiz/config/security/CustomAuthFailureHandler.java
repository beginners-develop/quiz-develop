package com.bku.training.quiz.config.security;

import com.bku.training.quiz.entities.Member;
import com.bku.training.quiz.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * @author Nam Tran
 * @project Quiz
 **/
@Component
public class CustomAuthFailureHandler implements AuthenticationFailureHandler {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MemberService memberService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            Member member = memberService.getMemberByUsername(userDetails.getUsername());
            String minutes = new SimpleDateFormat("mm").format(memberService.LOCK_TIME);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                if (member.getFailedLogin() < memberService.MAX_FAILED_LOGIN - 1) {
                    memberService.increaseFailedLogin(member);
                    int times = memberService.MAX_FAILED_LOGIN - member.getFailedLogin() - 1;
                    request.setAttribute("errorMessage", "The Username or Password is not correct! " +
                            "You have " + times + " times left to login");
                } else {
                    memberService.lockMember(member);
                    throw new LockedException("You're failed to login " + memberService.MAX_FAILED_LOGIN + " times" +
                            ". Please try again after " + minutes + " minutes");
                }
            } else if (!member.isActivated()) {
                request.setAttribute("errorMessage", "Please activate your account");
            } else if (member.isAccountNonLocked()) {
                if (memberService.unlockWhenOutOfLockTime(member)) {
                    throw new LockedException("Your account has been unlocked. Please try again");
                }
                request.setAttribute("errorMessage", "Please wait for your account is unlocked");
            }
        } catch (UsernameNotFoundException | LockedException errorMessage) {
            request.setAttribute("errorMessage", errorMessage.getMessage());
        }
        request.getRequestDispatcher("/login").forward(request, response);
    }
}
