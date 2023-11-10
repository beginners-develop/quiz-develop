package com.bku.training.quiz.exception;

/**
 * @author Nam Tran
 * @project QuizBE
 **/
public class AlreadyExistException extends RuntimeException {

    public AlreadyExistException() {
    }

    public AlreadyExistException(String message) {
        super(message);
    }
}
