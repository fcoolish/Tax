package cn.tax.core.util;

import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;

import cn.tax.nsfw.user.entity.User;

public class ExeclUtil {
	
	/*
	 * 导出用户的所有列表到execl
	 * 
	 */
	public static void exportUserExcel(List<User> userList,
			ServletOutputStream outputStream) 
	{
		// TODO Auto-generated method stub

		try {
			//1、创建工作簿
			HSSFWorkbook workbook = new HSSFWorkbook();
			//1.1、创建合并单元格对象
			CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, 4);//其实行号，列号
			//1.2、头标题样式
			HSSFCellStyle style1 = creatCellStyle(workbook, (short) 16);
			//1.3、列标题样式
			HSSFCellStyle style2 = creatCellStyle(workbook, (short) 16);
			//2、创建工作表
			HSSFSheet Sheet = workbook.createSheet();
			//2.1、加载合并单元格对象
			Sheet.addMergedRegion(cellRangeAddress);
			//设置默认列宽
			Sheet.setDefaultColumnWidth(25);
			//3、创建行
			//3.1、创建头标题行；并且设置头标题
			HSSFRow row1 = Sheet.createRow(0);
			HSSFCell cell1 = row1.createCell(0);
			//加载单元格样式
			cell1.setCellStyle(style1);
			cell1.setCellValue("用户列表");
			//3.2、创建列标题行；并且设置列标题
			HSSFRow row2 = Sheet.createRow(1);
			String[] titles = { "用户名", "账号", "所属部门", "性别", "电子邮箱" };
			for (int i = 0; i < titles.length; i++) {

				HSSFCell cell2 = row1.createCell(i);
				//加载单元格样式
				cell2.setCellStyle(style2);
				cell2.setCellValue(titles[i]);

			}
			//4、操作单元格；将用户列表写入excel
			if (userList != null) {
				for (int j = 0; j < userList.size(); j++) {
					HSSFRow Row = Sheet.createRow(j + 2);
					HSSFCell Cell = Row.createCell(0);
					Cell.setCellValue(userList.get(j).getName());
					HSSFCell Cell1 = Row.createCell(1);
					Cell1.setCellValue(userList.get(j).getAccount());
					HSSFCell Cell2 = Row.createCell(2);
					Cell2.setCellValue(userList.get(j).getDept());
					HSSFCell Cell3 = Row.createCell(3);
					Cell3.setCellValue(userList.get(j).isGender() ? "男" : "女");
					HSSFCell Cell4 = Row.createCell(4);
					Cell4.setCellValue(userList.get(j).getEmail());

				}
				//5、输出
			}
			workbook.write(outputStream);
			workbook.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	/**
	 * 创建单元格样式
	 * @param workbook	工作薄
	 * @param fontSize	字体大小
	 * @return	单元格样式
	 */
	public static HSSFCellStyle creatCellStyle(HSSFWorkbook workbook,short fontSize) 
	{
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
		//创建字体
		HSSFFont font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗字体
		font.setFontHeightInPoints(fontSize);
		//加载字体
		style.setFont(font);
		return style;
	}
}
