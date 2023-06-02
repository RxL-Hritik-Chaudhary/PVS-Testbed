package com.rxlogix.testEngine;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.rxlogix.pvSignalTest.controller.PvSignalTestController;
import com.rxlogix.pvSignalTest.dto.TestCaseDTO;

public class BrowserSetup {
	private static final Logger logger = LogManager.getLogger(BrowserSetup.class);

	public static Properties properties;
	static WebDriver driver;
	private Map<String, Object> vars;
	static JavascriptExecutor js = (JavascriptExecutor) driver;
	private StringBuffer verificationErrors = new StringBuffer();
	@Before
    public void setUp() throws Exception {
		logger.debug("Setting Up the browser");
		driver = new ChromeDriver();
        properties = PropertiesWrapper.getPropertiesForUIConfig("");
        String driverKeyName = properties.getProperty("driver.key.name");
        String webdirver = properties.getProperty("browser.driver");
		logger.debug("============webdirver======================", () -> webdirver);

        System.setProperty(driverKeyName, webdirver);
	
      
       
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
        
        //TODO pick from config file
        //done
        String appUrl = properties.getProperty("app.url");
        driver.get(appUrl);
        
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3600, TimeUnit.SECONDS);

    }
	
	@After
    public void tearDown() {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }
	
	
	public void delay(long time) throws Exception {
		try {
        Thread.sleep(time);
		} 
		catch(Exception ex) {
			System.out.print(ex);
		}
    }
	
	public void defaultCheckedBoxes(String alertType, List<String> defaultCheckBoxList) {
        for (String property : defaultCheckBoxList) {
        	
            if(!driver.findElement(By.id(property)).isSelected()) {
            	driver.findElement(By.id(property)).click();
            	try {
					delay(2000);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
         
        }
    }
	
	public void defaultUncheckedBoxes(String alertType, List<String> defaultUncheckedBoxesList) {
        for (String property : defaultUncheckedBoxesList) {
        	if(driver.findElement(By.id(property)).isSelected()) {
            	driver.findElement(By.id(property)).click();
            	try {
					delay(2000);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
          
        }
    }
	
	public void login() {
		logger.debug("---------Logging in the system---------");
		driver.findElement(By.id("username")).sendKeys("signaldev");
	    driver.findElement(By.id("password")).sendKeys("signaldev");
	    driver.findElement(By.id("loginSubmit")).click();
	    driver.findElement(By.cssSelector(".md-settings-applications")).click();
	    driver.findElement(By.cssSelector(".md-settings-applications")).click();
		logger.debug("---------Logged in successfully----------");

	    try {
			delay(1000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void addAlertName(String alertName) {
		 	driver.findElement(By.name("name")).click();
		    driver.findElement(By.name("name")).sendKeys(alertName);
	}
	
	public void selectPriority(String priority) {
	    driver.findElement(By.id("priority")).click();
	    {
	      WebElement dropdown = driver.findElement(By.id("priority"));
	      dropdown.findElement(By.xpath("//option[. = '"+priority+"']")).click();
	    }
	    driver.findElement(By.id("priority")).click();
	}

	public void selectAssignedTo(String assignedTo) {
		
	    try {
	    	driver.findElement(By.id("select2-assignedTo-container")).click();
		    delay(1000);
		    driver.findElement(By.cssSelector(".select2-search--dropdown > .select2-search__field")).sendKeys(assignedTo);
		    delay(2000);
		    driver.findElement(By.cssSelector(".select2-search--dropdown > .select2-search__field")).sendKeys(Keys.ENTER);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
	}
	
	public String createAlertName(String alertType) {
		String prefix="SmokeTestingPVSignalDev";
		if(alertType.equalsIgnoreCase("Aggregate Case Review")) {
			prefix += "_AGG_";
		} else if(alertType.equalsIgnoreCase("Individual Case Review")) {
			prefix += "_ICR_";
		}
		long milliseconds = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");    
        Date resultdate = new Date(milliseconds);
        
        String alertName = prefix + sdf.format(resultdate);
		
		return alertName;
	}
	
	public void runAlert() {
		  driver.findElement(By.id("saveRun")).click();
	}
	
	public void addDataSource(String dataSource, TestCaseDTO dto) {
	    try {
	    	String[] dataSources = dataSource.split(",");
	    	if(dataSources.length > 1) {
	    		dto.setIsIntegrated(true);
	    	}
	        for (String dataSrc : dataSources) {
		        driver.findElement(By.cssSelector(".select2-container--focus .select2-selection__rendered")).click();
		        delay(2000);
			    driver.findElement(By.cssSelector(".select2:nth-child(2) .select2-search:nth-child(2) > .select2-search__field")).sendKeys(dataSrc);
			    delay(2000);
			    driver.findElement(By.cssSelector(".select2:nth-child(2) .select2-search:nth-child(2) > .select2-search__field")).sendKeys(Keys.ENTER);
				delay(2000);
	        }
	    	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void doProductSelection(TestCaseDTO dto) {
		
	    try {
	    	driver.findElement(By.cssSelector(".wrapper:nth-child(1) .fa")).click();
			delay(2000);
	    	if(dto.getIsIntegrated()) {
				driver.findElement(By.xpath("//*[@id=\"parentDivDataSources\"]/span/span[1]/span/ul/li/input")).click();
			    delay(2000);
			    driver.findElement(By.cssSelector("#parentDivDataSources .select2-search__field")).sendKeys(Keys.ENTER);
			    delay(2000);
			    driver.findElement(By.cssSelector(".product-modal-dialog .addProductValues")).click();
			    delay(2000);
			    driver.findElement(By.cssSelector(".addAllProducts")).click();
			    delay(2000);
	    	} else {
	    		
	    		String[] productList = dto.getProducts().split(",");
	    		
	    		for(String product: productList) {
	    			 	driver.findElement(By.cssSelector(".productColumn")).click();
					    delay(2000);
					    driver.findElement(By.cssSelector(".productColumn")).sendKeys(product);
					    delay(2000);
					    driver.findElement(By.cssSelector(".productColumn")).sendKeys(Keys.ENTER);
					    delay(5000);
					    driver.findElement(By.cssSelector(".dicLi")).click();
					    delay(5000);
					    driver.findElement(By.cssSelector(".product-modal-dialog .addProductValues")).click();
					    delay(5000);
					    driver.findElement(By.cssSelector(".product-modal-dialog .addProductValues")).click();
					    delay(3000);
	    		}
	    		driver.findElement(By.cssSelector(".addAllProducts")).click();
	    		delay(2000);
			   
	    	}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
}
	

}
