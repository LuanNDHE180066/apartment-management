<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="util.Util"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- mobile metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="viewport" content="initial-scale=1, maximum-scale=1">
        <!-- site metas -->
<title>Apartment management</title>        <meta name="keywords" content="">
        <meta name="description" content="">
        <meta name="author" content="">
        <!-- site icon -->
        <link rel="icon"img-responsive rounded href="images/fevicon.png" type="image/png" />
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
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
        <style>
            .dropdown-toggle img {
    width: 30px;      /* Kích thước ảnh (có thể chỉnh theo ý muốn) */
    height: 30px;
    object-fit: cover;  /* Giữ tỷ lệ gốc của ảnh, không bị méo */
    border-radius: 50%; /* Bo tròn ảnh */
    margin-right: 5px;  /* Tạo khoảng cách giữa ảnh và tên người dùng */
    vertical-align: middle; /* Căn giữa ảnh với text */
}

        </style>
    </head>
    <body>
        <div class="topbar">
            <jsp:useBean id="util" class="util.Util" scope="page"/>

            <nav class="navbar navbar-expand-lg navbar-light">
                <div class="full">
                    <button type="button" id="sidebarCollapse" class="sidebar_toggle"><i class="fa fa-bars"></i></button>
                    <div class="logo_section">
                        <a href="index.jsp"><img class="img-responsive" src="images/logo/png-transparent-computer-icons-home-house-home-angle-building-rectangle-thumbnail.png" alt="#" /></a>
                    </div>
                    <div class="right_topbar">
                        <div class="icon_info">
<!--                            <ul>
                                <li><a href="#"><i class="fa fa-bell-o"></i><span class="badge">2</span></a></li>
                                <li><a href="#"><i class="fa fa-question-circle"></i></a></li>
                                <li><a href="#"><i class="fa fa-envelope-o"></i><span class="badge">3</span></a></li>
                            </ul>-->
                            <ul class="user_profile_dd">
                                <li>
                                    <a class="dropdown-toggle" data-toggle="dropdown"><img class="img-responsive rounded-circle" src="${sessionScope.person.image == null ?'images/avatar/person.jpg':sessionScope.person.image}"alt="#" /><span class="name_user">${sessionScope.person.name}</span></a>
                                    <div class="dropdown-menu">
                                        <a class="dropdown-item" href="${util.getTableNameByRoleId(sessionScope.account.roleId)}">My Profile</a>
                                        <a class="dropdown-item" href="#">Settings</a>
                                        <a class="dropdown-item" href="#">Help</a>
                                        <a class="dropdown-item" href="logout"><span>Log Out</span> <i class="fa fa-sign-out"></i></a>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </nav>
        </div>

        <script src="js/jquery.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <!-- wow animation -->
        <!-- select country -->
        <!-- owl carousel -->

        <!-- chart js -->
        <!-- nice scrollbar -->
        <script src="js/perfect-scrollbar.min.js"></script>
        <script>
            var ps = new PerfectScrollbar('#sidebar');
        </script>
        <!-- custom js -->
        <script>
            function toggleVisibility(id) {
                const element = document.getElementById(id);
                element.style.display = element.style.display === "none" || !element.style.display ? "block" : "none";
            }
        </script>

    </body>
</html>
