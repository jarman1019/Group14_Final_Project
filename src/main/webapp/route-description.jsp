<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Route Description</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        h1 {
            color: #4CAF50;
        }
        .btn {
            background-color: #4CAF50;
            color: white;
            padding: 10px 15px;
            text-decoration: none;
            border-radius: 5px;
        }
        .btn:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <h1>Route Description</h1>

    <p><strong>Route ID:</strong> ${route.routeId}</p>
    <p><strong>Route Name:</strong> ${route.routeName}</p>
    <p><strong>Start Point:</strong> ${route.startPoint}</p>
    <p><strong>End Point:</strong> ${route.endPoint}</p>

    <p><strong>Description:</strong> ${routeDescription}</p>

    <a href="RouteController?action=list" class="btn">Back to Route</a>
</body>
</html>
