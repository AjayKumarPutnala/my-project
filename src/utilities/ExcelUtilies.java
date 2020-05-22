package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtilies {
Workbook wb;
public ExcelUtilies (String excelpath)throws Throwable
{
	FileInputStream fi=new FileInputStream(excelpath);
	wb=WorkbookFactory.create(fi);
}
public int rowCount(String sheetname)
{
	return wb.getSheet(sheetname).getLastRowNum();
}
public int colcount(String sheetname)
{
	return wb.getSheet(sheetname).getRow(0).getLastCellNum();
}
public String getCellData(String sheetname,int row,int col)
{
	String data="";
	if(wb.getSheet(sheetname).getRow(row).getCell(col).getCellType()==Cell.CELL_TYPE_NUMERIC)
	{
		int celldata=(int)wb.getSheet(sheetname).getRow(row).getCell(col).getNumericCellValue();
		data=String.valueOf(celldata);
	}
	else
	{
		data=wb.getSheet(sheetname).getRow(row).getCell(col).getStringCellValue();
	}
	return data;
}
public void setCellData(String sheetname,int row,int col,String status,String outputexcel)throws Throwable
{
	Sheet ws=wb.getSheet(sheetname);
	Row rownum=ws.getRow(row);
	Cell cell=rownum.createCell(col);
	cell.setCellValue(status);
	if(status.equalsIgnoreCase("pass"))
	{
		CellStyle style=wb.createCellStyle();
		Font font=wb.createFont();
		font.setColor(IndexedColors.GREEN.getIndex());
		font.setBold(true);
		font.setBoldweight(font.BOLDWEIGHT_BOLD);
		style.setFont(font);
		rownum.getCell(col).setCellStyle(style);
	}
	else if(status.equalsIgnoreCase("fail"))
	{
		CellStyle style=wb.createCellStyle();
		Font font=wb.createFont();
		font.setColor(IndexedColors.RED.getIndex());
		font.setBold(true);
		font.setBoldweight(font.BOLDWEIGHT_BOLD);
		style.setFont(font);
		rownum.getCell(col).setCellStyle(style);
	}
	else if(status.equalsIgnoreCase("blocked"))
	{
		CellStyle style=wb.createCellStyle();
		Font font=wb.createFont();
		font.setColor(IndexedColors.RED.getIndex());
		font.setBold(true);
		font.setBoldweight(font.BOLDWEIGHT_BOLD);
		style.setFont(font);
		rownum.getCell(col).setCellStyle(style);
	}
	FileOutputStream fo=new FileOutputStream(outputexcel);
	wb.write(fo);
}
}
