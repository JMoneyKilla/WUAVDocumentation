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

    public void createProject(Project project) throws SQLException{
        String name = project.getName();
        String dateLastVisited = project.dateLastVisited();
        String customerName = project.getCustomerName();
        String companyName = project.getCompanyName();
        int companyType = project.getCompanyType();

        String sql = "INSERT INTO Project (project_name, date_last_visited, customer_name, company_name, company_type,)" +
                " VALUES (?,?,?,?,?)";

        try (Connection con = dbc.getConnection();) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, dateLastVisited);
            ps.setString(3, customerName);
            ps.setString(4, companyName);
            ps.setInt(5, companyType);
            ps.execute();
        }
    }

    public boolean deleteProject(Project project) throws SQLException{
        try (Connection con = dbc.getConnection()) {
            int id = project.getId();
            String sql = "DELETE FROM Project WHERE (id=?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int result = ps.executeUpdate();
            if (result > 0)
                return true;
        }
        return false;
    }

    public void updateProject(Project project) throws SQLException{
        int id = project.getId();
        String name = project.getName();
        String dateLastVisited = project.dateLastVisited();
        String customerName = project.getCustomerName();
        String companyName = project.getCompanyName();
        int companyType = project.getCompanyType();

        String sql = "Update Project SET project_name = ?, date_last_visited = ?, customer_name = ?,\n" +
                "company_name = ?, company_type = ? WHERE id = ?";

        try (Connection con = dbc.getConnection();) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, dateLastVisited);
            ps.setString(3, customerName);
            ps.setString(4, companyName);
            ps.setInt(5, companyType);
            ps.setInt(6, id);
            ps.execute();
        }
    }
}
