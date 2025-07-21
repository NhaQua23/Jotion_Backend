package com.nhaqua23.jotion.service.impl;

import com.nhaqua23.jotion.dto.response.StaticNoteResponse;
import com.nhaqua23.jotion.exception.EntityNotFoundException;
import com.nhaqua23.jotion.exception.ErrorCode;
import com.nhaqua23.jotion.model.Page;
import com.nhaqua23.jotion.model.StaticNote;
import com.nhaqua23.jotion.model.User;
import com.nhaqua23.jotion.repository.PageRepository;
import com.nhaqua23.jotion.repository.StaticNoteRepository;
import com.nhaqua23.jotion.repository.UserRepository;
import com.nhaqua23.jotion.service.StaticNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StaticNoteServiceImpl implements StaticNoteService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PageRepository pageRepository;

	@Autowired
	private StaticNoteRepository staticNoteRepository;

	@Override
	public StaticNoteResponse save(StaticNoteResponse dto) {
		if (staticNoteRepository.existsByPageId(dto.getPageId())) {
			StaticNote staticNote = staticNoteRepository.findByPageId(dto.getPageId());
			staticNote.setContent(dto.getContent());

			return StaticNoteResponse.toStaticNoteDTO(staticNoteRepository.save(staticNote));
		} else {
			User user = userRepository.findById(dto.getUserId())
					.orElseThrow(() -> new EntityNotFoundException(
							"User not found with ID = " + dto.getUserId(),
							ErrorCode.USER_NOT_FOUND
					));
			Page page = pageRepository.findById(dto.getPageId())
					.orElseThrow(() -> new EntityNotFoundException(
							"Page not found with ID = " + dto.getPageId(),
							ErrorCode.PAGE_NOT_FOUND
					));

			StaticNote staticNote = StaticNoteResponse.toStaticNote(dto);
			staticNote.setUser(user);
			staticNote.setPage(page);

			return StaticNoteResponse.toStaticNoteDTO(staticNoteRepository.save(staticNote));
		}
	}

	@Override
	public List<StaticNoteResponse> getAll() {
		return staticNoteRepository.findAll().stream()
				.map(StaticNoteResponse::toStaticNoteDTO).collect(Collectors.toList());
	}

	@Override
	public StaticNoteResponse getById(Integer id) {
		return staticNoteRepository.findById(id)
				.map(StaticNoteResponse::toStaticNoteDTO)
				.orElseThrow(() -> new EntityNotFoundException(
						"No note found with id: " + id,
						ErrorCode.PAGE_NOT_FOUND
				));
	}

	@Override
	public StaticNoteResponse getByPageId(Integer pageId) {
		return StaticNoteResponse.toStaticNoteDTO(staticNoteRepository.findByPageId(pageId));

	}

	@Override
	public void delete(Integer id) {
		staticNoteRepository.deleteById(id);
	}
}
