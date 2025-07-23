package com.nhaqua23.jotion.repository;

import com.nhaqua23.jotion.model.PageContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PageContentRepository extends JpaRepository<PageContent, Integer> {

	List<PageContent> findBlockNotesByPageId(int pageId);

	PageContent findBlockNoteByPageId(int pageId);
}
