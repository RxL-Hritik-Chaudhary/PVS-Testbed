package com.rxlogix.pvSignalTest.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.LoggerFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.rxlogix.pvSignalTest.dto.ExStatusDTO;
import com.rxlogix.pvSignalTest.dto.ExStatusRepository;
import com.rxlogix.pvSignalTest.dto.NativeRepository;
import com.rxlogix.pvSignalTest.dto.TestCaseDTO;
import com.rxlogix.pvSignalTest.service.FileManagerServiceImpl;
import com.rxlogix.testEngine.AggregateConfigurationTest;
import com.rxlogix.testEngine.IndividualConfigurationTest;
import com.rxlogix.testEngine.testSignalLogin;

import javax.annotation.Resource;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@CrossOrigin("http://localhost:3000")
public class PvSignalTestController {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(PvSignalTestController.class);

	// TODO: why @resource?
	// because we inject a bean
	@Resource
	ExStatusRepository exStatusRepository;

	@Autowired
	FileManagerServiceImpl fileService;
	AggregateConfigurationTest aggregateConfigurationTest = new AggregateConfigurationTest();
	IndividualConfigurationTest individualConfigurationTest = new IndividualConfigurationTest();

	// TODO: use dependency injection wherever applicable and add log4lj logger
	// instead of println

	// todo: move logic part to service
	// main logic part already in service/testEngine
	@RequestMapping(value = "/api/runAlerts", method = RequestMethod.POST)
	public ResponseEntity<List<String>> runAlerts(@RequestBody List<TestCaseDTO> dtoList) {
		logger.info("runAlerts() method called");

		List<String> alertNames = new ArrayList<>();

		try {
			aggregateConfigurationTest.browserSetup();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		aggregateConfigurationTest.login();

		Integer size = dtoList.size();
		for (int i = 0; i < size; i++) {
			TestCaseDTO testCaseDTO = dtoList.get(i);
			// TODO: ADD LOGGER AND SEND TESTCASEdtoList TO THE METHOD
			//done
			logger.info("Currently executing Alert details : {}", testCaseDTO.toString());

			try {

				if (dtoList.get(i).getAlertType().equalsIgnoreCase("Individual Case Review")) {
					alertNames.add(individualConfigurationTest.testICR(dtoList.get(i)));
				} else if (dtoList.get(i).getAlertType().equalsIgnoreCase("Aggregate Case Review")) {
					alertNames.add(aggregateConfigurationTest.testSignalLoginMethod(dtoList.get(i)));

				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		aggregateConfigurationTest.logout();

		return new ResponseEntity<List<String>>(alertNames, HttpStatus.OK);
	}

	@RequestMapping("/")
	public String healthCheck() {
		return "PV Signal Testing Application is Running!!!";
	}

	
	@ModelAttribute
	@RequestMapping(value = "/exeStatus", method = RequestMethod.POST)
	public ResponseEntity<List<Map<String, Object>>> testApi(@RequestBody List<String> alertNames) {
		logger.info("Fetching new execution status of alerts.....");
		List<Map<String, Object>> executionStatus = exStatusRepository.fetchExecutionStatus();
		logger.info("Fetched successfully");
		return new ResponseEntity<List<Map<String, Object>>>(executionStatus, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/deleteAlerts", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteAlerts() {
		logger.info("Delete method called to delete '%Smoke%' Named Alerts");
		try {
			exStatusRepository.deleteAutomationAlerts();
			logger.info("Alerts deletion successful");
			return new ResponseEntity<String>("Success", HttpStatus.OK);
		}
		catch(Exception ex) {
			logger.error("Error encountered during the deletion of alerts with exception {}",ex);
			return new ResponseEntity<String>("Failed", HttpStatus.OK);
		}
	}
 
}
