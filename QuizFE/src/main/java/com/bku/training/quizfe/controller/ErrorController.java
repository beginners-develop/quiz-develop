package com.bku.training.quizfe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {
    @GetMapping("/error/400")
    private String badRequestErrorPage() {
        return "error/400";
    }

    @GetMapping("/error/403")
    private String accessDeniedErrorPage() {
        return "error/403";
    }

    @GetMapping("/error/404")
    private String errorNotFoundPage() {
        return "error/404";
    }

    @GetMapping("/error/500")
    private String internalServerErrorPage() {
        return "error/500";
    }
}
