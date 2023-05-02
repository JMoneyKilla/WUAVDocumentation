package bll;

import be.Project;
import dal.ProjectDAO;

import java.sql.SQLException;
import java.util.List;

public class ProjectManager {
    ProjectDAO projectDAO = new ProjectDAO();

    public List<Project> getProjects() {
        try {
            return projectDAO.getAllProjects();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
