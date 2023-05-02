package bll;

import be.User;

import java.sql.SQLException;
import java.util.List;

public class FacadeManagers {

    UserManager userManager = new UserManager();

    /*
    UserManager methods
     */

    public List getAllUsers() throws SQLException {
        return userManager.getAllUsers();
    }

    public String getEmail(User user) throws SQLException{
        return userManager.getEmail(user);
    }

    public String getPassword(User user) throws SQLException{
        return userManager.getPassword(user);
    }

    public void createUser(User user) throws SQLException{
        userManager.createUser(user);
    }

    public void deleteUser(User user) throws SQLException{
        userManager.deleteUser(user);
    }

    public void deleteUserLogin(User user) throws SQLException{
        userManager.deleteUserLogin(user);
    }

    public void updateUser(User user) throws SQLException{
        userManager.updateUser(user);
    }

    public void updateUserLogin(User user, String email, String password) throws SQLException {
        userManager.updateUserLogin(user, email, password);
    }

    public boolean validateLogin(String email, String password) throws SQLException {
        return userManager.validateLogin(email, password);
    }

    public int getUserIdFromEmail(String email) throws SQLException {
        return userManager.getUserIdFromEmail(email);
    }

    public User getUserFromLoginById(int id) throws SQLException {
        return userManager.getUserFromLoginById(id);
    }
}
