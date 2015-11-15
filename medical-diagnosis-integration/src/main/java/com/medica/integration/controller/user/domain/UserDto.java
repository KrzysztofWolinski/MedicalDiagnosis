package com.medica.integration.controller.user.domain;

import com.medica.integration.domain.user.User;

public class UserDto {
	
	private String pesel;
	
	private String name; 
	
	private String surname;
		
	private AddressDto address;
	
	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public AddressDto getAddress() {
		return address;
	}

	public void setAddress(AddressDto address) {
		this.address = address;
	}
	
	public static UserDto fromUser(User user) {
		UserDto userDto = new UserDto();
		
		userDto.setPesel(user.getPesel());
		userDto.setName(user.getName());
		userDto.setSurname(user.getSurname());
		userDto.setAddress(AddressDto.fromAddress(user.getAddress()));
		
		return userDto;
	}

}
