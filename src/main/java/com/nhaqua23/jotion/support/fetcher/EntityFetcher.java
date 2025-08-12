package com.nhaqua23.jotion.support.fetcher;

import org.springframework.stereotype.Component;

import com.nhaqua23.jotion.exception.EntityNotFoundException;
import com.nhaqua23.jotion.exception.ErrorCode;
import com.nhaqua23.jotion.model.Page;
import com.nhaqua23.jotion.model.PageContent;
import com.nhaqua23.jotion.model.SharedPage;
import com.nhaqua23.jotion.model.User;
import com.nhaqua23.jotion.model.Workspace;
import com.nhaqua23.jotion.repository.PageContentRepository;
import com.nhaqua23.jotion.repository.PageRepository;
import com.nhaqua23.jotion.repository.SharedPageRepository;
import com.nhaqua23.jotion.repository.UserRepository;
import com.nhaqua23.jotion.repository.WorkspaceRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EntityFetcher {

    private final UserRepository userRepository;
    private final WorkspaceRepository workspaceRepository;
    private final PageRepository pageRepository;
    private final SharedPageRepository sharedPageRepository;
    private final PageContentRepository pageContentRepository;

    // User
    public User getUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "User not found with ID = " + id,
                        ErrorCode.USER_NOT_FOUND));
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(
                        "User not found with email = " + email,
                        ErrorCode.USER_NOT_FOUND));
    }

    // Workspace
    public Workspace getWorkspaceById(Integer id) {
        return workspaceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Workspace not found with ID = " + id,
                        ErrorCode.WORKSPACE_NOT_FOUND));
    }

    // Page
    public Page getPageById(Integer id) {
        return pageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Page not found with ID = " + id,
                        ErrorCode.PAGE_NOT_FOUND));
    }

    // SharedPage
    public SharedPage getSharedPageById(Integer id) {
        return sharedPageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Shared page not found with ID = " + id,
                        ErrorCode.SHARED_PAGE_NOT_FOUND));
    }

    public SharedPage getSharedPageByUserAndPage(User user, Page page) {
        return sharedPageRepository.findByUserAndPage(user, page)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Shared page not found with user = " + user.getId() + " and page = " + page.getId(),
                        ErrorCode.SHARED_PAGE_NOT_FOUND));
    }

    // PageContent
    public PageContent getPageContentById(Integer id) {
        return pageContentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Page content not found with ID = " + id,
                        ErrorCode.CONTENT_NOT_FOUND));
    }
}
