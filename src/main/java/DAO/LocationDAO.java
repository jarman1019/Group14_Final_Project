/*
 * File name: LocationDAO.java
 * Author: Jarmanjit Singh, 041154515
 * Course: 25S CST8288 Section 024 
 * Assignment: Final Project
 * Date: Aug 9, 2025
 * Professor: Marwan Farah
 * Purpose: Provides database operations related to vehicle location data such as saving, 
 *          retrieving current or historical locations, and filtering by timestamp.
 */

package DAO;

import model.Location;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Data Access Object for managing location records in the database.
 * Encapsulates CRUD operations for {@link Location} objects.
 * 
 * Assumes a table structure:
 * <pre>
 * CREATE TABLE Location (
 *     location_id INT AUTO_INCREMENT PRIMARY KEY,
 *     vehicle_id VARCHAR(50) NOT NULL,
 *     latitude DOUBLE NOT NULL,
 *     longitude DOUBLE NOT NULL,
 *     timestamp TIMESTAMP NOT NULL
 * );
 * </pre>
 * 
 * Foreign key assumed on vehicle_id linked to Vehicle table.
 * 
 * @author Jarmanjit Singh
 * @version 1.0
 * @since 21
 */
public class LocationDAO {

    /**
     * Saves a {@link Location} record to the database.
     *
     * @param location The location to save.
     */
    public void saveLocation(Location location) {
        String sql = "INSERT INTO Location (vehicle_id, latitude, longitude, timestamp) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, location.getVehicleId());
            stmt.setDouble(2, location.getLatitude());
            stmt.setDouble(3, location.getLongitude());
            stmt.setTimestamp(4, new Timestamp(location.getTimestamp().getTime()));
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves all location records for a given vehicle within a date range.
     *
     * @param vehicleId The vehicle identifier.
     * @param from Start of date range.
     * @param to End of date range.
     * @return List of matching locations.
     */
    public List<Location> getLocationHistory(String vehicleId, Date from, Date to) {
        List<Location> history = new ArrayList<>();
        String sql = "SELECT * FROM Location WHERE vehicle_id = ? AND timestamp BETWEEN ? AND ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, vehicleId);
            stmt.setTimestamp(2, new Timestamp(from.getTime()));
            stmt.setTimestamp(3, new Timestamp(to.getTime()));
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                history.add(mapResultSetToLocation(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return history;
    }

    /**
     * Retrieves all locations updated since the given time.
     *
     * @param since The timestamp to filter by.
     * @return List of recent location updates.
     */
    public List<Location> getLocationsSince(Date since) {
        List<Location> recentLocations = new ArrayList<>();
        String sql = "SELECT * FROM Location WHERE timestamp >= ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setTimestamp(1, new Timestamp(since.getTime()));
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                recentLocations.add(mapResultSetToLocation(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recentLocations;
    }

    /**
     * Maps a ResultSet row to a {@link Location} object.
     *
     * @param rs ResultSet with location data.
     * @return Mapped Location object.
     * @throws SQLException if column parsing fails.
     */
    private Location mapResultSetToLocation(ResultSet rs) throws SQLException {
        return new Location.Builder(
                rs.getDouble("latitude"),
                rs.getDouble("longitude"),
                rs.getTimestamp("timestamp"),
                rs.getString("vehicle_id")
        ).build();
    }
}
