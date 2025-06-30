package com.nhaqua23.jotion.service.impl;

import com.nhaqua23.jotion.dto.UserDTO;
import com.nhaqua23.jotion.exception.EntityNotFoundException;
import com.nhaqua23.jotion.exception.ErrorCode;
import com.nhaqua23.jotion.exception.InvalidEntityException;
import com.nhaqua23.jotion.model.User;
import com.nhaqua23.jotion.model.UserRole;
import com.nhaqua23.jotion.repository.UserRepository;
import com.nhaqua23.jotion.service.UserService;
import com.nhaqua23.jotion.validator.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl  implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDTO save(UserDTO dto) {
		List<String> errors = UserValidator.validateCreateUser(dto);
		if (!errors.isEmpty()) {
			log.error(errors.toString());
			throw new InvalidEntityException(
					"User is not valid",
					ErrorCode.USER_NOT_VALID,
					errors
			);
		}

		User user = UserDTO.toUser(dto);

		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		user.setRole(UserRole.USER);

		return UserDTO.toUserDTO(userRepository.save(user));
	}

	@Override
	public UserDTO update(Integer id, UserDTO dto) {
		List<String> errors = UserValidator.validateUpdateUser(dto);
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

		user.setUsername(dto.getUsername());

		return UserDTO.toUserDTO(userRepository.save(user));
	}

	@Override
	public List<UserDTO> getAll() {
		return userRepository.findAll().stream()
				.map(UserDTO::toUserDTO).collect(Collectors.toList());
	}

	@Override
	public UserDTO getById(Integer id) {
		return userRepository.findById(id)
				.map(UserDTO::toUserDTO)
				.orElseThrow(() -> new EntityNotFoundException(
						"User not found with ID = " + id,
						ErrorCode.USER_NOT_FOUND
				));
	}

	@Override
	public UserDTO getByEmail(String email) {
		return userRepository.findByEmail(email)
				.map(UserDTO::toUserDTO)
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
