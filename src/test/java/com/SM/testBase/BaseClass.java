package com.SM.testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.SM.utilities.JavaScriptUtil;
import com.aventstack.extentreports.utils.FileUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	public WebDriver driver;
	public Properties prop;
	
	JavaScriptUtil js = new JavaScriptUtil();
	
	public Logger logger = LogManager.getLogger(this.getClass());
	
	/***
	 * @author Naresh
	 * @description This method is used to open the browser and launch the application
	 * @param br
	 * @throws IOException
	 * @Date 4th Aug 2021
	 */
	@BeforeClass
	@Parameters("browsername")
	
	public void setup(String br) throws IOException {
		prop = new Properties();
		FileInputStream fis = new FileInputStream(".\\resources\\config.properties");
		prop.load(fis);
		
		//Opening the browser based on parameter passed in testnh.xml file
		if (br.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			String browName = js.getBrowserInfo(driver);
			System.out.println("*****"+browName+"is launched*****");
		} else if(br.equalsIgnoreCase("edge")) {
			logger.info("*****Edge Browser will be launched*****");
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if(br.equalsIgnoreCase("firefox")) {
			logger.info("*****Firefox Browser will be launched*****");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else {
			logger.info("*****Please pass the browsername as chrome / edge / firefox*****");
		}
		
		//Maximising the application
		driver.manage().window().maximize();
		//Application will be opened based on the url passed on config.properties
		driver.get(prop.getProperty("url"));
	}
	
	/***
	 * @author Naresh
	 * @Description This method is used to close the browser
	 * @date 4th Aug 2021
	 */
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	public void capturescreen(WebDriver driver, String testName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "\\screenshots\\"+testName+".png");
		FileUtils.copyFile(source, target);
		logger.info("*****Screenshot captured successfully*****");
	}

}
