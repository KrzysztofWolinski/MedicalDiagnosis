package com.medica.integration.domain.diagnosis;

import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.medica.integration.domain.EntityLongId;

@Entity(name = "condition_probabilities")
public class ConditionProbability extends EntityLongId {

	@NotNull
	String diseaseName;
	
	@Min(0)
	@Max(100)
	@NotNull
	Integer probability;

	public String getDiseaseName() {
		return diseaseName;
	}

	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}

	public Integer getProbability() {
		return probability;
	}

	public void setProbability(Integer probability) {
		this.probability = probability;
	}
	
}
