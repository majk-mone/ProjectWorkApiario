package apiario.utility;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import apiario.model.Apiario;

public class PdfGeneratorApiario {

    private static final Logger logger = LoggerFactory.getLogger(PdfGeneratorApiario.class);

    public static ByteArrayInputStream apiarioReport(List<Apiario> apiario) {

	Document document = new Document();
	ByteArrayOutputStream out = new ByteArrayOutputStream();

	try {
	    PdfWriter.getInstance(document, out);
	    document.open();

	    Font titleFont = FontFactory.getFont(FontFactory.TIMES_BOLDITALIC);
	    titleFont.setColor(255, 0, 0);
	    titleFont.isBold();
	    titleFont.setSize(25);

	    Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
	    headFont.setColor(255, 0, 0);

	    PdfPTable table = new PdfPTable(4);
	    table.setWidthPercentage(100);
	    table.setWidths(new int[] { 1, 3, 3, 3 });

	    PdfPCell hcell;
	    hcell = new PdfPCell(new Phrase("Id", headFont));
	    hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    hcell.setPaddingBottom(5);
	    table.addCell(hcell);

	    hcell = new PdfPCell(new Phrase("indirizzo", headFont));
	    hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(hcell);

	    hcell = new PdfPCell(new Phrase("longitudine", headFont));
	    hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(hcell);

	    hcell = new PdfPCell(new Phrase("latitudine", headFont));
	    hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(hcell);

	    for (Apiario apiari : apiario) {

		PdfPCell cell;
		cell = new PdfPCell(new Phrase(apiari.getIdApiario().toString()));
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setPaddingBottom(5);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(apiari.getIndirizzo()));
		cell.setPaddingLeft(5);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(apiari.getLongitudine().toString()));
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setPaddingRight(5);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(apiari.getLatitudine().toString()));
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setPaddingRight(5);
		table.addCell(cell);
	    }
	    Chunk chunk = new Chunk("Lista Apiari", titleFont);

	    chunk.setBackground(BaseColor.LIGHT_GRAY);
	    com.itextpdf.text.Paragraph titolo3 = new com.itextpdf.text.Paragraph(chunk);
	    titolo3.setAlignment(Element.ALIGN_CENTER);
	    document.add(titolo3);

	    document.add(Chunk.NEWLINE);

	    document.add(table);
	    document.close();

	} catch (DocumentException ex) {

	    logger.error("Error occurred: {0}", ex);
	}

	return new ByteArrayInputStream(out.toByteArray());
    }
}
