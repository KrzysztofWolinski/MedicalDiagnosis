package com.medica.integration.domain.diagnosis;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import com.medica.integration.domain.EntityLongId;
import com.medica.integration.service.diagnosis.domain.FieldValueType;

@Entity(name = "data_pieces")
public class DataPiece extends EntityLongId {

	@NotNull
	private String name;
	
	@NotNull
	private String value;
	
	@NotNull
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
