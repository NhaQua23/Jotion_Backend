package com.nhaqua23.jotion.controller.api;

import com.nhaqua23.jotion.dto.WorkspaceDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Workspace", description = "Workspace management APIs")
@RequestMapping("/api")
public interface WorkspaceAPI {

	@PostMapping(
			value = "/workspaces",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Create a workspace",
			description = "Get a WorkspaceDTO object. The response is WorkspaceDTO object if create successfully"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Workspace created successfully"),
			@ApiResponse(responseCode = "1001", description = "User not found"),
			@ApiResponse(responseCode = "2002", description = "Workspace not valid"),
	})
	ResponseEntity<WorkspaceDTO> createWorkspace(
			@RequestBody WorkspaceDTO dto
	);

	@PatchMapping(
			value = "/workspaces/{id}",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Update a workspace",
			description = "Update an existing workspace by ID"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Workspace updated successfully"),
			@ApiResponse(responseCode = "1002", description = "Workspace not found"),
			@ApiResponse(responseCode = "2002", description = "Workspace not valid"),
	})
	ResponseEntity<WorkspaceDTO> updateWorkspace(
			@PathVariable("id") Integer id,
			@RequestBody WorkspaceDTO dto
	);

	@GetMapping(
			value = "/workspaces",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Get all workspaces",
			description = "Retrieve all workspaces"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Workspaces retrieved successfully"),
	})
	ResponseEntity<List<WorkspaceDTO>> getAllWorkspaces();

	@GetMapping(
			value = "/workspaces/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Get the workspace by id",
			description = "Retrieve a specific workspace by ID"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Workspace retrieved successfully"),
			@ApiResponse(responseCode = "1002", description = "Workspace not found"),
	})
	ResponseEntity<WorkspaceDTO> getWorkspaceById(
			@PathVariable("id") Integer id
	);

	@GetMapping(
			value = "/users/{id}/workspaces",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Get all workspaces by user id",
			description = "Retrieve all workspaces by a specific user ID"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Workspaces retrieved successfully"),
			@ApiResponse(responseCode = "1001", description = "User not found"),
	})
	ResponseEntity<List<WorkspaceDTO>> getAllWorkspacesByUserId(
			@PathVariable("id") Integer userId
	);

	@DeleteMapping(
			value = "/workspaces/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Delete the workspace",
			description = "Delete a specific workspace by ID"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Workspace deleted successfully"),
			@ApiResponse(responseCode = "1002", description = "Workspace not found"),
	})
	ResponseEntity deleteWorkspaceById(
			@PathVariable("id") Integer id
	);
}
