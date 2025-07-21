package com.nhaqua23.jotion.service;

import com.nhaqua23.jotion.dto.workspace.CreateWorkspaceRequest;
import com.nhaqua23.jotion.dto.workspace.UpdateWorkspaceRequest;
import com.nhaqua23.jotion.dto.workspace.WorkspaceResponse;

import java.util.List;

public interface WorkspaceService {

	WorkspaceResponse save(CreateWorkspaceRequest request);

	WorkspaceResponse update(Integer id, UpdateWorkspaceRequest request);

	List<WorkspaceResponse> getAll();

	WorkspaceResponse getById(Integer id);

	List<WorkspaceResponse> getAllByUserId(Integer userId);

	void delete(Integer id);
}
