package com.nhaqua23.jotion.repository;

import com.nhaqua23.jotion.model.NoteTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteTemplateRepository extends JpaRepository<NoteTemplate, Integer> {

	Boolean existsByPageId(Integer pageId);

	NoteTemplate findByPageId(Integer pageId);
}
