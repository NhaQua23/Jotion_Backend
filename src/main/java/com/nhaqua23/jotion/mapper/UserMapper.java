package com.nhaqua23.jotion.mapper;

import com.nhaqua23.jotion.dto.user.CreateUserRequest;
import com.nhaqua23.jotion.dto.user.UserResponse;
import com.nhaqua23.jotion.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toUserResponse(User user);

    User toUser(CreateUserRequest request);
}