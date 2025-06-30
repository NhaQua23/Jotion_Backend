package com.nhaqua23.jotion.service;

import com.nhaqua23.jotion.dto.StaticNoteDTO;

import java.util.List;

public interface StaticNoteService {

	StaticNoteDTO save(StaticNoteDTO dto);

	List<StaticNoteDTO> getAll();

	StaticNoteDTO getById(Integer id);

	StaticNoteDTO getByPageId(Integer pageId);

	void delete(Integer id);
}
