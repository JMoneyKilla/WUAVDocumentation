package be.documents;

public class DiagramDoc implements IDocument{
    private int projectId;
    private int userId;
    private int documentType;
    private int refNumber;
    private String description;
    private String absolutePath;
    private String documentName;
    private String dateAdded;
    DiagramDoc(int projectId, int userId, int documentType, int refNumber,
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
}
