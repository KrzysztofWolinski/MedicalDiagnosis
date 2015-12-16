package com.medica.integration.controller.history;

import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.medica.integration.controller.history.domain.HistoryDeleteDataRequest;
import com.medica.integration.controller.history.domain.HistoryGetDataByDateResponse;
import com.medica.integration.controller.history.domain.HistoryGetDataByNameResponse;
import com.medica.integration.controller.history.domain.HistoryGetDataDetailsRequest;
import com.medica.integration.controller.history.domain.HistoryGetDataDetailsResponse;
import com.medica.integration.controller.history.domain.HistoryGetDataRequest;
import com.medica.integration.controller.history.domain.HistoryReviewResultRequest;
import com.medica.integration.service.auth.AuthService;
import com.medica.integration.service.auth.exceptions.InvalidCredentialsException;
import com.medica.integration.service.history.HistoryService;
import com.medica.integration.service.history.domain.HistoryByDateDataBlock;
import com.medica.integration.service.history.domain.HistoryByNameDataBlock;

@RestController
@RequestMapping("/history")
public class HistoryController {

	@Inject
	AuthService authService;
	
	@Inject
	HistoryService historyService;
	
	@RequestMapping(value = "/by-date", method = RequestMethod.POST)
    public HistoryGetDataByDateResponse getUserHistoryByDate(@RequestBody HistoryGetDataRequest request) throws InvalidCredentialsException {  	
		if (authService.isAuthorized(request.getUsername(), request.getToken())) {
			List<HistoryByDateDataBlock> data = this.historyService.getDataByDate(request.getUsername());
			
			return new HistoryGetDataByDateResponse(data);
			
		} else {
			throw new InvalidCredentialsException();
		}
    }
	
	@RequestMapping(value = "/by-name", method = RequestMethod.POST)
    public HistoryGetDataByNameResponse getUserHistoryByName(@RequestBody HistoryGetDataRequest request) throws InvalidCredentialsException {  	
		if (authService.isAuthorized(request.getUsername(), request.getToken())) {
			List<HistoryByNameDataBlock> data = this.historyService.getDataByName(request.getUsername());
			
			return new HistoryGetDataByNameResponse(data);
			
		} else {
			throw new InvalidCredentialsException();
		}
    }
	
	@RequestMapping(value = "/results", method = RequestMethod.POST)
    public HistoryGetDataDetailsResponse getDataDetails(@RequestBody HistoryGetDataDetailsRequest request) throws InvalidCredentialsException {  	
		if (authService.isAuthorized(request.getUsername(), request.getToken())) {
						
			return this.historyService.getDataDetails(request.getUsername(), request.getDataId());
			
		} else {
			throw new InvalidCredentialsException();
		}
    }
	
	@RequestMapping(value = "/review", method = RequestMethod.POST)
    public void reviewDiagnosis(@RequestBody HistoryReviewResultRequest request) throws InvalidCredentialsException {  	
		if (authService.isAuthorized(request.getUsername(), request.getToken())) {
						
			historyService.reviewDiagnosisDataResults(request.getUsername(), request.getDataId(), request.getNewConditionProbabilities());
			
		} else {
			throw new InvalidCredentialsException();
		}
    }
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public void deleteSingleDataSet(@PathVariable Long id, @RequestBody HistoryDeleteDataRequest request) throws InvalidCredentialsException {
		if (authService.isAuthorized(request.getUsername(), request.getToken())) {
			
			historyService.deleteSingliDataSet(id, request.getUsername());
			
		} else {
			throw new InvalidCredentialsException();
		}
	}
	
}
