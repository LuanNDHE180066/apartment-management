<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Apartment Management</title>
        <link rel="icon" href="images/fevicon.png" type="image/png" />
        <link rel="stylesheet" href="css/bootstrap.min.css" />
        <link rel="stylesheet" href="style.css" />
        <link rel="stylesheet" href="css/responsive.css" />
        <link rel="stylesheet" href="css/colors.css" />
        <link rel="stylesheet" href="css/bootstrap-select.css" />
        <link rel="stylesheet" href="css/perfect-scrollbar.css" />
        <link rel="stylesheet" href="css/custom.css" />
        <link rel="stylesheet" href="js/semantic.min.css" />
        <link rel="stylesheet" href="css/jquery.fancybox.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css"/>
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <style>
            .pagination {
                margin-top: 20px;
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
            .pagination a:hover, .pagination a.active {
                background-color: #007bff;
                color: white;
            }
            #table-infor th, #table-infor td {
                text-align: center;
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
                                        <h2>Quản lý Chức Vụ</h2>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="white_shd full margin_bottom_30">
                                        <div class="full graph_head">
                                            <div class="heading1 margin_0">
                                                <h2>Các Chức Vụ Trong Hệ Thống</h2>
                                            </div>
                                        </div>
                                        <div class="table_section padding_infor_info">
                                            <div class="table-responsive-sm">
                                                <table class="table w-100" id="table-infor">
                                                    <thead>
                                                        <tr>
                                                            <th style="background-color: #6B90DA; color: black; width: 10%">ID</th>
                                                            <th style="background-color: #6B90DA; color: black;width: 20%">Tên Chức Vụ</th>
                                                            <th style="background-color: #6B90DA; color: black">Mô Tả Quyền Hạn</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach items="${requestScope.roles}" var="item">
                                                            <tr>
                                                                <td style="text-align: left;">${item.id}</td>
                                                                <td style="text-align: left;">${item.name}</td>
                                                                <td style="text-align: left;">${item.description}</td>
                                                            </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="white_shd full margin_bottom_30">
                                        <div class="full graph_head">
                                            <div class="heading1 margin_0">
                                                <h2>Thêm Chức Vụ Mới</h2>
                                            </div>
                                        </div>
                                        <div class="padding_infor_info">
                                            <form action="add-role" method="get">
                                                <div class="form-group">
                                                    <label for="name">Tên Chức Vụ</label>
                                                    <input type="text" class="form-control" id="name" name="name" required>
                                                </div>
                                                <div class="form-group">
                                                    <label for="des">Mô Tả Chức Vụ</label>
                                                    <textarea class="form-control" id="des" name="des" rows="4" required></textarea>
                                                </div>
                                                <button type="submit" class="btn btn-primary">Thêm</button>
                                                <span style="color: green">${requestScope.msg}</span>
                                                <span style="color: red">${requestScope.error}</span>
                                            </form>
                                        </div>
                                    </div>
                                </div>
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
