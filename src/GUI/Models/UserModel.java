package GUI.Models;

import be.User;
import bll.FacadeManager;
import java.sql.SQLException;
import java.util.List;

public class UserModel {

    private static UserModel instance;

    FacadeManager bll = new FacadeManager();
    User loggedInUser;

    public static UserModel getInstance(){
        if(instance==null)
            instance = new UserModel();
        return instance;
    }

    public List getAllUsers(){
        try {
            return bll.getAllUsers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean validateLogin(String email, String password){
        try {
            return bll.validateLogin(email, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
            throw new RuntimeException(e);
        }
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
            throw new RuntimeException(e);
        }
    }
}
