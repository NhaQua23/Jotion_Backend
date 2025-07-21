package com.nhaqua23.jotion.service.impl;

import com.nhaqua23.jotion.dto.workspace.CreateWorkspaceRequest;
import com.nhaqua23.jotion.dto.workspace.UpdateWorkspaceRequest;
import com.nhaqua23.jotion.dto.workspace.WorkspaceResponse;
import com.nhaqua23.jotion.exception.EntityNotFoundException;
import com.nhaqua23.jotion.exception.ErrorCode;
import com.nhaqua23.jotion.exception.InvalidEntityException;
import com.nhaqua23.jotion.mapper.WorkspaceMapper;
import com.nhaqua23.jotion.model.User;
import com.nhaqua23.jotion.model.Workspace;
import com.nhaqua23.jotion.repository.UserRepository;
import com.nhaqua23.jotion.repository.WorkspaceRepository;
import com.nhaqua23.jotion.service.WorkspaceService;
import com.nhaqua23.jotion.validator.WorkspaceValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class WorkspaceServiceImpl implements WorkspaceService {

	private final WorkspaceRepository workspaceRepository;
	private final UserRepository userRepository;
	private final WorkspaceMapper workspaceMapper;

	@Override
	public WorkspaceResponse save(CreateWorkspaceRequest request) {
//		List<String> errors = WorkspaceValidator.validateWorkspace(request);
//		if (!errors.isEmpty()) {
//			log.error(errors.toString());
//			throw new InvalidEntityException(
//					"Workspace is not valid",
//					ErrorCode.WORKSPACE_NOT_VALID,
//					errors
//			);
//		}

		User user = userRepository.findById(request.getUserId())
				.orElseThrow(() -> new EntityNotFoundException(
						"User not found with ID = " + request.getUserId(),
						ErrorCode.USER_NOT_FOUND
				));

		Workspace workspace = workspaceMapper.toWorkspace(request);
		workspace.setUser(user);
		workspace.setCreatedAt(LocalDate.now());
		workspace.setUpdatedAt(LocalDate.now());
		workspace.setEditable(true);

		return workspaceMapper.toWorkspaceResponse(workspaceRepository.save(workspace));
	}

	@Override
	public WorkspaceResponse update(Integer id, UpdateWorkspaceRequest request) {
//		List<String> errors = WorkspaceValidator.validateWorkspace(request);
//		if (!errors.isEmpty()) {
//			log.error(errors.toString());
//			throw new InvalidEntityException(
//					"Workspace is not valid",
//					ErrorCode.WORKSPACE_NOT_VALID,
//					errors
//			);
//		}

		Workspace workspace = workspaceRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(
						"Workspace not found with ID = " + id,
						ErrorCode.WORKSPACE_NOT_FOUND
				));

//		if (!workspace.isEditable()) {
//			throw new InvalidEntityException(
//					"Workspace is not valid",
//					ErrorCode.WORKSPACE_NOT_VALID,
//					errors
//			);
//		}

		workspace.setName(request.getName());
		workspace.setUpdatedAt(LocalDate.now());

		return workspaceMapper.toWorkspaceResponse(workspaceRepository.save(workspace));
	}

	@Override
	public List<WorkspaceResponse> getAll() {
		return workspaceRepository.findAll().stream()
				.map(workspaceMapper::toWorkspaceResponse).collect(Collectors.toList());
	}

	@Override
	public WorkspaceResponse getById(Integer id) {
		return workspaceRepository.findById(id)
				.map(workspaceMapper::toWorkspaceResponse)
				.orElseThrow(() -> new EntityNotFoundException(
						"Workspace not found with ID = " + id,
						ErrorCode.WORKSPACE_NOT_FOUND
				));
	}

	@Override
	public List<WorkspaceResponse> getAllByUserId(Integer userId) {
		return workspaceRepository.findWorkspaceByUserId(userId).stream()
				.map(workspaceMapper::toWorkspaceResponse).collect(Collectors.toList());
	}

	@Override
	public void delete(Integer id) {
		Workspace workspace = workspaceRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(
						"Workspace not found with ID = " + id,
						ErrorCode.WORKSPACE_NOT_FOUND
				));

		workspaceRepository.delete(workspace);
	}
}
