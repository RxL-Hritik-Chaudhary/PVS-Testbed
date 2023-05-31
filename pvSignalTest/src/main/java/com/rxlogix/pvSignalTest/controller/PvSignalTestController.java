package com.rxlogix.pvSignalTest.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import com.rxlogix.pvSignalTest.service.FileManagerService;
import com.rxlogix.testEngine.AggregateConfigurationTest;
import com.rxlogix.testEngine.IndividualConfigurationTest;
import com.rxlogix.testEngine.testSignalLogin;

import javax.annotation.Resource;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

@RestController
@CrossOrigin("http://localhost:3000")
public class PvSignalTestController {

	@Resource
	ExStatusRepository exStatusRepository;

	@Autowired
	FileManagerService fileService;
	AggregateConfigurationTest aggregateConfigurationTest = new AggregateConfigurationTest();
	IndividualConfigurationTest individualConfigurationTest = new IndividualConfigurationTest();

	@RequestMapping(value = "/api/runAlertsTest", method = RequestMethod.POST)
	public ResponseEntity<Boolean> runAlertsTest(@RequestBody List<TestCaseDTO> dtoList) {
		System.out.println("++++++++++++++++++++Hacker++++++++++++++++++++++");
		System.out.println(dtoList.get(0));

		return ResponseEntity.status(HttpStatus.OK).body(true);
	}

	@RequestMapping(value = "/api/runAlerts", method = RequestMethod.POST)
	public ResponseEntity<List<String>> runAlerts(@RequestBody List<TestCaseDTO> dtoList) {
		System.out.println("1111111111111111111111111111111111111111111111111111");
		System.out.println(dtoList.get(0));
		System.out.println("runAlerts called");
		List<String> alertNames = new ArrayList<>();
		;

		try {
			aggregateConfigurationTest.browserSetup();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		aggregateConfigurationTest.login();

		Integer size = dtoList.size();
		System.out.print("size " + size);
		for (int i = 0; i < size; i++) {
			System.out.println("running " + i + " dtoList");
			// TODO: ADD LOGGER AND SEND TESTCASEdtoList TO THE METHOD
			try {

				System.out.println("dtoList.get(i).getAlertType()-------------------" + dtoList.get(i).getAlertType());

				if (dtoList.get(i).getAlertType().equalsIgnoreCase("Individual Case Review")) {
					System.out.println("111111111111111111111111111");
					alertNames.add(individualConfigurationTest.testICR(dtoList.get(i)));
				} else if (dtoList.get(i).getAlertType().equalsIgnoreCase("Aggregate Case Review")) {

					System.out.println("222222222222222222222222222");
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

	/*
	 * @ModelAttribute
	 * 
	 * @RequestMapping(value = "/api/executionStatus", method = RequestMethod.POST)
	 * public ResponseEntity<List<ExStatusDTO>> executionStatus(@RequestBody
	 * List<String> alertNames) { List<ExStatusDTO> executionStatusList = new
	 * ArrayList<ExStatusDTO>(); System.out.println(alertNames.get(0)+ " size " +
	 * alertNames.size()); for (int i = 0; i < alertNames.size() ; i++) { try {
	 * ExStatusDTO executionStatus =
	 * exStatusRepository.fetchExecutionStatus(alertNames.get(i));
	 * executionStatusList.add(executionStatus); } catch(Exception ex) {
	 * ex.printStackTrace(); } } System.out.println(" executionStatusList  " +
	 * executionStatusList.size());
	 * 
	 * return new ResponseEntity<List<ExStatusDTO>>(executionStatusList,
	 * HttpStatus.OK); }
	 */

	/*
	 * @RequestMapping(value = "/api/executionValue", method = RequestMethod.POST)
	 * public ResponseEntity<Map<String, String>> executionValue(@RequestBody
	 * List<String> alertNames) { List<Map<String, String>> executionStatusList =
	 * new ArrayList<Map<String, String>>();
	 * 
	 * for (int i = 0; i < alertNames.size(); i++) {
	 * 
	 * System.out.println("Alert Name for execution status-> " + alertNames.get(i));
	 * 
	 * Map<String, String> currentExecutionStatus = new HashMap<String, String>();
	 * 
	 * try { aggregateConfigurationTest.browserSetup();
	 * aggregateConfigurationTest.login();
	 * aggregateConfigurationTest.getExecutionStatus(alertNames.get(i)); } catch
	 * (Exception e1) { // TODO Auto-generated catch block e1.printStackTrace(); } }
	 * 
	 * return new ResponseEntity<Map<String, String>>(currentExecutionStatus,
	 * HttpStatus.OK); }
	 */

	@RequestMapping("/")
	public String healthCheck() {
		return "PV Signal Testing Application is Running!!!";
	}
	
	@ModelAttribute
	@RequestMapping(value = "/exeStatus", method = RequestMethod.POST)
	public ResponseEntity<List<Map<String, Object>>> testApi(@RequestBody List<String> alertNames) {
		List<Map<String, Object>> executionStatus = exStatusRepository.fetchExecutionStatus();
		
		return new ResponseEntity<List<Map<String, Object>>>(executionStatus, HttpStatus.OK);
	}

	@RequestMapping("/runTestAlerts")
	public Boolean pvSignalTesting() throws Exception {

		Map<String, String> excelToDtoMap = new HashMap<>();
		excelToDtoMap.put("Alert Type", "alertType");
		excelToDtoMap.put("Assigned To", "assignedTo");
		excelToDtoMap.put("Owner", "owner");
		excelToDtoMap.put("Products", "products");
		excelToDtoMap.put("Priority", "priority");

		System.out.println("runTestAlerts Running");

		AggregateConfigurationTest aggregateConfigurationTest = new AggregateConfigurationTest();

		FileInputStream fis = new FileInputStream(new File("/home/shivamvashist/ImportFile.xlsx"));
		Workbook wb = new XSSFWorkbook(fis);

		Sheet sheet = wb.getSheet("Configurations"); // creating a Sheet object to retrieve object
		Iterator<Row> itr = sheet.iterator(); // iterating over excel file

		List<String> headers = new ArrayList();
		// Map <String, String> alertData = new HashMap();
		List<Map> alertDataList = new ArrayList();
		List<TestCaseDTO> teseCaseDtoList = new ArrayList();
		int rowIndex = 0;
		while (itr.hasNext()) {
			Row row = itr.next();
			Iterator<Cell> cellIterator = row.cellIterator(); // iterating over each column
			Map<String, String> tmpDataMap = new HashMap();
			TestCaseDTO testCaseDTO = new TestCaseDTO();
			int columnIndex = 0;
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				switch (cell.getCellType()) {
				case STRING: // field that represents string cell type
					if (rowIndex == 0) {
						headers.add(cell.getStringCellValue());
					} else {
						tmpDataMap.put(headers.get(columnIndex), cell.getStringCellValue());
					}
					System.out.println(columnIndex);
					System.out.println(cell.getStringCellValue() + "\t\t\t");
					break;
				case BOOLEAN:
					tmpDataMap.put(headers.get(columnIndex), cell.getStringCellValue());
					System.out.println(cell.getStringCellValue() + "\t\t\t");
				case NUMERIC: // field that represents number cell type
					// System.out.print(cell.getNumericCellValue() + "\t\t\t");
					break;
				default:
				}
				columnIndex++;
			}
			System.out.println(tmpDataMap);
			alertDataList.add(tmpDataMap);
			rowIndex++;
			// System.out.println("");
		}
		System.out.println(alertDataList);
		for (int i = 0; i < alertDataList.size(); i++) {
			System.out.println(alertDataList.get(i));
			Map<String, String> tempDataMap = alertDataList.get(i);
			if (!tempDataMap.isEmpty()) {
				System.out.println("temp data");
			}

		}
		wb.close();
		// testSignalLogin.testSignalLoginMethod();

		return true;
	}

	public static String getCellValue(Cell cell) {
		switch (cell.getCellType()) {
		case STRING: // field that represents string cell type
			return cell.getStringCellValue() + "\t\t\t";
		case NUMERIC: // field that represents number cell type
			return cell.getNumericCellValue() + "\t\t\t";
		case BOOLEAN:
			return cell.getBooleanCellValue() + "\t\t\t";
//	        case Date:    //field that represents Date cell type
//	            return cell.getDateCellValue() + "\t\t\t";
		default:
			return "";
		}

	}
}
