/*
 * File name: LoggingSystem.java
 * Author: Jarmanjit Singh, 041154515
 * Course: 25S CST8288 Section 024
 * Assignment: Final Project
 * Date: Aug 9, 2025
 * Professor: Marwan Farah
 * Purpose: Receiver class providing actual implementations for logging
 *          operator breaks, generating cost reports, and performance dashboards.
 */

package patterns.command;

/**
 * Receiver class that performs the actual logging and report generation
 * actions in the command pattern.
 * 
 * @author Jarmanjit Singh
 * @since 21
 * @version 1.0
 */
public class LoggingSystem {

    /**
     * Logs a break event for the given operator.
     * @param operatorId The identifier of the operator taking a break.
     */
    public void logOperatorBreak(String operatorId) {
        System.out.println("Logging break for operator: " + operatorId);
    }

    /**
     * Generates a cost report related to fuel and maintenance.
     */
    public void generateCostReport() {
        System.out.println("Generating cost report for fuel and maintenance.");
    }

    /**
     * Generates a performance dashboard for the specified operator.
     * @param operatorId The identifier of the operator.
     */
    public void generatePerformanceDashboard(String operatorId) {
        System.out.println("Generating performance dashboard for operator: " + operatorId);
    }
}
