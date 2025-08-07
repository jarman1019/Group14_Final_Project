-- Drop the database if it exists and create a new one
DROP DATABASE IF EXISTS PTFMS;
CREATE DATABASE PTFMS;
USE PTFMS;

-- Create the User table with auto-incrementing user_id
CREATE TABLE User (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    user_type VARCHAR(50) NOT NULL
);

-- Create the Route table with auto-incrementing route_id
CREATE TABLE Route (
    route_id INT AUTO_INCREMENT PRIMARY KEY,
    route_name VARCHAR(100) NOT NULL,
    start_point VARCHAR(100) NOT NULL,
    end_point VARCHAR(100) NOT NULL
);

-- Create the Vehicle table with auto-incrementing vehicle_id
CREATE TABLE Vehicle (
    vehicle_id INT AUTO_INCREMENT PRIMARY KEY,
    vehicle_type VARCHAR(50) NOT NULL,
    vehicle_number VARCHAR(50) NOT NULL UNIQUE,
    fuel_type VARCHAR(50) NOT NULL,
    max_passengers INT NOT NULL,
    consumption_rate DOUBLE NOT NULL,
    route_id INT,
    FOREIGN KEY (route_id) REFERENCES Route(route_id)
);

-- Create the Maintenance table with auto-incrementing maintenance_id
CREATE TABLE Maintenance (
    maintenance_id INT AUTO_INCREMENT PRIMARY KEY,
    vehicle_id INT NOT NULL,
    alert_type VARCHAR(100) NOT NULL,
    scheduled_date DATE NOT NULL,
    FOREIGN KEY (vehicle_id) REFERENCES Vehicle(vehicle_id)
);

-- Create the UserVehicle junction table
CREATE TABLE UserVehicle (
    user_id INT NOT NULL,
    vehicle_id INT NOT NULL,
    PRIMARY KEY (user_id, vehicle_id),
    FOREIGN KEY (user_id) REFERENCES User(user_id),
    FOREIGN KEY (vehicle_id) REFERENCES Vehicle(vehicle_id)
);

CREATE TABLE Location (
    location_id INT AUTO_INCREMENT PRIMARY KEY,
    vehicle_id INT NOT NULL,
    latitude DECIMAL(10, 8) NOT NULL,
    longitude DECIMAL(11, 8) NOT NULL,
    timestamp DATETIME NOT NULL,
    FOREIGN KEY (vehicle_id) REFERENCES Vehicle(vehicle_id)
);

-- Add necessary ALTER statements for cascading behavior

-- Update the Maintenance table to use ON DELETE CASCADE
ALTER TABLE Maintenance
DROP FOREIGN KEY maintenance_ibfk_1;

ALTER TABLE Maintenance
ADD CONSTRAINT maintenance_ibfk_1
FOREIGN KEY (vehicle_id) REFERENCES Vehicle(vehicle_id)
ON DELETE CASCADE;

-- Update the UserVehicle table to use ON DELETE CASCADE for both foreign keys
ALTER TABLE UserVehicle
DROP FOREIGN KEY uservehicle_ibfk_1;

ALTER TABLE UserVehicle
ADD CONSTRAINT uservehicle_ibfk_1
FOREIGN KEY (user_id) REFERENCES User(user_id)
ON DELETE CASCADE;

ALTER TABLE UserVehicle
DROP FOREIGN KEY uservehicle_ibfk_2;

ALTER TABLE UserVehicle
ADD CONSTRAINT uservehicle_ibfk_2
FOREIGN KEY (vehicle_id) REFERENCES Vehicle(vehicle_id)
ON DELETE CASCADE;

-- Update the Vehicle table to use ON DELETE SET NULL for route_id
ALTER TABLE Vehicle
DROP FOREIGN KEY vehicle_ibfk_1;

ALTER TABLE Vehicle
ADD CONSTRAINT vehicle_ibfk_1
FOREIGN KEY (route_id) REFERENCES Route(route_id)
ON DELETE SET NULL;