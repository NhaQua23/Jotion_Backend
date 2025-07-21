package com.nhaqua23.jotion.dto.user;

import com.nhaqua23.jotion.model.UserRole;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {

	Integer id;
	String username;
	String password;
	String email;
	UserRole role;
}
