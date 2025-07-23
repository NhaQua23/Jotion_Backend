package com.nhaqua23.jotion.controller;

import com.nhaqua23.jotion.controller.api.PageContentAPI;
import com.nhaqua23.jotion.dto.response.PageContentResponse;
import com.nhaqua23.jotion.service.PageContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PageContentController implements PageContentAPI {

	@Autowired
	private PageContentService blockNoteService;

	@Override
	public ResponseEntity<PageContentResponse> createNote(PageContentResponse dto) {
		return new ResponseEntity<>(blockNoteService.save(dto), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<PageContentResponse> updateNote(Integer id, PageContentResponse dto) {
		return new ResponseEntity<>(blockNoteService.update(id, dto), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<PageContentResponse>> getAllNotes() {
		return new ResponseEntity<>(blockNoteService.getAll(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<PageContentResponse> getNoteById(Integer id) {
		return new ResponseEntity<>(blockNoteService.getById(id), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<PageContentResponse> getNoteByPageId(Integer id) {
		return new ResponseEntity<>(blockNoteService.getByPageId(id), HttpStatus.OK);
	}

	@Override
	public ResponseEntity deleteNoteById(Integer id) {
		blockNoteService.delete(id);
		return new ResponseEntity(HttpStatus.OK);
	}
}
