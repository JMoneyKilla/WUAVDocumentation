package dal;

import be.Project;
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
    public String getUserName(int userId) throws SQLException {
        String userName = "could not find user";
        String sql = "Select user_name FROM [USER] WHERE user_id = " + userId + ";";
        try (Connection connection = dbc.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                userName = rs.getString("user_name");
            }
        }
        return userName;
    }

    public List getAllEmails() throws SQLException{
        List<String> emails = new ArrayList<>();
        String sql = "SELECT email FROM Login";
        try (Connection connection = dbc.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String email = rs.getString("email");
                emails.add(email);
            }
            return emails;
        }
    }

    public String getEmail(User user) throws SQLException{
        int id = user.getId();
        String email = "";
        String sql = "SELECT email FROM Login WHERE user_id = ?";
        try (Connection connection = dbc.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                email = rs.getString("email");
            }
        }
        return email;
    }

    public String getPassWord(User user) throws SQLException{
    int id = user.getId();
    String password = "";
        String sql = "SELECT password FROM Login WHERE user_id = ?";
        try (Connection connection = dbc.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                password = rs.getString("password");
            }
        }
        return password;
    }

    public boolean validateLogin(String email, String password) throws SQLException {
        String sql = "SELECT * FROM Login WHERE email = '" + email + "';";
        try(Connection con = dbc.getConnection()){
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                if(rs.getString("password").equals(password))
                    return true;
            }
        }
        return false;
    }

    public int getUserIdFromEmail(String email) throws SQLException{
        int userId = 0;
        String sql = "SELECT user_id FROM Login WHERE email = ?";
        try (Connection connection = dbc.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                userId = rs.getInt("user_id");
            }
            return userId;
        }
    }


    public User getUserInLoginById(int id) throws SQLException {
        String sql = "SELECT * FROM [User] u\n" +
                "JOIN Login l \n" +
                "ON l.user_id = u.user_id\n" +
                "WHERE l.user_id = ?;";
        try (Connection connection = dbc.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            User user = null;
            while (rs.next()) {
                int userId = rs.getInt("user_id");
                String name = rs.getString("user_name");
                int type = rs.getInt("user_type");
                user = new User(userId, name, type);
            }
            return user;
        }
    }

    public void createUser(User user) throws SQLException {
        String sql = "INSERT INTO [User] (user_name, user_type) VALUES (?,?)";
        String name = user.getName();
        int type = user.getType();

        try (Connection con = dbc.getConnection();) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, type);

            ps.execute();
        }
    }

    public void deleteUser(User user) throws SQLException {
        int id = user.getId();
        String sql = "DELETE FROM User WHERE user_id=?";

        try (Connection con = dbc.getConnection();) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
        }
    }

    public void deleteUserLogin(User user) throws SQLException{
        int id = user.getId();
        String sql = "DELETE FROM Login WHERE user_id=?";

        try (Connection con = dbc.getConnection();) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
        }
    }

    public void updateUser(User user) throws SQLException{
        int id = user.getId();
        String name = user.getName();
        int userType = user.getType();

        String sql = "Update [User] SET user_name = ?, user_type = ? WHERE id = ?";

        try (Connection con = dbc.getConnection();) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, userType);
            ps.setInt(3, id);
            ps.execute();
        }
    }


    public void updateUserLogin(User user, String email, String password) throws SQLException{
        int id = user.getId();

        String sql = "Update Login SET email = ?, password = ? WHERE id = ?";

        try (Connection con = dbc.getConnection();) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ps.setInt(3, id);
            ps.execute();
        }
    }

    private int getNextEventId() {
        try (Connection con = dbc.getConnection()) {
            ResultSet rs = con.createStatement().executeQuery("SELECT TOP 1 * FROM Project ORDER BY project_id DESC;");
            rs.next();
            int id = rs.getInt("project_id");
            int nextID = id;
            return nextID;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addUserToProject(User user) throws SQLException {
        int userId = user.getId();
        int projectId = getNextEventId();
        String sql = "INSERT INTO UserProject(project_id, user_id) VALUES (?,?)";
        try(Connection con = dbc.getConnection()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, projectId);
            ps.setInt(2, userId);
            ps.execute();
        }
    }
}
