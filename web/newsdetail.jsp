<%-- 
    Document   : newjsp
    Created on : Feb 1, 2025, 9:56:13 PM
    Author     : quang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
        /* Thiết lập font chữ và nền */
        body {
            font-family: 'Merriweather', serif;
            background-color: #f8f9fa;
        }

        /* Container chính của bài báo */
        .news-container {
            background: #fff;
            border-radius: 8px;
            padding: 30px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            margin-top: 30px;
            max-width: 800px;
            margin-left: auto;
            margin-right: auto;
        }

        /* Tiêu đề bài viết */
        .news-title {
            font-size: 34px;
            font-weight: bold;
            color: #333;
            text-align: center;
            margin-bottom: 10px;
        }

        /* Thông tin ngày đăng và tác giả */
        .news-meta {
            font-size: 14px;
            color: #888;
            text-align: center;
            margin-bottom: 20px;
        }

        /* Nội dung bài viết */
        .news-content {
            font-size: 18px;
            line-height: 1.8;
            color: #333;
            text-align: justify;
        }

        /* Định dạng ảnh trong nội dung */
        .news-content img {
            max-width: 100%;
            height: auto;
            display: block;
            margin: 20px auto;
            border-radius: 5px;
        }

        /* Trích dẫn đặc biệt */
        blockquote {
            font-style: italic;
            border-left: 4px solid #007bff;
            padding-left: 15px;
            margin: 20px 0;
            color: #555;
        }

        /* Link nguồn tin */
        .news-source a {
            font-size: 16px;
            font-weight: bold;
            color: #007bff;
            text-decoration: none;
        }

        .news-source a:hover {
            text-decoration: underline;
        }

        /* Tiêu đề danh sách bài viết liên quan */
        .related-links h4 {
            font-size: 20px;
            margin-top: 30px;
            font-weight: bold;
        }

        /* Danh sách bài viết liên quan */
        .list-group-item a {
            text-decoration: none;
            color: #333;
            font-size: 16px;
        }

        .list-group-item a:hover {
            color: #ff6600;
        }

        /* Nút quay lại */
        .btn-primary {
            background-color: #007bff;
            border: none;
            padding: 10px 15px;
            font-size: 16px;
            font-weight: bold;
        }

        .btn-primary:hover {
            background-color: #0056b3;
        }
        .news-category{
            text-align: center;
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
                <div class="container">
        <div class="news-container">

            <!-- Chỉ admin mới có nút Update -->
            <c:if test="${sessionScope.account.roleId == 2}">
                <button class="btn btn-warning mb-3 float-end" onclick="window.location = 'update-news?id=${param.id}';">
                    ✏️ Cập nhật
                </button>
            </c:if>

            <!-- Tiêu đề bài viết -->
            <h1 class="news-title">${requestScope.news.title}</h1>
            
            <!-- Ngày đăng & tác giả -->
            
            <h5><p class="news-meta">
                🕒 ${requestScope.news.formatdate()} | ✍ ${requestScope.news.staff.name}
            </p></h5>
            <h5 class="news-category">${requestScope.news.category}</h5>

            <!-- Nội dung bài viết (có thể chứa ảnh trong content) -->
            <div class="news-content">
                ${requestScope.news.content}
            </div>

            <!-- Trích dẫn đặc biệt (nếu có) -->

            <!-- Nguồn tin -->
            <c:if test="${requestScope.news.source.matches('^(https?|ftp)://.*$')}">
                <p class="news-source">
                    📌 <a href="${requestScope.news.source}" target="_blank">
                        Đọc thêm <i class="fas fa-external-link-alt"></i>
                    </a>
                </p>
            </c:if>

            <!-- Bài viết liên quan -->
            <div class="related-links">
                <h4>📖 Tin gần đây</h4>
                <ul class="list-group">
                    <c:forEach items="${requestScope.listOtherNews}" var="n">
                        <li class="list-group-item">
                            <a href="news-detail?id=${n.id}">
                                ➜ ${n.title}
                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </div>

            <!-- Nút quay lại danh sách -->
            <div class="mt-4 text-center">
                <button class="btn btn-primary" onclick="window.location = 'view-news';">
                    ⬅️ Trở lại
                </button>
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
