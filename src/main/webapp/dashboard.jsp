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
        /* Reset & base */
        * {
            box-sizing: border-box;
        }
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #e2f0fb 0%, #cde6fb 100%);
            margin: 0;
            padding: 40px 20px;
            display: flex;
            justify-content: center;
            align-items: flex-start;
            min-height: 100vh;
        }

        /* Container */
        ul.dashboard-links {
            background: white;
            padding: 40px 60px;
            border-radius: 12px;
            box-shadow: 0 12px 30px rgba(0, 0, 0, 0.1);
            max-width: 450px;
            width: 100%;
            text-align: center;
        }

        /* Heading */
        h2 {
            margin-bottom: 8px;
            font-weight: 700;
            color: #1a2935;
            font-size: 28px;
        }

        p {
            margin-top: 0;
            margin-bottom: 30px;
            color: #5a6d7c;
            font-size: 17px;
            font-weight: 500;
            letter-spacing: 0.02em;
        }

        /* List style */
        ul.dashboard-links li {
            margin: 14px 0;
        }

        /* Link style */
        ul.dashboard-links a {
            display: block;
            background-color: #007bff;
            color: white;
            font-weight: 600;
            text-decoration: none;
            padding: 14px 25px;
            border-radius: 8px;
            font-size: 17px;
            transition: background-color 0.3s ease;
            box-shadow: 0 3px 7px rgb(0 123 255 / 0.4);
            user-select: none;
        }

        ul.dashboard-links a:hover,
        ul.dashboard-links a:focus {
            background-color: #0056b3;
            box-shadow: 0 6px 14px rgb(0 86 179 / 0.6);
        }

        /* Logout special button */
        ul.dashboard-links a.logout-link {
            background-color: #dc3545;
            box-shadow: 0 3px 7px rgb(220 53 69 / 0.5);
        }

        ul.dashboard-links a.logout-link:hover,
        ul.dashboard-links a.logout-link:focus {
            background-color: #b02a37;
            box-shadow: 0 6px 14px rgb(176 42 55 / 0.75);
        }

        /* Responsive */
        @media (max-width: 480px) {
            ul.dashboard-links {
                padding: 30px 20px;
                max-width: 100%;
            }

            ul.dashboard-links a {
                padding: 12px 20px;
                font-size: 15px;
            }

            h2 {
                font-size: 24px;
            }

            p {
                font-size: 15px;
            }
        }
    </style>
</head>
<body>
    <ul class="dashboard-links">
        <h2>Welcome, <%= currentUser.getName() %>!</h2>
        <p>You are logged in as <%= currentUser.getUserType() %></p>

        <li><a href="VehicleController?action=list">Vehicles</a></li>
        <li><a href="MaintenanceController?action=list">Maintenance</a></li>
        <li><a href="RouteController?action=list">Routes</a></li>
        <li><a href="UserController?action=list">Users</a></li>
        <li><a href="current-location.jsp">Get Current Location</a></li>
        <li><a href="location-history.jsp">Get Location History</a></li>
        <li><a href="vehicle-map.jsp">View Active Vehicle Locations</a></li>
        <li><a href="location-form.jsp">Update Vehicle Location</a></li>
        <li><a href="breaklog-form.jsp">Add Break</a></li>
        <li><a href="logout.jsp" class="logout-link">Logout</a></li>
    </ul>
</body>
</html>
