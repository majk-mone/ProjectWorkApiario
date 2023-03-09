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

import apiario.model.Arnia;

public class PdfGeneratorArnia {
    private static final Logger logger = LoggerFactory.getLogger(PdfGeneratorArnia.class);

    public static ByteArrayInputStream arniaReport(List<Arnia> arnia) {

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

	    PdfPTable table = new PdfPTable(6);
	    table.setWidthPercentage(100);
	    table.setWidths(new int[] { 1, 3, 3, 3, 6, 3 });

	    PdfPCell hcell;
	    hcell = new PdfPCell(new Phrase("Id", headFont));
	    hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(hcell);

	    hcell = new PdfPCell(new Phrase("tipoRegina", headFont));
	    hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(hcell);

	    hcell = new PdfPCell(new Phrase("annoRegina", headFont));
	    hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(hcell);

	    hcell = new PdfPCell(new Phrase("annoAcquisto", headFont));
	    hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(hcell);

	    hcell = new PdfPCell(new Phrase("Indirizzo Apiario", headFont));
	    hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(hcell);

	    hcell = new PdfPCell(new Phrase("TipoArnia", headFont));
	    hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(hcell);

	    for (Arnia arnie : arnia) {
		PdfPCell cell;

		cell = new PdfPCell(new Phrase(arnie.getIdArnia().toString()));
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setPaddingBottom(5);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(arnie.getTipoRegina()));
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(arnie.getAnnoRegina().toString()));
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(arnie.getAnnoAcquisto().toString()));
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(arnie.getApiario().getIndirizzo()));
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);

		cell = new PdfPCell(new Phrase(arnie.getTipoArnia().getDescrizione().toString()));
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(cell);
	    }

	    Chunk chunk = new Chunk("Lista Arnie", titleFont);

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
