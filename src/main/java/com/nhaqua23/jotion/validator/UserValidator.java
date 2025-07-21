package com.nhaqua23.jotion.validator;

import com.nhaqua23.jotion.dto.user.CreateUserRequest;
import com.nhaqua23.jotion.dto.user.UserResponse;
import com.nhaqua23.jotion.dto.user.UpdateProfileRequest;

import java.util.ArrayList;
import java.util.List;

public class UserValidator {

	public static List<String> validateCreateUser(CreateUserRequest request) {
		List<String> errors = new ArrayList<>();

		if (request == null) {
			errors.add("Missing Username");
			errors.add("Missing Password");
			errors.add("Missing Email");
			return errors;
		}

		if (request.getUsername() == null || request.getUsername().isBlank()) {
			errors.add("Missing Username");
		}
		if (request.getPassword() == null || request.getPassword().isBlank()) {
			errors.add("Missing Password");
		}
		if (request.getEmail() == null || request.getEmail().isBlank()) {
			errors.add("Missing Email");
		}

		return errors;
	}

	public static List<String> validateUpdateUser(UpdateProfileRequest request) {
		List<String> errors = new ArrayList<>();

		if (request == null) {
			errors.add("Missing Username");
			return errors;
		}

		if (request.getUsername() == null || request.getUsername().isBlank()) {
			errors.add("Missing Username");
		}

		return errors;
	}

	public static List<String> validateUserCredentials(UserResponse dto) {
		List<String> errors = new ArrayList<>();

		if (dto.getEmail() == null || dto.getEmail().isBlank()) {
			errors.add("Missing Email");
		}
		if (dto.getPassword() == null || dto.getPassword().isBlank()) {
			errors.add("Missing Password");
		}

		return errors;
	}
}
