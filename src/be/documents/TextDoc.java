package be.documents;

public class TextDoc implements IDocument{
    private int projectId;
    private int userId;
    private int documentId;
    private int documentType;
    private String description;
    private String documentName;
    private String dateAdded;
    TextDoc(int projectId, int userId, int documentId, int documentType,
            String description, String documentName, String dateAdded){
        this.projectId = projectId;
        this.userId = userId;
        this.documentId = documentId;
        this.documentType = documentType;
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

    public int getDocumentType() {
        return documentType;
    }

    @Override
    public int getRefNumber() {
        return -4;
    }

    public void setDocumentType(int documentType) {
        this.documentType = documentType;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String getAbsolutePath() {
        return null;
    }

    public void setDescription(String description) {
        this.description = description;
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
