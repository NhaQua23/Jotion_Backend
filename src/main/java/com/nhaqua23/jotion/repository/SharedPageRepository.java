package com.nhaqua23.jotion.repository;

import com.nhaqua23.jotion.model.Page;
import com.nhaqua23.jotion.model.SharedPage;
import com.nhaqua23.jotion.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SharedPageRepository extends JpaRepository<SharedPage, Integer> {

	Optional<SharedPage> findByUserAndPage(User user, Page page);

	Optional<SharedPage> findByPageId(Integer pageId);

	List<SharedPage> findAllByUserId(Integer userId);

	Boolean existsByPageId(Integer pageId);

	Boolean existsByUserAndPage(User user, Page page);

	List<SharedPage> findAllByPageId(Integer pageId);
}
