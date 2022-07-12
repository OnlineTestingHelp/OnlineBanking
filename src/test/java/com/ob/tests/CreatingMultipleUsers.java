package com.ob.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
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

public class CreatingMultipleUsers extends baseTest {
	Logger log;

	
	//XLS - HSSFWorkbook
	XSSFWorkbook book;
	static XSSFSheet sheet ;
	File file;
	int rowNo;
	int colNo;
	
	//Read and write the data excel
	public void excel() throws InvalidFormatException, IOException {
		//HSSF -XLS file
		//XSSF -XLSX file
		file = new File("E:\\Excel\\Data.xlsx");
		book = new XSSFWorkbook();
		sheet = book.createSheet("TestData");
		
		writeData(0,"123454321","Venkat","Krishna","VK","OnlineTesting","Online@123","1234","10000");
		writeData(1,"123454321","Venkat","Krishna","VK","OnlineTesting","Online@123","1234","10000");
		writeData(2,"123454321","Venkat","Krishna","VK","OnlineTesting","Online@123","1234","10000");
		writeData(3,"123454321","Venkat","Krishna","VK","OnlineTesting","Online@123","1234","10000");
		writeData(4,"123454321","Venkat","Krishna","VK","OnlineTesting","Online@123","1234","10000");
		writeData(5,"123454321","Venkat","Krishna","VK","OnlineTesting","Online@123","1234","10000");
		writeData(6,"123454321","Venkat","Krishna","VK","OnlineTesting","Online@123","1234","10000");
		
		
		FileOutputStream fos = new FileOutputStream(file);
		book.write(fos);
		fos.close();
		
		FileInputStream fis = new FileInputStream(file);
		book = new XSSFWorkbook(fis);
		sheet = book.getSheet("TestData");
		
		rowNo = sheet.getLastRowNum();
		colNo = sheet.getRow(1).getPhysicalNumberOfCells();
		for(int i=0;i<=rowNo;i++) {
			System.out.println("-----Row Number-----"+ i);
			System.out.println(sheet.getRow(i).getCell(0).getStringCellValue());
			System.out.println(sheet.getRow(i).getCell(1).getStringCellValue());
			System.out.println(sheet.getRow(i).getCell(2).getStringCellValue());
			System.out.println(sheet.getRow(i).getCell(3).getStringCellValue());
			System.out.println(sheet.getRow(i).getCell(4).getStringCellValue());
			System.out.println(sheet.getRow(i).getCell(5).getStringCellValue());
		}

	}
	
	public static void writeData(int rowNo,String accNo,String fname,String lname,String middleName,String emailId,String password,String pin,String begBalance) {
		XSSFRow row = sheet.createRow(rowNo);
		row.createCell(0).setCellValue(accNo);
		row.createCell(1).setCellValue(fname);
		row.createCell(2).setCellValue(lname);
		row.createCell(3).setCellValue(middleName);
		row.createCell(4).setCellValue(emailId);
		row.createCell(5).setCellValue(password);
		row.createCell(6).setCellValue(pin);
		row.createCell(7).setCellValue(begBalance);
	}
	
	@Test(dataProvider = "multipleUsers")
	public void multipleUsers(String accNo,String fname,String lname,String middleName,String email,String pwd,String pin,String begBalance) {
		log = (Logger) LogManager.getLogger(creatingUser.class);
	try {	
		String UserName;
		String Password;
		String adminURL;
		String randomNo = String.valueOf(Utilities.getAccountNum());
		
		
		//Read properties file
		adminURL = Utilities.getProperty("AdminUrl");
		UserName = Utilities.getProperty("AdminUserName");
		Password = Utilities.getProperty("AdminPassword");
		
		//Navigate to Admin Login 
		log.info("Test Execution Started");
		driver.get(adminURL);

		AdminLoginPage al = new AdminLoginPage(driver,test);
		al.login(UserName, Password);
		AdminHomePage ah = new AdminHomePage(driver,test);
		ah.verifyAdminLogin();
		
		//Click on New User
		ah.clickOnAccManagement();
		
		//New Account page
		NewAccountPage nc = new NewAccountPage(driver,test);
		int pinValue = Integer.parseInt(pin);
		int beginingBalance = Integer.parseInt(begBalance);
		String emailValue = email + randomNo +"@gmail.com";
		
		nc.clickOnNewAccount();		
		nc.createNewAccount(randomNo,fname, lname, middleName, emailValue,pwd,pinValue, beginingBalance);
		
		ManageAccountPage ma = new ManageAccountPage(driver,test);
		ma.enterAccountNo(accNo);
		Thread.sleep(3000);
		ma.verifyAccountNo(accNo);
		
		//Login with newly created user
		driver.get(Utilities.getProperty("UserUrl"));
		
		UserLoginPage ulp = new UserLoginPage(driver,test);
		ulp.login(emailValue, pwd);
		
		UserHomePage uhp = new UserHomePage(driver,test);
		uhp.verifyAccountNumber(accNo);
		
		test.log(LogStatus.PASS,"New User Creation is done successfully");
		log.info("New User creation is done successfully");
		//Assert.assertFalse(true);
	}catch(Exception e) {
	log.info("New User is not successful");
	e.printStackTrace();
}
		
	}
	
	@DataProvider(name="multipleUsers")
	public Object[][] mulUsers() throws InvalidFormatException, IOException{
		
		CreatingMultipleUsers cmu = new CreatingMultipleUsers();
		cmu.excel();

		rowNo = sheet.getLastRowNum();
		colNo = sheet.getRow(1).getPhysicalNumberOfCells();
		Object[][] obj = new Object[rowNo][colNo];
		
		for(int i=0;i<=rowNo-1;i++) {
			for(int j=0;j<=colNo-1;j++) {
				obj[i][j] = sheet.getRow(i).getCell(j).getStringCellValue();
			}
		}

		return obj;
		
	}

//	public static void main(String args[]) throws InvalidFormatException, IOException {
//		CreatingMultipleUsers cmu = new CreatingMultipleUsers();
//		cmu.excel();
//	}

}
