<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="model.Maintenance" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${maintenance != null ? 'Edit' : 'Add'} Maintenance</title>
    <style>
        /* Adding some basic styles directly to the page */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }

        .form-container {
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            max-width: 600px;
            margin: auto;
        }

        h1 {
            text-align: center;
            color: #333;
        }

        .form-container label {
            display: block;
            margin: 10px 0 5px;
            font-weight: bold;
        }

        .form-container input {
            width: 100%;
            padding: 10px;
            margin: 5px 0 20px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        .form-container button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            width: 100%;
            font-size: 16px;
        }

        .form-container button:hover {
            background-color: #45a049;
        }

        .form-container .cancel-btn {
            background-color: #f44336;
            margin-top: 10px;
        }

        .form-container .cancel-btn:hover {
            background-color: #d32f2f;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h1>${maintenance != null ? 'Edit' : 'Add'} Maintenance</h1>

        <form action="MaintenanceController" method="post">
            <input type="hidden" name="maintenanceId" value="${maintenance != null ? maintenance.maintenanceId : ''}">

            <label for="vehicleId">Vehicle ID:</label>
            <input type="number" id="vehicleId" name="vehicleId" value="${maintenance != null ? maintenance.vehicleId : ''}" required>

            <label for="alertType">Alert Type:</label>
            <input type="text" id="alertType" name="alertType" value="${maintenance != null ? maintenance.alertType : ''}" required>

            <label for="scheduledDate">Scheduled Date:</label>
            <input type="date" id="scheduledDate" name="scheduledDate" value="${maintenance != null ? maintenance.scheduledDate : ''}" required>

            <button type="submit">Save Maintenance</button>
        </form>

        <a href="MaintenanceController?action=list" class="btn cancel-btn">Cancel</a>
    </div>
</body>
</html>
