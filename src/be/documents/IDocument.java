package be.documents;

import java.io.File;

public interface IDocument {
    int getProjectId();
    int getUserId();
    int getDocumentId();
    String getDescription();
    File getImageFile();
    String getDocumentName();
    int getDocumentType();
    int getRefNumber();
    String getDateAdded();
}
