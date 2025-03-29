<%-- 
    Document   : viewapartmentadmin
    Created on : Feb 15, 2025, 11:09:42 PM
    Author     : PC
--%>

<!DOCTYPE html>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">
    <head>
        <!-- basic -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- mobile metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- site metas -->
        <title>Thông tin căn hộ</title>
        <link rel="icon" href="images/fevicon.png" type="image/png" />
        <!-- bootstrap css -->
        <link rel="stylesheet" href="css/bootstrap.min.css" />
        <!-- custom css -->
        <style>
            .table th, .table td {
                border: 1px solid rgba(0, 0, 0, 0.2);
                text-align: center;
            }
            .pagination {
                margin-top: 20px;
                margin-left: 1250px;
                text-align: right;
            }
            .pagination a {
                display: inline-block;
                width: 30px;
                margin: 0 1px;
                padding: 5px 10px;
                border: 1px solid #007bff;
                color: #007bff;
                text-decoration: none;
                border-radius: 5px;
                text-align: center;
            }
            .pagination a:hover {
                background-color: #007bff;
                color: white;
            }
            .pagination a.active {
                font-weight: bold;
                background-color: #007bff;
                color: white;
            }
            #table-infor th, #table-infor td {
                text-align: center;
            }
            .option {
                margin-left: 1.5px;
            }
            .table th {
                text-align: center;
                background-color: #6B90DA;
                color: black;
            }
            .table td {
                text-align: center;
                color: black;
                font-weight: 300;
            }
        </style>
    </head>
    <body class="inner_page tables_page">
        <div class="full_container">
            <div class="inner_container">
                <!-- Sidebar  -->
                <%@include file="sidebar.jsp" %>
                <!-- end sidebar -->
                <!-- right content -->
                <div id="content">
                    <!-- topbar -->
                    <%@include file="topbar.jsp" %>
                    <!-- end topbar -->
                    <div class="midde_cont">
                        <div class="container-fluid">
                            <div class="row column_title">
                                <div class="col-md-12">
                                    <div class="page_title">
                                        <h2></h2>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <!-- table section -->
                                <div class="col-md-12">
                                    <div class="white_shd full margin_bottom_30">
                                        <div class="full graph_head">
                                            <div class="heading1 margin_0">
                                                <h2>Thông Tin Căn Hộ</h2>
                                            </div>
                                        </div>
                                        <div style="margin-left: 40px;">
                                            <form action="view-apartment-admin" method="GET">
                                                <div class="row align-items-center">
                                                    <div class="col-md-2">
                                                        <input type="text" class="form-control" name="searchName" value="${param.searchName}" placeholder="Tìm kiếm theo Tên Phòng">
                                                    </div>
                                                    <div class="col-md-2">
                                                        <input type="number" min="0" max="16" class="form-control" name="floor" value="${requestScope.floor != null ? requestScope.floor:''}" placeholder="Tìm kiếm theo Tầng">
                                                    </div>
                                                    <div class="col-md-2">
                                                        <select class="form-control" name="filterType">
                                                            <option value="">Lọc theo Loại</option>
                                                            <c:forEach items="${sessionScope.types}" var="o">
                                                                <option value="${o.id}" <c:if test="${requestScope.filterType == o.id}">selected</c:if>>${o.name}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
<!--                                                    <div class="col-md-2">
                                                        <select class="form-control" name="filterStatus">
                                                            <option value="">Lọc theo Trạng Thái</option>
                                                            <option value="1" ${requestScope.filterStatus == '1' ? 'selected' : ''}>Hoạt Động</option>
                                                            <option value="0" ${requestScope.filterStatus == '0' ? 'selected' : ''}>Không Hoạt Động</option>
                                                        </select>
                                                    </div>-->
                                                    
                                                    <div class="col-md-2 d-flex">
                                                        <button type="submit" class="btn btn-primary" style="margin-right: 5px;">Lọc</button>
                                                        <a href="view-pending-change-represent-preson" class="btn btn-primary">Thay đổi người đại diện</a>
                                                        <a style="margin-left: 5px" href="view-change-resident-request" class="btn btn-primary">Thay đổi Đang Chờ</a>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                        <div class="table_section padding_infor_info">
                                            <div class="table-responsive-sm">
                                                <table class="table w-100">
                                                    <thead>
                                                        <tr>
                                                            <th>ID Căn Hộ</th>
                                                            <th>Số Người</th>
                                                            <th>Tầng</th>
                                                            <th>Loại</th>
                                                            <th>Tùy Chọn</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach items="${requestScope.apartmentes}" var="o">
                                                            <tr>
                                                                <td>${o.id}</td>
                                                                <td>${o.numberOfPerson}</td>
                                                                <td>${o.floor.number}</td>
                                                                <td>${o.roomtype.name}</td>
                                                                <td>
                                                                    <a class="option" href="viewdetailapartment-admin?apartmentId=${o.id}"><i class="fa-solid fa-eye"></i></a>
                                                                    <a class="option" href="update-apartment?id=${o.id}"><i class="fa-solid fa-pen-to-square"></i></a>
                                                                </td>
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
                        <form method="get" action="view-apartment-admin" style="display: flex; align-items: center; gap: 10px;">
                            <label for="page" style="font-size: 14px; font-weight: bold;">Trang:</label>
                            <input type="text" name="filterStatus" value="${param.filterStatus}" hidden="">
                            <input type="text" name="filterType" value="${param.filterType}" hidden="">
                            <input type="text" name="searchName" value="${param.searchName}" hidden="">
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
                                <p>Bản quyền © 2018 Thiết kế bởi html.design. Tất cả quyền được bảo lưu.</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- jQuery -->
            <script src="js/jquery.min.js"></script>
            <script src="js/popper.min.js"></script>
            <script src="js/bootstrap.min.js"></script>
            <script src="js/custom.js"></script>
    </body>
</html>