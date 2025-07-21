package com.nhaqua23.jotion.controller;

import com.nhaqua23.jotion.controller.api.SharedPageAPI;
import com.nhaqua23.jotion.dto.page.PageResponse;
import com.nhaqua23.jotion.dto.response.SharedPageResponse;
import com.nhaqua23.jotion.service.SharedPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SharedPageController implements SharedPageAPI {

	@Autowired
	private SharedPageService sharedPageService;

	@Override
	public ResponseEntity<SharedPageResponse> sharePage(SharedPageResponse dto) {
		return new ResponseEntity<>(sharedPageService.sharePage(dto), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<SharedPageResponse> unSharePage(SharedPageResponse dto) {
		return new ResponseEntity<>(sharedPageService.unSharePage(dto), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<SharedPageResponse>> getAllSharedPages() {
		return new ResponseEntity<>(sharedPageService.getAll(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<SharedPageResponse> getPageById(Integer id) {
		return new ResponseEntity<>(sharedPageService.getById(id), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<PageResponse>> getAllSharedPagesByUserId(Integer userId) {
		return new ResponseEntity<>(sharedPageService.getAllPagesByUserId(userId), HttpStatus.OK);
	}
}
