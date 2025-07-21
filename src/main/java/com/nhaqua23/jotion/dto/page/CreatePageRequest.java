package com.nhaqua23.jotion.dto.page;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreatePageRequest {

    String title;
    Integer workspaceId;
    Integer authorId;
}
