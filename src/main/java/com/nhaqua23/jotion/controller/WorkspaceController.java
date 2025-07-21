package com.nhaqua23.jotion.controller;

import com.nhaqua23.jotion.controller.api.WorkspaceAPI;
import com.nhaqua23.jotion.dto.response.CommonResponse;
import com.nhaqua23.jotion.dto.workspace.CreateWorkspaceRequest;
import com.nhaqua23.jotion.dto.workspace.UpdateWorkspaceRequest;
import com.nhaqua23.jotion.dto.workspace.WorkspaceResponse;
import com.nhaqua23.jotion.service.WorkspaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WorkspaceController implements WorkspaceAPI {

	private final WorkspaceService workspaceService;

	@Override
	public ResponseEntity<CommonResponse<WorkspaceResponse>> createWorkspace(CreateWorkspaceRequest request) {
		CommonResponse response = CommonResponse.builder()
				.status("success")
				.message("Workspace created successfully")
				.data(workspaceService.save(request))
				.build();

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<CommonResponse<WorkspaceResponse>> updateWorkspace(Integer id, UpdateWorkspaceRequest request) {
		CommonResponse response = CommonResponse.builder()
				.status("success")
				.message("Workspace updated successfully")
				.data(workspaceService.update(id, request))
				.build();

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CommonResponse<WorkspaceResponse>> getAllWorkspaces() {
		CommonResponse response = CommonResponse.builder()
				.status("success")
				.data(workspaceService.getAll())
				.build();

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CommonResponse<WorkspaceResponse>> getWorkspaceById(Integer id) {
		CommonResponse response = CommonResponse.builder()
				.status("success")
				.data(workspaceService.getById(id))
				.build();

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CommonResponse<WorkspaceResponse>> getAllWorkspacesByUserId(Integer userId) {
		CommonResponse response = CommonResponse.builder()
				.status("success")
				.data(workspaceService.getAllByUserId(userId))
				.build();

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CommonResponse<WorkspaceResponse>> deleteWorkspaceById(Integer id) {
		workspaceService.delete(id);
		CommonResponse response = CommonResponse.builder()
				.status("success")
				.message("Workspace deleted successfully")
				.build();

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
