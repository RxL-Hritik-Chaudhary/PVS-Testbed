package com.rxlogix.pvSignalTest.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rxlogix.pvSignalTest.dto.FileResponse;
import com.rxlogix.pvSignalTest.service.FileStorageServiceImpl;


@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("files")
public class FileController {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(FileController.class);
	
	@Autowired
	private FileStorageServiceImpl fileStorageService;
	
	@PostMapping
	public ResponseEntity<FileResponse> uploadFile(@RequestParam("file") MultipartFile file){
		String fileName = fileStorageService.storeFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/files/")
				.path(fileName)
				.toUriString();
		
		FileResponse fileResponse = new FileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
		logger.info("----------{} file uploaded successfully---------",fileName);
		return new ResponseEntity<FileResponse>(fileResponse,HttpStatus.OK);
	}
	
	@GetMapping("/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName,HttpServletRequest request){

		Resource resource = fileStorageService.loadFileAsResource(fileName);
		
		String contentType = null;
		
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		}catch(IOException ex) {
			logger.error("Could not determine fileType");
			ex.printStackTrace();
		}
		
		if(contentType==null) {
			contentType = "application/octet-stream";
		}
		
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(contentType))
				.body(resource);
	} 
	
}