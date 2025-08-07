<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Maintenance" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Maintenance Records</title>
    <style>
        /* General Reset */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: Arial, sans-serif;
            line-height: 1.6;
            background-color: #f4f4f4;
            color: #333;
        }

        .container {
            width: 90%;
            margin: 20px auto;
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
        }

        header h1 {
            font-size: 2rem;
            color: #333;
        }

        .btn {
            display: inline-block;
            padding: 10px 20px;
            text-align: center;
            font-size: 1rem;
            text-decoration: none;
            border-radius: 5px;
            background-color: #4CAF50;
            color: white;
            transition: background-color 0.3s ease;
        }

        .btn:hover {
            background-color: #45a049;
        }

        .add-new {
            background-color: #007bff;
        }

        .add-new:hover {
            background-color: #0056b3;
        }

        .delete {
            background-color: #dc3545;
        }

        .delete:hover {
            background-color: #c82333;
        }

        .maintenance-table table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        .maintenance-table th, .maintenance-table td {
            padding: 12px;
            text-align: left;
            border: 1px solid #ddd;
        }

        .maintenance-table th {
            background-color: #f2f2f2;
        }

        .maintenance-table tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        .maintenance-table tr:hover {
            background-color: #f1f1f1;
        }

        .maintenance-table td a {
            margin-right: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <header>
            <h1>Maintenance Records</h1>
            <a href="MaintenanceController?action=new" class="btn add-new">Add New Maintenance</a>
        </header>

        <section class="maintenance-table">
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Vehicle ID</th>
                        <th>Alert Type</th>
                        <th>Scheduled Date</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        List<Maintenance> maintenances = (List<Maintenance>) request.getAttribute("maintenances");
                        for (Maintenance maintenance : maintenances) {
                    %>
                    <tr>
                        <td><%= maintenance.getMaintenanceId() %></td>
                        <td><%= maintenance.getVehicleId() %></td>
                        <td><%= maintenance.getAlertType() %></td>
                        <td><%= maintenance.getScheduledDate() %></td>
                        <td>
                            <a href="MaintenanceController?action=edit&maintenanceId=<%= maintenance.getMaintenanceId() %>" class="btn">Edit</a>
                            <a href="MaintenanceController?action=delete&maintenanceId=<%= maintenance.getMaintenanceId() %>" class="btn delete">Delete</a>
                        </td>
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </section>
    </div>
                <a href="dashboard.jsp" class="btn" id="back">Go to Homepage</a>
</body>
</html>
