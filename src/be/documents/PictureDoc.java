package be.documents;

public class PictureDoc implements IDocument{
    private int projectId;
    private int userId;
    private int documentType;
    private int refNumber;
    private String description;
    private String absolutePath;
    private String documentName;
    private String dateAdded;
    PictureDoc(int projectId, int userId, int documentType, int refNumber,
                      String description, String absolutePath, String documentName, String dateAdded){
        this.projectId = projectId;
        this.userId = userId;
        this.documentType = documentType;
        this.refNumber = refNumber;
        this.description = description;
        this.absolutePath = absolutePath;
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

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
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
