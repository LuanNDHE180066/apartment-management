<%-- 
    Document   : dashboard-resident-information
    Created on : Feb 23, 2025, 8:41:41 PM
    Author     : thanh
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib
    prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!DOCTYPE html>
    <html>
        <head>
            <!-- basic -->
            <meta charset="utf-8" />
            <meta http-equiv="X-UA-Compatible" content="IE=edge" />
            <!-- mobile metas -->
            <meta name="viewport" content="width=device-width, initial-scale=1" />
            <meta name="viewport" content="initial-scale=1, maximum-scale=1" />
            <!-- site metas -->
            <title>Change Service</title>            <link rel="icon" href="images/fevicon.png" type="image/png" />
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
            <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
            <!--[if lt IE 9]>
              <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
              <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
            <![endif]-->
            <style>



            </style>
        </head>
        <body>
            <%@include file="sidebar.jsp" %>
            <div id="content">
                <%@include file="topbar.jsp" %>
                <div class="container">
                    <!-- Bảng dữ liệu -->
                    <div class="form-section" style="background: white; padding: 30px; border-radius: 10px; box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); margin-bottom: 20px;">
                        <form action="view-service-change" method="post" style="display: flex; flex-direction: column; gap: 10px;">
                            <!-- Dịch vụ + Select cùng dòng -->
                            <div style="display: flex; align-items: center;">
                                <p style="font-weight: bold; margin-right: 10px;">Dịch vụ:</p>
                                <select name="serviceId" onchange="this.form.submit()" 
                                        style="padding: 5px; font-size: 14px; border: 1px solid #ccc; border-radius: 5px; width: 20%">
                                    <c:forEach items="${requestScope.allService}" var="item">
                                        <option ${item.id == requestScope.usingService.id?'selected':''} value="${item.id}">${item.name}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <!-- Các thông tin còn lại nằm ngang -->
                            <div style="display: flex; flex-wrap: wrap; gap: 10px;">
                                <p style="margin: 0; padding: 8px 12px; background: #f8f9fa; border-radius: 5px; font-size: 14px; box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.1);">
                                    Công ty cung cấp: ${requestScope.usingService.company.name}
                                </p>
                                <p style="margin: 0; padding: 8px 12px; background: #f8f9fa; border-radius: 5px; font-size: 14px; box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.1);">
                                    Đơn vị: ${requestScope.usingService.unit}
                                </p>
                                <p style="margin: 0; padding: 8px 12px; background: #f8f9fa; border-radius: 5px; font-size: 14px; box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.1);">
                                    Mô tả: ${requestScope.usingService.description}
                                </p>
                                <p style="margin: 0; padding: 8px 12px; background: #f8f9fa; border-radius: 5px; font-size: 14px; box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.1);">
                                    Ngày mở: ${requestScope.usingService.startDate}
                                </p>
                            </div>
                        </form>
                    </div>


                    <div class="data-section" style="display: flex; gap: 20px; align-items: flex-start;">
                        <div class="table-container" style="flex: 1; min-width: 300px; padding-top: 30px">
                            <table style="width: 100%; border-collapse: collapse; text-align: left; font-family: Arial, sans-serif; border-radius: 8px; overflow: hidden; box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);">
                                <tr style="background-color: #007bff; color: white;">
                                    <th style="padding: 10px; border: 1px solid #ddd;">Time</th>
                                    <th style="padding: 10px; border: 1px solid #ddd;">Price</th>
                                </tr>
                                <c:forEach items="${requestScope.data}" var="item">
                                    <tr style="background-color: #f9f9f9;">
                                        <td style="padding: 10px; border: 1px solid #ddd;text-align: center">${item.time}</td>
                                        <td style="padding: 10px; border: 1px solid #ddd;text-align: right">
                                            <fmt:formatNumber currencyCode="VND" type="currency" value="${item.price}" maxFractionDigits="0"/>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>

                        <!-- Biểu đồ -->
                        <div class="chart-container" style="flex: 2; min-width: 400px; height: 300px;">
                            <canvas id="service-information"></canvas>
                        </div>
                    </div>


                    <!-- Script vẽ biểu đồ -->
                    <script>
                        var chart = document.getElementById("service-information").getContext("2d");

                        var labelsData = [
                        <c:forEach items="${requestScope.data}" var="item" varStatus="loop">
                        "${item.time}"<c:if test="${!loop.last}">,</c:if>
                        </c:forEach>
                        ];

                        var priceData = [
                        <c:forEach items="${requestScope.data}" var="item" varStatus="loop">
                            ${item.price}<c:if test="${!loop.last}">,</c:if>
                        </c:forEach>
                        ];

                        var myChart = new Chart(chart, {
                            type: "line",
                            data: {
                                labels: labelsData,
                                datasets: [{
                                        label: "Giá dịch vụ",
                                        data: priceData,
                                        backgroundColor: "rgba(0, 156, 255, .5)",
                                        borderColor: "#007bff",
                                        borderWidth: 2,
                                        fill: false
                                    }]
                            },
                            options: {
                                responsive: true,
                                maintainAspectRatio: false,
                                scales: {
                                    yAxes: [{
                                            ticks: {
                                                beginAtZero: true
                                            }
                                        }]
                                }
                            }
                        });
                    </script>
                </div>
                <div class="container-fluid">
                    <div class="footer">
                        <p>Copyright © 2018 Designed by html.design. All rights reserved.</p>
                    </div>
                </div>
                <script src="js/jquery.min.js"></script>
                <script src="js/popper.min.js"></script>
                <script src="js/bootstrap.min.js"></script>
                <script src="js/custom.js"></script>
        </body> 
    </html>
