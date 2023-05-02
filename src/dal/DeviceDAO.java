package dal;


import be.Device;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeviceDAO {
    DataBaseConnection dbc = DataBaseConnection.getInstance();

    public List<Device> getDevicesOnProject(int id) throws SQLException {
        List<Device> projectDevices = new ArrayList<>();
        String sql = "SELECT * FROM Device WHERE project_id = " + id + ";";
        try (Connection connection = dbc.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int deviceId = rs.getInt("device_id");
                int projectId = rs.getInt("project_id");
                int userId = rs.getInt("user_id");
                String name = rs.getString("device_name");
                String description = rs.getString("description");
                String dateAdded = rs.getString("date_added");
                Device device = new Device(deviceId, projectId, userId, name, description, dateAdded);
                projectDevices.add(device);
            }
        }
        return projectDevices;
    }
    public void createDevice(Device device) throws SQLException{
        int projectId = device.getProjectId();
        int userId = device.getUserId();
        String name = device.getDeviceName();
        String description = device.getDescription();
        String dateAdded = device.getDateAdded();


        String sql = "INSERT INTO Device (project_id, user_id, device_name, description, date_added)" +
                " VALUES (?,?,?,?,?)";

        try (Connection con = dbc.getConnection();) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, projectId);
            ps.setInt(2, userId);
            ps.setString(3, name);
            ps.setString(4, description);
            ps.setString(5, dateAdded);
            ps.execute();
        }
    }
    public boolean deleteDevice(Device device) throws SQLException{
        try (Connection con = dbc.getConnection()) {
            int id = device.getDeviceId();
            String sql = "DELETE FROM Device WHERE (device_id = ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int result = ps.executeUpdate();
            if (result > 0)
                return true;
        }
        return false;
    }
   public void updateDevice(Device device) throws SQLException{
        int deviceId = device.getDeviceId();
       String name = device.getDeviceName();
       String description = device.getDescription();

       String sql = "Update Device SET device_name = ?, description = ? WHERE id = ?";

       try (Connection con = dbc.getConnection();) {
           PreparedStatement ps = con.prepareStatement(sql);
           ps.setString(1, name);
           ps.setString(2, description);
           ps.setInt(3, deviceId);
           ps.execute();
       }
   }
}
