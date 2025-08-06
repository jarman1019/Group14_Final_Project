-- Insert sample data into the User table
INSERT INTO User (name, email, password, user_type) VALUES
('John Doe', 'john.doe@example.com', 'password123', 'admin'),
('Jane Smith', 'jane.smith@example.com', 'securepass', 'driver'),
('Alice Johnson', 'alice.johnson@example.com', 'alicepass', 'driver'),
('Bob Brown', 'bob.brown@example.com', 'bobbypass', 'passenger'),
('Eve Green', 'eve.green@example.com', 'evepass', 'passenger');

-- Insert sample data into the Route table
INSERT INTO Route (route_name, start_point, end_point) VALUES
('Route A', 'Central Station', 'North Terminal'),
('Route B', 'East Gate', 'West Gate'),
('Route C', 'Downtown', 'Suburbia'),
('Route D', 'Airport', 'City Center'),
('Route E', 'Harbor', 'Industrial Zone');

-- Insert sample data into the Vehicle table
INSERT INTO Vehicle (vehicle_type, vehicle_number, fuel_type, max_passengers, consumption_rate, route_id) VALUES
('Bus', 'BUS1234', 'Diesel', 50, 8.5, 1),
('Van', 'VAN5678', 'Petrol', 12, 10.0, 2),
('Taxi', 'TAXI9101', 'CNG', 4, 12.5, 3),
('Truck', 'TRUCK1122', 'Diesel', 2, 15.0, 4),
('Minibus', 'MINI3344', 'Electric', 20, 5.0, 5);

-- Insert sample data into the Maintenance table
INSERT INTO Maintenance (vehicle_id, alert_type, scheduled_date) VALUES
(1, 'Oil Change', '2023-11-15'),
(2, 'Brake Check', '2023-12-01'),
(3, 'Battery Replacement', '2023-11-20'),
(4, 'Tire Rotation', '2023-12-10'),
(5, 'Software Update', '2023-12-05');

-- Insert sample data into the UserVehicle junction table
INSERT INTO UserVehicle (user_id, vehicle_id) VALUES
(2, 1), -- Jane Smith drives Bus BUS1234
(3, 2), -- Alice Johnson drives Van VAN5678
(4, 3), -- Bob Brown uses Taxi TAXI9101
(5, 4), -- Eve Green uses Truck TRUCK1122
(2, 5); -- Jane Smith also drives Minibus MINI3344