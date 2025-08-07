<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.User" %>
<%
    User currentUser = (User) session.getAttribute("currentUser");
    if (currentUser == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<html>
<head>
    <title>Dashboard</title>
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
            flex-direction: column;
        }

        h2 {
            color: #333;
            margin-bottom: 20px;
        }

        p {
            color: #555;
            font-size: 16px;
            margin-bottom: 20px;
        }

        .dashboard-links {
            list-style: none;
            padding: 0;
            text-align: center;
        }

        .dashboard-links a {
            display: inline-block;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            padding: 12px 20px;
            margin: 10px;
            font-size: 16px;
            border-radius: 4px;
            transition: background-color 0.3s;
        }

        .dashboard-links a:hover {
            background-color: #45a049;
        }

        .logout-link {
            background-color: #f44336;
        }

        .logout-link:hover {
            background-color: #e53935;
        }
    </style>
</head>
<body>
    <h2>Welcome, <%= currentUser.getName() %>!</h2>
    <p>You are logged in as <%= currentUser.getUserType() %></p>

    <ul class="dashboard-links">
        <li><a href="VehicleController?action=list">Vehicles</a></li>
        <li><a href="MaintenanceController?action=list">Maintenance</a></li>
        <li><a href="RouteController?action=list">Routes</a></li>
        <li><a href="UserController?action=list">Users</a></li>
        
        <li><a href="current-location.jsp">Get Current Location</a></li>
        <li><a href="location-history.jsp">Get Location History</a></li>
        <li><a href="vehicle-map.jsp">View Active Vehicle Locations</a></li>
        <li><a href="location-form.jsp">Update Vehicle Location</a></li>
        
        <li><a href="logout.jsp" class="logout-link">Logout</a></li>
    </ul>
</body>
</html>
