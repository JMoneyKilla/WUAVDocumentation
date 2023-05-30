package be.documents;

import be.enums.DocumentType;

import java.io.File;

public class DiagramDoc implements IDocument{
    private int projectId;
    private int userId;
    private int documentId;
    private DocumentType documentType = DocumentType.DIAGRAM_DOC;
    private int refNumber;
    private String description;
    private File imageFile;
    private String documentName;
    private String dateAdded;
    DiagramDoc(int projectId, int userId, int documentId, int refNumber,
               String description, File imageFile, String documentName, String dateAdded){
        this.projectId = projectId;
        this.userId = userId;
        this.documentId = documentId;
        this.refNumber = refNumber;
        this.description = description;
        this.imageFile = imageFile;
        this.documentName = documentName;
        this.dateAdded = dateAdded;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getUserId() {
        return userId;
    }

    @Override
    public int getDocumentId() {
        return documentId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public int getRefNumber() {
        return refNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public File getImageFile() {
        return imageFile;
    }

    public String getDocumentName() {
        return documentName;
    }

    public String getDateAdded() {
        return dateAdded;
    }
}
