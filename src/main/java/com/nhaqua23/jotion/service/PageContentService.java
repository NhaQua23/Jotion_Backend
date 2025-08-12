package com.nhaqua23.jotion.service;

import java.util.List;
import java.util.Map;

import com.nhaqua23.jotion.dto.content.PageContentRequest;
import com.nhaqua23.jotion.dto.content.PageContentResponse;

public interface PageContentService {

	PageContentResponse createPageContent(PageContentRequest request);
	
	PageContentResponse getById(Integer id);
	
	PageContentResponse getByPageId(Integer pageId);
	
	List<PageContentResponse> getAll();
	
	List<PageContentResponse> getAllByPageId(Integer pageId);

	List<PageContentResponse> getAllByUserId(Integer userId);
		
	PageContentResponse updatePageContent(Integer id, PageContentRequest request);

	PageContentResponse updateContentByPageId(PageContentRequest request);

	void deleteById(Integer id);

	void deleteByPageId(Integer pageId);

	boolean hasPermissionToEdit(Integer pageId, Integer userId);

	boolean hasPermissionToView(Integer pageId, Integer userId);

	List<PageContentResponse> searchContentInWorkspace(Integer workspaceId, String query, Integer userId);

	Map<String, Object> getUserContentStats(Integer userId);

	List<PageContentResponse> getUserRecentActivity(Integer userId, Integer limit);
	
	List<PageContentResponse> getContentHistory(Integer pageId, Integer userId);
	
	PageContentResponse getContentByVersion(Integer pageId, Integer version, Integer userId);
}
