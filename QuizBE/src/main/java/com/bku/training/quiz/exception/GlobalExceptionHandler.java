package com.bku.training.quiz.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.bku.training.quiz.rest.error.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
// sử dụng ko đúng method
	/*
	 * @Override protected ResponseEntity<Object>
	 * handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException
	 * ex, HttpHeaders headers, HttpStatus status, WebRequest request) { // TODO
	 * Auto-generated method stub String message = ex.getMessage(); List<String>
	 * details = new ArrayList<>(); details.add("Request method not supported");
	 * ApiErrors errors = new ApiErrors(message,details, status,
	 * LocalDateTime.now()); return ResponseEntity.status(status).body(errors); }
	 */
//kiểu dữ liệu trả về ko phù hợp (ko phải dạng json)
	/*
	 * @Override protected ResponseEntity<Object>
	 * handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
	 * HttpHeaders headers, HttpStatus status, WebRequest request) { // TODO
	 * Auto-generated method stub String message = ex.getMessage(); List<String>
	 * details = new ArrayList<String>(); details.add("Media not supported");
	 * details.add(ex.getMessage()); ApiErrors errors = new
	 * ApiErrors(message,details, status, LocalDateTime.now()); return
	 * ResponseEntity.status(status).body(errors); }
	 */
//băt lỗi khi dev viết code sai chỗ khi khai báo getmapping là 1 pathparam còn phía dưới lại viết Pathparam(value  = "khác vs param phía trên") 
	/*
	 * @Override protected ResponseEntity<Object>
	 * handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders
	 * headers, HttpStatus status, WebRequest request) { String message =
	 * ex.getMessage(); List<String> details = new ArrayList<String>();
	 * details.add("Path variable is missing"); ApiErrors errors = new
	 * ApiErrors(message,details, status, LocalDateTime.now()); return
	 * ResponseEntity.status(status).body(errors); }
	 */
// truyền vào ko đúng tên rest ví dụ: id = 2 mà viết là ids = 2
	/*
	 * @Override protected ResponseEntity<Object>
	 * handleMissingServletRequestParameter(MissingServletRequestParameterException
	 * ex, HttpHeaders headers, HttpStatus status, WebRequest request) { String
	 * message = ex.getMessage(); List<String> details = new ArrayList<String>();
	 * details.add("Request Param is missing"); ApiErrors errors = new
	 * ApiErrors(message,details, status, LocalDateTime.now()); return
	 * ResponseEntity.status(status).body(errors); }
	 */
// khi trên url truyền về cần kiểu Integer nhưng user lại nhập kiểu string
	/*
	 * @Override protected ResponseEntity<Object>
	 * handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus
	 * status, WebRequest request) { String message = ex.getMessage(); List<String>
	 * details = new ArrayList<String>(); details.add("Mismatch of type"); ApiErrors
	 * errors = new ApiErrors(message,details, status, LocalDateTime.now()); return
	 * ResponseEntity.status(status).body(errors); }
	 */
//khi ko có đưa bất cứ thông tin nào vào body mà chỉ bấm send thoi(nhập body rỗng vào)
	/*
	 * @Override protected ResponseEntity<Object>
	 * handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders
	 * headers, HttpStatus status, WebRequest request) { String message =
	 * ex.getMessage(); List<String> details = new ArrayList<String>();
	 * details.add("Request body is not readable"); ApiErrors errors = new
	 * ApiErrors(message,details, status, LocalDateTime.now()); return
	 * ResponseEntity.status(status).body(errors); }
	 */
	
	
	// khi nhập vào value id để tìm kiếm record mà dưới db ko có id đó thì sẽ báo lỗi này
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<Object> handleIdNotFoundException(IdNotFoundException ex){
		String message = ex.getMessage();
		List<String> details = new ArrayList<String>();
		details.add("Id not available");
		ApiError errors = new ApiError(message,details, HttpStatus.NOT_FOUND, LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
	}
	// tất cả nhưng lỗi còn lại
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleOther(Exception ex){
		String message = ex.getMessage();
		List<String> details = new ArrayList<String>();
		details.add("Other exception");
		details.add(ex.getMessage());
		ApiError errors = new ApiError(message,details, HttpStatus.BAD_REQUEST, LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}

	/**
	 * Handle username not found exception
	 */
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<?> handleUsernameNotFoundException(UsernameNotFoundException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}

	/**
	 * Handle user locked exception
	 */
	@ExceptionHandler(LockedException.class)
	public ResponseEntity<?> handleLockedException(LockedException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}

	/**
	 * Handle bad credentials  exception
	 */
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<?> handleBadCredentialsException(BadCredentialsException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}

	/**
	 * Handle user disabled exception
	 */
	@ExceptionHandler(DisabledException.class)
	public ResponseEntity<?> handleDisabledException(DisabledException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}

	/**
	 * Handle already exist exception
	 */
	@ExceptionHandler(AlreadyExistException.class)
	public ResponseEntity<?> handleAlreadyExistException(AlreadyExistException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}

	/**
	 * Handle validate exception
	 */
	@ExceptionHandler(ValidateException.class)
	public ResponseEntity<?> handleValidateException(ValidateException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}

	/**
	 * Handle not found exception
	 */
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<?> handleNotFoundException(NotFoundException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}
}
