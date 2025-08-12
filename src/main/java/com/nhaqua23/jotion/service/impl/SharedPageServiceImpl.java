package com.nhaqua23.jotion.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhaqua23.jotion.dto.shared.RevokePageAccessRequest;
import com.nhaqua23.jotion.dto.shared.SharePageRequest;
import com.nhaqua23.jotion.dto.shared.SharedPageResponse;
import com.nhaqua23.jotion.dto.shared.UnsharePageRequest;
import com.nhaqua23.jotion.dto.shared.UpdateUserRoleRequest;
import com.nhaqua23.jotion.exception.AuthorizationException;
import com.nhaqua23.jotion.exception.EntityAlreadyExistsException;
import com.nhaqua23.jotion.exception.ErrorCode;
import com.nhaqua23.jotion.mapper.SharedPageMapper;
import com.nhaqua23.jotion.model.Page;
import com.nhaqua23.jotion.model.ShareStatus;
import com.nhaqua23.jotion.model.SharedPage;
import com.nhaqua23.jotion.model.User;
import com.nhaqua23.jotion.model.UserRole;
import com.nhaqua23.jotion.repository.SharedPageRepository;
import com.nhaqua23.jotion.service.SharedPageService;
import com.nhaqua23.jotion.support.fetcher.EntityFetcher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class SharedPageServiceImpl implements SharedPageService {

	private final SharedPageRepository sharedPageRepository;
	private final SharedPageMapper sharedPageMapper;
	private final EntityFetcher entityFetcher;

	@Override
	public SharedPageResponse sharePage(SharePageRequest request) {
		log.info("Sharing page {} with user {}", request.getPageId(), request.getUserEmail());

		// Validate and fetch entities
		Page page = entityFetcher.getPageById(request.getPageId());
		User targetUser = entityFetcher.getUserByEmail(request.getUserEmail());
		User sharedByUser = entityFetcher.getUserById(request.getSharedByUserId());

		// Check if the user requesting the share has permission
		if (!canSharePage(request.getSharedByUserId(), request.getPageId())) {
			throw new AuthorizationException("You don't have permission to share this page", ErrorCode.SHARE_PERMISSION_DENIED);
		}

		// Check if already actively shared with this user
		if (sharedPageRepository.existsActiveShareByUserAndPage(targetUser, page)) {
			throw new EntityAlreadyExistsException("Page is already shared with this user", ErrorCode.PAGE_ALREADY_SHARED);
		}

		// Check if there's an existing share record (could be revoked/expired)
		Optional<SharedPage> existingShare = sharedPageRepository.findByUserAndPage(targetUser, page);
		
		SharedPage sharedPage;
		if (existingShare.isPresent()) {
			// Reactivate existing share record
			sharedPage = existingShare.get();
			sharedPage.setRole(request.getRole());
			sharedPage.setSharedBy(sharedByUser);
			sharedPage.setStatus(ShareStatus.ACTIVE);
			sharedPage.setSharedAt(LocalDateTime.now());
			log.info("Reactivating existing share record for user {} on page {}", request.getUserEmail(), request.getPageId());
		} else {
			// Create owner record if it doesn't exist
			ensureOwnerExists(page);

			// Create new share record
			sharedPage = new SharedPage();
			sharedPage.setUser(targetUser);
			sharedPage.setPage(page);
			sharedPage.setRole(request.getRole());
			sharedPage.setSharedBy(sharedByUser);
			sharedPage.setStatus(ShareStatus.ACTIVE);
			log.info("Creating new share record for user {} on page {}", request.getUserEmail(), request.getPageId());
		}

		SharedPage savedShare = sharedPageRepository.save(sharedPage);
		log.info("Successfully shared page {} with user {}", request.getPageId(), request.getUserEmail());

		return sharedPageMapper.toSharedPageResponse(savedShare);
	}

	@Override
	public SharedPageResponse unSharePage(UnsharePageRequest request) {
		log.info("Unsharing page {} from user {}", request.getPageId(), request.getUserEmail());

		// Validate entities
		Page page = entityFetcher.getPageById(request.getPageId());
		User targetUser = entityFetcher.getUserByEmail(request.getUserEmail());
		User requestedByUser = entityFetcher.getUserById(request.getRequestedByUserId());

		// Find the shared page record
		SharedPage sharedPage = entityFetcher.getSharedPageByUserAndPage(targetUser, page);

		// Check permissions - only owner or the user themselves can unshare
		boolean isOwner = page.getAuthor().getId().equals(request.getRequestedByUserId());
		boolean isSelf = targetUser.getId().equals(request.getRequestedByUserId());
		boolean hasOwnerRole = hasOwnerRole(request.getRequestedByUserId(), request.getPageId());

		if (!isOwner && !isSelf && !hasOwnerRole) {
			throw new AuthorizationException("You don't have permission to unshare this page", ErrorCode.UNSHARE_PERMISSION_DENIED);
		}

		// Prevent owner from being unshared
		if (sharedPage.getRole() == UserRole.OWNER && !isSelf) {
			throw new AuthorizationException("Cannot remove owner access", ErrorCode.OWNER_CANNOT_BE_REMOVED);
		}

		// Mark as revoked instead of deleting for audit trail
		sharedPage.setStatus(ShareStatus.REVOKED);
		SharedPage updatedShare = sharedPageRepository.save(sharedPage);

		log.info("Successfully unshared page {} from user {}", request.getPageId(), request.getUserEmail());
		return sharedPageMapper.toSharedPageResponse(updatedShare);
	}

	@Override
	@Transactional(readOnly = true)
	public SharedPageResponse getById(Integer id) {
		return sharedPageMapper.toSharedPageResponse(entityFetcher.getSharedPageById(id));
	}

	@Override
	@Transactional(readOnly = true)
	public List<SharedPageResponse> getAllSharedPages() {
		return sharedPageRepository.findAll()
			.stream()
			.map(sharedPageMapper::toSharedPageResponse)
			.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public List<SharedPageResponse> getSharedPagesByUserId(Integer userId) {
		return sharedPageRepository.findActiveSharesByUserId(userId, ShareStatus.ACTIVE)
			.stream()
			.map(sharedPageMapper::toSharedPageResponse)
			.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public List<SharedPageResponse> getPageCollaborators(Integer pageId) {
		return sharedPageRepository.findActiveSharesByPageId(pageId, ShareStatus.ACTIVE)
			.stream()
			.map(sharedPageMapper::toSharedPageResponse)
			.collect(Collectors.toList());
	}

	@Override
	public SharedPageResponse updateUserRole(UpdateUserRoleRequest request) {
		Page page = entityFetcher.getPageById(request.getPageId());
		User user = entityFetcher.getUserById(request.getUserId());

		if (!canSharePage(request.getUpdatedBy(), request.getPageId())) {
			throw new AuthorizationException("You don't have permission to update role for this page", ErrorCode.ROLE_UPDATE_PERMISSION_DENIED);
		}

		SharedPage sharedPage = entityFetcher.getSharedPageByUserAndPage(user, page);

		UserRole role = request.getRole();
		sharedPage.setRole(role);

		SharedPage updatedShare = sharedPageRepository.save(sharedPage);
		return sharedPageMapper.toSharedPageResponse(updatedShare);
	}

	@Override
	public SharedPageResponse revokePageAccess(RevokePageAccessRequest request) {
		Page page = entityFetcher.getPageById(request.getPageId());
		User user = entityFetcher.getUserById(request.getUserId());

		if (!canSharePage(request.getRevokedBy(), request.getPageId())) {
			throw new AuthorizationException("You don't have permission to revoke access to this page", ErrorCode.UNSHARE_PERMISSION_DENIED);
		}

		SharedPage sharedPage = entityFetcher.getSharedPageByUserAndPage(user, page);

		// Mark as revoked instead of deleting for audit trail
		sharedPage.setStatus(ShareStatus.REVOKED);
		SharedPage updatedShare = sharedPageRepository.save(sharedPage);

		log.info("Successfully revoked access to page {} for user {}", request.getPageId(), request.getUserId());
		return sharedPageMapper.toSharedPageResponse(updatedShare);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean hasPageAccess(Integer userId, Integer pageId) {
		// Check if user is the page author
		Page page = entityFetcher.getPageById(pageId);
		if (page.getAuthor().getId().equals(userId)) {
			return true;
		}

		// Check if user has active share
		return sharedPageRepository.findActiveSharesByUserId(userId, ShareStatus.ACTIVE)
			.stream()
			.anyMatch(share -> share.getPage().getId().equals(pageId));
	}

	@Override
	@Transactional(readOnly = true)
	public boolean canEditPage(Integer userId, Integer pageId) {
		// Check if user is the page author
		Page page = entityFetcher.getPageById(pageId);
		if (page.getAuthor().getId().equals(userId)) {
			return true;
		}

		// Check if user has OWNER or COLLABORATOR role
		return sharedPageRepository.findActiveSharesByUserId(userId, ShareStatus.ACTIVE)
			.stream()
			.filter(share -> share.getPage().getId().equals(pageId))
			.anyMatch(share -> share.getRole().equals(UserRole.OWNER) || share.getRole().equals(UserRole.COLLABORATOR));
	}

	@Override
	@Transactional(readOnly = true)
	public boolean canSharePage(Integer userId, Integer pageId) {
		// Check if user is the page author
		Page page = entityFetcher.getPageById(pageId);

		if (page.getAuthor().getId().equals(userId)) {
			return true;
		}

		// Check if user has OWNER role in shared pages
		return hasOwnerRole(userId, pageId);
	}

	private void ensureOwnerExists(Page page) {
		// Check if owner record exists
		boolean ownerExists = sharedPageRepository.findByPageIdAndRoleAndStatus(
			page.getId(), UserRole.OWNER, ShareStatus.ACTIVE
		).stream().anyMatch(share -> share.getUser().getId().equals(page.getAuthor().getId()));

		if (!ownerExists) {
			SharedPage ownerShare = new SharedPage();
			ownerShare.setUser(page.getAuthor());
			ownerShare.setPage(page);
			ownerShare.setRole(UserRole.OWNER);
			ownerShare.setStatus(ShareStatus.ACTIVE);
			ownerShare.setSharedBy(page.getAuthor());
			sharedPageRepository.save(ownerShare);
		}
	}

	private boolean hasOwnerRole(Integer userId, Integer pageId) {
		return sharedPageRepository.findByPageIdAndRoleAndStatus(
			pageId, UserRole.OWNER, ShareStatus.ACTIVE
		).stream().anyMatch(share -> share.getUser().getId().equals(userId));
	}
}
