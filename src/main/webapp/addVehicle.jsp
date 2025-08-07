<%@ page import="model.Vehicle" %>
<%@ page import="model.VehicleType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Vehicle</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f7fc;
            margin: 0;
            padding: 30px;
        }

        h2 {
            text-align: center;
            color: #333;
        }

        form {
            background-color: #fff;
            padding: 25px;
            border-radius: 8px;
            max-width: 500px;
            margin: auto;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
        }

        label, select, input {
            display: block;
            width: 100%;
            margin-bottom: 15px;
        }

        input[type="text"],
        input[type="number"],
        select {
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 6px;
            font-size: 14px;
        }

        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-size: 16px;
            width: 100%;
            margin-top: 10px;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }

        a {
            display: block;
            text-align: center;
            margin-top: 15px;
            color: #2196F3;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }

        .form-group {
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
<h2>Add Vehicle</h2>

<form action="VehicleController" method="post">
    <input type="hidden" name="action" value="insert"/>

    <div class="form-group">
        <label for="vehicleType">Vehicle Type:</label>
        <select name="vehicleType" id="vehicleType" required>
            <% for (VehicleType type : VehicleType.values()) { %>
                <option value="<%= type %>"><%= type %></option>
            <% } %>
        </select>
    </div>

    <div class="form-group">
        <label for="vehicleNumber">Vehicle Number:</label>
        <input type="text" id="vehicleNumber" name="vehicleNumber" required/>
    </div>

    <div class="form-group">
        <label for="fuelType">Fuel Type:</label>
        <input type="text" id="fuelType" name="fuelType" required/>
    </div>

    <div class="form-group">
        <label for="consumptionRate">Consumption Rate:</label>
        <input type="number" id="consumptionRate" step="0.01" name="consumptionRate" required/>
    </div>

    <div class="form-group">
        <label for="maxPassengers">Max Passengers:</label>
        <input type="number" id="maxPassengers" name="maxPassengers" required/>
    </div>

    <div class="form-group">
        <label for="routeId">Route ID:</label>
        <input type="number" id="routeId" name="routeId"/>
    </div>

    <input type="submit" value="Add Vehicle"/>
    <a href="VehicleController?action=list">Cancel</a>
</form>

</body>
</html>
