package com.nhaqua23.jotion.service;

import com.nhaqua23.jotion.dto.response.NoteTemplateResponse;

import java.util.List;

public interface NoteTemplateService {

	NoteTemplateResponse save(NoteTemplateResponse dto);

	List<NoteTemplateResponse> getAll();

	NoteTemplateResponse getById(Integer id);

	NoteTemplateResponse getByPageId(Integer pageId);

	void delete(Integer id);
}
