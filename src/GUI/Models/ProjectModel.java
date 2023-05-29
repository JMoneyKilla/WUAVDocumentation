package GUI.Models;

import be.Device;
import be.Project;
import be.User;
import be.documents.IDocument;
import be.handlers.AlertBoxStrategy;
import be.handlers.IOAlertStrategy;
import be.handlers.SQLAlertStrategy;
import be.enums.UserType;
import bll.managers.InputManager;
import bll.managers.FacadeManager;
import javafx.collections.FXCollections;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import bll.validator.ProjectValidator;
import javafx.collections.ObservableList;

import java.io.IOException;
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
    AlertBoxStrategy alertBoxStrategy;

    private BooleanProperty isProjectSelected = new SimpleBooleanProperty(false);
    private BooleanProperty addedDocument = new SimpleBooleanProperty(false);
    private BooleanProperty addedDevice = new SimpleBooleanProperty(false);

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
            alertBoxStrategy = new SQLAlertStrategy();
            alertBoxStrategy.showCustomAlert("Could not retrieve project documents from the database." +
                    "please check your internet connection is stable or contact your system administrator.");
        } catch (IOException e) {
            alertBoxStrategy = new IOAlertStrategy();
            alertBoxStrategy.showGenericAlert(e);
        }
    }
    public void refreshProjectDevices(){
        try{
            projectDevices.clear();
            projectDevices.addAll(facadeManager.getDevicesOnProject(selectedProject.getId()));
        } catch (SQLException e){
            alertBoxStrategy = new SQLAlertStrategy();
            alertBoxStrategy.showCustomAlert("Could not retrieve project devices from the database." +
                    "please check your internet connection is stable or contact your system administrator.");
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
        fetchAllUserProjects(userModel.getLoggedInUser().getId());
        fetchAllOldProjects();

    }

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
            alertBoxStrategy = new SQLAlertStrategy();
            alertBoxStrategy.showGenericAlert(e);
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

    public void fetchAllUserProjects(int userId){
        userProjects.clear();
        try {
            userProjects.addAll(facadeManager.getUserProjects(userId));
        } catch (SQLException e) {
            alertBoxStrategy = new SQLAlertStrategy();
            alertBoxStrategy.showCustomAlert("Could not retrieve projects for logged in user.");
        }
    }

    public ObservableList<Project> getUserProjects(int userId){
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
        } catch (SQLException | IOException e) {
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
            alertBoxStrategy = new SQLAlertStrategy();
            alertBoxStrategy.showCustomAlert("Unable to connect to database to update project. Please check you" +
                    "have a stable connection and try again.");
        }
    }

    public void deleteProject(Project project) {
        try {
            facadeManager.deleteProject(project);
        } catch (SQLException e) {
            alertBoxStrategy = new SQLAlertStrategy();
            alertBoxStrategy.showCustomAlert("Could not delete project from database." +
                    "check your connection is stable and contact your system administrator");
        }
    }

    public void deleteProjectFromUserProject(Project project){
        try {
            facadeManager.deleteProjectFromUserProject(project);
        } catch (SQLException e) {
            alertBoxStrategy = new SQLAlertStrategy();
            alertBoxStrategy.showCustomAlert("Could not delete project from database." +
                    "check your connection is stable and contact your system administrator");
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
            alertBoxStrategy = new SQLAlertStrategy();
            alertBoxStrategy.showCustomAlert("An error occured while trying to delete old projects." +
                    "Please check your connection is stable, or try manual deletion of old projects");
        }
    }

    public void deleteMultipleProjects() {
        try {
            ids = getMultipleIds();
            facadeManager.deleteMultipleProjects(ids);
        } catch (SQLException e) {
            alertBoxStrategy = new SQLAlertStrategy();
            alertBoxStrategy.showCustomAlert("An error occured while trying to delete old projects." +
                    "Please check your connection is stable, or try manual deletion of old projects");
        }
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

    public boolean isZipCodeValid(String number){
        return validator.isZipCodeValid(number);
    }

    // Input methods

    public String getDateToday(){
        return inputManager.getDateToday();
    }

    public void searchAddress(String str, User user){
        if(user.getType()== UserType.TECHNICIAN){
            userProjects.clear();
            try {
                userProjects.addAll(inputManager.searchCompanyAddress(str, user));
            } catch (SQLException e) {
                alertBoxStrategy = new SQLAlertStrategy();
                alertBoxStrategy.showCustomAlert("An error occured while trying to filter projects address. " +
                        "Check that your internet connection is stable and contact your system administrator");
            }
        }
        else{ projects.clear();
        try {
            projects.addAll(inputManager.searchCompanyAddress(str, user));
        } catch (SQLException e) {
            alertBoxStrategy = new SQLAlertStrategy();
            alertBoxStrategy.showCustomAlert("An error occured while trying to filter projects address. " +
                    "Check that your internet connection is stable and contact your system administrator");
        }}
    }

    public void searchCustomerName(String str, User user){
        if(user.getType()== UserType.TECHNICIAN){
            userProjects.clear();
            try {
                userProjects.addAll(inputManager.searchCustomerName(str, user));
            } catch (SQLException e) {
                alertBoxStrategy = new SQLAlertStrategy();
                alertBoxStrategy.showCustomAlert("An error occured while trying to filter projects by customer name. " +
                        "Check that your internet connection is stable and contact your system administrator");
            }
        }
        else {projects.clear();
        try {
            projects.addAll(inputManager.searchCustomerName(str, user));
        } catch (SQLException e) {
            alertBoxStrategy = new SQLAlertStrategy();
            alertBoxStrategy.showCustomAlert("An error occured while trying to filter projects by customer name. " +
                    "Check that your internet connection is stable and contact your system administrator");
        }}
    }

    public void searchProjectName(String str, User user){
        if(user.getType()== UserType.TECHNICIAN){
            userProjects.clear();
            try {
                userProjects.addAll(inputManager.searchProjectName(str, user));
            } catch (SQLException e) {
                alertBoxStrategy = new SQLAlertStrategy();
                alertBoxStrategy.showCustomAlert("An error occured while trying to filter projects by project name. " +
                        "Check that your internet connection is stable and contact your system administrator");

            }
        }
        else{
        projects.clear();
        try {
            projects.addAll(inputManager.searchProjectName(str, user));
        } catch (SQLException e) {
            alertBoxStrategy = new SQLAlertStrategy();
            alertBoxStrategy.showCustomAlert("An error occured while trying to filter projects by project name. " +
                    "Check that your internet connection is stable and contact your system administrator");
        }
        }
    }

    public void searchZipCode(String str, User user) {
        if (user.getType() == UserType.TECHNICIAN) {
            userProjects.clear();
            try {
                userProjects.addAll(inputManager.searchZipCode(str, user));
            } catch (SQLException e) {
                alertBoxStrategy = new SQLAlertStrategy();
                alertBoxStrategy.showCustomAlert("An error occured while trying to filter projects by zip code. " +
                        "Check that your internet connection is stable and contact your system administrator");
            }
        } else {
            projects.clear();
            try {
                projects.addAll(inputManager.searchZipCode(str, user));
            } catch (SQLException e) {
                alertBoxStrategy = new SQLAlertStrategy();
                alertBoxStrategy.showCustomAlert("An error occured while trying to filter projects by zip code. " +
                        "Check that your internet connection is stable and contact your system administrator");
            }
        }
    }


    public void deleteDocument(IDocument document){
        try {
            facadeManager.deleteDocument(document);
        } catch (SQLException e) {
            alertBoxStrategy = new SQLAlertStrategy();
            alertBoxStrategy.showCustomAlert("An error occured while trying to delete document from the project. " +
                    "Check that your internet connection is stable and contact your system administrator");
        }
    }
    public void deleteDevice(Device device){
        try {
            facadeManager.deleteDevice(device);
        } catch (SQLException e) {
            alertBoxStrategy = new SQLAlertStrategy();
            alertBoxStrategy.showCustomAlert("An error occured while trying to delete document from the project. " +
                    "Check that your internet connection is stable and contact your system administrator");
        }
    }

    public BooleanProperty addedDocumentProperty() {
        return addedDocument;
    }

    public void setAddedDocument(boolean addedDocument) {
        this.addedDocument.set(addedDocument);
    }


    public BooleanProperty addedDeviceProperty() {
        return addedDevice;
    }

    public void setAddedDevice(boolean addedDevice) {
        this.addedDevice.set(addedDevice);
    }
}
