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

public class BreakLogService {

    private final BreakLogDAO breakLogDAO;

    public BreakLogService(BreakLogDAO breakLogDAO) {
        this.breakLogDAO = breakLogDAO;
    }

    public void logBreak(BreakLog breakLog) {
        breakLogDAO.saveBreakLog(breakLog);
    }

    public List<BreakLog> getBreakLogsForOperator(int operatorId) {
        return breakLogDAO.getBreakLogsByOperator(operatorId);
    }

    public List<BreakLog> getAllBreakLogs() {
        return breakLogDAO.getAllBreakLogs();
    }
}
