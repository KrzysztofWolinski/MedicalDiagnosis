package com.medica.integration.controller.history;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.medica.integration.controller.history.domain.HistoryGetDataResponse;

@RestController
@RequestMapping("/history")
public class HistoryController {

	@RequestMapping(value = "/data", method = RequestMethod.POST)
    public HistoryGetDataResponse getUserDataHistoryList() {  	
		// TODO mock		
        return new HistoryGetDataResponse();
    }
	
	@RequestMapping(value = "/diagnosis", method = RequestMethod.POST)
    public String getUserDiagnosisHistoryList() {  	
		// TODO mock		
        return "user:getUserDiagnosisHistoryList";
    }
	
	@RequestMapping(value = "/data/{id}", method = RequestMethod.GET)
    public String getUserDataHistoryDetails(@PathVariable String id) {  	
		// TODO mock		
        return "user:getUserDataHistoryDetails:" + id;
    }
	
	@RequestMapping(value = "/diagnosis/{id}", method = RequestMethod.GET)
    public String getUserDiagnosisHistoryDetails(@PathVariable String id) {  	
		// TODO mock		
        return "user:getUserDiagnosisHistoryDetails:" + id;
    }
}
