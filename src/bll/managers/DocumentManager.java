package bll.managers;

import be.documents.IDocument;
import dal.DocumentDAO;

import java.sql.SQLException;
import java.util.List;

public class DocumentManager {
   private DocumentDAO documentDAO = new DocumentDAO();
    public List<IDocument> getAllProjectDocuments(int id) throws SQLException {
        return documentDAO.getAllProjectDocuments(id);
    }
    public boolean deleteDocument(IDocument document) throws SQLException{
        return documentDAO.deleteDocument(document);
    }
    public void updateDocument(IDocument document) throws SQLException{
        documentDAO.updateDocument(document);
    }
    public void createDocument(IDocument document) throws SQLException{
        documentDAO.createDocument(document);
    }
}
