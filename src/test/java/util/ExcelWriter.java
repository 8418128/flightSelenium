package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import skyscanner.dao.Result;

public class ExcelWriter {
	
	private static int COLUMNS_NUM = 5;
	
	public static void generateExcel(List<Result> results,String path) throws IOException {
		 		long time = System.currentTimeMillis();
		 		Calendar calendar = Calendar.getInstance();
		 		calendar.setTimeInMillis(time);
		 		
			    Workbook workbook = new XSSFWorkbook();
			    Sheet sheet = initSheet(workbook);

			    int rowNum = 1;

			    for (Result r : results) {
			      Row row = sheet.createRow(rowNum++);
			      row.createCell(0).setCellValue(CalendarUtils.formatDate(calendar.getTime(),"dd/MM/yyyy hh:mm"));
			      row.createCell(1).setCellValue(r.getFrom());
			      row.createCell(2).setCellValue(r.getTo());
			      row.createCell(3).setCellValue(r.getPrice());
			      row.createCell(4).setCellValue(r.getDatego());
			      row.createCell(5).setCellValue(r.getHourgo());
			      row.createCell(7).setCellValue(r.getDateback());
			      row.createCell(7).setCellValue(r.getHourback());
			      row.createCell(8).setCellValue(r.getUrl());
			    }

			    for (int i = 0; i < COLUMNS_NUM; i++) {
			      sheet.autoSizeColumn(i);
			    }
			    
			    FileOutputStream fileOut = new FileOutputStream(path+"report.xlsx");
			    workbook.write(fileOut);
			    workbook.close();			    
			    fileOut.close();
	}
	
	private static Sheet initSheet(Workbook workbook) {
		Font headerFont = workbook.createFont();
	    headerFont.setBold(true);
	    headerFont.setFontHeightInPoints((short) 14);
    	Sheet sheet = workbook.createSheet("report");
    	CellStyle headerCellStyle = workbook.createCellStyle();
	    headerCellStyle.setFont(headerFont);
    	createHeaders(sheet.createRow(0), headerCellStyle);
    	return sheet;
	}
	
	private static void createHeaders(Row headerRow,CellStyle headerCellStyle) {
		String[] headers = {"Test Date","Departure airport","Arrival airport","Price","Departure date","Departure time","Arrival date","Arrival time","Url"};
		
		for(int i=0;i<=headers.length;i++) {
			Cell cell6 = headerRow.createCell(i);
		    cell6.setCellValue(headers[i]);
		    cell6.setCellStyle(headerCellStyle);
		}
	}
}
