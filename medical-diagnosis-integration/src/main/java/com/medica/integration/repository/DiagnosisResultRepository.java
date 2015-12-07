package com.medica.integration.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medica.integration.domain.diagnosis.DiagnosisResult;

public interface DiagnosisResultRepository extends JpaRepository<DiagnosisResult, Long> {

	public List<DiagnosisResult> findByRatedTrue();
	
}
