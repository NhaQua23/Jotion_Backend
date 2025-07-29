package com.nhaqua23.jotion.handler;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.nhaqua23.jotion.dto.error.ErrorResponse;
import com.nhaqua23.jotion.exception.AppException;
import com.nhaqua23.jotion.exception.AuthenticationException;
import com.nhaqua23.jotion.exception.AuthorizationException;
import com.nhaqua23.jotion.exception.DatabaseException;
import com.nhaqua23.jotion.exception.EntityAlreadyExistsException;
import com.nhaqua23.jotion.exception.EntityNotFoundException;
import com.nhaqua23.jotion.exception.ErrorCode;
import com.nhaqua23.jotion.exception.InvalidEntityException;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleException(
			EntityNotFoundException e,
			HttpServletRequest request
	) {
		log.warn("Entity not found: {}", e.getMessage());

		final ErrorResponse errorResponse = buildErrorResponse(
			e.getErrorCode(), e.getMessage(), request, e.getErrorCode().getHttpStatus()
		);
		
		return new ResponseEntity<>(errorResponse, e.getErrorCode().getHttpStatus());
	}

	@ExceptionHandler(InvalidEntityException.class)
	public ResponseEntity<ErrorResponse> handleException(
			InvalidEntityException e,
			HttpServletRequest request
	) {
		log.warn("Invalid entity: {}", e.getMessage());

		final ErrorResponse errorResponse = buildErrorResponse(
			e.getErrorCode(), e.getMessage(), request, e.getErrorCode().getHttpStatus()
		);
		errorResponse.setErrors(e.getErrorMessages());

		return new ResponseEntity<>(errorResponse, e.getErrorCode().getHttpStatus());
	}

	@ExceptionHandler(EntityAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handleException(
			EntityAlreadyExistsException e,
			HttpServletRequest request
	) {
		log.warn("Entity already exists: {}", e.getMessage());

		final ErrorResponse errorResponse = buildErrorResponse(
			e.getErrorCode(), e.getMessage(), request, e.getErrorCode().getHttpStatus()
		);
		
		return new ResponseEntity<>(errorResponse, e.getErrorCode().getHttpStatus());
	}

	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<ErrorResponse> handleException(
			AuthorizationException e,
			HttpServletRequest request
	) {
		log.warn("Authorization exception: {}", e.getMessage());

		final ErrorResponse errorResponse = buildErrorResponse(
			e.getErrorCode(), e.getMessage(), request, e.getErrorCode().getHttpStatus()
		);
		
		return new ResponseEntity<>(errorResponse, e.getErrorCode().getHttpStatus());
	}

	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<ErrorResponse> handleException(
			AuthenticationException e,
			HttpServletRequest request
	) {
		log.warn("Authentication exception: {}", e.getMessage());

		final ErrorResponse errorResponse = buildErrorResponse(
			e.getErrorCode(), e.getMessage(), request, e.getErrorCode().getHttpStatus()
		);
		
		return new ResponseEntity<>(errorResponse, e.getErrorCode().getHttpStatus());
	}

	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<ErrorResponse> handleException(
			DatabaseException e,
			HttpServletRequest request
	) {
		log.warn("Database exception: {}", e.getMessage());

		final ErrorResponse errorResponse = buildErrorResponse(
			e.getErrorCode(), e.getMessage(), request, e.getErrorCode().getHttpStatus()
		);
		
		return new ResponseEntity<>(errorResponse, e.getErrorCode().getHttpStatus());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleException(
			MethodArgumentNotValidException e,
			HttpServletRequest request
	) {
		log.warn("Validation failded: {}", e.getMessage());
		
		Map<String, String> fieldErrors = new HashMap<>();
		e.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			fieldErrors.put(fieldName, errorMessage);
		});

		final ErrorResponse errorResponse = buildErrorResponse(
			ErrorCode.INVALID_REQUEST_BODY, "Validation failed", request, HttpStatus.BAD_REQUEST
		);
		errorResponse.setFieldErrors(fieldErrors);

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ErrorResponse> handleException(
			AccessDeniedException e,
			HttpServletRequest request
	) {
		log.warn("Access denied: {}", e.getMessage());

		final ErrorResponse errorResponse = buildErrorResponse(
			ErrorCode.UNAUTHORIZED_ACCESS, "Access denied", request, HttpStatus.FORBIDDEN
		);

		return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ErrorResponse> handleException(
			DataIntegrityViolationException e,
			HttpServletRequest request
	) {
		log.warn("Data integrity violation: {}", e.getMessage());

		final ErrorResponse errorResponse = buildErrorResponse(
			ErrorCode.DATABASE_ERROR, "Data integrity violation", request, HttpStatus.INTERNAL_SERVER_ERROR
		);

		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(AppException.class)
	public ResponseEntity<ErrorResponse> handleException(
			AppException e,
			HttpServletRequest request
	) {
		log.warn("Application exception: {}", e.getMessage());

		ErrorCode errorCode =  e.getErrorCode() != null ? e.getErrorCode() : ErrorCode.INTERNAL_SERVER_ERROR;
		final ErrorResponse errorResponse = buildErrorResponse(
			errorCode, e.getMessage(), request, errorCode.getHttpStatus()
		);

		return new ResponseEntity<>(errorResponse, errorCode.getHttpStatus());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(
			Exception e,
			HttpServletRequest request
	) {
		log.warn("Exception: {}", e.getMessage());

		final ErrorResponse errorResponse = buildErrorResponse(
			ErrorCode.INTERNAL_SERVER_ERROR, "An unexpected error occurred", request, HttpStatus.INTERNAL_SERVER_ERROR
		);

		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private ErrorResponse buildErrorResponse(
		ErrorCode errorCode, String message, 
		HttpServletRequest request, HttpStatus httpStatus
	) {
		return ErrorResponse.builder()
			.httpStatusCode(httpStatus.value())
			.errorCode(errorCode)
			.message(message != null ? message : errorCode.getDefaultMessage())
			.timestamp(LocalDateTime.now())
			.requestId(UUID.randomUUID().toString())
			.path(request.getRequestURI())
			.method(request.getMethod())
			.build();
	}
}
