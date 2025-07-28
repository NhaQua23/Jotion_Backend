package com.nhaqua23.jotion.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(
	name = "sharedPages",
	uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "page_id"})
)
@Data
@EqualsAndHashCode(exclude = {"user", "page"})
public class SharedPage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private UserRole role;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ShareStatus status = ShareStatus.ACTIVE;

	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@Column(name = "shared_at")
	private LocalDateTime sharedAt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "page_id", nullable = false)
	private Page page;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "shared_by_id")
	private User sharedBy;

	@PrePersist
	protected void onCreate() {
		createdAt = LocalDateTime.now();
		if (sharedAt == null) {
			sharedAt = createdAt;
		}
	}

	@PreUpdate
	protected void onUpdate() {
		updatedAt = LocalDateTime.now();
	}
}
