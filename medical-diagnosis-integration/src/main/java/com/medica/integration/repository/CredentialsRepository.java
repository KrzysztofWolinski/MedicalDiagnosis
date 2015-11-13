package com.medica.integration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medica.integration.domain.user.Credentials;

public interface CredentialsRepository extends JpaRepository<Credentials, String> {
	
	public Credentials findByUsername(String username);
	
}
