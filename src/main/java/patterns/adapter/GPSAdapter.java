/*
 * File name: GPSAdapter.java
 * Author: Jarmanjit Singh, 041154515
 * Course: 25S CST8288 Section 024
 * Assignment: Final Project
 * Date: Aug 9, 2025
 * Professor: Marwan Farah
 * Purpose:
 *   Adapter class that bridges the LegacyGPS system to the IGPS interface,
 *   converting legacy GPS data formats to modern Location objects and vice versa.
 */

package patterns.adapter;

import model.Location;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Adapter implementation to integrate LegacyGPS with IGPS interface.
 * Converts legacy string GPS data to Location objects and handles updates.
 * 
 * @author Jarmanjit Singh
 * @since 21
 * @version 1.0
 */
public class GPSAdapter implements IGPS {

    /**
     * Legacy GPS instance that this adapter wraps.
     */
    private LegacyGPS legacyGPS;

    /**
     * Date format used to parse and format date strings in legacy data.
     */
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * Constructs the GPSAdapter with the given LegacyGPS instance.
     * @param legacyGPS the legacy GPS system instance
     */
    public GPSAdapter(LegacyGPS legacyGPS) {
        this.legacyGPS = legacyGPS;
    }

    /**
     * Retrieves the current location of the vehicle from legacy data.
     * @param vehicleId the ID of the vehicle
     * @return Location object representing the current position
     */
    @Override
    public Location getCurrentLocation(String vehicleId) {
        String data = legacyGPS.fetchPosition(vehicleId);
        return convertToLocation(vehicleId, data);
    }

    /**
     * Updates the vehicle location in the legacy GPS system.
     * @param vehicleId the ID of the vehicle
     * @param latitude latitude coordinate
     * @param longitude longitude coordinate
     */
    @Override
    public void updateLocation(String vehicleId, double latitude, double longitude) {
        String coords = latitude + ":" + longitude + ":" + System.currentTimeMillis();
        legacyGPS.pushPosition(vehicleId, coords);
    }

    /**
     * Retrieves the historical location data for a vehicle between two dates.
     * Converts legacy data strings to Location objects.
     * 
     * @param vehicleId the ID of the vehicle
     * @param from start date/time for history range
     * @param to end date/time for history range
     * @return List of Location objects within the specified date range
     */
    @Override
    public List<Location> getLocationHistory(String vehicleId, Date from, Date to) {
        String[] data = legacyGPS.getHistoricalData(
            vehicleId, 
            DATE_FORMAT.format(from), 
            DATE_FORMAT.format(to)
        );
        
        return Arrays.stream(data)
                   .map(raw -> convertToLocation(vehicleId, raw))
                   .collect(Collectors.toList());
    }

    /**
     * Converts a legacy GPS data string to a Location object.
     * Expected format: "latitude,longitude,timestamp"
     * 
     * @param vehicleId the ID of the vehicle
     * @param data raw legacy GPS data string
     * @return Location object parsed from the data string
     */
    private Location convertToLocation(String vehicleId, String data) {
        String[] parts = data.split(",");
        return new Location.Builder(
            Double.parseDouble(parts[0]),
            Double.parseDouble(parts[1]),
            new Date(Long.parseLong(parts[2])),
            vehicleId
        ).build();
    }
}
