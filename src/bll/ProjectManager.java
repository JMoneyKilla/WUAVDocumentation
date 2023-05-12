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

    public List getUserProjects(int userId) throws SQLException {
        return projectDAO.getUserProjects(userId);
    }

    public void createProject(Project project) throws SQLException {
        projectDAO.createProject(project);
    }

    public void updateProject(Project project) throws SQLException {
        projectDAO.updateProject(project);
    }

    public void deleteProject(Project project) throws SQLException {
        projectDAO.deleteProject(project);
    }

    public void deleteProjectFromUserProject(Project project) throws SQLException{
        projectDAO.deleteFromProjectFromUserProject(project);
    }

    public void deleteMultipleProjects(List ids) throws SQLException {
        projectDAO.deleteMultipleProjects(ids);
    }

    public void deleteMultipleProjectsFromUserProject(List ids) throws SQLException {
        projectDAO.deleteMultipleProjectsFromUserProject(ids);
    }
}
