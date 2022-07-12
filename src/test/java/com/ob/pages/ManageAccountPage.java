package com.ob.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ob.BaseTest.baseTest;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ManageAccountPage {
	
	WebDriver driver;
	ExtentTest test;

	@FindBy(xpath = "//input[@type='search']")
	WebElement search;
	
	@FindBy(xpath = "//table[@id='indi-list']/tbody/tr[1]/td[2]")
	WebElement userTable;
	
	
	@FindBy(xpath = "//table[@id='indi-list']/tbody/tr[1]/td[4]")
	WebElement currentBalance;
	
	public ManageAccountPage(WebDriver driver,ExtentTest test) {
		this.driver = driver;
		this.test = test;
		
		PageFactory.initElements(driver, this);			
	}
	
	public void enterAccountNo(String accountNo) {
		try {
			search.sendKeys(accountNo);
			test.log(LogStatus.PASS, "Entering account no is successful");
		}catch(Exception e) {
			test.log(LogStatus.FAIL, "Entering account no is not successful");
			e.printStackTrace();
		}
		}
	
	public void verifyAccountNo(String accountNo) {
		String accNo = userTable.getText();
		if(accNo.contentEquals(accountNo)) {
			test.log(LogStatus.PASS, "Account Number verified successfully");
		}
		else {
			test.log(LogStatus.FAIL, "Account Number is not matched");
		}
		

	}
	
	public String getCurrentBalance() {
		String currBalance;
		currBalance = currentBalance.getText();
		return currBalance;
	}
}
