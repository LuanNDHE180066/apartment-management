<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <!-- basic -->
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <!-- mobile metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <meta name="viewport" content="initial-scale=1, maximum-scale=1" />
        <!-- site metas -->
        <title>Apartment Management</title>
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

        <!-- Custom CSS for centering and styling -->
        <style>
            .form-container {
                display: flex;
                flex-direction: column;
                justify-content: center;
                align-items: center;
                height: 100vh; /* Full viewport height */
                text-align: center;
            }
            .form-container h1 {
                font-size: 2.5rem; /* Larger font size */
                color: #333; /* Dark gray color */
                margin-bottom: 20px; /* Space below the header */
            }
            .form-container img {
                max-width: 100%; /* Ensure the image is responsive */
                height: auto; /* Maintain aspect ratio */
            }

            /* Hiệu ứng loading spinner */
            .loading-spinner {
                border: 6px solid #f3f3f3; /* Màu viền nhạt */
                border-top: 6px solid #3498db; /* Màu viền chính */
                border-radius: 50%;
                width: 40px;
                height: 40px;
                animation: spin 1s linear infinite; /* Chạy vô hạn */
                margin: 20px auto; /* Căn giữa và tạo khoảng cách */
                display: block; /* Luôn hiển thị */
            }

            @keyframes spin {
                0% {
                    transform: rotate(0deg);
                }
                100% {
                    transform: rotate(360deg);
                }
            }

            /* Nút Logout */
            .logout-button {
                display: inline-block;
                margin-top: 20px; /* Khoảng cách từ hình ảnh đến nút */
                padding: 10px 20px; /* Kích thước padding */
                font-size: 1rem; /* Cỡ chữ */
                color: #fff; /* Màu chữ */
                background-color: #3498db; /* Màu nền */
                border: none; /* Không viền */
                border-radius: 5px; /* Bo góc */
                text-decoration: none; /* Bỏ gạch chân */
                transition: background-color 0.3s ease; /* Hiệu ứng hover */
            }

            .logout-button:hover {
                background-color: #2980b9; /* Màu nền khi hover */
            }
        </style>
    </head>
    <body class="inner_page tables_page">
        <div class="full_container">
            <div class="inner_container">
                <%@include file="sidebar.jsp" %>
                <div id="content">
                    <%@include file="topbar.jsp" %>
                    <div class="midde_cont">
                        <div class="container-fluid">
                            <div class="form-container">
                                <h1>Đang chờ hệ thống thêm bạn vào căn hộ</h1>
                                <div class="loading-spinner" id="loadingSpinner"></div> <!-- Hiệu ứng loading -->
                                <img src="images/logo/con-bo-sua.jpg" alt="alt"/>
                                <a href="logout" class="logout-button">Logout</a> <!-- Nút Logout -->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- JavaScript -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/custom.js"></script>
        <!-- SheetJS for Excel generation -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.18.5/xlsx.full.min.js"></script>

        <script>
            // Hiển thị hiệu ứng loading
            function showLoading() {
                document.getElementById('loadingSpinner').style.display = 'block';
            }

            // Hiển thị loading và không ẩn đi
            showLoading(); // Hiển thị loading mãi mãi
        </script>
    </body>
</html>