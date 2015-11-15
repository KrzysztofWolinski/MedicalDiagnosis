package com.medica.integration.controller.user.domain;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.medica.integration.domain.user.Address;
import com.medica.integration.domain.user.User;

public class UserDtoTest {

	@Test
	public void shouldConvertUserToUserDto() {
		User user = getUser();
		
		UserDto userDto = UserDto.fromUser(user);
		
		assertThat(userDto.getPesel(), equalTo(user.getPesel()));
		assertThat(userDto.getName(), equalTo(user.getName()));
		assertThat(userDto.getSurname(), equalTo(user.getSurname()));
		assertThat(userDto.getAddress().getStreet(), equalTo(user.getAddress().getStreet()));
		assertThat(userDto.getAddress().getHouseNumber(), equalTo(user.getAddress().getHouseNumber()));
		assertThat(userDto.getAddress().getFlatNumber(), equalTo(user.getAddress().getFlatNumber()));
		assertThat(userDto.getAddress().getPostalCode(), equalTo(user.getAddress().getPostalCode()));
		assertThat(userDto.getAddress().getCity(), equalTo(user.getAddress().getCity()));
	}

	private User getUser() {
		User user = new User();
		Address address = new Address();
		
		address.setStreet("ul. Kwiatowa");
		address.setHouseNumber("10");
		address.setFlatNumber("1");
		address.setPostalCode("10-123");
		address.setCity("Gdańsk");
		
		user.setPesel("12345");
		user.setName("Jan");
		user.setSurname("Kowalski");
		user.setAddress(address);
		
		return user;
	}
	
}
