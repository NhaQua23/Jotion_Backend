package com.nhaqua23.jotion.exception;

import java.util.List;

public class InvalidEntityException extends AppException {

	private static final long serialVersionUID = 3L;
	private List<String> errorMessages;

	public InvalidEntityException(String message) {
		super(message);
	}

	public InvalidEntityException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidEntityException(String message, ErrorCode errorCode) {
		super(message, errorCode);
	}

	public InvalidEntityException(String message, Throwable cause, ErrorCode errorCode) {
		super(message, cause, errorCode);
	}

	public InvalidEntityException(String message, ErrorCode errorCode, List<String> errorMessages) {
		super(message, errorCode);
		this.errorMessages = errorMessages;
	}

	public InvalidEntityException(String message, Throwable cause, ErrorCode errorCode, List<String> errorMessages) {
		super(message, cause, errorCode);
		this.errorMessages = errorMessages;
	}

	public List<String> getErrorMessages() {
		return errorMessages;
	}
}
