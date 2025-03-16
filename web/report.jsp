<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard Thống Kê Chi Tiêu</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #e9ecef;
        }

        h2 {
            color: #343a40;
            margin-bottom: 20px;
        }

        .dashboard {
            display: flex;
            flex-direction: column;
            gap: 20px;
        }

        .dashboard-item {
            padding: 20px;
            border-radius: 8px;
            background-color: #ffffff;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            transition: transform 0.2s;
        }

        .dashboard-item:hover {
            transform: translateY(-5px);
        }

        .dashboard-item h4 {
            margin: 0;
            color: #007bff;
        }

        table {
            width: 100%;
            margin-top: 20px;
            border-collapse: collapse;
            background-color: #ffffff;
        }

        table, th, td {
            border: 1px solid #dee2e6;
        }

        th, td {
            padding: 15px;
            text-align: left;
        }

        th {
            background-color: #007bff;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f8f9fa;
        }

        .footer {
            margin-top: 20px;
            text-align: center;
            color: #6c757d;
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Dashboard Thống Kê Chi Tiêu</h2>

    <div class="dashboard">
        <div class="dashboard-item">
            <h4>Tổng Chi Tiêu: 
                <fmt:formatNumber value="${requestScope.totalExpenditure}" type="currency" currencyCode="VND" maxFractionDigits="0"/>
            </h4>
        </div>
        <div class="dashboard-item">
            <h4>Chi Tiêu Cao Nhất: 
                <fmt:formatNumber value="${requestScope.highestExpenditure.amount}" type="currency" currencyCode="VND" maxFractionDigits="0"/>
                (Tên: ${requestScope.highestExpenditure.name})
            </h4>
        </div>
    </div>

    <h3>Chi Tiết Chi Tiêu</h3>
    <table>
        <thead>
            <tr>
                <th>Tên Chi</th>
                <th>Ngày Chi</th>
                <th>Số Tiền</th>
                <th>Mô Tả</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="expenditure" items="${requestScope.expenditures}">
                <tr>
                    <td>${expenditure.name}</td>
                    <td>
                        <fmt:formatDate value="${expenditure.date}" pattern="dd/MM/yyyy"/>
                    </td>
                    <td>
                        <fmt:formatNumber value="${expenditure.amount}" type="currency" currencyCode="VND" maxFractionDigits="0"/>
                    </td>
                    <td>${expenditure.description}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <div class="footer">
        <p>Copyright © 2025 Designed by Your Company. All rights reserved.</p>
    </div>
</div>

<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>