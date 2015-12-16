package com.medica.integration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.medica.integration.domain.diagnosis.DiagnosisResult;
import com.medica.integration.domain.user.User;

public interface DiagnosisResultRepository extends JpaRepository<DiagnosisResult, Long> {

	public List<DiagnosisResult> findByRatedTrue();
	
	@Query("SELECT COUNT(r) FROM DiagnosisResult r WHERE r.patient = :user AND r.recentlyAdded = true")
	public Integer countNewDiagnosisResults(@Param(value = "user") User user);
	
}
