package GUI.Models;

import be.Project;
import bll.DateInput;
import bll.FacadeManager;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import bll.validator.ProjectValidator;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public class ProjectModel {
    private static ProjectModel instance;
    private ObservableList<Project> projects;
    FacadeManager facadeManager = new FacadeManager();

    private BooleanProperty isProjectSelected = new SimpleBooleanProperty(false);

    ProjectValidator validator = new ProjectValidator();
    DateInput dateInput = new DateInput();


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
        this.isProjectSelected.set(isProjectSelected);}

    public void createProject(Project project){
        try {
            facadeManager.createProject(project);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Validator methods

    public boolean isProjectValid(String name, String companyName, String address){
        return validator.isProjectValid(name, companyName, address);
    }

    // Date method

    public String getDateToday(){
        return dateInput.getDateToday();
    }
}
