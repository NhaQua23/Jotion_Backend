package com.nhaqua23.jotion.service;

import com.nhaqua23.jotion.dto.BlockNoteDTO;

import java.util.List;

public interface BlockNoteService {

	BlockNoteDTO save(BlockNoteDTO dto);

	BlockNoteDTO update(Integer id, BlockNoteDTO dto);

	List<BlockNoteDTO> getAll();

	BlockNoteDTO getById(Integer id);

	List<BlockNoteDTO> getAllByPageId(Integer pageId);

	BlockNoteDTO getByPageId(Integer pageId);

	void delete(Integer id);
}
