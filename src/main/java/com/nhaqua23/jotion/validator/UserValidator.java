package com.nhaqua23.jotion.validator;

import com.nhaqua23.jotion.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

public class UserValidator {

	public static List<String> validateCreateUser(UserDTO dto) {
		List<String> errors = new ArrayList<>();

		if (dto == null) {
			errors.add("Missing Username");
			errors.add("Missing Password");
			errors.add("Missing Email");
			return errors;
		}

		if (dto.getUsername() == null || dto.getUsername().isBlank()) {
			errors.add("Missing Username");
		}
		if (dto.getPassword() == null || dto.getPassword().isBlank()) {
			errors.add("Missing Password");
		}
		if (dto.getEmail() == null || dto.getEmail().isBlank()) {
			errors.add("Missing Email");
		}

		return errors;
	}

	public static List<String> validateUpdateUser(UserDTO dto) {
		List<String> errors = new ArrayList<>();

		if (dto == null) {
			errors.add("Missing Username");
			return errors;
		}

		if (dto.getUsername() == null || dto.getUsername().isBlank()) {
			errors.add("Missing Username");
		}

		return errors;
	}

	public static List<String> validateUserCredentials(UserDTO dto) {
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
