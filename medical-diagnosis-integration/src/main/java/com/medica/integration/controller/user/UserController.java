package com.medica.integration.controller.user;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.medica.integration.controller.user.domain.UserDto;
import com.medica.integration.controller.user.domain.UserRequestDto;
import com.medica.integration.domain.user.User;
import com.medica.integration.service.auth.AuthService;
import com.medica.integration.service.user.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Inject
	private AuthService authService;
	
	@Inject
	private UserService userService;
	
	@RequestMapping(value = "/get", method = RequestMethod.POST)
	@ResponseBody
    public UserDto getUserProfile(@RequestBody UserRequestDto request) {  	
		if (authService.isAuthorized(request.getUsername(), request.getToken())) {
			User user = userService.getUserProfile(request.getUsername());
			return UserDto.fromUser(user);
		} else {
			// TODO  unauthorized
			return null;
		}
    }
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
    public void updateUserProfile(@RequestBody UserRequestDto request) {  	
		if (authService.isAuthorized(request.getUsername(), request.getToken())) {
			userService.updateUserProfile(request.getUsername(), request.getUser());
		} else {
			// TODO unauthorized
		}
        
    }
	
}
