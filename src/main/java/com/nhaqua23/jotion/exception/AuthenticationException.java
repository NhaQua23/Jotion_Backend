package com.nhaqua23.jotion.exception;

/**
 * Exception thrown when authentication fails or is required
 */
public class AuthenticationException extends AppException {

	private static final long serialVersionUID = 6L;

	public AuthenticationException(String message) {
		super(message);
	}

    public AuthenticationException(String message, Throwable cause) {
		super(message, cause);
	}

	public AuthenticationException(String message, ErrorCode errorCode) {
		super(message, errorCode);
	}

	public AuthenticationException(String message, Throwable cause, ErrorCode errorCode) {
		super(message, cause, errorCode);
	}
}
