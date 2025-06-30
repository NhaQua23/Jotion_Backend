package com.nhaqua23.jotion.exception;

public class EntityAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 3L;
	private ErrorCode errorCode;

	public EntityAlreadyExistsException(String message) {
		super(message);
	}

	public EntityAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public EntityAlreadyExistsException(String message, ErrorCode errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public EntityAlreadyExistsException(String message, Throwable cause, ErrorCode errorCode) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}
}
