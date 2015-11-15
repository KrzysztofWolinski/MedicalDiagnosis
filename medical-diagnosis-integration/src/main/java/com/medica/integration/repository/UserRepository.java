package com.medica.integration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.medica.integration.domain.user.User;

public interface UserRepository extends JpaRepository<User, String> {

	@Query(value = "SELECT * FROM user u WHERE u.credentials_username = :username", nativeQuery = true)
	public User findByUsername(@Param("username") String username);
	
}
