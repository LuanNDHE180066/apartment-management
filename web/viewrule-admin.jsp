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
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <style>
            .pagination .btn {
                padding:  10px;
                font-size: 14px;
                min-width: 75px;
                text-align: center;
                white-space: nowrap;
            }

            .status-active {
                color: green;

            }
            .status-inactive {
                color: red;

            }
            .pagination .btn-primary {
                background-color: #007bff !important; /* Bootstrap primary blue */
                color: #fff !important;
            }
            .pagination .btn-primary.active {
                background-color: #0056b3 !important; /* Darker blue for active */
                color: #fff !important;
            }
            .table th {
                text-align: center;
                background-color: #6B90DA;
                color: black;
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
                                                <h2>N?i Quy Chung C?</h2>
                                            </div>
                                        </div>

                                        <!-- Filter Bar + Add Button -->
                                        <div style="margin-left: 40px; margin-bottom: 15px;">
                                            <form action="view-rule-admin" method="post" class="row align-items-center">
                                                <!-- Search by Description -->
                                                <div class="col-md-3">
                                                    <label for="searchDescription" style="font-weight: bold">T�m Ki?m B?ng T�n</label>
                                                    <input type="text" id="searchDescription" name="title" class="form-control" placeholder="Enter description" value="${sessionScope.title}">
                                                </div>

                                                <!-- Filter by Effective Date -->
                                                <div class="col-md-3">
                                                    <label for="dateFilter" style="font-weight: bold">T�m Ki?m B?ng Ng�y Hi?u L?c</label>
                                                    <select id="dateFilter" class="form-control" name="date">
                                                        <option value="">Ch?n</option>
                                                        <option value="1" <c:if test="${sessionScope.date ==1}">selected="" </c:if>>1 Ng�y Tr??c</option>
                                                        <option value="7" <c:if test="${sessionScope.date ==7}">selected="" </c:if>>7 Ng�y Tr??c</option>
                                                        <option value="15" <c:if test="${sessionScope.date ==15}">selected="" </c:if>>15 Ng�y Tr??c</option>
                                                        <option value="30" <c:if test="${sessionScope.date ==30}">selected="" </c:if>>1 Th�ng Tr??c</option>
                                                        <option value="180" <c:if test="${sessionScope.date ==180}">selected="" </c:if>>6 Th�ng Tr??c</option>
                                                        <option value="365" <c:if test="${sessionScope.date ==365}">selected="" </c:if>>1 N?m Tr??c</option>
                                                        </select>
                                                    </div>
                                                    <!-- Search Button -->
                                                    <div class="col-md-3 mt-4">
                                                        <button type="submit" class="btn btn-primary">T�m Ki?m</button>
                                                        <a href="add-new-rule" class="btn btn-primary">Th�m M?i</a>
                                                    </div>
                                                </form>
                                            </div>

                                            <!-- Table Section -->
                                            <div class="table_section padding_infor_info">
                                                <div class="table-responsive-sm">
                                                    <table class="table w-100">
                                                        <thead>
                                                            <tr>
                                                                <th>ID</th>
                                                                <th>Ti�u ??</th>
                                                                <th>N?i D?ng</th>
                                                                <th>Ng�y T?o</th>
                                                                <th>Ng�y Hi?u L?c</th>
                                                                <th>T�nh Tr?ng</th>
                                                                <th>Ng??i T?o</th>
                                                                <th>X�a</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                        <c:forEach items="${requestScope.rules}" var="rule">
                                                            <tr>
                                                                <td>${rule.id}</td>
                                                                <td>${rule.title}</td>
                                                                <td>${rule.description}</td>
                                                                <td>${rule.date}</td>
                                                                <td>${rule.effectiveDate}</td>                                               
                                                                <td>
                                                                    <span class="${rule.status == 'Active' ? 'status-active' : 'status-inactive'}">${rule.status}</span>
                                                                </td>                                              
                                                                <td>${rule.staff.name}</td>  
                                                                <td><a href="update-rule?id=${rule.id}"><i class="fa-solid fa-pen-to-square"></i></a></td>
                                                                <td><a href="delete-rule?id=${rule.id}" onclick="return confirm('Are you sure to delete this rule?')"><i class="material-icons" title="Delete">&#xE872;</i></a></td>
                                                            </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                </table>
                                                <div class="pagination">
                                                    <c:if test="${currentPage > 1}">
                                                        <a href="view-rule-admin?page=${currentPage - 1}" class="btn btn-sm btn-primary text-white">Tr??c</a>
                                                    </c:if>

                                                    <c:forEach begin="1" end="${totalPages}" var="i">
                                                        <a href="view-rule-admin?page=${i}" class="btn btn-sm btn-primary text-white ${i == currentPage ? 'active' : ''}">${i}</a>
                                                    </c:forEach>

                                                    <c:if test="${currentPage < totalPages}">
                                                        <a href="view-rule-admin?page=${currentPage + 1}" class="btn btn-sm btn-primary text-white">Sau</a>
                                                    </c:if>
                                                </div>


                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <!-- JavaScript to auto-fill date filter -->


                                <!-- More tables can be added here -->
                            </div>
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