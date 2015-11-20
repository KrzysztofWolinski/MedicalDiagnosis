package com.medica.integration.domain.diagnosis;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.medica.integration.service.diagnosis.domain.FieldValueType;

@Entity(name = "data_pieces")
public class DataPiece {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	private String value;
	
	private FieldValueType type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public FieldValueType getType() {
		return type;
	}

	public void setType(FieldValueType type) {
		this.type = type;
	}

}
