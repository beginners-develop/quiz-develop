package com.bku.training.quiz.config.security;

import com.bku.training.quiz.entities.Member;
import com.bku.training.quiz.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * @author Nam Tran
 * @project Quiz
 **/
@Component
public class CustomAuthSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private MemberService memberService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Member member = memberService.getMemberByUsername(userDetails.getUsername());
        if (member.getFailedLogin() > 0) {
            memberService.resetFailedLogin(member);
        }
        if (roles.contains("ROLE_MEMBER")) {
            response.sendRedirect(request.getContextPath() + "/index");
        }
    }
}
