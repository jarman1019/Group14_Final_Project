/*
 * File name: User.java
 * Author: Jarmanjit Singh, 041154515
 * Course: 25S CST8288 Section 024 
 * Assignment: Final Project
 * Date: Aug 9, 2025
 * Professor: Marwan Farah
 * Purpose: Defines an immutable User model class and a Builder pattern
 *          for creating user instances with required and optional fields.
 */

package model;

/**
 * Represents an immutable User in the system with basic attributes such as
 * ID, name, email, password, and user role type. Instances are created via
 * the nested {@link Builder} class to enforce validation and controlled
 * instantiation.
 * 
 * <p>
 *     The Builder pattern enhances flexibility and readability when creating
 *     User objects with optional parameters.
 * </p>
 * 
 * @author Jarmanjit Singh
 * @version 1.0
 * @since 21
 */
public class User {

    /** Unique identifier for the user. */
    private final int userId;

    /** The name of the user (optional). */
    private final String name;

    /** Email associated with the user account. */
    private final String email;

    /** User’s password (assumed to be securely stored/hashed elsewhere). */
    private final String password;

    /** Role or type of user (e.g., Admin or Regular). */
    private final String userType;

    /**
     * Private constructor invoked by the Builder.
     * Assigns values built from the Builder to the final fields.
     *
     * @param builder Builder instance used to populate fields.
     */
    private User(Builder builder) {
        this.userId = builder.userId;
        this.name = builder.name;
        this.email = builder.email;
        this.password = builder.password;
        this.userType = builder.userType;
    }

    /**
     * Returns the unique identifier for the user.
     *
     * @return user ID.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Returns the name of the user.
     *
     * @return user's name or {@code null} if not provided.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the email address for the user.
     *
     * @return user's email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the password associated with the user.
     * Typically, this would be a hashed value.
     *
     * @return user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns the user’s role or type.
     *
     * @return e.g. "Admin" or "Regular".
     */
    public String getUserType() {
        return userType;
    }

    /**
     * Returns a descriptive string representing this user.  
     * The actual password is not printed for security.
     *
     * @return string representation of this user.
     */
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='[PROTECTED]'" +
                ", userType='" + userType + '\'' +
                '}';
    }

    /**
     * Builder class for creating {@link User} instances safely
     * with optional fields and validation logic.
     */
    public static class Builder {
        private int userId;
        private String name;
        private String email;
        private String password;
        private String userType;

        /**
         * Creates a Builder with mandatory fields.
         *
         * @param userId   Unique ID of the user.
         * @param email    Email address of the user.
         * @param password Password for the user.
         */
        public Builder(int userId, String email, String password) {
            this.userId = userId;
            this.email = email;
            this.password = password;
        }

        /**
         * Sets the optional name.
         *
         * @param name User’s name.
         * @return current Builder (for chaining).
         */
        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the user type/role.
         *
         * @param userType Role type.
         * @return current Builder (for chaining).
         */
        public Builder withUserType(String userType) {
            this.userType = userType;
            return this;
        }

        /**
         * Constructs and returns a {@link User} object after validation.
         *
         * @return new User instance.
         * @throws IllegalStateException if any required fields are missing.
         */
        public User build() {
            validate();
            return new User(this);
        }

        /**
         * Validates required fields prior to object creation.
         *
         * @throws IllegalStateException if email, password, or userType
         *                               is {@code null} or empty.
         */
        private void validate() {
            if (email == null || email.isEmpty()) {
                throw new IllegalStateException("Email cannot be null or empty");
            }
            if (password == null || password.isEmpty()) {
                throw new IllegalStateException("Password cannot be null or empty");
            }
            if (userType == null || userType.isEmpty()) {
                throw new IllegalStateException("User type cannot be null or empty");
            }
        }
    }
}
