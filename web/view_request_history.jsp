<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
        <meta name="keywords" content="">
        <meta name="description" content="">
        <meta name="author" content="">
        <!-- site icon -->
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
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
        <style>
            .table th, .table td {
                border: 1px solid rgba(0, 0, 0, 0.2);
                text-align: center;
            }
        </style>
    </head>
    <body class="dashboard dashboard_1">
        <div class="full_container">
            <div class="inner_container">
                <!-- Sidebar  -->
                <%@include file="sidebar.jsp" %>
                <!-- end sidebar -->
                <!-- right content -->
                <div id="content">
                    <!-- topbar -->
                    <%@include file="topbar.jsp" %>
                    <div class="midde_cont">
                        <div class="container-fluid">
                            <div class="row">
                                <!-- table section -->
                                <div class="col-md-12">
                                    <div class="white_shd full margin_bottom_30">
                                        <div class="full graph_head">
                                            <div class="heading1 margin_0">
                                                <h2>Thông tin lịch sử yêu cầu</h2>
                                            </div>
                                        </div>
                                        <div style="margin-left: 40px;">
                                            <form action="viewrequest_history" method="post">
                                                <div class="row align-items-center">
                                                    <div class="col-md-2">
                                                        <label for="fromDate">Từ:</label>
                                                        <input type="date" class="form-control" id="fromDate" name="from" placeholder="From" value="${requestScope.from}">
                                                    </div>
                                                    <div class="col-md-2">
                                                        <label for="toDate">Đến:</label>
                                                        <input type="date" class="form-control" id="toDate" name="to" placeholder="To" value="${requestScope.to}">
                                                    </div>
                                                    <div class="col-md-2">
                                                        <label for="typeRequest">Loại:</label>
                                                        <select class="form-control" id="typeRequest" name="typeRequest">
                                                            <option value="">Chọn loại yêu cầu</option>
                                                            <c:forEach items="${requestScope.listType}" var="t">
                                                                <option value="${t.id}" <c:if test="${requestScope.selectedType == t.id}">selected</c:if>>${t.name}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="col-md-4 d-flex align-items-end">
                                                        <button type="submit" class="btn btn-primary" style="margin-right: 5px;">Lọc</button>
                                                        <a href="resident-add-request"  class="btn btn-primary">Tạo yêu cầu</a>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                        <div class="table_section padding_infor_info">
                                            <div class="table-responsive-sm">
                                                <table class="table w-100">
                                                    <thead>
                                                        <tr>
                                                            <th>Căn hộ</th>
                                                            <th>Ngày tạo</th>
                                                            <th>Ngày trả lời</th>
                                                            <th>Loại</th>
                                                            <th>Người thực hiện</th>
                                                            <th>Thông tin</th>
                                                            <th>Trạng thái</th>
                                                            <th>Lựa chọn</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach items="${listRequest}" var="l">
                                                            <tr>
                                                                <td style="text-align: left">${l.aid.id}</td>
                                                                <td style="text-align: left">${l.date}</td>
                                                                <td style="text-align: left">
                                                                    <c:choose>
                                                                        <c:when test="${l.responseDate == null}">Chưa phản hồi</c:when>
                                                                        <c:otherwise>${l.responseDate}</c:otherwise>
                                                                    </c:choose>
                                                                </td>
                                                                <td style="text-align: left">${l.requestType.name}</td>
                                                                <td style="text-align: left">
                                                                    <c:choose>
                                                                        <c:when test="${l.staffId.name == null}">
                                                                            <span style="color: orange;">Đợi</span>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <span style="color: green;">${l.staffId.name}</span>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </td>
                                                                <td style="text-align: left">${l.detail}</td>
                                                                <td style="text-align: left">
                                                                    <c:choose>
                                                                        <c:when test="${l.status == 'Đang làm'}">
                                                                            <span style="color: blue;">Đang làm</span>
                                                                        </c:when>
                                                                        <c:when test="${l.status == 'Xong'}">
                                                                            <span style="color: green;">Xong</span>
                                                                        </c:when>
                                                                        <c:when test="${l.status == 'Đợi'}">
                                                                            <span style="color: red;">Đợi</span>
                                                                        </c:when>
                                                                    </c:choose>
                                                                </td>
                                                                <td style="text-align: left">
                                                                    <c:if test="${l.status == 'Đợi'}">
                                                                        <a href="delete-request-resident?id=${l.id}" onclick="return confirm('Are you sure to delete this request?')"><i class="fa-solid fa-trash"></i></a>
                                                                    </c:if>
                                                                    <c:if test="${l.status == 'Đợi'}">
                                                                        <a style="margin-left: 3px" href="update-request?id=${l.id}"><i class="fa-solid fa-pen-to-square"></i></a>
                                                                    </c:if>
                                                                </td>
                                                            </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                        <!-- Pagination Controls -->
                                    </div>
                                </div>
                                <!-- Pagination Controls -->
                                <form method="get" action="viewrequest_history" style="display: flex; align-items: center; gap: 10px;">
                                    <label for="page" style="font-size: 14px; font-weight: bold;">Trang:</label>
                                    <select id="page" name="page" onchange="this.form.submit()" 
                                            style="padding: 6px 12px; font-size: 14px; border: 1px solid #ddd; border-radius: 4px; cursor: pointer;">
                                        <c:forEach begin="1" end="${requestScope.totalPage}" var="page">
                                            <option value="${page}" <c:if test="${page == requestScope.currentPage}">selected</c:if>>
                                                ${page}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </form> 

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- jQuery -->
        <script src="js/jquery.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/custom.js"></script>
    </body>
</html>