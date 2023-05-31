package com.rxlogix.pvSignalTest;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.rxlogix.pvSignalTest.message.FileStorageProperties;
import com.rxlogix.pvSignalTest.service.FileManagerService;
import com.rxlogix.pvSignalTest.dto.NativeQueryDTO;
import com.rxlogix.pvSignalTest.dto.NativeRepository;


@SpringBootApplication
@EnableConfigurationProperties({
	FileStorageProperties.class
})
public class PvSignalTestApplication implements CommandLineRunner {
	
	@Resource
	FileManagerService fileService;
	//@Resource
	//NativeRepository nativeRepo; // = new NativeRepository() ; 

	public static void main(String[] args) {
		SpringApplication.run(PvSignalTestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		fileService.deleteAll();
		fileService.init();
		//NativeQueryDTO x = nativeRepo.runmyquery(1);
		//System.out.print(x);
		// TODO Auto-generated method stub
		
	} 

}


