package com.nhaqua23.jotion.controller;

import com.nhaqua23.jotion.controller.api.UserAPI;
import com.nhaqua23.jotion.dto.response.CommonResponse;
import com.nhaqua23.jotion.dto.user.UpdateProfileRequest;
import com.nhaqua23.jotion.dto.user.CreateUserRequest;
import com.nhaqua23.jotion.dto.user.UserResponse;
import com.nhaqua23.jotion.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController implements UserAPI {

	private final UserService userService;

	@Override
	public ResponseEntity<CommonResponse<UserResponse>> createUser(CreateUserRequest request) {
		CommonResponse response = CommonResponse.builder()
				.status("success")
				.message("User created successfully")
				.data(userService.save(request))
				.build();

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<CommonResponse<UserResponse>> updateProfile(Integer id, UpdateProfileRequest request) {
		CommonResponse response = CommonResponse.builder()
				.status("success")
				.message("User updated successfully")
				.data(userService.updateProfile(id, request))
				.build();

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CommonResponse<UserResponse>> getAllUsers() {
		CommonResponse response = CommonResponse.builder()
				.status("success")
				.data(userService.getAll())
				.build();

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CommonResponse<UserResponse>> getUserById(Integer id) {
		CommonResponse response = CommonResponse.builder()
				.status("success")
				.data(userService.getById(id))
				.build();

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CommonResponse<UserResponse>> deleteUserById(Integer id) {
		userService.delete(id);
		CommonResponse response = CommonResponse.builder()
				.status("success")
				.message("User deleted successfully")
				.build();

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
