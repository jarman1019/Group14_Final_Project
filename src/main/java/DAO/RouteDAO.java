
package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Route;
/**
 * This class provides data access operations for the Route entity.
 * It interacts with the database to perform CRUD (Create, Read, Update, Delete)
 * operations on the Route table.
 * @author varshil
 */
public class RouteDAO {

    // SQL Queries
    private static final String INSERT_ROUTE_SQL = 
        "INSERT INTO Route (route_name, start_point, end_point) VALUES (?, ?, ?)";
    private static final String SELECT_ROUTE_BY_ID = 
        "SELECT * FROM Route WHERE route_id = ?";
    private static final String SELECT_ALL_ROUTES = 
        "SELECT * FROM Route";
    private static final String UPDATE_ROUTE_SQL = 
        "UPDATE Route SET route_name = ?, start_point = ?, end_point = ? WHERE route_id = ?";
    private static final String DELETE_ROUTE_SQL = 
        "DELETE FROM Route WHERE route_id = ?";

    /**
     * Inserts a new route into the database.
     * @param route The Route object to be inserted.
     * @throws SQLException If a database access error occurs or the insertion fails.
     */
    public void insertRoute(Route route) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ROUTE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, route.getRouteName());
            preparedStatement.setString(2, route.getStartPoint());
            preparedStatement.setString(3, route.getEndPoint());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating route failed, no rows affected.");
            }
        }
    }

    /**
     * Retrieves a route by its ID.
     * @param routeId The ID of the route to retrieve.
     * @return The Route object if found; otherwise, null.
     * @throws SQLException If a database access error occurs or the query fails.
     */
    public Route selectRoute(int routeId) throws SQLException {
        Route route = null;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ROUTE_BY_ID)) {
            preparedStatement.setInt(1, routeId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String routeName = resultSet.getString("route_name");
                String startPoint = resultSet.getString("start_point");
                String endPoint = resultSet.getString("end_point");

                route = new Route.Builder(routeId, routeName)
                        .withStartPoint(startPoint)
                        .withEndPoint(endPoint)
                        .build();
            }
        }
        return route;
    }

    /**
     * Retrieves all routes from the database.
     * @return A list of all Route objects, or an empty list if no records exist.
     * @throws SQLException If a database access error occurs or the query fails.
     */
    public List<Route> selectAllRoutes() throws SQLException {
        List<Route> routes = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ROUTES)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int routeId = resultSet.getInt("route_id");
                String routeName = resultSet.getString("route_name");
                String startPoint = resultSet.getString("start_point");
                String endPoint = resultSet.getString("end_point");

                Route route = new Route.Builder(routeId, routeName)
                        .withStartPoint(startPoint)
                        .withEndPoint(endPoint)
                        .build();
                routes.add(route);
            }
        }
        return routes;
    }

    /**
     * Updates an existing route in the database.
     * @param route The updated Route object.
     * @return True if the update was successful; otherwise, false.
     * @throws SQLException If a database access error occurs or the update fails.
     */
    public boolean updateRoute(Route route) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ROUTE_SQL)) {
            preparedStatement.setString(1, route.getRouteName());
            preparedStatement.setString(2, route.getStartPoint());
            preparedStatement.setString(3, route.getEndPoint());
            preparedStatement.setInt(4, route.getRouteId());

            rowUpdated = preparedStatement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    /**
     * Deletes a route from the database by its ID.
     * @param routeId The ID of the route to delete.
     * @return True if the deletion was successful; otherwise, false.
     * @throws SQLException If a database access error occurs or the deletion fails.
     */
    public boolean deleteRoute(int routeId) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ROUTE_SQL)) {
            preparedStatement.setInt(1, routeId);

            rowDeleted = preparedStatement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
}