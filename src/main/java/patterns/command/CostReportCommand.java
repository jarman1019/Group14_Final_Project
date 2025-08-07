/*
 * File name: CostReportCommand.java
 * Author: Jarmanjit Singh, 041154515
 * Course: 25S CST8288 Section 024
 * Assignment: Final Project
 * Date: Aug 9, 2025
 * Professor: Marwan Farah
 * Purpose: Implements the Command to generate a cost report via the LoggingSystem.
 */

package patterns.command;

/**
 * Concrete Command implementation to generate a cost report.
 * Delegates the request to the LoggingSystem.
 * 
 * @author Jarmanjit Singh
 * @since 21
 * @version 1.0
 */
public class CostReportCommand implements Command {
    /**
     * The receiver class that performs the actual cost report generation.
     */
    private LoggingSystem loggingSystem;

    /**
     * Constructs the command with the given LoggingSystem receiver.
     * @param loggingSystem The logging system to execute the command on.
     */
    public CostReportCommand(LoggingSystem loggingSystem) {
        this.loggingSystem = loggingSystem;
    }

    /**
     * Executes the command by invoking cost report generation on the LoggingSystem.
     */
    @Override
    public void execute() {
        loggingSystem.generateCostReport();
    }
}
