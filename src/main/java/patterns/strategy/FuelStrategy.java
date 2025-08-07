package patterns.strategy;

/**
 * Represents a strategy for calculating fuel or energy consumption.
 * @author Tasmia Aboni
 */
public interface FuelStrategy {

    /**
     * Calculates the fuel or energy consumption for a given distance.
     * @param distance The distance traveled in kilometers.
     * @return The total fuel or energy consumption, typically in liters (L) or kilowatt-hours (kWh).
     */
    double calculateFuelConsumption(double distance);

    /**
     * Provides a description of the fuel or energy consumption strategy.
     * @return A string describing the strategy.
     */
    String getStrategyDescription();
}