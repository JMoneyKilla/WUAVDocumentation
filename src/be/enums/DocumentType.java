package be.enums;

public enum DocumentType {
    TEXT_DOC(3),
    PICTURE_DOC(2),
    DIAGRAM_DOC(1);
    final int documentType;
    DocumentType(int documentType){
        this.documentType = documentType;
    }
    public int getTypeId(){
        return this.documentType;
    }
}
