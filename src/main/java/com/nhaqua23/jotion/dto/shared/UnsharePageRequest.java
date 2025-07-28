package com.nhaqua23.jotion.dto.shared;

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
public class UnsharePageRequest {
    
    @NotNull(message = "Page ID is required")
    Integer pageId;

    @NotNull(message = "User email is required")
    @Email(message = "Invalid email format")
    String userEmail;

    @NotNull(message = "Requested by user ID is required")
    Integer requestedByUserId;
}
