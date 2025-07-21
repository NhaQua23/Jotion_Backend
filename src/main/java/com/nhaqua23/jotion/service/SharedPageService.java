package com.nhaqua23.jotion.service;

import com.nhaqua23.jotion.dto.page.PageResponse;
import com.nhaqua23.jotion.dto.response.SharedPageResponse;

import java.util.List;

public interface SharedPageService {

	SharedPageResponse sharePage(SharedPageResponse dto);

	SharedPageResponse unSharePage(SharedPageResponse dto);

	List<SharedPageResponse> getAll();

	SharedPageResponse getById(Integer id);

	List<SharedPageResponse> getAllByUserId(Integer userId);

	List<PageResponse> getAllPagesByUserId(Integer userId);
}
