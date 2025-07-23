package com.nhaqua23.jotion.dto.response;

import com.nhaqua23.jotion.model.PageContent;
import lombok.Data;

@Data
public class PageContentResponse {

	private Integer id;
	private String content;
	private Integer pageId;
	private Integer createdById;

	public static PageContent toBlockNote(PageContentResponse dto) {
		final PageContent note = new PageContent();

		note.setContent(dto.getContent());

		return note;
	}

	public static PageContentResponse toBlockNoteDTO(PageContent note) {
		final PageContentResponse dto = new PageContentResponse();

		dto.setId(note.getId());
		dto.setContent(note.getContent());
		dto.setPageId(note.getPage().getId());
		dto.setCreatedById(note.getCreatedBy().getId());

		return dto;
	}
}
