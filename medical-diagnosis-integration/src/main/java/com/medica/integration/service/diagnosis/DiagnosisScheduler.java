package com.medica.integration.service.diagnosis;

import java.util.List;

import javax.inject.Inject;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.medica.core.controller.DiagnosisCoreService;
import com.medica.core.domain.DiagnosisCoreData;
import com.medica.core.domain.communication.learn.LearnRequest;
import com.medica.core.domain.communication.learn.LearnResponse;
import com.medica.core.domain.rule.DiagnosisCoreRule;
import com.medica.integration.domain.diagnosis.DiagnosisData;
import com.medica.integration.domain.diagnosis.DiagnosisRule;
import com.medica.integration.repository.DiagnosisDataRepository;
import com.medica.integration.repository.DiagnosisRuleRepository;
import com.medica.integration.service.converters.DiagnosisDataConverter;
import com.medica.integration.service.converters.DiagnosisRuleConverter;

@Service
@Transactional
public class DiagnosisScheduler {

	@Inject
	private DiagnosisRuleRepository diagnosisRuleRepository;
	
	@Inject
	private DiagnosisDataRepository diagnosisDataRepository;
	
	@Inject
	private DiagnosisCoreService diagnosisCoreService;
	
	@Scheduled(fixedDelayString = "${diagnosis.learn.time_interval}")
	public void runLearnService() {
		//List<DiagnosisResult> results = diagnosisResultRepository.findByRatedTrue();
		List<DiagnosisData> ratedData = diagnosisDataRepository.findRatedData();
		
		// TODO results -> data, teraz w wynikach nie ma danych
		if (!ratedData.isEmpty()) {
			LearnRequest request = new LearnRequest();

			List<DiagnosisCoreData> convertedData = DiagnosisDataConverter.convertToDto(ratedData);
			request.setData(convertedData);
			
			LearnResponse response = diagnosisCoreService.learn(request);
			
			List<DiagnosisCoreRule> generatedRules = response.getRules();
			
			List<DiagnosisRule> convertedRules = DiagnosisRuleConverter.convertToDao(generatedRules);
			
			diagnosisRuleRepository.save(convertedRules);
		}
		
	}
	
}
