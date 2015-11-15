package com.medica.integration.service.user;

import com.medica.integration.controller.user.domain.UserDto;
import com.medica.integration.domain.user.User;

public interface UserService {

	public User getUserProfile(String username);
	
	public void updateUserProfile(String username, UserDto user);
}
