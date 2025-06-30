package com.nhaqua23.jotion.dto;

import com.nhaqua23.jotion.model.StaticNote;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StaticNoteDTO {

	private Integer id;
	private String content;
	private Integer userId;
	private Integer pageId;

	public static StaticNote toStaticNote(StaticNoteDTO dto) {
		final StaticNote staticNote = new StaticNote();

		staticNote.setContent(dto.getContent());

		return staticNote;
	}

	public static StaticNoteDTO toStaticNoteDTO(StaticNote staticNote) {
		return StaticNoteDTO.builder()



				.id(staticNote.getId())
				.content(staticNote.getContent())
				.userId(staticNote.getUser().getId())
				.pageId(staticNote.getPage().getId())
				.build();
	}
}
