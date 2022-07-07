package com.ob.tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ob.BaseTest.baseTest;
import com.ob.pages.AdminHomePage;
import com.ob.pages.AdminLoginPage;
import com.ob.pages.ManageAccountPage;
import com.ob.pages.NewAccountPage;
import com.ob.pages.UserHomePage;
import com.ob.pages.UserLoginPage;
import com.ob.utilities.Utilities;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class creatingUser extends baseTest {
	Logger log;
	
	@Test
	public void createUser() throws IOException, InterruptedException {
		try {
			log = (Logger) LogManager.getLogger(creatingUser.class);
				String UserName;
				String Password;
				String adminURL;
				String fname = "Venkat";
				String lname = "Krishna";
				String middleName = "VK";
				String emailId  = "onlinetestinghelp" + Utilities.getRandomNum() +"@gmail.com";
				String password = "Online@123";
				int pinNumber = 1234;
				int begBalance = 10000;
				String accNo = String.valueOf(Utilities.getAccountNum());
				
				//Read properties file
				adminURL = Utilities.getProperty("AdminUrl");
				UserName = Utilities.getProperty("AdminUserName");
				Password = Utilities.getProperty("AdminPassword");
				
				//Navigate to Admin Login 
				log.info("Test Execution Started");
				driver.get(adminURL);

				AdminLoginPage al = new AdminLoginPage(driver);
				al.login(UserName, Password);
				AdminHomePage ah = new AdminHomePage(driver);
				ah.verifyAdminLogin();
				
				//Click on New User
				ah.clickOnAccManagement();
				
				//New Account page
				NewAccountPage nc = new NewAccountPage(driver);
				nc.clickOnNewAccount();		
				nc.createNewAccount(accNo,fname, lname, middleName, emailId,password,pinNumber, begBalance);
				
				ManageAccountPage ma = new ManageAccountPage(driver);
				ma.enterAccountNo(accNo);
				Thread.sleep(3000);
				ma.verifyAccountNo(accNo);
				
				//Login with newly created user
				driver.get(Utilities.getProperty("UserUrl"));
				
				UserLoginPage ulp = new UserLoginPage(driver);
				ulp.login(emailId, password);
				
				UserHomePage uhp = new UserHomePage(driver);
				uhp.verifyAccountNumber(accNo);
				
				test.log(LogStatus.PASS,"New User Creation is done successfully");
				log.info("New User creation is done successfully");
				//Assert.assertFalse(true);
		}catch(Exception e) {
			log.info("New User is not successful");
			e.printStackTrace();
		}
		
	}

}
