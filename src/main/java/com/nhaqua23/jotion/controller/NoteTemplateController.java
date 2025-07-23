package com.nhaqua23.jotion.controller;

import com.nhaqua23.jotion.controller.api.NoteTemplateAPI;
import com.nhaqua23.jotion.dto.response.NoteTemplateResponse;
import com.nhaqua23.jotion.service.NoteTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NoteTemplateController implements NoteTemplateAPI {

	@Autowired
	private NoteTemplateService service;

	@Override
	public ResponseEntity<NoteTemplateResponse> createStatic(NoteTemplateResponse dto) {
		return new ResponseEntity<>(service.save(dto), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<List<NoteTemplateResponse>> getAllStatics() {
		return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<NoteTemplateResponse> getStaticNoteById(Integer id) {
		return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<NoteTemplateResponse> getStaticNoteByPageId(Integer id) {
		return new ResponseEntity<>(service.getByPageId(id), HttpStatus.OK);
	}

	@Override
	public ResponseEntity deleteStaticNoteById(Integer id) {
		service.delete(id);
		return new ResponseEntity(HttpStatus.OK);
	}
}
