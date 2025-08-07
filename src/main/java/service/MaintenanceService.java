/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import DAO.MaintenanceDAO;
import model.Maintenance;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Kunj
 */
/**
 * Service class for handling maintenance-related business logic.
 * Implements the business layer between presentation and data access layers.
 */
public class MaintenanceService {
    private final MaintenanceDAO maintenanceDAO;

    /**
     * Constructs a MaintenanceService with dependency injection.
     * @param maintenanceDAO Data access object for maintenance records
     */
    public MaintenanceService(MaintenanceDAO maintenanceDAO) {
        this.maintenanceDAO = maintenanceDAO;
    }

    /**
     * Creates a new maintenance record.
     * @param maintenance Maintenance record to create
     * @throws SQLException if database operation fails
     * @throws IllegalArgumentException if maintenance data is invalid
     */
    public void createMaintenance(Maintenance maintenance) throws SQLException, IllegalArgumentException {
        validateMaintenance(maintenance);
        maintenanceDAO.insertMaintenance(maintenance);
    }

    /**
     * Retrieves a maintenance record by ID.
     * @param maintenanceId ID of the maintenance record
     * @return Maintenance object if found
     * @throws SQLException if database operation fails
     */
    public Maintenance getMaintenanceById(int maintenanceId) throws SQLException {
        return maintenanceDAO.selectMaintenance(maintenanceId);
    }

    /**
     * Retrieves all maintenance records.
     * @return List of all maintenance records
     * @throws SQLException if database operation fails
     */
    public List<Maintenance> getAllMaintenanceRecords() throws SQLException {
        return maintenanceDAO.selectAllMaintenances();
    }

    /**
     * Retrieves maintenance records for a specific vehicle.
     * @param vehicleId ID of the vehicle
     * @return List of maintenance records for the vehicle
     * @throws SQLException if database operation fails
     */
    public List<Maintenance> getMaintenanceByVehicleId(int vehicleId) throws SQLException {
        List<Maintenance> allRecords = maintenanceDAO.selectAllMaintenances();
        return allRecords.stream()
                .filter(m -> m.getVehicleId() == vehicleId)
                .toList();
    }

    /**
     * Updates a maintenance record.
     * @param maintenance Updated maintenance data
     * @return true if update was successful
     * @throws SQLException if database operation fails
     * @throws IllegalArgumentException if maintenance data is invalid
     */
    public boolean updateMaintenance(Maintenance maintenance) throws SQLException, IllegalArgumentException {
        validateMaintenance(maintenance);
        return maintenanceDAO.updateMaintenance(maintenance);
    }

    /**
     * Deletes a maintenance record.
     * @param maintenanceId ID of the maintenance record to delete
     *  @return true if deletion was successful
     * @throws SQLException if database operation fails
     */
    public boolean deleteMaintenance(int maintenanceId) throws SQLException {
        return maintenanceDAO.deleteMaintenance(maintenanceId);
    }

    /**
     * Checks for upcoming maintenance alerts (within 7 days).
     * @return List of maintenance records needing attention
     * @throws SQLException if database operation fails
     */
    public List<Maintenance> getUpcomingMaintenanceAlerts() throws SQLException {
        LocalDate today = LocalDate.now();
        LocalDate weekFromNow = today.plusDays(7);
        
        return maintenanceDAO.selectAllMaintenances().stream()
                .filter(m -> !m.getScheduledDate().isBefore(today))
                .filter(m -> !m.getScheduledDate().isAfter(weekFromNow))
                .toList();
    }

    /**
     * Validates maintenance business rules.
     * @param maintenance Maintenance record to validate
     * @throws IllegalArgumentException if validation fails
     */
    private void validateMaintenance(Maintenance maintenance) throws IllegalArgumentException {
        if (maintenance.getAlertType() == null || maintenance.getAlertType().trim().isEmpty()) {
            throw new IllegalArgumentException("Alert type cannot be empty");
        }
        if (maintenance.getScheduledDate() == null) {
            throw new IllegalArgumentException("Scheduled date cannot be empty");
        }
        if (maintenance.getScheduledDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Scheduled date cannot be in the past");
        }
    }
}