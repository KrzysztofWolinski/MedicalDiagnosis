package com.medica.integration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.medica.integration.domain.diagnosis.DiagnosisData;
import com.medica.integration.domain.user.User;

public interface DiagnosisDataRepository extends JpaRepository<DiagnosisData, Long> {

	public List<DiagnosisData> getByPatient(@Param(value = "patient") User patient);
	
	public DiagnosisData findTop1ByPatientOrderByDateSubtmittedDesc(@Param(value = "patient") User patient);
	
	@Query(value = "SELECT * FROM diagnosis_data_sets d, diagnosis_results r WHERE d.diagnosisResult_id = r.id AND r.rated = true", nativeQuery = true)
	public List<DiagnosisData> findRatedData();
	
}
