package ua.logos.service;

import java.util.List;

import ua.logos.domain.UserDTO;

public interface UserService {
	void save(UserDTO dto);
	
	List<UserDTO> findAllUserrs();
	
	boolean existsByUsername(String username);
	UserDTO findByUsername(String username);
	
	String signin(String username, String password);
}
