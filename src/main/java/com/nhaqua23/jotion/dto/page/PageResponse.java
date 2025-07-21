package com.nhaqua23.jotion.dto.page;

import com.nhaqua23.jotion.model.Page;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageResponse {

	Integer id;
	String title;
	String background;
	LocalDate createdAt;
	LocalDate updatedAt;
	Integer workspaceId;
	Integer authorId;

	public static Page toPage(PageResponse dto) {
		final Page page = new Page();

		page.setTitle(dto.getTitle());
		page.setBackground(dto.getBackground());
		page.setCreatedAt(dto.getCreatedAt());
		page.setUpdatedAt(dto.getUpdatedAt());

		return page;
	}

	public static PageResponse toPageDTO(Page page) {
		final PageResponse dto = new PageResponse();

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
