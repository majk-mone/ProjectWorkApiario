package apiario.utility;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import apiario.model.Apiario;

public class ExcelGeneratorApiario {

    public static ByteArrayInputStream apiarioToExcel(List<Apiario> apiari) throws IOException {
	String[] COLUMNs = { "idApiario", "indirizzo", "longitudine", "latitudine" };
	try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {

	    Sheet sheet = workbook.createSheet("Apiario");

	    Font headerFont = workbook.createFont();
	    headerFont.setBold(true);
	    headerFont.setColor(IndexedColors.RED.getIndex());

	    CellStyle headerCellStyle = workbook.createCellStyle();
	    headerCellStyle.setFont(headerFont);

	    // Row for Header
	    Row headerRow = sheet.createRow(0);

	    // Header

	    for (int col = 0; col < COLUMNs.length; col++) {
		Cell cell = headerRow.createCell(col);
		cell.setCellValue(COLUMNs[col]);
		cell.setCellStyle(headerCellStyle);
	    }

	    int rowIdx = 1;

	    for (Apiario apiario : apiari) {
		Row row = sheet.createRow(rowIdx++);

		row.createCell(0).setCellValue(apiario.getIdApiario());
		row.createCell(1).setCellValue(apiario.getIndirizzo());
		row.createCell(2).setCellValue(apiario.getLongitudine());
		row.createCell(3).setCellValue(apiario.getLatitudine());
	    }

	    workbook.write(out);
	    return new ByteArrayInputStream(out.toByteArray());
	}
    }
}
