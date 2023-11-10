package com.bku.training.quiz.services;

import jakarta.mail.internet.AddressException;

/**
 * @author Nam Tran
 * @project Quiz
 **/
public interface EmailService {

    void sendEmail(String sendTo, String subject, String content) throws Exception;
}
