package com.nhaqua23.jotion.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.nhaqua23.jotion.dto.shared.SharePageRequest;
import com.nhaqua23.jotion.dto.shared.SharedPageResponse;
import com.nhaqua23.jotion.dto.shared.UnsharePageRequest;
import com.nhaqua23.jotion.model.SharedPage;

@Mapper(componentModel = "spring")
public interface SharedPageMapper {
    
    @Mapping(target = "userEmail", source = "user.email")
    @Mapping(target = "userName", source = "user.username")
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "pageId", source = "page.id")
    @Mapping(target = "pageTitle", source = "page.title")
    @Mapping(target = "sharedByEmail", source = "sharedBy.email", defaultValue = "")
    @Mapping(target = "sharedByName", source = "sharedBy.username", defaultValue = "")
    @Mapping(target = "sharedByUserId", source = "sharedBy.id")
    SharedPageResponse toSharedPageResponse(SharedPage sharedPage);
}
