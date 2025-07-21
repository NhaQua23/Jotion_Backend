package com.nhaqua23.jotion.service.impl;

import com.nhaqua23.jotion.dto.user.CreateUserRequest;
import com.nhaqua23.jotion.dto.user.UserResponse;
import com.nhaqua23.jotion.dto.user.UpdateProfileRequest;
import com.nhaqua23.jotion.exception.EntityNotFoundException;
import com.nhaqua23.jotion.exception.ErrorCode;
import com.nhaqua23.jotion.exception.InvalidEntityException;
import com.nhaqua23.jotion.mapper.UserMapper;
import com.nhaqua23.jotion.model.User;
import com.nhaqua23.jotion.model.UserRole;
import com.nhaqua23.jotion.repository.UserRepository;
import com.nhaqua23.jotion.service.UserService;
import com.nhaqua23.jotion.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final UserMapper userMapper;

	@Override
	public UserResponse save(CreateUserRequest request) {
		List<String> errors = UserValidator.validateCreateUser(request);
		if (!errors.isEmpty()) {
			log.error(errors.toString());
			throw new InvalidEntityException(
					"User is not valid",
					ErrorCode.USER_NOT_VALID,
					errors
			);
		}

		User user = userMapper.toUser(request);
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setRole(UserRole.USER);

		return userMapper.toUserResponse(userRepository.save(user));
	}

	@Override
	public UserResponse updateProfile(Integer id, UpdateProfileRequest request) {
		List<String> errors = UserValidator.validateUpdateUser(request);
		if (!errors.isEmpty()) {
			log.error(errors.toString());
			throw new InvalidEntityException(
					"User is not valid",
					ErrorCode.USER_NOT_VALID,
					errors
			);
		}

		User user = userRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(
						"User not found with ID = " + id,
						ErrorCode.USER_NOT_FOUND
				));
		user.setUsername(request.getUsername());

		return userMapper.toUserResponse(userRepository.save(user));
	}

	@Override
	public List<UserResponse> getAll() {
		return userRepository.findAll().stream()
				.map(userMapper::toUserResponse).collect(Collectors.toList());
	}

	@Override
	public UserResponse getById(Integer id) {
		return userRepository.findById(id)
				.map(userMapper::toUserResponse)
				.orElseThrow(() -> new EntityNotFoundException(
						"User not found with ID = " + id,
						ErrorCode.USER_NOT_FOUND
				));
	}

	@Override
	public UserResponse getByEmail(String email) {
		return userRepository.findByEmail(email)
				.map(userMapper::toUserResponse)
				.orElseThrow(() -> new EntityNotFoundException(
						"User not found with email = " + email,
						ErrorCode.USER_NOT_FOUND
				));
	}

	@Override
	public void delete(Integer id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(
						"User not found with ID = " + id,
						ErrorCode.USER_NOT_FOUND
				));

		userRepository.delete(user);
	}
}
