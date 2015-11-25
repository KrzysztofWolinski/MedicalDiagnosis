package com.medica.integration.service.diagnosis.domain;

import java.util.ArrayList;
import java.util.List;

public class ChoiceFieldValue extends FieldValue {

	private int selectedValueIndex = 0;
	
	private List<Object> possibleValues = new ArrayList<Object>(); 
	
	public void setSelectedValueIndex(int index) {
		this.selectedValueIndex = index;
	}
	
	@Override
	public Object getValue() {
		if ((possibleValues != null) && (selectedValueIndex < possibleValues.size()) && (selectedValueIndex > -1)) {
			return possibleValues.get(selectedValueIndex);
		} else {
			return null;
		}
	}
	
	public void setValue(Object value) {
		selectedValueIndex = possibleValues.indexOf(value);
	}

	public List<Object> getPossibleValues() {
		return possibleValues;
	}

	public void setPossibleValues(List<Object> possibleValues) {
		this.possibleValues = possibleValues;
	}
	
	public void addPossibleValue(Object value) {
		this.possibleValues.add(value);
	}
	
}
