package com.medica.integration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medica.integration.domain.diagnosis.DiagnosisData;

public interface DiagnosisDataRepository extends JpaRepository<DiagnosisData, Long> {

}
