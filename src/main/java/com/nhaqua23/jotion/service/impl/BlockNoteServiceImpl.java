package com.nhaqua23.jotion.service.impl;

import com.nhaqua23.jotion.dto.BlockNoteDTO;
import com.nhaqua23.jotion.exception.EntityNotFoundException;
import com.nhaqua23.jotion.exception.ErrorCode;
import com.nhaqua23.jotion.model.*;
import com.nhaqua23.jotion.repository.BlockNoteRepository;
import com.nhaqua23.jotion.repository.PageRepository;
import com.nhaqua23.jotion.repository.SharedPageRepository;
import com.nhaqua23.jotion.repository.UserRepository;
import com.nhaqua23.jotion.service.BlockNoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BlockNoteServiceImpl implements BlockNoteService {

	@Autowired
	private BlockNoteRepository blockNoteRepository;

	@Autowired
	private PageRepository pageRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SharedPageRepository sharedPageRepository;

//	private PageWebSocketHandler pageWebSocketHandler;

	@Override
	public BlockNoteDTO save(BlockNoteDTO dto) {
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
					return new BlockNoteDTO();
				}
			}

			BlockNote note = blockNoteRepository.findBlockNoteByPageId(dto.getPageId());
			note.setContent(dto.getContent());

//			String updateMessage = "Page " + dto.getId() + " has been updated";
//			pageWebSocketHandler.broadcastUpdate(updateMessage);

			return BlockNoteDTO.toBlockNoteDTO(blockNoteRepository.save(note));
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

			BlockNote note = BlockNoteDTO.toBlockNote(dto);
			note.setPage(page);
			note.setCreatedBy(user);

			return BlockNoteDTO.toBlockNoteDTO(blockNoteRepository.save(note));
		}
	}

	@Override
	public BlockNoteDTO update(Integer id, BlockNoteDTO dto) {
		BlockNote note = blockNoteRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(
						"Block not found with ID = " + id,
						ErrorCode.TEXT_NOT_FOUND
				));

		note.setContent(dto.getContent());

		return BlockNoteDTO.toBlockNoteDTO(blockNoteRepository.save(note));
	}

	@Override
	public List<BlockNoteDTO> getAll() {
		return blockNoteRepository.findAll().stream()
				.map(BlockNoteDTO::toBlockNoteDTO).collect(Collectors.toList());
	}

	@Override
	public BlockNoteDTO getById(Integer id) {
		return blockNoteRepository.findById(id)
				.map(BlockNoteDTO::toBlockNoteDTO)
				.orElseThrow(() -> new EntityNotFoundException(
						"Block not found with ID = " + id,
						ErrorCode.TEXT_NOT_FOUND
				));
	}

	@Override
	public List<BlockNoteDTO> getAllByPageId(Integer pageId) {
		return blockNoteRepository.findBlockNotesByPageId(pageId).stream()
				.map(BlockNoteDTO::toBlockNoteDTO).collect(Collectors.toList());
	}

	@Override
	public BlockNoteDTO getByPageId(Integer pageId) {
		BlockNote note = blockNoteRepository.findBlockNoteByPageId(pageId);

		if (note == null) {
			return new BlockNoteDTO();
		}

		return BlockNoteDTO.toBlockNoteDTO(note);
	}

	@Override
	public void delete(Integer id) {
		BlockNote note = blockNoteRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(
						"Block not found with ID = " + id,
						ErrorCode.TEXT_NOT_FOUND
				));

		blockNoteRepository.delete(note);
	}
}
