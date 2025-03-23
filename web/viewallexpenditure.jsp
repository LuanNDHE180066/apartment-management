
<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@page import="dao.ApartmentDAO" %>
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
        <!-- site css -->
        <link rel="stylesheet" href="style.css" />
        <!-- responsive css -->
        <link rel="stylesheet" href="css/responsive.css" />
        <!-- color css -->
        <link rel="stylesheet" href="css/colors.css" />
        <!-- select bootstrap -->
        <link rel="stylesheet" href="css/bootstrap-select.css" />
        <!-- scrollbar css -->
        <link rel="stylesheet" href="css/perfect-scrollbar.css" />
        <!-- custom css -->
        <link rel="stylesheet" href="css/custom.css" />
        <!-- calendar file css -->
        <link rel="stylesheet" href="js/semantic.min.css" />
        <!-- fancy box js -->
        <link rel="stylesheet" href="css/jquery.fancybox.css" />
        <link rel="stylesheet" href="<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg==" crossorigin="anonymous" referrerpolicy="no-referrer" />"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <style>
                body {
            font-family: 'Roboto', sans-serif !important;
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
            .table th {
                text-align: center;
                background-color: #6B90DA;
                color: black;
            }
            .display-none{
                display: none;
            }

            .tbody td{
                text-align: center;
            }

            .display-none{
                display: none;
            }
             .table td{
                 text-align: left;
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
                                        <div class="full graph_head">
                                            <div class="heading1 margin_0">
                                                <h2>Khoản chi</h2>
                                            </div>
                                        </div>
                                        <div style="margin-left: 40px;">
                                            <form action="view-expenditure" method="get">
                                                <div class="row align-items-center">
                                                    <div class="col-md-8">
                                                        <div class="row align-items-center">
                                                            <div class="col-md-2">
                                                                <input type="text" class="form-control" value="${param.title}" name="title" placeholder="Nhập tiêu dề">
                                                            </div>
                                                            <div class="col-md-2">
                                                                <input type="date" class="form-control" value="${param.startDate}" name="startDate" placeholder="From">
                                                            </div>
                                                            <div class="col-md-2">
                                                                <input type="date" class="form-control" value="${param.endDate}" name="endDate" placeholder="To">
                                                            </div>
                                                            <div class="col-md-2.5">
                                                                <select class="form-control" name="category">
                                                                    <option value="">Select category</option>
                                                                    <c:forEach items="${requestScope.categorylist}" var="ca">
                                                                        <option ${param.category == ca.id? 'selected':''} value="${ca.id}">${ca.categoryName}</option>
                                                                    </c:forEach>
                                                                </select>
                                                            </div>
                                                            <div class="col-md-2 d-flex">
                                                                <button type="submit" class="btn btn-primary" style="margin-right: 5px;">Tìm</button>
                                                                <span class="btn btn-primary" style="display: inline-block; ${roleId != 3 ?'display: none':''}"><a style="color: white" href="add-expenditure">Thêm mới</a></span>
                                                                <span  class="btn btn-primary" style="display: inline-block; margin-left:10px ">
                                                                    <a style="color: white" href="view-pending-expenditure">Khoản chi chờ duyệt</a></span>
                                                                <span  class="btn btn-primary" style="display: inline-block; margin-left:10px ">
                                                                    <a style="color: white" href="view-expense-category">Các loại phí</a></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>

                                        <div class="table_section padding_infor_info">
                                            <div class="table-responsive-sm">
                                                <table class="table w-100" id="table-infor">
                                                    <thead>
                                                        <tr>
                                                            <th style="text-align: center">ID</th>
                                                            <th style="text-align: center">Tiêu đề</th>
                                                            <th style="text-align: center">Tổng phí</th>
                                                            <th style="text-align: center">Ngày duyệt</th>
                                                            <th style="text-align: center">Loại phí</th>
                                                            <th style="text-align: center">Công ty</th>
                                                            <th style="text-align: center">Người tạo</th>
                                                            <th style="text-align: center">Kế toán</th>
                                                            <th style="text-align: center">Giám đốc</th>
                                                            <th  style="text-align: center">Khác</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody class="tbody">
                                                    <h3>${requestScope.message}</h3>
                                                    <c:forEach items="${requestScope.listExpenditure}" var="expenditure">
                                                        <tr>
                                                            <td>${expenditure.id}</td>
                                                            <td>${expenditure.title}</td>
                                                            <td> <fmt:formatNumber value="${expenditure.totalPrice}" type="currency" currencyCode="VND" maxFractionDigits="2"/> </td>
                                                            <td>${expenditure.approveddate}</td>
                                                            <td>${expenditure.category.categoryName}</td>
                                                            <td>${expenditure.company.name}</td>
                                                            <td>${expenditure.createdStaff.name}</td>
                                                            <td>${expenditure.chiefAccountantId.name}</td>
                                                            <td>${expenditure.currentAdmin.name}</td>
                                                            <td>
                                                                <a class="${sessionScope.account.roleId != 3? 'display-none':''}" href="update-expenditure?id=${expenditure.id}"><i class="fa-solid fa-pen-to-square"></i></a>
                                                                <a href="view-expenditure-change-history?id=${expenditure.id}" 
                                                                   style="margin-left: 10px;"><i class="fa-solid fa-history"></i></a>

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
                    <form method="get" action="view-expenditure" style="display: flex; align-items: center; gap: 10px;">
                        <label for="page" style="font-size: 14px; font-weight: bold;">Page:</label>
                        <input type="text" name="title" value="${param.title}" hidden="">
                        <input type="text" name="category" value="${param.category}" hidden="">
                        <input type="date" name="startDate" value="${param.startDate}" hidden="">
                        <input type="date" name="endDate"  value="${param.endDate}" hidden="">
                        <select id="page" name="page" onchange="this.form.submit()" 
                                style="padding: 6px 12px; font-size: 14px; border: 1px solid #ddd; border-radius: 4px; cursor: pointer;">
                            <c:forEach begin="1" end="${requestScope.totalPage}" var="page">
                                <option value="${page}" <c:if test="${page == requestScope.currentPage}"> selected</c:if>>
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