package com.medica.integration.controller.diagnosis.domain.form;

import java.util.List;

public class ChoiceFieldValue extends FieldValue {

	private int selectedValueIndex = 0;
	
	private List<String> possibleValues; 
	
	public void setSelectedValueIndex(int index) {
		this.selectedValueIndex = index;
	}
	
	@Override
	public Object getValue() {
		if ((possibleValues != null) && (selectedValueIndex < possibleValues.size())) {
			return possibleValues.get(selectedValueIndex);
		} else {
			return null;
		}
	}

	public List<String> getPossibleValues() {
		return possibleValues;
	}

	public void setPossibleValues(List<String> possibleValues) {
		this.possibleValues = possibleValues;
	}
	
}
