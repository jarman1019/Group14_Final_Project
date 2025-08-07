package patterns.strategy;

/**
 * Represents a strategy for calculating fuel consumption for diesel-powered vehicles.
 * @author Tasmia Aboni
 */
public class DieselFuelStrategy implements FuelStrategy {

    /**
     * Calculates the fuel consumption for a given distance.
     * @param distance The distance traveled in kilometers.
     * @return The total fuel consumption in liters.
     */
    @Override
    public double calculateFuelConsumption(double distance) {
        return distance * 0.25;
    }

    /**
     * Provides a description of the diesel fuel consumption strategy.
     *
     * @return A string describing the strategy, including the consumption rate.
     */
    @Override
    public String getStrategyDescription() {
        return "Diesel consumption strategy (0.25L/km)";
    }
}