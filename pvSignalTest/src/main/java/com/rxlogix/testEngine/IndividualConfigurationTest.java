package com.rxlogix.testEngine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.slf4j.LoggerFactory;

import com.rxlogix.pvSignalTest.controller.PvSignalTestController;
import com.rxlogix.pvSignalTest.dto.TestCaseDTO;


public class IndividualConfigurationTest extends BrowserSetup {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(IndividualConfigurationTest.class);

	
	@Test
	public String testICR(TestCaseDTO dto) throws Exception{ 
		logger.info("ICR Alert configuration method got called...");
		String alertName = "";
		 	try {
		 		driver.findElement(By.cssSelector(".has_sub:nth-child(5) span:nth-child(2)")).click();
			    driver.findElement(By.linkText("Individual Case Configuration")).click();
			    
			    dto.setIsIntegrated(false);
			    
			    List<String> defaultCheckedBoxesList =new ArrayList<String>();
	            List<String> defaultUncheckedBoxesList =new ArrayList<String>();
	            
	            if(dto.getIsAdhoc() && !dto.getIsIntegrated()) {
	            	defaultCheckedBoxesList.add("adhocRun");
	            }
	            else {
	    		    defaultUncheckedBoxesList.add("adhocRun");
	            }
	            
	            if(dto.getIsExcludeFollowUp()) {
	            	defaultCheckedBoxesList.add("excludeFollowUp");
	            }
	            else {
	    		    defaultUncheckedBoxesList.add("excludeFollowUp");

	            }
	            
	            if(dto.getIsExcludeNonValidCases()) {
	            	defaultCheckedBoxesList.add("excludeNonValidCases");
	            }
	            else {
	    		    defaultUncheckedBoxesList.add("excludeNonValidCases");

	            }
	            
	            if(dto.getIsIncludeMissingCases()) {
	            	defaultCheckedBoxesList.add("missedCases");
	            }
	            else {
	    		    defaultUncheckedBoxesList.add("missedCases");

	            }
	            
				/*
				 * if(dto.getIsApplyAlertStopList()) {
				 * defaultCheckedBoxesList.add("applyAlertStopList"); } else {
				 * defaultUncheckedBoxesList.add("applyAlertStopList");
				 * 
				 * }
				 */
	            
	            if(dto.getIsIncludeMedicallyConfirmedCases()) {
	            	defaultCheckedBoxesList.add("includeMedicallyConfirmedCases");
	            }
	            else {
	    		    defaultUncheckedBoxesList.add("includeMedicallyConfirmedCases");

	            }
	            delay(1000);
	            
	            defaultCheckedBoxes("individual",defaultCheckedBoxesList);
			    delay(2000);
	            defaultUncheckedBoxes("individual",defaultUncheckedBoxesList);
				logger.info("Alert check/uncheck boxes task completed successfully");

			    //date range type
				logger.info("Alert Date RangeType setting up");
	            driver.findElement(By.id("select2-dateRangeType-container")).click();
	            driver.findElement(By.cssSelector(".select2-search--dropdown > .select2-search__field")).click();
	            driver.findElement(By.cssSelector(".select2-search--dropdown > .select2-search__field")).sendKeys(dto.getDateRangeType());
	            driver.findElement(By.cssSelector(".select2-search--dropdown > .select2-search__field")).sendKeys(Keys.ENTER);
				logger.info("Alert Date RangeType setted-up successfully");
	            delay(1000);
	            
	            //evaluate case date on
				logger.info("Alert Evaluate Case Date On setting up");
	            driver.findElement(By.id("select2-evaluateDateAsNonSubmission-container")).click();
	            driver.findElement(By.cssSelector(".select2-search--dropdown > .select2-search__field")).click();
	            driver.findElement(By.cssSelector(".select2-search--dropdown > .select2-search__field")).sendKeys(dto.getEvaluateCaseDateOn());
	            driver.findElement(By.cssSelector(".select2-search--dropdown > .select2-search__field")).sendKeys(Keys.ENTER);
	            if(dto.getEvaluateCaseDateOn().contains("Version As Of")) {
	            	driver.findElement(By.id("asOfVersionDateId")).click();
	                driver.findElement(By.id("asOfVersionDateId")).sendKeys(dto.getVersionAsOfDate());
	              
	            }
				logger.info("Alert Evaluate Case Date On setted-up successfully");
	            delay(1000);
	            
	            //Date Range
				logger.info("Alert Date Range setting up");
	            driver.findElement(By.id("select2-alertDateRangeInformationdateRangeEnum-container")).click();
	            driver.findElement(By.cssSelector(".select2-search--dropdown > .select2-search__field")).click();
	            driver.findElement(By.cssSelector(".select2-search--dropdown > .select2-search__field")).sendKeys(dto.getDateRange());
	            driver.findElement(By.cssSelector(".select2-search--dropdown > .select2-search__field")).sendKeys(Keys.ENTER);
	            delay(1000);
	            if(dto.getDateRange().contains("X")) {
	            	driver.findElement(By.id("alertDateRangeInformation.relativeDateRangeValue")).click();
	                driver.findElement(By.id("alertDateRangeInformation.relativeDateRangeValue")).sendKeys(Keys.BACK_SPACE);
	                driver.findElement(By.id("alertDateRangeInformation.relativeDateRangeValue")).sendKeys(dto.getxForDateRange());
	                
	            }
	            else if(dto.getDateRange().contains("Custom")) {
	                driver.findElement(By.id("dateRangeStart")).click();
	                driver.findElement(By.id("dateRangeStart")).sendKeys(dto.getStartDate());
	                delay(500);
	                driver.findElement(By.id("dateRangeEnd")).click();
	                driver.findElement(By.id("dateRangeEnd")).sendKeys(dto.getEndDate());
	            }
				logger.info("Alert Date Range setted-up successfully");
	            delay(1000);
	            
	            //limit case series
				logger.info("Alert Limit Case Series setting up");
	            if(!dto.getLimitCaseSeries().contains("null")) {
	            	driver.findElement(By.id("select2-limitToCaseSeries-container")).click();
	                driver.findElement(By.cssSelector(".select2-search--dropdown > .select2-search__field")).click();
	                driver.findElement(By.cssSelector(".select2-search--dropdown > .select2-search__field")).sendKeys(dto.getLimitCaseSeries());
	                delay(500);
	                driver.findElement(By.cssSelector(".select2-search--dropdown > .select2-search__field")).sendKeys(Keys.ENTER);
	            }
				logger.info("Alert Limit Case Series setted-up successfully");
				delay(4000);
			    //code for product addition
			    
			    doProductSelection(dto);
			    
			    js.executeScript("window.scrollTo(0,0)");
			    
			    alertName = createAlertName(dto.getAlertType());
			    
			    driver.findElement(By.id("select2-dateRangeType-container")).click();
			    driver.findElement(By.id("select2-dateRangeType-container")).click();
			    
			    delay(2000);
			    addAlertName(alertName);
			    delay(3000);
			    
			    selectPriority(dto.getPriority());
			    delay(2000);
			    selectAssignedTo(dto.getAssignedTo());
			    delay(2000);
			    runAlert();
			    return alertName;
		 	}catch(Exception ex) {
				ex.printStackTrace();
			}
		    return alertName;

	}
}

