<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
            body {
                font-family: Arial, sans-serif;
                background-color: #f8f9fa;
                color: #333;
            }

            .container-fluid {
                padding: 20px;
            }

            .white_shd {
                background: #fff;
                border-radius: 10px;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
                padding: 20px;
            }

            h2, h3 {
                color: #2c3e50;
            }

            h3 {
                font-size: 20px;
                font-weight: bold;
                border-bottom: 3px solid #3498db;
                display: inline-block;
                padding-bottom: 5px;
                margin-bottom: 15px;
            }

            .table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 15px;
            }

            .table th, .table td {
                padding: 10px;
                text-align: left;
                border-bottom: 1px solid #ddd;
            }

            .table th {
                background-color: #3498db;
                color: white;
                font-weight: bold;
            }

            .table tbody tr:hover {
                background-color: #f1f1f1;
            }

            input[type="number"] {
                width: 60px;
                padding: 5px;
                border: 1px solid #ccc;
                border-radius: 4px;
                text-align: center;
            }

            a {
                text-decoration: none;
                color: #3498db;
                font-size: 18px;
            }

            a:hover {
                color: #217dbb;
            }

            /* Căn chỉnh bảng */
            .table-responsive-sm {
                margin-bottom: 20px;
            }

            .table_section {
                padding: 20px;
            }

            /* Dropdown */
            select {
                padding: 7px;
                border-radius: 5px;
                border: 1px solid #ccc;
                font-size: 14px;
                cursor: pointer;
            }

            select:focus {
                outline: none;
                border-color: #3498db;
            }

            /* Flexbox */
            .d-flex {
                display: flex;
                justify-content: space-between;
                align-items: center;
            }
            td{
                color: black;
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
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="white_shd full margin_bottom_30">
                                        <div class="full graph_head d-flex justify-content-between align-items-center">
                                            <div class="heading1 margin_0">
                                                <h2>Service Register</h2>
                                            </div>
                                        </div>
                                        <div class="table_section padding_infor_info">
                                            <div style="margin-left: 40px; margin-bottom: 30px; display: flex; align-items: center; gap: 10px;">
                                                <form action="view-service-resident" method="post" style="display: flex; align-items: center; gap: 10px;">
                                                    <label for="idapartment" style="font-weight: bold;">Chung cư số:</label>
                                                    <select onchange="this.form.submit()" id="idapartment" name="idapartment" 
                                                            style="padding: 5px; border: 1px solid #ccc; border-radius: 4px; font-size: 14px; width: 200px;">
                                                        <c:forEach items="${requestScope.owned}" var="item">
                                                            <option ${requestScope.aid==item.id? 'selected':'' } value="${item.id}">${item.id}</option>
                                                        </c:forEach>
                                                    </select>
                                                </form>
                                            </div>


                                            <div style="display: flex" class="table-responsive-sm">
                                                <div style="width: 50%">
                                                    <h3 style="margin-bottom: 20px; font-size: 24px; font-weight: bold; color: #2c3e50; display: inline-block; border-bottom: 3px solid #3498db; padding-bottom: 5px;">
                                                        Dịch vụ đang sử dụng
                                                    </h3>

                                                    <table class="table w-100">
                                                        <thead>
                                                            <tr>
                                                                <th style="width: 40%;">Name of service</th>
                                                                <th style="width: 5%;">Quantity</th>
                                                                <th style="width: 15%;">Unit Price</th>
                                                                <th style="width: 15%;">Unit</th>
                                                                <th style="width: 25%;">Supplier</th>
                                                                <th>Action</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:forEach items="${requestScope.usingServices}" var="item">
                                                                <c:if test="${item.service.categoryService.id!='SV001'}">
                                                                <form action="update-service-resident" method="post">
                                                                    <tr>
                                                                        <td style="text-align: left">${item.service.name}</td>
                                                                        <td style="text-align: center">
                                                                            <input name="serviceId" value="${item.service.id}" hidden=""/>
                                                                            <input name="apartmentId" value="${item.apartment.id}" hidden=""/>
                                                                            <input onchange="this.form.submit()" step="1"
                                                                                   ${item.service.categoryService.id == 'SV002' || requestScope.canUpdate==0 ? 'readonly' : ''}
                                                                                   type="number" min="1" name="quantity" value="${item.quantity}"/>
                                                                        </td> 
                                                                        <td style="text-align: right">
                                                                            <fmt:formatNumber type="currency" currencyCode="VND" value="${item.service.unitPrice}"/>
                                                                        </td>
                                                                        <td style="text-align: left">${item.service.unit}</td>
                                                                        <td style="text-align: left">${item.service.company.name}</td>
                                                                        <td style="text-align: center">
                                                                            <a href="${requestScope.canUpdate == 1 ? 
                                                                                       'delete-service-resident?sid='.concat(item.service.id).concat('&aid=').concat(item.apartment.id) 
                                                                                       : '#'}" 
                                                                               class="fa-solid fa-minus">
                                                                            </a>

                                                                        </td>
                                                                    </tr>
                                                                </form>
                                                            </c:if>
                                                        </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>
                                                <div style="width: 50%;margin-left: 20px">
                                                    <h3 style="margin-bottom: 20px; font-size: 24px; font-weight: bold; color: #2c3e50; display: inline-block; border-bottom: 3px solid #3498db; padding-bottom: 5px;">
                                                        Dịch vụ Có thể sử dụng
                                                    </h3>
                                                    <table  class="table w-100">
                                                        <thead>
                                                            <tr>
                                                                <th style="width: 40%;">Name of service</th>
                                                                <th style="width: 15%;">Unit Price</th>
                                                                <th style="width: 25%;">Supplier</th>
                                                                <th>Action</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:forEach items="${requestScope.notUsingServices}" var="item">
                                                                <tr>
                                                                    <td>${item.name}</td>
                                                                    <td>${item.unitPrice}</td>
                                                                    <td>${item.company.name}</td>
                                                                    <td style="text-align: center">
                                                                        <a href="${requestScope.canUpdate == 1 ? 
                                                                                   'update-service-resident?aid='.concat(requestScope.aid).concat('&sid=').concat(item.id) 
                                                                                   : '#'}" 
                                                                           class="fa-solid fa-plus">
                                                                    </td>
                                                                </tr>
                                                            </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>


                                            <div class="table-responsive-sm">
                                                <div>
                                                    <h3 style="margin-bottom: 20px; font-size: 24px; font-weight: bold; color: #2c3e50; display: inline-block; border-bottom: 3px solid #3498db; padding-bottom: 5px;">
                                                        Dịch vụ cố định
                                                    </h3>
                                                </div>

                                                <table class="table w-100">
                                                    <thead>
                                                        <tr>
                                                            <th style="width: 40%;">Name of service</th>
                                                            <th style="width: 5%;">Quantity</th>
                                                            <th style="width: 15%;">Unit Price</th>
                                                            <th style="width: 15%;">Unit</th>
                                                            <th style="width: 25%;">Supplier</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach items="${requestScope.usingServices}" var="item">
                                                            <c:if test="${item.service.categoryService.id=='SV001'}">
                                                                <tr>    
                                                                    <td>${item.service.name}</td>
                                                                    <td style="text-align: center">${item.quantity}</td> 
                                                                    <td style="text-align: right">
                                                                        <fmt:formatNumber type="currency" currencyCode="VND" value="${item.service.unitPrice}"/>
                                                                    </td>
                                                                    <td>${item.service.unit}</td>
                                                                    <td>${item.service.company.name}</td>
                                                                </tr>
                                                            </c:if>
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