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
    UserModel userModel = UserModel.getInstance();

    private BooleanProperty isProjectSelected = new SimpleBooleanProperty(false);

    private String inputText;
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
    public void refreshProjectDevices(){
        try{
            projectDevices.clear();
            projectDevices.addAll(facadeManager.getDevicesOnProject(selectedProject.getId()));
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void refreshUserProjects(){
        projects.clear();
        try {
            projects.addAll(facadeManager.getUserProjects(userModel.getLoggedInUser().getId()));
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
        fetchAllProjects();
        fetchAllOldProjects();

    }

    /*public List<Project> getProjects() {
        return facadeManager.getProjects();
    }*/

    public void fetchAllProjects(){
        projects.clear();
        projects.addAll(facadeManager.getProjects());
    }

    public ObservableList<Project> getProjects(){
        return projects;
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

    public void setInputText(String inputText){
        this.inputText=inputText;
    }

    public String getInputText(){
        return inputText;
    }

    // Validator methods

    public boolean isProjectValid(String name, String companyName, String address, String zipcode, String phoneNumber, String email){
        return validator.isProjectValid(name, companyName, address, zipcode, phoneNumber, email);
    }

    public boolean isEmailValid(String email){
        return validator.isEmailValid(email);
    }

    public boolean isNumberValid(String number){
        return validator.isNumberValid(number);
    }

    // Input methods

    public String getDateToday(){
        return inputManager.getDateToday();
    }

    public void searchAddress(String str){
        projects.clear();
        try {
            projects.addAll(inputManager.searchCompanyAddress(str));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void searchCustomerName(String str){
        projects.clear();
        try {
            projects.addAll(inputManager.searchCustomerName(str));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void searchProjectName(String str){
        projects.clear();
        try {
            projects.addAll(inputManager.searchProjectName(str));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void searchZipCode(String str){
        projects.clear();
        try {
            projects.addAll(inputManager.searchZipCode(str));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
