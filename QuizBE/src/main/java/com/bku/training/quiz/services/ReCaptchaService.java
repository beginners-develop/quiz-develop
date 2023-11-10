package com.bku.training.quiz.services;

/**
 * @author Nam Tran
 * @project Quiz
 **/
public interface ReCaptchaService {

    boolean validateReCaptcha(String gRecaptchaResponse);

}
