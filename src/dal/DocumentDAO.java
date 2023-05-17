package dal;

import be.documents.DocumentBuilder;
import be.documents.IDocument;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DocumentDAO {
    DataBaseConnection dbc = DataBaseConnection.getInstance();
    public List<IDocument> getAllProjectDocuments(int id) throws SQLException, IOException {
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
                byte[] imageBytes = rs.getBytes("absolute_path");
                String documentName = rs.getString("document_name");
                File imageFile = new File("savedImages/" + documentName + "_" + documentId + ".png");
                Files.write(imageFile.toPath(), imageBytes);
                int documentType = rs.getInt("document_type");
                int refNumber = rs.getInt("ref_num");
                String dateAdded = rs.getString("date_added");
                documentBuilder.projectId(projectId).userId(userId).documentId(documentId).description(description).
                        absolutePath(imageFile).documentName(documentName).documentType(documentType).
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
    public void updateDocument(IDocument document) throws SQLException, IOException {
        int id = document.getDocumentId();
        String description = document.getDescription();
        byte[] absolutePath = Files.readAllBytes(document.getImageFile().toPath());        String documentName = document.getDocumentName();
        int refNumber = document.getRefNumber();
        String dateAdded = document.getDateAdded();

        String sql = "Update Document SET description = ?, absolute_path = ?, document_name = ?,\n" +
                "ref_num = ?, date_added = ? WHERE document_id = ?";

        try (Connection con = dbc.getConnection();) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, description);
            ps.setBytes(2, absolutePath);
            ps.setString(3, documentName);
            ps.setInt(4, refNumber);
            ps.setString(5, dateAdded);
            ps.setInt(6, id);
            ps.execute();
        }
    }
    public void createDocument(IDocument document) throws SQLException, IOException {
        int projectId = document.getProjectId();
        int userId = document.getUserId();
        String description = document.getDescription();
        byte[] absolutePath = Files.readAllBytes(document.getImageFile().toPath());
        String documentName = document.getDocumentName();
        int documentType = document.getDocumentType();
        int refNumber = document.getRefNumber();
        String dateAdded = document.getDateAdded();

        String sql = "INSERT INTO Document (project_id, user_id, description, absolute_path," +
                "document_name, document_type, ref_num, date_added)" +
                " VALUES (?,?,?,?,?,?,?,?)";

        try (Connection con = dbc.getConnection();) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, projectId);
            ps.setInt(2, userId);
            ps.setString(3, description);
            ps.setBytes(4, absolutePath);
            ps.setString(5, documentName);
            ps.setInt(6, documentType);
            ps.setInt(7, refNumber);
            ps.setString(8, dateAdded);
            ps.execute();
        }
    }
}
