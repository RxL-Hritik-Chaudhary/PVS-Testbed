package com.rxlogix.testEngine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.rxlogix.pvSignalTest.dto.TestCaseDTO;


public class IndividualConfigurationTest extends BrowserSetup {
	
	
	@Test
	public String testICR(TestCaseDTO dto) throws Exception{ 
		
		String alertName = "";
		 	try {
		 		driver.findElement(By.cssSelector(".has_sub:nth-child(5) span:nth-child(2)")).click();
			    driver.findElement(By.linkText("Individual Case Configuration")).click();
			    
			    dto.setIsIntegrated(false);
			    
			    List<String> defaultCheckedBoxesList =new ArrayList<String>();
	            List<String> defaultUncheckedBoxesList =new ArrayList<String>();
	            //defaultCheckedBoxesList.addAll(Arrays.asList("excludeFollowUp","applyAlertStopList","includeMedicallyConfirmedCases","excludeNonValidCases","missedCases"));
	            
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
	            
	            if(dto.getIsApplyAlertStopList()) {
	            	defaultCheckedBoxesList.add("applyAlertStopList");
	            }
	            else {
	    		    defaultUncheckedBoxesList.add("applyAlertStopList");

	            }
	            
	            if(dto.getIsIncludeMedicallyConfirmedCases()) {
	            	defaultCheckedBoxesList.add("includeMedicallyConfirmedCases");
	            }
	            else {
	    		    defaultUncheckedBoxesList.add("includeMedicallyConfirmedCases");

	            }
	            delay(1000);
	            
	            defaultCheckedBoxes("individual",defaultCheckedBoxesList);
			    delay(2000);
			    //defaultUncheckedBoxesList.addAll(Arrays.asList("groupBySmq"));
	            defaultUncheckedBoxes("individual",defaultUncheckedBoxesList);

			    //date range type
	            driver.findElement(By.id("select2-dateRangeType-container")).click();
	            driver.findElement(By.cssSelector(".select2-search--dropdown > .select2-search__field")).click();
	            driver.findElement(By.cssSelector(".select2-search--dropdown > .select2-search__field")).sendKeys(dto.getDateRangeType());
	            driver.findElement(By.cssSelector(".select2-search--dropdown > .select2-search__field")).sendKeys(Keys.ENTER);
	            delay(1000);
	            
	            //evaluate case date on
	            driver.findElement(By.id("select2-evaluateDateAsNonSubmission-container")).click();
	            driver.findElement(By.cssSelector(".select2-search--dropdown > .select2-search__field")).click();
	            driver.findElement(By.cssSelector(".select2-search--dropdown > .select2-search__field")).sendKeys(dto.getEvaluateCaseDateOn());
	            driver.findElement(By.cssSelector(".select2-search--dropdown > .select2-search__field")).sendKeys(Keys.ENTER);
	            if(dto.getEvaluateCaseDateOn().contains("Version As Of")) {
	            	driver.findElement(By.id("asOfVersionDateId")).click();
	                driver.findElement(By.id("asOfVersionDateId")).sendKeys(dto.getVersionAsOfDate());
	              
	            }
	            delay(1000);
	            
	            //Date Range
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
	            delay(1000);
	            
	            //limit case series
	            if(!dto.getLimitCaseSeries().contains("null")) {
	            	driver.findElement(By.id("select2-limitToCaseSeries-container")).click();
	                driver.findElement(By.cssSelector(".select2-search--dropdown > .select2-search__field")).click();
	                driver.findElement(By.cssSelector(".select2-search--dropdown > .select2-search__field")).sendKeys(dto.getLimitCaseSeries());
	                delay(500);
	                driver.findElement(By.cssSelector(".select2-search--dropdown > .select2-search__field")).sendKeys(Keys.ENTER);
	            }
	            
			    //code for product addition
			    delay(4000);
			    doProductSelection(dto);
			    
			    js.executeScript("window.scrollTo(0,0)");
			    
			    alertName = createAlertName(dto.getAlertType());
			    
			    
			    driver.findElement(By.id("select2-dateRangeType-container")).click();
			    driver.findElement(By.id("select2-dateRangeType-container")).click();
			    
			    System.out.println(alertName);
			    delay(2000);
			    addAlertName(alertName);
			    delay(3000);
			    
//			    delay(2000);
//			    driver.findElement(By.id("select2-assignedTo-container")).click();
//			    delay(2000);
//			    driver.findElement(By.cssSelector(".select2-search--dropdown > .select2-search__field")).sendKeys("sig");
			    
//			    driver.findElement(By.id("select2-assignedTo-container")).click();
//			    delay(1000);
//			    System.out.println(dto.getAssignedTo());
//			    driver.findElement(By.cssSelector(".select2-search--dropdown > .select2-search__field")).sendKeys(dto.getAssignedTo());
//			    delay(2000);
			    selectPriority(dto.getPriority());
			    delay(2000);
			    selectAssignedTo(dto.getAssignedTo());
			    delay(2000);
			    runAlert();
			    return alertName;
		 	}catch(Exception ex) {
				System.out.println(ex);
			}
		    return alertName;

	}
}

