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
    private static final String FIND_ALL_BY_GROUP_ID ="SELECT * FROM users WHERE group_id = ?;";

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
            e.printStackTrace();
        }
    }

    public void findAllByGroupID(int userGroupId) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_BY_GROUP_ID);
            statement.setInt(1, userGroupId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                System.out.println(String.format("Group ID: %s, User ID: , Username: %s, Email: %s",
                        userGroupId, id, username, email));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void findAllUsers() {
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_USERS_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int groupId = resultSet.getInt("group_id");
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                System.out.println(String.format("User ID: %s, User group ID: %s, Username: %s, Email: %s",
                        id, groupId, username, email));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}