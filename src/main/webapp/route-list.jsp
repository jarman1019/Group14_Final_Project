<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Routes</title>
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
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        table th, table td {
            padding: 8px 12px;
            text-align: left;
            border: 1px solid #ddd;
        }
        table th {
            background-color: #f4f4f4;
        }
        
        #back{
            margin-top:100px;
        }
    </style>
</head>
<body>
    <h1>Routes</h1>

    <a href="add-route.jsp" class="btn">Add New Route</a>

    <table>
        <thead>
            <tr>
                <th>Route ID</th>
                <th>Route Name</th>
                <th>Start Point</th>
                <th>End Point</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="route" items="${routes}">
                <tr>
                    <td>${route.routeId}</td>
                    <td>${route.routeName}</td>
                    <td>${route.startPoint}</td>
                    <td>${route.endPoint}</td>
                    <td>
                        <a href="RouteController?action=edit&routeId=${route.routeId}" class="btn">Edit</a> |
                        <a href="RouteController?action=delete&routeId=${route.routeId}" onclick="return confirm('Are you sure you want to delete?');" class="btn">Delete</a> |
                        <a href="RouteController?action=routeDescription&routeId=${route.routeId}" class="btn">Route Description</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <a href="dashboard.jsp" class="btn" id="back">Go to Homepage</a>
</body>
</html>
