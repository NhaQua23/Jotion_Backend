package com.nhaqua23.jotion.service;

import java.util.List;

import com.nhaqua23.jotion.dto.shared.RevokePageAccessRequest;
import com.nhaqua23.jotion.dto.shared.SharePageRequest;
import com.nhaqua23.jotion.dto.shared.SharedPageResponse;
import com.nhaqua23.jotion.dto.shared.UnsharePageRequest;
import com.nhaqua23.jotion.dto.shared.UpdateUserRoleRequest;

public interface SharedPageService {

	SharedPageResponse sharePage(SharePageRequest request);

	SharedPageResponse unSharePage(UnsharePageRequest request);

	SharedPageResponse getById(Integer id);

	List<SharedPageResponse> getAllSharedPages();

	List<SharedPageResponse> getSharedPagesByUserId(Integer userId);

	List<SharedPageResponse> getPageCollaborators(Integer pageId);

	SharedPageResponse updateUserRole(UpdateUserRoleRequest request);
	
	SharedPageResponse revokePageAccess(RevokePageAccessRequest request);

	boolean hasPageAccess(Integer userId, Integer pageId);

	boolean canEditPage(Integer userId, Integer pageId);

	boolean canSharePage(Integer userId, Integer pageId);
}
