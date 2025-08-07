/*
 * File name: UserController.java
 * Author: Jarmanjit Singh, 041154515
 * Course: 25S CST8288 Section 024 
 * Assignment: Final Project
 * Date: Aug 9, 2025
 * Professor: Marwan Farah
 * Purpose: A servlet controller that handles HTTP requests related to user
 *          management actions such as listing, creating, updating, deleting,
 *          logging in, and authenticating system users.
 */

package controller;

import model.User;
import service.UserService;
import DAO.UserDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * This servlet processes incoming HTTP requests related to users
 * (list, create, update, delete, authenticate) and routes them
 * to the appropriate view or service method.
 * 
 * @author Jarmanjit Singh
 * @version 1.0
 * @since 21
 */
public class UserController extends HttpServlet {

    /** Business layer service used to perform user-related operations. */
    private UserService userService;

    /**
     * Initializes the servlet by constructing a {@link UserService}
     * with a {@link UserDAO} as its dependency.
     */
    @Override
    public void init() {
        userService = new UserService(new UserDAO());
    }

    /**
     * Handles GET requests for listing, creating, editing, deleting,
     * and logging in users.
     *
     * @param request  HTTP request object.
     * @param response HTTP response object.
     * @throws ServletException if servlet or database issue occurs.
     * @throws IOException      if I/O error occurs.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if (action == null) {
                action = "list";
            }

            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "insert":
                    insertUser(request, response);
                    break;
                case "delete":
                    deleteUser(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    updateUser(request, response);
                    break;
                case "login":
                    loginUser(request, response);
                    break;
                default:
                    listUsers(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    /**
     * Retrieves all users and forwards the list to the user list page.
     *
     * @param request  The HTTP request object.
     * @param response The HTTP response object.
     * @throws SQLException       If a database access error occurs.
     * @throws ServletException   If there is an issue forwarding the request.
     * @throws IOException        If an I/O error occurs during request processing.
     */
    private void listUsers(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<User> users = userService.getAllUsers();
        request.setAttribute("users", users);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Forwards the client to the user creation form JSP.
     *
     * @param request  The HTTP request object.
     * @param response The HTTP response object.
     * @throws ServletException If there is an issue forwarding the request.
     * @throws IOException      If an I/O error occurs during request processing.
     */
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Displays the edit form populated with an existing user’s data.
     * @param request  The HTTP request object.
     * @param response The HTTP response object.
     * @throws SQLException       If a database access error occurs.
     * @throws ServletException   If there is an issue forwarding the request.
     * @throws IOException        If an I/O error occurs during request processing.
     */
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        User existingUser = userService.getUserById(userId);
        request.setAttribute("user", existingUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Inserts a new user record into the database from form input.
     * @param request  The HTTP request object.
     * @param response The HTTP response object.
     * @throws SQLException If a database access error occurs.
     * @throws IOException  If an I/O error occurs during redirection.
     */
    private void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String userType = request.getParameter("userType");

        User user = new User.Builder(0, email, password)
                .withName(name)
                .withUserType(userType)
                .build();

        userService.registerUser(user);
        response.sendRedirect("UserController");
    }

    /**
     * Authenticates a user and redirects to the dashboard if successful.
     * @param request  The HTTP request object.
     * @param response The HTTP response object.
     * @throws IOException      If an I/O error occurs during redirection.
     * @throws ServletException If there is an issue forwarding the request.
     */
    private void loginUser(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = userService.login(email, password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", user);
            response.sendRedirect("dashboard.jsp");
        } else {
            request.setAttribute("error", "Invalid email or password.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }
    }

    /**
     * Updates an existing user using data from the request.
     * @param request  The HTTP request object.
     * @param response The HTTP response object.
     * @throws SQLException If a database access error occurs.
     * @throws IOException  If an I/O error occurs during redirection.
     */
    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String userType = request.getParameter("userType");

        User updatedUser = new User.Builder(userId, email, password)
                .withName(name)
                .withUserType(userType)
                .build();

        userService.updateUser(updatedUser);
        response.sendRedirect("UserController");
    }

    /**
     * Deletes an existing user by ID.
     * @param request  The HTTP request object.
     * @param response The HTTP response object.
     * @throws SQLException If a database access error occurs.
     * @throws IOException  If an I/O error occurs during redirection.
     */
    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        userService.deleteUser(userId);
        response.sendRedirect("UserController");
    }

    /**
     * Handles POST requests by delegating to {@link #doGet(HttpServletRequest, HttpServletResponse)}.
     *
     * @param request  The HTTP request object.
     * @param response The HTTP response object.
     * @throws ServletException If there is an issue with the servlet or database access.
     * @throws IOException      If an I/O error occurs during request processing.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
