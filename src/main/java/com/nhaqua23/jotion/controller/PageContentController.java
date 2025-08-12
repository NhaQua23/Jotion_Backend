package com.nhaqua23.jotion.controller;

import com.nhaqua23.jotion.controller.api.PageContentAPI;
import com.nhaqua23.jotion.dto.content.PageContentRequest;
import com.nhaqua23.jotion.dto.content.PageContentResponse;
import com.nhaqua23.jotion.dto.response.CommonResponse;
import com.nhaqua23.jotion.exception.AuthorizationException;
import com.nhaqua23.jotion.exception.ErrorCode;
import com.nhaqua23.jotion.model.User;
import com.nhaqua23.jotion.service.PageContentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PageContentController implements PageContentAPI {

	private final PageContentService pageContentService;

	@Override
	public ResponseEntity<CommonResponse<PageContentResponse>> createPageContent(
			@Valid PageContentRequest request) {
		log.info("Creating page content for page {}", request.getPageId());
		CommonResponse response = CommonResponse.builder()
				.status("success")
				.message("Page content created successfully")
				.data(pageContentService.createPageContent(request))
				.build();

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@Override
	public ResponseEntity<CommonResponse<PageContentResponse>> getPageContentById(Integer id) {
		log.info("Getting page content by ID {}", id);
		CommonResponse response = CommonResponse.builder()
				.status("success")
				.message("Page content retrieved successfully")
				.data(pageContentService.getById(id))
				.build();

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CommonResponse<PageContentResponse>> getPageContentByPageId(Integer pageId) {
		log.info("Getting page content by page ID {}", pageId);
		CommonResponse response = CommonResponse.builder()
				.status("success")
				.message("Page content retrieved successfully")
				.data(pageContentService.getByPageId(pageId))
				.build();

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CommonResponse<PageContentResponse>> getAllPageContents() {
		log.info("Getting all page contents");
		CommonResponse response = CommonResponse.builder()
				.status("success")
				.message("All page contents retrieved successfully")
				.data(pageContentService.getAll())
				.build();

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CommonResponse<PageContentResponse>> getAllPageContentByPageId(Integer pageId) {
		log.info("Getting all page contents by page ID {}", pageId);
		CommonResponse response = CommonResponse.builder()
				.status("success")
				.message("All page contents retrieved successfully")
				.data(pageContentService.getAllByPageId(pageId))
				.build();

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CommonResponse<PageContentResponse>> getPageContentsByUserId(Integer userId) {
		log.info("Getting all page contents by user ID {}", userId);
		CommonResponse response = CommonResponse.builder()
				.status("success")
				.message("All page contents retrieved successfully")
				.data(pageContentService.getAllByUserId(userId))
				.build();

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CommonResponse<PageContentResponse>> updatePageContent(Integer id,
			@Valid PageContentRequest request) {
		log.info("Updating page content with ID {}", id);
		CommonResponse response = CommonResponse.builder()
				.status("success")
				.message("Page content updated successfully")
				.data(pageContentService.updatePageContent(id, request))
				.build();

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CommonResponse<PageContentResponse>> updatePageContentByPageId(
			@Valid PageContentRequest request) {
		log.info("Updating page content by page ID {}", request.getPageId());
		CommonResponse response = CommonResponse.builder()
				.status("success")
				.message("Page content updated successfully")
				.data(pageContentService.updateContentByPageId(request))
				.build();

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CommonResponse<PageContentResponse>> deletePageContent(Integer id) {
		log.info("Deleting page content with ID {}", id);
		pageContentService.deleteById(id);
		CommonResponse response = CommonResponse.builder()
				.status("success")
				.message("Page content deleted successfully")
				.build();

		return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<CommonResponse<PageContentResponse>> deletePageContentByPageId(Integer pageId) {
		log.info("Deleting page content by page ID {}", pageId);
		pageContentService.deleteByPageId(pageId);
		CommonResponse response = CommonResponse.builder()
				.status("success")
				.message("Page content deleted successfully")
				.build();

		return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
	}

	// @Override
    // public ResponseEntity<CommonResponse<Boolean>> checkContentPermission(Integer pageId, Authentication authentication) {\

    //     log.info("Checking content permission for page {}", pageId);
    //     boolean hasPermission = pageContentService.hasPermissionToEdit(pageId, pageId);
        
    //     CommonResponse<Boolean> response = CommonResponse.<Boolean>builder()
    //             .status("success")
    //             .message("Permission check completed")
    //             .data(hasPermission)
    //             .build();

    //     return new ResponseEntity<>(response, HttpStatus.OK);
    // }

    // @Override
    // public ResponseEntity<CommonResponse<List<PageContentResponse>>> getContentHistory(
    //         Integer pageId,
    //         Authentication authentication) {
        
    //     Integer userId = extractUserIdFromAuthentication(authentication);
    //     log.info("Getting content history for page {} by user {}", pageId, userId);
        
    //     // Check permission first
    //     if (!pageContentService.hasPermissionToEdit(pageId, userId)) {
    //         log.warn("User {} does not have permission to view content history for page {}", userId, pageId);
    //         CommonResponse<List<PageContentResponse>> response = CommonResponse.<List<PageContentResponse>>builder()
    //                 .status("error")
    //                 .message("Insufficient permissions")
    //                 .build();
    //         return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    //     }
        
    //     List<PageContentResponse> history = pageContentService.getContentHistory(pageId);
    //     CommonResponse<List<PageContentResponse>> response = CommonResponse.<List<PageContentResponse>>builder()
    //             .status("success")
    //             .message("Content history retrieved successfully")
    //             .data(history)
    //             .build();

    //     return new ResponseEntity<>(response, HttpStatus.OK);
    // }

    // @Override
    // public ResponseEntity<CommonResponse<PageContentResponse>> getContentVersion(
    //         Integer pageId,
    //         Integer version,
    //         Authentication authentication) {
        
    //     Integer userId = extractUserIdFromAuthentication(authentication);
    //     log.info("Getting content version {} for page {} by user {}", version, pageId, userId);
        
    //     // Check permission first
    //     if (!pageContentService.hasPermissionToEdit(pageId, userId)) {
    //         log.warn("User {} does not have permission to view content version for page {}", userId, pageId);
    //         CommonResponse<PageContentResponse> response = CommonResponse.<PageContentResponse>builder()
    //                 .status("error")
    //                 .message("Insufficient permissions")
    //                 .build();
    //         return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    //     }
        
    //     PageContentResponse content = pageContentService.getContentByVersion(pageId, version);
    //     CommonResponse<PageContentResponse> response = CommonResponse.<PageContentResponse>builder()
    //             .status("success")
    //             .message("Content version retrieved successfully")
    //             .data(content)
    //             .build();

    //     return new ResponseEntity<>(response, HttpStatus.OK);
    // }

    /**
     * Extract user ID from JWT authentication token
     */
    private Integer extractUserIdFromAuthentication(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AuthorizationException("User not authenticated", ErrorCode.UNAUTHENTICATED);
        }
        
        // Assuming you have a custom UserPrincipal class
        // Adjust this based on your JWT implementation
        Object principal = authentication.getPrincipal();
        
        if (principal instanceof User) {
            // If using custom UserPrincipal that implements UserDetails
            // return ((UserPrincipal) principal).getId();
            
            // For now, extract from username (adjust based on your implementation)
            String username = ((User) principal).getUsername();
            // You might need to lookup user ID from username
            // return userService.findByUsername(username).getId();
            
            // Temporary fallback - replace with actual implementation
            return 1; // Replace this with actual user ID extraction
        }
        
        throw new IllegalStateException("Invalid authentication principal type");
    }
}
