<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Apartment Management</title>

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="css/bootstrap.min.css" />
        <link rel="stylesheet" href="style.css" />
        <link rel="stylesheet" href="css/custom.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css"/>
        <style>
            .graph_head h2 {
                text-align: center;
                font-size: 24px;
                font-weight: bold;
                margin-bottom: 20px;
            }

            form {
                padding: 15px 0;
            }

            .align-items-center .form-control {
                height: 38px;
                font-size: 14px;
            }

            .align-items-center .btn {
                height: 38px;
                font-size: 14px;
            }

            .table_section {
                padding: 20px;
            }

            .table th, .table td {
                text-align: center;
                vertical-align: middle;
                padding: 12px;
            }

            .table thead {
                background-color: #007bff;
                color: white;
            }

            .table tbody tr:nth-child(even) {
                background-color: #f9f9f9;
            }

            .table tbody tr:hover {
                background-color: #f1f1f1;
            }

            .btn-primary {
                background-color: #007bff;
                border-color: #007bff;
            }

            .btn-primary:hover {
                background-color: #0056b3;
                border-color: #004494;
            }

            .d-flex {
                display: flex;
                gap: 10px;
                align-items: center;
            }

            .col-md-4.d-flex {
                justify-content: flex-start;
            }
            td{
                color: black;
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
                            <div class="row column_title">
                                <div class="col-md-12">
                                    <div class="page_title">
                                        <h2>Tables</h2>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="white_shd full margin_bottom_30">
                                        <div class="full graph_head">
                                            <h2>Services Information</h2>
                                        </div>
                                        <div style="margin-left: 40px;">
                                            <form action="all-services" method="post">
                                                <div class="row align-items-center">
                                                    <div class="col-md-2">
                                                        <input id="searchInput" name="name" type="text" class="form-control" placeholder="Tìm kiếm theo tên ">
                                                    </div>
                                                    <div class="col-md-2">
                                                        <select class="form-control" name="status">
                                                            <option value="">Lọc theo trạng thái</option>
                                                            <option value="1" <c:if test="${sessionScope.status == 1}">selected</c:if>>Đang hoạt động</option>
                                                            <option value="0" <c:if test="${sessionScope.status == 0}">selected</c:if>>Đã đóng</option>
                                                            </select>
                                                        </div>
                                                        <div class="col-md-2">
                                                            <select class="form-control" name="category">
                                                                <option value="" >Thể loại</option>
                                                            <c:forEach items="${requestScope.listCategories}" var="cs">
                                                                <option value="${cs.id}" <c:if test="${sessionScope.category == cs.id}">selected</c:if>>${cs.name}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="col-md-2">
                                                        <select class="form-control" name="company">
                                                            <option value="" >Công ti cung cấp </option>
                                                            <c:forEach items="${requestScope.listCompanies}" var="c">
                                                                <option value="${c.id}" <c:if test="${sessionScope.company == c.id}">selected</c:if>>${c.name}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="col-md-4 d-flex">
                                                        <button type="submit" class="btn btn-primary me-2">Filter</button>
                                                        <a href="add-service-staff" class="btn btn-primary">Add New Service</a>
                                                    </div>
                                                </div>
                                            </form>

                                        </div>
                                        <div class="table_section padding_infor_info">
                                            <div class="table-responsive-sm">
                                                <table class="table w-100">
                                                    <thead>
                                                        <tr>
                                                            <th>Tên dịch vụ</th>
                                                            <th>Phí</th>
                                                            <th>Đơn vị</th>
                                                            <th>Mô tả</th>
                                                            <th>Loại dịch vụ</th>
                                                            <th>Cung cấp</th>
                                                            <th>Trạng thái</th>
                                                            <th style="width: 10%">Ngày tạo</th>
                                                            <th>Ngày đóng</th>
                                                            <th>Action</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach items="${requestScope.listServices}" var="list">
                                                            <tr>
                                                                <td style="text-align: left">${list.name}</td>
                                                                <td style="text-align: left">
                                                                    <fmt:formatNumber type="currency" currencyCode="VND" value="${list.unitPrice}" maxFractionDigits="0"/>
                                                                </td>
                                                                <td style="text-align: left">${list.unit}</td>
                                                                <td style="text-align: left">${list.description}</td>
                                                                <td style="text-align: left">${list.categoryService.name}</td>
                                                                <td style="text-align: left">${list.company.name}</td>
                                                                <td style="text-align: left">${list.status==1?'Active':'Inactive'}</td>
                                                                <td style="text-align: left">
                                                                    ${list.startDateFormat}
                                                                </td>
                                                                <td style="text-align: left">${list.endDateFormat}</td>
                                                                <td style="text-align: left"><a href="update-service-staff?id=${list.id}"><i class="fa-solid fa-pen-to-square"></i></a></td>
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
                </div>
            </div>
        </div>
        <!-- jQuery & Bootstrap -->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
