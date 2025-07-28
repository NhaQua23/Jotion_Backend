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

	List<SharedPageResponse> getAllSharedPages();

	SharedPageResponse getById(Integer id);

	List<SharedPageResponse> getSharedPagesByUserId(Integer userId);

	List<SharedPageResponse> getPageCollaborators(Integer pageId);

	boolean hasPageAccess(Integer userId, Integer pageId);

	boolean canEditPage(Integer userId, Integer pageId);

	boolean canSharePage(Integer userId, Integer pageId);

	SharedPageResponse revokePageAccess(RevokePageAccessRequest request);

	SharedPageResponse updateUserRole(UpdateUserRoleRequest request);
}
