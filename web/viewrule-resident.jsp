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
        <style>
            .pagination .btn {
                padding: 5px 10px; /* Adjust padding for better fit */
                font-size: 14px; /* Adjust font size */
                min-width: 75px; /* Ensures button width adapts */
                text-align: center; /* Centers text */
                white-space: nowrap; /* Prevents text from wrapping */
            }

            .status-active {
                color: green;

            }
            .status-inactive {
                color: red;

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
                                                <h2>BaVi apartment's rules</h2>
                                            </div>
                                        </div>
                                        <div style="margin-left: 40px; margin-bottom: 15px;">
                                            <form action="view-rule-resident" method="post" class="row align-items-center">
                                                <!-- Search by Description -->
                                                <div class="col-md-3">
                                                    <label for="searchDescription" style="font-weight: bold">Search Title</label>
                                                    <input type="text" id="searchDescription" name="title" class="form-control" placeholder="Enter description" value="${sessionScope.title}">
                                                </div>

                                                <!-- Filter by Effective Date -->
                                                <div class="col-md-3">
                                                    <label for="dateFilter" style="font-weight: bold">Filter by Effective Date</label>
                                                    <select id="dateFilter" class="form-control" name="date">
                                                        <option value="">Select</option>
                                                        <option value="1" <c:if test="${sessionScope.date ==1}">selected="" </c:if>>1 Day Ago</option>
                                                        <option value="7" <c:if test="${sessionScope.date ==7}">selected="" </c:if>>7 Days Ago</option>
                                                        <option value="15" <c:if test="${sessionScope.date ==15}">selected="" </c:if>>15 Days Ago</option>
                                                        <option value="30" <c:if test="${sessionScope.date ==30}">selected="" </c:if>>1 Month Ago</option>
                                                        <option value="180" <c:if test="${sessionScope.date ==180}">selected="" </c:if>>6 Months Ago</option>
                                                        <option value="365" <c:if test="${sessionScope.date ==365}">selected="" </c:if>>1 Year Ago</option>
                                                        </select>
                                                    </div>
                                                    <!-- Search Button -->
                                                    <div class="col-md-3 mt-4">
                                                        <button type="submit" class="btn btn-primary">Search</button>

                                                    </div>
                                                </form>
                                            </div>
                                            <div class="table_section padding_infor_info">
                                                <div class="table-responsive-sm">
                                                    <table class="table w-100">
                                                        <thead>
                                                            <tr>
                                                                <th>Title</th>
                                                                <th>Description</th>
                                                                <th>Effective Date</th>                                               
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                        <c:forEach items="${requestScope.rules}" var="rule">
                                                            <c:if test="${rule.status=='Active'}">
                                                                <tr>
                                                                    <td>${rule.title}</td>
                                                                    <td>${rule.description}</td>
                                                                    <td>${rule.effectiveDate}</td>                                               
                                                                </tr>
                                                            </c:if>
                                                        </c:forEach>
                                                    </tbody>
                                                </table>
                                                <div class="pagination">
                                                    <c:if test="${currentPage > 1}">
                                                        <a href="view-rule-resident?page=${currentPage - 1}" class="btn btn-sm btn-primary">Previous</a>
                                                    </c:if>

                                                    <c:forEach begin="1" end="${totalPages}" var="i">
                                                        <a href="view-rule-resident?page=${i}" class="btn btn-sm ${i == currentPage ? 'btn-dark' : 'btn-light'}">${i}</a>
                                                    </c:forEach>

                                                    <c:if test="${currentPage < totalPages}">
                                                        <a href="view-rule-resident?page=${currentPage + 1}" class="btn btn-sm btn-primary">Next</a>
                                                    </c:if>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
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