package com.nhaqua23.jotion.dto.error;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.nhaqua23.jotion.exception.ErrorCode;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

	Integer httpStatusCode;
	ErrorCode errorCode;
	String message;
	List<String> errors;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Ho_Chi_Minh")
	LocalDateTime timestamp;

	String requestId;
	String path;
	String method;

	// For validation errors - field-specific errors
	Map<String, String> fieldErrors;

	// For debugging (only in development)
	String stackTrace;

	// Additional context information
	Map<String, Object> context;
}
