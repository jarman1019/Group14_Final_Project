/*
 * File name: LogBreakCommand.java
 * Author: Jarmanjit Singh, 041154515
 * Course: 25S CST8288 Section 024
 * Assignment: Final Project
 * Date: Aug 9, 2025
 * Professor: Marwan Farah
 * Purpose: Implements the Command to log an operator's break using the LoggingSystem.
 */

package patterns.command;

/**
 * Concrete Command implementation to log an operator's break.
 * Delegates the action to the LoggingSystem with the operator's ID.
 * 
 * @author Jarmanjit Singh
 * @since 21
 * @version 1.0
 */
public class LogBreakCommand implements Command {
    /**
     * The receiver that performs the actual logging.
     */
    private LoggingSystem loggingSystem;
    
    /**
     * The identifier of the operator taking the break.
     */
    private String operatorId;

    /**
     * Constructs the command with the given LoggingSystem and operator ID.
     * @param loggingSystem The logging system to execute the command on.
     * @param operatorId The operator's unique identifier.
     */
    public LogBreakCommand(LoggingSystem loggingSystem, String operatorId) {
        this.loggingSystem = loggingSystem;
        this.operatorId = operatorId;
    }

    /**
     * Executes the command by logging the operator's break.
     */
    @Override
    public void execute() {
        loggingSystem.logOperatorBreak(operatorId);
    }
}
