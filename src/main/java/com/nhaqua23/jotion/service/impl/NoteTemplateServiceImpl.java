package com.nhaqua23.jotion.service.impl;

import com.nhaqua23.jotion.dto.response.NoteTemplateResponse;
import com.nhaqua23.jotion.exception.EntityNotFoundException;
import com.nhaqua23.jotion.exception.ErrorCode;
import com.nhaqua23.jotion.model.Page;
import com.nhaqua23.jotion.model.NoteTemplate;
import com.nhaqua23.jotion.model.User;
import com.nhaqua23.jotion.repository.PageRepository;
import com.nhaqua23.jotion.repository.NoteTemplateRepository;
import com.nhaqua23.jotion.repository.UserRepository;
import com.nhaqua23.jotion.service.NoteTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteTemplateServiceImpl implements NoteTemplateService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PageRepository pageRepository;

	@Autowired
	private NoteTemplateRepository staticNoteRepository;

	@Override
	public NoteTemplateResponse save(NoteTemplateResponse dto) {
		if (staticNoteRepository.existsByPageId(dto.getPageId())) {
			NoteTemplate staticNote = staticNoteRepository.findByPageId(dto.getPageId());
			staticNote.setContent(dto.getContent());

			return NoteTemplateResponse.toStaticNoteDTO(staticNoteRepository.save(staticNote));
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

			NoteTemplate staticNote = NoteTemplateResponse.toStaticNote(dto);
			staticNote.setUser(user);
			staticNote.setPage(page);

			return NoteTemplateResponse.toStaticNoteDTO(staticNoteRepository.save(staticNote));
		}
	}

	@Override
	public List<NoteTemplateResponse> getAll() {
		return staticNoteRepository.findAll().stream()
				.map(NoteTemplateResponse::toStaticNoteDTO).collect(Collectors.toList());
	}

	@Override
	public NoteTemplateResponse getById(Integer id) {
		return staticNoteRepository.findById(id)
				.map(NoteTemplateResponse::toStaticNoteDTO)
				.orElseThrow(() -> new EntityNotFoundException(
						"No note found with id: " + id,
						ErrorCode.PAGE_NOT_FOUND
				));
	}

	@Override
	public NoteTemplateResponse getByPageId(Integer pageId) {
		return NoteTemplateResponse.toStaticNoteDTO(staticNoteRepository.findByPageId(pageId));

	}

	@Override
	public void delete(Integer id) {
		staticNoteRepository.deleteById(id);
	}
}
