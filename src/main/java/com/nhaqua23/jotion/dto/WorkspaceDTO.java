package com.nhaqua23.jotion.dto;

import com.nhaqua23.jotion.model.Workspace;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class WorkspaceDTO {

	private Integer id;
	private String name;
	private LocalDate createdAt;
	private LocalDate updatedAt;
	private boolean editable;
	private Integer userId;


	public static Workspace toWorkspace(WorkspaceDTO dto) {
		final Workspace workspace = new Workspace();

		workspace.setName(dto.getName());
		workspace.setCreatedAt(dto.getCreatedAt());
		workspace.setUpdatedAt(dto.getUpdatedAt());

		return workspace;
	}

	public static WorkspaceDTO toWorkspaceDTO(Workspace workspace) {
		return WorkspaceDTO.builder()
				.id(workspace.getId())
				.name(workspace.getName())
				.createdAt(workspace.getCreatedAt())
				.updatedAt(workspace.getUpdatedAt())
				.editable(workspace.isEditable())
				.userId(workspace.getUser().getId())
				.build();
	}
}
