package com.nhaqua23.jotion.service.impl;

import com.nhaqua23.jotion.dto.response.PageContentResponse;
import com.nhaqua23.jotion.exception.EntityNotFoundException;
import com.nhaqua23.jotion.exception.ErrorCode;
import com.nhaqua23.jotion.model.*;
import com.nhaqua23.jotion.repository.PageContentRepository;
import com.nhaqua23.jotion.repository.PageRepository;
import com.nhaqua23.jotion.repository.SharedPageRepository;
import com.nhaqua23.jotion.repository.UserRepository;
import com.nhaqua23.jotion.service.PageContentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PageContentServiceImpl implements PageContentService {

	@Autowired
	private PageContentRepository blockNoteRepository;

	@Autowired
	private PageRepository pageRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SharedPageRepository sharedPageRepository;

//	private PageWebSocketHandler pageWebSocketHandler;

	@Override
	public PageContentResponse save(PageContentResponse dto) {
		if (blockNoteRepository.findBlockNoteByPageId(dto.getPageId()) != null) {
			Boolean isSharedPage = sharedPageRepository.existsByPageId(dto.getPageId());
			if (isSharedPage) {
				Page page = pageRepository.findById(dto.getPageId())
						.orElseThrow(() -> new EntityNotFoundException(
								"Page not found with id: " + dto.getPageId(),
								ErrorCode.PAGE_NOT_FOUND
						));
				User user = userRepository.findById(dto.getCreatedById())
						.orElseThrow(() -> new EntityNotFoundException(
								"User not found",
								ErrorCode.USER_NOT_FOUND
						));
				SharedPage sharedPage = sharedPageRepository.findByUserAndPage(user, page)
						.orElseThrow(() -> new EntityNotFoundException(
								"Current user does not have access to unshared this page",
								ErrorCode.PAGE_NOT_FOUND
						));

				if (!sharedPage.getRole().equals(UserRole.OWNER) &&
						!sharedPage.getRole().equals(UserRole.COLLABORATOR)) {
					return new PageContentResponse();
				}
			}

			PageContent note = blockNoteRepository.findBlockNoteByPageId(dto.getPageId());
			note.setContent(dto.getContent());

//			String updateMessage = "Page " + dto.getId() + " has been updated";
//			pageWebSocketHandler.broadcastUpdate(updateMessage);

			return PageContentResponse.toBlockNoteDTO(blockNoteRepository.save(note));
		} else {
			User user = userRepository.findById(dto.getCreatedById())
					.orElseThrow(() -> new EntityNotFoundException(
							"User not found with ID = " + dto.getCreatedById(),
							ErrorCode.USER_NOT_FOUND
					));
			Page page = pageRepository.findById(dto.getPageId())
					.orElseThrow(() -> new EntityNotFoundException(
							"Page not found with ID = " + dto.getPageId(),
							ErrorCode.PAGE_NOT_FOUND
					));

			PageContent note = PageContentResponse.toBlockNote(dto);
			note.setPage(page);
			note.setCreatedBy(user);

			return PageContentResponse.toBlockNoteDTO(blockNoteRepository.save(note));
		}
	}

	@Override
	public PageContentResponse update(Integer id, PageContentResponse dto) {
		PageContent note = blockNoteRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(
						"Block not found with ID = " + id,
						ErrorCode.TEXT_NOT_FOUND
				));

		note.setContent(dto.getContent());

		return PageContentResponse.toBlockNoteDTO(blockNoteRepository.save(note));
	}

	@Override
	public List<PageContentResponse> getAll() {
		return blockNoteRepository.findAll().stream()
				.map(PageContentResponse::toBlockNoteDTO).collect(Collectors.toList());
	}

	@Override
	public PageContentResponse getById(Integer id) {
		return blockNoteRepository.findById(id)
				.map(PageContentResponse::toBlockNoteDTO)
				.orElseThrow(() -> new EntityNotFoundException(
						"Block not found with ID = " + id,
						ErrorCode.TEXT_NOT_FOUND
				));
	}

	@Override
	public List<PageContentResponse> getAllByPageId(Integer pageId) {
		return blockNoteRepository.findBlockNotesByPageId(pageId).stream()
				.map(PageContentResponse::toBlockNoteDTO).collect(Collectors.toList());
	}

	@Override
	public PageContentResponse getByPageId(Integer pageId) {
		PageContent note = blockNoteRepository.findBlockNoteByPageId(pageId);

		if (note == null) {
			return new PageContentResponse();
		}

		return PageContentResponse.toBlockNoteDTO(note);
	}

	@Override
	public void delete(Integer id) {
		PageContent note = blockNoteRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(
						"Block not found with ID = " + id,
						ErrorCode.TEXT_NOT_FOUND
				));

		blockNoteRepository.delete(note);
	}
}
