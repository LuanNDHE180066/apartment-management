<!DOCTYPE html>

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
        <title>Apartment management</title>        <link rel="icon" href="images/fevicon.png" type="image/png" />
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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css"/>
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <style>
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
                                                <h2>Floor Information</h2>
                                            </div>
                                        </div>
                                        <div class="table_section padding_infor_info">
                                            <div class="table-responsive-sm">
                                                <div style="margin-bottom: 20px">
                                                    <div class="row align-items-center">
                                                        <div class="col-md-2">
                                                            <form action="view-floor-staff" method="Post">
                                                                <select class="form-control" name="numFloor" onchange="this.form.submit()">
                                                                    <option value="all">All</option>
                                                                    <c:forEach begin="1" end="${requestScope.nofloor}" var="num">
                                                                        <option value="${num}">${num}</option>
                                                                    </c:forEach>
                                                                </select>
                                                            </form>
                                                        </div>
                                                        <div class="col-md-2">
                                                            <form action="view-floor-staff" method="Post">
                                                                <select class="form-control" name="type"  onchange="this.form.submit()">
                                                                    <option value="all">All</option>
                                                                    <c:forEach items="${requestScope.types}" var="type">
                                                                        <option value="${type}">${type}</option>
                                                                    </c:forEach>
                                                                </select>
                                                            </form>

                                                        </div>
                                                    </div>
                                                </div>

                                                <table class="table w-100" id="table-infor">
                                                    <thead>
                                                        <tr>
                                                            <th style="background-color: #6B90DA;color: black">Number</th>
                                                            <th style="background-color: #6B90DA;color: black">Square</th>                                                       
                                                            <th style="background-color: #6B90DA;color: black">Usage</th>
                                                            <th style="background-color: #6B90DA;color: black; width: 40%">Detail</th>
                                                            <th style="background-color: #6B90DA;color: black">Number person</th>
                                                            <th style="background-color: #6B90DA;color: black">Using Room</th>
                                                            <th style="background-color: #6B90DA;color: black">Empty Room</th>

                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach items="${requestScope.floors}" var="floor">
                                                            <tr>
                                                                <td style="text-align: left;">${floor.number}</td>
                                                                <td style="text-align: left;">${floor.square}</td>
                                                                <td style="text-align: left;">${floor.usageType}</td>
                                                                <td style="text-align: left;">${floor.note}</td>
                                                                <td>${floor.noPerson}</td>
                                                                <td>${floor.noUsingRoom}</td>
                                                                <td>${floor.noNotUsingRoom}</td>
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
                        <div class="container-fluid">
                            <div class="footer">
                                <p>Copyright � 2018 Designed by html.design. All rights reserved.</p>
                            </div>
                        </div>
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