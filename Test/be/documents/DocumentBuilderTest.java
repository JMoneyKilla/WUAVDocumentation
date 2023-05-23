package be.documents;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DocumentBuilderTest {


    @Test
    void testBuildDiagramDoc() {
        DocumentBuilder documentBuilder = new DocumentBuilder();
        documentBuilder.projectId(1);
        documentBuilder.userId(2);
        documentBuilder.documentType(1);
        documentBuilder.refNumber(2338);
        documentBuilder.description("diagram document");
        documentBuilder.absolutePath(null);
        documentBuilder.documentName("Sound diagram");
        documentBuilder.dateAdded("01/01/2021");
        DiagramDoc diagramDoc = (DiagramDoc) documentBuilder.buildDiagramDoc();
        assertEquals(1, diagramDoc.getProjectId());
        assertEquals(2, diagramDoc.getUserId());
        assertEquals(1, diagramDoc.getDocumentType());
        assertEquals(2338, diagramDoc.getRefNumber());
        assertEquals("diagram document", diagramDoc.getDescription());
        assertNull(diagramDoc.getImageFile());
        assertEquals("Sound diagram", diagramDoc.getDocumentName());
        assertEquals("01/01/2021", diagramDoc.getDateAdded());
    }

    @Test
    void testBuildPictureDoc() {
        DocumentBuilder documentBuilder = new DocumentBuilder();
        documentBuilder.projectId(1);
        documentBuilder.userId(2);
        documentBuilder.documentType(2);
        documentBuilder.refNumber(2338);
        documentBuilder.description("picture document");
        documentBuilder.absolutePath(null);
        documentBuilder.documentName("Speaker picture");
        documentBuilder.dateAdded("01/01/2021");
        PictureDoc pictureDoc = (PictureDoc) documentBuilder.buildPictureDoc();
        assertEquals(1, pictureDoc.getProjectId());
        assertEquals(2, pictureDoc.getUserId());
        assertEquals(2, pictureDoc.getDocumentType());
        assertEquals(2338, pictureDoc.getRefNumber());
        assertEquals("picture document", pictureDoc.getDescription());
        assertNull(pictureDoc.getImageFile());
        assertEquals("Speaker picture", pictureDoc.getDocumentName());
        assertEquals("01/01/2021", pictureDoc.getDateAdded());
    }

    @Test
    void testBuildTextDoc() {
        DocumentBuilder documentBuilder = new DocumentBuilder();
        documentBuilder.projectId(1);
        documentBuilder.userId(2);
        documentBuilder.documentType(3);
        documentBuilder.description("documentation in text form");
        documentBuilder.documentName("text document");
        documentBuilder.dateAdded("01/01/2021");
        TextDoc textDoc = (TextDoc) documentBuilder.buildTextDoc();
        assertEquals(1, textDoc.getProjectId());
        assertEquals(2, textDoc.getUserId());
        assertEquals(3, textDoc.getDocumentType());
        assertEquals("documentation in text form", textDoc.getDescription());
        assertEquals("text document", textDoc.getDocumentName());
        assertEquals("01/01/2021", textDoc.getDateAdded());
    }
}