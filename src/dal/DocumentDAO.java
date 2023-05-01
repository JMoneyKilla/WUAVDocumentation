package dal;

import be.Project;
import be.documents.DocumentBuilder;
import be.documents.IDocument;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DocumentDAO {
    DataBaseConnection dbc = DataBaseConnection.getInstance();
    public List<IDocument> getAllProjectDocuments(int id) throws SQLException {
        List<IDocument> projectDocuments = new ArrayList<>();
        DocumentBuilder documentBuilder = new DocumentBuilder();
        String sql = "SELECT * FROM Document WHERE project_id = " + id + ";";
        try (Connection connection = dbc.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int projectId = rs.getInt("project_id");
                int userId = rs.getInt("user_id");
                int documentId = rs.getInt("document_id");
                String description = rs.getString("description");
                String absolutePath = rs.getString("absolute_path");
                String documentName = rs.getString("document_name");
                int documentType = rs.getInt("document_type");
                int refNumber = rs.getInt("ref_num");
                String dateAdded = rs.getString("date_added");
                documentBuilder.projectId(projectId).userId(userId).documentId(documentId).description(description).
                        absolutePath(absolutePath).documentName(documentName).documentType(documentType).
                        refNumber(refNumber).dateAdded(dateAdded);
                IDocument document = documentBuilder.build(documentType);
                projectDocuments.add(document);
            }
        }
        return projectDocuments;
    }
    public boolean deleteDocument(IDocument document) throws SQLException{
        try (Connection con = dbc.getConnection()) {
            int id = document.getDocumentId();
            String sql = "DELETE FROM Document WHERE (document_id=?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int result = ps.executeUpdate();
            if (result > 0)
                return true;
        }
        return false;
    }
    public void updateDocument(IDocument document) throws SQLException{
        int id = document.getDocumentId();
        String description = document.getDescription();
        String absolutePath = document.getAbsolutePath();
        String documentName = document.getDocumentName();
        int refNumber = document.getRefNumber();
        String dateAdded = document.getDateAdded();

        String sql = "Update Document SET description = ?, absolute_path = ?, document_name = ?,\n" +
                "ref_num = ?, date_added = ? WHERE document_id = ?";

        try (Connection con = dbc.getConnection();) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, description);
            ps.setString(2, absolutePath);
            ps.setString(3, documentName);
            ps.setInt(4, refNumber);
            ps.setString(5, dateAdded);
            ps.setInt(6, id);
            ps.execute();
        }
    }
    public void createDocument(IDocument document) throws SQLException{
        int projectId = document.getProjectId();
        int userId = document.getUserId();
        int documentId = document.getDocumentId();
        String description = document.getDescription();
        String absolutePath = document.getAbsolutePath();
        String documentName = document.getDocumentName();
        int documentType = document.getDocumentType();
        int refNumber = document.getRefNumber();
        String dateAdded = document.getDateAdded();

        String sql = "INSERT INTO Document (project_id, user_id, document_id, description, absolute_path," +
                "document_name, document_type, ref_num, date_added)" +
                " VALUES (?,?,?,?,?,?,?,?,?)";

        try (Connection con = dbc.getConnection();) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, projectId);
            ps.setInt(2, userId);
            ps.setInt(3, documentId);
            ps.setString(4, description);
            ps.setString(5, absolutePath);
            ps.setString(6, documentName);
            ps.setInt(7, documentType);
            ps.setInt(8, refNumber);
            ps.setString(9, dateAdded);
            ps.execute();
        }
    }
}
