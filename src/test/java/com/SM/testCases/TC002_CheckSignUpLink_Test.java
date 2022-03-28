package com.SM.testCases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.SM.pageObjects.LoginPage;
import com.SM.testBase.BaseClass;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC002_CheckSignUpLink_Test extends BaseClass {
	
	@Test
	public void checkSignupLinkTest() throws IOException {
		logger.info("*****Executing checkSignupLinkTest*****");
		LoginPage lp = new LoginPage(driver);
		logger.info("*****Webdriver is initiated*****");
		String actVal = lp.getSignupLinkText();
		logger.info("*****Check that Sign Up link is present*****");
		capturescreen(driver, "Check Signup Link Test");
		Assert.assertEquals(actVal, "Sign Up","Sign Up link is missing");
		logger.info("*****Sign Up link is present*****");
	}

}
