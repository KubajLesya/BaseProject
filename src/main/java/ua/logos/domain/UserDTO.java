package ua.logos.domain;

import lombok.Data;
import ua.logos.entity.UserRole;

@Data
public class UserDTO {
	private Long id;

	private String username;
	
	private String password;
	
	private String firstName;
	
	private String lastName;
	
	private UserRole role;
}
