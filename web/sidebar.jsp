<%-- 
    Document   : sidebar
    Created on : 29 Jan 2025, 16:45:26
    Author     : NCPC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="util.Util"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css"/>
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
        <style>
            .display-none{
                display: none;
            }
            .user_img {
                width: 60px;  /* Kích thước khung chứa ảnh */
                height: 60px;
                overflow: hidden;
                border-radius: 50%;  /* Bo tròn khung chứa */
                display: flex;
                justify-content: center;
                align-items: center;
            }

            .user_img img {
                width: 100%;
                height: 100%;
                object-fit: cover;  /* Đảm bảo ảnh không bị méo */
                border-radius: 50%;  /* Bo tròn ảnh bên trong */
            }

        </style>
    </head>
    <body>
        <jsp:useBean id="ut" class="util.Util" scope="page"/>
        <nav id="sidebar">
            <div class="sidebar_blog_1">
                <div class="sidebar-header">
                    <div class="logo_section">
                        <a href="index.jsp"><img class="logo_icon img-responsive" src="images/logo/building.jpg" alt="#" /></a>
                    </div>
                </div>
                <div class="sidebar_user_info">
                    <div class="icon_setting"></div>
                    <div class="user_profle_side">
                        <div class="user_img"><img class="img-responsive rounded-circle" src="${sessionScope.person.image == null ?'images/avatar/person.jpg':sessionScope.person.image}" alt="#" /></div>
                        <div class="user_info">
                            <h6>${sessionScope.person.name}</h6>
                            <p><span class="online_animation"></span> Online</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="sidebar_blog_2">
                <h4>General</h4>
                <ul class="list-unstyled components">
                    <!--                    <li class="active">
                                            <a href="#dashboard" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle"><i class="fa fa-dashboard yellow_color"></i> <span>Dashboard</span></a>
                                            <ul class="collapse list-unstyled" id="dashboard">
                                                <li>
                                                    <a href="dashboard.html">> <span>Default Dashboard</span></a>
                                                </li>
                                                <li>
                                                    <a href="dashboard_2.html">> <span>Dashboard style 2</span></a>
                                                </li>
                                            </ul>
                                        </li>-->
                    <!--                    <li><a href="widgets.html"><i class="fa fa-clock-o orange_color"></i> <span>Widgets</span></a></li>-->
                    <!--                    <li>
                                            <a href="#element" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle"><i class="fa fa-diamond purple_color"></i> <span>Elements</span></a>
                                            <ul class="collapse list-unstyled" id="element">
                                                <li><a href="general_elements.html"> <span>General Elements</span></a></li>
                                                <li><a href="media_gallery.html"> <span>Media Gallery</span></a></li>
                                                <li><a href="icons.html"> <span>Icons</span></a></li>
                                                <li><a href="invoice.html"> <span>Invoice</span></a></li>
                                            </ul>
                                        </li>-->
                    <li class="${sessionScope.account.roleId != 0? 'display-none':''}"><a href="view-resident"><i class="fa-solid fa-person"></i> <span>Thông tin dân cư</span></a></li>
                    <li class="${sessionScope.account.roleId != 0? 'display-none':''}"><a href="view-all-staff"><i class="fa-solid fa-users-line"></i> <span>Thông tin nhân viên</span></a></li>
                    <li class="${sessionScope.account.roleId != 0? 'display-none':''}"><a href="view-all-company"><i class="fa-solid fa-building"></i> <span>Thông tin công ti</span></a></li>
                    <li ><a href="view-news"><i class="fa-regular fa-newspaper"></i> <span>Tin tức</span></a></li>
                    <li class="${(sessionScope.account.roleId == 0 || sessionScope.account.roleId == 2)? '':'display-none'}"><a href="view-request-type"><i class="fa-solid fa-repeat"></i> <span>Loại yêu cầu</span></a></li>
                    <li class="${(sessionScope.account.roleId == 0 || sessionScope.account.roleId == 3) ? 'display-none':''}"><a href="${ut.getSiteToViewRequest(sessionScope.account.roleId)}"><i class="fa-solid fa-repeat"></i> <span>Yêu cầu từ dân cư</span></a></li>
                    <li class=""><a href="view-all-feedback"><i class="fa-solid fa-comment"></i><span>Đánh giá chung</span></a></li>
                    <li class="${(sessionScope.account.roleId == 0 || sessionScope.account.roleId == 2|| sessionScope.account.roleId == 1|| sessionScope.account.roleId == 6) ? 'display-none':''}"><a href="${ut.getSiteToViewRule(sessionScope.account.roleId)}"><i class="fa-solid fa-scale-balanced"></i><span>Quy tắc trung cư</span></a></li>
                    <li class="${sessionScope.account.roleId != 0? 'display-none':''}"><a  href="view-categoryservice-staff"><i class="fas fa-cube"></i><span>Loại dịch vụ</span></a></li>
                    <li class="${sessionScope.account.roleId != 0? 'display-none':''}"><a href="view-roomtype"><i class="fa-solid fa-house-flag"></i><span>Loại phòng</span></a></li>
                    <li class="${sessionScope.account.roleId != 1 && sessionScope.account.roleId != 6 ? 'display-none':''}"><a href="view-all-resident-apartment"><i class="fa-solid fa-house-flag"></i><span>Căn hộ của tôi</span></a></li>
                    <li class="${sessionScope.account.roleId != 0? 'display-none':''}"><a  href="view-floor-staff"><i class="fa-solid fa-building"></i><span>Thông tin tầng</span></a></li>
                    <li class="${sessionScope.account.roleId != 0? 'display-none':''}">
                        <a  href="${ut.getSiteToViewApartment(sessionScope.account.roleId)}"><i class="fa-solid fa-scale-balanced"></i><span>Thông tin căn hộ</span></a>
                    </li>
                    <li class="${(sessionScope.account.roleId != 0 && sessionScope.account.roleId != 3) ? 'display-none':''}">
                        <a  href="view-all-contract"><i class="fa-solid fa-book"></i><span>View contract</span></a>
                    </li>
<!--                    <li class="${(sessionScope.account.roleId != 0 && sessionScope.account.roleId != 3) ? 'display-none':''}">
                        <a  href="view-expenditure"><i class="fa fa-money-bill"></i><span>Khoản chi</span></a>
                    </li>-->
                    <li class="${(sessionScope.account.roleId != 0 && sessionScope.account.roleId != 3) ? 'display-none':''}">
                        <a  href="expenditure-report"><i class="fa fa-money-bill"></i><span>Khoản chi</span></a>
                    </li>
                    <li class="${sessionScope.account.roleId != 0? 'display-none':''}">
                        <a  href="dashboard-resident"><i class="fa fa-dashboard"></i><span>Dash Board Resident</span></a>
                    </li>
                    <li class="${(sessionScope.account.roleId == 0 || sessionScope.account.roleId == 2)? '':'display-none'}">
                        <a href="dashboard-request"><i class="fa-solid fa-repeat"></i> <span>Dash Board Request</span></a>
                    </li>
                    <li class="${sessionScope.account.roleId != 1 && sessionScope.account.roleId != 6? 'display-none':''}">
                        <a  href="view-service-resident"><i class="fa fa-headset"></i><span>Sử dụng dịch vụ</span></a>
                    </li>
                    <li class="${sessionScope.account.roleId != 2? 'display-none':''}">
                        <a href="all-services"><i class="fa fa-headset"></i><span>Quản lí dịch vụ</span></a>
                    </li>
                    <li class="${sessionScope.account.roleId != 3? 'display-none':''}">
                        <a href="view-apartmentservice-staff"><i class="fa fa-headset"></i><span>Theo dõi sử dụng dịch vụ</span></a>
                    </li>
                    <li class="${sessionScope.account.roleId != 1 && sessionScope.account.roleId != 6? 'display-none':''}">
                        <a href="view-invoice-resident"><i class="fa fa-money-bill-wave"></i><span>Hóa đơn</span></a>
                    </li>
                    <li class="${sessionScope.account.roleId != 3? 'display-none':''}">
                        <a href="view-invoice-staff"><i class="fa fa-money-bill-wave"></i><span>Hóa đơn</span></a>
                    </li>
                    <li class="${sessionScope.account.roleId != 3? 'display-none':''}">
                        <a href="dashboard-invoice-staff"><i class="fa fa-dashboard"></i><span>Chart</span></a>
                    </li>
                    <li class="${sessionScope.account.roleId != 0? 'display-none':''}">
                        <a href="view-role"><i class="fa fa-shield-alt"></i><span>Chức vụ</span></a>
                    </li>
<!--                    <li class="${sessionScope.account.roleId != 3? 'display-none':''}">
                        <a href="fund"><i class="fa fa-dashboard"></i><span>fund</span></a>
                    </li>-->
                    <li>
                        <a href="view-service-change"><i class="fa fa-chart-line"></i><span>Biến động giá dịch vụ</span></a>
                    </li>


                    <!--                    <li>
                                            <a href="#apps" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle"><i class="fa fa-object-group blue2_color"></i> <span>Apps</span></a>
                                            <ul class="collapse list-unstyled" id="apps">
                                                <li><a href="email.html"> <span>Email</span></a></li>
                                                <li><a href="calendar.html"> <span>Calendar</span></a></li>
                                                <li><a href="media_gallery.html">> <span>Media Gallery</span></a></li>
                                            </ul>
                                        </li>
                                        <li><a href="price.html"><i class="fa fa-briefcase blue1_color"></i> <span>Pricing Tables</span></a></li>
                                        <li>
                                            <a href="contact.html">
                                                <i class="fa fa-paper-plane red_color"></i> <span>Contact</span></a>
                                        </li>
                                        <li class="active">
                                            <a href="#additional_page" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle"><i class="fa fa-clone yellow_color"></i> <span>Additional Pages</span></a>
                                            <ul class="collapse list-unstyled" id="additional_page">
                                                <li>
                                                    <a href="profile.jsp">> <span>Profile</span></a>
                                                </li>
                                                <li>
                                                    <a href="project.html">> <span>Projects</span></a>
                                                </li>
                                                <li>
                                                    <a href="login.html">> <span>Login</span></a>
                                                </li>
                                                <li>
                                                    <a href="404_error.html">> <span>404 Error</span></a>
                                                </li>
                                            </ul>
                                        </li>
                                        <li><a href="map.html"><i class="fa fa-map purple_color2"></i> <span>Map</span></a></li>
                                        <li><a href="charts.html"><i class="fa fa-bar-chart-o green_color"></i> <span>Charts</span></a></li>
                                        <li><a href="settings.html"><i class="fa fa-cog yellow_color"></i> <span>Settings</span></a></li>-->
                </ul>
            </div>
        </nav>
        <script src="js/jquery.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <!-- wow animation -->
        <script src="js/animate.js"></script>
        <!-- select country -->
        <script src="js/bootstrap-select.js"></script>
        <!-- owl carousel -->
        <script src="js/owl.carousel.js"></script> 
        <!-- chart js -->
        <script src="js/Chart.min.js"></script>
        <script src="js/Chart.bundle.min.js"></script>
        <script src="js/utils.js"></script>
        <script src="js/analyser.js"></script>
        <!-- nice scrollbar -->
        <script src="js/perfect-scrollbar.min.js"></script>
        <script>
            var ps = new PerfectScrollbar('#sidebar');
        </script>
        <!-- custom js -->
        <script src="js/custom.js"></script>
        <script src="js/chart_custom_style1.js"></script>
    </body>
</html>
