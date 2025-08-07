/*
 * File name: LocationController.java
 * Author: Jarmanjit Singh
 * Course: 25S CST8288 Section 024
 * Assignment: Final Project
 * Date: Aug 7, 2025
 * Professor: Marwan Farah
 * Purpose: Servlet controller for managing vehicle location-related HTTP requests.
 *          Handles retrieval of current location, location history, all active locations,
 *          and updates to vehicle location via GET and POST requests.
 */

package controller;

import service.LocationService;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import model.Location;

/**
 * Servlet to handle requests related to vehicle locations.
 * Supports actions: 
 * - current: get current location of a vehicle,
 * - history: get location history of a vehicle within a date range,
 * - default: show all active vehicle locations.
 * Handles updates of vehicle location data via POST requests.
 * 
 * Initializes a {@link LocationService} with a {@link DAO.LocationDAO} and
 * GPS adapter integration for location tracking.
 * 
 * @author Jarmanjit Singh
 * @since 21
 * @version 1.0
 */
public class LocationController extends HttpServlet {

    /** Service layer for location management */
    private LocationService locationService;

    /** Date formatter for parsing date/time strings in requests */
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * Initializes the servlet and sets up LocationService with dependencies.
     */
    @Override
    public void init() {
        this.locationService = new LocationService(
            new DAO.LocationDAO(), 
            new patterns.adapter.GPSAdapter(new patterns.adapter.LegacyGPS())
        );
    }

    /**
     * Handles GET requests to retrieve location data based on the "action" parameter.
     * 
     * Actions:
     * <ul>
     *   <li>"current": Retrieves current location for a given vehicleId.</li>
     *   <li>"history": Retrieves location history between given from/to dates for a vehicleId.</li>
     *   <li>Default: Retrieves all active vehicle locations.</li>
     * </ul>
     * Forwards results to corresponding JSP pages for display.
     * 
     * @param request  HTTP request containing parameters "action", "vehicleId", and optionally "from", "to".
     * @param response HTTP response used for forwarding.
     * @throws ServletException if request processing or forwarding fails.
     * @throws IOException if an I/O error occurs during request handling.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        
        String action = request.getParameter("action");
        String vehicleId = request.getParameter("vehicleId");
        
        try {
            if ("current".equals(action)) {
                Location current = locationService.getCurrentLocation(vehicleId);
                request.setAttribute("location", current);
                request.getRequestDispatcher("/current-location.jsp").forward(request, response);
            } 
            else if ("history".equals(action)) {
                Date from = dateFormat.parse(request.getParameter("from"));
                Date to = dateFormat.parse(request.getParameter("to"));
                List<Location> history = locationService.getLocationHistory(vehicleId, from, to);
                request.setAttribute("locations", history);
                request.getRequestDispatcher("/location-history.jsp").forward(request, response);
            }
            else {
                // Show all active vehicles
                request.setAttribute("locations", locationService.getAllActiveLocations());
                request.getRequestDispatcher("/vehicle-map.jsp").forward(request, response);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    /**
     * Handles POST requests to update the current location of a vehicle.
     * Expects parameters "vehicleId", "lat", and "lng".
     * After updating the location, redirects to the current location view.
     * 
     * @param request  HTTP request containing location update parameters.
     * @param response HTTP response for redirecting to updated location page.
     * @throws ServletException if request handling fails.
     * @throws IOException if an I/O error occurs during redirection.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        
        String vehicleId = request.getParameter("vehicleId");
        double lat = Double.parseDouble(request.getParameter("lat"));
        double lng = Double.parseDouble(request.getParameter("lng"));
        
        locationService.updateVehicleLocation(vehicleId, lat, lng);
        response.sendRedirect("location?action=current&vehicleId=" + vehicleId);
    }
}
