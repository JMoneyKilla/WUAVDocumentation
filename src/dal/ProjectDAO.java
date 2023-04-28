package dal;

import be.Project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAO {

    DataBaseConnection dbc = DataBaseConnection.getInstance();

    public List<Project> getAllProjects() throws SQLException {
        List<Project> allProjects = new ArrayList<>();
        String sql = "SELECT * FROM Project";
        try (Connection connection = dbc.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("project_id");
                String name = rs.getString("project_name");
                String dateLastVisited = rs.getString("date_last_visited");
                String customerName = rs.getString("customer_name");
                String companyName = rs.getString("company_name");
                int type = rs.getInt("company_type");
                Project project = new Project(id, name, dateLastVisited, customerName, companyName, type);
                allProjects.add(project);
            }
        }
        return allProjects;
    }
}
