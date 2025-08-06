package DAO;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * This class manages database connections as a singleton while maintaining
 * the original static getConnection() interface for backward compatibility.
 * 
 * @author Jarmanjit Singh
 */
public class DatabaseConnection {

    // Singleton instance
    private static volatile DatabaseConnection instance;
    
    // Database configuration
    private String url;
    private String user;
    private String password;

    // Static initialization block for backward compatibility
    static {
        getInstance(); // Initialize the singleton
    }

    private DatabaseConnection() {
        loadProperties();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found", e);
        }
    }

    /**
     * Returns the singleton instance (thread-safe)
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
     * Loads configuration from properties file with defaults
     */
    private void loadProperties() {
        Properties props = new Properties();
        
        
        try (InputStream in = getClass().getClassLoader()
                .getResourceAsStream("database.properties")) {
            
            if (in != null) {
                props.load(in);
                url = props.getProperty("jdbc.url");
                user = props.getProperty("jdbc.username");
                password = props.getProperty("jdbc.password");
            }
        } catch (IOException e) {
            System.err.println("Warning: Could not load database.properties, using default values");
        }
    }

    /**
     * Gets a new database connection
     * (Not maintaining a single connection instance to match original behavior)
     */
    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    /**
     * Static method that maintains original interface
     * @return A new Connection object
     * @throws SQLException if connection fails
     */
    public static Connection getConnection() throws SQLException {
        return getInstance().createConnection();
    }
}