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
                                        <h2>Tables</h2>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <!-- table section -->
                                <div class="col-md-12">
                                    <div class="white_shd full margin_bottom_30">
                                        <div class="full graph_head">
                                            <div class="heading1 margin_0">
                                                <h2>Resident Information</h2>
                                            </div>
                                        </div>
                                        <div style="margin-left: 40px;">
                                            <form action="view-resident" method="GET">
                                                <div class="row align-items-center">
                                                    <div class="col-md-2">
                                                        <input type="text" value="${param.searchName}" class="form-control" name="searchName" placeholder="Search by Name">
                                                    </div>
                                                    <div class="col-md-2">
                                                        <select class="form-control" name="filterStatus">
                                                            <option value="">Filter by Status</option>
                                                            <option value="1" ${param.filterStatus == '1' ? 'selected' : ''}>Active</option>
                                                            <option value="0" ${param.filterStatus == '0' ? 'selected' : ''}>Inactive</option>
                                                        </select>
                                                    </div>
                                                    <div class="col-md-4 d-flex">
                                                        <button type="submit" class="btn btn-primary" style="margin-right: 5px;">Filter</button>
                                                        <a href="addnewresident.jsp" class="btn btn-primary">Add new Resident</a>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>

                                        <div class="table_section padding_infor_info">
                                            <div class="table-responsive-sm">
                                                <table class="table w-100" id="table-infor">
                                                    <thead>
                                                        <tr>
                                                            <th>ID</th>
                                                            <th>Name</th>                                                       
                                                            <th>Phone</th>
                                                            <th>Email</th>                                               
                                                            <th>Status</th> 
                                                            <th>View Detail</th>

                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <h3>${requestScope.message}</h3>
                                                        <c:forEach items="${requestScope.listResident}" var="resident">
                                                            <tr>
                                                                <td>${resident.pId}</td>
                                                                <td>${resident.name}</td>
                                                                <td>${resident.phone}</td>
                                                                <td>${resident.email}</td>                                               
                                                                <td style="color: ${resident.status == '1'?'green':'red'}">${resident.status == '1'?'Active': 'Inactive'}</td>
                                                                <td style="text-align: center;">
                                                                    <a href="#" data-toggle="modal" data-target="#residentDetail${resident.pId}">
                                                                        <i class="fa fa-user" aria-hidden="true"></i>
                                                                    </a>
                                                                </td> 
                                                                
                                                            </tr>
                                                            <!-- Modal for resident details -->
                                                        <div id="residentDetail${resident.pId}" class="modal fade">
                                                            <div class="modal-dialog" style="max-width: 60%">
                                                                <div class="modal-content">
                                                                    <div class="modal-header">
                                                                        <h3>Resident Information</h3>
                                                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                                    </div>
                                                                    <div class="modal-body" style="display: flex;">
                                                                        <div style="width: 50%; text-align: center;">
                                                                            <img style="width: 70%; border-radius: 50%; margin-top: 15%" src="images/logo/person.jpg" alt="Resident Image"/>
                                                                        </div>
                                                                        <div style="width: 50%">
                                                                            <p><strong>ID:</strong> ${resident.pId}</p>
                                                                            <p><strong>Name:</strong> ${resident.name}</p>
                                                                            <p><strong>Bod:</strong> ${resident.bod}</p>
                                                                            <p><strong>Email:</strong> ${resident.email}</p>
                                                                            <p><strong>Phone:</strong> ${resident.phone}</p>
                                                                            <p><strong>Address:</strong> ${resident.address}</p>
                                                                            <p><strong>CCCD:</strong> ${resident.cccd}</p>
                                                                            <p><strong>Gender:</strong> ${resident.gender}</p>
                                                                            <p><strong>Status:</strong> ${resident.status == 1 ? 'Active' : 'Inactive'}</p>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <form method="get" action="view-resident" style="display: flex; align-items: center; gap: 10px;">
                            <label for="page" style="font-size: 14px; font-weight: bold;">Page:</label>
                            <select id="page" name="page" onchange="this.form.submit()" 
                                    style="padding: 6px 12px; font-size: 14px; border: 1px solid #ddd; border-radius: 4px; cursor: pointer;">
                                <c:forEach begin="1" end="${requestScope.totalPage}" var="page">
                                    <option value="${page}" <c:if test="${page == requestScope.currentPage}">selected</c:if>>
                                        ${page}
                                    </option>
                                </c:forEach>
                            </select>
                        </form>

                        <!-- footer -->
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