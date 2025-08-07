/*
 * File name: LegacyGPS.java
 * Author: Jarmanjit Singh, 041154515
 * Course: 25S CST8288 Section 024
 * Assignment: Final Project
 * Date: Aug 9, 2025
 * Professor: Marwan Farah
 * Purpose:
 *   Acts as an adapter to a third-party legacy GPS system with a different interface.
 *   Provides methods to fetch current position, push updated position, 
 *   and retrieve historical location data as raw string formats.
 */

package patterns.adapter;

/**
 * Represents a legacy third-party GPS system with a non-standard interface.
 * Methods provide access to current position, update position, 
 * and fetch historical location data using string-encoded coordinates.
 * 
 * @author Jarmanjit Singh
 * @since 21
 * @version 1.0
 */
public class LegacyGPS {

    /**
     * Fetches the current position for the given device.
     * Returns a string formatted as "latitude,longitude,timestamp".
     * 
     * @param deviceId The unique identifier of the GPS device.
     * @return The current position string.
     */
    public String fetchPosition(String deviceId) {
        return "45.4215,-75.6972," + System.currentTimeMillis();
    }

    /**
     * Pushes an updated position to the GPS system.
     * Expects coordinates in the format "lat:long:time".
     * 
     * @param deviceId The unique identifier of the GPS device.
     * @param coords The coordinate string to push.
     */
    public void pushPosition(String deviceId, String coords) {
        System.out.println("Legacy GPS updated: " + deviceId + " - " + coords);
    }

    /**
     * Retrieves historical location data between specified start and end timestamps.
     * Returns an array of strings formatted as "lat,long,time".
     * 
     * @param deviceId The unique identifier of the GPS device.
     * @param start The start timestamp as a string.
     * @param end The end timestamp as a string.
     * @return An array of location strings.
     */
    public String[] getHistoricalData(String deviceId, String start, String end) {
        return new String[]{
            "45.4215,-75.6972," + (System.currentTimeMillis() - 3600000),
            "45.4215,-75.6972," + (System.currentTimeMillis() - 1800000)
        };
    }
}
