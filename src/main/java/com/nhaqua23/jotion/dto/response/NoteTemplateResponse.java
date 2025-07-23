package com.nhaqua23.jotion.dto.response;

import com.nhaqua23.jotion.model.NoteTemplate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NoteTemplateResponse {

	private Integer id;
	private String content;
	private Integer userId;
	private Integer pageId;

	public static NoteTemplate toStaticNote(NoteTemplateResponse dto) {
		final NoteTemplate staticNote = new NoteTemplate();

		staticNote.setContent(dto.getContent());

		return staticNote;
	}

	public static NoteTemplateResponse toStaticNoteDTO(NoteTemplate staticNote) {
		return NoteTemplateResponse.builder()



				.id(staticNote.getId())
				.content(staticNote.getContent())
				.userId(staticNote.getUser().getId())
				.pageId(staticNote.getPage().getId())
				.build();
	}
}
