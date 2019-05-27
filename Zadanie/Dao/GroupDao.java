package Dao;

import java.sql.*;
import Classes.Group;
import ConnectionUtil.ConnectionUtil;


public class GroupDao {
    private static final String CREATE_GROUP_QUERY =
            "INSERT INTO user_group(name) VALUES (?)";
    private static final String READ_GROUP_QUERY =
            "SELECT * FROM user_group where id = ?";
    private static final String UPDATE_GROUP_QUERY =
            "UPDATE user_group SET name = ? WHERE id = ?";
    private static final String DELETE_GROUP_QUERY =
            "DELETE FROM user_group WHERE id = ?";
    private static final String FIND_ALL_GROUP_QUERY =
            "SELECT * FROM user_group";

    public Group create(Group group) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_GROUP_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, group.getName());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                group.setId(resultSet.getInt(1));
            }
            return group;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Group read(int groupId) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_GROUP_QUERY);
            statement.setInt(1, groupId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Group group = new Group(id, name);
                return group;
            }
        } catch (SQLException e) {
            System.out.println("Grupa o podanym id nie istnieje.");
            e.printStackTrace();
        }
        return null;
    }

    public void update(Group group) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_GROUP_QUERY);
            statement.setString(1, group.getName());
            statement.setInt(2, group.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int groupId) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_GROUP_QUERY);
            statement.setInt(1, groupId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Grupa o podanym id nie istnieje.");
            e.printStackTrace();
        }
    }

    public String findAllGroup() {
        int id = 0;
        String name = "";
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_GROUP_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("id");
                name = resultSet.getString("name");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return String.format("Classes.Group ID: %s, Name: %s",
                id, name);
    }


}