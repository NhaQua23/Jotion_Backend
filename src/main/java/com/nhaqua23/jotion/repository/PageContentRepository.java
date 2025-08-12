package com.nhaqua23.jotion.repository;

import com.nhaqua23.jotion.model.PageContent;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PageContentRepository extends JpaRepository<PageContent, Integer> {

	// Find by page
	List<PageContent> findByPageId(Integer pageId);

	Optional<PageContent> findByPageIdAndIsActiveTrue(Integer pageId);

	Optional<PageContent> findByPageIdOrderByUpdatedAtDesc(Integer pageId);

	// Find by user
	List<PageContent> findByCreatedById(Integer userId);

	List<PageContent> findByUpdatedById(Integer userId);

	// Find by page and user
	List<PageContent> findByPageIdAndCreatedById(Integer pageId, Integer userId);

	List<PageContent> findByPageIdAndUpdatedById(Integer pageId, Integer userId);

	// Find active content
	List<PageContent> findByIsActiveTrueOrderByUpdatedAtDesc();

	// Find by content type
	List<PageContent> findByContentType(String contentType);

	// Find by date range
	List<PageContent> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);

	List<PageContent> findByUpdatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);

	// Custom queries
	@Query("SELECT pc FROM PageContent pc " +
			"WHERE pc.page.id = :pageId AND pc.isActive = true")
	Optional<PageContent> findActiveContentByPageId(
			@Param("pageId") Integer pageId);

	@Query("SELECT pc FROM PageContent pc " +
			"WHERE pc.page.id IN :pageIds AND pc.isActive = true")
	List<PageContent> findActiveContentByPageIds(
			@Param("pageIds") List<Integer> pageIds);

	@Query("SELECT COUNT(pc) FROM PageContent pc " +
			"WHERE pc.createdBy.id = :userId AND pc.createdAt >= :date")
	Long countByUserSinceDate(
			@Param("userId") Integer userId,
			@Param("date") LocalDateTime date);

	@Query("SELECT pc FROM PageContent pc " +
			"WHERE pc.page.workspace.id = :workspaceId AND pc.isActive = true")
	List<PageContent> findActiveContentByWorkspaceId(
			@Param("workspaceId") Integer workspaceId);

	// Check existence
	boolean existsByPageIdAndIsActiveTrue(Integer pageId);

	boolean existsByPageIdAndCreatedById(Integer pageId, Integer userId);

	// Search methods
	@Query("SELECT pc FROM PageContent pc " +
			"WHERE pc.page.workspace.id = :workspaceId " +
			"AND pc.isActive = true " +
			"AND (LOWER(pc.content) LIKE LOWER(CONCAT('%', :query, '%')) " +
			"OR LOWER(pc.page.title) LIKE LOWER(CONCAT('%', :query, '%'))) " +
			"ORDER BY pc.updatedAt DESC ")
	List<PageContent> searchContentInWorkspace(
			@Param("workspaceId") Integer workspaceId,
			@Param("query") String query);

	// Analytics methods
	@Query("SELECT COUNT(pc) FROM PageContent pc " +
			"WHERE pc.createdBy.id = :userId ")
	Long countContentByUser(@Param("userId") Integer userId);

	@Query("SELECT COUNT(pc) FROM PageContent pc " +
			"WHERE pc.updatedBy.id = :userId ")
	Long countUpdatedContentByUser(@Param("userId") Integer userId);

	@Query("SELECT pc FROM PageContent pc " +
			"WHERE pc.createdBy.id = :userId " +
			"OR pc.updatedBy.id = :userId " +
			"ORDER BY pc.updatedAt DESC ")
	List<PageContent> findRecentActivityByUser(
			@Param("userId") Integer userId,
			Pageable pageable);

	// Version-specific methods (for future enhancement)
	@Query("SELECT pc FROM PageContent pc " +
			"WHERE pc.page.id = :pageId " +
			"AND pc.contentVersion = :version")
	Optional<PageContent> findByPageIdAndVersion(
			@Param("pageId") Integer pageId,
			@Param("version") Integer version);

	@Query("SELECT pc FROM PageContent pc " +
			"WHERE pc.page.id = :pageId " +
			"ORDER BY pc.contentVersion DESC")
	List<PageContent> findContentHistoryByPageId(
			@Param("pageId") Integer pageId);
}
