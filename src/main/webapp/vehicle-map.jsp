<%@ page import="java.util.List" %>
<%@ page import="model.Location" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Active Vehicles</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f7fc;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: flex-start;
            min-height: 100vh;
            flex-direction: column;
            padding: 30px;
        }

        h2 {
            color: #333;
            margin-bottom: 20px;
            text-align: center;
        }

        form {
            text-align: center;
            margin-bottom: 20px;
        }

        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 12px 25px;
            font-size: 16px;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }

        table {
            border-collapse: collapse;
            width: 100%;
            max-width: 700px;
            margin: 0 auto 20px auto;
            background-color: white;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }

        th, td {
            border: 1px solid #ddd;
            text-align: center;
            padding: 12px;
        }

        th {
            background-color: #007bff;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        p {
            text-align: center;
            font-size: 18px;
            color: #555;
        }

        .back-link {
            display: block;
            text-align: center;
            margin-top: 10px;
            font-size: 16px;
            text-decoration: none;
            color: #007bff;
            transition: color 0.3s;
        }

        .back-link:hover {
            color: #0056b3;
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <h2>Active Vehicles in Last 5 Minutes</h2>

    <form action="location" method="get">
        <input type="submit" value="Refresh List"/>
    </form>

    <%
        List<Location> locations = (List<Location>) request.getAttribute("locations");
        if (locations != null && !locations.isEmpty()) {
    %>
        <table>
            <tr>
                <th>Vehicle ID</th>
                <th>Latitude</th>
                <th>Longitude</th>
                <th>Timestamp</th>
            </tr>
            <%
                for (Location loc : locations) {
            %>
            <tr>
                <td><%= loc.getVehicleId() %></td>
                <td><%= loc.getLatitude() %></td>
                <td><%= loc.getLongitude() %></td>
                <td><%= loc.getTimestamp() %></td>
            </tr>
            <%
                }
            %>
        </table>
    <%
        } else {
    %>
        <p>No active locations found.</p>
    <%
        }
    %>

    <a href="dashboard.jsp" class="back-link">Back to Dashboard</a>
</body>
</html>
