package com.nhaqua23.jotion.dto.workspace;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WorkspaceResponse {

	Integer id;
	String name;
	LocalDate createdAt;
	LocalDate updatedAt;
	boolean editable;
	Integer userId;
}
