package apiario.utility;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import apiario.model.Trattamento;

public class ExcelGeneratorTrattamenti {

    public static ByteArrayInputStream trattamentiExcel(List<Trattamento> trattamenti) throws IOException {

	String[] COLUMNs = { "idTrattamento", "idArnia", "descrizione", "dataTrattamento" };

	try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
	    Sheet sheet = workbook.createSheet("Trattamenti");
	    // Date cellStyle
	    CreationHelper createHelper = workbook.getCreationHelper();
	    CellStyle cellStyle = workbook.createCellStyle();
	    cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy/MM/dd"));

	    Font headerFont = workbook.createFont();
	    headerFont.setBold(true);
	    headerFont.setColor(IndexedColors.RED.getIndex());

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
	    for (Trattamento trattamento : trattamenti) {

		String formattedDate = trattamento.getDataTrattamento()
			.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		Row row = sheet.createRow(rowIdx++);
		row.createCell(0).setCellValue(trattamento.getIdTrattamento());
		row.createCell(1).setCellValue(trattamento.getArnia().getIdArnia());
		row.createCell(2).setCellValue(trattamento.getDescrizione());
		row.createCell(3).setCellValue(formattedDate);
	    }
	    workbook.write(out);
	    return new ByteArrayInputStream(out.toByteArray());
	}

    }

}
