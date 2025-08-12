package com.nhaqua23.jotion.dto.content;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nhaqua23.jotion.model.PageContent;

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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PageContentResponse {

	Integer id;
	String content;
	String contentType;
	Integer contentVersion;

	// Page information
	Integer pageId;
	String pageTitle;

	// User information
	Integer createdById;
	String createdByUsername;
	Integer updatedById;
	String updatedByUsername;

	// Timestamps
	LocalDateTime createdAt;
	LocalDateTime updatedAt;

	// Metadata
	Long version;
	Boolean isActive;
	Integer contentLength;

	// Helper methods
	public boolean isEmpty() {
		return content == null || content.trim().isEmpty();
	}

	public boolean isJsonContent() {
		return "application/json".equalsIgnoreCase(contentType);
	}

	public boolean isActive() {
		return Boolean.TRUE.equals(isActive);
	}

	public boolean hasBeenUpdated() {
		return updatedById != null && !updatedById.equals(createdById);
	}
}
