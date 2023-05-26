package bll.helpers;

import GUI.Models.ProjectModel;
import be.enums.DocumentType;
import be.Project;
import be.documents.IDocument;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.PDPageContentStream.AppendMode;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class PDFReportGenerator {
    ProjectModel projectModel = ProjectModel.getInstance();
    Project project;
    List<IDocument> documentList;
    float pageWidth = 0;
    float pageHeight = 0;
    int currentHeight = 20;

    public PDFReportGenerator(Project project, List<IDocument> documentList){
        this.project = project;
        this.documentList = documentList;
    }
    public void generatePDFProfessionel(){
        try {
            // Create a new PDF document
            PDDocument document = new PDDocument();

            // Create a new page
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            // Create a new content stream for adding content
            PDPageContentStream contentStream = new PDPageContentStream(document, page, AppendMode.APPEND, true);
            initPDFReport(contentStream, project, page, document);
            page = new PDPage(PDRectangle.A4);
            document.addPage(page);
            contentStream.close();
            contentStream = new PDPageContentStream(document, page);
            currentHeight = 20;
            int docNum = 1;

            // Add an image to the page
            for (IDocument d: documentList) {
                if(docNum > 4){
                    page = new PDPage(PDRectangle.A4);
                    document.addPage(page);
                    contentStream.close();
                    contentStream = new PDPageContentStream(document, page);
                    currentHeight = 20;
                    docNum = 1;
                }
                if(d.getDocumentType() == DocumentType.DIAGRAM_DOC || d.getDocumentType() == DocumentType.PICTURE_DOC){
                    pdfPicDiaGen(contentStream, d, document);
                }
                if (d.getDocumentType() == DocumentType.TEXT_DOC) {
                    pdfTextDocGen(contentStream, d);
                }
                docNum++;
                currentHeight += 200;
            }
            // Close the content stream
            contentStream.close();
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
    public void generatePDFSimple(){
        try {
            // Create a new PDF document
            PDDocument document = new PDDocument();
            // Create a new page
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            // Create a new content stream for adding content
            PDPageContentStream contentStream = new PDPageContentStream(document, page, AppendMode.APPEND, true);

            initPDFReport(contentStream, project, page, document);

            page = new PDPage(PDRectangle.A4);
            document.addPage(page);
            contentStream.close();
            contentStream = new PDPageContentStream(document, page);
            currentHeight = 20;
            int docNum = 1;

            // Add an image to the page
            for (IDocument d: documentList) {
                if(docNum > 4){
                    page = new PDPage(PDRectangle.A4);
                    document.addPage(page);
                    contentStream.close();
                    contentStream = new PDPageContentStream(document, page);
                    currentHeight = 20;
                    docNum = 1;
                }
                if(d.getDocumentType() == DocumentType.PICTURE_DOC){
                    pdfPicDiaGen(contentStream, d, document);
                    docNum++;
                    currentHeight += 200;
                }
                if(d.getDocumentType() == DocumentType.DIAGRAM_DOC){
                    continue;
                }
                if (d.getDocumentType() == DocumentType.TEXT_DOC) {
                    pdfTextDocGen(contentStream, d);
                    docNum++;
                    currentHeight += 200;
                }
            }
            contentStream.close();

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

    public void pdfTextDocGen(PDPageContentStream contentStream, IDocument d) throws IOException {
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 15);
        contentStream.beginText();
        contentStream.newLineAtOffset(50, pageHeight-currentHeight-20);
        contentStream.showText(d.getDocumentName());
        String[] splitAtNewLine = d.getDescription().split("\n");
        for (String s : splitAtNewLine) {
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.newLineAtOffset(0, -13);
            contentStream.showText(s);
        }
        contentStream.endText();
    }

    public void pdfPicDiaGen(PDPageContentStream contentStream, IDocument d, PDDocument document) throws IOException {
        PDImageXObject pdfImage = PDImageXObject.createFromFile(d.getImageFile().getPath(), document);
        contentStream.drawImage(pdfImage, 50, pageHeight-currentHeight-pageWidth/3, pageWidth/3, pageWidth/3);
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 15);
        contentStream.beginText();
        contentStream.newLineAtOffset(pageWidth/3+60, pageHeight-currentHeight-20);
        contentStream.showText(d.getDocumentName());
        String[] splitAtNewLine = d.getDescription().split("\n");
        for (String s : splitAtNewLine) {
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.newLineAtOffset(0, -13);
            contentStream.showText(s);
        }
        contentStream.endText();
    }

    public void initPDFReport(PDPageContentStream contentStream, Project project, PDPage page, PDDocument document) throws IOException {
        String logoImagePath = "resource/icons/logo.png";
        String absoluteImagePath = getClass().getClassLoader().getResource(logoImagePath).getPath();


        PDImageXObject logoImage = PDImageXObject.createFromFile(absoluteImagePath, document);
        // Draw the company logo at the top left corner
        contentStream.drawImage(logoImage, (page.getMediaBox().getWidth()- 384)/2, (page.getMediaBox().getHeight()-192)/2, 384, 192);

        String name = project.getName();
        String customerName = project.getCustomerName();
        String companyAddress = project.getCompanyAddress();
        int zipCode = project.getZipCode();

        // Get the width and height of the page
        pageWidth = page.getMediaBox().getWidth();
        pageHeight = page.getMediaBox().getHeight();

        // Calculate the width of the text
        float textWidth = PDType1Font.HELVETICA.getStringWidth(name) / 1000 * 25;

        // Calculate the x-coordinate for centering the text
        float centerX = (pageWidth - textWidth) / 2;

        // Calculate the y-coordinate for placing the text
        float y = pageHeight - 50;

        // Add text to the page
        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 25);
        contentStream.newLineAtOffset(centerX, y);
        contentStream.showText(name);
        contentStream.endText();



        // Set the font and font size
        contentStream.setFont(PDType1Font.HELVETICA, 12);

        // Set the initial starting position
        float startX = 50;
        float startY = 700;

        // Add text with line breaks
        contentStream.beginText();
        contentStream.newLineAtOffset(startX, startY);
        contentStream.showText("Customer name: " + customerName);
        contentStream.newLineAtOffset(0, -20);
        contentStream.showText("Customer address: " + companyAddress + ", " + zipCode);

        contentStream.newLineAtOffset(0, -550);
        contentStream.showText("Contact Us:");
        contentStream.newLineAtOffset(0, -30);
        contentStream.showText("Phone: " + project.getPhoneNumber());
        contentStream.newLineAtOffset(0, -30);
        contentStream.showText("Email: " + project.getCustomerEmail());
        contentStream.endText();

    }
}

