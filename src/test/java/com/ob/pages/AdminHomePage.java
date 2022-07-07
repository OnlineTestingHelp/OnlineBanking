package com.ob.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.ob.BaseTest.baseTest;
import com.relevantcodes.extentreports.LogStatus;

public class AdminHomePage extends baseTest{
	
	WebDriver driver;

	@FindBy(xpath = "//a[contains(text(),'Online Banking System - Admin')]")
	WebElement OBSAdminLink;
	
	@FindBy(xpath = "//p[contains(text(),'Account Management')]/parent::a")
	WebElement accountManagementLink;
	
	@FindBy(xpath = "//p[contains(text(),'Transaction')]/parent::a[contains(@class,'nav-withdraw')]")
	WebElement transaction;
	
	@FindBy(xpath = "//p[text()='Manage Account']/parent::a")
	WebElement manageAccountLink;
	
	public AdminHomePage(WebDriver driver) {
		this.driver = driver;
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyAdminLogin() {
		boolean status = false;
		try {
			status = OBSAdminLink.isDisplayed();
			Assert.assertTrue(true, "User is logged in successfully");
			test.log(LogStatus.PASS, "User is logged in successfully");
		}catch(Exception e) {
			Assert.assertTrue(false, "Admin User is not logged in");
			test.log(LogStatus.FAIL, "User is NOT logged in");
			e.printStackTrace();
		}
		return status;
	}
	
	public void clickOnAccManagement() {
		try {
			Thread.sleep(4000);
			WebDriverWait wait =new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOf(accountManagementLink));
			
			accountManagementLink.click();
			Assert.assertTrue(true, "Click on account management link is successfull");
			test.log(LogStatus.PASS, "Click on account management link is successfull");
		}catch(Exception e) {
			Assert.assertTrue(false, "Failed to click on account management link");
			test.log(LogStatus.FAIL, "Failed to click on account management link");
			e.printStackTrace();
		}
	}
	
	public void clickOnTransaction() {
		try {
		transaction.click();
		Assert.assertTrue(true, "Click on Transaction link is successfull");
		test.log(LogStatus.PASS, "Click on Transaction link is successfull");
	}catch(Exception e) {
		Assert.assertTrue(false, "Failed to click on Transaction link");
		test.log(LogStatus.FAIL, "Failed to click on Transaction link");
		e.printStackTrace();
	}
	}
	
	public void clickOnManageAccount() {
		manageAccountLink.click();
	}
}
