<%@ page import="java.util.List" %>
<%@ page import="model.Location" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Location History</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f7fc;
            margin: 0;
            padding: 0 20px 40px 20px;
            display: flex;
            justify-content: center;
            align-items: flex-start;
            min-height: 100vh;
            flex-direction: column;
        }

        h2, h3 {
            color: #333;
            text-align: center;
            margin-bottom: 20px;
        }

        form {
            max-width: 400px;
            margin: 0 auto 30px auto;
            background: white;
            padding: 20px 25px;
            border-radius: 8px;
            box-shadow: 0 0 12px rgba(0,0,0,0.1);
        }

        form label {
            display: block;
            margin-bottom: 15px;
            font-weight: 600;
            color: #555;
        }

        form input[type="text"] {
            width: 100%;
            padding: 8px 10px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 14px;
        }

        form input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 12px 30px;
            font-size: 16px;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            display: block;
            margin: 0 auto;
        }

        form input[type="submit"]:hover {
            background-color: #45a049;
        }

        table {
            border-collapse: collapse;
            width: 100%;
            max-width: 700px;
            margin: 0 auto;
            background-color: white;
            box-shadow: 0 0 12px rgba(0,0,0,0.1);
        }

        th, td {
            border: 1px solid #ddd;
            padding: 12px;
            text-align: center;
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
            margin-top: 30px;
        }

        .back-link {
            display: block;
            text-align: center;
            margin-top: 25px;
            font-size: 16px;
            text-decoration: none;
            color: #007bff;
            transition: color 0.3s ease;
        }

        .back-link:hover {
            color: #0056b3;
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <h2>Get Location History</h2>
    <form action="location" method="get">
        <input type="hidden" name="action" value="history"/>
        <label>Vehicle ID:
            <input type="text" name="vehicleId" required>
        </label>
        <label>From (yyyy-MM-dd HH:mm:ss):
            <input type="text" name="from" required>
        </label>
        <label>To (yyyy-MM-dd HH:mm:ss):
            <input type="text" name="to" required>
        </label>
        <input type="submit" value="Fetch History"/>
    </form>

    <%
        List<Location> locations = (List<Location>) request.getAttribute("locations");
        if (locations != null && !locations.isEmpty()) {
    %>
        <h3>Results:</h3>
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
        <p>No location history found.</p>
    <%
        }
    %>

    <a href="dashboard.jsp" class="back-link">Back to Dashboard</a>
</body>
</html>
