package com.rxlogix.pvSignalTest.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.rxlogix.pvSignalTest.dto.FileResponse;

public interface FileControllerImpl {

	public ResponseEntity<FileResponse> uploadFile(@RequestParam("file") MultipartFile file);
	
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName,HttpServletRequest request);

}