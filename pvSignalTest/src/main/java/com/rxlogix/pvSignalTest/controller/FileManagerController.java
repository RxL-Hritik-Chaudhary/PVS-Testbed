package com.rxlogix.pvSignalTest.controller;


import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.rxlogix.pvSignalTest.service.FileManagerService;
import com.rxlogix.testEngine.AggregateConfigurationTest;



@Controller
@CrossOrigin("http://localhost:3000")
public class FileManagerController {
	
	@Autowired
	FileManagerService fileService;
	AggregateConfigurationTest aggregateConfigurationTest = new AggregateConfigurationTest();
	
	
	private final Path root = Paths.get("importFile");
	

	 @RequestMapping(value = "/api/uploadTestCases", method = RequestMethod.POST)
	 @ResponseBody
	  public ResponseEntity<Boolean> uploadFiles(@RequestParam MultipartFile file) {
		System.out.println("uploadFile method called:");
	    String message = "";
	    try {
	    	System.out.print(file);
	    	//fileService.save(file);
	       //message = "Uploaded the file successfully: " + file.getOriginalFilename();
	       return ResponseEntity.status(HttpStatus.OK).body(true);
	      //return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
	    } catch (Exception e) {
	      //message = "Could not upload the file: " + file.getOriginalFilename() + "!";
	      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(false);
	    }
	  }
	
	@RequestMapping(value = "/api/getFileInfo", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<TestCaseDTO>> getFileInfo() throws IOException{
		
		List<TestCaseDTO> testCaseDTOList = new ArrayList<TestCaseDTO>();
		testCaseDTOList = fileService.getImportFileData();
		return ResponseEntity.status(HttpStatus.OK).body(testCaseDTOList);
	}
	
	@GetMapping("/files/{filename:.+}")
	  @ResponseBody
	  public ResponseEntity<Resource> getFile(@PathVariable String filename) {
	    Resource file = fileService.load(filename);
	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
	  }
	
	
	@RequestMapping(value = "/getTestCaseData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<TestCaseDTO>> getFileData() throws IOException{
		
		List<TestCaseDTO> testCaseDTOList = fileService.getImportFileData();
		
		return ResponseEntity.status(HttpStatus.OK).body(testCaseDTOList);
	}
	
	@RequestMapping(value = "/api/checkIfFileExists", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Boolean> checkIfFileExists() throws IOException{
		
		if(fileService.checkIfFileExists()) {
			return ResponseEntity.status(HttpStatus.OK).body(true);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(false);
	}
	
	@RequestMapping(value = "/api/deleteFile", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Boolean> deleteFile() throws IOException{
		
		
		if(fileService.deleteFile()) {
			return ResponseEntity.status(HttpStatus.OK).body(true);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(false);
		}
		
	}
	
	

}
