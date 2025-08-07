/*
 * File name: BreakLogService.java
 * Author: Jarmanjit Singh, 041154515
 * Course: 25S CST8288 Section 024
 * Assignment: Final Project
 * Date: Aug 7, 2025
 * Professor: Marwan Farah
 * Purpose: Service layer to handle business logic for BreakLogs.
 */

package service;

import DAO.BreakLogDAO;
import model.BreakLog;

import java.util.List;

/**
 * Service class for managing break log business operations.
 * Acts as an intermediary between the controller and DAO layers,
 * providing methods to log breaks and retrieve break log data.
 * 
 * @author Jarmanjit Singh
 * @since 21
 * @version 1.0
 */
public class BreakLogService {

    /** Data access object for break logs */
    private final BreakLogDAO breakLogDAO;

    /**
     * Constructs the BreakLogService with the specified DAO.
     * @param breakLogDAO DAO instance for break log persistence
     */
    public BreakLogService(BreakLogDAO breakLogDAO) {
        this.breakLogDAO = breakLogDAO;
    }

    /**
     * Logs a new break record.
     * @param breakLog BreakLog object to be saved
     */
    public void logBreak(BreakLog breakLog) {
        breakLogDAO.saveBreakLog(breakLog);
    }

    /**
     * Retrieves break logs for a specific operator.
     * @param operatorId The ID of the operator
     * @return List of BreakLog objects associated with the operator
     */
    public List<BreakLog> getBreakLogsForOperator(int operatorId) {
        return breakLogDAO.getBreakLogsByOperator(operatorId);
    }

    /**
     * Retrieves all break logs.
     * @return List of all BreakLog objects
     */
    public List<BreakLog> getAllBreakLogs() {
        return breakLogDAO.getAllBreakLogs();
    }
}
