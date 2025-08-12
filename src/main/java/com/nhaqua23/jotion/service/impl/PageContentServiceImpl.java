package com.nhaqua23.jotion.service.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhaqua23.jotion.dto.content.PageContentRequest;
import com.nhaqua23.jotion.dto.content.PageContentResponse;
import com.nhaqua23.jotion.exception.AuthorizationException;
import com.nhaqua23.jotion.exception.EntityAlreadyExistsException;
import com.nhaqua23.jotion.exception.EntityNotFoundException;
import com.nhaqua23.jotion.exception.ErrorCode;
import com.nhaqua23.jotion.exception.InvalidEntityException;
import com.nhaqua23.jotion.mapper.PageContentMapper;
import com.nhaqua23.jotion.model.Page;
import com.nhaqua23.jotion.model.PageContent;
import com.nhaqua23.jotion.model.User;
import com.nhaqua23.jotion.model.UserRole;
import com.nhaqua23.jotion.repository.PageContentRepository;
import com.nhaqua23.jotion.repository.SharedPageRepository;
import com.nhaqua23.jotion.service.PageContentService;
import com.nhaqua23.jotion.support.fetcher.EntityFetcher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class PageContentServiceImpl implements PageContentService {

	private final PageContentRepository pageContentRepository;
	private final SharedPageRepository sharedPageRepository;
	private final PageContentMapper pageContentMapper;
	private final EntityFetcher entityFetcher;

	@Override
	public PageContentResponse createPageContent(PageContentRequest request) {
		log.debug("Creating page content for page {} by user {}", request.getPageId(), request.getUserId());

		// Validate input
		validateRequest(request);

		// Check if content already exists for this page
		if (pageContentRepository.existsByPageIdAndIsActiveTrue(request.getPageId())) {
			throw new EntityAlreadyExistsException(
					"Page content already exists for page: " + request.getPageId(),
					ErrorCode.CONTENT_ALREADY_EXISTS);
		}

		// Check permissions
		if (!hasPermissionToEdit(request.getPageId(), request.getUserId())) {
			throw new AuthorizationException(
					"User does not have permission to create content for this page",
					ErrorCode.INSUFFICIENT_PERMISSIONS);
		}
		
		// Get entities
		User user = entityFetcher.getUserById(request.getUserId());
		Page page = entityFetcher.getPageById(request.getPageId());

		// Create page content
		PageContent pageContent = pageContentMapper.toPageContent(request);
		pageContent.setPage(page);
		pageContent.setCreatedBy(user);
		pageContent.setUpdatedBy(user);

		PageContent savedContent = pageContentRepository.save(pageContent);
		log.info("Created page content with ID {} for page {}", savedContent.getId(), request.getPageId());

		return pageContentMapper.toPageContentResponse(savedContent);
	}

	@Override
	@Transactional(readOnly = true)
	public PageContentResponse getById(Integer id) {
		log.info("Getting page content by ID: {}", id);
		PageContent pageContent = entityFetcher.getPageContentById(id);

		return pageContentMapper.toPageContentResponse(pageContent);
	}

	@Override
	@Transactional(readOnly = true)
	public PageContentResponse getByPageId(Integer pageId) {
		log.info("Getting page content by page ID: {}", pageId);
		PageContent pageContent = pageContentRepository.findActiveContentByPageId(pageId)
				.orElseThrow(() -> new EntityNotFoundException(
						"Page content not found for page: " + pageId,
						ErrorCode.CONTENT_NOT_FOUND));

		return pageContentMapper.toPageContentResponse(pageContent);
	}

	@Override
	@Transactional(readOnly = true)
	public List<PageContentResponse> getAll() {
		log.info("Getting all page contents");

		return pageContentRepository.findAll().stream()
				.map(pageContentMapper::toPageContentResponse)
				.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public List<PageContentResponse> getAllByPageId(Integer pageId) {
		log.info("Getting all page contents by page ID: {}", pageId);

		return pageContentRepository.findByPageId(pageId).stream()
				.map(pageContentMapper::toPageContentResponse)
				.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public List<PageContentResponse> getAllByUserId(Integer userId) {
		log.info("Getting all page contents by user ID: {}", userId);

		return pageContentRepository.findByCreatedById(userId).stream()
				.map(pageContentMapper::toPageContentResponse)
				.collect(Collectors.toList());
	}

	@Override
	public PageContentResponse updatePageContent(Integer id, PageContentRequest request) {
		log.info("Updating page content {} by user {}", id, request.getUserId());

		// Validate input
		validateRequest(request);

		// Get existing content
		PageContent pageContent = entityFetcher.getPageContentById(id);

		// Check if content is active
		if (!pageContent.getIsActive()) {
			throw new EntityNotFoundException(
					"Page content not found for page: " + pageContent.getPage().getId(),
					ErrorCode.CONTENT_NOT_FOUND);
		}

		// Check permissions
		if (!hasPermissionToEdit(pageContent.getPage().getId(), request.getUserId())) {
			throw new AuthorizationException(
					"User does not have permission to update this content",
					ErrorCode.INSUFFICIENT_PERMISSIONS);
		}

		// Check version for opimistic locking
		if (request.getVersion() != null && !request.getVersion().equals(pageContent.getVersion())) {
			throw new OptimisticLockingFailureException(
					"Content has been modified by another user. Please refresh and try again.");
		}
		
		// Update content
		User user = entityFetcher.getUserById(request.getUserId());
		
		pageContent.setContent(request.getContent());
		pageContent.setContentType(request.getContentType());
		pageContent.setUpdatedBy(user);
		
		if (request.getContentVersion() != null) {
			pageContent.setContentVersion(request.getContentVersion());
		}

		PageContent newPageContent = pageContentRepository.save(pageContent);

		log.info("Updated page content with ID {}", newPageContent.getId());
		return pageContentMapper.toPageContentResponse(newPageContent);
	}

	@Override
	public PageContentResponse updateContentByPageId(PageContentRequest request) {
		log.info("Updating page content by page ID: {}", request.getPageId());

		// Validate input
		validateRequest(request);

		// Check permissions
		if (!hasPermissionToEdit(request.getPageId(), request.getUserId())) {
			throw new AuthorizationException(
					"User does not have permission to update this content",
					ErrorCode.INSUFFICIENT_PERMISSIONS);
		}
		
		// Get existing content or create new one
		PageContent pageContent = pageContentRepository.findActiveContentByPageId(request.getPageId())
				.orElse(null);

		if (pageContent != null) {
			// Update existing content
			return updatePageContent(pageContent.getId(), request);
		} else {
			// Create new content
			return createPageContent(request);
		}
	}

	@Override
	public void deleteById(Integer id) {
		log.debug("Deleting page content by ID: {}", id);

		PageContent pageContent = entityFetcher.getPageContentById(id);

		// Check if content is already deleted
		if (!pageContent.getIsActive()) {
			log.warn("Page content with ID {} is already deleted", id);
			return;
		}

		pageContent.setIsActive(false);
		pageContentRepository.save(pageContent);

		log.info("Deleted page content with ID {}", id);
	}

	@Override
	public void deleteByPageId(Integer pageId) {
		log.debug("Deleting page content by page ID: {}", pageId);

		List<PageContent> contents = pageContentRepository.findByPageId(pageId);

		if (contents.isEmpty()) {
			log.warn("No page content found for page ID {}", pageId);
			return;
		}

		contents.forEach(content -> {
			if (content.getIsActive()) {
				content.setIsActive(false);
			}
		});

		pageContentRepository.saveAll(contents);
		long deletedCount = contents.stream().mapToLong(content -> content.getIsActive() ? 0 : 1).sum();
		log.info("Deleted {} page contents for page {}", deletedCount, pageId);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean hasPermissionToEdit(Integer pageId, Integer userId) {
		Page page = entityFetcher.getPageById(pageId);
		if (page.getAuthor().getId().equals(userId)) {
			return true;
		}

		User user = entityFetcher.getUserById(userId);

		return sharedPageRepository.findByUserAndPage(user, page)
				.map(sharedPage -> sharedPage.getRole() == UserRole.OWNER
						|| sharedPage.getRole() == UserRole.COLLABORATOR)
				.orElse(false);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean hasPermissionToView(Integer pageId, Integer userId) {
		Page page = entityFetcher.getPageById(pageId);

		// Page owner has view permission
		if (page.getAuthor().getId().equals(userId)) {
			return true;
		}

		User user = entityFetcher.getUserById(userId);

		// Check shared page permissions (all roles can view)
		return sharedPageRepository.findByUserAndPage(user, page)
				.map(sharedPage -> sharedPage.getRole() == UserRole.OWNER
						|| sharedPage.getRole() == UserRole.COLLABORATOR
						|| sharedPage.getRole() == UserRole.VIEWER)
				.orElse(false);
	}

	@Override
	@Transactional(readOnly = true)
	public List<PageContentResponse> searchContentInWorkspace(Integer workspaceId, String query, Integer userId) {
		log.info("Searching content in workspace {} with query: {}", workspaceId, query);

		// Check permissions
		if (!hasPermissionToView(workspaceId, userId)) {
			throw new AuthorizationException(
					"User does not have permission to search content in this workspace",
					ErrorCode.INSUFFICIENT_PERMISSIONS);
		}

		List<PageContent> contents = pageContentRepository.searchContentInWorkspace(workspaceId, query);
		return contents.stream()
				.map(pageContentMapper::toPageContentResponse)
				.collect(Collectors.toList());
	}
	
	@Override
	@Transactional(readOnly = true)
	public Map<String, Object> getUserContentStats(Integer userId) {
		log.info("Getting user content statistics for user {}", userId);

		// Check permissions
		if (!hasPermissionToView(userId, userId)) {
			throw new AuthorizationException(
					"User does not have permission to view their own content stats",
					ErrorCode.INSUFFICIENT_PERMISSIONS);
		}

		LocalDateTime thirtyDaysAgo = LocalDateTime.now().minusDays(30);
		Long recentActivity = pageContentRepository.countByUserSinceDate(userId, thirtyDaysAgo);

		Map<String, Object> stats = new HashMap<>();
		stats.put("totalContentCreated", pageContentRepository.countContentByUser(userId));
		stats.put("totalContentUpdated", pageContentRepository.countUpdatedContentByUser(userId));
		stats.put("recentActivity30Days", recentActivity);

		return stats;
	}

	@Override
	@Transactional(readOnly = true)
	public List<PageContentResponse> getUserRecentActivity(Integer userId, Integer limit) {
		log.info("Getting recent activity for user {} with limit {}", userId, limit);

		// Check permissions
		if (!hasPermissionToView(userId, userId)) {
			throw new AuthorizationException(
					"User does not have permission to view their own recent activity",
					ErrorCode.INSUFFICIENT_PERMISSIONS);
		}

		Pageable pageable = PageRequest.of(0, limit != null ? limit : 10);
		List<PageContent> contents = pageContentRepository.findRecentActivityByUser(userId, pageable);

		return contents.stream()
				.map(pageContentMapper::toPageContentResponse)
				.collect(Collectors.toList());
	}

	@Override
	public List<PageContentResponse> getContentHistory(Integer pageId, Integer userId) {
		log.info("Getting content history for page {} with user {}", pageId, userId);

		// Check permissions
		if (!hasPermissionToView(pageId, userId)) {
			throw new AuthorizationException(
					"User does not have permission to view content history for this page",
					ErrorCode.INSUFFICIENT_PERMISSIONS);
		}

		List<PageContent> history = pageContentRepository.findContentHistoryByPageId(pageId);
		return history.stream()
				.map(pageContentMapper::toPageContentResponse)
				.collect(Collectors.toList());
	}

	@Override
	public PageContentResponse getContentByVersion(Integer pageId, Integer version, Integer userId) {
		log.info("Getting content by version {} for page {} with user {}", version, pageId, userId);

		// Check permissions
		if (!hasPermissionToView(pageId, userId)) {
			throw new AuthorizationException(
					"User does not have permission to view content history for this page",
					ErrorCode.INSUFFICIENT_PERMISSIONS);
		}

		Optional<PageContent> content = pageContentRepository.findByPageIdAndVersion(pageId, version);
		if (content.isEmpty()) {
			log.warn("Content version {} not found for page {}", version, pageId);
			throw new EntityNotFoundException(
					"Content version " + version + " not found for page " + pageId,
					ErrorCode.CONTENT_NOT_FOUND);
		}

		return pageContentMapper.toPageContentResponse(content.get());
	}

	private void validateRequest(PageContentRequest request) {
		if (request == null) {
			throw new InvalidEntityException("Page content request cannot be null",
					ErrorCode.INVALID_REQUEST_PARAMETER);
		}
		if (request.getPageId() == null) {
			throw new InvalidEntityException("Page ID is required",
					ErrorCode.MISSING_REQUIRED_FIELD);
		}
		if (request.getUserId() == null) {
			throw new InvalidEntityException("User ID is required",
					ErrorCode.MISSING_REQUIRED_FIELD);
		}
		if (request.getContent() == null || request.getContent().trim().isEmpty()) {
			throw new InvalidEntityException("Content is required",
					ErrorCode.MISSING_REQUIRED_FIELD);
		}
	}
}
