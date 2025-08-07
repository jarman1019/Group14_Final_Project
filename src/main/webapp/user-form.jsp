<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Form</title>
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
            max-width: 500px;
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            font-weight: bold;
            color: #555;
            display: block;
            margin-bottom: 5px;
        }
        .form-group input, .form-group select {
            width: 100%;
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ddd;
            border-radius: 5px;
            box-sizing: border-box;
        }
        .form-group input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
        }
        .form-group input[type="submit"]:hover {
            background-color: #45a049;
        }
        .back-link {
            display: block;
            text-align: center;
            margin-top: 20px;
            text-decoration: none;
            color: #007BFF;
        }
        .back-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

    <div class="container">
        <h2>${user != null ? "Edit User" : "New User"}</h2>

        <form action="UserController" method="get">
            <input type="hidden" name="action" value="${user != null ? 'update' : 'insert'}"/>
            <c:if test="${user != null}">
                <input type="hidden" name="userId" value="${user.userId}"/>
            </c:if>

            <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" value="${user != null ? user.name : ''}" required/>
            </div>

            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" value="${user != null ? user.email : ''}" required/>
            </div>

            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" value="${user != null ? user.password : ''}" required/>
            </div>

            <div class="form-group">
                <label for="userType">User Type:</label>
                <select name="userType" id="userType">
                    <option value="Manager" ${user.userType == 'Manager' ? 'selected' : ''}>Manager</option>
                    <option value="Operator" ${user.userType == 'Operator' ? 'selected' : ''}>Operator</option>
                </select>
            </div>

            <div class="form-group">
                <input type="submit" value="Save"/>
            </div>
        </form>
                <a href="login.jsp" class="back-link">Login</a>
        <a href="UserController" class="back-link">Back to list</a>
    </div>

</body>
</html>
