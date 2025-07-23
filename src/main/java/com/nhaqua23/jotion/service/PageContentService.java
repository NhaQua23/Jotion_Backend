package com.nhaqua23.jotion.service;

import com.nhaqua23.jotion.dto.response.PageContentResponse;

import java.util.List;

public interface PageContentService {

	PageContentResponse save(PageContentResponse dto);

	PageContentResponse update(Integer id, PageContentResponse dto);

	List<PageContentResponse> getAll();

	PageContentResponse getById(Integer id);

	List<PageContentResponse> getAllByPageId(Integer pageId);

	PageContentResponse getByPageId(Integer pageId);

	void delete(Integer id);
}
