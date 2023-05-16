package bll.helpers;

import be.Project;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.awt.*;
import java.io.IOException;
public class PDFReportGenerator {
    public void generatePDF(Project project){
        try {
            // Create a new PDF document
            PDDocument document = new PDDocument();

            // Create a new page
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            // Create a new content stream for adding content
            PDPageContentStream contentStream = new PDPageContentStream(document, page, AppendMode.APPEND, true);
            String name = project.getName();
            String customerName = project.getCustomerName();
            String companyAddress = project.getCompanyAddress();
            int zipCode = project.getZipCode();

            // Get the width and height of the page
            float pageWidth = page.getMediaBox().getWidth();
            float pageHeight = page.getMediaBox().getHeight();

            // Calculate the width of the text
            String titleText = "Report: " + name;
            float textWidth = PDType1Font.HELVETICA.getStringWidth(titleText) / 1000 * 25;

            // Calculate the x-coordinate for centering the text
            float centerX = (pageWidth - textWidth) / 2;

            // Calculate the y-coordinate for placing the text
            float y = pageHeight - 50;

            // Add text to the page
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 25);
            contentStream.newLineAtOffset(centerX, y);
            contentStream.showText(titleText);
            contentStream.endText();



            // Set the font and font size
            contentStream.setFont(PDType1Font.HELVETICA, 12);

            // Set the initial starting position
            float startX = 50;
            float startY = 750;

            // Add text with line breaks
            contentStream.beginText();
            contentStream.newLineAtOffset(startX, startY);
            contentStream.showText("Customer name: " + customerName);
            contentStream.newLineAtOffset(0, -15);
            contentStream.showText("Company address: " + companyAddress + ", " + zipCode);
            contentStream.endText();

            // Add an image to the page
            PDImageXObject image = PDImageXObject.createFromFile("Reports/test.jpg", document);
            contentStream.drawImage(image, 0, 50, pageWidth, pageHeight-200);


            // Close the content stream
            contentStream.close();

            // Create a new page
            PDPage secondPage = new PDPage(PDRectangle.A4);
            document.addPage(secondPage);

            // Create a content stream for the second page
            PDPageContentStream contentStream2 = new PDPageContentStream(document, secondPage);

            // Add text and images to the second page
            contentStream2.beginText();
            contentStream2.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream2.newLineAtOffset(50, 700);
            contentStream2.showText("Second Page");
            contentStream2.endText();
            contentStream2.close();

            // Save the document to a file
            document.save("Reports/report.pdf");

            // Closes the document and opens it
            document.close();
            Desktop desktop = Desktop.getDesktop();
            desktop.open(new java.io.File("Reports/report.pdf"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

