package com.nhaqua23.jotion.repository;

import com.nhaqua23.jotion.model.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PageRepository extends JpaRepository<Page, Integer> {

	List<Page> findPageByWorkspaceId(int workspaceId);

	List<Page> findPageByAuthorId(int authorId);

//	List<Page> findPagesByTagsId(int tagsId);
}
