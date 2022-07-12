package com.ob.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ob.BaseTest.baseTest;
import com.ob.utilities.Utilities;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class NewAccountPage{
	
	
	WebDriver driver;
	ExtentTest test;
		
		@FindBy(name="account_number")
		WebElement accountNumber;
		
		@FindBy(name="firstname")
		WebElement firstName;
		
		@FindBy(name="middlename")
		WebElement middleName;
		
		@FindBy(name="lastname")
		WebElement lastName;
		
		@FindBy(name="email")
		WebElement email;
		
		@FindBy(name="generated_password")
		WebElement password;
		
		@FindBy(name="pin")
		WebElement pin;
		
		@FindBy(name="balance")
		WebElement balance;
		
		@FindBy(xpath = "//button[text()='Save'][@form='account-form']")
		WebElement Save;
		
		@FindBy(xpath = "//p[contains(text(),'New Account')]")
		WebElement newAccount;
		
		public NewAccountPage(WebDriver driver,ExtentTest test) {
			this.driver = driver;
			this.test = test;
			PageFactory.initElements(driver, this);			
		}
		
		public void clickOnNewAccount() {
			newAccount.click();
		}
		
		public void createNewAccount(String accNo, String fname,String lname,String midName,String emailId,String passwrd,int pinNum,int begBalance) {
			try {
			accountNumber.sendKeys(accNo);
			firstName.sendKeys(fname);
			lastName.sendKeys(lname);
			middleName.sendKeys(midName);
			email.sendKeys(emailId);
			password.sendKeys(passwrd);
			pin.sendKeys(String.valueOf(pinNum));
			balance.sendKeys(String.valueOf(begBalance));
			Save.click();
			test.log(LogStatus.PASS, "Account number is created successfully");
			}catch(Exception e) {
				test.log(LogStatus.FAIL, "Account Number is not created");
			}
			
		}
	
}
