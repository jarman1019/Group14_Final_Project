
package controller;

import service.RouteService;
import model.Route;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * This servlet handles HTTP requests related to route records.
 * It provides functionality for listing, creating, updating, deleting, and viewing routes.
 * @author varshil
 */
public class RouteController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private RouteService routeService;

    /**
     * Initializes the servlet by setting up the RouteService with a RouteDAO instance.
     *
     * @throws ServletException If an error occurs during initialization.
     */
    @Override
    public void init() throws ServletException {
        routeService = new RouteService(new DAO.RouteDAO());
    }

    /**
     * Handles GET requests for listing, editing, deleting, viewing, and describing routes.
     * @param request  The HTTP request object.
     * @param response The HTTP response object.
     * @throws ServletException If there is an issue with the servlet or database access.
     * @throws IOException      If an I/O error occurs during request processing.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if (action == null) {
                action = "list"; // Default action
            }

            switch (action) {
                case "list":
                    listRoutes(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteRoute(request, response);
                    break;
                case "routeDescription": // Handle route description view
                    viewRouteDescription(request, response);
                    break;
                default:
                    listRoutes(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    /**
     * Handles POST requests for creating or updating routes.
     * @param request  The HTTP request object.
     * @param response The HTTP response object.
     * @throws ServletException If there is an issue handling the route record.
     * @throws IOException      If an I/O error occurs during redirection.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("edit".equals(action)) {
                updateRoute(request, response);
            } else {
                addRoute(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    /**
     * Retrieves all routes and forwards them to the route list page.
     *
     * @param request  The HTTP request object.
     * @param response The HTTP response object.
     * @throws SQLException       If a database access error occurs.
     * @throws ServletException   If there is an issue forwarding the request.
     * @throws IOException        If an I/O error occurs during request processing.
     */
    private void listRoutes(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Route> routes = routeService.getAllRoutes();
        request.setAttribute("routes", routes);
        request.getRequestDispatcher("route-list.jsp").forward(request, response);
    }

    /**
     * Prepares and displays the edit form for a specific route.
     * @param request  The HTTP request object.
     * @param response The HTTP response object.
     * @throws SQLException       If a database access error occurs.
     * @throws ServletException   If there is an issue forwarding the request.
     * @throws IOException        If an I/O error occurs during request processing.
     */
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int routeId = Integer.parseInt(request.getParameter("routeId"));
        Route existingRoute = routeService.getRouteById(routeId);
        request.setAttribute("route", existingRoute);
        request.getRequestDispatcher("route-form.jsp").forward(request, response);
    }

    /**
     * Adds a new route to the database.
     * @param request  The HTTP request object.
     * @param response The HTTP response object.
     * @throws SQLException       If a database access error occurs.
     * @throws ServletException   If there is an issue handling the route record.
     * @throws IOException        If an I/O error occurs during redirection.
     */
    private void addRoute(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String routeName = request.getParameter("routeName");
        String startPoint = request.getParameter("startPoint");
        String endPoint = request.getParameter("endPoint");

        Route route = new Route.Builder(0, routeName)
            .withStartPoint(startPoint)
            .withEndPoint(endPoint)
            .build();

        routeService.createRoute(route);
        response.sendRedirect("RouteController?action=list");
    }

    /**
     * Updates an existing route in the database.
     * @param request  The HTTP request object.
     * @param response The HTTP response object.
     * @throws SQLException       If a database access error occurs.
     * @throws ServletException   If there is an issue handling the route record.
     * @throws IOException        If an I/O error occurs during redirection.
     */
    private void updateRoute(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int routeId = Integer.parseInt(request.getParameter("routeId"));
        String routeName = request.getParameter("routeName");
        String startPoint = request.getParameter("startPoint");
        String endPoint = request.getParameter("endPoint");

        Route route = new Route.Builder(routeId, routeName)
            .withStartPoint(startPoint)
            .withEndPoint(endPoint)
            .build();

        routeService.updateRoute(route);
        response.sendRedirect("RouteController?action=list");
    }

    /**
     * Deletes a route from the database based on the provided ID.
     * @param request  The HTTP request object.
     * @param response The HTTP response object.
     * @throws SQLException       If a database access error occurs.
     * @throws ServletException   If there is an issue handling the route record.
     * @throws IOException        If an I/O error occurs during redirection.
     */
    private void deleteRoute(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int routeId = Integer.parseInt(request.getParameter("routeId"));
        routeService.deleteRoute(routeId);
        response.sendRedirect("RouteController?action=list");
    }


    /**
     * Displays the description of a specific route.
     * @param request  The HTTP request object.
     * @param response The HTTP response object.
     * @throws SQLException       If a database access error occurs.
     * @throws ServletException   If there is an issue forwarding the request.
     * @throws IOException        If an I/O error occurs during request processing.
     */
    private void viewRouteDescription(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int routeId = Integer.parseInt(request.getParameter("routeId"));
        String description = routeService.getRouteDescription(routeId);

        // Retrieve the route data (if you want to display some basic route info on the description page)
        Route route = routeService.getRouteById(routeId);

        // Set route and description as request attributes
        request.setAttribute("routeDescription", description);
        request.setAttribute("route", route);

        // Forward to the route-description.jsp page
        request.getRequestDispatcher("route-description.jsp").forward(request, response);
    }
}