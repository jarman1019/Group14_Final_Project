/*
 * File name: UserService.java
 * Author: Jarmanjit Singh, 041154515
 * Course: 25S CST8288 Section 024 
 * Assignment: Final Project
 * Date: Aug 9, 2025
 * Professor: Marwan Farah
 * Purpose: Provides business-layer operations for user-related features and
 *          mediates between the presentation and data access layers.
 */

package service;

import DAO.UserDAO;
import model.User;
import java.sql.SQLException;
import java.util.List;

/**
 * Service class for handling user-related business logic.
 * Acts as the application’s business layer between controllers/UI and the DAO.
 * 
 * @author Jarmanjit Singh
 * @version 1.0
 * @since 21
 */
public class UserService {

    /** Data Access Object used for retrieving and persisting users. */
    private final UserDAO userDAO;

    /**
     * Constructs a UserService instance with the required DAO.
     *
     * @param userDAO Data access object used to perform database operations.
     */
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * Registers a new user in the system after validation.
     *
     * @param user User to be registered.
     * @throws SQLException           if database operation fails.
     * @throws IllegalArgumentException if user data is invalid.
     */
    public void registerUser(User user) throws SQLException, IllegalArgumentException {
        validateUser(user);
        userDAO.insertUser(user);
    }

    /**
     * Retrieves a user by their unique ID.
     *
     * @param userId ID of the user to retrieve.
     * @return User object if found.
     * @throws SQLException if database operation fails.
     */
    public User getUserById(int userId) throws SQLException {
        return userDAO.selectUser(userId);
    }

    /**
     * Retrieves all users in the system.
     *
     * @return List of User objects.
     * @throws SQLException if database operation fails.
     */
    public List<User> getAllUsers() throws SQLException {
        return userDAO.getAllUsers();
    }

    /**
     * Attempts to authenticate a user using email and password.
     *
     * @param email    Email of the user.
     * @param password Password for the user.
     * @return User object if credentials match; otherwise null.
     */
    public User login(String email, String password) {
        return userDAO.findByEmailAndPassword(email, password);
    }

    /**
     * Updates an existing user’s data after validation.
     *
     * @param user Updated user object.
     * @return true if the update was successful.
     * @throws SQLException           if database operation fails.
     * @throws IllegalArgumentException if validation fails.
     */
    public boolean updateUser(User user) throws SQLException, IllegalArgumentException {
        validateUser(user);
        return userDAO.updateUser(user);
    }

    /**
     * Deletes a user from the system by ID.
     *
     * @param userId ID of user to delete.
     * @return true if deletion was successful.
     * @throws SQLException if database operation fails.
     */
    public boolean deleteUser(int userId) throws SQLException {
        return userDAO.deleteUser(userId);
    }

    /**
     * Authenticates user credentials manually by scanning all users.
     *
     * @param email    User's email.
     * @param password User's password.
     * @return User if authentication succeeds, otherwise null.
     * @throws SQLException if database access fails when retrieving users.
     */
    public User authenticateUser(String email, String password) throws SQLException {
        List<User> users = userDAO.getAllUsers();
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Validates user business rules before database operations.
     *
     * @param user User object to validate.
     * @throws IllegalArgumentException if validation fails.
     */
    private void validateUser(User user) throws IllegalArgumentException{
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        if (user.getUserType() == null ||
                !(user.getUserType().equals("Manager") || user.getUserType().equals("Operator"))) {
            throw new IllegalArgumentException("User type must be either 'Manager' or 'Operator'");
        }
    }
}
