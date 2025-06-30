package com.nhaqua23.jotion.service;

import com.nhaqua23.jotion.dto.PageDTO;
import com.nhaqua23.jotion.dto.SharedPageDTO;

import java.util.List;

public interface SharedPageService {

	SharedPageDTO sharePage(SharedPageDTO dto);

	SharedPageDTO unSharePage(SharedPageDTO dto);

	List<SharedPageDTO> getAll();

	SharedPageDTO getById(Integer id);

	List<SharedPageDTO> getAllByUserId(Integer userId);

	List<PageDTO> getAllPagesByUserId(Integer userId);
}
