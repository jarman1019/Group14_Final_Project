/**
 * @author varshil
 */

package model;

/**
 * Represents a Route in the system.
 */
public class Route {
    /**
     * The unique identifier for the route.
     */
    private final int routeId;

    /**
     * The name of the route.
     */
    private final String routeName;

    /**
     * The starting point of the route.
     */
    private final String startPoint;

    /**
     * The ending point of the route.
     */
    private final String endPoint;

    /**
     * Private constructor to enforce the use of the Builder for creating instances.
     *
     * @param builder The Builder instance used to construct this Route object.
     */
    private Route(Builder builder) {
        this.routeId = builder.routeId;
        this.routeName = builder.routeName;
        this.startPoint = builder.startPoint;
        this.endPoint = builder.endPoint;
    }

    /**
     * Retrieves the unique identifier for the route.
     *
     * @return The route ID.
     */
    public int getRouteId() {
        return routeId;
    }

    /**
     * Retrieves the name of the route.
     *
     * @return The route name.
     */
    public String getRouteName() {
        return routeName;
    }

    /**
     * Retrieves the starting point of the route.
     *
     * @return The start point.
     */
    public String getStartPoint() {
        return startPoint;
    }

    /**
     * Retrieves the ending point of the route.
     *
     * @return The end point.
     */
    public String getEndPoint() {
        return endPoint;
    }

    /**
     * Returns a string representation of the Route object.
     *
     * @return A string containing the route ID, route name, start point, and end point.
     */
    @Override
    public String toString() {
        return "Route{" +
                "routeId=" + routeId +
                ", routeName='" + routeName + '\'' +
                ", startPoint='" + startPoint + '\'' +
                ", endPoint='" + endPoint + '\'' +
                '}';
    }

    /**
     * A builder class for constructing immutable Route objects.
     */
    public static class Builder {
        private int routeId;
        private String routeName;
        private String startPoint;
        private String endPoint;

        /**
         * Constructs a Builder with mandatory fields for creating a Route object.
         *
         * @param routeId   The unique identifier for the route.
         * @param routeName The name of the route.
         */
        public Builder(int routeId, String routeName) {
            this.routeId = routeId;
            this.routeName = routeName;
        }

        /**
         * Sets the starting point for the Route object.
         *
         * @param startPoint The starting point of the route.
         * @return This Builder instance for method chaining.
         */
        public Builder withStartPoint(String startPoint) {
            this.startPoint = startPoint;
            return this;
        }

        /**
         * Sets the ending point for the Route object.
         *
         * @param endPoint The ending point of the route.
         * @return This Builder instance for method chaining.
         */
        public Builder withEndPoint(String endPoint) {
            this.endPoint = endPoint;
            return this;
        }

        /**
         * Builds and validates the Route object.
         *
         * @return A new Route object constructed using this Builder.
         * @throws IllegalStateException If validation fails (e.g., route name, start point,
         *                               or end point is null or empty).
         */
        public Route build() {
            validate();
            return new Route(this);
        }

        /**
         * Validates the fields of the Builder before constructing the Route object.
         *
         * @throws IllegalStateException If the route name, start point, or end point is null or empty.
         */
        private void validate() {
            if (routeName == null || routeName.isEmpty()) {
                throw new IllegalStateException("Route name cannot be null or empty");
            }
            if (startPoint == null || startPoint.isEmpty()) {
                throw new IllegalStateException("Start point cannot be null or empty");
            }
            if (endPoint == null || endPoint.isEmpty()) {
                throw new IllegalStateException("End point cannot be null or empty");
            }
        }
    }
}