package GUI.Models;

import be.documents.IDocument;
import bll.FacadeManager;

import java.io.IOException;
import java.sql.SQLException;

public class DocumentModel {

    FacadeManager documentManager = new FacadeManager();

    public boolean deleteDocument(IDocument document){
        try {
            return documentManager.deleteDocument(document);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateDocument(IDocument document){
        try {
            documentManager.updateDocument(document);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void createDocument(IDocument document){
        try {
            documentManager.createDocument(document);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
