<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Route</title>
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

        h1 {
            color: #333;
            text-align: center;
        }

        .form-container {
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 400px;
            margin: auto;
        }

        .form-container label {
            font-size: 14px;
            color: #555;
            margin-bottom: 8px;
            display: block;
        }

        .form-container input, .form-container textarea {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 14px;
            box-sizing: border-box;
        }

        .form-container button {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            font-size: 16px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .form-container button:hover {
            background-color: #45a049;
        }

        .form-container a {
            display: block;
            text-align: center;
            color: #4CAF50;
            text-decoration: none;
            margin-top: 10px;
        }

        .form-container a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h1>Add New Route</h1>

        <form action="RouteController" method="post">
            <input type="hidden" name="action" value="add"> <!-- Action for adding a new route -->

            <label for="routeName">Route Name:</label>
            <input type="text" id="routeName" name="routeName" required>

            <label for="startPoint">Start Point:</label>
            <input type="text" id="startPoint" name="startPoint" required>

            <label for="endPoint">End Point:</label>
            <input type="text" id="endPoint" name="endPoint" required>

            <!-- Optional: Add a description field if needed -->
            <label for="description">Description:</label>
            <textarea id="description" name="description"></textarea>

            <button type="submit">Add Route</button>
        </form>

        <a href="RouteController?action=list">Cancel</a> <!-- Link to go back to the list of routes -->
    </div>
</body>
</html>
