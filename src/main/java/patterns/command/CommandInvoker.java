/*
 * File name: CommandInvoker.java
 * Author: Jarmanjit Singh, 041154515
 * Course: 25S CST8288 Section 024
 * Assignment: Final Project
 * Date: Aug 9, 2025
 * Professor: Marwan Farah
 * Purpose: Implements the Invoker class in the Command pattern that invokes commands.
 */

package patterns.command;

/**
 * Invoker class that stores and executes a Command.
 * It allows setting a Command object and triggering its execution.
 * 
 * @author Jarmanjit Singh
 * @since 21
 * @version 1.0
 */
public class CommandInvoker {
    /**
     * The command to be executed.
     */
    private Command command;

    /**
     * Sets the command to be executed by this invoker.
     * @param command The command instance to set.
     */
    public void setCommand(Command command) {
        this.command = command;
    }

    /**
     * Executes the currently set command if it exists.
     */
    public void executeCommand() {
        if (command != null) {
            command.execute();
        }
    }
}
