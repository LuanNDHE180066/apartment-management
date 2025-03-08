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
            .news-container {
    background: #ffffff;
    border-radius: 10px;
    padding: 20px;
    box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
}

.news-title {
    font-size: 28px;
    color: #004175;
}

.news-meta {
    font-size: 14px;
    color: #6c757d;
}

.news-image {
    max-width: 100%;
    height: auto;
    border-radius: 10px;
    box-shadow: 2px 2px 10px rgba(0, 0, 0, 0.2);
}

.news-content {
    font-size: 18px;
    line-height: 1.6;
}

.news-source a {
    font-size: 16px;
    text-decoration: none;
    transition: color 0.3s ease;
}

.news-source a:hover {
    color: #ff6600;
}

.related-links h4 {
    color: #333;
}

.list-group-item a {
    text-decoration: none;
    transition: color 0.3s ease;
}

.list-group-item a:hover {
    color: #ff6600;
}

.btn-warning {
    background-color: #f39c12;
    border: none;
}

.btn-primary {
    background-color: #007bff;
    border: none;
}

.btn-primary:hover {
    background-color: #0056b3;
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
                <div class="container mt-5"> 
    <div class="row">
        <div class="col-lg-8 offset-lg-2"> 
            <div class="news-container p-4 rounded shadow-sm bg-white">
                
                <!-- Chỉ admin mới có nút Update -->
                <c:if test="${sessionScope.account.roleId == 2}">
                    <button id="btn-update" class="btn btn-warning mb-3" onclick="window.location = 'update-news?id=${param.id}';">
                        ✏️ Update News
                    </button>
                </c:if>

                <!-- Tiêu đề bài viết -->
                <h1 class="news-title text-dark fw-bold">${requestScope.news.title}</h1>

                <!-- Ngày đăng & tác giả -->
                <p class="news-meta text-muted">
                    <i class="far fa-calendar-alt"></i> ${requestScope.news.date} &nbsp; | &nbsp;
                    <i class="fas fa-user"></i> ${requestScope.news.staff.name}
                </p>

                <!-- Hình ảnh bài viết -->
                <div class="text-center mb-4">
                    <img src="${requestScope.news.image}" class="news-image img-fluid rounded shadow" width="80%"/>
                </div>

                <!-- Nội dung bài viết -->
                <p class="news-content text-justify fs-5">
                    ${requestScope.news.content}
                </p>

                <!-- Nguồn tin -->
                <c:if test="${requestScope.news.source.matches('^(https?|ftp)://.*$')}">
                    <p class="news-source">
                        📌 <a href="${requestScope.news.source}" target="_blank" class="text-primary fw-bold">
                            Read more <i class="fas fa-external-link-alt"></i>
                        </a>
                    </p>
                </c:if>

                <!-- Bài viết liên quan -->
                <div class="related-links mt-4">
                    <h4 class="fw-bold">📰 Related Articles</h4>
                    <ul class="list-group">
                        <c:forEach items="${requestScope.listOtherNews}" var="n">
                            <li class="list-group-item">
                                <a href="news-detail?id=${n.id}" class="text-dark fw-semibold">
                                    ➜ ${n.title}
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>

                <!-- Nút quay lại danh sách -->
                <div class="mt-4 text-center">
                    <button class="btn btn-primary" onclick="window.location = 'view-news';">
                        ⬅️ Back to News List
                    </button>
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
