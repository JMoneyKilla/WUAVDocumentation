package be.documents;

import java.io.File;

public class PictureDoc implements IDocument{
    private int projectId;
    private int userId;
    private int documentId;
    private int documentType;
    private int refNumber;
    private String description;
    private File imageFile;
    private String documentName;
    private String dateAdded;
    PictureDoc(int projectId, int userId, int documentId, int documentType, int refNumber,
               String description, File imageFile, String documentName, String dateAdded){
        this.projectId = projectId;
        this.userId = userId;
        this.documentId = documentId;
        this.documentType = documentType;
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

    public int getDocumentType() {
        return documentType;
    }

    public void setDocumentType(int documentType) {
        this.documentType = documentType;
    }

    public int getRefNumber() {
        return refNumber;
    }

    public void setRefNumber(int refNumber) {
        this.refNumber = refNumber;
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

    public void setImageFile(File imageFile) {
        this.imageFile = imageFile;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }
}
