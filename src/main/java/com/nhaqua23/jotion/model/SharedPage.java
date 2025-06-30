package com.nhaqua23.jotion.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "sharedPages")
@Data
public class SharedPage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private UserRole role;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "page_id")
	private Page page;

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "author_id")
//	private User author;
}
