/*
 * File name: VehicleType.java
 * Author: Tasmia Tabassom Aboni, 041130376
 * Course: 25S CST8288 Section 024 
 * Assignment: Final Project
 * Date: Aug 6, 2025
 * Professor: Marwan Farah
  */


package model;

/**
 * Represents the type of vehicle in the system.
 * @author  Tasmia Aboni
 */
public enum VehicleType {
    /**
     * Represents a bus powered by diesel.
     */
    Bus("Diesel"),

    /**
     * Represents a van powered by petrol.
     */
    Van("Petrol"),

    /**
     * Represents a taxi powered by CNG (Compressed Natural Gas).
     */
    Taxi("CNG"),

    /**
     * Represents another variant of a taxi powered by CNG (Compressed Natural Gas).
     */
    TAXI("CNG"),

    /**
     * Represents a truck powered by diesel.
     */
    Truck("Diesel"),

    /**
     * Represents another variant of a bus powered by CNG (Compressed Natural Gas).
     */
    BUS("CNG"),

    /**
     * Represents a minibus powered by diesel.
     */
    Minibus("Diesel"),

    /**
     * Represents a diesel-electric train powered by electricity.
     */
    DIESEL_ELECTRIC_TRAIN("Electric");

    /**
     * The default fuel type associated with the vehicle type.
     */
    private final String defaultFuelType;

    /**
     * Constructs a VehicleType with the specified default fuel type.
     *
     * @param defaultFuelType The default fuel type for the vehicle type.
     */
    VehicleType(String defaultFuelType) {
        this.defaultFuelType = defaultFuelType;
    }

    /**
     * Retrieves the default fuel type associated with the vehicle type.
     *
     * @return The default fuel type.
     */
    public String getDefaultFuelType() {
        return defaultFuelType;
    }
}