package com.nhaqua23.jotion.validator;

import com.nhaqua23.jotion.dto.PageDTO;

import java.util.ArrayList;
import java.util.List;

public class PageValidator {

	public static List<String> validatePage(PageDTO dto) {
		List<String> errors = new ArrayList<>();

		if (dto == null) {
			errors.add("Missing Title");
			return errors;
		}

		if (dto.getTitle() == null || dto.getTitle().isBlank()) {
			errors.add("Missing Title");
		}

		return errors;
	}
}
