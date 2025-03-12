<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" import="util.Util,model.Feedback"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
        <style>
            /* CSS để làm cho đường kẻ của bảng xám mờ, căn giữa tên cột và màu nền của tiêu đề cột */
            .table th, .table td {
                border: none;
            }
            .table {
                border-collapse: collapse;
            }
            .table th {
                text-align: center;
                background-color: #6B90DA;
                color: black;
            }
            .hiddenRow {
                padding: 0 !important;
            }
            .table tr.accordion-toggle {
                cursor: pointer;
                background-color: #f9f9f9;
                color: #005cbf;
                font-weight: bold;
                border-radius: 10px;
                padding: 12px;
                overflow: hidden;
            }
            .accordion-toggle:hover {
                background-color: #e0e0e0;
            }
            .accordion-toggle td:first-child {
                border-top-left-radius: 20px;
                border-bottom-left-radius: 20px;
            }
            .accordion-toggle td:last-child {
                border-top-right-radius: 20px;
                border-bottom-right-radius: 20px;
            }
            .accordion-toggle td {
                padding: 15px;
            }
            .table tbody tr {
                margin-bottom: 10px;
            }
            .table .card-body {
                background-color: white;
                padding: 15px;
                border-radius: 8px;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            }
            .image-gallery {
                display: flex;
                flex-wrap: wrap;
                gap: 10px;
                margin-top: 10px;
            }
            .feedback-img {
                width: 250px;
                height: 250px;
                object-fit: cover;
                border-radius: 5px;
                box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.2);
                transition: transform 0.2s;
                cursor: pointer;
            }
            .feedback-img:hover {
                transform: scale(1.1);
            }
            .pagination {
                display: flex;
                justify-content: center;
                align-items: center;
                margin-top: 10px;
                gap: 10px;
            }
            .pagination a {
                display: inline-flex;
                justify-content: center;
                align-items: center;
                padding: 10px 20px;
                min-width: 120px;
                height: 45px;
                text-align: center;
                font-weight: bold;
                border-radius: 5px;
                text-decoration: none;
                white-space: nowrap;
            }
            .pagination a.btn-primary {
                background-color: #007bff;
                color: white;
                border: none;
            }
            .pagination span {
                font-weight: bold;
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
                            <div class="row column_title">
                                <div class="col-md-12">
                                    <div class="page_title">
                                        <h2>Feedback Information</h2>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-12">
                                    <div class="white_shd full margin_bottom_30">
                                        <div class="full graph_head d-flex justify-content-between align-items-center">
                                            <div class="heading1 margin_0">
                                                <h2>Feedback Table</h2>
                                            </div>

                                        </div>

                                        <!-- Filter Form -->
                                        <div style="margin-left: 40px;">
                                            <form action="view-all-feedback" method="GET">
                                                <div class="row align-items-center">
                                                    <div class="col-md-2">
                                                        <div class="form-group text-center">
                                                            <label for="searchName" class="fw-bold">Title</label>
                                                            <input type="text" value="${param.searchName}" class="form-control" name="searchName" placeholder="Search by Name">
                                                        </div>
                                                    </div>
                                                    <div class="col-md-2">
                                                        <div class="form-group text-center">
                                                            <label for="startDate" class="fw-bold">Start Date</label>
                                                            <input type="date" class="form-control" name="startDate" value="${param.startDate}">
                                                        </div>
                                                    </div>
                                                    <div class="col-md-2">
                                                        <div class="form-group text-center">
                                                            <label for="endDate" class="fw-bold">End Date</label>
                                                            <input type="date" class="form-control" name="endDate" value="${param.endDate}">
                                                        </div>
                                                    </div>
                                                    <div class="col-md-2">
                                                        <div class="form-group text-center">
                                                            <label class="fw-bold">Service Type</label>
                                                            <select class="form-control" name="serviceType">
                                                                <option value="">Select Service Type</option>
                                                                <c:forEach items="${sessionScope.listRequestType}" var="service">
                                                                    <option value="${service.id}" ${service.id == param.serviceType ? 'selected' : ''}>
                                                                        ${service.name}
                                                                    </option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class=" text-center mt-3">
                                                        <button type="submit" class="btn btn-primary">Filter</button>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>


                                        <!-- Feedback Table -->
                                        <div class="table_section padding_infor_info">
                                            <div class="table-responsive-sm">
                                                <table class="table w-100">
                                                    <thead>
                                                        <tr>
                                                            <th style="width: 15%;">Người tạo đơn</th>
                                                            <th style="width: 20%;">Tên dịch vụ</th>
                                                            <th style="width: 10%;">Thời Gian</th>
                                                            <th style="width: 10%;">Mức độ hài lòng</th>
                                                            <th style="width: 10%;">Hành động</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                    <h3>${requestScope.message}</h3>
                                                    <c:forEach items="${sessionScope.listFeedback}" var="feedback" varStatus="loop">
                                                        <tr class="accordion-toggle" data-target="#feedbackDetail${loop.index}">
                                                            <td>${feedback.resident.name}</td>
                                                            <td>${feedback.requestType.name}</td>
                                                            <td>${feedback.date}</td>
                                                            <td>
                                                                <c:choose>
                                                                    <c:when test="${feedback.rate == 5}">Rất hài lòng ⭐⭐⭐⭐⭐</c:when>
                                                                    <c:when test="${feedback.rate == 4}">Hài lòng ⭐⭐⭐⭐</c:when>
                                                                    <c:when test="${feedback.rate == 3}">Bình thường ⭐⭐⭐</c:when>
                                                                    <c:when test="${feedback.rate == 2}">Không hài lòng ⭐⭐</c:when>
                                                                    <c:when test="${feedback.rate == 1}">Rất tệ ⭐</c:when>
                                                                    <c:otherwise>Chưa đánh giá</c:otherwise>
                                                                </c:choose>
                                                            </td>
                                                            <td class="action-column">
                                                                <%
                                                                   
                                                                    Object feedbackObj = pageContext.getAttribute("feedback");
                                                                    String feedbackDate = null;
                                                                    if (feedbackObj != null) {
                                                                        model.Feedback feedback = (model.Feedback) feedbackObj; 
                                                                        feedbackDate = feedback.getDate(); 
                                                                    }

                                                                    Util u = new Util();
                                                                    boolean showButton = u.compareFeedbackDateToCurrentTime(feedbackDate,1);   
                                                                           
                                                                    if (showButton) {
                                                                %>
                                                                <a href="request-update-feedback?id=${feedback.id}" 
                                                                   class="btn btn-primary request-update-btn">
                                                                    Yêu cầu cập nhật
                                                                </a>
                                                                <%
                                                                    }
                                                                %>
                                                            </td>


                                                        </tr>

                                                        <!-- Feedback Detail Row -->
                                                        <tr id="feedbackDetail${loop.index}" style="display: none;">
                                                            <td colspan="5">
                                                                <div class="card card-body">
                                                                    <p><strong>Chi tiết:</strong> ${feedback.detail}</p>
                                                                    <c:if test="${not empty feedback.img}">
                                                                        <div class="image-gallery">
                                                                            <c:forEach var="imageUrl" items="${feedback.img}">
                                                                                <img src="${imageUrl}" alt="Feedback Image" class="feedback-img" data-image="${imageUrl}">
                                                                            </c:forEach>
                                                                        </div>
                                                                    </c:if>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <div class="pagination" style="display: flex; align-items: center; gap: 10px;">
                                                <form id="paginationForm" method="get" action="view-all-feedback" style="display: flex; align-items: center; gap: 10px;">

                                                    <!-- Previous Button -->
                                                    <button type="button" onclick="changePage(${currentPage - 1})"
                                                            style="padding: 6px 12px; font-size: 14px; border: 1px solid #ddd; border-radius: 4px; cursor: pointer;"
                                                            ${currentPage == 1 ? 'disabled' : ''}>
                                                        Previous
                                                    </button>

                                                    <label for="page" style="font-size: 14px; font-weight: bold;">Page:</label>
                                                    <select id="page" name="page" onchange="document.getElementById('paginationForm').submit()" 
                                                            style="padding: 6px 12px; font-size: 14px; border: 1px solid #ddd; border-radius: 4px; cursor: pointer;">
                                                        <c:forEach begin="1" end="${requestScope.totalPage}" var="page">
                                                            <option value="${page}" <c:if test="${page == requestScope.currentPage}">selected</c:if>>${page}</option>
                                                        </c:forEach>
                                                    </select>

                                                    <!-- Next Button -->
                                                    <button type="button" onclick="changePage(${currentPage + 1})"
                                                            style="padding: 6px 12px; font-size: 14px; border: 1px solid #ddd; border-radius: 4px; cursor: pointer;"
                                                            ${currentPage >= totalPage ? 'disabled' : ''}>
                                                        Next
                                                    </button>

                                                    <!-- Hidden Inputs for Filters -->
                                                    <input type="hidden" name="searchName" value="${param.searchName}">
                                                    <input type="hidden" name="startDate" value="${param.startDate}">
                                                    <input type="hidden" name="endDate" value="${param.endDate}">
                                                    <input type="hidden" name="serviceType" value="${param.serviceType}">
                                                </form>

                                                <!-- Display Total Pages -->
                                                <span style="font-size: 14px; font-weight: bold;">Total Pages: ${totalPage}</span>
                                            </div>


                                            <!-- Pagination -->



                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!-- Footer -->
                            <div class="modal fade" id="imageModal" tabindex="-1" role="dialog" aria-labelledby="imageModalLabel" aria-hidden="false">
                                <div class="modal-dialog modal-lg" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="imageModalLabel">Feedback Image</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body text-center">
                                            <img id="modalImage" src="" alt="Feedback Image" style="max-width: 100%; height: auto;">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- jQuery & Bootstrap -->
                <script src="js/jquery.min.js"></script>
                <script src="js/popper.min.js"></script>
                <script src="js/bootstrap.min.js"></script>
                <script src="js/custom.js"></script>
                <!-- jQuery and Bootstrap (Include these before your script) -->
                <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
                <script>
                                                        function changePage(newPage) {
                                                            let pageSelect = document.getElementById("page");
                                                            if (newPage >= 1 && newPage <= ${totalPage}) {
                                                                pageSelect.value = newPage;  // Update the dropdown selection
                                                                document.getElementById("paginationForm").submit();  // Submit form
                                                            }
                                                        }

                </script>
                <script>
                    document.addEventListener("DOMContentLoaded", function () {
                        document.querySelectorAll(".request-update-btn").forEach(function (button) {
                            button.addEventListener("click", function () {
                                let feedbackId = this.getAttribute("data-feedback-id");

                                // Show confirmation dialog
                                let confirmUpdate = confirm("Bạn có chắc chắn muốn yêu cầu cư dân cập nhật phản hồi không?");
                                if (confirmUpdate) {
                                    // Redirect to the request update feedback URL
                                    window.location.href = "request-update-feedback?id=" + feedbackId;
                                }
                            });
                        });
                    });
                </script>



                <!-- Toggle Details & Image Modal -->
                <script>
                    document.addEventListener("DOMContentLoaded", function () {
                        document.querySelectorAll(".accordion-toggle").forEach(function (row) {
                            row.addEventListener("click", function () {
                                let targetId = this.getAttribute("data-target");
                                let targetElement = document.querySelector(targetId);

                                if (targetElement.style.display === "none" || targetElement.style.display === "") {
                                    targetElement.style.display = "table-row";
                                } else {
                                    targetElement.style.display = "none";
                                }
                            });
                        });

                        document.querySelectorAll(".feedback-img").forEach(function (img) {
                            img.addEventListener("click", function () {
                                document.getElementById("modalImage").src = this.getAttribute("data-image");
                                $('#imageModal').modal('show');
                            });
                        });
                    });
                </script>
                </body>

                </html>