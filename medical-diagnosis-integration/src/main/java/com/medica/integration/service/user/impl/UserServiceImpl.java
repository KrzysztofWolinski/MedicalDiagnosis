package com.medica.integration.service.user.impl;

import javax.inject.Inject;

import com.medica.integration.controller.user.domain.UserDto;
import com.medica.integration.domain.user.Address;
import com.medica.integration.domain.user.User;
import com.medica.integration.repository.UserRepository;
import com.medica.integration.service.user.UserService;

public class UserServiceImpl implements UserService {

	@Inject
	UserRepository userRepository;
	
	@Override
	public User getUserProfile(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public void updateUserProfile(String username, UserDto user) {
		User updatedUser = userRepository.findByUsername(username);
		
		updatedUser.setName(user.getName());
		updatedUser.setSurname(user.getSurname());
		updatedUser.setPesel(user.getPesel());
		
		Address updatedAddress = updatedUser.getAddress();
		updatedAddress.setStreet(user.getAddress().getStreet());
		updatedAddress.setHouseNumber(user.getAddress().getHouseNumber());
		updatedAddress.setFlatNumber(user.getAddress().getFlatNumber());
		updatedAddress.setPostalCode(user.getAddress().getFlatNumber());
		updatedAddress.setCity(user.getAddress().getCity());
		
		updatedUser.setAddress(updatedAddress);
		
		userRepository.save(updatedUser);
	}

}
