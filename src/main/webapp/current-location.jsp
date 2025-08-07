<%@ page import="model.Location" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>Current Location</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f7fc;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .form-container {
            background: white;
            padding: 25px 30px;
            border-radius: 8px;
            box-shadow: 0 4px 10px rgba(0,0,0,0.1);
            width: 400px;
            box-sizing: border-box;
            text-align: center;
        }
        h2 {
            color: #333;
            margin-bottom: 20px;
        }
        label {
            display: block;
            font-size: 14px;
            color: #555;
            margin-bottom: 12px;
        }
        input[type="text"] {
            width: 100%;
            padding: 10px 12px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 14px;
            box-sizing: border-box;
        }
        input[type="submit"] {
            width: 100%;
            padding: 12px 0;
            background-color: #4CAF50;
            color: white;
            font-size: 16px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-top: 15px;
            transition: background-color 0.3s ease;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        .result {
            margin-top: 25px;
            text-align: left;
            font-size: 15px;
            color: #333;
        }
        .result p {
            margin: 6px 0;
        }
        a.back-link {
            display: block;
            margin-top: 30px;
            color: #4CAF50;
            text-decoration: none;
            font-size: 15px;
        }
        a.back-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h2>Get Current Location</h2>
        <form action="location" method="get">
            <input type="hidden" name="action" value="current"/>
            <label for="vehicleId">Vehicle ID:
                <input type="text" id="vehicleId" name="vehicleId" required />
            </label>
            <input type="submit" value="Get Location"/>
        </form>

        <%
            Location location = (Location) request.getAttribute("location");
            if (location != null) {
        %>
            <div class="result">
                <h3>Result:</h3>
                <p><strong>Vehicle ID:</strong> <%= location.getVehicleId() %></p>
                <p><strong>Latitude:</strong> <%= location.getLatitude() %></p>
                <p><strong>Longitude:</strong> <%= location.getLongitude() %></p>
                <p><strong>Timestamp:</strong> <%= location.getTimestamp() %></p>
            </div>
        <%
            }
        %>

        <a href="dashboard.jsp" class="back-link">Back to Dashboard</a>
    </div>
</body>
</html>
