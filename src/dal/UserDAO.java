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


    /**
     * Returns list of all Users.
     * @return
     * @throws SQLException
     */
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

    /**
     * Returns list of all Users of user_type = 2. (Technicians).
     * @return
     * @throws SQLException
     */
    public List getAllTechnicians() throws SQLException {
        List<User> allTechUsers = new ArrayList<>();
        String sql = "SELECT * FROM [User] WHERE user_type = 2";
        try (Connection connection = dbc.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("user_id");
                String name = rs.getString("user_name");
                int type = rs.getInt("user_type");
                User user = new User(id, name, type);
                allTechUsers.add(user);
            }
        }
        return allTechUsers;
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

    public String getEmail(User user) throws SQLException {
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

    public String getPassWord(User user) throws SQLException {
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

    /**
     * Validates email and password. Returns true if the string matches email and password in database.
     * @param email
     * @param password
     * @return
     * @throws SQLException
     */
    public boolean validateLogin(String email, String password) throws SQLException {
        String sql = "SELECT * FROM Login WHERE email = '" + email + "';";
        try (Connection con = dbc.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("password").equals(password))
                    return true;
            }
        }
        return false;
    }

    public int getUserIdFromEmail(String email) throws SQLException {
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

    private int getNextProjectId() {
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
        int projectId = getNextProjectId();
        String sql = "INSERT INTO UserProject(project_id, user_id) VALUES (?,?)";
        try (Connection con = dbc.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, projectId);
            ps.setInt(2, userId);
            ps.execute();
        }
    }

    public void addUserToSpecificProject(User user, Project project) throws SQLException {
        int userId = user.getId();
        int projectId = project.getId();
        String sql = "INSERT INTO UserProject(project_id, user_id) VALUES (?,?)";
        try (Connection con = dbc.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, projectId);
            ps.setInt(2, userId);
            ps.execute();
        }
    }

    public void deleteUserFromProject(User user, Project project) throws SQLException {
        int projectId = project.getId();
        int userId = user.getId();
        String sql = "DELETE FROM UserProject WHERE project_id = ? AND user_id = ?";
        try (Connection con = dbc.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, projectId);
            ps.setInt(2, userId);
            ps.execute();
        }
    }


    public List<User> getUserByProject(int project_id) throws SQLException {
        List<User> userByProject = new ArrayList<>();
        String sql = "SELECT * FROM [User] u\n" +
                "JOIN UserProject up\n" +
                "ON up.user_id = u.user_id\n" +
                "WHERE up.project_id = ?;";
        try (Connection connection = dbc.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, project_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("user_id");
                String name = rs.getString("user_name");
                int type = rs.getInt("user_type");
                User user = new User(id, name, type);
                userByProject.add(user);
            }
        }
        return userByProject;
    }
}
