package com.nhaqua23.jotion.dto;

import com.nhaqua23.jotion.exception.ErrorCode;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ErrorDTO {

	private Integer httpStatusCode;
	private ErrorCode errorCode;
	private String message;
	private List<String> errors;
}
