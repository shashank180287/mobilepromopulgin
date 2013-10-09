package com.mobile.tool.core.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import com.mobile.tool.core.model.FileType;
import com.mobile.tool.core.model.ICSVModel;

@Component
public abstract class FileUploader {

	public boolean uploadMultiPartFile(MultipartFile file, String fileType) {
        try {
			switch (FileType.valueOf(fileType.toUpperCase())) {
				case TSV:
					
					break;
				case EXCEL_XLS:{
					HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream());
					HSSFSheet sheet = workbook.getSheetAt(0);
					Iterator<Row> rowIterator = sheet.iterator();
					return processReadCSVRecords(convertExcelFileRowToModel(rowIterator));
				}
				case EXCEL_XLSX:{
					XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
					XSSFSheet sheet = workbook.getSheetAt(0);
					Iterator<Row> rowIterator = sheet.iterator();
					return processReadCSVRecords(convertExcelFileRowToModel(rowIterator));
				}
				default:
				{
					ICsvBeanReader beanReader = null;
					try{
						beanReader = new CsvBeanReader(new BufferedReader(new InputStreamReader(file.getInputStream())), CsvPreference.STANDARD_PREFERENCE);
			            final String[] header = beanReader.getHeader(isHeaderElementUsedForMapping());
			            final CellProcessor[] processors = getProcessors();
			            
			            List<ICSVModel> readCSVModelList = new ArrayList<ICSVModel>();
			            ICSVModel csvModel;
			            while( (csvModel = beanReader.read(getCSVModelClass(), header, processors)) != null ) {
			            	readCSVModelList.add(csvModel);
			            }
			            return processReadCSVRecords(readCSVModelList);
			            
					}
			        finally{
			        	if(beanReader!=null)
			        		beanReader.close();
			        }
				}
			}
		 } catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		return true;
	}

	@SuppressWarnings("rawtypes")
	private List<ICSVModel> convertExcelFileRowToModel(Iterator<Row> excelFileRows) throws IllegalArgumentException, IllegalAccessException, InstantiationException, NoSuchFieldException, SecurityException{
		Class csvFileModelClass = getCSVModelClass();
		String[] excelFileFields = getCSVFileColumnsNamesOrder();
		List<ICSVModel> csvModelList = new ArrayList<ICSVModel>();
		while (excelFileRows.hasNext()) {
			Row excelFileRow = excelFileRows.next();
			ICSVModel modelClassObj = (ICSVModel) csvFileModelClass.newInstance();
			int i=0;
			for (Cell cell : excelFileRow) {
				Field modelClassField = modelClassObj.getClass().getDeclaredField(excelFileFields[i]);
				modelClassField.setAccessible(true);
	            switch(cell.getCellType()) {
	                case Cell.CELL_TYPE_BOOLEAN:
	                	modelClassField.set(modelClassObj, cell.getBooleanCellValue());
	                    break;
	                case Cell.CELL_TYPE_NUMERIC:
	                	modelClassField.set(modelClassObj, cell.getNumericCellValue());
	                    break;
	                case Cell.CELL_TYPE_STRING:
	                	modelClassField.set(modelClassObj, cell.getStringCellValue());
	                    break;
	            }
	            i++;
			}
			csvModelList.add(modelClassObj);
		}
		return csvModelList;
	}
	
	protected abstract String[] getCSVFileColumnsNamesOrder();
	
	protected abstract boolean processReadCSVRecords(List<ICSVModel> readCSVModelList);

	protected abstract boolean isHeaderElementUsedForMapping();

	protected abstract CellProcessor[] getProcessors();
	
	protected abstract Class<? extends ICSVModel> getCSVModelClass();
}
