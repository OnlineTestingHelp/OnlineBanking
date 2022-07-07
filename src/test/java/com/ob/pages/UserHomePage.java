package com.ob.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ob.BaseTest.baseTest;
import com.relevantcodes.extentreports.LogStatus;

public class UserHomePage extends baseTest{
	
	
	WebDriver driver;

	@FindBy(xpath = "//h3[contains(text(),'Account Number:')]")
	WebElement accountNumbertxt;
	
	@FindBy(xpath = "//h3[contains(text(),'Current Balance')]")
	WebElement balanceTxt;
	
	public UserHomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public void verifyAccountNumber(String accNo) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(accountNumbertxt));
		
		String accNoApp = accountNumbertxt.getText();
		if(accNoApp.contains(accNo)) {
			//System.out.println("Account Number is matched successfully");
			test.log(LogStatus.PASS, "Account Number is matched successfully");
		}else {
			test.log(LogStatus.FAIL, "Account Number is not matched");
		}
	}
	
	public void verifyBalance(String expBalance) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(balanceTxt));
		
		String balance = balanceTxt.getText();
		if(balance.contains(expBalance)) {
			//System.out.println("Account Number is matched successfully");
			test.log(LogStatus.PASS, "Balance is matched successfully");
		}else {
			test.log(LogStatus.FAIL, "Balance is not matched");
		}
	}

}
