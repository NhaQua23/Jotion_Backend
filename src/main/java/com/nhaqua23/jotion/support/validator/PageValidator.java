package com.nhaqua23.jotion.support.validator;

import com.nhaqua23.jotion.dto.page.PageResponse;

import java.util.ArrayList;
import java.util.List;

public class PageValidator {

	public static List<String> validatePage(PageResponse dto) {
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
