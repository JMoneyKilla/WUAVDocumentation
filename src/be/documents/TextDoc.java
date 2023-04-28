package be.documents;

public class TextDoc implements IDocument{
    private int projectId;
    private int userId;
    private int documentType;
    private String description;
    private String documentName;
    private String dateAdded;
    TextDoc(int projectId, int userId, int documentType,
                      String description, String documentName, String dateAdded){
        this.projectId = projectId;
        this.userId = userId;
        this.documentType = documentType;
        this.description = description;
        this.documentName = documentName;
        this.dateAdded = dateAdded;
    }

}
