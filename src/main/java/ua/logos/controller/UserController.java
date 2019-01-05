package ua.logos.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.logos.domain.UserDTO;
import ua.logos.domain.request.SigninRequest;
import ua.logos.domain.response.SigninResponse;
import ua.logos.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {
	@Autowired
	private UserService userService;
	
	@PostMapping("signup")
	public ResponseEntity<?> createUser(@RequestBody UserDTO dto) {
		userService.save(dto);
		return new ResponseEntity<>(HttpStatus.CREATED);
	} 
	// $2a$10$NqAcc9BsZkskATCebLbCIuQAPeFSClfcqRwM5kwTQ0wwX4S0HVQTe
	// $2a$10$5uAbin4Ey6VEeytCu/4Lw.uxZFsXBjLrmmTP3MGYtXJ0QVhLnpCaa
	@PostMapping("signin")
	public ResponseEntity<?> loginUser(@RequestBody SigninRequest request) {
		String token = userService.signin(request.getUsername(), request.getPassword());
		String role = "";
		System.out.println(token + "\n" + request.getUsername() + "\n" + request.getPassword());
		if(token != null) {
			role = userService.findByUsername(request.getUsername()).getRole().toString();
			System.out.println("ROLE: " + role);
		}
		
		return new ResponseEntity<>(new SigninResponse(token, role), HttpStatus.OK);
	}
}
