package com.nhaqua23.jotion.service;

import com.nhaqua23.jotion.dto.page.CreatePageRequest;
import com.nhaqua23.jotion.dto.page.PageResponse;
import com.nhaqua23.jotion.dto.page.UpdateBackgroundRequest;
import com.nhaqua23.jotion.dto.page.UpdateTitleRequest;

import java.util.List;

public interface PageService {

	PageResponse save(CreatePageRequest request);

	PageResponse update(Integer id, UpdateTitleRequest request);

	PageResponse updateBackground(Integer id, UpdateBackgroundRequest request);

	List<PageResponse> getAll();

	PageResponse getById(Integer id);

	List<PageResponse> getAllByWorkspaceId(Integer workspaceId);

	List<PageResponse> getAllByAuthorId(Integer authorId);

//	List<PageDTO> getAllByTagId(Integer tagId);

	void delete(Integer id);
}
