/*
 * File name: BreakLogController.java
 * Author: Jarmanjit Singh, 041154515
 * Course: 25S CST8288 Section 024
 * Assignment: Final Project
 * Date: Aug 7, 2025
 * Professor: Marwan Farah
 * Purpose: Servlet to manage break log HTTP requests, including listing break logs
 *          and creating new break log entries via form submission.
 */

package controller;

import model.BreakLog;
import service.BreakLogService;
import DAO.BreakLogDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Servlet controller that handles HTTP requests related to break logs.
 * Supports listing all break logs and adding new break logs.
 * Delegates business logic to the BreakLogService.
 * 
 * @author Jarmanjit Singh
 * @since 21
 * @version 1.0
 */
public class BreakLogController extends HttpServlet {

    /** Service layer for break log operations */
    private BreakLogService breakLogService;

    /** Date formatter for parsing and formatting timestamps */
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * Initializes the servlet and its dependencies.
     */
    @Override
    public void init() {
        breakLogService = new BreakLogService(new BreakLogDAO());
    }

    /**
     * Handles HTTP GET requests.
     * If action parameter equals "list", forwards to breaklog-list.jsp with all break logs.
     * Otherwise, forwards to breaklog-form.jsp for creating new break logs.
     *
     * @param request  HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException if servlet error occurs
     * @throws IOException      if I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        if ("list".equals(action)) {
            List<BreakLog> logs = breakLogService.getAllBreakLogs();
            request.setAttribute("breakLogs", logs);
            request.getRequestDispatcher("/breaklog-list.jsp").forward(request, response);
        } else {
            // Show empty form or handle other GET actions if needed
            request.getRequestDispatcher("/breaklog-form.jsp").forward(request, response);
        }
    }

    /**
     * Handles HTTP POST requests.
     * Parses form data to create and save a new BreakLog record,
     * then redirects to the break log listing page.
     *
     * @param request  HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException if servlet error occurs
     * @throws IOException      if I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            int operatorId = Integer.parseInt(request.getParameter("operatorId"));
            String reason = request.getParameter("reason");
            Date timestamp = dateFormat.parse(request.getParameter("timestamp"));

            BreakLog breakLog = new BreakLog.Builder(operatorId, reason, timestamp).build();
            breakLogService.logBreak(breakLog);

            response.sendRedirect("breaklog?action=list");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
