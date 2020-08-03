package papeleria_legado;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import papeleria_legado.Models.Sell_Detail;
import javafx.collections.ObservableList;

public class GenerateTicket {

	public static boolean exportPDF(String titlePDF, ObservableList<Sell_Detail> listDetails)
			throws DocumentException, MalformedURLException, IOException {
		String name = System.getProperty("user.home") + "/Desktop/" + titlePDF + ".pdf";
		Document pdf = new Document();
		PdfWriter.getInstance(pdf, new FileOutputStream(name));
		pdf.open();
		com.itextpdf.text.Image logo = com.itextpdf.text.Image
				.getInstance(System.getProperty("user.dir") + "/src/assets/images/legado_papeleria.jpeg");
		logo.scaleToFit(100, 100);
		logo.setAlignment(Chunk.ALIGN_LEFT);
		logo.setAbsolutePosition(70, 720);
		Paragraph title = new Paragraph(titlePDF);
		title.setAlignment(Element.ALIGN_CENTER);
		pdf.add(new Paragraph(" "));
		pdf.add(new Paragraph(" "));
		pdf.add(title);
		pdf.add(logo);
		pdf.add(new Paragraph(" "));
		pdf.add(new Paragraph(" "));
		PdfPTable table = new PdfPTable(3);
		PdfPCell c1 = new PdfPCell(new Phrase("Cantidad"));
		table.addCell(c1);
		c1 = new PdfPCell(new Phrase("Producto"));
		table.addCell(c1);
		c1 = new PdfPCell(new Phrase("Importe"));
		table.addCell(c1);
		table.setHeaderRows(1);
		for (Sell_Detail item : listDetails) {
			table.addCell(String.valueOf(item.getQuantity()));
			table.addCell(String.valueOf(item.getName()));
			table.addCell(String.valueOf(item.getPrice()));
		}
		pdf.add(table);
		Paragraph money = new Paragraph();
		money.setAlignment(Element.ALIGN_RIGHT);
		for (Sell_Detail item : listDetails) {
			money.add("Subtotal $" + String.valueOf(item.getSubtotal()));
			money.add(new Paragraph(" "));
			money.add("Total $" + String.valueOf(item.getTotal()));
		}
		pdf.add(new Paragraph(" "));
		pdf.add(new Paragraph(" "));
		pdf.add(money);
		pdf.add(new Paragraph(" "));
		pdf.add(new Paragraph(" "));
		Paragraph ty = new Paragraph();
		ty.setAlignment(Element.ALIGN_CENTER);
		ty.add("Gracias por tu compra");
		ty.add(new Paragraph(" "));
		ty.add("Papelería Legado");
		pdf.add(ty);
		pdf.close();
		return true;
	}
}
