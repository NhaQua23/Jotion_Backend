package com.nhaqua23.jotion.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.nhaqua23.jotion.dto.page.CreatePageRequest;
import com.nhaqua23.jotion.dto.page.PageResponse;
import com.nhaqua23.jotion.dto.page.UpdateBackgroundRequest;
import com.nhaqua23.jotion.dto.page.UpdateTitleRequest;
import com.nhaqua23.jotion.exception.EntityNotFoundException;
import com.nhaqua23.jotion.exception.ErrorCode;
import com.nhaqua23.jotion.mapper.PageMapper;
import com.nhaqua23.jotion.model.Page;
import com.nhaqua23.jotion.model.SharedPage;
import com.nhaqua23.jotion.model.User;
import com.nhaqua23.jotion.model.UserRole;
import com.nhaqua23.jotion.model.Workspace;
import com.nhaqua23.jotion.repository.PageRepository;
import com.nhaqua23.jotion.repository.SharedPageRepository;
import com.nhaqua23.jotion.repository.UserRepository;
import com.nhaqua23.jotion.repository.WorkspaceRepository;
import com.nhaqua23.jotion.service.PageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PageServiceImpl implements PageService {

	private final PageRepository pageRepository;
	private final WorkspaceRepository workspaceRepository;
	private final UserRepository userRepository;
	private final SharedPageRepository sharedPageRepository;
	private final PageMapper pageMapper;

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

		User user = userRepository.findById(request.getAuthorId())
				.orElseThrow(() -> new EntityNotFoundException(
						"User not found with ID = " + request.getAuthorId(),
						ErrorCode.USER_NOT_FOUND
				));
		Workspace workspace = workspaceRepository.findById(request.getWorkspaceId())
				.orElseThrow(() -> new EntityNotFoundException(
						"Workspace not found with ID = " + request.getWorkspaceId(),
						ErrorCode.WORKSPACE_NOT_FOUND
				));

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
			Page page = pageRepository.findById(id)
					.orElseThrow(() -> new EntityNotFoundException(
							"Page not found with id: " + id,
							ErrorCode.PAGE_NOT_FOUND
					));
			User user = userRepository.findById(request.getAuthorId())
					.orElseThrow(() -> new EntityNotFoundException(
							"User not found with id: " + request.getAuthorId(),
							ErrorCode.USER_NOT_FOUND
					));
			SharedPage sharedPage = sharedPageRepository.findByUserAndPage(user, page)
					.orElseThrow(() -> new EntityNotFoundException(
							"Current user does not have access to unshared this page",
							ErrorCode.PAGE_NOT_FOUND
					));

			if (!sharedPage.getRole().equals(UserRole.OWNER) &&
					!sharedPage.getRole().equals(UserRole.COLLABORATOR)) {
				return new PageResponse();
			}
		}

		Page page = pageRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("" +
						"Page not found with ID: " + id,
						ErrorCode.PAGE_NOT_FOUND
				));

		page.setTitle(request.getTitle());
		page.setUpdatedAt(LocalDate.now());

		return pageMapper.toPageResponse(pageRepository.save(page));
	}

	@Override
	public PageResponse updateBackground(Integer id, UpdateBackgroundRequest request) {
		Boolean isSharedPage = sharedPageRepository.existsByPageId(id);
		if (isSharedPage) {
			Page page = pageRepository.findById(id)
					.orElseThrow(() -> new EntityNotFoundException(
							"Page not found with id: " + id,
							ErrorCode.PAGE_NOT_FOUND
					));
			User user = userRepository.findById(request.getAuthorId())
					.orElseThrow(() -> new EntityNotFoundException(
							"User not found with id: " + request.getAuthorId(),
							ErrorCode.USER_NOT_FOUND
					));
			SharedPage sharedPage = sharedPageRepository.findByUserAndPage(user, page)
					.orElseThrow(() -> new EntityNotFoundException(
							"Current user does not have access to unshared this page",
							ErrorCode.PAGE_NOT_FOUND
					));

			if (!sharedPage.getRole().equals(UserRole.OWNER) &&
					!sharedPage.getRole().equals(UserRole.COLLABORATOR)) {
				return new PageResponse();
			}
		}

		Page page = pageRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("" +
						"Page not found with ID: " + id,
						ErrorCode.PAGE_NOT_FOUND
				));

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
		return pageRepository.findById(id)
				.map(pageMapper::toPageResponse)
				.orElseThrow(() -> new EntityNotFoundException("" +
						"Page not found with ID = " + id,
						ErrorCode.PAGE_NOT_FOUND
				));
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
		Page page = pageRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("" +
						"Page not found with ID = " + id,
						ErrorCode.PAGE_NOT_FOUND
				));

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
