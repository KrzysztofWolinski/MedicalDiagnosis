package com.medica.core.service.learn;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.medica.core.domain.CoreDataPiece;
import com.medica.core.domain.DiagnosisCoreData;
import com.medica.core.domain.DiagnosisCoreResult;
import com.medica.core.domain.rule.DiagnosisCoreRule;
import com.medica.core.service.learn.impl.DefaultLearnService;

public class DefaultLearnServiceTest {

	LearnService learnService = new DefaultLearnService();
	
//	@Test
//	public void shouldReturnEmptyRuleSetForEmptyInputDataList() {
//		List<DiagnosisCoreRule> result = learnService.generateRules(getEmptyDataList());
//		
//		assertThat(result.isEmpty(), is(true));
//	}
	
	@Test
	public void shouldReturnSomeRuleSetForSimpleSampleInputDataList() {
		List<DiagnosisCoreRule> result = learnService.generateRules(getSampleDataList());
		
		assertThat(result.isEmpty(), is(false));
	}
	
	private List<DiagnosisCoreData> getEmptyDataList() {
		List<DiagnosisCoreData> emptyDataList = new ArrayList<DiagnosisCoreData>();
		
		return emptyDataList;
	}
	
	private List<DiagnosisCoreData> getSampleDataList() {
		List<DiagnosisCoreData> sampleDataList = new ArrayList<DiagnosisCoreData>();
		
		sampleDataList.add(prepareTestData("10", "T", "1", "FLU"));
		sampleDataList.add(prepareTestData("11", "T", "2", "FLU"));
		sampleDataList.add(prepareTestData("1", "N", "5", ""));
		
		return sampleDataList;
	}
	
	private DiagnosisCoreData prepareTestData(String v1, String v2, String v3, String condition) {
		DiagnosisCoreData data = new DiagnosisCoreData();
		
		CoreDataPiece piece1 = new CoreDataPiece();
		piece1.setName("A");
		piece1.setValue(v1);
		data.addDataPiece(piece1);
		
		CoreDataPiece piece2 = new CoreDataPiece();
		piece2.setName("B");
		piece2.setValue(v2);
		data.addDataPiece(piece2);
		
		CoreDataPiece piece3 = new CoreDataPiece();
		piece3.setName("C");
		piece3.setValue(v3);
		data.addDataPiece(piece3);
		
		
		DiagnosisCoreResult result = new DiagnosisCoreResult();
		result.setRated(true);
		
		if (condition != "") {	
			result.addConditionProbability(condition, 100);
		}

		data.setDiagnosisResult(result);
		
		return data;
	}
}
