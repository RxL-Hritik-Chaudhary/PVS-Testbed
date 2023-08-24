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
import org.slf4j.LoggerFactory;

import com.rxlogix.pvSignalTest.dto.TestCaseDTO;

public class AggregateConfigurationTest extends BrowserSetup {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(BrowserSetup.class);

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
		logger.info("Aggregate Alert configuration method got called...");

		String alertName = "";

		try {
			// TODO pick from config file
			driver.findElement(By.cssSelector(".has_sub:nth-child(5) span:nth-child(2)")).click();
			driver.findElement(By.cssSelector(".open > .dropdown-toggle > span:nth-child(2)")).click();
			driver.findElement(By.linkText("Aggregate Configuration")).click();
			delay(1000);


			if (dto.getDataSource() != null) {
				addDataSource(dto.getDataSource(), dto);
			}


			List<String> defaultCheckedBoxesList = new ArrayList<String>();
			List<String> defaultUncheckedBoxesList = new ArrayList<String>();
			
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
			logger.info("Alert Date RangeType setting up");
			driver.findElement(By.id("select2-dateRangeType-container")).click();
			driver.findElement(By.cssSelector(".select2-search--dropdown > .select2-search__field")).click();
			driver.findElement(By.cssSelector(".select2-search--dropdown > .select2-search__field"))
					.sendKeys(dto.getDateRangeType());
			driver.findElement(By.cssSelector(".select2-search--dropdown > .select2-search__field"))
					.sendKeys(Keys.ENTER);
			logger.info("Alert Date RangeType setted-up successfully");

			delay(1000);
			// evaluate case date on
			logger.info("Alert Evaluate Case Date setting up");
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
			logger.info("Alert Evaluate Case Date setted-up successfully");
			delay(1000);

			// product type
			logger.info("Alert Evaluate Case Date setting up");
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
			logger.info("Alert Evaluate Case Date setted-up successfully");
			delay(1000);

			// Date Range
			logger.info("Alert Date Range setting up");
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
			logger.info("Alert Date Range setting-up successfully");
			delay(1000);
			defaultCheckedBoxes("aggregate", defaultCheckedBoxesList);
			delay(1000);
			// defaultUncheckedBoxesList.addAll(Arrays.asList("groupBySmq"));
			defaultUncheckedBoxes("aggregate", defaultUncheckedBoxesList);
			logger.info("Alert check/uncheck boxes task completed successfully");

			delay(2000);
			doProductSelection(dto);

			js.executeScript("window.scrollTo(0,0)");

			alertName = createAlertName(dto.getAlertType());

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
			ex.printStackTrace();
		}
		return alertName;
	}

	@Test
	public void logout() {
		logger.info("Logout method called..");
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
		logger.info("............Logout successfully.............");
	}

	
}
