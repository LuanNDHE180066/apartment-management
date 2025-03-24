<%-- 
    Document   : viewallexpenditure
    Created on : Feb 24, 2025, 4:52:47 PM
    Author     : PC
--%>
<!DOCTYPE html>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
    <head>
        <!-- basic -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- mobile metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- site metas -->
        <title>Quản lý khoản chi</title>
        <link rel="icon" href="images/fevicon.png" type="image/png" />
        <!-- bootstrap css -->
        <link rel="stylesheet" href="css/bootstrap.min.css" />
        <!-- custom css -->
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
             .table td{
                 text-align: center;
                 color: black;
                 font-weight: 300;
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

                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="white_shd full margin_bottom_30">

                                        <div class="heading1 margin_0">
                                            <h2 style="margin-left: 30px">Expenditure Update History Information</h2>
                                        </div>

                                        <div class="table_section padding_infor_info">
                                            <div class="table-responsive-sm">
                                                <table class="table w-100" id="table-infor">
                                                    <thead>
                                                        <tr>
                                                            <th>ID</th>
                                                            <th>Loại phí</th>
                                                            <th>Tổng chi</th>
                                                            <th>Ngày duyệt</th>
                                                            
                                                            <th>Loại phí</th>
                                                            <th>Công ty</th>
                                                            <th>Kế toán</th>
                                                            <th>Giám đốc</th>
                                                            <th>Người thay đổi</th>
                                                            <th>Ngày thay đổi</th>
                                                            <th>Option</th>
                                                            <!--       <th>Note</th> -->
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                    <h3>${requestScope.message}</h3>
                                                    <c:forEach items="${requestScope.listExpenditure}" var="expenditure">
                                                        <tr>
                                                            <td style="text-align: center">${expenditure.id}</td>
                                                            <td style="text-align: center">${expenditure.title}</td>
                                                            <td>
                                                                <%--%>         <fmt:setLocale value="vi_VN"/> <%-- Thi?t l?p locale v? Vi?t Nam --%>
                                                                <%--        <fmt:formatNumber value="${expenditure.totalPrice}" type="currency" currencyCode="VND" maxFractionDigits="0"/> --%>
                                                                ${expenditure.totalPrice}
                                                            </td>
                                                            <td style="text-align: center">${expenditure.approveddate}</td>
                                                            
                                                            <td style="text-align: center">${expenditure.category.categoryName}</td>
                                                            <td style="text-align: center">${expenditure.company.name}</td>

                                                            <td style="text-align: center">${expenditure.chiefAccountantId.name}</td>
                                                            <td style="text-align: center">${expenditure.currentAdmin.name}</td>
                                                            <td style="text-align: center">${expenditure.modifiedBy.name}</td>
                                                            <td style="text-align: center">${expenditure.modifiedDate}</td>
                                                            <td>
                                                                <c:if test="${expenditure.chiefAccountantApproveStatus == 1 && expenditure.currentAdminApproveStatus == 1}">
                                                                    <span style="color: green">Approved</span>
                                                                </c:if>
                                                                <c:if test="${expenditure.chiefAccountantApproveStatus == -1 || expenditure.currentAdminApproveStatus == -1}">
                                                                    <span style="color: red">Rejected</span>
                                                                </c:if>
                                                                <c:if test="${expenditure.chiefAccountantApproveStatus == 0 || expenditure.currentAdminApproveStatus == 0}">
                                                                    <span style="color:#FF9900 ">Pending</span>
                                                                </c:if>
                                                                <a style="text-align: center" class="approval-link" href="view-pending-expenditure-detail?id=${expenditure.heid}&type=1">
                                                                    <i class="fa-solid fa-eye"></i> 
                                                                </a>
                                                            </td>
                                                    <!--    <td>${expenditure.note}</td>  -->
                                                        </tr>
                                                    </c:forEach>
                                                    </tbody>
                                                </table>
                                                    <a href="expenditure-report" style="color: white" class="btn btn-primary">Quay lại</a>
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