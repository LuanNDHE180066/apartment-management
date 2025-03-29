<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <!-- basic -->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- mobile metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Contract Detail</title>
        <!-- bootstrap css -->
        <link rel="stylesheet" href="css/bootstrap.min.css" />
        <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
        <style>
            .contract-container {
                background: #ffffff;
                padding: 40px;
                border-radius: 10px;
                box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
                margin: 50px auto;
                max-width: 900px;
                text-align: center;
            }

            /* Định dạng tiêu đề */
            .contract-title {
                color: #007bff;
                font-size: 36px;
                font-weight: bold;
                text-align: center;
                margin-bottom: 30px;
            }

            /* Dàn layout chi tiết hợp đồng + ảnh */
            .contract-content {
                display: flex;
                justify-content: space-between;
                align-items: flex-start;
                gap: 20px;
            }

            /* Card chi tiết hợp đồng */
            .contract-details {
                flex: 1;
                text-align: left;
                padding: 20px;
                border-radius: 10px;
                background: #f9f9f9;
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            }

            /* Hình ảnh */
            .contract-image {
                flex: 1;
                text-align: center;
            }

            .contract-image img {
                max-width: 100%;
                height: auto;
                border-radius: 10px;
                box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
                cursor: pointer;
                transition: transform 0.3s ease-in-out;
            }

            .contract-image img:hover {
                transform: scale(1.05);
            }

            /* Nút Back */
            .back-button {
                text-align: center;
                margin-top: 30px;
            }

            #btn {
                background-color: #007bff;
                color: white;
                border: none;
                padding: 10px 20px;
                border-radius: 5px;
                font-size: 18px;
                transition: background-color 0.3s ease;
            }

            #btn:hover {
                background-color: #0056b3;
            }
            .modal-body {
                text-align: center;
                padding: 0;
            }

            .modal-body img {
                width: 100%; /* Đảm bảo ảnh chiếm toàn bộ modal */
                max-width: 900px; /* Giới hạn kích thước tối đa */
                height: auto;
                display: block;
                margin: auto;
                border-radius: 10px;
            }

        </style>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <%@ include file="sidebar.jsp" %>
                <!-- end sidebar -->
                <div id="content" class="col-md-12">
                    <%@ include file="topbar.jsp" %>
                    <!-- end topbar -->
                    <div class="contract-container">
                        <h2 class="contract-title">${requestScope.contract.title}</h2>

                        <div class="contract-content">
                            <!-- Contract Details -->
                            <div class="contract-details">
                                <h3>Thông tin hợp đồng</h3>
                                <p><strong>Người làm:</strong> ${requestScope.contract.staff.name}</p>
                                <p><strong>Quản lý:</strong> ${requestScope.contract.admin.name}</p>
                                <p><strong>Kế toán:</strong> ${requestScope.contract.accountant.name}</p>
                                <p><strong>Công ty:</strong> ${requestScope.contract.company.name}</p>
                                <p><strong>Ngày ký:</strong> ${requestScope.contract.formatSigndate()}</p>
                                <p><strong>Ngày bắt đầu:</strong> ${requestScope.contract.formatStartdate()}</p>
                                <p><strong>Ngày kết thúc:</strong> ${requestScope.contract.formatEnddate()}</p>
                                <p><strong>Mô tả:</strong> ${requestScope.contract.description}</p>
                            </div>

                            <!-- Contract Image -->
                            <div class="contract-image">
                                <p><strong>Ảnh:</strong></p>
                                <c:if test="${not empty requestScope.listimg}">
                                    <!-- Hiển thị ảnh đầu tiên làm ảnh đại diện -->
                                    <img src="${requestScope.listimg[0]}" alt="Contract Image" id="previewImage" 
                                         data-toggle="modal" data-target="#imageModal"
                                         style="width: 250px; height: auto; margin: 5px; cursor: pointer;" />
                                </c:if>
                            </div>

                            <!-- Modal hiển thị toàn bộ ảnh -->
                            <div class="modal fade" id="imageModal" tabindex="-1" role="dialog" aria-labelledby="imageModalLabel" aria-hidden="true">
                                <div class="modal-dialog modal-lg" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="imageModalLabel">Contract Images</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body text-center">
                                            <c:forEach var="imgPath" items="${requestScope.listimg}">
                                                <img src="${imgPath}" alt="Contract Image" class="modal-image"
                                                     style="width: 100%; max-width: 600px; margin-bottom: 10px;" />
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>

                        <div class="back-button">
                            <button id="btn" onclick="window.location = 'view-all-contract';">Trở lại</button>
                        </div>
                    </div>

                </div>
            </div>
        </div>

        <!-- Modal -->
<!--        <div class="modal fade" id="imageModal" tabindex="-1" role="dialog" aria-labelledby="imageModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="imageModalLabel">Contract Image</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <c:forEach var="imgPath" items="${requestScope.listimg}">
                            <img src="${imgPath}" alt="Contract Image" style="width: 100%; height: auto; margin-bottom: 10px;"/>
                        </c:forEach>
                    </div>

                </div>
            </div>
        </div>-->

        <!-- jQuery -->
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>