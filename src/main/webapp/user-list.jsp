<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<html>
    <head>
        <title>User List</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f9;
                margin: 0;
                padding: 0;
            }
            h2 {
                text-align: center;
                color: #333;
                margin-top: 20px;
            }
            .container {
                max-width: 900px;
                margin: 0 auto;
                background-color: #fff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }
            th, td {
                padding: 12px;
                text-align: left;
                border: 1px solid #ddd;
            }
            th {
                background-color: #4CAF50;
                color: white;
            }
            tr:nth-child(even) {
                background-color: #f9f9f9;
            }
            tr:hover {
                background-color: #f1f1f1;
            }
            a {
                display: block;
                text-align: center;
                text-decoration: none;
                color: #007BFF;
                font-size: 16px;
                margin-top: 20px;
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
            a:hover {
                text-decoration: underline;
            }
        </style>
    </head>
    <body>

        <div class="container">
            <h2>Users</h2>
            <a href="UserController?action=new">Add New User</a>

            <table>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>User Type</th>
                </tr>

                <%
                    List<User> users = (List<User>) request.getAttribute("users");
                    if (users != null) {
                        for (User user : users) {
                %>
                <tr>
                    <td><%= user.getUserId()%></td>
                    <td><%= user.getName()%></td>
                    <td><%= user.getEmail()%></td>
                    <td><%= user.getUserType()%></td>
                </tr>
                <%
                    }
                } else {
                %>
                <tr><td colspan="4">No users found.</td></tr>
                <%
                    }
                %>
            </table>
        </div>
        <a href="dashboard.jsp" class="btn" id="back">Go to Homepage</a>
    </body>
</html>
