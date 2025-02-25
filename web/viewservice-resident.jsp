<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
    <head>
        <!-- basic -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- mobile metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="viewport" content="initial-scale=1, maximum-scale=1">
        <!-- site metas -->
        <title>Apartment management</title>        
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
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

        <style>
            /* CSS để làm cho đường kẻ của bảng xám mờ, căn giữa tên cột và màu nền của tiêu đề cột */
            .table th, .table td {
                border: 1px solid rgba(0, 0, 0, 0.2); /* Đặt đường kẻ màu xám mờ */
            }
            .table {
                border-collapse: collapse; /* Đảm bảo không có khoảng cách giữa các đường kẻ */
            }
            .table th {
                text-align: center; /* Căn giữa tên cột */
                background-color: #6B90DA; /* Màu nền cho tiêu đề cột */
                color: black; /* Màu chữ trắng để nổi bật trên nền xanh */
            }
        </style>
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
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
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="white_shd full margin_bottom_30">
                                        <div class="full graph_head d-flex justify-content-between align-items-center">
                                            <div class="heading1 margin_0">
                                                <h2>Service Register</h2>
                                            </div>
                                        </div>
                                        <div class="table_section padding_infor_info">
                                            <div style="margin-left: 40px; margin-bottom: 30px; display: flex; align-items: center; gap: 10px;">
                                                <form action="view-service-resident" method="post" style="display: flex; align-items: center; gap: 10px;">
                                                    <label for="idapartment" style="font-weight: bold;">Chung cư số:</label>
                                                    <select onchange="this.form.submit()" id="idapartment" name="idapartment" 
                                                            style="padding: 5px; border: 1px solid #ccc; border-radius: 4px; font-size: 14px; width: 200px;">
                                                        <c:forEach items="${requestScope.owned}" var="item">
                                                            <option ${requestScope.aid==item.id? 'selected':'' } value="${item.id}">${item.id}</option>
                                                        </c:forEach>
                                                    </select>
                                                </form>
                                                <button type="button" style="padding: 8px 16px; background-color: #007bff; color: white; border: none;
                                                        border-radius: 4px; font-size: 14px; cursor: pointer;">
                                                    <a style="color: black" href="#">Add service</a>
                                                </button>
                                            </div>


                                            <div class="table-responsive-sm">
                                                <table class="table w-100">
                                                    <thead>
                                                        <tr>
                                                            <th style="width: 40%;">Name of service</th>
                                                            <th style="width: 15%;">Quantity</th>
                                                            <th style="width: 15%;">Unit Price</th>
                                                            <th style="width: 25%;">Supplier</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach items="${requestScope.usingServices}" var="item">
                                                            <tr>
                                                                <td>${item.service.name}</td>
                                                                <td>${item.quantity}</td> 
                                                                <td>${item.service.unitPrice}</td>
                                                                <td>${item.service.company.name}</td>
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
                    <!-- footer -->

                </div>
                <!-- end dashboard inner -->
            </div>
        </div>
        <!-- jQuery -->
        <script src="js/jquery.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/custom.js"></script>
    </body>
</html>