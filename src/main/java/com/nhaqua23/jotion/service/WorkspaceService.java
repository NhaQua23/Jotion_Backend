package com.nhaqua23.jotion.service;

import com.nhaqua23.jotion.dto.WorkspaceDTO;

import java.util.List;

public interface WorkspaceService {

	WorkspaceDTO save(WorkspaceDTO workspaceDTO);

	WorkspaceDTO update(Integer id, WorkspaceDTO workspaceDTO);

	List<WorkspaceDTO> getAll();

	WorkspaceDTO getById(Integer id);

	List<WorkspaceDTO> getAllByUserId(Integer userId);

	void delete(Integer id);
}
