package com.nhaqua23.jotion.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.nhaqua23.jotion.dto.content.PageContentRequest;
import com.nhaqua23.jotion.dto.content.PageContentResponse;
import com.nhaqua23.jotion.dto.response.CommonResponse;

import java.util.List;

@Tag(name = "PageContent", description = "Page content management APIs for note editing and storage")
@RequestMapping("/api")
public interface PageContentAPI {

	@PostMapping(
		value = "/contents", 
		consumes = MediaType.APPLICATION_JSON_VALUE, 
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
		summary = "Create page content", 
		description = "Create new content for a page. Each page can only have one active content."
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Page content created successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid input data"),
			@ApiResponse(responseCode = "401", description = "User not authenticated"),		
			@ApiResponse(responseCode = "403", description = "Insufficient permissions"),
			@ApiResponse(responseCode = "404", description = "Page content not found"),
			@ApiResponse(responseCode = "409", description = "Page content already exists")
	})
	ResponseEntity<CommonResponse<PageContentResponse>> createPageContent(
			@Valid @RequestBody PageContentRequest request);

	@GetMapping(
		value = "/contents/{id}", 
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
		summary = "Get page content by ID", 
		description = "Retrieve page content by its unique identifier"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Page content retrieved successfully"),
			@ApiResponse(responseCode = "401", description = "User not authenticated"),
			@ApiResponse(responseCode = "404", description = "Page content not found"),
			@ApiResponse(responseCode = "403", description = "Insufficient permissions")
	})
	ResponseEntity<CommonResponse<PageContentResponse>> getPageContentById(
			@Parameter(description = "Page content ID") @PathVariable Integer id);

	@GetMapping(
		value = "/pages/{pageId}/content", 
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
		summary = "Get page content by page ID", 
		description = "Retrieve the active content for a specific page"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Page content retrieved successfully"),
			@ApiResponse(responseCode = "401", description = "User not authenticated"),
			@ApiResponse(responseCode = "404", description = "Page content not found"),
			@ApiResponse(responseCode = "403", description = "Insufficient permissions")
	})
	ResponseEntity<CommonResponse<PageContentResponse>> getPageContentByPageId(
			@Parameter(description = "Page ID") @PathVariable Integer pageId);

	@GetMapping(
		value = "/contents", 
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
		summary = "Get all active page contents", 
		description = "Retrieve all active page contents ordered by last update"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Page contents retrieved successfully"),
			@ApiResponse(responseCode = "401", description = "User not authenticated"),
			@ApiResponse(responseCode = "404", description = "Page content not found"),
			@ApiResponse(responseCode = "403", description = "Insufficient permissions")
	})
	ResponseEntity<CommonResponse<PageContentResponse>> getAllPageContents();

	@GetMapping(
		value = "/pages/{pageId}/contents", 
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
		summary = "Get all page content versions by page ID", 
		description = "Retrieve all content versions (including inactive) for a specific page"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Page contents retrieved successfully"),
			@ApiResponse(responseCode = "401", description = "User not authenticated"),
			@ApiResponse(responseCode = "404", description = "Page content not found"),
			@ApiResponse(responseCode = "403", description = "Insufficient permissions")
	})
	ResponseEntity<CommonResponse<PageContentResponse>> getAllPageContentByPageId(
			@Parameter(description = "Page ID") @PathVariable Integer pageId);

	@GetMapping(
		value = "/users/{userId}/contents", 
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
		summary = "Get page contents by user", 
		description = "Retrieve all page contents created by a specific user"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Page contents retrieved successfully"),
			@ApiResponse(responseCode = "401", description = "User not authenticated"),
			@ApiResponse(responseCode = "404", description = "User not found"),
			@ApiResponse(responseCode = "403", description = "Insufficient permissions")
	})
	ResponseEntity<CommonResponse<PageContentResponse>> getPageContentsByUserId(
			@Parameter(description = "User ID") @PathVariable Integer userId);

	@PutMapping(
		value = "/contents/{id}", 
		consumes = MediaType.APPLICATION_JSON_VALUE, 
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
		summary = "Update page content", 
		description = "Update existing page content by ID"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Page content updated successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid input data"),
			@ApiResponse(responseCode = "403", description = "Insufficient permissions"),
			@ApiResponse(responseCode = "404", description = "Page content not found"),
			@ApiResponse(responseCode = "409", description = "Version conflict")
	})
	ResponseEntity<CommonResponse<PageContentResponse>> updatePageContent(
			@Parameter(description = "Page content ID") @PathVariable Integer id,
			@Valid @RequestBody PageContentRequest request);

	@PutMapping(
		value = "/pages/{pageId}/contents", 
		consumes = MediaType.APPLICATION_JSON_VALUE, 
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
		summary = "Update or create page content by page ID", 
		description = "Update existing content or create new content for a page"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Page content updated successfully"),
			@ApiResponse(responseCode = "201", description = "Page content created successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid input data"),
			@ApiResponse(responseCode = "403", description = "Insufficient permissions"),
			@ApiResponse(responseCode = "404", description = "Page not found")
	})
	ResponseEntity<CommonResponse<PageContentResponse>> updatePageContentByPageId(
			@Valid @RequestBody PageContentRequest request);

	@DeleteMapping(
		value = "/contents/{id}", 
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
		summary = "Delete page content", 
		description = "Permanently delete page content by ID"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Page content deleted successfully"),
			@ApiResponse(responseCode = "401", description = "User not authenticated"),			
			@ApiResponse(responseCode = "404", description = "Page content not found"),
			@ApiResponse(responseCode = "403", description = "Insufficient permissions")
	})
	ResponseEntity<CommonResponse<PageContentResponse>> deletePageContent(
			@Parameter(description = "Page content ID") @PathVariable Integer id);

	@DeleteMapping(
		value = "/pages/{pageId}/contents", 
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
		summary = "Delete all page content for a page", 
		description = "Permanently delete all content versions for a specific page"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Page contents deleted successfully"),
			@ApiResponse(responseCode = "401", description = "User not authenticated"),				
			@ApiResponse(responseCode = "404", description = "Page not found"),
			@ApiResponse(responseCode = "403", description = "Insufficient permissions")
	})
	ResponseEntity<CommonResponse<PageContentResponse>> deletePageContentByPageId(
			@Parameter(description = "Page ID") @PathVariable Integer pageId);

	// Permission APIs
	// @PostMapping(
	// 	value = "/pages/{pageId}/content/permissions/check", 
	// 	produces = MediaType.APPLICATION_JSON_VALUE
	// )
	// @Operation(
	// 	summary = "Check content permissions", 
	// 	description = "Check if authenticated user has permission to edit page content"
	// )
	// @ApiResponses(value = {
	// 		@ApiResponse(responseCode = "200", description = "Permission check completed"),
	// 		@ApiResponse(responseCode = "401", description = "User not authenticated"),
	// 		@ApiResponse(responseCode = "404", description = "Page not found")
	// })
	// ResponseEntity<CommonResponse<Boolean>> checkContentPermission(
	// 		@Parameter(description = "Page ID") @PathVariable Integer pageId,
	// 		Authentication authentication);

	// Content History APIs
	// @GetMapping(
	// 	value = "/pages/{pageId}/content/history", 
	// 	produces = MediaType.APPLICATION_JSON_VALUE
	// )
	// @Operation(
	// 	summary = "Get content history", 
	// 	description = "Retrieve all content versions for a page"
	// )
	// @ApiResponses(value = {
	// 		@ApiResponse(responseCode = "200", description = "Content history retrieved successfully"),
	// 		@ApiResponse(responseCode = "401", description = "User not authenticated"),
	// 		@ApiResponse(responseCode = "404", description = "Page not found"),
	// 		@ApiResponse(responseCode = "403", description = "Insufficient permissions")
	// })
	// ResponseEntity<CommonResponse<List<PageContentResponse>>> getContentHistory(
	// 		@Parameter(description = "Page ID") @PathVariable Integer pageId,
	// 		Authentication authentication);

	// @GetMapping(
	// 	value = "/pages/{pageId}/content/versions/{version}", 
	// 	produces = MediaType.APPLICATION_JSON_VALUE
	// )
	// @Operation(
	// 	summary = "Get specific content version", 
	// 	description = "Retrieve a specific version of page content"
	// )
	// @ApiResponses(value = {
	// 		@ApiResponse(responseCode = "200", description = "Content version retrieved successfully"),
	// 		@ApiResponse(responseCode = "401", description = "User not authenticated"),
	// 		@ApiResponse(responseCode = "404", description = "Content version not found"),
	// 		@ApiResponse(responseCode = "403", description = "Insufficient permissions")
	// })
	// ResponseEntity<CommonResponse<PageContentResponse>> getContentVersion(
	// 		@Parameter(description = "Page ID") @PathVariable Integer pageId,
	// 		@Parameter(description = "Content version") @PathVariable Integer version,
	// 		Authentication authentication);
}
