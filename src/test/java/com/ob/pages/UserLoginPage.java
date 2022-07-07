package com.ob.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.ob.BaseTest.baseTest;
import com.relevantcodes.extentreports.LogStatus;

public class UserLoginPage extends baseTest{
	
	WebDriver driver;

	@FindBy(name = "email")
	WebElement email;
	
	@FindBy(name = "password")
	WebElement password;
	
	@FindBy(xpath = "//button[text()='Login']")
	WebElement loginBtn;
	
	@FindBy(xpath = "//h3[contains(text(),'Account Number:')]")
	WebElement accountNumbertxt;
	
	public UserLoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void login(String UN, String Password) throws InterruptedException {
		try {
		email.sendKeys(UN);
		password.sendKeys(Password);
		//loginBtn.click();
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", loginBtn);
		test.log(LogStatus.PASS, "User is logged in successfully");
		}
		catch(Exception e) {
			test.log(LogStatus.FAIL, "User is not logged in");
		}
	}
	

}
