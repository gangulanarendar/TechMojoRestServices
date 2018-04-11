package com.techmojo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Service;

/**
 * @author narendar.gangula
 * 
 *         This class is acts like utility for reading the Excel file.
 * 
 */
@Service
public class ExcelReaderUtility {

	public HSSFWorkbook readExcelFile(String filepath) throws IOException {
		FileInputStream fileInputStream;
		HSSFWorkbook workbook = null;
		try {
			fileInputStream = new FileInputStream(new File(filepath));
			workbook = new HSSFWorkbook(fileInputStream);
		} catch (IOException e) {
			throw e;
		}
		return workbook;
	}

	public FormulaEvaluator getEvFormulaEvaluator(HSSFWorkbook workbook) {
		FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
		return evaluator;
	}

	public Sheet getActualsSheet(HSSFWorkbook workbook) {
		// return workbook.getSheetAt(3);
		return workbook.getSheet("Sheet7");
	}

}
