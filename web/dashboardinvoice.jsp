<%-- 
    Document   : dashboard-resident-information
    Created on : Feb 23, 2025, 8:41:41 PM
    Author     : thanh
--%>

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
            <title>Dashboard</title>            <link rel="icon" href="images/fevicon.png" type="image/png" />
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
        </head>
        <body>
            <%@include file="sidebar.jsp" %>
            <div id="content">
                <%@include file="topbar.jsp" %>
                <div style="text-align: center; margin-top: 30px; display: flex; justify-content: center;">
                    <button style="background-color: #4CAF50; color: black; padding: 15px 30px; margin: 0 10px; border: none; border-radius: 5px; cursor: pointer; font-size: 18px; flex: 1;">
                        <a style="color: black" href="dashboard-invoice-staff">Thống kê doanh số</a>
                        <button style="background-color: #008CBA; color: black; padding: 15px 30px; margin: 0 10px; border: none; border-radius: 5px; cursor: pointer; font-size: 18px; flex: 1;">
                            <a  style="color: black" href="dashboard-percent-service">Thống kê tỉ lệ</a>
                        </button>
                        <button style="background-color: #f44336; color: black; padding: 15px 30px; margin: 0 10px; border: none; border-radius: 5px; cursor: pointer; font-size: 18px; flex: 1;">
                            <a  style="color: black" href="dashboard-number-service">Chi tiết số lượng</a>
                        </button>
                </div>



                <c:choose>
                    <c:when test="${requestScope.screen == 1}">
                        <div style="margin-top: 30px; display: flex; justify-content: space-between; align-items: center; padding: 15px; background-color: #e1f5fe; border: 2px solid #ddd; border-radius: 8px; font-family: 'Roboto', sans-serif;">
                            <div style="font-size: 24px; font-weight: bold; color: black;">Tổng doanh số trong năm: ${requestScope.total} VNĐ</div>
                            <form onchange="this.submit()" action="dashboard-invoice-staff" method="post">
                                <div class="row align-items-center">
                                    <div class="col-md-4">
                                        <select name="year" style="width: 150px; padding: 8px; border: 1px solid #ccc; border-radius: 5px; font-size: 16px; font-family: 'Roboto', sans-serif;">
                                            <c:forEach begin="${requestScope.startYear}" end="${requestScope.endYear}" var="year">
                                                <option ${requestScope.currentYear== year?'selected':''} value="${year}">${year}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </c:when>

                    <c:when test="${requestScope.screen == 2}">
                        <div style="margin-top: 30px; display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; padding: 15px; background-color: #e1f5fe; border: 2px solid #ddd; border-radius: 8px; font-family: 'Roboto', sans-serif;">
                            <form action="dashboard-percent-service" method="post" style="display: flex; align-items: center; gap: 20px; flex-wrap: wrap; flex: 2; justify-content: flex-end;">
                                <div style="display: flex; align-items: center; gap: 10px; flex: 1; min-width: 200px;">
                                    <label for="year">Năm:</label>
                                    <select name="year" id="year" style="flex: 1; min-width: 150px; padding: 8px; border: 1px solid #ccc; border-radius: 5px; font-size: 16px;">
                                        <c:forEach begin="${requestScope.startYear}" end="${requestScope.endYear}" var="year">
                                            <option ${requestScope.currentYear== year?'selected':''} value="${year}">${year}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div style="display: flex; align-items: center; gap: 10px; flex: 1; min-width: 250px;">
                                    <label for="serviceId">Dịch vụ:</label>
                                    <select name="serviceName" id="serviceId" style="flex: 1; min-width: 150px; padding: 8px; border: 1px solid #ccc; border-radius: 5px; font-size: 16px;">
                                        <c:forEach items="${requestScope.sv}" var="item">
                                            <option ${requestScope.serviceName== item.name?'selected':''} value="${item.name}">${item.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <button type="submit" style="padding: 8px 15px; border: none; background-color: #007bff; color: white; border-radius: 5px; cursor: pointer; font-size: 16px;">Search</button>
                            </form>
                        </div>

                    </c:when>

                    <c:when test="${requestScope.screen == 3}">
                        <div style="margin-top: 30px; display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; padding: 15px; background-color: #e1f5fe; border: 2px solid #ddd; border-radius: 8px; font-family: 'Roboto', sans-serif;">
                            <form action="dashboard-number-service" method="post" style="display: flex; align-items: center; gap: 20px; flex-wrap: wrap; flex: 2; justify-content: flex-end;">
                                <div style="display: flex; align-items: center; gap: 10px; flex: 1; min-width: 200px;">
                                    <label for="year">Năm:</label>
                                    <select name="year" id="year" style="flex: 1; min-width: 150px; padding: 8px; border: 1px solid #ccc; border-radius: 5px; font-size: 16px;">
                                        <c:forEach begin="${requestScope.startYear}" end="${requestScope.endYear}" var="year">
                                            <option ${requestScope.currentYear== year?'selected':''} value="${year}">${year}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div style="display: flex; align-items: center; gap: 10px; flex: 1; min-width: 250px;">
                                    <label for="serviceId">Dịch vụ:</label>
                                    <select name="serviceName" id="serviceId" style="flex: 1; min-width: 150px; padding: 8px; border: 1px solid #ccc; border-radius: 5px; font-size: 16px;">
                                        <c:forEach items="${requestScope.sv}" var="item">
                                            <option ${requestScope.serviceName== item.name?'selected':''} value="${item.name}">${item.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <button type="submit" style="padding: 8px 15px; border: none; background-color: #007bff; color: white; border-radius: 5px; cursor: pointer; font-size: 16px;">Search</button>
                            </form>
                        </div>
                    </c:when>
                </c:choose>



                <div style="margin-top: 20px" class="midde_cont">
                    <canvas width="80%" id="invoice-information"></canvas>
                    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>
                    <script type="text/javascript">
                                var chart = document.getElementById("invoice-information").getContext("2d");
                                var myChart = new Chart(chart, {
                                    type: "line",
                                    data: {
                                        labels: ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"],
                                        datasets: [{
                                                label: "Chart",
                                                data: ${requestScope.data},
                                                backgroundColor: "rgba(0, 156, 255, .5)"
                                            }]
                                    },
                                    options: {
                                        responsive: true
                                    }
                                });
                    </script>
                </div>
            </div>
        </body> 
    </html>
