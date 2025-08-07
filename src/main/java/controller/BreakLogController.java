/*
 * File name: BreakLogController.java
 * Author: Jarmanjit Singh, 041154515
 * Course: 25S CST8288 Section 024
 * Assignment: Final Project
 * Date: Aug 7, 2025
 * Professor: Marwan Farah
 * Purpose: Servlet to manage break log HTTP requests.
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

public class BreakLogController extends HttpServlet {

    private BreakLogService breakLogService;

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void init() {
        breakLogService = new BreakLogService(new BreakLogDAO());
    }

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
