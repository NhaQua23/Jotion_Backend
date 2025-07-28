package com.nhaqua23.jotion.dto.shared;

import java.time.LocalDateTime;

import com.nhaqua23.jotion.model.ShareStatus;
import com.nhaqua23.jotion.model.SharedPage;
import com.nhaqua23.jotion.model.UserRole;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SharedPageResponse {

	Integer id;
	UserRole role;
	ShareStatus status;
	String userEmail;
	String userName;
	Integer userId;
	Integer pageId;
	String pageTitle;
	String sharedByEmail;
	String sharedByName;
	Integer sharedByUserId;
	LocalDateTime createdAt;
	LocalDateTime updatedAt;
	LocalDateTime sharedAt;

	// public static SharedPage toSharedPage(SharedPageResponse dto) {
	// 	final SharedPage sharedPage = new SharedPage();

	// 	sharedPage.setRole(dto.getRole());

	// 	return sharedPage;
	// }

	// public static SharedPageResponse toSharedPageDTO(SharedPage sharedPage) {
	// 	return SharedPageResponse.builder()
	// 			.id(sharedPage.getId())
	// 			.role(sharedPage.getRole())
	// 			.email(sharedPage.getUser().getEmail())
	// 			.pageId(sharedPage.getPage().getId())
	// 			.emailAuthor(sharedPage.getPage().getAuthor().getEmail())
	// 			.build();
	// }
}
