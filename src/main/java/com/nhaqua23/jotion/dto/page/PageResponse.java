package com.nhaqua23.jotion.dto.page;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageResponse {

	Integer id;
	String title;
	String background;
	LocalDate createdAt;
	LocalDate updatedAt;
	Integer workspaceId;
	Integer authorId;
}
