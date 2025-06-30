package com.nhaqua23.jotion.controller.api;

import com.nhaqua23.jotion.dto.PageDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Page", description = "Page management APIs")
@RequestMapping("/api")
public interface PageAPI {

	@PostMapping(
			value = "/pages",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Create a page",
			description = "Get a pageDTO object. The response is pageDTO object if create successfully"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Page created successfully"),
			@ApiResponse(responseCode = "1001", description = "User not found"),
			@ApiResponse(responseCode = "2002", description = "Workspace not found"),
			@ApiResponse(responseCode = "2002", description = "Page not valid"),
	})
	ResponseEntity<PageDTO> createPage(
			@RequestBody PageDTO dto
	);

	@PatchMapping(
			value = "/pages/{id}",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Update a page",
			description = "Update an existing page by ID"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Page updated successfully"),
			@ApiResponse(responseCode = "1002", description = "Page not found"),
			@ApiResponse(responseCode = "2002", description = "Page not valid"),
	})
	ResponseEntity<PageDTO> updatePage(
			@PathVariable("id") Integer id,
			@RequestBody PageDTO dto
	);

	@PatchMapping(
			value = "/pages/{id}/background",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Update a page",
			description = "Update an existing page by ID"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Page updated successfully"),
			@ApiResponse(responseCode = "1002", description = "Page not found"),
			@ApiResponse(responseCode = "2002", description = "Page not valid"),
	})
	ResponseEntity<PageDTO> updatePageBackground(
			@PathVariable("id") Integer id,
			@RequestBody PageDTO dto
	);

	@GetMapping(
			value = "/pages",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Get all pages",
			description = "Retrieve all pages"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Pages retrieved successfully"),
	})
	ResponseEntity<List<PageDTO>> getAllPages();

	@GetMapping(
			value = "/pages/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Get the page by id",
			description = "Retrieve a specific page by ID"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Page retrieved successfully"),
			@ApiResponse(responseCode = "1002", description = "Page not found"),
	})
	ResponseEntity<PageDTO> getPageById(
			@PathVariable("id") Integer id
	);

	@GetMapping(
			value = "/users/{id}/pages",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Get all pages by user id",
			description = "Retrieve all pages by a specific user ID"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Pages retrieved successfully"),
			@ApiResponse(responseCode = "1001", description = "User not found"),
	})
	ResponseEntity<List<PageDTO>> getAllPagesByAuthorId(
			@PathVariable("id") Integer authorId
	);

	@GetMapping(
			value = "/workspaces/{id}/pages",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Get all pages by workspace id",
			description = "Retrieve all pages by a specific workspace ID"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Pages retrieved successfully"),
			@ApiResponse(responseCode = "1001", description = "Workspace not found"),
	})
	ResponseEntity<List<PageDTO>> getAllPagesByWorkspaceId(
			@PathVariable("id") Integer workspaceId
	);

//	@GetMapping(
//			value = "/tags/{id}/pages",
//			produces = MediaType.APPLICATION_JSON_VALUE
//	)
//	@Operation(
//			summary = "Get all pages by tag id",
//			description = "Retrieve all pages by a specific tag ID"
//	)
//	@ApiResponses(value = {
//			@ApiResponse(responseCode = "200", description = "Pages retrieved successfully"),
//			@ApiResponse(responseCode = "1001", description = "Tag not found"),
//	})
//	ResponseEntity<List<PageDTO>> getAllPagesByTagId(
//			@PathVariable("id") Integer tagId
//	);

	@DeleteMapping(
			value = "/pages/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Delete the page",
			description = "Delete a specific page by ID"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Page deleted successfully"),
			@ApiResponse(responseCode = "1002", description = "Page not found"),
	})
	ResponseEntity deletePageById(
			@PathVariable("id") Integer id
	);
}
