package com.nhaqua23.jotion.exception;

public class EntityNotFoundException extends AppException {

	private static final long serialVersionUID = 2L;

	public EntityNotFoundException(String message) {
		super(message);
	}

	public EntityNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public EntityNotFoundException(String message, ErrorCode errorCode) {
		super(message, errorCode);
	}

	public EntityNotFoundException(String message, Throwable cause, ErrorCode errorCode) {
		super(message, cause, errorCode);
	}
}
