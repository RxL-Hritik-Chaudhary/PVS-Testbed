package com.rxlogix.testEngine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.rxlogix.pvSignalTest.dto.TestCaseDTO;

public class AggregateConfigurationTest extends BrowserSetup {

	@Test
	public void browserSetup() {
		try {
			setUp();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void loginSignal() {
		try {
			login();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public String testSignalLoginMethod(TestCaseDTO dto) throws Exception {

		String alertName = "";

		try {
			// TODO pick from config file
			driver.findElement(By.cssSelector(".has_sub:nth-child(5) span:nth-child(2)")).click();
			driver.findElement(By.cssSelector(".open > .dropdown-toggle > span:nth-child(2)")).click();
			driver.findElement(By.linkText("Aggregate Configuration")).click();
			delay(1000);

			System.out.println(dto.getDataSource());

			if (dto.getDataSource() != null) {
				addDataSource(dto.getDataSource(), dto);
			}

			System.out.println(dto.getIsIntegrated());

			List<String> defaultCheckedBoxesList = new ArrayList<String>();
			List<String> defaultUncheckedBoxesList = new ArrayList<String>();
			// defaultCheckedBoxesList.addAll(Arrays.asList("excludeFollowUp","missedCases","excludeNonValidCases",
			// "adhocRun"));
			System.out.println("dto.getIsAdhoc()==========" + dto.getIsAdhoc());
			if (dto.getIsAdhoc() && !dto.getIsIntegrated()) {
				defaultCheckedBoxesList.add("adhocRun");
			} else {
				defaultUncheckedBoxesList.add("adhocRun");
			}

			if (dto.getIsExcludeFollowUp()) {
				defaultCheckedBoxesList.add("excludeFollowUp");
			} else {
				defaultUncheckedBoxesList.add("excludeFollowUp");

			}

			if (dto.getIsDataMiningSMQ()) {
				defaultCheckedBoxesList.add("groupBySmq");
			} else {
				defaultUncheckedBoxesList.add("groupBySmq");

			}

			if (dto.getIsExcludeNonValidCases()) {
				defaultCheckedBoxesList.add("excludeNonValidCases");
			} else {
				defaultUncheckedBoxesList.add("excludeNonValidCases");

			}

			if (dto.getIsIncludeMissingCases()) {
				defaultCheckedBoxesList.add("missedCases");
			} else {
				defaultUncheckedBoxesList.add("missedCases");

			}

			delay(1000);
			// date range type
			driver.findElement(By.id("select2-dateRangeType-container")).click();
			driver.findElement(By.cssSelector(".select2-search--dropdown > .select2-search__field")).click();
			driver.findElement(By.cssSelector(".select2-search--dropdown > .select2-search__field"))
					.sendKeys(dto.getDateRangeType());
			driver.findElement(By.cssSelector(".select2-search--dropdown > .select2-search__field"))
					.sendKeys(Keys.ENTER);

			delay(1000);
			// evaluate case date on
			driver.findElement(By.id("select2-evaluateDateAsNonSubmission-container")).click();
			driver.findElement(By.cssSelector(".select2-search--dropdown > .select2-search__field")).click();
			driver.findElement(By.cssSelector(".select2-search--dropdown > .select2-search__field"))
					.sendKeys(dto.getEvaluateCaseDateOn());
			driver.findElement(By.cssSelector(".select2-search--dropdown > .select2-search__field"))
					.sendKeys(Keys.ENTER);
			if (dto.getEvaluateCaseDateOn().contains("Version As Of")) {
				driver.findElement(By.id("asOfVersionDateId")).click();
				driver.findElement(By.id("asOfVersionDateId")).sendKeys(dto.getVersionAsOfDate());

			}
			delay(1000);

			// product type
			driver.findElement(By.cssSelector(".select2-selection__choice:nth-child(2) > .select2-selection__choice__remove")).click();
			delay(500);
			String[] productTypeValues = dto.getProductType().split(",");
		    for(String prodType: productTypeValues) {
			    driver.findElement(By.cssSelector(".col-md-3:nth-child(4) .select2-search__field")).sendKeys(prodType);
			    delay(500);
			    driver.findElement(By.cssSelector(".col-md-3:nth-child(4) .select2-search__field")).sendKeys(Keys.ENTER);
			    delay(500);
			    driver.findElement(By.cssSelector(".select2-container--focus .select2-selection__rendered")).click();
			    delay(500);
			}
			delay(1000);

			// Date Range
			driver.findElement(By.id("select2-alertDateRangeInformationdateRangeEnum-container")).click();
			driver.findElement(By.cssSelector(".select2-search--dropdown > .select2-search__field")).click();
			driver.findElement(By.cssSelector(".select2-search--dropdown > .select2-search__field"))
					.sendKeys(dto.getDateRange());
			driver.findElement(By.cssSelector(".select2-search--dropdown > .select2-search__field"))
					.sendKeys(Keys.ENTER);
			if (dto.getDateRange().contains("X")) {
				driver.findElement(By.id("alertDateRangeInformation.relativeDateRangeValue")).click();
				driver.findElement(By.id("alertDateRangeInformation.relativeDateRangeValue")).sendKeys(Keys.BACK_SPACE);
				driver.findElement(By.id("alertDateRangeInformation.relativeDateRangeValue"))
						.sendKeys(dto.getxForDateRange());

			} else if (dto.getDateRange().contains("Custom")) {
				driver.findElement(By.id("dateRangeStart")).click();
				driver.findElement(By.id("dateRangeStart")).sendKeys(dto.getStartDate());
				delay(500);
				driver.findElement(By.id("dateRangeEnd")).click();
				driver.findElement(By.id("dateRangeEnd")).sendKeys(dto.getEndDate());
			}
			delay(1000);
			defaultCheckedBoxes("aggregate", defaultCheckedBoxesList);
			delay(1000);
			// defaultUncheckedBoxesList.addAll(Arrays.asList("groupBySmq"));
			defaultUncheckedBoxes("aggregate", defaultUncheckedBoxesList);

			delay(2000);
			doProductSelection(dto);

			js.executeScript("window.scrollTo(0,0)");

			alertName = createAlertName(dto.getAlertType());

			System.out.println(alertName);
			delay(2000);
			addAlertName(alertName);
			delay(2000);
			selectPriority(dto.getPriority());
			delay(2000);
			selectAssignedTo(dto.getAssignedTo());
			delay(2000);
			runAlert();

			return alertName;

		} catch (Exception ex) {
			System.out.println(ex);
		}
		return alertName;
	}

	@Test
	public void logout() {
		{
			WebElement element = driver.findElement(By.cssSelector(".md-settings"));
			Actions builder = new Actions(driver);
			builder.moveToElement(element).perform();
		}
		driver.findElement(By.cssSelector(".md-settings")).click();
		{
			WebElement element = driver.findElement(By.tagName("body"));
			Actions builder = new Actions(driver);
			builder.moveToElement(element, 0, 0).perform();
		}
		driver.findElement(By.cssSelector("#MI-logout > .logout")).click();
	}

	@Test
	public void getExecutionStatus(String alertName) throws Exception{

		driver.findElement(By.cssSelector(".md-settings-applications")).click();
		driver.findElement(By.linkText("View Execution Status")).click();
		driver.findElement(By.id("alertType")).click();
		{
			WebElement dropdown = driver.findElement(By.id("alertType"));
			dropdown.findElement(By.xpath("//option[. = 'Aggregate Configuration']")).click();
		}
		
		while(true) {
			driver.findElement(By.id("executionStatus")).click();
			{
				WebElement dropdown = driver.findElement(By.id("executionStatus"));
				dropdown.findElement(By.xpath("//option[. = 'Scheduled']")).click();
			}
			driver.findElement(By.id("executionStatus")).click();
			if(driver.getPageSource().contains(alertName)) {
				delay(5000);
				continue;
			}
			else {
				while(true) {
					driver.findElement(By.id("executionStatus")).click();
					{
						WebElement dropdown = driver.findElement(By.id("executionStatus"));
						dropdown.findElement(By.xpath("//option[. = 'In Progress']")).click();
					}
					driver.findElement(By.id("executionStatus")).click();
					if(driver.getPageSource().contains(alertName)) {
						delay(10000);
						continue;
					}
					else {
						driver.findElement(By.id("executionStatus")).click();
						{
							WebElement dropdown = driver.findElement(By.id("executionStatus"));
							dropdown.findElement(By.xpath("//option[. = 'Completed']")).click();
						}
						driver.findElement(By.id("executionStatus")).click();
						if(driver.getPageSource().contains(alertName)) {
							//TODO alert completed successfully 
							return;
						}
						else {
							driver.findElement(By.id("executionStatus")).click();
							{
								WebElement dropdown = driver.findElement(By.id("executionStatus"));
								dropdown.findElement(By.xpath("//option[. = 'Error']")).click();
							}
							driver.findElement(By.id("executionStatus")).click();
							if(driver.getPageSource().contains(alertName)) {
								//TODO alert failed 
								return;
							}
							else {
								//TODO something unexpected happen
								return;
							}
						}
					}
					
				}
				
			
			}
		
		}
	}

}
