package bll.managers;

import be.Device;
import be.Project;
import be.User;
import be.documents.IDocument;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class FacadeManager {
    private ProjectManager projectManager = new ProjectManager();
    UserManager userManager = new UserManager();
    private DeviceManager deviceManager = new DeviceManager();
    private DocumentManager documentManager = new DocumentManager();


    //Methods for accessing ProjectManager
    public List<Project> getProjects() {
        return projectManager.getProjects();
    }

    public List getUserProjects(int userId) throws SQLException {
        return projectManager.getUserProjects(userId);
    }
    public void createProject(Project project) throws SQLException {
        projectManager.createProject(project);
    }

    public void updateProject(Project project) throws SQLException {
        projectManager.updateProject(project);
    }

    public void deleteProject(Project project) throws SQLException {
        projectManager.deleteProject(project);
    }

    public void deleteProjectFromUserProject(Project project) throws SQLException{
        projectManager.deleteProjectFromUserProject(project);
    }

    public void deleteMultipleProjectsFromUserProject(List ids) throws SQLException {
        projectManager.deleteMultipleProjectsFromUserProject(ids);
    }
    public void deleteMultipleProjects(List ids) throws SQLException {
        projectManager.deleteMultipleProjects(ids);
    }

    //Methods for accessing DeviceManager
    public List<Device> getDevicesOnProject(int projectId) throws SQLException {
        return deviceManager.getDevicesOnProject(projectId);
    }
    public void createDevice(Device device) throws SQLException{
        deviceManager.createDevice(device);
    }
    public boolean deleteDevice(Device device) throws SQLException{
        return deviceManager.deleteDevice(device);
    }
    public void updateDevice(Device device) throws SQLException{
        deviceManager.updateDevice(device);
    }


    //Methods for accessing DocumentManager
    public List<IDocument> getAllProjectDocuments(int id) throws SQLException, IOException {
        return documentManager.getAllProjectDocuments(id);
    }
    public boolean deleteDocument(IDocument document) throws SQLException{
        return documentManager.deleteDocument(document);
    }
    public void updateDocument(IDocument document) throws SQLException, IOException {
        documentManager.updateDocument(document);
    }
    public void createDocument(IDocument document) throws SQLException, IOException {
            documentManager.createDocument(document);
    }

    /*
    UserManager methods
     */

    public List getAllUsers() throws SQLException {
        return userManager.getAllUsers();
    }

    public List getTechnicians() throws SQLException {
        return userManager.getAllTechnicians();
    }

    public List<User> getMissingTechs(int project_id) throws SQLException {
        return userManager.getMissingTechs(project_id);
    }

    public List<User> getTechsInProject(int project_id) throws SQLException {
        return userManager.getTechByProject(project_id);
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

    public void deleteUserFromProject(User user, Project project) throws SQLException {
        userManager.deleteUserFromProject(user, project);
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

    public void addUserToProject(User user) throws SQLException {
        userManager.addUserToProject(user);
    }

    public void addUserToSpecificProject(User user, Project project) throws SQLException {
        userManager.addUserToSpecificProject(user, project);
    }

    public String getUserName(int userId) throws SQLException {
        return userManager.getUserName(userId);}
    }