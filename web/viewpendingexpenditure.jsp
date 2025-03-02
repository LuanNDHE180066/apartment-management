<%-- 
    Document   : viewallexpenditure
    Created on : Feb 24, 2025, 4:52:47 PM
    Author     : PC
--%>
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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
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
            button {
                background: none;
                border: none;
                cursor: pointer;
                font-size: 24px;
                margin: 0 5px;
            }
            .approval-link {
                font-size: 20px;
                margin: 0 5px;
                color: inherit;
                text-decoration: none;
            }
            .table th {
                text-align: center;
                background-color: #6B90DA;
                color: black;
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

                                        <div class="heading1 margin_0">
                                            <h2>Pending Expenditure Information</h2>
                                        </div>

                                        <div class="table_section padding_infor_info">
                                            <div class="table-responsive-sm">
                                                <table class="table w-100 " id="table-infor">
                                                    <thead>
                                                        <tr>
                                                            <th>ID</th>
                                                            <th>Expense</th>
                                                            <th>Total fees</th>
                                                            <th>Approve Date</th>
                                                            <th>Payment Date</th>
                                                            <th>Category</th>
                                                            <th>Company</th>
                                                            <th>Staff Create</th>
                                                            <th>Chief Acountant</th>
                                                            <th>Responsible Person</th>
                                                            <th>Option</th>
                                                            <!--       <th>Note</th> -->
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                    <h3>${requestScope.message}</h3>
                                                    <c:forEach items="${requestScope.listExpenditure}" var="expenditure">
                                                        <tr>
                                                            <td>${expenditure.id}</td>
                                                            <td>${expenditure.title}</td>
                                                            <td>${expenditure.totalPrice}</td>
                                                            <td>${expenditure.approveddate}</td>
                                                            <td>${expenditure.paymentdate}</td>
                                                            <td>${expenditure.category.categoryName}</td>
                                                            <td>${expenditure.company.name}</td>
                                                            <td>${expenditure.createdStaff.name}</td>
                                                            <td>${expenditure.chiefAccountantId.name}</td>
                                                            <td>${expenditure.currentAdmin.name}</td>
                                                            <td>
                                                                <c:if test="${expenditure.currentAdmin.id == staffId && expenditure.currentAdminApproveStatus == 0}">
                                                                    <a class="approval-link" href="update-pending-expenditure?id=${expenditure.heid}&approve=1">
                                                                        <i class="fa-solid fa-check"></i>
                                                                    </a>
                                                                    <a class="approval-link" href="update-pending-expenditure?id=${expenditure.heid}&approve=-1">
                                                                        <i class="fa-solid fa-xmark"></i>
                                                                    </a>
                                                                </c:if>

                                                                <c:if test="${expenditure.currentAdmin.id == staffId && expenditure.currentAdminApproveStatus == 1}">
                                                                    <span style="color: green">Accept</span>
                                                                </c:if>
                                                                <c:if test="${expenditure.currentAdmin.id == staffId && expenditure.currentAdminApproveStatus == -1}">
                                                                    <span style="color: green">Accept</span>
                                                                </c:if>

                                                                <c:if test="${expenditure.chiefAccountantId.id == staffId && expenditure.chiefAccountantApproveStatus == 0}">
                                                                    <a class="approval-link" href="update-pending-expenditure?id=${expenditure.heid}&approve=1">
                                                                        <i class="fa-solid fa-check"></i>
                                                                    </a>
                                                                    <a class="approval-link" href="update-pending-expenditure?id=${expenditure.heid}&approve=-1">
                                                                        <i class="fa-solid fa-xmark"></i>
                                                                    </a>
                                                                </c:if>
                                                                <c:if test="${expenditure.chiefAccountantId.id == staffId && expenditure.chiefAccountantApproveStatus == 1}">
                                                                    <span style="color: green">Accept</span>
                                                                </c:if>
                                                                <c:if test="${expenditure.chiefAccountantId.id == staffId && expenditure.chiefAccountantApproveStatus == -1}">
                                                                    <span style="color: green">Accept</span>
                                                                </c:if>
                                                            </td>
                                                    <!--    <td>${expenditure.note}</td>  -->
                                                        </tr>
                                                    </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

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