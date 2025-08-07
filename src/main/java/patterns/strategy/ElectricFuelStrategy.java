package patterns.strategy;

/**
 * Represents a strategy for calculating fuel consumption for electric-powered vehicles.
 * @author Tasmia Aboni
 */
public class ElectricFuelStrategy implements FuelStrategy {

    /**
     * Calculates the energy consumption for a given distance.
     * @param distance The distance traveled in kilometers.
     * @return The total energy consumption in kilowatt-hours (kWh).
     */
    @Override
    public double calculateFuelConsumption(double distance) {
        return distance * 0.15;
    }

    /**
     * Provides a description of the electric energy consumption strategy.
     * @return A string describing the strategy, including the consumption rate.
     */
    @Override
    public String getStrategyDescription() {
        return "Electric consumption strategy (0.15kWh/km)";
    }
}