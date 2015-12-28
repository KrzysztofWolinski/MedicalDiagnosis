package com.medica.core.service.learn;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.medica.core.domain.CoreDataPiece;
import com.medica.core.domain.DiagnosisCoreData;
import com.medica.core.domain.DiagnosisCoreResult;
import com.medica.core.domain.rule.DiagnosisCoreRule;
import com.medica.core.service.learn.impl.DefaultLearnService;

public class DefaultLearnServiceTest {

	LearnService learnService = new DefaultLearnService();
	
	@Test
	public void shouldReturnEmptyRuleSetForEmptyInputDataList() {
		List<DiagnosisCoreRule> result = learnService.generateRules(getEmptyDataList());
		
		assertThat(result.isEmpty(), is(true));
	}
	
	@Test
	public void shouldReturnSomeRuleSetForStringOnlySampleInputDataList() {
		List<DiagnosisCoreRule> result = learnService.generateRules(getStringOnlySimpleDataList());
		
		assertThat(result.isEmpty(), is(false));
		assertThat(result.size(), is(1));
	}
	
	@Test
	@Ignore
	public void shouldReturnSingleRuleForNumberOnlySampleInputDataList() {
		List<DiagnosisCoreRule> result = learnService.generateRules(getNumbersOnlySimpleDataList());
		
		assertThat(result.isEmpty(), is(false));
		assertThat(result.size(), is(1));
	}
	
//	@Test
//	public void shouldReturnSomeRuleSetForSimpleSampleInputDataList() {
//		List<DiagnosisCoreRule> result = learnService.generateRules(getSampleDataList());
//		
//		assertThat(result.isEmpty(), is(false));
//	}
	
	private List<DiagnosisCoreData> getEmptyDataList() {
		List<DiagnosisCoreData> emptyDataList = new ArrayList<DiagnosisCoreData>();
		
		return emptyDataList;
	}
	
	private List<DiagnosisCoreData> getStringOnlySimpleDataList() {
		List<DiagnosisCoreData> sampleDataList = new ArrayList<DiagnosisCoreData>();
		
		sampleDataList.add(prepareTestData1("T", "FLU"));
		sampleDataList.add(prepareTestData1("T", "FLU"));
		sampleDataList.add(prepareTestData1("T", "FLU"));
		sampleDataList.add(prepareTestData1("N", "FLU"));
		sampleDataList.add(prepareTestData1("N", ""));
		sampleDataList.add(prepareTestData1("N", ""));
		sampleDataList.add(prepareTestData1("N", ""));
		
		return sampleDataList;
	}
	
	private List<DiagnosisCoreData> getNumbersOnlySimpleDataList() {
		List<DiagnosisCoreData> sampleDataList = new ArrayList<DiagnosisCoreData>();
		
		sampleDataList.add(prepareTestData1("1", "FLU"));
		sampleDataList.add(prepareTestData1("1", "FLU"));
		sampleDataList.add(prepareTestData1("1", "FLU"));
		sampleDataList.add(prepareTestData1("1", ""));
		sampleDataList.add(prepareTestData1("2", ""));
		sampleDataList.add(prepareTestData1("2", ""));
		sampleDataList.add(prepareTestData1("2", ""));
		
		return sampleDataList;
	}
	
	private List<DiagnosisCoreData> getSampleDataList() {
		List<DiagnosisCoreData> sampleDataList = new ArrayList<DiagnosisCoreData>();
		
		sampleDataList.add(prepareTestData3("10", "T", "1", "FLU"));
		sampleDataList.add(prepareTestData3("11", "T", "2", "FLU"));
		sampleDataList.add(prepareTestData3("1", "N", "5", ""));
		
		return sampleDataList;
	}
	
	private DiagnosisCoreData prepareTestData1(String v, String condition) {
		DiagnosisCoreData data = new DiagnosisCoreData();
		
		CoreDataPiece piece1 = new CoreDataPiece();
		piece1.setName("A");
		piece1.setValue(v);
		data.addDataPiece(piece1);
		
		DiagnosisCoreResult result = new DiagnosisCoreResult();
		result.setRated(true);
		
		if (condition != "") {	
			result.addConditionProbability(condition, 100);
		}

		data.setDiagnosisResult(result);
		
		return data;
	}
	
	private DiagnosisCoreData prepareTestData3(String v1, String v2, String v3, String condition) {
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
