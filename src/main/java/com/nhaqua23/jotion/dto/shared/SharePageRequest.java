package com.nhaqua23.jotion.dto.shared;

import com.nhaqua23.jotion.model.UserRole;

import jakarta.validation.constraints.Email;
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
public class SharePageRequest {
    
    @NotNull(message = "Page ID is required")
    Integer pageId;

    @NotNull(message = "User email is required")
    @Email(message = "Invalid email format")
    String userEmail;

    @NotNull(message = "Role is required")
    UserRole role;

    @NotNull(message = "Shared by user ID is required")
    Integer sharedByUserId;

    String message;
}
