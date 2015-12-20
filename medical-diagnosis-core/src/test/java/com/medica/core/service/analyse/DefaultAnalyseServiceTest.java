package com.medica.core.service.analyse;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Test;

import com.medica.core.domain.CoreDataPiece;
import com.medica.core.domain.DiagnosisCoreData;
import com.medica.core.domain.DiagnosisCoreResult;
import com.medica.core.domain.communication.analyse.AnalyseResponseStatus;
import com.medica.core.service.analyse.impl.DefaultAnalyseService;

public class DefaultAnalyseServiceTest {

	private static final String PIECE_NAME_1 = "A";
	private static final String PIECE_NAME_2 = "B";
	private static final String PIECE_NAME_3 = "C";
	
	DefaultAnalyseService analyseService = new DefaultAnalyseService();
	
	@Test
	public void shouldReturnOkForSimilarData() {
		List<DiagnosisCoreData> similarDataList = new ArrayList<DiagnosisCoreData>();
		
		similarDataList.add(get3PieceCoreData("1", "1", "1", getDate(2015, 12, 19), true, true));
		similarDataList.add(get3PieceCoreData("3", "3", "3", getDate(2015, 12, 20), true, true));
		similarDataList.add(get3PieceCoreData("2", "2", "2", getDate(2015, 12, 21), true, true));
		similarDataList.add(get3PieceCoreData("2", "3", "1", getDate(2015, 12, 22), true, true));
		
		AnalyseResponseStatus response = analyseService.analyseData(similarDataList);
		
		assertThat(response, equalTo(AnalyseResponseStatus.OK));
	}
	
	@Test
	public void shouldReturnWarningForDifferentData() {
		List<DiagnosisCoreData> differenetDataList = new ArrayList<DiagnosisCoreData>();
		
		differenetDataList.add(get3PieceCoreData("1", "1", "1", getDate(2015, 12, 19), true, true));
		differenetDataList.add(get3PieceCoreData("3", "3", "3", getDate(2015, 12, 20), true, true));
		differenetDataList.add(get3PieceCoreData("2", "2", "2", getDate(2015, 12, 21), true, true));
		differenetDataList.add(get3PieceCoreData("0", "6", "4", getDate(2015, 12, 22), true, true));
		
		AnalyseResponseStatus response = analyseService.analyseData(differenetDataList);
		
		assertThat(response, equalTo(AnalyseResponseStatus.WARNING));
	}
	
	@Test
	public void shouldReturnWarningForDataNotSimilarToHealthyData() {
		List<DiagnosisCoreData> differenetDataList = new ArrayList<DiagnosisCoreData>();
		
		differenetDataList.add(get3PieceCoreData("1", "1", "1", getDate(2015, 12, 19), true, true));
		differenetDataList.add(get3PieceCoreData("3", "3", "3", getDate(2015, 12, 20), true, true));
		differenetDataList.add(get3PieceCoreData("2", "2", "2", getDate(2015, 12, 21), true, true));
		differenetDataList.add(get3PieceCoreData("1", "6", "2", getDate(2015, 12, 22), false, true));
		differenetDataList.add(get3PieceCoreData("2", "4", "3", getDate(2015, 12, 23), false, true));
		differenetDataList.add(get3PieceCoreData("2", "5", "2", getDate(2015, 12, 24), true, true));
		
		AnalyseResponseStatus response = analyseService.analyseData(differenetDataList);
		
		assertThat(response, equalTo(AnalyseResponseStatus.WARNING));
	}
	
	private DiagnosisCoreData get3PieceCoreData(String v1, String v2, String v3, Date date, boolean healthy, boolean rated) {
		DiagnosisCoreData coreData = new DiagnosisCoreData();
		
		coreData.setDateSubmitted(date);
		
		CoreDataPiece dataPiece1 = new CoreDataPiece();
		CoreDataPiece dataPiece2 = new CoreDataPiece();
		CoreDataPiece dataPiece3 = new CoreDataPiece();
		
		dataPiece1.setName(PIECE_NAME_1);
		dataPiece1.setValue(v1);
		dataPiece2.setName(PIECE_NAME_2);
		dataPiece2.setValue(v2);
		dataPiece3.setName(PIECE_NAME_3);
		dataPiece3.setValue(v3);		
		
		coreData.addDataPiece(dataPiece1);
		coreData.addDataPiece(dataPiece2);
		coreData.addDataPiece(dataPiece3);
		
		DiagnosisCoreResult diagnosisResult = new DiagnosisCoreResult();
		diagnosisResult.setRated(rated);
		
		if (!healthy) {
			diagnosisResult.addConditionProbability("SICK", 100);
		}
		coreData.setDiagnosisResult(diagnosisResult);
		
		
		return coreData;
	}
	
	private Date getDate(int year, int month, int day) {
		Calendar date = new GregorianCalendar(year, month, day);
		
		return date.getTime();
	}
	
}
