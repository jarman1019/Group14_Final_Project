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
    BUS("Diesel"),

    
    /**
     * Represents an electric-light rail powered by electricity.
     */
    LIGHTRAIL("Electric"),

    /**
     * Represents a diesel-electric train powered by electricity.
     */
    TRAIN("Electric");

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