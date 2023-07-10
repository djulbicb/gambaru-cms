package com.gambaru.gambaru.cms.application.service.generators;

import java.awt.image.BufferedImage;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class PDFGenerator {
    public static byte[] generatePDF(List<BufferedImage> images, String outputFile) {
        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            PdfWriter.getInstance(document, outputStream);
            document.open();

            PdfPTable table = new PdfPTable(1);
            table.setWidthPercentage(20);
            document.add(table);

            for (BufferedImage image : images) {
                Image pdfImage = Image.getInstance(image, null);
//                pdfImage.setAlignment(Element.ALIGN_CENTER);
//                pdfImage.setSpacingAfter(0f);
//                pdfImage.setSpacingBefore(0f);
//                pdfImage.setPaddingTop(0f);
//                document.add(pdfImage);

                // Add text to the PDF
                Chunk chunk = new Chunk(Math.random() + "", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL));
//                Paragraph paragraph = new Paragraph(chunk);
//                paragraph.setAlignment(Element.ALIGN_CENTER);
//                paragraph.setSpacingBefore(0f);
//                paragraph.setSpacingAfter(0f);
//                document.add(paragraph);

                table.addCell(pdfImage);
                table.addCell(Math.random() + "");
            }
            document.add(table);

            document.close();
            System.out.println("PDF generated successfully.");
        } catch (DocumentException | IOException e) {
            System.out.println("PDF generation failed. Exception: " + e.getMessage());
        }
        return outputStream.toByteArray();
    }
}
