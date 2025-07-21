package com.nhaqua23.jotion.handler;

import com.nhaqua23.jotion.dto.response.ErrorResponse;
import com.nhaqua23.jotion.exception.AppException;
import com.nhaqua23.jotion.exception.EntityAlreadyExistsException;
import com.nhaqua23.jotion.exception.EntityNotFoundException;
import com.nhaqua23.jotion.exception.InvalidEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleException(
			EntityNotFoundException e,
			WebRequest request
	) {
		final ErrorResponse errorResponse = ErrorResponse.builder()
				.httpStatusCode(HttpStatus.NOT_FOUND.value())
				.errorCode(e.getErrorCode())
				.message(e.getMessage())
				.build();

		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InvalidEntityException.class)
	public ResponseEntity<ErrorResponse> handleException(
			InvalidEntityException e,
			WebRequest request
	) {
		final ErrorResponse errorResponse = ErrorResponse.builder()
				.httpStatusCode(HttpStatus.BAD_REQUEST.value())
				.errorCode(e.getErrorCode())
				.message(e.getMessage())
				.errors(e.getErrorMessages())
				.build();

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EntityAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handleException(
			EntityAlreadyExistsException e,
			WebRequest request
	) {
		final ErrorResponse errorResponse = ErrorResponse.builder()
				.httpStatusCode(HttpStatus.CONFLICT.value())
				.errorCode(e.getErrorCode())
				.message(e.getMessage())
				.build();

		return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(AppException.class)
	public ResponseEntity<ErrorResponse> handleException(
			AppException e,
			WebRequest request
	) {
		final ErrorResponse errorResponse = ErrorResponse.builder()
				.httpStatusCode(HttpStatus.BAD_REQUEST.value())
				.errorCode(e.getErrorCode())
				.message(e.getMessage())
				.build();

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
}
