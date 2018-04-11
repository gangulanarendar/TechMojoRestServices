package com.techmojo.util;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.techmojo.model.BankRecord;

/**
 * @author narendar.gangula
 * 
 *         This Class is responsible for reading the values from Excel file and
 *         storing it in Java Objects
 */
public class ExcelReader {
	private static final Logger LOGGER = LoggerFactory.getLogger(ExcelReader.class);
	 
	DecimalFormat df = new DecimalFormat("#.00");

	public static void main(String[] args) throws IOException, ParseException {
		ExcelReaderUtility excelReaderUtility = new ExcelReaderUtility();
		ExcelReader excelReader = new ExcelReader();
		HSSFWorkbook workbook = excelReaderUtility
				.readExcelFile("./ExternalResource/IFCB2009_01.xls");
		HSSFSheet sheet = (HSSFSheet) excelReaderUtility.getActualsSheet(workbook);
		for (BankRecord bean : excelReader.getBankRecordDTOList(workbook, sheet)) {
			System.out.println(bean);
		}
	}	

	
	public ArrayList<BankRecord> processRequest(String filePath) throws IOException, ParseException {
		LOGGER.info("processRequest ",filePath);
		ExcelReaderUtility excelReaderUtility = new ExcelReaderUtility();
		ExcelReader excelReader = new ExcelReader();
		HSSFWorkbook workbook = excelReaderUtility
				.readExcelFile(filePath);
		HSSFSheet sheet = (HSSFSheet) excelReaderUtility.getActualsSheet(workbook);
		LOGGER.info("processRequest completed");
		return excelReader.getBankRecordDTOList(workbook, sheet);
		
	}	
	/**
	 * This method reads the values from Excel file and stores in java Objects as
	 * Collection
	 * 
	 * @param workbook
	 * @param sheet
	 * @param evaluator
	 * @return
	 * @throws ParseException
	 */
	public ArrayList<BankRecord> getBankRecordDTOList(HSSFWorkbook workbook, Sheet sheet)
			throws ParseException {
		LOGGER.info("getBankRecordDTOList stared ");
		ArrayList<BankRecord> bankRecordDTOs = new ArrayList<BankRecord>();
		// System.out.println(sheet.getLastRowNum());
		Iterator<Row> rowIterator = sheet.iterator();
		Iterator<Cell> cellIterator;
		HSSFCell cell;
		
	
		BankRecord banlRecordDTO;
		if(rowIterator.hasNext())
		{
			rowIterator.next();
		}
		while (rowIterator.hasNext()) {
			HSSFRow row = (HSSFRow) rowIterator.next();
			//System.out.println(row.getRowNum());
			
			
				banlRecordDTO = new BankRecord();
				// System.out.println("row.getRowNum() "+row.getRowNum());
				cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					cell = (HSSFCell) cellIterator.next();
					try {
						// BANK IFSC MICR BRANCH ADDRESS CONTACT CITY DISTRICT STATE
						if (cell.getColumnIndex() == 0) {
							if (cell.getStringCellValue() != null && !cell.getStringCellValue().equals(""))
								banlRecordDTO.setBank(cell.getStringCellValue().trim());
						}
						if (cell.getColumnIndex() == 1) {
							if (cell.getStringCellValue() != null && !cell.getStringCellValue().equals("")) {
								banlRecordDTO.setIfsc(cell.getStringCellValue().trim());
							}
						}
						if (cell.getColumnIndex() == 2) {
							
								banlRecordDTO.setMicr(""+cell.getNumericCellValue());

							/*if (cell.getStringCellValue() != null && !cell.getStringCellValue().equals(""))
								banlRecordDTO.setBranch(cell.getStringCellValue().trim());*/
						
						}
						if (cell.getColumnIndex() == 3) {
							if (cell.getStringCellValue() != null && !cell.getStringCellValue().equals(""))
								banlRecordDTO.setBranch(cell.getStringCellValue().trim());
						}
						if (cell.getColumnIndex() == 4) {
							if (cell.getStringCellValue() != null && !cell.getStringCellValue().equals(""))
								banlRecordDTO.setAddress(cell.getStringCellValue().trim());
						}
						if (cell.getColumnIndex() == 5) {
							
								banlRecordDTO.setContact(""+cell.getNumericCellValue());
						}
						if (cell.getColumnIndex() == 6) {
							if (cell.getStringCellValue() != null && !cell.getStringCellValue().equals(""))
								banlRecordDTO.setCity(cell.getStringCellValue().trim());
						}
						if (cell.getColumnIndex() == 7) {
							if (cell.getStringCellValue() != null && !cell.getStringCellValue().equals(""))
								banlRecordDTO.setDistrict(cell.getStringCellValue().trim());
						}
						if (cell.getColumnIndex() == 8) {
							if (cell.getStringCellValue() != null && !cell.getStringCellValue().equals(""))
								banlRecordDTO.setState(cell.getStringCellValue().trim());
						}
					} catch (IllegalStateException e) {
						// System.out.println("Exception In ExcelReader ");
						e.printStackTrace();
					}
				} // while loop end cell iterator
				bankRecordDTOs.add(banlRecordDTO);
			}
		
		
	//	} // while loop end row iterator
		
		LOGGER.info("getBankRecordDTOList size "+bankRecordDTOs.size());
		return bankRecordDTOs;
	}
}