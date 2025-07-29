package com.nhaqua23.jotion.exception;

public class EntityAlreadyExistsException extends AppException {

	private static final long serialVersionUID = 4L;

	public EntityAlreadyExistsException(String message) {
		super(message);
	}

	public EntityAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public EntityAlreadyExistsException(String message, ErrorCode errorCode) {
		super(message, errorCode);
	}

	public EntityAlreadyExistsException(String message, Throwable cause, ErrorCode errorCode) {
		super(message, cause, errorCode);
	}
}
