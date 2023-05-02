package bll;

import be.Project;

import java.util.List;

public class FacadeManager {
    private ProjectManager projectManager = new ProjectManager();

    public List<Project> getProjects() {
        return projectManager.getProjects();
    }
}
