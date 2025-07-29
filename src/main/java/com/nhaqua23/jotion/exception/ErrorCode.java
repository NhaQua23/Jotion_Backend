package com.nhaqua23.jotion.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

	// 1000-1999: NOT FOUND ERRORS
	USER_NOT_FOUND(1000, "User not found", HttpStatus.NOT_FOUND),
	WORKSPACE_NOT_FOUND(1001, "Workspace not found", HttpStatus.NOT_FOUND),
	PAGE_NOT_FOUND(1002, "Page not found", HttpStatus.NOT_FOUND),
	TAG_NOT_FOUND(1003, "Tag not found", HttpStatus.NOT_FOUND),
	TEXT_NOT_FOUND(1004, "Text not found", HttpStatus.NOT_FOUND),
	IMAGE_NOT_FOUND(1005, "Image not found", HttpStatus.NOT_FOUND),
	TODO_NOT_FOUND(1006, "Todo not found", HttpStatus.NOT_FOUND),
	TABLE_NOT_FOUND(1007, "Table not found", HttpStatus.NOT_FOUND),
	JOURNAL_NOT_FOUND(1008, "Journal not found", HttpStatus.NOT_FOUND),
	FLASHCARD_NOT_FOUND(1009, "Flashcard not found", HttpStatus.NOT_FOUND),
	SHARED_PAGE_NOT_FOUND(1010, "Shared page not found", HttpStatus.NOT_FOUND),
	NOTE_TEMPLATE_NOT_FOUND(1011, "Note template not found", HttpStatus.NOT_FOUND),

	// 2000-2999: VALIDATION ERRORS
	USER_NOT_VALID(2000, "User data is invalid", HttpStatus.BAD_REQUEST),
	WORKSPACE_NOT_VALID(2001, "Workspace data is invalid", HttpStatus.BAD_REQUEST),
	PAGE_NOT_VALID(2002, "Page data is invalid", HttpStatus.BAD_REQUEST),
	TAG_NOT_VALID(2003, "Tag data is invalid", HttpStatus.BAD_REQUEST),
	TEXT_NOT_VALID(2004, "Text data is invalid", HttpStatus.BAD_REQUEST),
	IMAGE_NOT_VALID(2005, "Image data is invalid", HttpStatus.BAD_REQUEST),
	TODO_NOT_VALID(2006, "Todo data is invalid", HttpStatus.BAD_REQUEST),
	TABLE_NOT_VALID(2007, "Table data is invalid", HttpStatus.BAD_REQUEST),
	JOURNAL_NOT_VALID(2008, "Journal data is invalid", HttpStatus.BAD_REQUEST),
	FLASHCARD_NOT_VALID(2009, "Flashcard data is invalid", HttpStatus.BAD_REQUEST),
	INVALID_REQUEST_BODY(2010, "Request body is invalid", HttpStatus.BAD_REQUEST),
	INVALID_REQUEST_PARAMETER(2011, "Request parameter is invalid", HttpStatus.BAD_REQUEST),
	MISSING_REQUIRED_FIELD(2012, "Required field is missing", HttpStatus.BAD_REQUEST),

	// 3000-3999: CONFLICT ERRORS
	USER_ALREADY_EXISTS(3000, "User already exists", HttpStatus.CONFLICT),
	WORKSPACE_ALREADY_EXISTS(3001, "Workspace already exists", HttpStatus.CONFLICT),
	PAGE_ALREADY_EXISTS(3002, "Page already exists", HttpStatus.CONFLICT),
	TAG_ALREADY_EXISTS(3003, "Tag already exists", HttpStatus.CONFLICT),
	TEXT_ALREADY_EXISTS(3004, "Text already exists", HttpStatus.CONFLICT),
	IMAGE_ALREADY_EXISTS(3005, "Image already exists", HttpStatus.CONFLICT),
	TODO_ALREADY_EXISTS(3006, "Todo already exists", HttpStatus.CONFLICT),
	TABLE_ALREADY_EXISTS(3007, "Table already exists", HttpStatus.CONFLICT),
	JOURNAL_ALREADY_EXISTS(3008, "Journal already exists", HttpStatus.CONFLICT),
	FLASHCARD_ALREADY_EXISTS(3009, "Flashcard already exists", HttpStatus.CONFLICT),
	PAGE_ALREADY_SHARED(3010, "Page is already shared with this user", HttpStatus.CONFLICT),
	DUPLICATE_EMAIL(3011, "Email already exists", HttpStatus.CONFLICT),

	// 4000-4999: AUTHENTICATION & AUTHORIZATION ERRORS
	UNAUTHENTICATED(4000, "Authentication required", HttpStatus.UNAUTHORIZED),
	INVALID_CREDENTIALS(4001, "Invalid username or password", HttpStatus.UNAUTHORIZED),
	TOKEN_EXPIRED(4002, "Authentication token has expired", HttpStatus.UNAUTHORIZED),
	TOKEN_INVALID(4003, "Authentication token is invalid", HttpStatus.UNAUTHORIZED),
	UNAUTHORIZED_ACCESS(4100, "Access denied", HttpStatus.FORBIDDEN),
	INSUFFICIENT_PERMISSIONS(4101, "Insufficient permissions to perform this action", HttpStatus.FORBIDDEN),
	PAGE_ACCESS_DENIED(4102, "You don't have permission to access this page", HttpStatus.FORBIDDEN),
	WORKSPACE_ACCESS_DENIED(4103, "You don't have permission to access this workspace", HttpStatus.FORBIDDEN),
	SHARE_PERMISSION_DENIED(4104, "You don't have permission to share this page", HttpStatus.FORBIDDEN),
	UNSHARE_PERMISSION_DENIED(4105, "You don't have permission to unshare this page", HttpStatus.FORBIDDEN),
	OWNER_CANNOT_BE_REMOVED(4106, "Cannot remove owner access", HttpStatus.FORBIDDEN),
	ROLE_UPDATE_PERMISSION_DENIED(4107, "You don't have permission to update roles for this page", HttpStatus.FORBIDDEN),

	// 5000-5999: SERVER ERRORS
	INTERNAL_SERVER_ERROR(5000, "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR),
	DATABASE_ERROR(5001, "Database operation failed", HttpStatus.INTERNAL_SERVER_ERROR),
	EXTERNAL_SERVICE_ERROR(5002, "External service unavailable", HttpStatus.SERVICE_UNAVAILABLE),
	FILE_UPLOAD_ERROR(5003, "File upload failed", HttpStatus.INTERNAL_SERVER_ERROR),
	EMAIL_SEND_ERROR(5004, "Failed to send email", HttpStatus.INTERNAL_SERVER_ERROR),

	// 6000-6999: BUSINESS LOGIC ERRORS
	INVALID_OPERATION(6000, "Invalid operation", HttpStatus.BAD_REQUEST),
	OPERATION_NOT_ALLOWED(6001, "Operation not allowed in current state", HttpStatus.BAD_REQUEST),
	RESOURCE_LIMIT_EXCEEDED(6002, "Resource limit exceeded", HttpStatus.TOO_MANY_REQUESTS),
	INVALID_STATE_TRANSITION(6003, "Invalid state transition", HttpStatus.BAD_REQUEST);

	private final int code;
	private final String defaultMessage;
	private final HttpStatus httpStatus;

	ErrorCode(int code, String defaultMessage, HttpStatus httpStatus) {
		this.code = code;
		this.defaultMessage = defaultMessage;
		this.httpStatus = httpStatus;
	}

	public int getCode() {
		return code;
	}

	public String getDefaultMessage() {
		return defaultMessage;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}
