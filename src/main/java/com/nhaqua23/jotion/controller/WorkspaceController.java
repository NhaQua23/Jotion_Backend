package com.nhaqua23.jotion.controller;

import com.nhaqua23.jotion.controller.api.WorkspaceAPI;
import com.nhaqua23.jotion.dto.WorkspaceDTO;
import com.nhaqua23.jotion.service.WorkspaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WorkspaceController implements WorkspaceAPI {

	@Autowired
	private WorkspaceService workspaceService;

	@Override
	public ResponseEntity<WorkspaceDTO> createWorkspace(WorkspaceDTO dto) {
		return new ResponseEntity<>(workspaceService.save(dto), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<WorkspaceDTO> updateWorkspace(Integer id, WorkspaceDTO dto) {
		return new ResponseEntity<>(workspaceService.update(id, dto), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<WorkspaceDTO>> getAllWorkspaces() {
		return new ResponseEntity<>(workspaceService.getAll(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<WorkspaceDTO> getWorkspaceById(Integer id) {
		return new ResponseEntity<>(workspaceService.getById(id), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<WorkspaceDTO>> getAllWorkspacesByUserId(Integer userId) {
		return new ResponseEntity<>(workspaceService.getAllByUserId(userId), HttpStatus.OK);
	}

	@Override
	public ResponseEntity deleteWorkspaceById(Integer id) {
		workspaceService.delete(id);
		return new ResponseEntity(HttpStatus.OK);
	}
}
