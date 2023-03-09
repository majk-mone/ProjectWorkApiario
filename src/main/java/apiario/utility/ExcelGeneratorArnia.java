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

import apiario.model.Arnia;

public class ExcelGeneratorArnia {

    public static ByteArrayInputStream arniaExcel(List<Arnia> arnie) throws IOException {
	String[] COLUMNs = { "idArnia", "tipoRegina", "annoRegina", "annoAcquisto", "Indirizzo", "TipoArnia" };

	try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {

	    Sheet sheet = workbook.createSheet("Arnia");

	    Font headerFont = workbook.createFont();
	    headerFont.setBold(true);
	    headerFont.setColor(IndexedColors.RED.index);

	    CellStyle headerCellStyle = workbook.createCellStyle();
	    headerCellStyle.setFont(headerFont);

	    // Row for Header
	    Row headerRow = sheet.createRow(0);

	    for (int col = 0; col < COLUMNs.length; col++) {
		Cell cell = headerRow.createCell(col);
		cell.setCellValue(COLUMNs[col]);
		cell.setCellStyle(headerCellStyle);
	    }

	    int rowIdx = 1;

	    for (Arnia arnia : arnie) {
		Row row = sheet.createRow(rowIdx++);

		row.createCell(0).setCellValue(arnia.getIdArnia());
		row.createCell(1).setCellValue(arnia.getTipoRegina());
		row.createCell(2).setCellValue(arnia.getAnnoRegina());
		row.createCell(3).setCellValue(arnia.getAnnoAcquisto());
		row.createCell(4).setCellValue(arnia.getApiario().getIndirizzo());
		row.createCell(5).setCellValue(arnia.getTipoArnia().getDescrizione());

	    }
	    workbook.write(out);
	    return new ByteArrayInputStream(out.toByteArray());
	}
    }
}
