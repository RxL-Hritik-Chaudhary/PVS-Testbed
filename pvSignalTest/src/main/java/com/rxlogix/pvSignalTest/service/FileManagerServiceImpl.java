package com.rxlogix.pvSignalTest.service;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.rxlogix.pvSignalTest.dto.TestCaseDTO;

interface FileManagerServiceImpl {

	public void init() ;
	
	public void save(MultipartFile file) ;
		
	public boolean checkFile();
	
	public List<TestCaseDTO> getImportFileData();
	
	public Resource load(String filename);
	
	public void deleteAll();
	
}
