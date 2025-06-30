package com.nhaqua23.jotion.service;

import com.nhaqua23.jotion.dto.PageDTO;

import java.util.List;

public interface PageService {

	PageDTO save(PageDTO pageDTO);

	PageDTO update(Integer id, PageDTO pageDTO);

	PageDTO updateBackground(Integer id, PageDTO dto);

	List<PageDTO> getAll();

	PageDTO getById(Integer id);

	List<PageDTO> getAllByWorkspaceId(Integer workspaceId);

	List<PageDTO> getAllByAuthorId(Integer authorId);

//	List<PageDTO> getAllByTagId(Integer tagId);

	void delete(Integer id);
}
