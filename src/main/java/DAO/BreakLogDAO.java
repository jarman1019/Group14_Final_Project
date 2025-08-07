/*
 * File name: BreakLogDAO.java
 * Author: Jarmanjit Singh, 041154515
 * Course: 25S CST8288 Section 024 
 * Assignment: Final Project
 * Date: Aug 7, 2025
 * Professor: Marwan Farah
 * Purpose: DAO for managing BreakLog records including saving, retrieving by operator,
 *          and retrieving all break logs from the database.
 */

package DAO;

import model.BreakLog;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Data Access Object (DAO) class for managing BreakLog records in the database.
 * Provides methods to save break logs, retrieve logs by operator ID, and fetch all logs.
 * Handles conversion between ResultSet and BreakLog model.
 * 
 * @author Jarmanjit Singh
 * @since 21
 * @version 1.0
 */
public class BreakLogDAO {

    /**
     * Saves a BreakLog record to the database.
     * 
     * @param breakLog The BreakLog object to save.
     */
    public void saveBreakLog(BreakLog breakLog) {
        String sql = "INSERT INTO BreakLog (operator_id, reason, timestamp) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, breakLog.getOperatorId());
            stmt.setString(2, breakLog.getReason());
            stmt.setTimestamp(3, new Timestamp(breakLog.getTimestamp().getTime()));
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves all BreakLog records for a specific operator, ordered by timestamp descending.
     * 
     * @param operatorId The ID of the operator whose break logs are to be retrieved.
     * @return A list of BreakLog objects associated with the operator.
     */
    public List<BreakLog> getBreakLogsByOperator(int operatorId) {
        List<BreakLog> breakLogs = new ArrayList<>();
        String sql = "SELECT * FROM BreakLog WHERE operator_id = ? ORDER BY timestamp DESC";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, operatorId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                breakLogs.add(mapResultSetToBreakLog(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return breakLogs;
    }

    /**
     * Retrieves all BreakLog records from the database, ordered by timestamp descending.
     * 
     * @return A list of all BreakLog objects.
     */
    public List<BreakLog> getAllBreakLogs() {
        List<BreakLog> breakLogs = new ArrayList<>();
        String sql = "SELECT * FROM BreakLog ORDER BY timestamp DESC";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                breakLogs.add(mapResultSetToBreakLog(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return breakLogs;
    }

    /**
     * Maps a ResultSet row to a BreakLog object.
     * 
     * @param rs The ResultSet from a SQL query.
     * @return A BreakLog object populated with data from the current row of the ResultSet.
     * @throws SQLException If an error occurs accessing the ResultSet.
     */
    private BreakLog mapResultSetToBreakLog(ResultSet rs) throws SQLException {
        return new BreakLog.Builder(
            rs.getInt("operator_id"),
            rs.getString("reason"),
            rs.getTimestamp("timestamp")
        ).build();
    }
}
