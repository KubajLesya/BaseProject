package ua.logos.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table
public class UserEntity extends BaseEntity {
	private Long id;

	private String username;
	
	private String password;
	
	private String firstName;
	
	private String lastName;
	
	private UserRole role;
}
