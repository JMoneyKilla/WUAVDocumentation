package GUI.Models;

import be.Project;
import bll.FacadeManager;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;

import java.util.List;

public class ProjectModel {
    private static ProjectModel instance;
    private ObservableList<Project> projects;
    FacadeManager facadeManager = new FacadeManager();
    private BooleanProperty isProjectSelected = new SimpleBooleanProperty(false);

    public static ProjectModel getInstance() {
        if (instance == null)
            instance = new ProjectModel();
        return instance;
    }

    public List<Project> getProjects() {
        return facadeManager.getProjects();
    }

    public BooleanProperty isProjectSelectedProperty() {
        return isProjectSelected;
    }

    public void setIsProjectSelected(boolean isProjectSelected) {
        this.isProjectSelected.set(isProjectSelected);
    }
}
