package com.nhaqua23.jotion.dto.response;

import com.nhaqua23.jotion.model.BlockNote;
import lombok.Data;

@Data
public class BlockNoteResponse {

	private Integer id;
	private String content;
	private Integer pageId;
	private Integer createdById;

	public static BlockNote toBlockNote(BlockNoteResponse dto) {
		final BlockNote note = new BlockNote();

		note.setContent(dto.getContent());

		return note;
	}

	public static BlockNoteResponse toBlockNoteDTO(BlockNote note) {
		final BlockNoteResponse dto = new BlockNoteResponse();

		dto.setId(note.getId());
		dto.setContent(note.getContent());
		dto.setPageId(note.getPage().getId());
		dto.setCreatedById(note.getCreatedBy().getId());

		return dto;
	}
}
