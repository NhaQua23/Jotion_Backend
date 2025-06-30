package com.nhaqua23.jotion.controller.api;

import com.nhaqua23.jotion.dto.PageDTO;
import com.nhaqua23.jotion.dto.SharedPageDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "SharedPage", description = "SharedPage management APIs")
@RequestMapping("/api")
public interface SharedPageAPI {

	@PostMapping(
			value = "/pages/share",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Share  page",
			description = "Get a SharedPageDTO object. The response is SharedPageDTO object if share successfully"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Page created successfully"),
			@ApiResponse(responseCode = "1001", description = "User not found"),
			@ApiResponse(responseCode = "2002", description = "Page not valid"),
	})
	ResponseEntity<SharedPageDTO> sharePage(
			@RequestBody SharedPageDTO dto
	);

	@PostMapping(
			value = "/pages/unshare",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "UnShare  page",
			description = "UnShare page"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Page created successfully")
	})
	ResponseEntity<SharedPageDTO> unSharePage(
			@RequestBody SharedPageDTO dto
	);

	@GetMapping(
			value = "/shared",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Get all shared pages",
			description = "Retrieve all shared pages"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Shared pages retrieved successfully"),
	})
	ResponseEntity<List<SharedPageDTO>> getAllSharedPages();

	@GetMapping(
			value = "/shared/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Get the shared page by id",
			description = "Retrieve a specific shared page by ID"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Shared page retrieved successfully"),
			@ApiResponse(responseCode = "1002", description = "Shared page not found"),
	})
	ResponseEntity<SharedPageDTO> getPageById(
			@PathVariable("id") Integer id
	);

	@GetMapping(
			value = "/users/{id}/shared/pages",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Get all shared pages",
			description = "Retrieve all shared pages"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Shared pages retrieved successfully"),
	})
	ResponseEntity<List<PageDTO>> getAllSharedPagesByUserId(
			@PathVariable("id") Integer id
	);
}
