/*
 * File name: VehicleController.java
 * Author: Tasmia Tabassom Aboni, 041130376
 * Course: 25S CST8288 Section 024 
 * Assignment: Final Project
 * Date: Aug 6, 2025
 * Professor: Marwan Farah
 */

package controller;

import model.Vehicle;
import model.VehicleType;
import service.VehicleService;
import DAO.VehicleDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
/**
 * This servlet handles HTTP requests related to vehicle records.
 * It provides functionality for listing, creating, updating, and deleting vehicles.
 * @author  Tasmia
 */
public class VehicleController extends HttpServlet {
    private VehicleService vehicleService;

    /**
     * Initializes the servlet by setting up the VehicleService with a VehicleDAO instance.
     */
    @Override
    public void init() {
        // Initialize the VehicleService with the VehicleDAO
        VehicleDAO vehicleDAO = new VehicleDAO(); // You might need to instantiate your DAO object here
        vehicleService = new VehicleService(vehicleDAO);
    }

    /**
     * Handles GET requests for listing, creating, editing, and deleting vehicles.
     * @param request  The HTTP request object.
     * @param response The HTTP response object.
     * @throws ServletException If there is an issue with the servlet or database access.
     * @throws IOException      If an I/O error occurs during request processing.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if (action == null) {
                listVehicles(request, response);
            } else {
                switch (action) {
                    case "new":
                        showNewForm(request, response);  // This is for adding a new vehicle
                        break;
                    case "edit":
                        showEditForm(request, response);
                        break;
                    case "delete":
                        deleteVehicle(request, response);
                        break;
                    default:
                        listVehicles(request, response);
                        break;
                }
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    /**
     * Handles POST requests for inserting or updating vehicles.
     * @param request  The HTTP request object.
     * @param response The HTTP response object.
     * @throws ServletException If there is an issue handling the vehicle record.
     * @throws IOException      If an I/O error occurs during redirection.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            if ("insert".equals(action)) {
                insertVehicle(request, response);  // Insert new vehicle
            } else if ("update".equals(action)) {
                updateVehicle(request, response);  // Update existing vehicle
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    /**
     * Retrieves all vehicles and forwards them to the vehicle list page.
     *
     * @param request  The HTTP request object.
     * @param response The HTTP response object.
     * @throws SQLException       If a database access error occurs.
     * @throws ServletException   If there is an issue forwarding the request.
     * @throws IOException        If an I/O error occurs during request processing.
     */
    private void listVehicles(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Vehicle> vehicles = vehicleService.getAllVehicles();
        request.setAttribute("vehicles", vehicles);
        request.getRequestDispatcher("vehicle-list.jsp").forward(request, response);
    }

    /**
     * Displays the form for creating a new vehicle.
     *
     * @param request  The HTTP request object.
     * @param response The HTTP response object.
     * @throws ServletException If there is an issue forwarding the request.
     * @throws IOException      If an I/O error occurs during request processing.
     */
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("action", "insert");
        request.getRequestDispatcher("addVehicle.jsp").forward(request, response);  // Direct to add vehicle form
    }

    /**
     * Prepares and displays the edit form for an existing vehicle.
     * @param request  The HTTP request object.
     * @param response The HTTP response object.
     * @throws SQLException       If a database access error occurs.
     * @throws ServletException   If there is an issue forwarding the request.
     * @throws IOException        If an I/O error occurs during request processing.
     */
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int vehicleId = Integer.parseInt(request.getParameter("id"));
        Vehicle vehicle = vehicleService.getVehicleById(vehicleId);
        request.setAttribute("vehicle", vehicle);
        request.setAttribute("action", "update");
        request.getRequestDispatcher("vehicle-form.jsp").forward(request, response);  // Edit vehicle form
    }

    /**
     * Inserts a new vehicle into the database.
     * @param request  The HTTP request object.
     * @param response The HTTP response object.
     * @throws SQLException If a database access error occurs.
     * @throws IOException  If an I/O error occurs during redirection.
     */
    private void insertVehicle(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Vehicle vehicle = getVehicleFromRequest(request, 0);  // Insert new vehicle with id 0 (as it is not set)
        vehicleService.registerVehicle(vehicle);  // Use VehicleService to add vehicle
        response.sendRedirect("VehicleController");  // Redirect to the list of vehicles
    }

    /**
     * Updates an existing vehicle in the database.
     * @param request  The HTTP request object.
     * @param response The HTTP response object.
     * @throws SQLException If a database access error occurs.
     * @throws IOException  If an I/O error occurs during redirection.
     */
    private void updateVehicle(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
        Vehicle vehicle = getVehicleFromRequest(request, vehicleId);
        vehicleService.updateVehicle(vehicle);  // Use VehicleService to update vehicle
        response.sendRedirect("VehicleController");  // Redirect to the list of vehicles
    }

    /**
     * Deletes a vehicle from the database based on the provided ID.
     * @param request  The HTTP request object.
     * @param response The HTTP response object.
     * @throws SQLException If a database access error occurs.
     * @throws IOException  If an I/O error occurs during redirection.
     */
    private void deleteVehicle(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int vehicleId = Integer.parseInt(request.getParameter("id"));
        vehicleService.deleteVehicle(vehicleId);  // Use VehicleService to delete vehicle
        response.sendRedirect("VehicleController");  // Redirect to the list of vehicles
    }

    /**
     * Constructs a Vehicle object from the request parameters.
     *
     * @param request  The HTTP request object containing vehicle details.
     * @param vehicleId The ID of the vehicle (0 for new vehicles).
     * @return A Vehicle object populated with the request data.
     */
    private Vehicle getVehicleFromRequest(HttpServletRequest request, int vehicleId) {
        String vehicleTypeStr = request.getParameter("vehicleType");
        VehicleType type = VehicleType.valueOf(vehicleTypeStr.toUpperCase());

        String vehicleNumber = request.getParameter("vehicleNumber");
        String fuelType = request.getParameter("fuelType");
        double consumptionRate = Double.parseDouble(request.getParameter("consumptionRate"));
        int maxPassengers = Integer.parseInt(request.getParameter("maxPassengers"));
        int routeId = Integer.parseInt(request.getParameter("routeId"));

        return new Vehicle.Builder(vehicleId, type)
                .withFuelType(fuelType)
                .withConsumptionRate(consumptionRate)
                .withMaxPassengers(maxPassengers)
                .withVehicleNumber(vehicleNumber)
                .withRouteId(routeId)
                .build();
    }
}