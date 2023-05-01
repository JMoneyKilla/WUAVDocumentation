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
                Device device = new Device(deviceId, projectId, userId, name, description);
                projectDevices.add(device);
            }
        }
        return projectDevices;
    }

}
