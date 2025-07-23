package com.nhaqua23.jotion.controller.api;

import com.nhaqua23.jotion.dto.response.NoteTemplateResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "StaticNote", description = "StaticNote management APIs")
@RequestMapping("/api")
public interface NoteTemplateAPI {

	@PostMapping(
			value = "/statics",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Create a block",
			description = "Get a StaticNoteDTO object. The response is StaticNoteDTO object if create successfully"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "block created successfully"),
			@ApiResponse(responseCode = "2001", description = "block not valid"),
	})
	ResponseEntity<NoteTemplateResponse> createStatic(
			@RequestBody NoteTemplateResponse dto
	);

	@GetMapping(
			value = "/statics",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Get all blocks",
			description = "Retrieve all blocks"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "blocks retrieved successfully"),
	})
	ResponseEntity<List<NoteTemplateResponse>> getAllStatics();

	@GetMapping(
			value = "/statics/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Get the block by id",
			description = "Retrieve a specific block by id"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "block retrieved successfully"),
			@ApiResponse(responseCode = "1001", description = "block not found"),
	})
	ResponseEntity<NoteTemplateResponse> getStaticNoteById(
			@PathVariable("id") Integer id
	);

	@GetMapping(
			value = "/pages/{id}/statics",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Get the block by id",
			description = "Retrieve a specific block by id"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "block retrieved successfully"),
			@ApiResponse(responseCode = "1001", description = "block not found"),
	})
	ResponseEntity<NoteTemplateResponse> getStaticNoteByPageId(
			@PathVariable("id") Integer id
	);

	@DeleteMapping(
			value = "/statics/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Delete the block",
			description = "Delete a specific block by ID"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "block deleted successfully"),
			@ApiResponse(responseCode = "1001", description = "block not found"),
	})
	ResponseEntity deleteStaticNoteById(
			@PathVariable("id") Integer id
	);
}
