package be.documents;

import java.io.File;

public class DocumentBuilder {
    private int projectId;
    private int userId;
    private int documentId;
    private int documentType;
    private int refNumber;
    private String description;
    private File imageFile;
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
    public DocumentBuilder documentId(int documentId){
        this.documentId = documentId;
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
    public DocumentBuilder absolutePath(File imageFile){
        this.imageFile = imageFile;
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

    public IDocument build(int documentType){
        IDocument document = null;
        switch (documentType){
            case 1: document = buildDiagramDoc();
                    break;
            case 2: document = buildPictureDoc();
                    break;
            case 3: document = buildTextDoc();
                    break;
        }
        return document;
    }

    public IDocument buildDiagramDoc(){
        return new DiagramDoc(projectId, userId, documentId, documentType, refNumber, description, imageFile,
                documentName, dateAdded);
    }

    public IDocument buildPictureDoc(){
        return new PictureDoc(projectId, userId, documentId, documentType, refNumber, description, imageFile,
                documentName, dateAdded);
    }

    public IDocument buildTextDoc(){
        return new TextDoc(projectId, userId, documentId, documentType, description, documentName, dateAdded);
    }
}
