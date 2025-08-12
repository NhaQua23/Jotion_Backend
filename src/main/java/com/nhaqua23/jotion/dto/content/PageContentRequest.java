package com.nhaqua23.jotion.dto.content;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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
public class PageContentRequest {
    
    @Size(max = 1000000, message = "Content cannot exceed 1MB")
    String content;

    @NotBlank(message = "Content type is required")
    @Size(max = 50, message = "Content type cannot exceed 50 characters")
    @Builder.Default
    String contentType = "application/json";

    @NotNull(message = "Page ID is required")
    @Positive(message = "Page ID must be positive")
    Integer pageId;

    @NotNull(message = "User ID is required")
    @Positive(message = "User ID must be positive")
    Integer userId;

    @Positive(message = "Content version must be positive")
    @Builder.Default
    Integer contentVersion = 1;

    @Positive(message = "Version must be positive")
    @Builder.Default
    Long version = 1L;

    // Helper methods
    public boolean hasContent() {
        return content != null && !content.trim().isEmpty();
    }

    public boolean isJsonContent() {
        return "application/json".equalsIgnoreCase(contentType);
    }

    public void incrementVersion() {
        this.contentVersion = this.contentVersion != null ? this.contentVersion + 1 : this.contentVersion;
    }
}
