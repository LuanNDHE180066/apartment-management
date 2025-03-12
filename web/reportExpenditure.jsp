<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <!-- basic -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- mobile metas -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- site metas -->
    <title>Expenditure Report</title>
    <link rel="icon" href="images/fevicon.png" type="image/png" />
    <!-- bootstrap css -->
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <!-- custom css -->
    <style>
        .table th, .table td {
            border: 1px solid rgba(0, 0, 0, 0.2);
            text-align: center;
        }
        .table {
            border-collapse: collapse;
        }
        .table th {
            background-color: #6B90DA;
            color: black;
        }
        .table td {
            color: black;
            font-weight: 300;
        }
        /* Increased padding to create more space */
        .container-fluid {
            padding-top: 40px; /* Adjust this value as needed */
        }
    </style>
</head>
<body class="inner_page tables_page">
    <div class="full_container">
        <div class="inner_container">
            <%@include file="sidebar.jsp" %>
            <div id="content">
                <%@include file="topbar.jsp" %>
                <div class="midde_cont">
                    <div class="container-fluid">
                        <h2 style="margin-bottom: 20px">Expenditure Report</h2>

                        <!-- Filter Form -->
                        <form method="get" action="expenditure-report" class="mb-3">
                            <div class="row">
                                <div class="col-md-3">
                                    <label for="startDate">From Date:</label>
                                    <input type="date" id="startDate" name="startDate" class="form-control" value="${param.startDate}">
                                </div>
                                <div class="col-md-3">
                                    <label for="endDate">To Date:</label>
                                    <input type="date" id="endDate" name="endDate" class="form-control" value="${param.endDate}">
                                </div>
                                <div class="col-md-3">
                                    <label for="category">Category:</label>
                                    <select class="form-control" name="category">
                                        <option value="">Select category</option>
                                        <c:forEach items="${requestScope.categorylist}" var="ca">
                                            <option ${param.category == ca.id? 'selected':''} value="${ca.id}">${ca.categoryName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-md-3">
                                    <label>&nbsp;</label>
                                    <button type="submit" class="btn btn-primary btn-block">Filter</button>
                                </div>
                            </div>
                        </form>

                        <!-- Overview -->
                        <div class="row">
                            <div class="col-md-4">
                                <div class="card text-white bg-primary mb-3">
                                    <div class="card-header">Total Expenditure Count</div>
                                    <div class="card-body">
                                        <h5 class="card-title">${requestScope.totalExpenditure}</h5>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="card text-white bg-success mb-3">
                                    <div class="card-header">Total Amount Spent</div>
                                    <div class="card-body">
                                        <h5 class="card-title">${requestScope.totalAmount} VNĐ</h5>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <h3>Expenditure Details</h3>
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Expense</th>
                                    <th>Total Fees</th>
                                    <th>Approve Date</th>
                                    <th>Category</th>
                                    <th>Company</th>
                                    <th>Staff Create</th>
                                    <th>Chief Accountant</th>
                                    <th>Responsible Person</th>
                                    <th>Option</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${requestScope.listExpenditure}" var="expenditure">
                                    <tr>
                                        <td>${expenditure.id}</td>
                                        <td>${expenditure.title}</td>
                                        <td>${expenditure.totalPrice}</td>
                                        <td>${expenditure.approveddate}</td>
                                        <td>${expenditure.category.categoryName}</td>
                                        <td>${expenditure.company.name}</td>
                                        <td>${expenditure.createdStaff.name}</td>
                                        <td>${expenditure.chiefAccountantId.name}</td>
                                        <td>${expenditure.currentAdmin.name}</td>
                                        <td>
                                            <a class="${sessionScope.account.roleId != 3 ? 'display-none' : ''}" href="update-expenditure?id=${expenditure.id}"><i class="fa-solid fa-pen-to-square"></i></a>
                                            <a href="view-expenditure-change-history?id=${expenditure.id}" style="margin-left: 10px;"><i class="fa-solid fa-history"></i></a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>

                        <!-- Summary by Category -->
                        <h3>Summary by Category</h3>
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Category</th>
                                    <th>Number of Vouchers</th>
                                    <th>Total Amount</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${requestScope.categorySummary}" var="summary">
                                    <tr>
                                        <td>${summary.categoryName}</td>
                                        <td>${summary.voucherCount}</td>
                                        <td>${summary.totalAmount} VNĐ</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>

                        <!-- Export Report -->
                        <div class="text-right">
                            <button class="btn btn-secondary">Export to PDF</button>
                            <button class="btn btn-secondary">Export to Excel</button>
                            <button class="btn btn-secondary">Print Report</button>
                        </div>

                        <form method="get" action="expenditure-report" style="display: flex; align-items: center; gap: 10px;">
                            <label for="page" style="font-size: 14px; font-weight: bold;">Page:</label>
                            <input type="text" name="title" value="${param.title}" hidden="">
                            <input type="text" name="category" value="${param.category}" hidden="">
                            <input type="date" name="startDate" value="${param.startDate}" hidden="">
                            <input type="date" name="endDate" value="${param.endDate}" hidden="">
                            <select id="page" name="page" onchange="this.form.submit()" style="padding: 6px 12px; font-size: 14px; border: 1px solid #ddd; border-radius: 4px; cursor: pointer;">
                                <c:forEach begin="1" end="${requestScope.totalPage}" var="page">
                                    <option value="${page}" <c:if test="${page == requestScope.currentPage}"> selected</c:if>>${page}</option>
                                </c:forEach>
                            </select>
                        </form>

                        <div class="footer mt-4">
                            <p>Copyright © 2018 Designed by html.design. All rights reserved.</p>
                        </div>
                    </div>
                </div>
            </div>
            <!-- jQuery -->
            <script src="js/jquery.min.js"></script>
            <script src="js/bootstrap.min.js"></script>
        </body>
    </html>