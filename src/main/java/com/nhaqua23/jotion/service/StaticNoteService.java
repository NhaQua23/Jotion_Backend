package com.nhaqua23.jotion.service;

import com.nhaqua23.jotion.dto.response.StaticNoteResponse;

import java.util.List;

public interface StaticNoteService {

	StaticNoteResponse save(StaticNoteResponse dto);

	List<StaticNoteResponse> getAll();

	StaticNoteResponse getById(Integer id);

	StaticNoteResponse getByPageId(Integer pageId);

	void delete(Integer id);
}
