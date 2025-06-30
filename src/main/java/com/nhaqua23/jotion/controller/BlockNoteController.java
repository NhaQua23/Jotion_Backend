package com.nhaqua23.jotion.controller;

import com.nhaqua23.jotion.controller.api.BlockNoteAPI;
import com.nhaqua23.jotion.dto.BlockNoteDTO;
import com.nhaqua23.jotion.service.BlockNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BlockNoteController implements BlockNoteAPI {

	@Autowired
	private BlockNoteService blockNoteService;

	@Override
	public ResponseEntity<BlockNoteDTO> createNote(BlockNoteDTO dto) {
		return new ResponseEntity<>(blockNoteService.save(dto), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<BlockNoteDTO> updateNote(Integer id, BlockNoteDTO dto) {
		return new ResponseEntity<>(blockNoteService.update(id, dto), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<BlockNoteDTO>> getAllNotes() {
		return new ResponseEntity<>(blockNoteService.getAll(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<BlockNoteDTO> getNoteById(Integer id) {
		return new ResponseEntity<>(blockNoteService.getById(id), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<BlockNoteDTO> getNoteByPageId(Integer id) {
		return new ResponseEntity<>(blockNoteService.getByPageId(id), HttpStatus.OK);
	}

	@Override
	public ResponseEntity deleteNoteById(Integer id) {
		blockNoteService.delete(id);
		return new ResponseEntity(HttpStatus.OK);
	}
}
