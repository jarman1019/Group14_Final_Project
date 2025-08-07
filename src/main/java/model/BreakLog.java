/*
 * File name: BreakLog.java
 * Author: Jarmanjit Singh, 041154515
 * Course: 25S CST8288 Section 024 
 * Assignment: Final Project
 * Date: Aug 7, 2025
 * Professor: Marwan Farah
 * Purpose: 
 *   Represents a record of an operator's break or out-of-service event,
 *   including the operator's ID, reason for break, and timestamp of the event.
 */

package model;

import java.util.Date;

/**
 * Represents an operator break log entry with details about the break reason and timestamp.
 * Immutable class built using the Builder pattern.
 * 
 * @author Jarmanjit Singh
 * @since 21
 * @version 1.0
 */
public class BreakLog {

    /** Operator identifier */
    private final int operatorId;

    /** Reason for the break or out-of-service status */
    private final String reason;

    /** Timestamp when the break occurred */
    private final Date timestamp;

    /**
     * Private constructor to enforce usage of Builder for instance creation.
     * 
     * @param builder Builder instance containing the data.
     */
    private BreakLog(Builder builder) {
        this.operatorId = builder.operatorId;
        this.reason = builder.reason;
        this.timestamp = builder.timestamp;
    }

    /**
     * Gets the operator ID.
     * 
     * @return Operator ID as int.
     */
    public int getOperatorId() {
        return operatorId;
    }

    /**
     * Gets the reason for the break.
     * 
     * @return Reason string.
     */
    public String getReason() {
        return reason;
    }

    /**
     * Gets the timestamp of the break.
     * 
     * @return Timestamp as Date.
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * Builder class for constructing immutable {@link BreakLog} instances.
     */
    public static class Builder {

        /** Operator identifier */
        private final int operatorId;

        /** Reason for the break */
        private final String reason;

        /** Timestamp of the break */
        private final Date timestamp;

        /**
         * Constructs a Builder with required break log parameters.
         * 
         * @param operatorId ID of the operator.
         * @param reason Reason for break.
         * @param timestamp Timestamp of break event.
         */
        public Builder(int operatorId, String reason, Date timestamp) {
            this.operatorId = operatorId;
            this.reason = reason;
            this.timestamp = timestamp;
        }

        /**
         * Builds and returns the {@link BreakLog} instance.
         * 
         * @return New BreakLog object.
         */
        public BreakLog build() {
            return new BreakLog(this);
        }
    }

    /**
     * Returns a string representation of the BreakLog object.
     * 
     * @return Formatted string showing operator ID, reason, and timestamp.
     */
    @Override
    public String toString() {
        return String.format(
            "BreakLog[OperatorId: %d, Reason: %s, Timestamp: %s]", 
            operatorId, reason, timestamp.toString()
        );
    }
}
