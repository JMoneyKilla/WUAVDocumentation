package be.documents;

public interface IDocument {
    int getProjectId();
    int getUserId();
    int getDocumentId();
    String getDescription();
    String getAbsolutePath();
    String getDocumentName();
    int getDocumentType();
    int getRefNumber();
    String getDateAdded();
}
