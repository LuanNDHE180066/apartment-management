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
                                    <div class="page_title">
                                        <h2>Feedback Information</h2>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <!-- Feedback Information Table -->
                                <div class="col-md-12">
                                    <div class="white_shd full margin_bottom_30">
                                        <div class="full graph_head d-flex justify-content-between align-items-center">
                                            <div class="heading1 margin_0">
                                                <h2>Feedback Table</h2>
                                            </div>
                                            <button class="btn btn-primary" onclick="location.href = 'sendfeedback'">Send New Feedback</button>
                                        </div>
                                        <div class="table_section padding_infor_info">
                                            <div style="margin-left: 40px; margin-bottom: 30px;">
                                                <form action="filterfeedback" method="get">
                                                    <div class="row align-items-center">
                                                        <div class="col-md-2">
                                                            <input type="date" class="form-control" name="from" placeholder="From" value="${sessionScope.from}">
                                                        </div>
                                                        <div class="col-md-2">
                                                            <input type="date" class="form-control" name="to" placeholder="To" value="${sessionScope.to}">
                                                        </div>
                                                        <div class="col-md-2">
                                                            <select class="form-control" name="typeRequest">
                                                                <c:choose>
                                                                    <c:when test="${sessionScope.selectedType == null}">
                                                                        <option value="" disabled selected> --Select Type-- </option>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <option value="${sessionScope.selectedType.id}" selected>${sessionScope.selectedType.name}</option>
                                                                        <option value="" > --Select Type-- </option>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                                <c:forEach items="${requestScope.listTypeRquest}" var="t">
                                                                    <option value="${t.id}">${t.name}</option>
                                                                </c:forEach>
                                                                <!-- Add more options as needed -->
                                                            </select>
                                                        </div>
                                                        <div class="col-md-4 d-flex">
                                                            <button type="submit" class="btn btn-primary" style="margin-right: 5px;">Search</button>
                                                        </div>
                                                    </div>

                                                </form>

                                            </div>
                                            <div class="table-responsive-sm">
                                                <table class="table w-100">
                                                    <thead>
                                                        <tr>
                                                            <th style="width: 5%;">ID</th>
                                                            <th style="width: 15%;">Người tạo đơn</th>
                                                            <th style="width: 45%;">Detail</th>
                                                            <th style="width: 15%;">Ngày tạo đơn</th>
                                                            <th style="width: 20%;">Tên dịch vụ</th>
                                                            <th style="width: 5%;"></th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach items="${sessionScope.listFeedbackU}" var="feedback">
                                                            <tr>
                                                                <td>${feedback.id}</td>
                                                                <td>${feedback.resident.name}</td>
                                                                <td>${feedback.detail}</td>
                                                                <td>${feedback.date}</td>
                                                                <td>${feedback.requestType.name}</td>
                                                                <td><a href="deletefeedback?id=${feedback.id}" onclick="return confirm('Are you sure to delete this feedback?')""><i class="material-icons" title="Delete">&#xE872;</i></a></td>

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