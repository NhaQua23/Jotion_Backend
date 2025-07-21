package com.nhaqua23.jotion.service;

import com.nhaqua23.jotion.dto.response.BlockNoteResponse;

import java.util.List;

public interface BlockNoteService {

	BlockNoteResponse save(BlockNoteResponse dto);

	BlockNoteResponse update(Integer id, BlockNoteResponse dto);

	List<BlockNoteResponse> getAll();

	BlockNoteResponse getById(Integer id);

	List<BlockNoteResponse> getAllByPageId(Integer pageId);

	BlockNoteResponse getByPageId(Integer pageId);

	void delete(Integer id);
}
