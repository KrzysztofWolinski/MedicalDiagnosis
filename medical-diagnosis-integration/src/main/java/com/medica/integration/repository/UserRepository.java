package com.medica.integration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medica.integration.domain.user.User;

public interface UserRepository extends JpaRepository<User, String> {

}
