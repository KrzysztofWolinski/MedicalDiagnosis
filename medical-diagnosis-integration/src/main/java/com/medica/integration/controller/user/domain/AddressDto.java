package com.medica.integration.controller.user.domain;

import com.medica.integration.domain.user.Address;

public class AddressDto {

	private String street;
	
	private String houseNumber;
	
	private String flatNumber;
	
	private String postalCode;
	
	private String city;

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getFlatNumber() {
		return flatNumber;
	}

	public void setFlatNumber(String flatNumber) {
		this.flatNumber = flatNumber;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public static AddressDto fromAddress(Address address) {
		AddressDto addressDto = new AddressDto();
		
		addressDto.setStreet(address.getStreet());
		addressDto.setHouseNumber(address.getHouseNumber());
		addressDto.setFlatNumber(address.getFlatNumber());
		addressDto.setPostalCode(address.getPostalCode());
		addressDto.setCity(address.getCity());
		
		return addressDto;
	}
	
}
