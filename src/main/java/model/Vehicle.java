/*
 * File name: Vehicle.java
 * Author: Tasmia Tabassom Aboni, 041130376
 * Course: 25S CST8288 Section 024 
 * Assignment: Final Project
 * Date: Aug 6, 2025
 * Professor: Marwan Farah
  */


package model;

/**
 * Represents a Vehicle in the system.
 * 
 * @author Tasmia Aboni
 */
public class Vehicle {
    /**
     * The unique identifier for the vehicle.
     */
    private final int vehicleId;

    /**
     * The type of the vehicle (e.g., bus, train).
     */
    private final VehicleType type;

    /**
     * The type of fuel used by the vehicle (e.g., "Diesel", "Electric").
     */
    private final String fuelType;

    /**
     * The fuel consumption rate of the vehicle (e.g., liters per kilometer or kWh per kilometer).
     */
    private final double consumptionRate;

    /**
     * The maximum number of passengers the vehicle can accommodate.
     */
    private final int maxPassengers;

    /**
     * The unique identifier for the vehicle (e.g., license plate or registration number).
     */
    private final String vehicleNumber;

    /**
     * The ID of the route associated with the vehicle.
     */
    private final int routeId;

    /**
     * Private constructor to enforce the use of the Builder for creating instances.
     *
     * @param builder The Builder instance used to construct this Vehicle object.
     */
    private Vehicle(Builder builder) {
        this.vehicleId = builder.vehicleId;
        this.type = builder.type;
        this.fuelType = builder.fuelType;
        this.consumptionRate = builder.consumptionRate;
        this.maxPassengers = builder.maxPassengers;
        this.vehicleNumber = builder.vehicleNumber; // Initialize vehicle number
        this.routeId = builder.routeId;            // Initialize route ID
    }

    /**
     * Retrieves the unique identifier for the vehicle.
     *
     * @return The vehicle ID.
     */
    public int getVehicleId() { return vehicleId; }

    /**
     * Retrieves the type of the vehicle.
     *
     * @return The vehicle type.
     */
    public VehicleType getType() { return type; }

    /**
     * Retrieves the type of fuel used by the vehicle.
     *
     * @return The fuel type.
     */
    public String getFuelType() { return fuelType; }

    /**
     * Retrieves the fuel consumption rate of the vehicle.
     *
     * @return The consumption rate.
     */
    public double getConsumptionRate() { return consumptionRate; }

    /**
     * Retrieves the maximum number of passengers the vehicle can accommodate.
     *
     * @return The maximum passenger capacity.
     */
    public int getMaxPassengers() { return maxPassengers; }

    /**
     * Retrieves the unique identifier for the vehicle (e.g., license plate or registration number).
     *
     * @return The vehicle number.
     */
    public String getVehicleNumber() { return vehicleNumber; }

    /**
     * Retrieves the ID of the route associated with the vehicle.
     *
     * @return The route ID.
     */
    public int getRouteId() { return routeId; }

    /**
     * Returns a string representation of the Vehicle object.
     *
     * @return A string containing the vehicle ID, type, fuel type, consumption rate,
     *         maximum passengers, vehicle number, and route ID.
     */
    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleId=" + vehicleId +
                ", type=" + type +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                ", fuelType='" + fuelType + '\'' +
                ", consumptionRate=" + consumptionRate +
                ", maxPassengers=" + maxPassengers +
                ", routeId=" + routeId +
                '}';
    }

    /**
     * A builder class for constructing immutable Vehicle objects.
     */
    public static class Builder {
        private final int vehicleId;
        private final VehicleType type;
        private String fuelType;
        private double consumptionRate;
        private int maxPassengers;
        private String vehicleNumber; // New field for vehicle number
        private int routeId;          // Route ID field

        /**
         * Constructs a Builder with mandatory fields for creating a Vehicle object.
         *
         * @param vehicleId The unique identifier for the vehicle.
         * @param type      The type of the vehicle (e.g., bus, train).
         * @throws IllegalArgumentException If the vehicle type is null.
         */
        public Builder(int vehicleId, VehicleType type) {
            if (type == null) {
                throw new IllegalArgumentException("Vehicle type cannot be null");
            }
            this.vehicleId = vehicleId;
            this.type = type;
            this.fuelType = type.getDefaultFuelType(); // Default fuel type based on vehicle type
        }

        /**
         * Sets the fuel type for the Vehicle object.
         *
         * @param fuelType The type of fuel used by the vehicle.
         * @return This Builder instance for method chaining.
         */
        public Builder withFuelType(String fuelType) {
            this.fuelType = fuelType;
            return this;
        }

        /**
         * Sets the fuel consumption rate for the Vehicle object.
         *
         * @param rate The fuel consumption rate (must be non-negative).
         * @return This Builder instance for method chaining.
         * @throws IllegalArgumentException If the rate is negative.
         */
        public Builder withConsumptionRate(double rate) {
            if (rate < 0) throw new IllegalArgumentException("Rate cannot be negative");
            this.consumptionRate = rate;
            return this;
        }

        /**
         * Sets the maximum number of passengers for the Vehicle object.
         *
         * @param passengers The maximum number of passengers (must be positive).
         * @return This Builder instance for method chaining.
         * @throws IllegalArgumentException If the capacity is not positive.
         */
        public Builder withMaxPassengers(int passengers) {
            if (passengers <= 0) throw new IllegalArgumentException("Capacity must be positive");
            this.maxPassengers = passengers;
            return this;
        }

        /**
         * Sets the vehicle number for the Vehicle object.
         *
         * @param vehicleNumber The unique identifier for the vehicle (e.g., license plate).
         * @return This Builder instance for method chaining.
         */
        public Builder withVehicleNumber(String vehicleNumber) {
            this.vehicleNumber = vehicleNumber; // Set vehicle number
            return this;
        }

        /**
         * Sets the route ID for the Vehicle object.
         *
         * @param routeId The ID of the route associated with the vehicle.
         * @return This Builder instance for method chaining.
         */
        public Builder withRouteId(int routeId) {
            this.routeId = routeId; // Set route ID
            return this;
        }

        /**
         * Builds and validates the Vehicle object.
         *
         * @return A new Vehicle object constructed using this Builder.
         * @throws IllegalStateException If validation fails (e.g., missing vehicle number
         *                               or invalid train capacity).
         */
        public Vehicle build() {
            validate();
            return new Vehicle(this);
        }

        /**
         * Validates the fields of the Builder before constructing the Vehicle object.
         *
         * @throws IllegalStateException If the vehicle number is null or empty, or if the
         *                               train capacity is less than 100.
         */
        private void validate() {
            // Validate train capacity
            if (type == VehicleType.TRAIN && maxPassengers < 100) {
                throw new IllegalStateException("Trains must have capacity ≥ 100");
            }

            // Validate vehicle number
            if (vehicleNumber == null || vehicleNumber.isEmpty()) {
                throw new IllegalStateException("Vehicle number must be provided");
            }

            // Validate route ID (optional)
//            if (routeId == null) {
//                throw new IllegalStateException("Route ID must be provided");
//            }
        }
    }
}