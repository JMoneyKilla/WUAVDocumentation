package dal;


import be.Project;

import static org.junit.jupiter.api.Assertions.*;

class ProjectDAOTest {

    @org.junit.jupiter.api.Test
    void deleteProject() {
        ProjectDAO projectDAO = new ProjectDAO();
        projectDAO.createProject(new Project());
        boolean expected = true;
        boolean actual = projectDAO.deleteProject();
        assertEquals(expected, actual);
    }
}