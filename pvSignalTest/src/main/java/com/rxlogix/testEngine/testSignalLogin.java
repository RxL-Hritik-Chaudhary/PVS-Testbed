package com.rxlogix.testEngine;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.LoggerFactory;   

public class testSignalLogin extends BrowserSetup{
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(testSignalLogin.class);

	
	 static WebDriver driver = new ChromeDriver();
	 private Map<String, Object> vars;
	 static JavascriptExecutor js = (JavascriptExecutor) driver;
	 @Before
	  public void setUp() throws MalformedURLException {
	    vars = new HashMap<String, Object>();
	  }
	  @After
	  public void tearDown() {
	    driver.quit();
	  }

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		logger.info("Test PV Signal Main called");
	}
	

}
