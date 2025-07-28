package com.nhaqua23.jotion.controller.api;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nhaqua23.jotion.dto.response.CommonResponse;
import com.nhaqua23.jotion.dto.shared.RevokePageAccessRequest;
import com.nhaqua23.jotion.dto.shared.SharePageRequest;
import com.nhaqua23.jotion.dto.shared.SharedPageResponse;
import com.nhaqua23.jotion.dto.shared.UnsharePageRequest;
import com.nhaqua23.jotion.dto.shared.UpdateUserRoleRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "SharedPage", description = "Page sharing and collaboration management APIs")
@RequestMapping("/api/pages")
public interface SharedPageAPI {

	@PostMapping(
			value = "/share",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Share page with user",
			description = "Share a page with another user by email with specified role"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Page shared successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid request data"),
			@ApiResponse(responseCode = "401", description = "Unauthorized to share this page"),
			@ApiResponse(responseCode = "404", description = "Page or user not found"),
			@ApiResponse(responseCode = "409", description = "Page already shared with this user")
	})
	ResponseEntity<CommonResponse<SharedPageResponse>> sharePage(
			@Valid @RequestBody SharePageRequest request
	);

	@PostMapping(
			value = "/unshare",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Unshare page from user",
			description = "Remove user access to a shared page"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Page unshared successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid request data"),
			@ApiResponse(responseCode = "401", description = "Unauthorized to unshare this page"),
			@ApiResponse(responseCode = "404", description = "Page or user not found")
	})
	ResponseEntity<CommonResponse<SharedPageResponse>> unSharePage(
			@Valid @RequestBody UnsharePageRequest request
	);

	@GetMapping(
			value = "/shared",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Get all shared pages",
			description = "Retrieve all shared page records (admin only)"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Shared pages retrieved successfully")
	})
	ResponseEntity<CommonResponse<SharedPageResponse>> getAllSharedPages();

	@GetMapping(
			value = "/shared/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Get shared page by ID",
			description = "Retrieve a specific shared page record by ID"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Shared page found"),
			@ApiResponse(responseCode = "404", description = "Shared page not found")
	})
	ResponseEntity<CommonResponse<SharedPageResponse>> getSharedPageById(
			@Parameter(description = "Shared page ID") @PathVariable Integer id
	);

	@GetMapping(
			value = "/user/{userId}/shared",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Get user's shared pages",
			description = "Retrieve all pages shared with a specific user"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Shared pages retrieved successfully")
	})
	ResponseEntity<CommonResponse<SharedPageResponse>> getSharedPagesByUserId(
			@Parameter(description = "User ID") @PathVariable Integer userId
	);

	@GetMapping(
			value = "/{pageId}/collaborators",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Get page collaborators",
			description = "Retrieve all users who have access to a specific page"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Collaborators retrieved successfully"),
			@ApiResponse(responseCode = "404", description = "Page not found")
	})
	ResponseEntity<CommonResponse<SharedPageResponse>> getPageCollaborators(
			@Parameter(description = "Page ID") @PathVariable Integer pageId
	);

	@GetMapping(
			value = "/{pageId}/access/{userId}",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Check user page access",
			description = "Check if a user has access to a specific page"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Access check completed")
	})
	ResponseEntity<CommonResponse<Boolean>> checkPageAccess(
			@Parameter(description = "Page ID") @PathVariable Integer pageId,
			@Parameter(description = "User ID") @PathVariable Integer userId
	);

	@PutMapping(
			value = "/collaborators/role",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Update user role",
			description = "Update a user's role for a shared page"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Role updated successfully"),
			@ApiResponse(responseCode = "401", description = "Unauthorized to update roles"),
			@ApiResponse(responseCode = "404", description = "Page or user not found")
	})
	ResponseEntity<CommonResponse<SharedPageResponse>> updateUserRole(
			@Valid @RequestBody UpdateUserRoleRequest request
	);

	@DeleteMapping(
			value = "/collaborators",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	@Operation(
			summary = "Revoke page access",
			description = "Revoke a user's access to a shared page"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Access revoked successfully"),
			@ApiResponse(responseCode = "401", description = "Unauthorized to revoke access"),
			@ApiResponse(responseCode = "404", description = "Page or user not found")
	})
	ResponseEntity<CommonResponse<SharedPageResponse>> revokePageAccess(
			@Valid @RequestBody RevokePageAccessRequest request
	);
}
