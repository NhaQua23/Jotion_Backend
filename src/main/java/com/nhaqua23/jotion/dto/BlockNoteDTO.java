package com.nhaqua23.jotion.dto;

import com.nhaqua23.jotion.model.BlockNote;
import lombok.Data;

@Data
public class BlockNoteDTO {

	private Integer id;
	private String content;
	private Integer pageId;
	private Integer createdById;

	public static BlockNote toBlockNote(BlockNoteDTO dto) {
		final BlockNote note = new BlockNote();

		note.setContent(dto.getContent());

		return note;
	}

	public static BlockNoteDTO toBlockNoteDTO(BlockNote note) {
		final BlockNoteDTO dto = new BlockNoteDTO();

		dto.setId(note.getId());
		dto.setContent(note.getContent());
		dto.setPageId(note.getPage().getId());
		dto.setCreatedById(note.getCreatedBy().getId());

		return dto;
	}
}
