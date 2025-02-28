<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://fonts.googleapis.com/css2?family=Noto+Sans&display=swap" rel="stylesheet">
        <title>Contracts</title>
        <link rel="icon" href="images/fevicon.png" type="image/png" />
        <link rel="stylesheet" href="css/bootstrap.min.css" />
        <link rel="stylesheet" href="style.css" />
        <link rel="stylesheet" href="css/responsive.css" />
        <link rel="stylesheet" href="css/custom.css" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <style>
            body {
                /* Style body n?u c?n */
            }
            .contract-list {
                padding-left: 20px; /* Kho?ng cách bên trái */
            }
            .contract-list li {
                padding: 10px; /* Padding cho t?ng m?c */
                font-size: 18px; /* Kích th??c ch? cho t?ng m?c */
            }
            .contract-list li a {
                text-decoration: none; /* B? g?ch chân */
                color: #007bff; /* Màu liên k?t */
            }
            .contract-list li a:hover {
                color: #0056b3; /* Màu khi hover */
            }
            .graph_head {
                margin-bottom: 20px; /* Gi?m kho?ng cách d??i tiêu ?? */
            }
        </style>
    </head>
    <body class="inner_page contract_page">
        <div class="full_container">
            <div class="inner_container">
                <%@ include file="sidebar.jsp" %>
                <div id="content">
                    <%@ include file="topbar.jsp" %>
                    <div class="midde_cont">
                        <div class="container-fluid">
                            <div class="row column_title">
                                <div class="col-md-12">
                                    <div class="page_title">
                                        <h2>Contract List</h2>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="white_shd full margin_bottom_30">
                                        <div class="full graph_head">
                                            <div class="heading1 margin_0">
                                                <h2>Recently Contracts</h2>
                                            </div>
                                            <br> <hr><!-- comment -->
                                            <div>
                                                <form action="view-all-contract" method="get">
                                                    <div class="row align-items-center">
                                                        <div class="col-md-8">
                                                            <div class="row align-items-center">
                                                                <div class="col-md-3">
                                                                    <input type="text" class="form-control" value="${param.title}" name="title" placeholder="Enter title">
                                                                </div>
                                                                <div class="col-md-3">
                                                                    <input type="date" class="form-control" value="${param.startDate}" name="startDate" placeholder="From">
                                                                </div>
                                                                <div class="col-md-3">
                                                                    <input type="date" class="form-control" value="${param.endDate}" name="endDate" placeholder="To">
                                                                </div>
                                                                <div class="col-md-3 d-flex">
                                                                    <button type="submit" class="btn btn-primary" style="margin-right: 5px;">Filter</button>
                                                                    <c:if test="${sessionScope.account.roleId == 2}"> <a href="add-contract" class="btn btn-primary">Add Contract</a></c:if>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                    </form>
                                                </div>
                                            </div>

                                            <div class="contract_section">
                                                <ul class="contract-list">
                                                    <h3>${requestScope.message}</h3>
                                                <c:forEach var="c" items="${sessionScope.listContract}">
                                                    <li>-${c.startDate}<a href="contract-detail?id=${c.id}">: ${c.title}</a></li>
                                                        <c:if test="${sessionScope.account.roleId == 1}">
                                                        <a href="delete-contract?id=${c.id}" onclick="return confirm('Are you sure to delete this contract?')">
                                                            <i class="material-icons" title="Delete">&#xE872;</i>
                                                        </a>
                                                    </c:if>
                                                </c:forEach>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <form method="get" action="view-all-contract" style="display: flex; align-items: center; gap: 10px;">
                        <label for="page" style="font-size: 14px; font-weight: bold;">Page:</label>
                        <input type="text" name="clientName" value="" hidden="">
                        <input type="date" name="startDate" value="" hidden="">
                        <input type="date" name="endDate" value="" hidden="">
                        <select id="page" name="page" onchange="this.form.submit()" 
                                style="padding: 6px 12px; font-size: 14px; border: 1px solid #ddd; border-radius: 4px; cursor: pointer;">
                            <c:forEach begin="1" end="${requestScope.totalPage}" var="page">
                                <option value="${page}" <c:if test="${page == requestScope.currentPage}">selected</c:if>>
                                    ${page}
                                </option>
                            </c:forEach>
                        </select>
                    </form>
                    <div class="container-fluid">
                        <div class="footer">
                            <p>Copyright © 2025 Designed by Your Company. All rights reserved.</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="js/jquery.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/custom.js"></script>
    </body>
</html>