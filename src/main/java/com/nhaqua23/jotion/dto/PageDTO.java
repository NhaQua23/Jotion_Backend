package com.nhaqua23.jotion.dto;

import com.nhaqua23.jotion.model.Page;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PageDTO {

	private Integer id;
	private String title;
	private String background;
	private LocalDate createdAt;
	private LocalDate updatedAt;
	private Integer workspaceId;
	private Integer authorId;

	public static Page toPage(PageDTO dto) {
		final Page page = new Page();

		page.setTitle(dto.getTitle());
		page.setBackground(dto.getBackground());
		page.setCreatedAt(dto.getCreatedAt());
		page.setUpdatedAt(dto.getUpdatedAt());

		return page;
	}

	public static PageDTO toPageDTO(Page page) {
		final PageDTO dto = new PageDTO();

		dto.setId(page.getId());
		dto.setTitle(page.getTitle());
		dto.setBackground(page.getBackground());
		dto.setCreatedAt(page.getCreatedAt());
		dto.setUpdatedAt(page.getUpdatedAt());
		dto.setWorkspaceId(page.getWorkspace().getId());
		dto.setAuthorId(page.getAuthor().getId());

		return dto;
	}
}
