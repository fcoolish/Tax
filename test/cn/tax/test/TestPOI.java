package cn.tax.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

public class TestPOI 
{
//	@Test
//	public void testWrite() throws IOException
//	{
//		//1、创建工作簿
//		XSSFWorkbook workbook = new XSSFWorkbook();
//		//2、创建工作表
//		XSSFSheet sheet = workbook.createSheet("hello world");
//		//3、创建行
//		XSSFRow  row = sheet.createRow(2);
//		//4、创建单元格
//		XSSFCell cell = row.createCell(2);
//		cell.setCellValue("hello world");
//		
//		//输出到硬盘
//		FileOutputStream outputStream = new FileOutputStream("D:\\Workspace\\用户列表.xlsx");
//		//把execl输出到具体的地址
//		workbook.write(outputStream);
//		workbook.close();
//		outputStream.close();
//	}
//	@Test
//	public void testRead() throws IOException
//	{
//		FileInputStream inputStream = new FileInputStream("D:\\Workspace\\用户列表.xlsx");
//		//1、读取工作簿
//		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
//		//2、读取工作表
//		XSSFSheet sheet = workbook.getSheetAt(0);
//		XSSFRow row = sheet.getRow(3);
//		XSSFCell cell = row.getCell(3);
//		System.out.println(cell.getStringCellValue());
//		
//		workbook.close();
//		inputStream.close();
//	}
	public void testReadAll() throws IOException
	{
		FileInputStream inputStream = new FileInputStream("D:\\Workspace\\用户列表.xlsx");
		boolean is03Excel = true;
		
		//1、读取工作簿
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		//2、读取工作表
		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFRow row = sheet.getRow(3);
		XSSFCell cell = row.getCell(3);
		System.out.println(cell.getStringCellValue());
		
		workbook.close();
		inputStream.close();
	}
}
