/*
 * File name: Location.java
 * Author: Jarmanjit Singh, 041154515
 * Course: 25S CST8288 Section 024 
 * Assignment: Final Project
 * Date: Aug 9, 2025
 * Professor: Marwan Farah
 * Purpose: 
 *   This class represents a geographic location record for a vehicle at a specific timestamp.
 *   It uses the Builder pattern to create immutable instances containing latitude, longitude,
 *   timestamp, and vehicle identifier with validation on coordinates.
 */

package model;

import java.util.Date;

/**
 * Represents a geographic location record of a vehicle at a specific timestamp.
 * Immutable class built using the Builder pattern.
 * 
 * Stores latitude, longitude, timestamp of the location recording, and vehicle ID.
 * Latitude must be between -90 and 90, longitude between -180 and 180 degrees.
 * 
 * @author Jarmanjit Singh
 * @since 21
 * @version 1.0
 */
public class Location {

    /** Latitude coordinate in degrees, range [-90, 90] */
    private final double latitude;

    /** Longitude coordinate in degrees, range [-180, 180] */
    private final double longitude;

    /** Timestamp of when the location was recorded */
    private final Date timestamp;

    /** Identifier of the vehicle associated with this location */
    private final String vehicleId;

    /**
     * Private constructor to enforce usage of Builder for instance creation.
     * 
     * @param builder Builder instance containing the data
     */
    private Location(Builder builder) {
        this.latitude = builder.latitude;
        this.longitude = builder.longitude;
        this.timestamp = builder.timestamp;
        this.vehicleId = builder.vehicleId;
    }

    /**
     * Gets the latitude coordinate.
     * 
     * @return Latitude in degrees
     */
    public double getLatitude() { 
        return latitude; 
    }

    /**
     * Gets the longitude coordinate.
     * 
     * @return Longitude in degrees
     */
    public double getLongitude() { 
        return longitude; 
    }

    /**
     * Gets the timestamp of the location record.
     * 
     * @return Timestamp as a {@link Date} object
     */
    public Date getTimestamp() { 
        return timestamp; 
    }

    /**
     * Gets the vehicle identifier associated with this location.
     * 
     * @return Vehicle ID string
     */
    public String getVehicleId() { 
        return vehicleId; 
    }

    /**
     * Builder class for constructing immutable {@link Location} instances.
     */
    public static class Builder {

        /** Latitude coordinate in degrees */
        private final double latitude;

        /** Longitude coordinate in degrees */
        private final double longitude;

        /** Timestamp of the location */
        private final Date timestamp;

        /** Vehicle identifier */
        private final String vehicleId;

        /**
         * Constructs a Builder with required location parameters.
         * Validates latitude and longitude ranges.
         * 
         * @param latitude Latitude coordinate [-90, 90]
         * @param longitude Longitude coordinate [-180, 180]
         * @param timestamp Date and time of the location recording
         * @param vehicleId Identifier for the vehicle
         * @throws IllegalArgumentException if latitude or longitude are out of range
         */
        public Builder(double latitude, double longitude, Date timestamp, String vehicleId) {
            validateCoordinates(latitude, longitude);
            this.latitude = latitude;
            this.longitude = longitude;
            this.timestamp = timestamp;
            this.vehicleId = vehicleId;
        }

        /**
         * Validates that the latitude and longitude are within valid geographic bounds.
         * 
         * @param lat Latitude to validate
         * @param lng Longitude to validate
         * @throws IllegalArgumentException if coordinates are invalid
         */
        private void validateCoordinates(double lat, double lng) {
            if (lat < -90 || lat > 90) {
                throw new IllegalArgumentException("Latitude must be between -90 and 90");
            }
            if (lng < -180 || lng > 180) {
                throw new IllegalArgumentException("Longitude must be between -180 and 180");
            }
        }

        /**
         * Builds and returns the {@link Location} instance.
         * 
         * @return New Location object
         */
        public Location build() {
            return new Location(this);
        }
    }

    /**
     * Returns a string representation of the Location object.
     * 
     * @return Formatted string showing vehicle ID, coordinates, and timestamp
     */
    @Override
    public String toString() {
        return String.format(
            "Location[Vehicle: %s, Coords: (%.6f, %.6f), Time: %s]",
            vehicleId, latitude, longitude, timestamp
        );
    }
}
