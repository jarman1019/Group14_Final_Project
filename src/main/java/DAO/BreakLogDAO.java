/*
 * File name: BreakLogDAO.java
 * Author: Jarmanjit Singh, 041154515
 * Course: 25S CST8288 Section 024 
 * Assignment: Final Project
 * Date: Aug 7, 2025
 * Professor: Marwan Farah
 * Purpose: DAO for managing BreakLog records.
 */

package DAO;

import model.BreakLog;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BreakLogDAO {

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

    private BreakLog mapResultSetToBreakLog(ResultSet rs) throws SQLException {
        return new BreakLog.Builder(
            rs.getInt("operator_id"),
            rs.getString("reason"),
            rs.getTimestamp("timestamp")
        ).build();
    }
}
