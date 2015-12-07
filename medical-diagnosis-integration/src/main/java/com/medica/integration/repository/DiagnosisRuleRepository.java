package com.medica.integration.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medica.integration.domain.diagnosis.DiagnosisRule;

public interface DiagnosisRuleRepository extends JpaRepository<DiagnosisRule, Long> {

}
