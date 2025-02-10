package id.asqi.idesa.bumdes.core.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class ExcelGenerator {
	private Sheet sheet;

	public <RowDataType> byte[] generate (String sheetTitle, List<RowDataType> dataList, Class<RowDataType> rowDataTypeClass) throws IllegalAccessException, IOException {
		try(XSSFWorkbook workbook = new XSSFWorkbook()) {
			sheet = workbook.createSheet(sheetTitle);

			String[] headers = this.getHeaders(rowDataTypeClass);
			this.writeHeader(headers, workbook);

			for (int i = 0; i < dataList.size(); i++) {
				RowDataType data = dataList.get(i);
				writeRow(i + 1, data, createCellStyle(false, workbook));
			}

			try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
				workbook.write(out);
				return out.toByteArray();
			}
		}
	}

	private <RowDataType> void writeRow (int rowIndex, RowDataType rowData, CellStyle style) throws IllegalAccessException {
		Row row = sheet.createRow(rowIndex);
		Field[] fields = rowData.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			fields[i].setAccessible(true);
			Object data = fields[i].get(rowData);
			this.createCell(row, i, data, style);
		}
	}

	private void writeHeader (String[] headers, XSSFWorkbook workbook ) {
		Row row = sheet.createRow(0);
		for (int i = 0; i < headers.length; i++) {
			createCell(row, i, headers[i], this.createCellStyle(true,workbook));
		}
	}

	private <CellDataType> void createCell (Row row, int columnCount, CellDataType value, CellStyle style) {
		Cell cell = row.createCell(columnCount);
		switch (value.getClass().getSimpleName()) {
			case "Integer" -> cell.setCellValue((Integer) value);
			case "Long" -> cell.setCellValue((Long) value);
			case "String" -> cell.setCellValue((String) value);
			case "Boolean" -> cell.setCellValue((Boolean) value);
			default -> cell.setCellValue(value.toString());
		}
		cell.setCellStyle(style);
	}

	private CellStyle createCellStyle (boolean isHeader, XSSFWorkbook workbook) {
		CellStyle style = workbook.createCellStyle();
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		if (isHeader) {
			style.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		} else {
			style.setAlignment(HorizontalAlignment.LEFT);
			style.setVerticalAlignment(VerticalAlignment.CENTER);
		}
		return style;
	}

	private <RowDataType> String[] getHeaders(Class<RowDataType> rowClass){
		Field[] fields = rowClass.getDeclaredFields();
		return Arrays.stream(fields).map(Field::getName).toArray(String[]::new);

	}
}