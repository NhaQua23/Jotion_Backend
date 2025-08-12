package com.nhaqua23.jotion.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nhaqua23.jotion.dto.user.CreateUserRequest;
import com.nhaqua23.jotion.dto.user.UpdateProfileRequest;
import com.nhaqua23.jotion.dto.user.UserResponse;
import com.nhaqua23.jotion.exception.ErrorCode;
import com.nhaqua23.jotion.exception.InvalidEntityException;
import com.nhaqua23.jotion.mapper.UserMapper;
import com.nhaqua23.jotion.model.User;
import com.nhaqua23.jotion.model.UserRole;
import com.nhaqua23.jotion.repository.UserRepository;
import com.nhaqua23.jotion.service.UserService;
import com.nhaqua23.jotion.support.fetcher.EntityFetcher;
import com.nhaqua23.jotion.support.validator.UserValidator;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final UserMapper userMapper;
	private final EntityFetcher entityFetcher;

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

		User user = entityFetcher.getUserById(id);
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
		return userMapper.toUserResponse(entityFetcher.getUserById(id));
	}

	@Override
	public UserResponse getByEmail(String email) {
		return userMapper.toUserResponse(entityFetcher.getUserByEmail(email));
	}

	@Override
	public void delete(Integer id) {
		User user = entityFetcher.getUserById(id);
		userRepository.delete(user);
	}
}
