package com.nhaqua23.jotion.service;

import com.nhaqua23.jotion.dto.UserDTO;

import java.util.List;

public interface UserService {

	UserDTO save(UserDTO userDTO);

	UserDTO update(Integer id, UserDTO userDTO);

	List<UserDTO> getAll();

	UserDTO getById(Integer id);

	UserDTO getByEmail(String email);

	void delete(Integer id);
}
