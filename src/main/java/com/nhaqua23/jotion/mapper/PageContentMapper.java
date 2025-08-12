package com.nhaqua23.jotion.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.nhaqua23.jotion.dto.content.PageContentRequest;
import com.nhaqua23.jotion.dto.content.PageContentResponse;
import com.nhaqua23.jotion.model.PageContent;

@Mapper(componentModel = "spring")
public interface PageContentMapper {
    
    @Mapping(source = "page.id", target = "pageId")
    @Mapping(source = "page.title", target = "pageTitle")
    @Mapping(source = "createdBy.id", target = "createdById")
    @Mapping(source = "createdBy.username", target = "createdByUsername")
    @Mapping(source = "updatedBy.id", target = "updatedById")
    @Mapping(source = "updatedBy.username", target = "updatedByUsername")
    PageContentResponse toPageContentResponse(PageContent pageContent);
    
    PageContent toPageContent(PageContentRequest request);
}
