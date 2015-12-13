package com.medica.integration.domain.diagnosis;

import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.medica.integration.domain.EntityLongId;

@Entity(name = "diagnosis_rules")
public class DiagnosisRule extends EntityLongId {

	@NotNull
	private String decision;
	
	@NotNull
	private String expression;
	
	@Min(0)
	@Max(100)
	@NotNull
	private Integer probability;

	public String getDecision() {
		return decision;
	}

	public void setDecision(String decision) {
		this.decision = decision;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}
	
	public Integer getProbability() {
		return probability;
	}

	public void setProbability(Integer probability) {
		this.probability = probability;
	}
	
}
