package DAO;


import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Maintenance;
/**
 * This class provides data access operations for the Maintenance entity.
 * It interacts with the database to perform CRUD (Create, Read, Update, Delete)
 * operations on the Maintenance table.

 * @author Kunj
 * @author Kunj
 */
public class MaintenanceDAO {

    // SQL Queries
    private static final String INSERT_MAINTENANCE_SQL = 
        "INSERT INTO Maintenance (vehicle_id, alert_type, scheduled_date) VALUES (?, ?, ?)";
    private static final String SELECT_MAINTENANCE_BY_ID = 
        "SELECT * FROM Maintenance WHERE maintenance_id = ?";
    private static final String SELECT_ALL_MAINTENANCES = 
        "SELECT * FROM Maintenance";
    private static final String UPDATE_MAINTENANCE_SQL = 
        "UPDATE Maintenance SET vehicle_id = ?, alert_type = ?, scheduled_date = ? WHERE maintenance_id = ?";
    private static final String DELETE_MAINTENANCE_SQL = 
        "DELETE FROM Maintenance WHERE maintenance_id = ?";

    /**
     * Inserts a new maintenance record into the database.
     *
     *
     * @param maintenance The Maintenance object to be inserted.
     * @throws SQLException If a database access error occurs or the insertion fails.
     */
    public void insertMaintenance(Maintenance maintenance) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MAINTENANCE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, maintenance.getVehicleId());
            preparedStatement.setString(2, maintenance.getAlertType());
            preparedStatement.setDate(3, Date.valueOf(maintenance.getScheduledDate()));

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating maintenance record failed, no rows affected.");
            }
        }
    }

    /**
     * Retrieves a maintenance record by its ID.
     *
     * @param maintenanceId The ID of the maintenance record to retrieve.
     * @return The Maintenance object if found; otherwise, null.
     * @throws SQLException If a database access error occurs or the query fails.
     */
    public Maintenance selectMaintenance(int maintenanceId) throws SQLException {
        Maintenance maintenance = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MAINTENANCE_BY_ID)) {
            preparedStatement.setInt(1, maintenanceId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int vehicleId = resultSet.getInt("vehicle_id");
                String alertType = resultSet.getString("alert_type");
                LocalDate scheduledDate = resultSet.getDate("scheduled_date").toLocalDate();

                maintenance = new Maintenance.Builder(vehicleId, alertType, scheduledDate)
                        .withMaintenanceId(maintenanceId)
                        .build();
            }
        }
        return maintenance;
    }

    /**
     * Retrieves all maintenance records from the database.
     * @return A list of all Maintenance objects, or an empty list if no records exist.
     * @throws SQLException If a database access error occurs or the query fails.
     */
    public List<Maintenance> selectAllMaintenances() throws SQLException {
        List<Maintenance> maintenances = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MAINTENANCES)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int maintenanceId = resultSet.getInt("maintenance_id");
                int vehicleId = resultSet.getInt("vehicle_id");
                String alertType = resultSet.getString("alert_type");
                LocalDate scheduledDate = resultSet.getDate("scheduled_date").toLocalDate();

                Maintenance maintenance = new Maintenance.Builder(vehicleId, alertType, scheduledDate)
                        .withMaintenanceId(maintenanceId)
                        .build();
                maintenances.add(maintenance);
            }
        }
        return maintenances;
    }

    /**
     * Updates an existing maintenance record in the database.
     * @param maintenance The updated Maintenance object.
     * @return True if the update was successful; otherwise, false.
     * @throws SQLException If a database access error occurs or the update fails.
     */
    public boolean updateMaintenance(Maintenance maintenance) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MAINTENANCE_SQL)) {
            preparedStatement.setInt(1, maintenance.getVehicleId());
            preparedStatement.setString(2, maintenance.getAlertType());
            preparedStatement.setDate(3, Date.valueOf(maintenance.getScheduledDate()));
            preparedStatement.setInt(4, maintenance.getMaintenanceId());

            rowUpdated = preparedStatement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    /**
     * Deletes a maintenance record from the database by its ID.
     * @param maintenanceId The ID of the maintenance record to delete.
     * @return True if the deletion was successful; otherwise, false.
     * @throws SQLException If a database access error occurs or the deletion fails.
     */
    public boolean deleteMaintenance(int maintenanceId) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_MAINTENANCE_SQL)) {
            preparedStatement.setInt(1, maintenanceId);

            rowDeleted = preparedStatement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
}