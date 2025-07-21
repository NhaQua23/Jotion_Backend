package com.nhaqua23.jotion.mapper;

import com.nhaqua23.jotion.dto.page.CreatePageRequest;
import com.nhaqua23.jotion.dto.page.PageResponse;
import com.nhaqua23.jotion.model.Page;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PageMapper {

    @Mapping(source = "workspace.id", target = "workspaceId")
    @Mapping(source = "author.id", target = "authorId")
    PageResponse toPageResponse(Page page);

    Page toPage(CreatePageRequest request);
}
