<%@ page import="model.Vehicle" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Vehicle List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f7fc;
            margin: 0;
            padding: 20px;
        }

        h2 {
            text-align: center;
            color: #333;
        }

        a {
            text-decoration: none;
            background-color: #4CAF50;
            color: white;
            padding: 10px 15px;
            border-radius: 4px;
            transition: background-color 0.3s;
            margin-bottom: 20px;
            display: inline-block;
        }

        a:hover {
            background-color: #45a049;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background-color: white;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #ccc;
            padding: 12px;
            text-align: center;
        }

        th {
            background-color: #f0f0f0;
            color: #444;
        }

        td a {
            background-color: #2196F3;
            padding: 5px 10px;
            border-radius: 3px;
            color: white;
            margin: 0 3px;
            font-size: 14px;
        }

        td a:hover {
            background-color: #0b7dda;
        }

        td a:last-child {
            background-color: #f44336;
        }

        td a:last-child:hover {
            background-color: #e53935;
        }
                    #back {
                display: block;
                text-align: center;
                text-decoration: none;
                color: #007BFF;
                font-size: 16px;
                margin-top: 20px;
            }
            #back {
                background-color: #4CAF50;
                color: white;
                padding: 10px 15px;
                text-decoration: none;
                border-radius: 5px;
            }
            #back:hover {
                background-color: #45a049;
            }
            #back:hover {
                text-decoration: underline;
            }
    </style>
</head>
<body>
    <h2>Vehicles</h2>
    <a href="addVehicle.jsp">Add New Vehicle</a>

    <table>
        <tr>
            <th>ID</th>
            <th>Type</th>
            <th>Vehicle Number</th>
            <th>Fuel Type</th>
            <th>Consumption Rate</th>
            <th>Max Passengers</th>
            <th>Route ID</th>
            <th>Actions</th>
        </tr>
        <%
            List<Vehicle> vehicles = (List<Vehicle>) request.getAttribute("vehicles");
            if (vehicles != null) {
                for (Vehicle vehicle : vehicles) {
        %>
        <tr>
            <td><%= vehicle.getVehicleId() %></td>
            <td><%= vehicle.getType() %></td>
            <td><%= vehicle.getVehicleNumber() %></td>
            <td><%= vehicle.getFuelType() %></td>
            <td><%= vehicle.getConsumptionRate() %></td>
            <td><%= vehicle.getMaxPassengers() %></td>
            <td><%= vehicle.getRouteId() %></td>
            <td>
                <a href="VehicleController?action=edit&id=<%=vehicle.getVehicleId()%>">Edit</a>
                <a href="VehicleController?action=delete&id=<%=vehicle.getVehicleId()%>" onclick="return confirm('Are you sure?')">Delete</a>
            </td>
        </tr>
        <%
                }
            }
        %>
    </table>
    <a href="dashboard.jsp" class="btn" id="back">Go to Homepage</a>
</body>
</html>
