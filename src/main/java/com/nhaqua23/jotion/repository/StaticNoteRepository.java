package com.nhaqua23.jotion.repository;

import com.nhaqua23.jotion.model.StaticNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaticNoteRepository extends JpaRepository<StaticNote, Integer> {

	Boolean existsByPageId(Integer pageId);

	StaticNote findByPageId(Integer pageId);
}
