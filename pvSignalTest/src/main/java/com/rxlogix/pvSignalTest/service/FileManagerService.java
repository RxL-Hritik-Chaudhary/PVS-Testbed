package com.rxlogix.pvSignalTest.service;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.rxlogix.pvSignalTest.controller.FileController;
import com.rxlogix.pvSignalTest.dto.TestCaseDTO;
import com.rxlogix.testEngine.PropertiesWrapper;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

//todo: make this as implementation class and implement an interface
@Service
public class FileManagerService implements FileManagerServiceImpl {
	
	private static final Logger logger = LogManager.getLogger(FileManagerService.class);
	  
	private final Environment env;

   
    private final Path root;
	private final String fileName;
	
	public FileManagerService(Environment env) {
	        this.env = env;
	        root = Paths.get(env.getProperty("constants.uploads"));
	        fileName = env.getProperty("constants.fileName");
    }
	 
	public void init() {
	    try {
	      final Path root = Paths.get(env.getProperty("constants.uploads"));
	      Files.createDirectory(root);
	    } catch (IOException e) {
	      throw new RuntimeException("Could not initialize folder for upload!");
	    }
	  }
	
	public void save(MultipartFile file) {
	    try {    	
	    	//Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
	    } catch (Exception e) {
	      throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
	    }
	  }
	
	
	public boolean checkFile() {
		Path path = this.root.resolve(fileName);
		File file = new File(path.toString());
		return file.exists();
	}
	
	public List<TestCaseDTO> getImportFileData(){
		List<TestCaseDTO> testCaseDTOList = new ArrayList<TestCaseDTO>();
		try {
			logger.debug("------------------------- getting test case data ----------------------------------");
			
			Path path = this.root.resolve(fileName);
			
			FileInputStream fis= new FileInputStream(new File(env.getProperty("constants.savedFileLocation")));
			Workbook wb = new XSSFWorkbook(fis);   
			
			Sheet sheet=wb.getSheet("Configurations");     //creating a Sheet object to retrieve object  
			Iterator<Row> itr = sheet.iterator();    //iterating over excel file 
			
			List<String> headers = new ArrayList();
			Map<Integer, String> headerMap = new HashMap<Integer, String>();
			int rowIndex =0;
			while (itr.hasNext())                 
			{  
			Row row = itr.next();  
			Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column
			TestCaseDTO dto = new TestCaseDTO();
			Integer columnIndex=0;
			while (cellIterator.hasNext())   
				{
				columnIndex++;
				Cell cell = cellIterator.next();
				switch (cell.getCellType())               
				{  
				case STRING:    //field that represents string cell type
					if(rowIndex ==0) {
						headers.add(cell.getStringCellValue());
						headerMap.put(columnIndex, cell.getStringCellValue());
					} else {
						String key = headerMap.get(columnIndex);
						String value = cell.getStringCellValue();
						switch(key) {
						case "Alert Type": 
							dto.setAlertType(value);
							break;
						case "Assigned To": 
							dto.setAssignedTo(value);
							break;
						case "Owner":
							dto.setOwner(value);
							break;
						case "Products": 
							dto.setProducts(value);
							break;
						case "Priority":
							dto.setPriority(value);
							break;
						case "X":
							dto.setxForDateRange(value);
							break;
						case "Date Range Type": 
							dto.setDateRangeType(value);
							break;
						case "Start Date": 
							dto.setStartDate(value);
							break;
						case "End Date": 
							dto.setEndDate(value);
						case "Version As Of Date":
							dto.setVersionAsOfDate(value);
							break;
						case "Share With":
							dto.setShareWith(value);
							break;
						case "Scheduler":
							dto.setScheduler(value);
							break;
						case "Adhoc":
							if(value.equals("Yes")) {
								dto.setIsAdhoc(true);
							} else 
								dto.setIsAdhoc(false);
							break;
						case "Data Source":
							dto.setDataSource(value);
							break;
						case "Exclude FollowUp":
							if(value.equals("Yes")) {
								dto.setIsExcludeFollowUp(true);
							} else 
								dto.setIsExcludeFollowUp(false);
							break;
						case "Data Mining SMQ":
							if(value.equals("Yes")) {
								dto.setIsDataMiningSMQ(true);
							} else 
								dto.setIsDataMiningSMQ(false);
							break;
						case "Exclude Non Valid Cases":
							if(value.equals("Yes")) {
								dto.setIsExcludeNonValidCases(true);
							} else 
								dto.setIsExcludeNonValidCases(false);
							break;
						case "Include Missing Cases":
							if(value.equals("Yes")) {
								dto.setIsIncludeMissingCases(true);
							} else 
								dto.setIsIncludeMissingCases(false);
							break;
						case "Apply Alert Stop List":
							if(value.equals("Yes")) {
								dto.setIsApplyAlertStopList(true);
							} else 
								dto.setIsApplyAlertStopList(false);
							break;
						case "Include Medically Confirmed Cases":
							if(value.equals("Yes")) {
								dto.setIsIncludeMedicallyConfirmedCases(true);
							} else {
								dto.setIsIncludeMedicallyConfirmedCases(false);
							}
							break;
						case "Date Range":
							dto.setDateRange(value);
							break;
						case "Drug Type":
							dto.setDrugType(value);
							break;
						case "Evaluate Case Date On":
							dto.setEvaluateCaseDateOn(value);
							break;
						case "Limit Case Series":
							dto.setLimitCaseSeries(value);
							break;
						default:
							System.out.println(key);
							break;
						}
					}  
				break;  
				case NUMERIC:   
					String key = headerMap.get(columnIndex);
					Integer value = (int) cell.getNumericCellValue();
					switch(key) {
					case "X":
						String val = Integer.toString(value);  
						dto.setxForDateRange(val);
						break;
					default:
						break;
					}
					
				break;
				default:  
				}
			}
			if(rowIndex>0) {
				testCaseDTOList.add(dto);
			}
			rowIndex++;
			}
		} catch( Exception ex) {
			logger.debug("Reading file failed with error {} ",() -> ex);
		}
			
		return testCaseDTOList;
	}
	
	public Resource load(String filename) {
	    try {
	      Path file = root.resolve(filename);
	      Resource resource = new UrlResource(file.toUri());
	      if (resource.exists() || resource.isReadable()) {
	        return resource;
	      } else {
	        throw new RuntimeException("Could not read the file!");
	      }
	    } catch (MalformedURLException e) {
	      throw new RuntimeException("Error: " + e.getMessage());
	    }
	  }
	
	public void deleteAll() {
	    FileSystemUtils.deleteRecursively(root.toFile());
	  }
	
	  
}
