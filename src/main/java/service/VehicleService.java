/*
 * File name: VehicleService.java
 * Author: Tasmia Tabassom Aboni, 041130376
 * Course: 25S CST8288 Section 024 
 * Assignment: Final Project
 * Date: Aug 6, 2025
 * Professor: Marwan Farah
 */

package service;

import DAO.VehicleDAO;
import model.Vehicle;
import model.VehicleType;
import java.sql.SQLException;
import java.util.List;

/**
 * Service class to manage vehicle-related operations in the system.
 * @author  Tasmia
 */
public class VehicleService {
    private final VehicleDAO vehicleDAO;

    /**
     * Constructs a VehicleService with dependency injection.
     *
     * @param vehicleDAO Data access object for vehicles.
     */
    public VehicleService(VehicleDAO vehicleDAO) {
        this.vehicleDAO = vehicleDAO;
    }

    /**
     * Registers a new vehicle in the system.
     * @param vehicle The vehicle to register.
     * @throws SQLException           If a database operation fails.
     * @throws IllegalArgumentException If the vehicle data is invalid.
     */
    public void registerVehicle(Vehicle vehicle) throws SQLException, IllegalArgumentException {
        validateVehicle(vehicle);
        vehicleDAO.insertVehicle(vehicle);
    }

    /**
     * Retrieves a vehicle by its ID.
     *
     * @param vehicleId The ID of the vehicle.
     * @return The vehicle object if found; otherwise, null.
     * @throws SQLException If a database operation fails.
     */
    public Vehicle getVehicleById(int vehicleId) throws SQLException {
        return vehicleDAO.selectVehicle(vehicleId);
    }

    /**
     * Retrieves all vehicles in the system.
     *
     * @return A list of all vehicles.
     * @throws SQLException If a database operation fails.
     */
    public List<Vehicle> getAllVehicles() throws SQLException {
        return vehicleDAO.selectAllVehicles();
    }

    /**
     * Updates the information of an existing vehicle.
     * @param vehicle The updated vehicle data.
     * @return True if the update was successful; otherwise, false.
     * @throws SQLException           If a database operation fails.
     * @throws IllegalArgumentException If the vehicle data is invalid.
     */
    public boolean updateVehicle(Vehicle vehicle) throws SQLException {
        validateVehicle(vehicle);
        return vehicleDAO.updateVehicle(vehicle);
    }

    /**
     * Deletes a vehicle from the system based on its ID.
     *
     * @param vehicleId The ID of the vehicle to delete.
     * @return True if the deletion was successful; otherwise, false.
     * @throws SQLException If a database operation fails.
     */
    public boolean deleteVehicle(int vehicleId) throws SQLException {
        return vehicleDAO.deleteVehicle(vehicleId);
    }

    /**
     * Assigns a route to a vehicle.
     * @param vehicleId The ID of the vehicle to assign the route to.
     * @param routeId   The ID of the route to assign.
     * @return True if the assignment was successful; otherwise, false.
     * @throws SQLException If a database operation fails.
     */
    public boolean assignRoute(int vehicleId, int routeId) throws SQLException {
        Vehicle vehicle = vehicleDAO.selectVehicle(vehicleId);
        if (vehicle == null) {
            return false;
        }

        Vehicle updatedVehicle = new Vehicle.Builder(vehicleId, vehicle.getType())
                .withVehicleNumber(vehicle.getVehicleNumber())
                .withFuelType(vehicle.getFuelType())
                .withConsumptionRate(vehicle.getConsumptionRate())
                .withMaxPassengers(vehicle.getMaxPassengers())
                .withRouteId(routeId)
                .build();

        return vehicleDAO.updateVehicle(updatedVehicle);
    }

    /**
     * Validates vehicle business rules.
     * @param vehicle The vehicle to validate.
     * @throws IllegalArgumentException If any validation rule is violated.
     */
    private void validateVehicle(Vehicle vehicle) throws IllegalArgumentException {
        if (vehicle.getVehicleNumber() == null || vehicle.getVehicleNumber().trim().isEmpty()) {
            throw new IllegalArgumentException("Vehicle number cannot be empty");
        }

        if (vehicle.getType() == VehicleType.TRAIN && vehicle.getMaxPassengers() < 100) {
            throw new IllegalArgumentException("Trains must have capacity for at least 100 passengers");
        }

        if (vehicle.getConsumptionRate() < 0) {
            throw new IllegalArgumentException("Fuel consumption rate cannot be negative");
        }

        if (vehicle.getMaxPassengers() <= 0) {
            throw new IllegalArgumentException("Passenger capacity must be positive");
        }
    }

    /**
     * Gets the fuel efficiency description for a vehicle.
     * @param vehicleId The ID of the vehicle.
     * @return A description of the vehicle's fuel efficiency.
     * @throws SQLException           If a database operation fails.
     * @throws IllegalArgumentException If the vehicle is not found.
     */
    public String getFuelEfficiencyDescription(int vehicleId) throws SQLException {
        Vehicle vehicle = vehicleDAO.selectVehicle(vehicleId);
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle not found");
        }

        return String.format("%s (%.2f %s/km)",
                vehicle.getType().getDefaultFuelType(),
                vehicle.getConsumptionRate(),
                vehicle.getType() == VehicleType.TRAIN ? "L" : "kWh");
    }
}