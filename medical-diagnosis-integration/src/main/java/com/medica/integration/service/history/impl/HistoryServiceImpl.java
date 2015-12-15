package com.medica.integration.service.history.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.transaction.annotation.Transactional;

import com.medica.integration.controller.history.domain.HistoryGetDataDetailsResponse;
import com.medica.integration.domain.diagnosis.ConditionProbability;
import com.medica.integration.domain.diagnosis.DataPiece;
import com.medica.integration.domain.diagnosis.DiagnosisData;
import com.medica.integration.domain.user.User;
import com.medica.integration.repository.DiagnosisDataRepository;
import com.medica.integration.repository.UserRepository;
import com.medica.integration.service.history.HistoryService;
import com.medica.integration.service.history.domain.HistoryByDateDataBlock;
import com.medica.integration.service.history.domain.HistoryByDateDataPiece;
import com.medica.integration.service.history.domain.HistoryByNameDataBlock;
import com.medica.integration.service.history.domain.HistoryByNameDataPiece;
import com.medica.integration.service.history.domain.HistoryConditionProbability;
import com.medica.integration.service.history.domain.HistoryDataDetails;
import com.medica.integration.service.history.domain.HistoryDiagnosesDataBlock;

@Transactional
public class HistoryServiceImpl implements HistoryService {

	@Inject
	DiagnosisDataRepository diagnosisDataRepository; 
	
	@Inject
	UserRepository userRepository;
	
	@Override
	public List<HistoryByDateDataBlock> getDataByDate(String username) {
		User patient = this.userRepository.findByUsername(username);
		List<DiagnosisData> rawData = this.diagnosisDataRepository.getByPatient(patient);
		
		List<HistoryByDateDataBlock> data = convertDataToDataByDate(rawData); 
		
		return data;
	}

	@Override
	public List<HistoryByNameDataBlock> getDataByName(String username) {
		User patient = this.userRepository.findByUsername(username);
		List<DiagnosisData> rawData = this.diagnosisDataRepository.getByPatient(patient);
		
		List<HistoryByNameDataBlock> data = convertDataToDataByName(rawData); 
		
		return data;
	}

	@Override
	public List<HistoryDiagnosesDataBlock> getDiagnosesData(String username) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private List<HistoryByNameDataBlock> convertDataToDataByName(List<DiagnosisData> rawDataList) {
		List<HistoryByNameDataBlock> convertedData = new ArrayList<HistoryByNameDataBlock>();
		
		List<DataPiece> dataPieceList = rawDataList.stream().map(d -> d.getData()).flatMap(l -> l.stream()).collect(Collectors.toList());
		List<String> nameList = dataPieceList.stream().map(d -> d.getName()).distinct().collect(Collectors.toList());
		
		for (String name : nameList) {
			HistoryByNameDataBlock dataBlock = new HistoryByNameDataBlock(name);
			
			for (DiagnosisData diagnosisData : rawDataList) {
				Date currentDate = diagnosisData.getDateSubtmitted();
				
				List<HistoryByNameDataPiece> currentDataPieceList = diagnosisData.getData().stream()
					.filter(p -> p.getName().equals(name))
					.map(p -> {
						return new HistoryByNameDataPiece(currentDate, p.getValue(), p.getType());
					})
					.collect(Collectors.toList());
				
				dataBlock.getDataPieces().addAll(currentDataPieceList);
			}
			
			convertedData.add(dataBlock);
		}
		
		return convertedData;
		
	}
	
	private List<HistoryByDateDataBlock> convertDataToDataByDate(List<DiagnosisData> rawData) {
		List<HistoryByDateDataBlock> convertedData = new ArrayList<HistoryByDateDataBlock>();
		
		for (DiagnosisData data : rawData) {
			convertedData.add(convertDataToDataByDate(data));
		}
		
		return convertedData;
	}
	
	private HistoryByDateDataBlock convertDataToDataByDate(DiagnosisData data) {
		HistoryByDateDataBlock dataBlock = new HistoryByDateDataBlock();
		
		List<HistoryByDateDataPiece> dataPieces = data.getData().stream().map(d -> {
			return new HistoryByDateDataPiece(d.getName(),d.getValue(), d.getType());
		}).collect(Collectors.toList());

		dataBlock.setDataId(data.getId());
		dataBlock.setRated(data.getDiagnosisResult().isRated());
		dataBlock.setDate(data.getDateSubtmitted());
		dataBlock.setData(dataPieces);
		
		return dataBlock;
	}

	@Override
	public HistoryGetDataDetailsResponse getDataDetails(String username, Long id) {
		DiagnosisData retrivedData = diagnosisDataRepository.findById(id);
		
		// TODO check username
		if (retrivedData != null) {
			HistoryGetDataDetailsResponse response = new HistoryGetDataDetailsResponse();
			HistoryDataDetails dataDetails = new HistoryDataDetails();
			
			HistoryByDateDataBlock convertedData = convertDataToDataByDate(retrivedData);
			List<HistoryConditionProbability> conditionProbabilityList = new ArrayList<>();
			
			if (retrivedData.getDiagnosisResult() != null) {
				List<ConditionProbability> retrivedConditionProbabilityList = retrivedData.getDiagnosisResult().getConditionsProbablity();
				
				for (ConditionProbability probability : retrivedConditionProbabilityList) {
					HistoryConditionProbability historyConditionProbability = new HistoryConditionProbability();
					historyConditionProbability.setDiseaseName(probability.getDiseaseName());
					historyConditionProbability.setProbability(probability.getProbability());
					
					conditionProbabilityList.add(historyConditionProbability);
				}
			}
			
			dataDetails.setConditionProbability(conditionProbabilityList);
			dataDetails.setDataBlock(convertedData);
			
			response.setData(dataDetails);
			
			return response;
			
		} else {
			return null;
		}
	}

	@Override
	public void reviewDiagnosisDataResults(String username, Long id, List<HistoryConditionProbability> newConditionProbabilities) {
		DiagnosisData retrivedData = diagnosisDataRepository.getOne(id);
		
		// TODO check username
		// TODO check if data is valid
		if ((retrivedData != null) && (!newConditionProbabilities.isEmpty())) {
			List<ConditionProbability> conditionProbabilityList = new ArrayList<ConditionProbability>();
			
			for (HistoryConditionProbability probability : newConditionProbabilities) {
				ConditionProbability conditionProbability = new ConditionProbability();
				conditionProbability.setDiseaseName(probability.getDiseaseName());
				conditionProbability.setProbability(100);
				
				conditionProbabilityList.add(conditionProbability);
			}

			retrivedData.getDiagnosisResult().setConditionsProbablity(conditionProbabilityList);
			retrivedData.getDiagnosisResult().setRated(true);
			
			this.diagnosisDataRepository.saveAndFlush(retrivedData);
		}
		
	}

}
