package com.ob.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.ob.BaseTest.baseTest;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class AdminLoginPage {
	
	ExtentTest test;
	WebDriver driver;
	
	@FindBy(name = "username")
	WebElement UserName;
	
	@FindBy(name = "password")
	WebElement Password;
	
	@FindBy(xpath = "//button[text()='Sign In']")
	WebElement SignIn;
	
	public AdminLoginPage(WebDriver driver,ExtentTest test) {
		this.test = test;
		this.driver = driver;
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		PageFactory.initElements(driver, this);
	}

	public void login(String UN,String Pwd) {
		try {
			UserName.sendKeys(UN);
			Password.sendKeys(Pwd);
			SignIn.click();
			Assert.assertTrue(true, "User is clicked on sign in button");
			test.log(LogStatus.PASS, "User is clicked on sign in button");
		}catch(Exception e) {
			Assert.assertTrue(false, "User is unable to click on sign in button");
			test.log(LogStatus.FAIL, "User is unable to click on sign in button");
			e.printStackTrace();
		}
	}

}
