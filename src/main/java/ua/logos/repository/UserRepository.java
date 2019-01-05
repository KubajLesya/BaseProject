package ua.logos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.logos.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	boolean existsByUsername(String username);
	
	UserEntity findByUsername(String username);
}
