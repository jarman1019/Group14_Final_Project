<%@ page import="java.util.List" %>
<%@ page import="model.BreakLog" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Break Logs</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f7f9fc;
            padding: 20px;
        }

        h2 {
            color: #2c3e50;
            border-bottom: 2px solid #3498db;
            padding-bottom: 5px;
        }

        table {
            width: 80%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            padding: 12px 15px;
            border: 1px solid #ddd;
            text-align: center;
        }

        th {
            background-color: #3498db;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        a {
            display: inline-block;
            margin-top: 20px;
            text-decoration: none;
            padding: 10px 15px;
            background-color: #3498db;
            color: white;
            border-radius: 5px;
        }

        a:hover {
            background-color: #2980b9;
        }
    </style>
</head>
<body>
    <h2>All Operator Break Logs</h2>

    <c:if test="${not empty breakLogs}">
        <table>
            <tr>
                <th>Operator ID</th>
                <th>Reason</th>
                <th>Timestamp</th>
            </tr>
            <%
                List<BreakLog> breakLogs = (List<BreakLog>) request.getAttribute("breakLogs");
                if (breakLogs != null) {
                    for (BreakLog log : breakLogs) {
            %>
            <tr>
                <td><%= log.getOperatorId() %></td>
                <td><%= log.getReason() %></td>
                <td><%= log.getTimestamp() %></td>
            </tr>
            <%
                    }
                }
            %>
        </table>
    </c:if>

    <a href="breaklog">Log New Break</a>
    <a href="dashboard.jsp" class="btn" id="back">Go to Homepage</a>
</body>
</html>
