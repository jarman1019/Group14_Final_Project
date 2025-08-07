/*
 * File name: PerformanceDashboardCommand.java
 * Author: Jarmanjit Singh, 041154515
 * Course: 25S CST8288 Section 024
 * Assignment: Final Project
 * Date: Aug 9, 2025
 * Professor: Marwan Farah
 * Purpose: Concrete Command to trigger performance dashboard generation for a given operator.
 */

package patterns.command;

/**
 * Concrete command for generating a performance dashboard for an operator.
 * Implements the Command interface.
 * 
 * @author Jarmanjit Singh
 * @since 21
 * @version 1.0
 */
public class PerformanceDashboardCommand implements Command {

    private final LoggingSystem loggingSystem;
    private final String operatorId;

    /**
     * Constructs the PerformanceDashboardCommand with the receiver and operator id.
     * @param loggingSystem Receiver object that performs the actual action.
     * @param operatorId ID of the operator to generate dashboard for.
     */
    public PerformanceDashboardCommand(LoggingSystem loggingSystem, String operatorId) {
        this.loggingSystem = loggingSystem;
        this.operatorId = operatorId;
    }

    /**
     * Executes the command by invoking the receiver's dashboard generation method.
     */
    @Override
    public void execute() {
        loggingSystem.generatePerformanceDashboard(operatorId);
    }
}
