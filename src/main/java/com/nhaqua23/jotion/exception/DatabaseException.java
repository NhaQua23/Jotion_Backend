package com.nhaqua23.jotion.exception;

/**
 * Exception thrown when database operations fail
 */
public class DatabaseException extends AppException {

	private static final long serialVersionUID = 7L;

	public DatabaseException(String message) {
		super(message);
	}

    public DatabaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public DatabaseException(String message, ErrorCode errorCode) {
		super(message, errorCode);
	}

	public DatabaseException(String message, Throwable cause, ErrorCode errorCode) {
		super(message, cause, errorCode);
	}
}
