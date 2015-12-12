package com.medica.integration.service.diagnosis;

import java.util.List;

import javax.inject.Inject;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.medica.core.controller.DiagnosisCoreService;
import com.medica.core.domain.DiagnosisCoreResult;
import com.medica.core.domain.communication.learn.LearnRequest;
import com.medica.core.domain.communication.learn.LearnResponse;
import com.medica.core.domain.rule.DiagnosisCoreRule;
import com.medica.integration.domain.diagnosis.DiagnosisResult;
import com.medica.integration.domain.diagnosis.DiagnosisRule;
import com.medica.integration.repository.DiagnosisResultRepository;
import com.medica.integration.repository.DiagnosisRuleRepository;
import com.medica.integration.service.converters.DiagnosisResultConverter;
import com.medica.integration.service.converters.DiagnosisRuleConverter;

@Service
@Transactional
public class DiagnosisScheduler {

	@Inject
	private DiagnosisResultRepository diagnosisResultRepository;
	
	@Inject
	private DiagnosisRuleRepository diagnosisRuleRepository;
	
	@Inject
	private DiagnosisCoreService diagnosisCoreService;
	
	@Scheduled(fixedDelayString = "${diagnosis.learn.time_interval}")
	public void runLearnService() {
		List<DiagnosisResult> results = diagnosisResultRepository.findByRatedTrue();
		
		if (!results.isEmpty()) {
			LearnRequest request = new LearnRequest();

			List<DiagnosisCoreResult> convertedResults = DiagnosisResultConverter.convertToDto(results);
			
			request.setResults(convertedResults);
			
			LearnResponse response = diagnosisCoreService.learn(request);
			
			List<DiagnosisCoreRule> generatedRules = response.getRules();
			
			List<DiagnosisRule> convertedRules = DiagnosisRuleConverter.convertToDao(generatedRules);
			
			diagnosisRuleRepository.save(convertedRules);
		}
		
	}
	
}
