-- Insert sample data into the User table
INSERT INTO User (name, email, password, user_type) VALUES
('John Doe', 'john.doe@example.com', 'password123', 'operator'),
('Jane Smith', 'jane.smith@example.com', 'securepass', 'manager'),
('Alice Johnson', 'alice.johnson@example.com', 'alicepass', 'operator'),
('Bob Brown', 'bob.brown@example.com', 'bobbypass', 'manager');

-- Insert sample data into the Route table
INSERT INTO Route (route_name, start_point, end_point) VALUES
('Route A', 'Central Station', 'North Terminal'),
('Route B', 'East Gate', 'West Gate'),
('Route C', 'Downtown', 'Suburbia'),
('Route D', 'Airport', 'City Center'),
('Route E', 'Harbor', 'Industrial Zone');

-- Insert sample data into the Vehicle table
INSERT INTO Vehicle (vehicle_type, vehicle_number, fuel_type, max_passengers, consumption_rate, route_id) VALUES
('BUS', 'BUS1234', 'Diesel', 50, 8.5, 1),
('LIGHTRAIL', 'RAIL5678', 'Electric', 12, 10.0, 2),
('TRAIN', 'TRAIN9101', 'Diesel-Electric', 150, 12.5, 3);

-- Insert sample data into the Maintenance table
INSERT INTO Maintenance (vehicle_id, alert_type, scheduled_date) VALUES
(1, 'Oil Change', '2023-11-15'),
(2, 'Brake Check', '2023-12-01'),
(3, 'Battery Replacement', '2023-11-20');

-- Insert sample data into the UserVehicle junction table
INSERT INTO UserVehicle (user_id, vehicle_id) VALUES
(1, 1), 
(2, 2),
(3, 3);


-- Insert sample data into the Location table
INSERT INTO Location (vehicle_id, latitude, longitude, timestamp) VALUES
('1', 45.4215, -75.6972, '2025-08-07 14:30:00'),
('2', 40.7128, -74.0060, '2025-08-07 14:35:00'),
('3', 34.0522, -118.2437, '2025-08-07 14:40:00');

-- Insert into Break log table
INSERT INTO breaklog (operator_id, reason, timestamp) VALUES
(101, 'Lunch break', '2025-08-07 12:00:00'),
(102, 'Medical break', '2025-08-07 13:15:00'),
(103, 'Technical issue', '2025-08-07 14:45:00');

