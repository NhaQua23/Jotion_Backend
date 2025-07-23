package com.nhaqua23.jotion.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "staticNotes")
@Data
public class NoteTemplate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(columnDefinition = "TEXT")
	private String content;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "page_id")
	private Page page;
}
