package be.documents;

import be.enums.DocumentType;

import java.io.File;

public interface IDocument {
    int getProjectId();
    int getUserId();
    int getDocumentId();
    String getDescription();
    File getImageFile();
    String getDocumentName();
    DocumentType getDocumentType();
    int getRefNumber();
    String getDateAdded();
}
