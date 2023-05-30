package bll.managers;

import be.Project;
import be.User;
import be.enums.UserType;
import dal.UserDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserManager {

    UserDAO userDAO = new UserDAO();

    public List getAllUsers() throws SQLException {
        return userDAO.getAllUsers();
    }

    public List getAllTechnicians() throws SQLException {
        return userDAO.getAllTechnicians();
    }

    /**
     * Gets all technicians that are not already assigned to given project
     * @param project_id
     * @return List of missing Users
     * @throws SQLException
     */
    public List<User> getMissingTechs(int project_id) throws SQLException {
        List<User> getAllTechs = userDAO.getAllTechnicians();
        List<User> userProjects = userDAO.getUserByProject(project_id);
        List<User> missingTechs = new ArrayList<>(getAllTechs);
        missingTechs.removeIf(user -> userProjects.stream().anyMatch(user1 -> user1.getId() == user.getId()));
        return missingTechs;
    }

    /**
     * Gets all technicians assigned to given project
     * @param project_id
     * @return list of users on project
     * @throws SQLException
     */
    public List<User> getTechByProject(int project_id) throws SQLException {
        List<User> userProjects = userDAO.getUserByProject(project_id);
        List<User> techsInProject = new ArrayList<>();
        for(User u : userProjects){
            if(u.getType() == UserType.TECHNICIAN){
                techsInProject.add(u);
            }
        }
        return techsInProject;
    }


    public void deleteUserFromProject(User user, Project project) throws SQLException {
        userDAO.deleteUserFromProject(user, project);
    }


    public boolean validateLogin(String email, String password) throws SQLException{
        return userDAO.validateLogin(email, password);
    }

    public int getUserIdFromEmail(String email) throws SQLException {
        return userDAO.getUserIdFromEmail(email);
    }

    /**
     * Gets User from database using Login table. Used to set logged in user.
     * @param id
     * @return User
     * @throws SQLException
     */
    public User getUserFromLoginById(int id) throws SQLException {
        return userDAO.getUserInLoginById(id);
    }

    /**
     * Adds user to most recently created project. Used because freshly created projects do not immediately
     * have an ID while program is running.
     * @param user
     * @throws SQLException
     */
    public void addUserToProject(User user) throws SQLException {
        userDAO.addUserToProject(user);
    }

    /**
     * Adds user to an already existing/old project.
     * @param user
     * @param project
     * @throws SQLException
     */
    public void addUserToSpecificProject(User user, Project project) throws SQLException {
        userDAO.addUserToSpecificProject(user, project);
    }

    public String getUserName(int userId) throws SQLException {
        return userDAO.getUserName(userId);
    }
}

