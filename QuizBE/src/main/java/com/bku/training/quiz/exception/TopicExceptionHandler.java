package com.bku.training.quiz.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.bku.training.quiz.apierror.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class TopicExceptionHandler extends ResponseEntityExceptionHandler{
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<Object> handleBookNotFoundException(NotFoundException ex){
		String message = ex.getMessage();
		List<String> details = new ArrayList<String>();
		details.add("Topic not found");
		ApiErrors errors = new ApiErrors(message,details, HttpStatus.BAD_REQUEST, LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}
}
