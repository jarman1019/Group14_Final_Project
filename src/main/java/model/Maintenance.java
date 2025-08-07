package model;


import java.time.LocalDate;
/**
 * Represents a Maintenance record in the system.
 * @author Kunj
 */
public class Maintenance {
    /**
     * The unique identifier for the maintenance record.
     */
    private final int maintenanceId;

    /**
     * The ID of the vehicle associated with this maintenance record.
     */
    private final int vehicleId;

    /**
     * The type of alert for this maintenance record (e.g., "Service", "Repair").
     */
    private final String alertType;

    /**
     * The scheduled date for this maintenance activity.
     */
    private final LocalDate scheduledDate;

    /**
     * Private constructor to enforce the use of the Builder for creating instances.
     *
     * @param builder The Builder instance used to construct this Maintenance object.
     */
    private Maintenance(Builder builder) {
        this.maintenanceId = builder.maintenanceId;
        this.vehicleId = builder.vehicleId;
        this.alertType = builder.alertType;
        this.scheduledDate = builder.scheduledDate;
    }

    /**
     * Retrieves the unique identifier for the maintenance record.
     *
     * @return The maintenance ID.
     */
    public int getMaintenanceId() { return maintenanceId; }

    /**
     * Retrieves the ID of the vehicle associated with this maintenance record.
     *
     * @return The vehicle ID.
     */
    public int getVehicleId() { return vehicleId; }

    /**
     * Retrieves the type of alert for this maintenance record.
     *
     * @return The alert type.
     */
    public String getAlertType() { return alertType; }

    /**
     * Retrieves the scheduled date for this maintenance activity.
     *
     * @return The scheduled date.
     */
    public LocalDate getScheduledDate() { return scheduledDate; }

    /**
     * Returns a string representation of the Maintenance object.
     *
     * @return A string containing the maintenance ID, vehicle ID, alert type, and scheduled date.
     */
    @Override
    public String toString() {
        return "Maintenance{" +
                "maintenanceId=" + maintenanceId +
                ", vehicleId=" + vehicleId +
                ", alertType='" + alertType + '\'' +
                ", scheduledDate=" + scheduledDate +
                '}';
    }

    /**
     * A builder class for constructing immutable Maintenance objects.
     */
    public static class Builder {
        private int maintenanceId;
        private final int vehicleId;
        private final String alertType;
        private final LocalDate scheduledDate;

        /**
         * Constructs a Builder with mandatory fields for creating a Maintenance object.
         *
         * @param vehicleId      The ID of the vehicle associated with the maintenance record.
         * @param alertType      The type of alert for the maintenance record.
         * @param scheduledDate  The scheduled date for the maintenance activity.
         */
        public Builder(int vehicleId, String alertType, LocalDate scheduledDate) {
            this.vehicleId = vehicleId;
            this.alertType = alertType;
            this.scheduledDate = scheduledDate;
        }

        /**
         * Sets the maintenance ID for the Maintenance object.
         *
         * @param maintenanceId The unique identifier for the maintenance record.
         * @return This Builder instance for method chaining.
         */
        public Builder withMaintenanceId(int maintenanceId) {
            this.maintenanceId = maintenanceId;
            return this;
        }

        /**
         * Builds and validates the Maintenance object.
         *
         * @return A new Maintenance object constructed using this Builder.
         * @throws IllegalStateException If validation fails (e.g., alert type is null or empty,
         *                               or scheduled date is null).
         */
        public Maintenance build() {
            validate();
            return new Maintenance(this);
        }

        /**
         * Validates the fields of the Builder before constructing the Maintenance object.
         *
         * @throws IllegalStateException If the alert type is null or empty, or if the scheduled date is null.
         */
        private void validate() {
            if (alertType == null || alertType.isEmpty()) {
                throw new IllegalStateException("Alert type cannot be null or empty");
            }
            if (scheduledDate == null) {
                throw new IllegalStateException("Scheduled date cannot be null");
            }
        }
    }
}