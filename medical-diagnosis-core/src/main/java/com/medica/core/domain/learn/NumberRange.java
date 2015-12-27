package com.medica.core.domain.learn;

public class NumberRange extends Range {

	private float minValue;
	
	private float maxValue;

	public NumberRange() {
		super();
	}
	
	public float getMinValue() {
		return minValue;
	}

	public void setMinValue(float minValue) {
		this.minValue = minValue;
	}

	public float getMaxValue() {
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
