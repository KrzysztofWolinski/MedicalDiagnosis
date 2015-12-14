package com.medica.integration.domain.diagnosis;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.medica.integration.domain.EntityLongId;
import com.medica.integration.domain.user.User;

@Entity(name = "diagnosis_data_sets")
public class DiagnosisData extends EntityLongId {
	
	@NotNull
	private Date dateSubtmitted;

	@NotNull
	@ManyToOne
	private User patient;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<DataPiece> data = new ArrayList<DataPiece>();

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private DiagnosisResult diagnosisResult;
	
	public DiagnosisData() {
		this.dateSubtmitted = new Date();
	}
	
	public Date getDateSubtmitted() {
		return dateSubtmitted;
	}

	public void setDateSubtmitted(Date dateSubtmitted) {
		this.dateSubtmitted = dateSubtmitted;
	}

	public User getPatient() {
		return patient;
	}

	public void setPatient(User patient) {
		this.patient = patient;
	}

	public List<DataPiece> getData() {
		return data;
	}

	public void setData(List<DataPiece> data) {
		this.data = data;
	}
	
	public void addDataPiece(DataPiece dataPiece) {
		this.data.add(dataPiece);
	}

	public DiagnosisResult getDiagnosisResult() {
		return diagnosisResult;
	}

	public void setDiagnosisResult(DiagnosisResult diagnosisResult) {
		this.diagnosisResult = diagnosisResult;
	}
	
}
