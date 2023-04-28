package be.documents;

public class DocumentBuilder {
    private int projectId;
    private int userId;
    private int documentType;
    private int refNumber;
    private String description;
    private String absolutePath;
    private String documentName;
    private String dateAdded;

    public DocumentBuilder projectId(int projectId){
        this.projectId = projectId;
        return this;
    }
    public DocumentBuilder userId(int userId){
        this.userId = userId;
        return this;
    }
    public DocumentBuilder documentType(int documentType){
        this.documentType = documentType;
        return this;
    }
    public DocumentBuilder refNumber(int refNumber){
        this.refNumber = refNumber;
        return this;
    }
    public DocumentBuilder description(String description){
        this.description = description;
        return this;
    }
    public DocumentBuilder absolutePath(String absolutePath){
        this.absolutePath = absolutePath;
        return this;
    }
    public DocumentBuilder documentName(String documentName){
        this.documentName = documentName;
        return this;
    }
    public DocumentBuilder dateAdded(String dateAdded){
        this.dateAdded = dateAdded;
        return this;
    }

    public IDocument buildDiagramDoc(){
        return new DiagramDoc(projectId, userId, documentType, refNumber, description, absolutePath,
                documentName, dateAdded);
    }
    public IDocument buildPictureDoc(){
        return new PictureDoc(projectId, userId, documentType, refNumber, description, absolutePath,
                documentName, dateAdded);
    }
    public IDocument buildTextDoc(){
        return new TextDoc(projectId, userId, documentType, description, documentName, dateAdded);
    }
}
