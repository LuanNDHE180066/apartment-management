<%-- 
    Document   : dashboard_request
    Created on : Mar 11, 2025, 8:10:26 AM
    Author     : PC
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
            <!--[if lt IE 9]>
              <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
              <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
            <![endif]-->
        </head>
        <body>
            <%@include file="sidebar.jsp" %>
            <div id="content">
                <%@include file="topbar.jsp" %>
                <div style="margin-top: 30px">
                    <form onchange="this.submit()" action="dashboard-request" method="post">
                        <div class="row align-items-center">
                            <div class="col-md-4">
                                <select name="year" style="width: 100px">
                                    <c:forEach begin="${requestScope.startYear}" end="${requestScope.endYear}" var="year">
                                        <option value="${year}" <c:if test="${requestScope.selectedYear == year}">selected</c:if>>${year}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="row align-items-center">
                            <label for="top">Top Employee</label>
                            <div class="col-md-4" name="top">
                                <label for="name">Name</label>
                                <h4 id="name" name="name" >${requestScope.employee.name}</h4>
                            </div>
                            <div class="col-md-4">
                                <label for="role">Role</label>
                                <h4 id="role" name="role" >${requestScope.employee.role.name}</h4>
                            </div>
                            <div class="col-md-3">
                                <label for="requestnumber">Number Request</label>
                                <h4 id="requestnumber" name="requestnumber">${requestScope.employee.status}</h4>
                            </div>
                        </div>
                    </form>
                </div>
                <div style="margin-top: 20px" class="midde_cont">
                    <canvas width="80%" id="request-information"></canvas>
                    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>
                    <script type="text/javascript">
                        var chart = document.getElementById("request-information").getContext("2d");
                        var myChart = new Chart(chart, {
                            type: "line",
                            data: {
                                labels: ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"],
                                datasets: [{
                                        label: "Request",
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
