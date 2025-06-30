package com.nhaqua23.jotion.exception;

public class AppException extends RuntimeException {

	private static final long serialVersionUID = 4L;

	private ErrorCode errorCode;

	public AppException(String message) {
		super(message);
	}

	public AppException(String message, Throwable cause) {
		super(message, cause);
	}

	public AppException(String message, ErrorCode errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public AppException(String message, Throwable cause, ErrorCode errorCode) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}
}
