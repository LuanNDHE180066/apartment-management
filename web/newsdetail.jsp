<%-- 
    Document   : newjsp
    Created on : Feb 1, 2025, 9:56:13 PM
    Author     : quang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- basic -->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- mobile metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>News Detail</title>
        <!-- bootstrap css -->
        <link rel="stylesheet" href="css/bootstrap.min.css" />
        <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
        <style>
            body {
                background-color: #f8f9fa;
                margin: 0;
                padding: 0;
                font-family: 'Times New Roman', Times, serif; /* Sử dụng font Roboto và các font dự phòng */
            }
            .news-container {
                background: #ffffff;
                padding: 50px;
                border-radius: 10px;
                box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
                width: 100%;
                margin: 50px 0;
                transition: transform 0.3s;
            }
            .news-container:hover {
                transform: translateY(-5px);
            }
            .news-title {
                text-align: center;
                margin-bottom: 25px;
                color: #343a40;
                font-size: 36px;
                font-weight: bold;
                line-height: 1.4;
            }
            .news-date {
                text-align: left; /* Căn lề bên trái */
                color: #6c757d;
                margin-bottom: 30px;
                font-size: 16px; /* Kích thước chữ nhỏ hơn */
                padding-left: 15px; /* Khoảng cách từ lề bên trái */
            }
            .news-content {
                font-size: 22px;
                line-height: 1.8;
                color: #495057;
                padding: 15px;
                border-left: 4px solid #007bff;
                background-color: #f8f9fa;
                margin-bottom: 30px;
            }
            .related-links {
                margin-top: 30px;
            }
            .related-links ul {
                list-style-type: none;
                padding: 0;
            }
            .related-links li {
                margin-bottom: 10px;
                font-weight: bold;
            }
            .back-button {
                margin-bottom: 20px;
            }
            #btn {
                transition: background-color 0.3s ease; /* Smooth transition */
            }

            #btn:hover {
                background-color: #FF1C42; /* Change to a different shade or color */
                color: white; /* Optional: Change text color on hover */
            }
        </style>
    </head>
    <div class="full_container">
        <div class="inner_container">
            <%@ include file="sidebar.jsp" %>
            <!-- end sidebar -->
            <!-- right content -->
            <div id="content">
                <%@ include file="topbar.jsp" %>
                <!-- end topbar -->
                <!-- News Detail -->
                <div class="container-fluid mt-5"> 

                    <div class="row">
                        <div class="col-12"> 

                            <div class="news-container">
                                <c:if test="${sessionScope.account.roleId == 2}">
                                    <btn id="btn" class="btn btn-success" onclick="window.location = 'update-news?id=${param.id}';">
                                        Update News
                                    </btn></c:if>
                                <h2 class="news-title" style="color: #004175;">${requestScope.news.title}</h2>
                                <p class="news-date">Date: ${requestScope.news.date}, Post by: ${requestScope.news.staff.name}</p>
                                <p class="news-content">
                                    ${requestScope.news.content}
                                </p>
                                <div class="related-links">
                                    <ul>
                                        <li style="font-weight: bold; text-decoration: underline">Related Articles<li>
                                            <c:forEach items="${requestScope.listOtherNews}" var="n">
                                            <li>- <a href="news-detail?id=${n.id}">${n.title}</a></li>
                                            </c:forEach>
                                        <li><btn id="btn" onclick="window.location = 'view-news';" class="btn btn-success">Back to News List</btn></li>
                                    </ul>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- end news detail -->
            </div>
        </div>
    </div>
    <!-- jQuery -->
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</body>
</html>
