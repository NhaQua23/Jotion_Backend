package com.nhaqua23.jotion.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.nhaqua23.jotion.dto.page.CreatePageRequest;
import com.nhaqua23.jotion.dto.page.PageResponse;
import com.nhaqua23.jotion.dto.page.UpdateBackgroundRequest;
import com.nhaqua23.jotion.dto.page.UpdateTitleRequest;
import com.nhaqua23.jotion.mapper.PageMapper;
import com.nhaqua23.jotion.model.Page;
import com.nhaqua23.jotion.model.SharedPage;
import com.nhaqua23.jotion.model.User;
import com.nhaqua23.jotion.model.UserRole;
import com.nhaqua23.jotion.model.Workspace;
import com.nhaqua23.jotion.repository.PageRepository;
import com.nhaqua23.jotion.repository.SharedPageRepository;
import com.nhaqua23.jotion.service.PageService;
import com.nhaqua23.jotion.support.fetcher.EntityFetcher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PageServiceImpl implements PageService {

	private final PageRepository pageRepository;
	private final SharedPageRepository sharedPageRepository;
	private final PageMapper pageMapper;
	private final EntityFetcher entityFetcher;

	@Override
	public PageResponse save(CreatePageRequest request) {
//		List<String> errors = PageValidator.validatePage(request);
//		if (!errors.isEmpty()) {
//			log.error(errors.toString());
//			throw new InvalidEntityException(
//					"Page is not valid",
//					ErrorCode.PAGE_NOT_VALID,
//					errors
//			);
//		}

		User user = entityFetcher.getUserById(request.getAuthorId());
		Workspace workspace = entityFetcher.getWorkspaceById(request.getWorkspaceId());

		Page page = pageMapper.toPage(request);
		page.setWorkspace(workspace);
		page.setAuthor(user);
		page.setCreatedAt(LocalDate.now());
		page.setUpdatedAt(LocalDate.now());

		return pageMapper.toPageResponse(pageRepository.save(page));
	}

	@Override
	public PageResponse update(Integer id, UpdateTitleRequest request) {
//		List<String> errors = PageValidator.validatePage(request);
//		if (!errors.isEmpty()) {
//			log.error(errors.toString());
//			throw new InvalidEntityException(
//					"Page is not valid",
//					ErrorCode.PAGE_NOT_VALID,
//					errors
//			);
//		}

		Boolean isSharedPage = sharedPageRepository.existsByPageId(id);
		if (isSharedPage) {
			Page page = entityFetcher.getPageById(id);
			User user = entityFetcher.getUserById(request.getAuthorId());
			SharedPage sharedPage = entityFetcher.getSharedPageByUserAndPage(user, page);

			if (!sharedPage.getRole().equals(UserRole.OWNER) &&
					!sharedPage.getRole().equals(UserRole.COLLABORATOR)) {
				return new PageResponse();
			}
		}

		Page page = entityFetcher.getPageById(id);

		page.setTitle(request.getTitle());
		page.setUpdatedAt(LocalDate.now());

		return pageMapper.toPageResponse(pageRepository.save(page));
	}

	@Override
	public PageResponse updateBackground(Integer id, UpdateBackgroundRequest request) {
		Boolean isSharedPage = sharedPageRepository.existsByPageId(id);
		if (isSharedPage) {
			Page page = entityFetcher.getPageById(id);
			User user = entityFetcher.getUserById(request.getAuthorId());
			SharedPage sharedPage = entityFetcher.getSharedPageByUserAndPage(user, page);

			if (!sharedPage.getRole().equals(UserRole.OWNER) &&
					!sharedPage.getRole().equals(UserRole.COLLABORATOR)) {
				return new PageResponse();
			}
		}

		Page page = entityFetcher.getPageById(id);

		page.setBackground(request.getBackground());
		page.setUpdatedAt(LocalDate.now());

		return pageMapper.toPageResponse(pageRepository.save(page));
	}

	@Override
	public List<PageResponse> getAll() {
		return pageRepository.findAll().stream()
				.map(pageMapper::toPageResponse).collect(Collectors.toList());
	}

	@Override
	public PageResponse getById(Integer id) {
		return pageMapper.toPageResponse(entityFetcher.getPageById(id));
	}

	@Override
	public List<PageResponse> getAllByWorkspaceId(Integer workspaceId) {
		return pageRepository.findPageByWorkspaceId(workspaceId).stream()
				.map(pageMapper::toPageResponse).collect(Collectors.toList());
	}

	@Override
	public List<PageResponse> getAllByAuthorId(Integer authorId) {
		return pageRepository.findPageByAuthorId(authorId).stream()
				.map(pageMapper::toPageResponse).collect(Collectors.toList());
	}

//	@Override
//	public List<PageDTO> getAllByTagId(Integer tagId) {
//		return pageRepository.findPagesByTagsId(tagId).stream()
//				.map(PageDTO::toPageDTO).collect(Collectors.toList());
//	}

	@Override
	public void delete(Integer id) {
		Page page = entityFetcher.getPageById(id);

		// List<SharedPage> list = sharedPageRepository.findAllByPageId(id);
		// for (SharedPage shared : list) {
		// 	sharedPageRepository.delete(shared);
		// }

//		for (Tag tag : page.getTags()) {
//			tag.getPages().remove(page);
//		}
//		page.getTags().clear();

		pageRepository.delete(page);
	}
}
