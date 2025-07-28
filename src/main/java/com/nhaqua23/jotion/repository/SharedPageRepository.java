package com.nhaqua23.jotion.repository;

import com.nhaqua23.jotion.model.Page;
import com.nhaqua23.jotion.model.ShareStatus;
import com.nhaqua23.jotion.model.SharedPage;
import com.nhaqua23.jotion.model.User;
import com.nhaqua23.jotion.model.UserRole;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SharedPageRepository extends JpaRepository<SharedPage, Integer> {

	Optional<SharedPage> findByUserAndPage(User user, Page page);

	List<SharedPage> findByPageAndStatus(Page page, ShareStatus status);

	List<SharedPage> findByUserIdAndStatus(Integer userId, ShareStatus status);

	List<SharedPage> findByPageId(Integer pageId);

	List<SharedPage> findByUserId(Integer userId);

	boolean existsByUserAndPage(User user, Page page);

	boolean existsByPageId(Integer pageId);

	@Query("SELECT CASE WHEN COUNT(sp) > 0 THEN true ELSE false END FROM SharedPage sp WHERE sp.user = :user AND sp.page = :page AND sp.status = 'ACTIVE'")
	boolean existsActiveShareByUserAndPage(@Param("user") User user, @Param("page") Page page);

	@Query("SELECT sp FROM SharedPage sp WHERE sp.page.id = :pageId AND sp.status = :status")
	List<SharedPage> findActiveSharesByPageId(@Param("pageId") Integer pageId, @Param("status") ShareStatus status);

	@Query("SELECT sp FROM SharedPage sp WHERE sp.user.id = :userId AND sp.status = :status")
	List<SharedPage> findActiveSharesByUserId(@Param("userId") Integer userId, @Param("status") ShareStatus status);

	@Query("SELECT sp FROM SharedPage sp WHERE sp.page.id = :pageId AND sp.role = :role AND sp.status = :status")
	List<SharedPage> findByPageIdAndRoleAndStatus(@Param("pageId") Integer pageId, @Param("role") UserRole role, @Param("status") ShareStatus status);

	@Query("SELECT COUNT(sp) FROM SharedPage sp WHERE sp.page.id = :pageId AND sp.status = :status")
	long countActiveSharesByPageId(@Param("pageId") Integer pageId, @Param("status") ShareStatus status);

	@Query("SELECT sp FROM SharedPage sp WHERE sp.page.author.id = :pageId AND sp.status = :status")
	List<SharedPage> findByPageAuthorIdAndStatus(@Param("pageId") Integer pageId, @Param("status") ShareStatus status);

	void deleteByUserAndPage(User user, Page page);
}
