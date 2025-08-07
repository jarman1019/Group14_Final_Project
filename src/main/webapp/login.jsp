<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="bg-light">

<div class="container mt-5">
    <h2 class="text-center mb-4">User Login</h2>

    <!-- Error Message Display -->
    <%
        String error = (String) request.getAttribute("error");
        if (error != null) {
    %>
        <div class="alert alert-danger text-center w-50 mx-auto">
            <%= error %>
        </div>
    <%
        }
    %>

    <form action="UserController?action=login" method="post" class="w-50 mx-auto border p-4 rounded bg-white shadow">
        <div class="mb-3">
            <label for="email" class="form-label">Email address:</label>
            <input type="email" class="form-control" id="email" name="email" required/>
        </div>

        <div class="mb-3">
            <label for="password" class="form-label">Password:</label>
            <input type="password" class="form-control" id="password" name="password" required/>
        </div>

        <button type="submit" class="btn btn-primary w-100">Login</button>
    </form>

    <p class="text-center mt-3">
        Don't have an account? <a href="UserController?action=new">Register here</a>
    </p>
</div>

</body>
</html>
