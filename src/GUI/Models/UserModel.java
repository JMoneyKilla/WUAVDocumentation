package GUI.Models;

import be.Project;
import be.User;
import be.handlers.AlertBoxStrategy;
import be.handlers.SQLAlertStrategy;
import bll.managers.FacadeManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class UserModel {

    private static UserModel instance;

    FacadeManager bll = new FacadeManager();
    private final ObservableList<User> users;
    private final ObservableList<User> techs;
    private final ObservableList<User> missingTechs;
    private final ObservableList<User> assignedTechs;
    User loggedInUser;
    AlertBoxStrategy alertBoxStrategy;


    public static UserModel getInstance(){
        if(instance==null)
            instance = new UserModel();
        return instance;
    }

    public UserModel(){
        users = FXCollections.observableArrayList();
        techs = FXCollections.observableArrayList();
        missingTechs = FXCollections.observableArrayList();
        assignedTechs = FXCollections.observableArrayList();
        fetchAllUsers();
        fetchAllTechs();
    }

    public void fetchAllUsers(){
        users.clear();
        try {
             users.addAll(bll.getAllUsers());
        } catch (SQLException e) {
            alertBoxStrategy = new SQLAlertStrategy();
            alertBoxStrategy.showCustomAlert("An error occured while retrieving users from database " +
                    "Check that your internet connection is stable and contact your system administrator");        }
    }

    public void fetchAllTechs(){
        techs.clear();
        try {
            techs.addAll((bll.getTechnicians()));
        } catch (SQLException e) {
            alertBoxStrategy = new SQLAlertStrategy();
            alertBoxStrategy.showGenericAlert(e);
        }
    }

    public void fetchAllMissingTechs(int project_id){
        missingTechs.clear();
        try {
            missingTechs.addAll(bll.getMissingTechs(project_id));
        } catch (SQLException e) {
            alertBoxStrategy = new SQLAlertStrategy();
            alertBoxStrategy.showGenericAlert(e);
        }
    }

    public void fetchAssignedTechs(int project_id){
        assignedTechs.clear();
        try {
            assignedTechs.addAll(bll.getTechsInProject(project_id));
        } catch (SQLException e) {
            alertBoxStrategy = new SQLAlertStrategy();
            alertBoxStrategy.showGenericAlert(e);
        }
    }

    public ObservableList<User> getAssignedTechs(){
        return assignedTechs;
    }

    public ObservableList<User> getMissingTechs(){
        return missingTechs;
    }


    public boolean validateLogin(String email, String password){
        try {
            return bll.validateLogin(email, password);
        } catch (SQLException e) {
            alertBoxStrategy = new SQLAlertStrategy();
            alertBoxStrategy.showCustomAlert("Could not validate user login. " +
                    "Check that your internet connection is stable and contact your system administrator");
        }
        return false;
    }

    public int getUserIdFromEmail(String email){
        try {
            return bll.getUserIdFromEmail(email);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User getUserFromLoginById(int id){
        try {
            return bll.getUserFromLoginById(id);
        } catch (SQLException e) {
            alertBoxStrategy = new SQLAlertStrategy();
            alertBoxStrategy.showGenericAlert(e);
        }
        return null;
    }

    public void setLoggedInUser(User user){
        this.loggedInUser=user;

    }
    public User getLoggedInUser(){
        return loggedInUser;
    }

    public void addUserToProject(User user) {
        try {
            bll.addUserToProject(user);
        } catch (SQLException e) {
            alertBoxStrategy = new SQLAlertStrategy();
            alertBoxStrategy.showGenericAlert(e);
        }
    }

    public void addUserToSpecificProject(User user, Project project){
        try {
            bll.addUserToSpecificProject(user, project);
        } catch (SQLException e) {
            alertBoxStrategy = new SQLAlertStrategy();
            alertBoxStrategy.showGenericAlert(e);
        }
    }

    public void deleteUserFromProject(User user, Project project){
        try {
            bll.deleteUserFromProject(user, project);
        } catch (SQLException e) {
            alertBoxStrategy = new SQLAlertStrategy();
            alertBoxStrategy.showGenericAlert(e);
        }
    }

    public String getUserName(int userId){
        try {
            return bll.getUserName(userId);
        } catch (SQLException e) {
            alertBoxStrategy = new SQLAlertStrategy();
            alertBoxStrategy.showGenericAlert(e);
        }
        return null;
    }
    }
