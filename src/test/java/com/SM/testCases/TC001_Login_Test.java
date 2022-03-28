package com.SM.testCases;

import java.io.IOException;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.SM.pageObjects.LoginPage;
import com.SM.testBase.BaseClass;
import com.SM.utilities.JavaScriptUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC001_Login_Test extends BaseClass {	
	@Test
	public void loginTest() throws IOException, InterruptedException {
		logger.info("*****Executing loginTest*****");
		String expTitle = "Welcome to SurveyMonkey!";
		String actTitle = "Welcome to SurveyMonkey!";
		
		LoginPage lp = new LoginPage(driver);
		logger.info("*****Webdriver is initiated*****");
		
		JavaScriptUtil js = new JavaScriptUtil();

		lp.setUsername(prop.getProperty("username"));
		logger.info("*****Username entered is : " + prop.getProperty("username") +"*****");
		lp.setPassword(prop.getProperty("password"));
		logger.info("*****Password entered is : " + prop.getProperty("password") +"*****");
		lp.clickLoginBtn();
		logger.info("*****Clicked on Login button*****");
		
		//Capturing screenshot of the application
		capturescreen(driver, "loginTest");
		
		//Verify that login is successful
		Assert.assertEquals(actTitle, expTitle,"Login is failed");
		logger.info("*****User logged in successfully*****");
	}
}
