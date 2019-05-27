package Dao;

import Classes.Solution;
import ConnectionUtil.ConnectionUtil;

import java.sql.*;

public class SolutionDao {
    private static final String CREATE_SOLUTION_QUERY =
            "INSERT INTO solution(created, updated, description, exercise_id, users_id) VALUES (?, ?, ?, ?, ?)";
    private static final String READ_SOLUTION_QUERY =
            "SELECT * FROM solution where id = ?";
    private static final String UPDATE_SOLUTION_QUERY =
            "UPDATE solution SET created = ?, updated = ?, description = ?, exercise_id = ?, users_id = ? WHERE id = ?";
    private static final String DELETE_SOLUTION_QUERY =
            "DELETE FROM solution WHERE id = ?";
    private static final String FIND_ALL_SOLUTION_QUERY =
            "SELECT * FROM solution";
    private static final String FIND_ALL_BY_USER_ID = "SELECT * FROM solution WHERE users_id = ?;";
    private static final String FIND_ALL_BY_EXERCISE_ID = "SELECT * FROM solution WHERE exercise_id" +
            " = ? ORDER BY created DESC;";

    public static Solution create(Solution solution) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_SOLUTION_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, solution.getCreated());
            statement.setString(2, solution.getUpdated());
            statement.setString(3, solution.getDescription());
            statement.setInt(4, solution.getExerciseId());
            statement.setInt(5, solution.getUsersId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                solution.setId(resultSet.getInt(1));
            }
            return solution;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Solution read(int solutionId) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_SOLUTION_QUERY);
            statement.setInt(1, solutionId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String created = resultSet.getString("created");
                String updated = resultSet.getString("updated");
                String description = resultSet.getString("description");
                int exerciseId = resultSet.getInt("exercise_id");
                int usersId = resultSet.getInt("users_id");
                Solution solution = new Solution(id, created, updated, description, exerciseId, usersId);
                return solution;
            }
        } catch (SQLException e) {
            System.out.println("Rozwiazanie o podanym id nie istnieje.");
            e.printStackTrace();
        }
        return null;
    }

    public static void update(Solution solution) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_SOLUTION_QUERY);
            statement.setString(1, solution.getCreated());
            statement.setString(2, solution.getUpdated());
            statement.setString(3, solution.getDescription());
            statement.setInt(4, solution.getExerciseId());
            statement.setInt(5, solution.getUsersId());
            statement.setInt(6, solution.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete(int solutionId) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_SOLUTION_QUERY);
            statement.setInt(1, solutionId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Rozwiazanie o podanym id nie istnieje.");
            e.printStackTrace();
        }
    }

    public static String findAllByUserId(int userId) {
        int id = 0;
        String created = "";
        String updated = "";
        String description = "";
        int exerciseId = 0;
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_BY_USER_ID);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("id");
                created = resultSet.getString("created");
                updated = resultSet.getString("updated");
                description = resultSet.getString("description");
                exerciseId = resultSet.getInt("exercise_id");

            }

        } catch (SQLException e) {
            System.out.println("Uzytkownik o podanym id nie istnieje.");
            e.printStackTrace();
        }
        return String.format("Classes.Solution ID: %s, Crated: %s, Updated: %s, Description: %s," +
                " Classes.Exercise id: %s, Classes.User ID: %s", id, created, updated, description, exerciseId, userId);
    }

    public static String findAllByExerciseId(int exerciseId) {
        int id = 0;
        String created = "";
        String updated = "";
        String description = "";
        int usersId = 0;
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_BY_EXERCISE_ID);
            statement.setInt(1, exerciseId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("id");
                created = resultSet.getString("created");
                updated = resultSet.getString("updated");
                description = resultSet.getString("description");
                usersId = resultSet.getInt("users_id");

            }

        } catch (SQLException e) {
            System.out.println("Cwiczenie o podanym id nie istnieje.");
            e.printStackTrace();
        }
        return String.format("ID: %s, Crated: %s, Updated: %s, Description: %s," +
                " Classes.Exercise id: %s, Classes.User id: %s", id, created, updated, description, exerciseId, usersId);
    }

    public static String findAllSolutions() {
        int id = 0;
        String created = "";
        String updated = "";
        String description = "";
        int exerciseId = 0;
        int usersId = 0;
        try (Connection conn = ConnectionUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_SOLUTION_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("id");
                created = resultSet.getString("created");
                updated = resultSet.getString("updated");
                description = resultSet.getString("description");
                exerciseId = resultSet.getInt("exercise_id");
                usersId = resultSet.getInt("users_id");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return String.format("ID: %s, Crated: %s, Updated: %s, Description: %s," +
                " Classes.Exercise id: %s, Classes.User id: %s", id, created, updated, description, exerciseId, usersId);
    }

}