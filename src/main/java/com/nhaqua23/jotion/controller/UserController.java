package com.nhaqua23.jotion.controller;

import com.nhaqua23.jotion.controller.api.UserAPI;
import com.nhaqua23.jotion.dto.UserDTO;
import com.nhaqua23.jotion.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController implements UserAPI {

	@Autowired
	private UserService userService;

	@Override
	public ResponseEntity<UserDTO> createUser(UserDTO dto) {
		return new ResponseEntity<>(userService.save(dto), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<UserDTO> updateUser(Integer id, UserDTO dto) {
		return new ResponseEntity<>(userService.update(id, dto), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<UserDTO> getUserById(Integer id) {
		return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
	}

	@Override
	public ResponseEntity deleteUserById(Integer id) {
		userService.delete(id);
		return new ResponseEntity(HttpStatus.OK);
	}
}
