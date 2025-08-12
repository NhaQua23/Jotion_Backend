package com.nhaqua23.jotion.dto.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IntrospectDTO {

	private String token;
	private boolean valid;
}
