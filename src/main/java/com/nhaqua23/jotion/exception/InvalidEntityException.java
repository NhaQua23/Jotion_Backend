package com.nhaqua23.jotion.exception;

import java.util.List;

public class InvalidEntityException extends RuntimeException {

	private static final long serialVersionUID = 2L;
	private ErrorCode errorCode;
	private List<String> errorMessages;

	public InvalidEntityException(String message) {
		super(message);
	}

	public InvalidEntityException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidEntityException(String message, ErrorCode errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public InvalidEntityException(String message, Throwable cause, ErrorCode errorCode) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	public InvalidEntityException(String message, ErrorCode errorCode, List<String> errorMessages) {
		super(message);
		this.errorCode = errorCode;
		this.errorMessages = errorMessages;
	}

	public InvalidEntityException(String message, Throwable cause, ErrorCode errorCode, List<String> errorMessages) {
		super(message, cause);
		this.errorCode = errorCode;
		this.errorMessages = errorMessages;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public List<String> getErrorMessages() {
		return errorMessages;
	}
}
