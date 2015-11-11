package com.medica.integration.controller.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String getUserProfile() {  	
		// TODO mock		
        return "user:getUserProfile";
    }
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
    public String updateUserProfile() {  	
		// TODO mock		
        return "user:updateUserProfile";
    }
	
}
