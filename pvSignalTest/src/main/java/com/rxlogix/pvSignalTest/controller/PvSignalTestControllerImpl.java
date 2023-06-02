package com.rxlogix.pvSignalTest.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.rxlogix.pvSignalTest.dto.TestCaseDTO;

public interface PvSignalTestControllerImpl {

	public ResponseEntity<List<String>> runAlerts(@RequestBody List<TestCaseDTO> dtoList);
	
	public String healthCheck();
	
	public ResponseEntity<List<Map<String, Object>>> testApi(@RequestBody List<String> alertNames);
	
}
