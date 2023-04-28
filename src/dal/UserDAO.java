package dal;

import be.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    DataBaseConnection dbc = DataBaseConnection.getInstance();

    public List<User> getAllUsers() throws SQLException {
        List<User> allUsers = new ArrayList<>();
        String sql = "SELECT * FROM [User]";
        try (Connection connection = dbc.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("user_id");
                String name = rs.getString("user_name");
                int type = rs.getInt("user_type");
                User user = new User(id, name, type);
                allUsers.add(user);
            }
        }
        return allUsers;
    }
}
