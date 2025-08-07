/*
 * File name: DatabaseConnection.java
 * Author: Jarmanjit Singh, 041154515
 * Course: 25S CST8288 Section 024 
 * Assignment: Final Project
 * Date: Aug 9, 2025
 * Professor: Marwan Farah
 * Purpose: Provides a thread-safe singleton class responsible for establishing
 *          and providing database connections using configuration properties.
 */

package DAO;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Singleton class that manages database connections and loads configuration
 * from a properties file at runtime.
 *
 * <p>
 * It ensures only one instance of the database configuration is loaded and
 * provides a static method to obtain a new {@link Connection} to the database.
 * </p>
 *
 * @author Jarmanjit Singh
 * @version 1.0
 * @since 21
 */
public class DatabaseConnection {

    /** Volatile singleton instance to allow double-checked locking. */
    private static volatile DatabaseConnection instance;

    /** JDBC URL of the database server. */
    private String url;

    /** Username to connect to the database. */
    private String user;

    /** Password to connect to the database. */
    private String password;

    /*
     * Static initializer to create the singleton instance
     * when the class is loaded.
     */
    static {
        getInstance();
    }

    /**
     * Private constructor loads properties and initializes the database driver.
     */
    private DatabaseConnection() {
        loadProperties();
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found", e);
        }
    }

    /**
     * Gets the singleton instance of this class using double-checked locking
     * to ensure thread safety.
     *
     * @return The singleton {@code DatabaseConnection} instance
     */
    private static DatabaseConnection getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }

    /**
     * Loads database configuration values (jdbc.url, jdbc.username, jdbc.password)
     * from the {@code database.properties} file located in the classpath.
     * If the file cannot be loaded, default values are left as {@code null}
     * which may result in failure at connection time.
     */
    private void loadProperties() {
        Properties props = new Properties();

        try (InputStream in = getClass()
                .getClassLoader()
                .getResourceAsStream("database.properties")) {

            if (in != null) {
                props.load(in);
                url = props.getProperty("jdbc.url");
                user = props.getProperty("jdbc.username");
                password = props.getProperty("jdbc.password");
            }
        } catch (IOException e) {
            System.err.println("Warning: Could not load database.properties.");
        }
    }

    /**
     * Creates a new {@link Connection} object using the configured database
     * URL, username, and password.
     *
     * @return a valid JDBC {@code Connection} to the database
     * @throws SQLException if a connection cannot be established
     */
    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    /**
     * Static utility method used by clients to easily obtain a new database
     * {@link Connection}. Each invocation returns a new connection instance.
     *
     * <p>
     *     Note: Caller is responsible for closing the connection.
     * </p>
     *
     * @return A new database connection
     * @throws SQLException if a connection cannot be created
     */
    public static Connection getConnection() throws SQLException {
        return getInstance().createConnection();
    }
}
