package com.nhaqua23.jotion.service;

import com.nhaqua23.jotion.dto.user.CreateUserRequest;
import com.nhaqua23.jotion.dto.user.UserResponse;
import com.nhaqua23.jotion.dto.user.UpdateProfileRequest;

import java.util.List;

public interface UserService {

	UserResponse save(CreateUserRequest request);

	UserResponse updateProfile(Integer id, UpdateProfileRequest request);

	List<UserResponse> getAll();

	UserResponse getById(Integer id);

	UserResponse getByEmail(String email);

	void delete(Integer id);
}
