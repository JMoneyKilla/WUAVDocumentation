package be.documents;

import be.enums.DocumentType;

import java.io.File;

public class TextDoc implements IDocument{
    private int projectId;
    private int userId;
    private int documentId;
    private DocumentType documentType = DocumentType.TEXT_DOC;
    private String description;
    private String documentName;
    private String dateAdded;
    TextDoc(int projectId, int userId, int documentId,
            String description, String documentName, String dateAdded){
        this.projectId = projectId;
        this.userId = userId;
        this.documentId = documentId;
        this.description = description;
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

    @Override
    public int getRefNumber() {
        return -4;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public File getImageFile() {
        return null;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDocumentName() {
        return documentName;
    }

    public String getDateAdded() {
        return dateAdded;
    }
}
