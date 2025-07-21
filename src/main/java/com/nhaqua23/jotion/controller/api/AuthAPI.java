package com.nhaqua23.jotion.controller.api;

import com.nimbusds.jose.JOSEException;
import com.nhaqua23.jotion.dto.AuthDTO;
import com.nhaqua23.jotion.dto.IntrospectDTO;
import com.nhaqua23.jotion.dto.user.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;

@Tag(name = "Auth", description = "Auth management APIs")
@RequestMapping("/api")
public interface AuthAPI {

	@PostMapping(
			value = "/login",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Log in",
			description = "Get a UserDTO object. The response is token if log in successfully"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "User logged in successfully"),
			@ApiResponse(responseCode = "2001", description = "User not exist"),
	})
	ResponseEntity<AuthDTO> login(
			@RequestBody UserResponse dto
	);

	@PostMapping(
			value = "/signup",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Sign up",
			description = "Get a UserDTO object. The response is token if log in successfully"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "User logged in successfully"),
			@ApiResponse(responseCode = "2001", description = "User not exist"),
	})
	ResponseEntity<UserResponse> signup(
			@RequestBody UserResponse dto
	);

	@PostMapping(
			value = "/introspect",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "",
			description = "Get a IntrospectDTO object. The response is boolean if token has value"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "User logged in successfully"),
			@ApiResponse(responseCode = "2001", description = "User not exist"),
	})
	ResponseEntity<IntrospectDTO> introspect(
			@RequestBody IntrospectDTO dto
	) throws ParseException, JOSEException;
}
