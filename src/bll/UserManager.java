package bll;

import be.Project;
import be.User;
import dal.UserDAO;

import java.sql.SQLException;
import java.util.List;

public class UserManager {

    UserDAO userDAO = new UserDAO();

    public List getAllUsers() throws SQLException {
        return userDAO.getAllUsers();
    }

    public String getEmail(User user) throws SQLException{
        return userDAO.getEmail(user);
    }

    public String getPassword(User user) throws SQLException{
        return userDAO.getPassWord(user);
    }

    public void createUser(User user) throws SQLException{
        userDAO.createUser(user);
    }

    public void deleteUser(User user) throws SQLException{
        userDAO.deleteUser(user);
    }

    public void deleteUserLogin(User user) throws SQLException {
        userDAO.deleteUserLogin(user);
    }

    public void updateUser(User user) throws SQLException{
        userDAO.updateUser(user);
    }

    public void updateUserLogin(User user, String email, String password) throws SQLException {
        userDAO.updateUserLogin(user, email, password);
    }

    public boolean validateLogin(String email, String password) throws SQLException{
        return userDAO.validateLogin(email, password);
    }

    public int getUserIdFromEmail(String email) throws SQLException {
        return userDAO.getUserIdFromEmail(email);
    }

    public User getUserFromLoginById(int id) throws SQLException {
        return userDAO.getUserInLoginById(id);
    }

    public void addUserToProject(User user) throws SQLException {
        userDAO.addUserToProject(user);
    }
    public String getUserName(int userId) throws SQLException {
        return userDAO.getUserName(userId);
    }
    }
