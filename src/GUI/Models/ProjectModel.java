package GUI.Models;

import be.Project;
import bll.DateInput;
import bll.FacadeManager;
import bll.validator.ProjectValidator;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public class ProjectModel {
    private static ProjectModel instance;
    private ObservableList<Project> projects;
    FacadeManager facadeManager = new FacadeManager();
    ProjectValidator validator = new ProjectValidator();
    DateInput dateInput = new DateInput();

    public static ProjectModel getInstance(){
        if(instance==null)
            instance = new ProjectModel();
        return instance;
    }
    public List<Project> getProjects() {
        return facadeManager.getProjects();
    }

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
