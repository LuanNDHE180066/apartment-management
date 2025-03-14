<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            /* CSS để làm cho đường kẻ của bảng xám mờ, căn giữa tên cột và màu nền của tiêu đề cột */
            .table th, .table td {
                border: 1px solid rgba(0, 0, 0, 0.2); /* Đặt đường kẻ màu xám mờ */
            }
            .table {
                border-collapse: collapse; /* Đảm bảo không có khoảng cách giữa các đường kẻ */
            }
            .table th {
                text-align: center; /* Căn giữa tên cột */
                background-color: #6B90DA; /* Màu nền cho tiêu đề cột */
                color: black; /* Màu chữ trắng để nổi bật trên nền xanh */
            }
            .hiddenRow {
                padding: 0 !important;
            }
            .table tr.accordion-toggle {
                cursor: pointer;
                background-color: #f9f9f9;
            }
            .accordion-toggle:hover {
                background-color: #e0e0e0;
            }
            .table .card-body {
                background-color: #f0f0f0;
                border: 1px solid #d0d0d0;
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
                justify-content: center; /* Center pagination controls */
                align-items: center;
                margin-top: 10px;
                gap: 10px; /* Space between elements */
            }

            .pagination a {
                display: inline-flex;
                justify-content: center; /* Center text inside */
                align-items: center;
                padding: 10px 20px; /* Adjust padding for text visibility */
                min-width: 120px; /* Ensure button is wide enough */
                height: 45px; /* Ensure enough height */
                text-align: center;
                font-weight: bold;
                border-radius: 5px;
                text-decoration: none;
                white-space: nowrap; /* Prevent text from wrapping */
            }

            .pagination a.btn-primary {
                background-color: #007bff; /* Bootstrap primary color */
                color: white;
                border: none;
            }

            .pagination span {
                font-weight: bold;
            }
            /* Remove all table borders */
            .table, .table th, .table td {
                border: none;
            }

            /* Table header styling */
            .table th {
                text-align: center;
                background-color: #6B90DA;
                color: black;
            }

            /* First row (author row) blue background with rounded corners */
            .accordion-toggle {

                color: #005cbf;
                font-weight: bold;
                border-radius: 10px;  /* Apply border-radius */
                padding: 12px;
                overflow: hidden;
            }

            /* Round left & right corners for first and last cells */
            .accordion-toggle td:first-child {
                border-top-left-radius: 20px;
                border-bottom-left-radius: 20px;
            }

            .accordion-toggle td:last-child {
                border-top-right-radius: 20px;
                border-bottom-right-radius: 20px;
            }

            /* Remove weird gaps */
            .accordion-toggle td {
                padding: 15px;
            }

            /* Add spacing between rows */
            .table tbody tr {
                margin-bottom: 10px;

            }

            /* Details row white background */
            .table .card-body {
                background-color: white;
                padding: 15px;
                border-radius: 8px;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
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

                            <div class="row">
                                <!-- Feedback Information Table -->
                                <div class="col-md-12">
                                    <div class="white_shd full margin_bottom_30">
                                        <div class="full graph_head d-flex justify-content-between align-items-center">

                                            <div class="heading1 ">
                                                <h2>Feedback Table</h2>
                                            </div>


                                            <div>
                                                <button class="btn btn-primary" onclick="location.href = 'sendfeedback'" style="margin-right: 20px">Send New Feedback</button>
                                                <button class="btn btn-primary" onclick="location.href = 'view-all-feedback'">View All</button>
                                            </div>
                                        </div>
                                        <div class="table_section padding_infor_info">
                                            <div style="margin-left: 40px; margin-bottom: 30px;">
                                                <form action="filterfeedback" method="get">
                                                    <div class="row align-items-center">
                                                        <div class="col-md-2">
                                                            <label for="startDate" class="fw-bold">Start Date</label>
                                                            <input type="date" class="form-control" name="from" value="${sessionScope.from}">
                                                        </div>
                                                        <div class="col-md-2">
                                                            <label for="endDate" class="fw-bold">End Date</label>
                                                            <input type="date" class="form-control" name="to" value="${sessionScope.to}">
                                                        </div>
                                                        <div class="col-md-2">
                                                            <label class="fw-bold">Service Type</label>
                                                            <select class="form-control" name="typeRequest">
                                                                <option value="" ${empty sessionScope.selectedType ? 'selected' : ''}>--Select Type--</option>
                                                                <c:forEach items="${requestScope.listTypeRequest}" var="t">
                                                                    <option value="${t.id}" ${sessionScope.selectedType == t.id ? 'selected' : ''}>${t.name}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </div>
                                                        <div class="col-md-4 d-flex">
                                                            <button type="submit" class="btn btn-primary" style="margin-right: 5px;">Search</button>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                            <div class="table-responsive-sm">
                                                <table class="table w-100">
                                                    <thead>
                                                        <tr>
                                                            <th style="width: 20%;">Tên dịch vụ</th>
                                                            <th style="width: 10%;">Thời Gian</th>
                                                            <th style="width: 10%;">Mức độ hài lòng</th>
                                                            <th style="width: 10%;" class="action-column">Hành động</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach items="${requestScope.listFeedbackU}" var="feedback" varStatus="loop">
                                                            <!-- First row: Author with blue background and rounded corners -->
                                                            <tr class="accordion-toggle" data-target="#feedbackDetail${loop.index}">
                                                                <td>${feedback.requestType.name}</td>
                                                                <c:set var="util" value="${util}" />
                                                                <td>${util.FormatDateTime(feedback.date)}</td>
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
                                                                    <c:set var="showButton" value="${util.compareFeedbackDateToCurrentTime(feedback.date, 3)}"/>
                                                                    <c:if test="${showButton}">
                                                                        <div class="dropdown-content">
                                                                            <a href="update-feed-back?id=${feedback.id}">✏ Edit</a>
                                                                            <a href="deletefeedback?id=${feedback.id}" onclick="return confirm('Are you sure to delete this feedback?')">🗑 Delete</a>
                                                                        </div>
                                                                    </c:if>
                                                                </td>
                                                            </tr>

                                                            <!-- Second row: Details with white background -->
                                                            <tr id="feedbackDetail${loop.index}" style="display: none;">
                                                                <td colspan="4">
                                                                    <div class="card card-body">
                                                                        <p><span style="font-weight: bold">Chi tiết:</span> ${feedback.detail}</p>
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
                                            <div class="pagination">
                                                <c:if test="${currentPage > 1}">
                                                    <a href="?page=${currentPage - 1}" class="btn btn-primary">Previous</a>
                                                </c:if>
                                                <span>Page ${currentPage} of ${totalPages}</span>
                                                <c:if test="${currentPage < totalPages}">
                                                    <a href="?page=${currentPage + 1}" class="btn btn-primary">Next</a>
                                                </c:if>
                                            </div>

                                            <!-- Modal for Image Inspection -->
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

                                                                                    // Handle image modal display
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