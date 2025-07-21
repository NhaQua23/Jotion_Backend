package com.nhaqua23.jotion.controller;

import com.nhaqua23.jotion.controller.api.PageAPI;
import com.nhaqua23.jotion.dto.page.CreatePageRequest;
import com.nhaqua23.jotion.dto.page.PageResponse;
import com.nhaqua23.jotion.dto.page.UpdateBackgroundRequest;
import com.nhaqua23.jotion.dto.page.UpdateTitleRequest;
import com.nhaqua23.jotion.dto.response.CommonResponse;
import com.nhaqua23.jotion.service.PageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PageController implements PageAPI {

	private final PageService pageService;

	@Override
	public ResponseEntity<CommonResponse<PageResponse>> createPage(CreatePageRequest request) {
		CommonResponse response = CommonResponse.builder()
				.status("success")
				.message("Page created successfully")
				.data(pageService.save(request))
				.build();

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<CommonResponse<PageResponse>> updatePage(Integer id, UpdateTitleRequest request) {
		CommonResponse response = CommonResponse.builder()
				.status("success")
				.message("Page updated successfully")
				.data(pageService.update(id, request))
				.build();

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CommonResponse<PageResponse>> updateBackground(Integer id, UpdateBackgroundRequest request) {
		CommonResponse response = CommonResponse.builder()
				.status("success")
				.message("Background updated successfully")
				.data(pageService.updateBackground(id, request))
				.build();

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CommonResponse<PageResponse>> getAllPages() {
		CommonResponse response = CommonResponse.builder()
				.status("success")
				.data(pageService.getAll())
				.build();

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CommonResponse<PageResponse>> getPageById(Integer id) {
		CommonResponse response = CommonResponse.builder()
				.status("success")
				.data(pageService.getById(id))
				.build();

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CommonResponse<PageResponse>> getAllPagesByAuthorId(Integer authorId) {
		CommonResponse response = CommonResponse.builder()
				.status("success")
				.data(pageService.getAllByAuthorId(authorId))
				.build();

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CommonResponse<PageResponse>> getAllPagesByWorkspaceId(Integer workspaceId) {
		CommonResponse response = CommonResponse.builder()
				.status("success")
				.data(pageService.getAllByWorkspaceId(workspaceId))
				.build();

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

//	@Override
//	public ResponseEntity<List<PageDTO>> getAllPagesByTagId(Integer tagId) {
//		return new ResponseEntity<>(pageService.getAllByTagId(tagId), HttpStatus.OK);
//	}

	@Override
	public ResponseEntity<CommonResponse<PageResponse>> deletePageById(Integer id) {
		pageService.delete(id);
		CommonResponse response = CommonResponse.builder()
				.status("success")
				.message("Page deleted successfully")
				.build();

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
