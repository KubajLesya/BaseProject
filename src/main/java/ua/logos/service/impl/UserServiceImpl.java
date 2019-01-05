package ua.logos.service.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ua.logos.config.jwt.JWTTokenProvider;
import ua.logos.domain.LocationDTO;
import ua.logos.domain.UserDTO;
import ua.logos.entity.Location;
import ua.logos.entity.UserEntity;
import ua.logos.entity.UserRole;
import ua.logos.exception.AlreadyExsistsException;
import ua.logos.repository.UserRepository;
import ua.logos.service.UserService;
import ua.logos.service.utils.ObjectMapperUtils;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ObjectMapperUtils modelMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JWTTokenProvider jwtTokenProvider;
	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public void save(UserDTO dto) {
		if(userRepository.existsByUsername(dto.getUsername())) {
			throw new AlreadyExsistsException("User with username[" + dto.getUsername() + "] already exsists");
		} else {
			dto.setRole(UserRole.ROLE_USER);
			dto.setPassword(passwordEncoder.encode(dto.getPassword()));
			
			UserEntity entity = modelMapper.map(dto, UserEntity.class);
			userRepository.save(entity);
			}
	}

	@Override
	public List<UserDTO> findAllUserrs() {
		List<UserEntity> user = userRepository.findAll();
		List<UserDTO> userDTOs = modelMapper.mapAll(user, UserDTO.class);
		return userDTOs;
	}

	@Override
	public boolean existsByUsername(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UserDTO findByUsername(String username) {
		return modelMapper.map(userRepository.findByUsername(username), UserDTO.class);
		
	}

	@Override
	public String signin(String username, String password) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRole());
	}

}
