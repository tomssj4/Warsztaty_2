package Dao;

import Classes.User;
import ConnectionUtil.ConnectionUtil;

import java.sql.*;

public class UserDao {
    private static final String CREATE_USER_QUERY =
            "INSERT INTO users(group_id, username, email, password) VALUES (?, ?, ?, ?)";
    private static final String READ_USER_QUERY =
            "SELECT * FROM users where id = ?";
    private static final String UPDATE_USER_QUERY =
            "UPDATE users SET group_id = ?, username = ?, email = ?, password = ? where id = ?";
    private static final String DELETE_USER_QUERY =
            "DELETE FROM users WHERE id = ?";
    private static final String FIND_ALL_USERS_QUERY =
            "SELECT * FROM users";
    private static final String FIND_ALL_BY_GROUP_ID = "SELECT * FROM users WHERE group_id = ?;";

    public User create(User user) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_USER_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, user.getGroup_id());
            statement.setString(2, user.getUserName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User read(int userId) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_USER_QUERY);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setGroup_id(resultSet.getInt("group_id"));
                user.setUserName(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        } catch (SQLException e) {
            System.out.println("Uzytwkownik o podanym id nie istnieje.");
            e.printStackTrace();
        }
        return null;
    }

    public void update(User user) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_USER_QUERY);
            statement.setInt(1, user.getGroup_id());
            statement.setString(2, user.getUserName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setInt(5, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int userId) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_USER_QUERY);
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Uzytwkownik o podanym id nie istnieje.");
            e.printStackTrace();
        }
    }

    public String findAllByGroupID(int userGroupId) {
        int id = 0;
        String username = "";
        String email = "";
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_BY_GROUP_ID);
            statement.setInt(1, userGroupId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt("id");
                username = resultSet.getString("username");
                email = resultSet.getString("email");


            }
        } catch (SQLException e) {
            System.out.println("Grupa o podanym id nie istnieje.");
            e.printStackTrace();
        }
        return String.format("Classes.Group ID: %s, Classes.User ID: , Username: %s, Email: %s",
                userGroupId, id, username, email);
    }

    public String findAllUsers() {
        int id = 0;
        int groupId = 0;
        String username = "";
        String email = "";
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_USERS_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("id");
                groupId = resultSet.getInt("group_id");
                username = resultSet.getString("username");
                email = resultSet.getString("email");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return String.format("Classes.User ID: %s, Classes.User group ID: %s, Username: %s, Email: %s",
                id, groupId, username, email);
    }


}