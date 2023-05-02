package GUI.Models;

import be.Project;
import bll.FacadeManager;
import javafx.collections.ObservableList;

import java.util.List;

public class ProjectModel {
    private static ProjectModel instance;
    private ObservableList<Project> projects;
    FacadeManager facadeManager = new FacadeManager();

    public static ProjectModel getInstance(){
        if(instance==null)
            instance = new ProjectModel();
        return instance;
    }
    public List<Project> getProjects() {
        return facadeManager.getProjects();
    }
}
