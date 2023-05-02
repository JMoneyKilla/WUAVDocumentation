package bll;

import be.Device;
import be.Project;
import be.documents.IDocument;
import bll.managers.DeviceManager;
import bll.managers.DocumentManager;

import java.sql.SQLException;
import java.util.List;

public class FacadeManager {
    private ProjectManager projectManager = new ProjectManager();
    private DeviceManager deviceManager = new DeviceManager();
    private DocumentManager documentManager = new DocumentManager();


    //Methods for accessing ProjectManager
    public List<Project> getProjects() {
        return projectManager.getProjects();
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
    public List<IDocument> getAllProjectDocuments(int id) throws SQLException {
        return documentManager.getAllProjectDocuments(id);
    }
    public boolean deleteDocument(IDocument document) throws SQLException{
        return documentManager.deleteDocument(document);
    }
    public void updateDocument(IDocument document) throws SQLException{
        documentManager.updateDocument(document);
    }
    public void createDocument(IDocument document) throws SQLException{
        documentManager.createDocument(document);
    }

    //Methods for accessing UserManager
}
