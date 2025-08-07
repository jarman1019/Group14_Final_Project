/*
 * File name: IGPS.java
 * Author: Jarmanjit Singh, 041154515
 * Course: 25S CST8288 Section 024
 * Assignment: Final Project
 * Date: Aug 9, 2025
 * Professor: Marwan Farah
 * Purpose:
 *   Defines the GPS interface for accessing and updating vehicle location data,
 *   including current position retrieval and historical location queries.
 */

package patterns.adapter;

import java.util.Date;
import java.util.List;
import model.Location;

/**
 * Interface representing GPS operations.
 * Provides methods for retrieving current location, updating location,
 * and obtaining historical location data for a vehicle.
 * 
 * @author Jarmanjit Singh
 * @since 21
 * @version 1.0
 */
public interface IGPS {

    /**
     * Retrieves the current location of a vehicle.
     * @param vehicleId The unique identifier of the vehicle.
     * @return The current Location of the vehicle.
     */
    Location getCurrentLocation(String vehicleId);

    /**
     * Updates the location of a vehicle.
     * @param vehicleId The unique identifier of the vehicle.
     * @param latitude The latitude coordinate.
     * @param longitude The longitude coordinate.
     */
    void updateLocation(String vehicleId, double latitude, double longitude);

    /**
     * Retrieves the historical location data for a vehicle
     * between specified start and end dates.
     * @param vehicleId The unique identifier of the vehicle.
     * @param from The start date/time of the query range.
     * @param to The end date/time of the query range.
     * @return A list of Location objects within the date range.
     */
    List<Location> getLocationHistory(String vehicleId, Date from, Date to);
}
