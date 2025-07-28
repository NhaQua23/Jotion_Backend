package com.nhaqua23.jotion.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.nhaqua23.jotion.controller.api.SharedPageAPI;
import com.nhaqua23.jotion.dto.response.CommonResponse;
import com.nhaqua23.jotion.dto.shared.RevokePageAccessRequest;
import com.nhaqua23.jotion.dto.shared.SharePageRequest;
import com.nhaqua23.jotion.dto.shared.SharedPageResponse;
import com.nhaqua23.jotion.dto.shared.UnsharePageRequest;
import com.nhaqua23.jotion.dto.shared.UpdateUserRoleRequest;
import com.nhaqua23.jotion.service.SharedPageService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SharedPageController implements SharedPageAPI {

    private final SharedPageService sharedPageService;

    @Override
    public ResponseEntity<CommonResponse<SharedPageResponse>> sharePage(@Valid SharePageRequest request) {
        log.info("Sharing page {} with user {}", request.getPageId(), request.getUserEmail());
		CommonResponse response = CommonResponse.builder()
			.status("success")
			.message("Page shared successfully")
			.data(sharedPageService.sharePage(request))
			.build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CommonResponse<SharedPageResponse>> unSharePage(@Valid UnsharePageRequest request) {
        log.info("Unsharing page {} from user {}", request.getPageId(), request.getUserEmail());
		CommonResponse response = CommonResponse.builder()
			.status("success")
			.message("Page unshared successfully")
			.data(sharedPageService.unSharePage(request))
			.build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CommonResponse<SharedPageResponse>> getAllSharedPages() {
		CommonResponse response = CommonResponse.builder()
			.status("success")
			.message("Shared pages retrieved successfully")
			.data(sharedPageService.getAllSharedPages())
			.build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CommonResponse<SharedPageResponse>> getSharedPageById(Integer id) {
		CommonResponse response = CommonResponse.builder()
			.status("success")
			.message("Shared page retrieved successfully")
			.data(sharedPageService.getById(id))
			.build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CommonResponse<SharedPageResponse>> getSharedPagesByUserId(Integer userId) {
		CommonResponse response = CommonResponse.builder()
			.status("success")
			.message("Shared pages retrieved successfully")
			.data(sharedPageService.getSharedPagesByUserId(userId))
			.build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CommonResponse<SharedPageResponse>> getPageCollaborators(Integer pageId) {
		CommonResponse response = CommonResponse.builder()
			.status("success")
			.message("Collaborators retrieved successfully")
			.data(sharedPageService.getPageCollaborators(pageId))
			.build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CommonResponse<Boolean>> checkPageAccess(Integer pageId, Integer userId) {
		CommonResponse response = CommonResponse.builder()
			.status("success")
			.message("Access checked successfully")
			.data(sharedPageService.hasPageAccess(userId, pageId))
			.build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CommonResponse<SharedPageResponse>> updateUserRole(UpdateUserRoleRequest request) {
        log.info("Updating role for user {} on page {} to {}", request.getUserId(), request.getPageId(), request.getRole());
		CommonResponse response = CommonResponse.builder()
			.status("success")
			.message("Role updated successfully")
			.data(sharedPageService.updateUserRole(request))
			.build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CommonResponse<SharedPageResponse>> revokePageAccess(RevokePageAccessRequest request) {
        log.info("Revoking access for user {} on page {}", request.getUserId(), request.getPageId());
		CommonResponse response = CommonResponse.builder()
			.status("success")
			.message("Access revoked successfully")
			.data(sharedPageService.revokePageAccess(request))
			.build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
