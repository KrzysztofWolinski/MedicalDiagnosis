package com.medica.integration.domain.diagnosis;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.medica.integration.domain.EntityLongId;
import com.medica.integration.domain.user.User;

@Entity(name = "diagnosis_results")
public class DiagnosisResult extends EntityLongId {

	@NotNull
	private Boolean rated = false;
	
	@NotNull
	@ManyToOne
	private User patient;

	@NotNull
	@ManyToOne
	private DiagnosisData diagnosedData;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<ConditionProbability> conditionsProbablity;
	
	public User getPatient() {
		return patient;
	}

	public void setPatient(User patient) {
		this.patient = patient;
	}

	public List<ConditionProbability> getConditionsProbablity() {
		return conditionsProbablity;
	}

	public void setConditionsProbablity(List<ConditionProbability> conditionsProbablity) {
		this.conditionsProbablity = conditionsProbablity;
	}

	public DiagnosisData getDiagnosedData() {
		return diagnosedData;
	}

	public void setDiagnosedData(DiagnosisData diagnosedData) {
		this.diagnosedData = diagnosedData;
	}

	public boolean isRated() {
		return rated;
	}

	public void setRated(boolean rated) {
		this.rated = rated;
	}
	
}
