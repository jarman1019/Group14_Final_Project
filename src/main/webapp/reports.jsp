<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Reporting & Analytics</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f4f8;
            padding: 30px;
        }
        h1 {
            color: #2c3e50;
        }
        .dashboard-section {
            background-color: #fff;
            border-radius: 8px;
            padding: 20px;
            margin-bottom: 25px;
            box-shadow: 0 2px 6px rgba(0,0,0,0.1);
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 15px;
        }
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #3498db;
            color: white;
        }
    </style>
</head>
<body>

<h1>Reporting & Analytics Dashboard</h1>

<div class="dashboard-section">
    <h2>Transit Maintenance Dashboard</h2>
    <table>
        <tr>
            <th>Vehicle ID</th>
            <th>Last Maintenance</th>
            <th>Status</th>
        </tr>
        <tr>
            <td>BUS-102</td>
            <td>2025-07-10</td>
            <td>Completed</td>
        </tr>
        <tr>
            <td>TRAIN-23</td>
            <td>2025-08-01</td>
            <td>Scheduled</td>
        </tr>
    </table>
</div>

<div class="dashboard-section">
    <h2>Operator Performance Dashboard</h2>
    <table>
        <tr>
            <th>Operator ID</th>
            <th>On-time Arrival Rate</th>
            <th>Efficiency Score</th>
        </tr>
        <tr>
            <td>1</td>
            <td>92%</td>
            <td>8.7</td>
        </tr>
        <tr>
            <td>2</td>
            <td>85%</td>
            <td>7.9</td>
        </tr>
    </table>
</div>

<div class="dashboard-section">
    <h2>Cost Reports</h2>
    <table>
        <tr>
            <th>Month</th>
            <th>Fuel/Energy Cost ($)</th>
            <th>Maintenance Cost ($)</th>
        </tr>
        <tr>
            <td>July 2025</td>
            <td>12,300</td>
            <td>4,800</td>
        </tr>
        <tr>
            <td>August 2025</td>
            <td>13,100</td>
            <td>5,200</td>
        </tr>
    </table>
</div>

</body>
</html>
