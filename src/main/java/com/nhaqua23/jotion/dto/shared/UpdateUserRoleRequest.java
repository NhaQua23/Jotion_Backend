package com.nhaqua23.jotion.dto.shared;

import com.nhaqua23.jotion.model.UserRole;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateUserRoleRequest {

    @NotNull(message = "Page ID is required")
    Integer pageId;

    @NotNull(message = "User ID is required")
    Integer userId;

    @NotNull(message = "Role is required")
    UserRole role;

    @NotNull(message = "Updated by user ID is required")
    Integer updatedBy;
}
