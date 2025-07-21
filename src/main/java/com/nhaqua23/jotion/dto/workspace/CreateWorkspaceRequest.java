package com.nhaqua23.jotion.dto.workspace;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateWorkspaceRequest {

    String name;
    Integer userId;
}
