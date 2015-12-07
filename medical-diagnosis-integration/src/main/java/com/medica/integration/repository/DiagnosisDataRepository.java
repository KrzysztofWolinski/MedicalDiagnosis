package com.medica.integration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.medica.integration.domain.diagnosis.DiagnosisData;
import com.medica.integration.domain.user.User;

public interface DiagnosisDataRepository extends JpaRepository<DiagnosisData, Long> {

	public List<DiagnosisData> getByPatient(@Param(value = "patient") User patient);
	
	public DiagnosisData findTop1ByPatientOrderByDateSubtmittedDesc(@Param(value = "patient") User patient);
	
}
