package com.medica.integration.domain.diagnosis;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.medica.integration.domain.EntityLongId;
import com.medica.integration.domain.user.User;

@Entity
@Table(name = "diagnosis_results")
public class DiagnosisResult extends EntityLongId {

	@NotNull
	private Boolean rated = false;
	
	@NotNull
	private Boolean recentlyAdded = true;
	
	@NotNull
	@ManyToOne
	private User patient;
	
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

	public boolean isRated() {
		return rated;
	}

	public void setRated(boolean rated) {
		this.rated = rated;
	}
	
	public Boolean getRecentlyAdded() {
		return recentlyAdded;
	}

	public void setRecentlyAdded(Boolean recentlyAdded) {
		this.recentlyAdded = recentlyAdded;
	}
}
