<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="dao.ApartmentDAO" %>
<html lang="en">
    <head>
        <!-- basic -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- mobile metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
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
        <link rel="stylesheet" href="<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg==" crossorigin="anonymous" referrerpolicy="no-referrer" />"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <style>
            .table th, .table td {
                border: 1px solid rgba(0, 0, 0, 0.2);
            }
            .table {
                border-collapse: collapse;
            }
            .table th {
                text-align: center;
                background-color: #6B90DA;
                color: black;
            }
            .search-section {
                margin-bottom: 15px; /* Giảm khoảng cách giữa các phần tử */
            }
            .modal-content {
                border-radius: 10px;
                box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.2);
                background: #fff;
                padding: 20px;
            }

            .modal-header {
                background-color: #6B90DA;
                color: white;
                padding: 15px;
                border-top-left-radius: 10px;
                border-top-right-radius: 10px;
            }

            .modal-header h3 {
                margin: 0;
                font-size: 20px;
                font-weight: bold;
            }

            .modal-body {
                padding: 20px;
                display: flex;
                flex-wrap: wrap;
                justify-content: space-between;
            }

            .modal-body p {
                margin: 5px 0;
                font-size: 16px;
            }

            .modal-body strong {
                color: #333;
            }

            .close {
                color: white;
                font-size: 24px;
                opacity: 0.8;
                transition: 0.3s;
            }

            .close:hover {
                opacity: 1;
            }

            .modal-backdrop {
                background-color: rgba(0, 0, 0, 0.5);
            }
            /* Hiệu ứng nền mờ khi mở modal */
            .modal-backdrop {
                background-color: rgba(0, 0, 0, 0.6) !important;
            }

            /* Căn chỉnh modal */
            .modal-dialog {
                max-width: 500px;
                margin: 10% auto;
                transform: scale(0.9);
                transition: all 0.3s ease-in-out;
            }

            .modal.show .modal-dialog {
                transform: scale(1);
            }


            .modal-content {
                border-radius: 12px;
                box-shadow: 0px 4px 20px rgba(0, 0, 0, 0.3);
                background: #ffffff;
                padding: 20px;
                border: none;
            }

            .modal-header {
                background-color: #4a90e2;
                color: white;
                border-top-left-radius: 12px;
                border-top-right-radius: 12px;
                padding: 15px;
            }

            .modal-header h3 {
                font-size: 24px;
                font-weight: bold;
                margin: 0;
            }

            .modal-body {
                padding: 20px;
                display: flex;
                flex-direction: column;
            }

            .info-box {
                background-color: #f8f9fa; /* Light background for contrast */
                border: 1px solid #e0e0e0;
                border-radius: 8px;
                padding: 10px;
                margin-bottom: 15px;
                font-size: 16px;
                color: #333;
            }

            .info-box strong {
                color: #4a90e2; /* Highlight the labels */
            }

            .close {
                color: white;
                font-size: 28px;
                opacity: 0.9;
            }

            .close:hover {
                opacity: 1;
            }

            .modal-backdrop {
                background-color: rgba(0, 0, 0, 0.7) !important;
            }

        </style>
    </head>
    <body class="inner_page tables_page">
        <jsp:useBean id="aptDAO" class="dao.ApartmentDAO" scope="page"/>
        <div class="full_container">
            <div class="inner_container">
                <%@include file="sidebar.jsp" %>
                <div id="content">
                    <%@include file="topbar.jsp" %>
                    <div class="midde_cont">
                        <div class="container-fluid">
                            <div class="row column_title">
                                <div class="col-md-12">
                                    <div class="page_title">
                                        <h2>My apartment</h2>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-12">
                                    <div class="white_shd full margin_bottom_30">
                                        <div class="full graph_head">
                                            <div class="heading1 margin_0">
                                                <h2>Apartment</h2>
                                            </div>
                                        </div>
                                        <div style="margin-left: 40px;">
                                            <form action="view-all-resident-apartment" method="GET">

                                            </form>
                                        </div>
                                        <div class="table_section padding_infor_info">
                                            <div class="table-responsive-sm">
                                                <table class="table w-100">
                                                    <thead>
                                                        <tr>
                                                            <th style="width: 10%;">Apartment Number</th>
                                                            <th style="width: 10%;">Number of Person</th>
                                                            <th style="width: 30%;">Floor</th>
                                                            <th style="width: 30%;">Information</th>
                                                            <th style="width: 5%;">Other</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                    <h3>${requestScope.message}</h3>
                                                    <h3 style="color: red">${requestScope.error}</h3>
                                                    <c:forEach items="${sessionScope.listapartment}" var="listapartment">
                                                        <tr>
                                                            <td>${listapartment.id}</td>
                                                            <td>${listapartment.numberOfPerson}</td>
                                                            <td>${listapartment.floor.number}</td>
                                                            <td>${listapartment.infor}</td>
                                                            <td style="text-align: center">
                                                                <a href="#" data-toggle="modal" data-target="#apartmentDetail${listapartment.id}">
                                                                    <i class="fa fa-home" aria-hidden="true"></i>
                                                                </a>
                                                                <a href="updatenopersonre?id=${listapartment.id}"><i class="fa-solid fa-pen-to-square"></i></a>   
                                                            </td>

                                                        </tr>
                                                        <div id="apartmentDetail${listapartment.id}" class="modal fade">
                                                            <div class="modal-dialog">
                                                                <div class="modal-content">
                                                                    <!-- Header -->
                                                                    <div class="modal-header">
                                                                        <h3>
                                                                            <i class="fa fa-home" aria-hidden="true"></i> Apartment Information
                                                                        </h3>
                                                                        <button type="button" class="close btn-close-modal" data-dismiss="modal">&times;</button>
                                                                    </div>
                                                                    <!-- Body -->
                                                                    <div class="modal-body">
                                                                        <div class="info-box">
                                                                            <strong>Room Type:</strong> <span>${listapartment.roomtype.name}</span>
                                                                        </div>
                                                                        <div class="info-box">
                                                                            <strong>Max Persons:</strong> <span>${listapartment.roomtype.limitPerson}</span>
                                                                        </div>
                                                                        <div class="info-box">
                                                                            <strong>Area:</strong> <span>${listapartment.roomtype.square} m²</span>
                                                                        </div>
                                                                        <div class="info-box">
                                                                            <strong>Bedrooms:</strong> <span>${listapartment.roomtype.bedroom}</span>
                                                                        </div>
                                                                        <div class="info-box">
                                                                            <strong>Living Rooms:</strong> <span>${listapartment.roomtype.livingRoom}</span>
                                                                        </div>
                                                                        <div class="info-box">
                                                                            <strong>Bathrooms:</strong> <span>${listapartment.roomtype.bathRoom}</span>
                                                                        </div>
                                                                        <div class="info-box">
                                                                            <strong>Balconies:</strong> <span>${listapartment.roomtype.balcony}</span>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>


                                                    </c:forEach>


                                                    <!--                                                    <td>
                                                                                                            <a href="update-room-type?id=${room.id}"><i class="fa-solid fa-pen-to-square"></i></a>
                                                    <c:if test="${!aptDAO.getApartmentByRoomType(room.id)}">
                                                    <a href="delete-room-type?id=${room.id}"><i class="fa-solid fa-trash"></i></a>
                                                    </c:if>
                                            </td>-->
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--                    <form method="get" action="view-all-feedback" style="display: flex; align-items: center; gap: 10px;">
                                             Dropdown ch?n trang 
                                            <label for="page" style="font-size: 14px; font-weight: bold;">Trang:</label>
                                            <select id="page" name="page" onchange="this.form.submit()" 
                                                    style="padding: 6px 12px; font-size: 14px; border: 1px solid #ddd; border-radius: 4px; cursor: pointer;">
                    <c:forEach begin="1" end="${requestScope.totalPage}" var="page">
                        <option value="${page}" <c:if test="${page == requestScope.currentPage}">selected</c:if>>
                        ${page}
                    </option>
                    </c:forEach>
                </select>
    
                <input type="text" value="${param.searchName}" class="form-control" name="searchName" hidden="">
                <input type="date"  name="startDate" placeholder="Start Date" value="${param.startDate}" hidden="">
                <input type="date"  name="endDate" placeholder="End Date" value="${param.endDate}" hidden="">
                <input type="text"  name="endDate" placeholder="End Date" value="${param.serviceType}" hidden="">
            </form>-->
                    <div class="container-fluid">
                        <div class="footer">
                            <p>Copyright © 2018 Designed by html.design. All rights reserved.</p>
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