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
        <title>Pluto - Responsive Bootstrap Admin Panel Templates</title>
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
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
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
                                                <h2>Staff Information</h2>
                                            </div>
                                        </div>
                                        <div style="margin-left: 40px;">
                                            <form action="view-all-staff" method="GET">
                                                <div class="row align-items-center">
                                                    <div class="col-md-2">
                                                        <input type="text" class="form-control" name="searchName" placeholder="Search by Name">
                                                    </div>
                                                    <div class="col-md-2">
                                                        <select class="form-control" name="filterStatus">
                                                            <option value="-1">Filter by Status</option>
                                                            <option value="1">Active</option>
                                                            <option value="0">Inactive</option>
                                                        </select>
                                                    </div>
                                                    <div class="col-md-4 d-flex">
                                                        <button type="submit" class="btn btn-primary" style="margin-right: 5px;">Filter</button>
                                                        <a href="addnewstaff.jsp"  class="btn btn-primary">Add new Staff</a>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                        <div class="table_section padding_infor_info">
                                            <div class="table-responsive-sm">
                                                <table class="table w-100">
                                                    <thead>
                                                        <tr>
                                                            <th>ID</th>
                                                            <th>Name</th>
                                                            <th>Email</th>                                               
                                                            <th>Address</th>
                                                            <th>Role</th>
                                                            <th>Status</th> 
                                                            <th>Detail</th>
                                                            <th>Option</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach items="${requestScope.staffs}" var="staff">
                                                            <tr>
                                                                <td>${staff.id}</td>
                                                                <td>${staff.name}</td>
                                                                <td>${staff.email}</td>                                               
                                                                <td>${staff.address}</td>
                                                                <td>${staff.role.name}</td>
                                                                <td>${staff.status==1?'Working':'Retired'}</td>
                                                                <td style="text-align: center">
                                                                    <a href="#" data-toggle="modal" data-target="#staffDetail${staff.id}">
                                                                        <i class="fa fa-user" aria-hidden="true"></i>
                                                                    </a>
                                                                </td>

                                                                <td><a href="updateStaffInfor?id=${staff.id}">Update</a></td>
                                                            </tr>
                                                        <div id="staffDetail${staff.id}" class="modal fade">
                                                            <div class="modal-dialog" style="max-width: 60%">
                                                                <div class="modal-content">
                                                                    <div class="modal-header">
                                                                        <h3>Staff Information</h3>
                                                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                                    </div>
                                                                    <div class="modal-body" style="display: flex ">
                                                                        <div style="width: 50%;text-align: center;">
                                                                            <img style="width: 70%; border-radius: 50%;margin-top: 15%" src="images/logo/person.jpg" alt="alt"/>
                                                                        </div>
                                                                        <div style="width: 50%">
                                                                            <p><strong>ID:</strong> ${staff.id}</p>
                                                                            <p><strong>Name:</strong> ${staff.name}</p>
                                                                            <p><strong>Bod</strong> ${staff.bod}</p>
                                                                            <p><strong>Email:</strong> ${staff.email}</p>
                                                                            <p><strong>Phone:</strong> ${staff.phone}</p>
                                                                            <p><strong>Address:</strong> ${staff.address}</p>
                                                                            <p><strong>CCCD:</strong> ${staff.cccd}</p>
                                                                            <p><strong>Salary:</strong> ${staff.salary} VND</p>
                                                                                <p><strong>Education: </strong> ${staff.education}</p>
                                                                            <p><strong>Bank: </strong> ${staff.bank}</p>
                                                                            <p><strong>Start date :</strong> ${staff.startDate}</p>
                                                                            <p><strong>End date :</strong> ${staff.endDate}</p>
                                                                            <p><strong>Role:</strong> ${staff.role.name}</p>
                                                                            <p><strong>Status:</strong> ${staff.status == 1 ? 'Working' : 'Retired'}</p>
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
                                <!-- More tables can be added here -->
                            </div>
                        </div>
                    </div>
                    <form method="get" action="view-all-staff" style="display: flex; align-items: center; gap: 10px;">
                        <!-- Dropdown ch?n trang -->
                        <label for="page" style="font-size: 14px; font-weight: bold;">Trang:</label>
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