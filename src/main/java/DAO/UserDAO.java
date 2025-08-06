/*
 * File name: UserDAO.java
 * Author: Jarmanjit Singh, 041154515
 * Course: 25S CST8288 Section 024 
 * Assignment: Final Project
 * Date: Aug 9, 2025
 * Professor: Marwan Farah
 * Purpose: Handles all CRUD (Create, Read, Update, Delete) database interactions
 *          for the User table/entity using JDBC.
 */

package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.User;

/**
 * Data Access Object responsible for interacting with the <code>User</code> table
 * in the database. Provides CRUD operations using standard JDBC API.
 * <p>
 *     Ensures the business layer can create, retrieve, update, and delete users
 *     without knowing low-level database details.
 * </p>
 * 
 * @author Jarmanjit Singh
 * @version 1.0
 * @since 21
 */
public class UserDAO {

    /** SQL statement used to insert a new user record into the database. */
    private static final String INSERT_USER_SQL =
            "INSERT INTO User (name, email, password, user_type) VALUES (?, ?, ?, ?)";

    /** SQL query to retrieve a user record by its unique ID. */
    private static final String SELECT_USER_BY_ID =
            "SELECT * FROM User WHERE user_id = ?";

    /** SQL query to retrieve all user records from the database. */
    private static final String SELECT_ALL_USERS =
            "SELECT * FROM User";

    /** SQL statement to update an existing user record in the database. */
    private static final String UPDATE_USER_SQL =
            "UPDATE User SET name = ?, email = ?, password = ?, user_type = ? WHERE user_id = ?";

    /** SQL statement to delete a user record from the database by ID. */
    private static final String DELETE_USER_SQL =
            "DELETE FROM User WHERE user_id = ?";

    /**
     * Inserts a new user row in the database based on the provided {@link User} object.
     *
     * @param user The user to be inserted.
     * @throws SQLException If insertion fails or a database error occurs.
     */
    public void insertUser(User user) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL, Statement.RETURN_GENERATED_KEYS)) {

            // Map User object values to query parameters
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getUserType());

            // Execute insertion
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }
        }
    }

    /**
     * Retrieves a user object by a specific ID.
     *
     * @param userId The unique identifier of the user.
     * @return A fully populated {@code User} object if found; otherwise {@code null}.
     * @throws SQLException If a database access error occurs.
     */
    public User selectUser(int userId) throws SQLException {
        User user = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {

            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Extract fields from result
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String userType = resultSet.getString("user_type");

                // Build immutable User object using Builder pattern
                user = new User.Builder(userId, email, password)
                        .withName(name)
                        .withUserType(userType)
                        .build();
            }
        }
        return user;
    }

    /**
     * Retrieves all users from the database.
     *
     * @return A list of {@code User} objects. Empty list if none found.
     * @throws SQLException If an SQL exception occurs.
     */
    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM user";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // Loop through all rows and construct User objects
            while (rs.next()) {
                User user = new User.Builder(rs.getInt("user_id"), rs.getString("email"), rs.getString("password"))
                        .withName(rs.getString("name"))
                        .withUserType(rs.getString("user_type"))
                        .build();
                users.add(user);
            }
        }
        return users;
    }

    /**
     * Retrieves a user using their email and password.
     *
     * @param email    The email used for authentication.
     * @param password The password used for authentication.
     * @return A matching {@code User} if credentials are found, otherwise {@code null}.
     */
    public User findByEmailAndPassword(String email, String password) {
        String sql = "SELECT * FROM user WHERE email = ? AND password = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User.Builder(rs.getInt("user_id"), rs.getString("email"), rs.getString("password"))
                        .withName(rs.getString("name"))
                        .withUserType(rs.getString("user_type"))
                        .build();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Updates an existing user in the database.
     *
     * @param user The {@link User} object containing updated fields.
     * @return {@code true} if the update was successful.
     * @throws SQLException If a database access error occurs.
     */
    public boolean updateUser(User user) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_SQL)) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getUserType());
            preparedStatement.setInt(5, user.getUserId());

            rowUpdated = preparedStatement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    /**
     * Deletes a user record from the database based on its ID.
     *
     * @param userId The unique identifier of the user to be deleted.
     * @return {@code true} if the deletion was successful.
     * @throws SQLException If a SQL error occurs during the operation.
     */
    public boolean deleteUser(int userId) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_SQL)) {

            preparedStatement.setInt(1, userId);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
}
