package com.nhaqua23.jotion.controller;

import com.nhaqua23.jotion.controller.api.StaticNoteAPI;
import com.nhaqua23.jotion.dto.StaticNoteDTO;
import com.nhaqua23.jotion.service.StaticNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StaticNoteController implements StaticNoteAPI {

	@Autowired
	private StaticNoteService service;

	@Override
	public ResponseEntity<StaticNoteDTO> createStatic(StaticNoteDTO dto) {
		return new ResponseEntity<>(service.save(dto), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<List<StaticNoteDTO>> getAllStatics() {
		return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<StaticNoteDTO> getStaticNoteById(Integer id) {
		return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<StaticNoteDTO> getStaticNoteByPageId(Integer id) {
		return new ResponseEntity<>(service.getByPageId(id), HttpStatus.OK);
	}

	@Override
	public ResponseEntity deleteStaticNoteById(Integer id) {
		service.delete(id);
		return new ResponseEntity(HttpStatus.OK);
	}
}
