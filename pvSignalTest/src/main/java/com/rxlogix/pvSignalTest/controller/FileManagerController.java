package com.rxlogix.pvSignalTest.controller;


import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.rxlogix.pvSignalTest.dto.TestCaseDTO;
import com.rxlogix.pvSignalTest.service.FileManagerServiceImpl;
import com.rxlogix.testEngine.AggregateConfigurationTest;


//todo: merge with other controller
//FileController is used to save the file and FileManagerController is used to retrieve data from saved file
@Controller
@CrossOrigin("http://localhost:3000")
public class FileManagerController {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(FileManagerController.class);

	
	public FileManagerController(Environment env, FileManagerServiceImpl fileService) {
		super();
		this.env = env;
		this.fileService = fileService;
	}

	private final Environment env;
	
	@Autowired
	FileManagerServiceImpl fileService;
	//todo: remove if not used
	//AggregateConfigurationTest aggregateConfigurationTest = new AggregateConfigurationTest();
	
	
	private final Path root = Paths.get("importFile");
	
	
	@RequestMapping(value = "/api/getFileInfo", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<TestCaseDTO>> getFileInfo() throws IOException{
		
		List<TestCaseDTO> testCaseDTOList = new ArrayList<TestCaseDTO>();
		testCaseDTOList = fileService.getImportFileData();
		return ResponseEntity.status(HttpStatus.OK).body(testCaseDTOList);
	}
	@RequestMapping(value = "/api/checkFileExists", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> checkFileIsExists() throws IOException{
		logger.info("Checking if file already exists");
		File f = new File(env.getProperty("constants.savedFileLocation"));
		if(f.exists() && !f.isDirectory()) { 
			logger.warn("Checked. File Already exists in system");
			return new ResponseEntity<String>("true", HttpStatus.OK);
		}
		logger.warn("Checked. File doesn't exists");
		return new ResponseEntity<String>("false", HttpStatus.OK);
	}
	
	@GetMapping("/files/{filename:.+}")
	  @ResponseBody
	  public ResponseEntity<Resource> getFile(@PathVariable String filename) {
	    Resource file = fileService.load(filename);
	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	  }

}
