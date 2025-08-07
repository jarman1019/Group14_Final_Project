package controller;


import DAO.MaintenanceDAO;
import model.Maintenance;
import service.MaintenanceService;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
/**
 * This servlet handles HTTP requests related to maintenance records.
 * It provides functionality for listing, creating, updating, and deleting maintenance records.
 * @author Kunj
 */
public class MaintenanceController extends HttpServlet {

    private MaintenanceService maintenanceService;

    /**
     * Initializes the servlet by setting up the MaintenanceService with a MaintenanceDAO instance.
     */
    @Override
    public void init() {
        maintenanceService = new MaintenanceService(new MaintenanceDAO());
    }

    /**
     * Handles GET requests for listing, creating, editing, and deleting maintenance records.
     * @param request  The HTTP request object.
     * @param response The HTTP response object.
     * @throws ServletException If there is an issue with the servlet or database access.
     * @throws IOException      If an I/O error occurs during request processing.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        try {
            switch (action) {
                case "new":
                    request.getRequestDispatcher("/maintenance-form.jsp").forward(request, response);
                    break;
                case "edit":
                    showMaintenanceForm(request, response);
                    break;
                case "delete":
                    deleteMaintenance(request, response);
                    break;
                default:
                    listMaintenances(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException("Database access error", e);
        }
    }

    /**
     * Retrieves all maintenance records and forwards them to the maintenance list page.
     *
     * @param request  The HTTP request object.
     * @param response The HTTP response object.
     * @throws SQLException       If a database access error occurs.
     * @throws ServletException   If there is an issue forwarding the request.
     * @throws IOException        If an I/O error occurs during request processing.
     */
    private void listMaintenances(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Maintenance> maintenances = maintenanceService.getAllMaintenanceRecords();
        request.setAttribute("maintenances", maintenances);
        request.getRequestDispatcher("/maintenance-list.jsp").forward(request, response);
    }

    /**
     * Prepares and displays the maintenance form for creating or editing a record.
     * @param request  The HTTP request object.
     * @param response The HTTP response object.
     * @throws ServletException If there is an issue retrieving the maintenance record or forwarding the request.
     * @throws IOException      If an I/O error occurs during request processing.
     */
    private void showMaintenanceForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String maintenanceId = request.getParameter("maintenanceId");
        if (maintenanceId != null) {
            try {
                Maintenance maintenance = maintenanceService.getMaintenanceById(Integer.parseInt(maintenanceId));
                request.setAttribute("maintenance", maintenance);
            } catch (SQLException e) {
                throw new ServletException("Error retrieving maintenance", e);
            }
        }
        request.getRequestDispatcher("/maintenance-form.jsp").forward(request, response);
    }

    /**
     * Deletes a maintenance record based on the provided ID.
     * @param request  The HTTP request object.
     * @param response The HTTP response object.
     * @throws SQLException If a database access error occurs.
     * @throws IOException  If an I/O error occurs during redirection.
     */
    private void deleteMaintenance(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String maintenanceId = request.getParameter("maintenanceId");
        if (maintenanceId != null) {
            maintenanceService.deleteMaintenance(Integer.parseInt(maintenanceId));
        }
        response.sendRedirect("MaintenanceController?action=list");
    }

    /**
     * Handles POST requests for creating or updating maintenance records.
     * @param request  The HTTP request object.
     * @param response The HTTP response object.
     * @throws ServletException If there is an issue handling the maintenance record.
     * @throws IOException      If an I/O error occurs during redirection.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String maintenanceId = request.getParameter("maintenanceId");
        int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
        String alertType = request.getParameter("alertType");
        String scheduledDate = request.getParameter("scheduledDate");

        Maintenance maintenance = new Maintenance.Builder(vehicleId, alertType, java.time.LocalDate.parse(scheduledDate))
                .withMaintenanceId(maintenanceId != null && !maintenanceId.isEmpty() ? Integer.parseInt(maintenanceId) : 0)
                .build();

        try {
            if (maintenanceId == null || maintenanceId.isEmpty()) {
                maintenanceService.createMaintenance(maintenance);
            } else {
                maintenanceService.updateMaintenance(maintenance);
            }
            response.sendRedirect("MaintenanceController?action=list");
        } catch (SQLException | IllegalArgumentException e) {
            throw new ServletException("Error handling maintenance record", e);
        }
    }
}