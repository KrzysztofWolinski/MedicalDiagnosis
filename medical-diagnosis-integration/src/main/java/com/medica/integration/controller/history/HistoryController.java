package com.medica.integration.controller.history;

import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.medica.integration.controller.history.domain.HistoryGetDataByDateResponse;
import com.medica.integration.controller.history.domain.HistoryGetDataByNameResponse;
import com.medica.integration.controller.history.domain.HistoryGetDataRequest;
import com.medica.integration.controller.history.domain.HistoryGetDiagnosesHistoryResponse;
import com.medica.integration.service.auth.AuthService;
import com.medica.integration.service.auth.exceptions.InvalidCredentialsException;
import com.medica.integration.service.history.HistoryService;
import com.medica.integration.service.history.domain.HistoryByDateDataBlock;
import com.medica.integration.service.history.domain.HistoryByNameDataBlock;
import com.medica.integration.service.history.domain.HistoryDiagnosesDataBlock;

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
	
	@RequestMapping(value = "/diagnoses", method = RequestMethod.POST)
    public HistoryGetDiagnosesHistoryResponse getUserDiagnosesHistory(@RequestBody HistoryGetDataRequest request) throws InvalidCredentialsException {  	
		if (authService.isAuthorized(request.getUsername(), request.getToken())) {
			List<HistoryDiagnosesDataBlock> data = this.historyService.getDiagnosesData(request.getUsername());
			
			return new HistoryGetDiagnosesHistoryResponse(data);
			
		} else {
			throw new InvalidCredentialsException();
		}
    }
}
