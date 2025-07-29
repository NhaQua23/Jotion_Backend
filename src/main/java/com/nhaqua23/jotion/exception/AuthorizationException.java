package com.nhaqua23.jotion.exception;

/**
 * Exception thrown when a user doesn't have sufficient permissions to perform an action
 */
public class AuthorizationException extends AppException {

    private static final long serialVersionUID = 5L;

    public AuthorizationException(String message) {
        super(message);
    }

    public AuthorizationException(String message, Throwable cause) {
        super(message, cause);
    }

    public AuthorizationException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public AuthorizationException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause, errorCode);
    }
}
