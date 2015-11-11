package com.medica.integration.domain.user;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Credentials {

	@Id
	Long id;
	
	private String username;
	
	private String password;
	
}
