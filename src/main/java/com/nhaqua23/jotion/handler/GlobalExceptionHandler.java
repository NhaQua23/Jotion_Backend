package com.nhaqua23.jotion.handler;

import com.nhaqua23.jotion.dto.ErrorDTO;
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
	public ResponseEntity<ErrorDTO> handleException(
			EntityNotFoundException e,
			WebRequest request
	) {
		final ErrorDTO errorDTO = ErrorDTO.builder()
				.httpStatusCode(HttpStatus.NOT_FOUND.value())
				.errorCode(e.getErrorCode())
				.message(e.getMessage())
				.build();

		return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InvalidEntityException.class)
	public ResponseEntity<ErrorDTO> handleException(
			InvalidEntityException e,
			WebRequest request
	) {
		final ErrorDTO errorDTO = ErrorDTO.builder()
				.httpStatusCode(HttpStatus.BAD_REQUEST.value())
				.errorCode(e.getErrorCode())
				.message(e.getMessage())
				.errors(e.getErrorMessages())
				.build();

		return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EntityAlreadyExistsException.class)
	public ResponseEntity<ErrorDTO> handleException(
			EntityAlreadyExistsException e,
			WebRequest request
	) {
		final ErrorDTO errorDTO = ErrorDTO.builder()
				.httpStatusCode(HttpStatus.CONFLICT.value())
				.errorCode(e.getErrorCode())
				.message(e.getMessage())
				.build();

		return new ResponseEntity<>(errorDTO, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(AppException.class)
	public ResponseEntity<ErrorDTO> handleException(
			AppException e,
			WebRequest request
	) {
		final ErrorDTO errorDTO = ErrorDTO.builder()
				.httpStatusCode(HttpStatus.BAD_REQUEST.value())
				.errorCode(e.getErrorCode())
				.message(e.getMessage())
				.build();

		return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
	}
}
