package com.nhaqua23.jotion.mapper;

import com.nhaqua23.jotion.dto.workspace.CreateWorkspaceRequest;
import com.nhaqua23.jotion.dto.workspace.WorkspaceResponse;
import com.nhaqua23.jotion.model.Workspace;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WorkspaceMapper {

    @Mapping(source = "user.id", target = "userId")
    WorkspaceResponse toWorkspaceResponse(Workspace workspace);

    Workspace toWorkspace(CreateWorkspaceRequest request);
}