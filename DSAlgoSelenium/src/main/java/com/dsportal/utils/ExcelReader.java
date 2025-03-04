
//src/main/java/com/dsportal/utils/ExcelReader.java
package com.dsportal.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelReader {
 private Workbook workbook;
 private Sheet sheet;
 
 public ExcelReader(String filePath) {
     try {
         FileInputStream fis = new FileInputStream(filePath);
         workbook = new XSSFWorkbook(fis);
     } catch (IOException e) {
         e.printStackTrace();
         throw new RuntimeException("Failed to load Excel file: " + filePath);
     }
 }
 
 public void selectSheet(String sheetName) {
     sheet = workbook.getSheet(sheetName);
     if (sheet == null) {
         throw new RuntimeException("Sheet not found: " + sheetName);
     }
 }
 
 public List<Map<String, String>> getDataAsListOfMaps() {
     List<Map<String, String>> dataList = new ArrayList<>();
     Row headerRow = sheet.getRow(0);
     
     for (int i = 1; i <= sheet.getLastRowNum(); i++) {
         Row dataRow = sheet.getRow(i);
         Map<String, String> dataMap = new HashMap<>();
         
         for (int j = 0; j < headerRow.getLastCellNum(); j++) {
             String key = getCellValueAsString(headerRow.getCell(j));
             String value = getCellValueAsString(dataRow.getCell(j));
             dataMap.put(key, value);
         }
         
         dataList.add(dataMap);
     }
     
     return dataList;
 }
 
 public Object[][] getDataAsObjectArray() {
     int rowCount = sheet.getLastRowNum();
     int colCount = sheet.getRow(0).getLastCellNum();
     
     Object[][] data = new Object[rowCount][colCount];
     
     for (int i = 1; i <= rowCount; i++) {
         Row row = sheet.getRow(i);
         
         for (int j = 0; j < colCount; j++) {
             data[i-1][j] = getCellValueAsString(row.getCell(j));
         }
     }
     
     return data;
 }
 
 private String getCellValueAsString(Cell cell) {
     if (cell == null) {
         return "";
     }
     
     switch (cell.getCellType()) {
         case STRING:
             return cell.getStringCellValue();
         case NUMERIC:
             if (DateUtil.isCellDateFormatted(cell)) {
                 return cell.getDateCellValue().toString();
             } else {
                 return String.valueOf(cell.getNumericCellValue());
             }
         case BOOLEAN:
             return String.valueOf(cell.getBooleanCellValue());
         case FORMULA:
             return cell.getCellFormula();
         default:
             return "";
     }
 }
 
 public void close() {
     try {
         workbook.close();
     } catch (IOException e) {
         e.printStackTrace();
     }
 }
}
