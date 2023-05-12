package GUI.Models;

import be.Device;
import be.Project;
import be.documents.IDocument;
import bll.InputManager;
import bll.FacadeManager;
import javafx.collections.FXCollections;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import bll.validator.ProjectValidator;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public class ProjectModel {
    private static ProjectModel instance;
    private final ObservableList<Project> projects;
    private final ObservableList<IDocument> projectDocuments;
    private final ObservableList<Device> projectDevices;
    private final ObservableList<Project> userProjects;
    private final ObservableList<Project> oldProjects;

    private Project selectedProject;

    private List ids;
    FacadeManager facadeManager = new FacadeManager();

    private BooleanProperty isProjectSelected = new SimpleBooleanProperty(false);

    ProjectValidator validator = new ProjectValidator();
    InputManager inputManager = new InputManager();


    public static ProjectModel getInstance() {
        if (instance == null)
            instance = new ProjectModel();
        return instance;
    }

    public void refreshProjectDocuments(){
        try {
            projectDocuments.clear();
            projectDocuments.addAll(facadeManager.getAllProjectDocuments(selectedProject.getId()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ProjectModel(){
        projects = FXCollections.observableArrayList();
        projectDocuments = FXCollections.observableArrayList();
        projectDevices = FXCollections.observableArrayList();
        userProjects = FXCollections.observableArrayList();
        oldProjects = FXCollections.observableArrayList();
        fetchAllOldProjects();

    }

    public List<Project> getProjects() {
        return facadeManager.getProjects();
    }

    public void fetchAllOldProjects() {
        oldProjects.clear();
        try {
            oldProjects.addAll(inputManager.getOldProjects());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public ObservableList<Project> getOldProjects() {
        return oldProjects;
    }

    public List<IDocument> getProjectDocuments(){
        return projectDocuments;
    }
    public List<Device> getProjectDevices(){
        return projectDevices;
    }

    public ObservableList<Project> getUserProjects(int userId){
        try {
            userProjects.addAll(facadeManager.getUserProjects(userId));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userProjects;
    }

    public Project getSelectedProject() {
        return selectedProject;
    }

    public void setSelectedProject(Project selectedProject) {
        this.selectedProject = selectedProject;
        try {
            projectDocuments.clear();
            projectDevices.clear();
            if(selectedProject != null){
                projectDocuments.addAll(facadeManager.getAllProjectDocuments(selectedProject.getId()));
                projectDevices.addAll(facadeManager.getDevicesOnProject(selectedProject.getId()));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

    public void updateProject(Project project) {
        try {
            facadeManager.updateProject(project);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteProject(Project project) {
        try {
            facadeManager.deleteProject(project);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteProjectFromUserProject(Project project){
        try {
            facadeManager.deleteProjectFromUserProject(project);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setMultipleIds(List<Integer> ids){
        this.ids=ids;
    }

    public List getMultipleIds(){
        return ids;
    }

    public void deleteMultipleProjectsInUserProject(){
        ids = getMultipleIds();
        try {
            facadeManager.deleteMultipleProjectsFromUserProject(ids);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteMultipleProjects() {
        try {
            ids = getMultipleIds();
            facadeManager.deleteMultipleProjects(ids);
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
        return inputManager.getDateToday();
    }
}
