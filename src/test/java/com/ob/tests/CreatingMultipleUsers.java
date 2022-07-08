package com.ob.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import com.ob.BaseTest.baseTest;

public class CreatingMultipleUsers extends baseTest {
	XSSFWorkbook book;
	static XSSFSheet sheet ;
	File file;
	
	//Read and write the data excel
	public void excel() throws InvalidFormatException, IOException {
		//HSSF -XLS file
		//XSSF -XLSX file
		file = new File("E:\\Excel\\Data.xlsx");
		book = new XSSFWorkbook();
		sheet = book.createSheet("TestData");
		
		writeData(0,"123454321","Venkat","Krishna","VK","OnlineTesting@gmail.com","Online@123","1234","10000");
		writeData(1,"123454321","Venkat","Krishna","VK","OnlineTesting@gmail.com","Online@123","1234","10000");
		writeData(2,"123454321","Venkat","Krishna","VK","OnlineTesting@gmail.com","Online@123","1234","10000");
		writeData(3,"123454321","Venkat","Krishna","VK","OnlineTesting@gmail.com","Online@123","1234","10000");
		writeData(4,"123454321","Venkat","Krishna","VK","OnlineTesting@gmail.com","Online@123","1234","10000");
		writeData(5,"123454321","Venkat","Krishna","VK","OnlineTesting@gmail.com","Online@123","1234","10000");
		
		
		FileOutputStream fos = new FileOutputStream(file);
		book.write(fos);
		fos.close();
		
		FileInputStream fis = new FileInputStream(file);
		book = new XSSFWorkbook(fis);
		sheet = book.getSheet("TestData");
		
		int rowNo = sheet.getLastRowNum();
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

	public static void main(String args[]) throws InvalidFormatException, IOException {
		CreatingMultipleUsers cmu = new CreatingMultipleUsers();
		cmu.excel();
	}

}
