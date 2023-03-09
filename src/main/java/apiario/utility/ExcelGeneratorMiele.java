package apiario.utility;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import apiario.model.ProduzioneMiele;

public class ExcelGeneratorMiele {

    public static ByteArrayInputStream mieleExcel(List<ProduzioneMiele> miele) throws IOException {

	String[] COLUMNs = { "idProduzione", "dataProduzione", "idArnia", "idTipoMiele", "quantita" };

	try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {

	    Sheet sheet = workbook.createSheet("Miele");

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
	    for (ProduzioneMiele proMiele : miele) {

		String formattedDate = proMiele.getDataProduzione().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		Row row = sheet.createRow(rowIdx++);

		row.createCell(0).setCellValue(proMiele.getIdProduzione());
		row.createCell(1).setCellValue(formattedDate);
		row.createCell(2).setCellValue(proMiele.getArnia().getIdArnia());
		row.createCell(3).setCellValue(proMiele.getTipoMiele().getDescrizione());
		row.createCell(4).setCellValue(proMiele.getQuantita());

	    }
	    workbook.write(out);
	    return new ByteArrayInputStream(out.toByteArray());
	}
    }
}
