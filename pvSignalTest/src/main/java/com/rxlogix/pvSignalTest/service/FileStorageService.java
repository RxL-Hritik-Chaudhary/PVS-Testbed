package com.rxlogix.pvSignalTest.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.rxlogix.pvSignalTest.message.FileStorageProperties;

public interface FileStorageService {


	public String storeFile(MultipartFile file);
	
	public Resource loadFileAsResource(String fileName);

}
