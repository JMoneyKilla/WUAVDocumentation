package bll.managers;

import be.Device;
import dal.DeviceDAO;

import java.sql.SQLException;
import java.util.List;

public class DeviceManager {
    private DeviceDAO deviceDAO = new DeviceDAO();

    public List<Device> getDevicesOnProject(int projectId) throws SQLException {
        return deviceDAO.getDevicesOnProject(projectId);
    }
    public void createDevice(Device device) throws SQLException{
        deviceDAO.createDevice(device);
    }
    public boolean deleteDevice(Device device) throws SQLException{
        return deviceDAO.deleteDevice(device);
    }
    public void updateDevice(Device device) throws SQLException{
        deviceDAO.updateDevice(device);
    }
}
