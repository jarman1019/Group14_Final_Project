/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import DAO.RouteDAO;
import model.Route;
import java.sql.SQLException;
import java.util.List;

/**
 * This class works as a service of Route.
 * @author varshil
 */
public class RouteService {
    private final RouteDAO routeDAO;

    /**
     * Constructs a RouteService with dependency injection.
     * @param routeDAO Data access object for routes
     */
    public RouteService(RouteDAO routeDAO) {
        this.routeDAO = routeDAO;
    }

    /**
     * Creates a new route in the system.
     * @param route Route to be created
     * @throws SQLException if database operation fails
     * @throws IllegalArgumentException if route data is invalid
     */
    public void createRoute(Route route) throws SQLException, IllegalArgumentException {
        validateRoute(route);
        routeDAO.insertRoute(route);
    }

    /**
     * Retrieves a route by its ID.
     * @param routeId ID of the route to retrieve
     * @return Route object if found
     * @throws SQLException if database operation fails
     */
    public Route getRouteById(int routeId) throws SQLException {
        return routeDAO.selectRoute(routeId);
    }

    /**
     * Retrieves all routes in the system.
     * @return List of all routes
     * @throws SQLException if database operation fails
     */
    public List<Route> getAllRoutes() throws SQLException {
        return routeDAO.selectAllRoutes();
    }

    /**
     * Updates an existing route.
     * @param route Updated route data
     * @return true if update was successful
     * @throws SQLException if database operation fails
     * @throws IllegalArgumentException if route data is invalid
     */
    public boolean updateRoute(Route route) throws SQLException, IllegalArgumentException {
        validateRoute(route);
        return routeDAO.updateRoute(route);
    }

    /**
     * Deletes a route from the system.
     * @param routeId ID of the route to delete
     * @return true if deletion was successful
     * @throws SQLException if database operation fails
     */
    public boolean deleteRoute(int routeId) throws SQLException {
        return routeDAO.deleteRoute(routeId);
    }

    /**
     * Validates route business rules.
     * @param route Route to validate
     * @throws IllegalArgumentException if validation fails
     */
    private void validateRoute(Route route) throws IllegalArgumentException {
        if (route.getRouteName() == null || route.getRouteName().trim().isEmpty()) {
            throw new IllegalArgumentException("Route name cannot be empty");
        }
        
        if (route.getStartPoint() == null || route.getStartPoint().trim().isEmpty()) {
            throw new IllegalArgumentException("Start point cannot be empty");
        }
        
        if (route.getEndPoint() == null || route.getEndPoint().trim().isEmpty()) {
            throw new IllegalArgumentException("End point cannot be empty");
        }
        
        // Additional business rules can be added here
        // For example, check if start and end points are different
        if (route.getStartPoint().equalsIgnoreCase(route.getEndPoint())) {
            throw new IllegalArgumentException("Start and end points cannot be the same");
        }
    }

    /**
     * Calculates and returns a summary description of the route.
     * @param routeId ID of the route
     * @return Formatted description of the route
     * @throws SQLException if database operation fails
     */
    public String getRouteDescription(int routeId) throws SQLException {
        Route route = routeDAO.selectRoute(routeId);
        if (route == null) {
            throw new IllegalArgumentException("Route not found");
        }
        
        return String.format("Route %d: %s (From: %s, To: %s)",
                route.getRouteId(),
                route.getRouteName(),
                route.getStartPoint(),
                route.getEndPoint());
    }
}