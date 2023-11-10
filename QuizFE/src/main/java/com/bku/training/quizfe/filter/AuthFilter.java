package com.bku.training.quizfe.filter;

import com.bku.training.quizfe.entities.LoginResponse;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Nam Tran
* @project Quiz
 **/
@WebFilter(urlPatterns = {"/setting/*", "/member/*", "/manage/*"})
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        System.out.println("check auth filter");
        String uri = request.getRequestURI();
        HttpSession session = request.getSession(false);
        String url = request.getContextPath() + "/login";
        if (session == null) {
            response.sendRedirect(url);
        } else {
            LoginResponse loginResponse = (LoginResponse) session.getAttribute("globalMember");
            if (loginResponse == null && !uri.endsWith("login")) {
                response.sendRedirect(url);
            }
            chain.doFilter(req, resp);
        }
    }

}
