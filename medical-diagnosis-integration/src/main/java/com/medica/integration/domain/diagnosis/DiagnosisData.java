package com.medica.integration.domain.diagnosis;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.medica.integration.domain.user.User;

@Entity(name = "diagnosis_data_sets")
public class DiagnosisData {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private Date dateSubtmitted;
	
	@OneToOne
	private User patient;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<DataPiece> data = new ArrayList<DataPiece>();

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
	
}
