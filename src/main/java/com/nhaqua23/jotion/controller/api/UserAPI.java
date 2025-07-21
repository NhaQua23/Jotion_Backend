package com.nhaqua23.jotion.controller.api;

import com.nhaqua23.jotion.dto.response.CommonResponse;
import com.nhaqua23.jotion.dto.user.UpdateProfileRequest;
import com.nhaqua23.jotion.dto.user.CreateUserRequest;
import com.nhaqua23.jotion.dto.user.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User", description = "User management APIs")
@RequestMapping("/api")
public interface UserAPI {

	@PostMapping(
			value = "/users",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Create a user",
			description = "Get a UserDTO object. The response is UserDTO object if create successfully"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "User created successfully"),
			@ApiResponse(responseCode = "2001", description = "User not valid"),
	})
	ResponseEntity<CommonResponse<UserResponse>> createUser(
			@RequestBody CreateUserRequest request
	);

	@PatchMapping(
			value = "/users/{id}",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Update a user",
			description = "Update an existing user by ID"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "User updated successfully"),
			@ApiResponse(responseCode = "1001", description = "User not found"),
			@ApiResponse(responseCode = "2001", description = "User not valid"),
	})
	ResponseEntity<CommonResponse<UserResponse>> updateProfile(
			@PathVariable("id") Integer id,
			@RequestBody UpdateProfileRequest request
	);

	@GetMapping(
			value = "/users",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Get all users",
			description = "Retrieve all users"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Users retrieved successfully"),
	})
	ResponseEntity<CommonResponse<UserResponse>> getAllUsers();

	@GetMapping(
			value = "/users/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Get the user by id",
			description = "Retrieve a specific user by id"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "User retrieved successfully"),
			@ApiResponse(responseCode = "1001", description = "User not found"),
	})
	ResponseEntity<CommonResponse<UserResponse>> getUserById(
			@PathVariable("id") Integer id
	);

	@DeleteMapping(
			value = "/users/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Delete the user",
			description = "Delete a specific user by ID"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "User deleted successfully"),
			@ApiResponse(responseCode = "1001", description = "User not found"),
	})
	ResponseEntity<CommonResponse<UserResponse>> deleteUserById(
			@PathVariable("id") Integer id
	);
}
