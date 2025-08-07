/*
 * File name: VhichileDAO.java
 * Author: Tasmia Tabassom Aboni, 041130376
 * Course: 25S CST8288 Section 024 
 * Assignment: Final Project
 * Date: Aug 6, 2025
 * Professor: Marwan Farah
 */

package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Vehicle;
import model.VehicleType;
/**
 * This class provides data access operations for the Vehicle entity.
 * It interacts with the database to perform CRUD (Create, Read, Update, Delete)
 * operations on the Vehicle table.
 * @author Rex
 */
public class VehicleDAO {
    // Updated SQL Queries to include vehicle_number
    private static final String INSERT_VEHICLE_SQL = 
        "INSERT INTO Vehicle (vehicle_type, vehicle_number, fuel_type, consumption_rate, max_passengers, route_id) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_VEHICLE_BY_ID = 
        "SELECT * FROM Vehicle WHERE vehicle_id = ?";
    private static final String SELECT_ALL_VEHICLES = 
        "SELECT * FROM Vehicle";
    private static final String UPDATE_VEHICLE_SQL = 
        "UPDATE Vehicle SET vehicle_type = ?, vehicle_number = ?, fuel_type = ?, consumption_rate = ?, max_passengers = ? WHERE vehicle_id = ?";
    private static final String DELETE_VEHICLE_SQL = 
        "DELETE FROM Vehicle WHERE vehicle_id = ?";

    /**
     * Inserts a new vehicle into the database.
     * @param vehicle The Vehicle object to be inserted.
     * @throws SQLException If a database access error occurs or the insertion fails.
     */
    public void insertVehicle(Vehicle vehicle) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_VEHICLE_SQL)) {
            preparedStatement.setString(1, vehicle.getType().toString());
            preparedStatement.setString(2, vehicle.getVehicleNumber()); // Include vehicle number
            preparedStatement.setString(3, vehicle.getFuelType());
            preparedStatement.setDouble(4, vehicle.getConsumptionRate());
            preparedStatement.setInt(5, vehicle.getMaxPassengers());
            preparedStatement.setInt(6, vehicle.getRouteId());
            preparedStatement.executeUpdate();
        }
    }

    /**
     * Retrieves a vehicle by its ID.
     * @param vehicleId The ID of the vehicle to retrieve.
     * @return The Vehicle object if found; otherwise, null.
     * @throws SQLException If a database access error occurs or the query fails.
     */
    public Vehicle selectVehicle(int vehicleId) throws SQLException {
        Vehicle vehicle = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_VEHICLE_BY_ID)) {
            preparedStatement.setInt(1, vehicleId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String vehicleType = resultSet.getString("vehicle_type");
                String vehicleNumber = resultSet.getString("vehicle_number"); // Retrieve vehicle number
                String fuelType = resultSet.getString("fuel_type");
                double consumptionRate = resultSet.getDouble("consumption_rate");
                int maxPassengers = resultSet.getInt("max_passengers");
                int routeId = resultSet.getInt("route_id");

                VehicleType type = VehicleType.valueOf(vehicleType);
                vehicle = new Vehicle.Builder(vehicleId, type)
                        .withVehicleNumber(vehicleNumber) // Set vehicle number
                        .withFuelType(fuelType)
                        .withConsumptionRate(consumptionRate)
                        .withMaxPassengers(maxPassengers)
                        .withRouteId(routeId)
                        .build();
            }
        }
        return vehicle;
    }

    /**
     * Retrieves all vehicles from the database.
     * @return A list of all Vehicle objects, or an empty list if no records exist.
     * @throws SQLException If a database access error occurs or the query fails.
     */
    public List<Vehicle> selectAllVehicles() throws SQLException {
        List<Vehicle> vehicles = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_VEHICLES)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int vehicleId = resultSet.getInt("vehicle_id");
                String vehicleType = resultSet.getString("vehicle_type");
                String vehicleNumber = resultSet.getString("vehicle_number"); // Retrieve vehicle number
                String fuelType = resultSet.getString("fuel_type");
                double consumptionRate = resultSet.getDouble("consumption_rate");
                int maxPassengers = resultSet.getInt("max_passengers");
                int routeId = resultSet.getInt("route_id");

                VehicleType type = VehicleType.valueOf(vehicleType);
                Vehicle vehicle = new Vehicle.Builder(vehicleId, type)
                        .withVehicleNumber(vehicleNumber) // Set vehicle number
                        .withFuelType(fuelType)
                        .withConsumptionRate(consumptionRate)
                        .withMaxPassengers(maxPassengers)
                        .withRouteId(routeId)
                        .build();
                vehicles.add(vehicle);
            }
        }
        return vehicles;
    }

    /**
     * Updates an existing vehicle in the database.
     * @param vehicle The updated Vehicle object.
     * @return True if the update was successful; otherwise, false.
     * @throws SQLException If a database access error occurs or the update fails.
     */
    public boolean updateVehicle(Vehicle vehicle) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_VEHICLE_SQL)) {
            preparedStatement.setString(1, vehicle.getType().toString());
            preparedStatement.setString(2, vehicle.getVehicleNumber()); // Include vehicle number
            preparedStatement.setString(3, vehicle.getFuelType());
            preparedStatement.setDouble(4, vehicle.getConsumptionRate());
            preparedStatement.setInt(5, vehicle.getMaxPassengers());
            preparedStatement.setInt(6, vehicle.getVehicleId());

            rowUpdated = preparedStatement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    /**
     * Deletes a vehicle from the database by its ID.
     * @param vehicleId The ID of the vehicle to delete.
     * @return True if the deletion was successful; otherwise, false.
     * @throws SQLException If a database access error occurs or the deletion fails.
     */
    public boolean deleteVehicle(int vehicleId) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_VEHICLE_SQL)) {
            preparedStatement.setInt(1, vehicleId);

            rowDeleted = preparedStatement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
}