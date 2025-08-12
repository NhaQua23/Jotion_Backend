package com.nhaqua23.jotion.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "page_contents", indexes = {
		@Index(name = "idx_page_content_page_id", columnList = "page_id"),
		@Index(name = "idx_page_content_created_by_id", columnList = "created_by_id"),
		@Index(name = "idx_page_content_updated_by_id", columnList = "updated_by_id"),
		@Index(name = "idx_page_content_active", columnList = "is_active")
})
@Data
@EqualsAndHashCode(exclude = { "page", "createdBy", "updatedBy" })
@ToString(exclude = { "page", "createdBy", "updatedBy" })
public class PageContent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "content", columnDefinition = "TEXT")
	@Size(max = 1000000, message = "Content cannot exceed 1MB")
	private String content;

	@Column(name = "content_type", length = 50, nullable = false)
	@NotBlank(message = "Content type is required")
	@Size(max = 50, message = "Content type cannot exceed 50 characters")
	private String contentType = "application/json";

	@Column(name = "content_version", nullable = false)
	@NotNull(message = "Content version is required")
	private Integer contentVersion = 1;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "page_id", nullable = false,
			foreignKey = @ForeignKey(name = "fk_page_content_page"))
	@JsonBackReference
	@NotNull(message = "Page is required")
	private Page page;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by_id", nullable = false,
			foreignKey = @ForeignKey(name = "fk_page_content_created_by"))
	@JsonBackReference
	@NotNull(message = "Created by user is required")
	private User createdBy;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "updated_by_id", nullable = true,
			foreignKey = @ForeignKey(name = "fk_page_content_updated_by"))
	@JsonBackReference
	private User updatedBy;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;

	@Version
	@Column(name = "version")
	private Long version;

	@Column(name = "is_active", nullable = false)
	private Boolean isActive = true;

	// Hepler methods
	public boolean isEmpty() {
		return content == null || content.trim().isEmpty();
	}

	public int getContentLength() {
		return content != null ? content.length() : 0;
	}

	@PrePersist
	protected void onCreate() {
		createdAt = LocalDateTime.now();
		updatedAt = createdAt;
	}

	@PreUpdate
	protected void onUpdate() {
		updatedAt = LocalDateTime.now();
	}
}
