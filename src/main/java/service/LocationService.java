package service;

import patterns.adapter.GPSAdapter;
import DAO.LocationDAO;
import model.Location;
import java.util.Date;
import java.util.List;

public class LocationService {
    private final LocationDAO locationDAO;
    private final GPSAdapter gpsAdapter;

    public LocationService(LocationDAO locationDAO, GPSAdapter gpsAdapter) {
        this.locationDAO = locationDAO;
        this.gpsAdapter = gpsAdapter;
    }

    public Location getCurrentLocation(String vehicleId) {
        Location location = gpsAdapter.getCurrentLocation(vehicleId);
        locationDAO.saveLocation(location);
        return location;
    }

    public void updateVehicleLocation(String vehicleId, double lat, double lng) {
        gpsAdapter.updateLocation(vehicleId, lat, lng);
        Location location = gpsAdapter.getCurrentLocation(vehicleId);
        locationDAO.saveLocation(location);
    }

    public List<Location> getLocationHistory(String vehicleId, Date start, Date end) {
        return gpsAdapter.getLocationHistory(vehicleId, start, end);
    }

    public List<Location> getAllActiveLocations() {
        // Get locations from last 5 minutes
        Date fiveMinutesAgo = new Date(System.currentTimeMillis() - (5 * 60 * 1000));
        return locationDAO.getLocationsSince(fiveMinutesAgo);
    }
}