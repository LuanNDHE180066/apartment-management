<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <!-- basic -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- mobile metas -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <!-- site metas -->
    <title>News List - Your Website</title>
    <link rel="icon" href="images/fevicon.png" type="image/png" />
    <!-- bootstrap css -->
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <!-- site css -->
    <link rel="stylesheet" href="style.css" />
    <!-- responsive css -->
    <link rel="stylesheet" href="css/responsive.css" />
    <!-- custom css -->
    <link rel="stylesheet" href="css/custom.css" />
</head>
<body class="inner_page news_page">
    <div class="full_container">
        <div class="inner_container">
            <!-- Sidebar -->
            <%@ include file="sidebar.jsp" %>
            <!-- end sidebar -->
            <!-- right content -->
            <div id="content">
                <!-- topbar -->
                <%@ include file="topbar.jsp" %>
                <!-- end topbar -->
                <div class="midde_cont">
                    <div class="container-fluid">
                        <div class="row column_title">
                            <div class="col-md-12">
                                <div class="page_title">
                                    <h2>Danh s�ch Tin T?c</h2>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <!-- news section -->
                            <div class="col-md-12">
                                <div class="white_shd full margin_bottom_30">
                                    <div class="full graph_head">
                                        <div class="heading1 margin_0">
                                            <h2>C�c Tin T?c G?n ?�y</h2>
                                        </div>
                                    </div>
                                    <div class="news_section padding_infor_info">
                                        <ul class="list-group">
                                            <c:forEach items="${requestScope.newsList}" var="news">
                                                <li class="list-group-item">
                                                    <a href="viewNews?id=${news.id}">
                                                        ${news.date} : ${news.title}
                                                    </a>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- footer -->
                <div class="container-fluid">
                    <div class="footer">
                        <p>Copyright � 2025 Designed by Your Company. All rights reserved.</p>
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