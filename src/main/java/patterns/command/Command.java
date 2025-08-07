/*
 * File name: Command.java
 * Author: Jarmanjit Singh, 041154515
 * Course: 25S CST8288 Section 024
 * Assignment: Final Project
 * Date: Aug 9, 2025
 * Professor: Marwan Farah
 * Purpose: Defines the Command interface for implementing the Command design pattern.
 */
package patterns.command;

/**
 * Represents a command that can be executed.
 * This interface declares a method for executing a command, 
 * supporting encapsulation of request as an object.
 * 
 * @author Jarmanjit Singh
 * @since 21
 * @version 1.0
 */
public interface Command {
    /**
     * Executes the command action.
     */
    void execute();
}
