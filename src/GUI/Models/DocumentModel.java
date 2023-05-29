package GUI.Models;

import be.documents.IDocument;
import be.handlers.AlertBoxStrategy;
import be.handlers.IOAlertStrategy;
import be.handlers.SQLAlertStrategy;
import bll.managers.FacadeManager;
import java.io.IOException;
import java.sql.SQLException;

public class DocumentModel {

    FacadeManager documentManager = new FacadeManager();
    AlertBoxStrategy alertBoxStrategy;

    public boolean deleteDocument(IDocument document){
        try {
            return documentManager.deleteDocument(document);
        } catch (SQLException e) {
            alertBoxStrategy = new SQLAlertStrategy();
            alertBoxStrategy.showCustomAlert("An error occured while trying to delete document. " +
                    "Check that your internet connection is stable and contact your system administrator");
        }
        return false;
    }
    public void updateDocument(IDocument document){
        try {
            documentManager.updateDocument(document);
        } catch (SQLException e) {
            alertBoxStrategy = new SQLAlertStrategy();
            alertBoxStrategy.showCustomAlert("An error occured while trying to update document. " +
                    "Check that your internet connection is stable and contact your system administrator");        } catch (IOException e) {
            alertBoxStrategy = new IOAlertStrategy();
            alertBoxStrategy.showGenericAlert(e);        }
    }
    public void createDocument(IDocument document){
        try {
            documentManager.createDocument(document);
        } catch (SQLException  e) {
            alertBoxStrategy = new SQLAlertStrategy();
            alertBoxStrategy.showCustomAlert("An error occured while trying to create new document. " +
                    "Check that your internet connection is stable and contact your system administrator");
        } catch (IOException e) {
            alertBoxStrategy = new IOAlertStrategy();
            alertBoxStrategy.showGenericAlert(e);
        }
    }
}
