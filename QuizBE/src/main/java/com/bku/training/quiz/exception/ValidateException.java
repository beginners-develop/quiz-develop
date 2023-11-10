package com.bku.training.quiz.exception;

/**
 * @author Nam Tran
 * @project QuizBE
 **/
public class ValidateException extends RuntimeException{
    public ValidateException() {
    }

    public ValidateException(String message) {
        super(message);
    }
}
