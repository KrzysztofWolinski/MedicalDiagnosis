package com.medica.core.domain.learn;

public class NumberRange extends Range {

	private Float minValue;
	
	private Float maxValue;

	public NumberRange() {
		super();
	}
	
	public Float getMinValue() {
		return minValue;
	}

	public void setMinValue(Float minValue) {
		this.minValue = minValue;
	}

	public Float getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(float maxValue) {
		this.maxValue = maxValue;
	}

	public boolean isInRange(float value) {
		if (minValue <= value && value <= maxValue) {
			return true;
		} else {
			return false;
		}
	}
}
