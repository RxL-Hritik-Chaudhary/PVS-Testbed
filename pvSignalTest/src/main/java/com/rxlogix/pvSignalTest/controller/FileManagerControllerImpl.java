package com.rxlogix.pvSignalTest.controller;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.rxlogix.pvSignalTest.dto.TestCaseDTO;

public interface FileManagerControllerImpl {

	public ResponseEntity<List<TestCaseDTO>> getFileInfo() throws Exception ;
	
	public ResponseEntity<Resource> getFile(@PathVariable String filename);
	
}
