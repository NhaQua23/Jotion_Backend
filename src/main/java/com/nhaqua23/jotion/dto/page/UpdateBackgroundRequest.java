package com.nhaqua23.jotion.dto.page;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateBackgroundRequest {

    String background;
    Integer workspaceId;
    Integer authorId;
}
