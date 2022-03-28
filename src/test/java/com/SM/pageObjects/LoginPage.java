package com.SM.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.SM.utilities.JavaScriptUtil;

public class LoginPage {
	//1
	public WebDriver driver;
	
	JavaScriptUtil js = new JavaScriptUtil();
	
	//2. Creating a constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver; //Assigning the driver passed from Test Case to local WebDriver driver
		PageFactory.initElements(driver, this);
	}
	
	//3. Username Locator
	@FindBy(id="username")
	@CacheLookup //Improve performance of locating elements
	WebElement txtUsername;
	
	//4. Password Locator
	@FindBy(id="password")
	@CacheLookup
	WebElement txtPassword;
	
	//5. Login Button
	@FindBy(xpath="//button[@type='submit']")
	@CacheLookup
	WebElement btnLogin;
	
	//Signup Locator
	@FindBy(linkText="Sign Up")
	@CacheLookup
	WebElement lnkSignup;
	
	
	//6. Enter username
	public void setUsername(String uname) {
		js.flash(txtUsername, driver);
		txtUsername.clear();
		txtUsername.sendKeys(uname);
	}
	
	//7. Enter Password
	public void setPassword(String pwd) {
		js.flash(txtPassword, driver);
		txtPassword.clear();
		txtPassword.sendKeys(pwd);
	}
	
	//8. Click on Login button
	public void clickLoginBtn() throws InterruptedException {
		js.scrollPageDown(driver);
		Thread.sleep(5000);
		btnLogin.click();
	}
	
	//Method to capture linkname
	public String getSignupLinkText() {
		String lnkName = lnkSignup.getText();
		js.flash(lnkSignup, driver);
		return lnkName;
	}
	
	
}
