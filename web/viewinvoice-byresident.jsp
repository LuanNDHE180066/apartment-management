<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
            td{
                color: black;
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
                            <div class="row column_title">
                                <div class="col-md-12">
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="white_shd full margin_bottom_30">
                                        <div class="full graph_head d-flex justify-content-between align-items-center">
                                            <div class="heading1 margin_0">
                                                <h2>Thông tin tiêu dùng</h2>
                                            </div>
                                        </div>
                                        <div class="table_section padding_infor_info">
                                            <!--                                            <div style="margin-left: 40px; margin-bottom: 30px; display: flex; align-items: center; gap: 10px;">
                                                                                            <form action="view-service-resident" method="post" style="display: flex; align-items: center; gap: 10px;">
                                                                                                <label for="idapartment" style="font-weight: bold;">Chung cư số:</label>
                                                                                                <select onchange="this.form.submit()" id="idapartment" name="idapartment" 
                                                                                                        style="padding: 5px; border: 1px solid #ccc; border-radius: 4px; font-size: 14px; width: 200px;">
                                            <c:forEach items="${requestScope.owned}" var="item">
                                                <option ${requestScope.aid==item.id? 'selected':'' } value="${item.id}">${item.id}</option>
                                            </c:forEach>
                                        </select>
                                    </form>
                                </div>-->


                                            <div class="table-responsive-sm">
                                                <div style="width: 100%">
                                                    <h3 style="margin-bottom: 20px; font-size: 24px; font-weight: bold; color: #2c3e50; display: inline-block; border-bottom: 3px solid #3498db; padding-bottom: 5px;">
                                                        Hóa đơn chưa thanh toán
                                                    </h3>

                                                    <table class="table w-100">
                                                        <thead>
                                                            <tr>
                                                                <th style="width: 5%;">ID</th>
                                                                <th style="width: 15%;">Total</th>
                                                                <th style="width: 15%;">Loan Date</th>
                                                                <th style="width: 15%;">Due Date</th>
                                                                <th style="width: 15%;">Description</th>
                                                                <th style="width: 10%;">Room</th>
                                                                <th style="width: 5%;"> Action</th>
                                                                <th style="width: 10%;">Thanh Toán</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:forEach items="${requestScope.invoiceNotPaidList}" var="item">
                                                                <c:if test="${item.status==0}">
                                                                    <tr>
                                                                        <td style="text-align: center">${item.id}</td>
                                                                        <td style="text-align: right">
                                                                            <fmt:formatNumber value="${item.total}" type="currency" currencyCode="VND" maxFractionDigits="0"/>
                                                                        </td> 
                                                                        <td style="text-align: center">${item.invoiceDateFormat}</td>
                                                                        <td style="text-align: center">${item.dueDateFormat}</td>
                                                                        <td style="text-align: left">${item.description}</td>
                                                                        <td style="text-align: center">${item.apartment.id}</td>
                                                                        <td style="text-align: center">
                                                                            <button class="fa fa-plus" style="background: none; border: none; cursor: pointer; font-size: 16px;"></button>
                                                                        </td>
                                                                        <td style="text-align: center;">
                                                                            <button style="
                                                                                    font-size: smaller;
                                                                                    background-color: #007bff;
                                                                                    border: 1px solid #007bff;
                                                                                    padding: 5px 10px;
                                                                                    border-radius: 3px;
                                                                                    cursor: pointer;
                                                                                    ">
                                                                                <a href="pay-invoice-resident?invoiceId=${item.id}&total=${item.total}" style="color: white; text-decoration: none;">Thanh toán</a>
                                                                            </button>
                                                                        </td>

                                                                    </tr>
                                                                    <tr class="detail-row" style="display: none; background-color: #f9f9f9;">
                                                                        <td colspan="8" style="padding: 15px; border-top: 2px solid #ddd;">
                                                                            <p style="font-size: 16px; font-weight: bold; margin-bottom: 10px;">Chi tiết hóa đơn #${item.id}</p>
                                                                            <table style="width: 100%; border-collapse: collapse; background: white; border-radius: 5px; box-shadow: 0px 2px 5px rgba(0,0,0,0.1);">
                                                                                <thead>
                                                                                    <tr style="background: #ddd; color: white; text-align: left;">
                                                                                        <th style="padding: 8px; border: 1px solid #ddd;background: #ddd">Dịch vụ</th>
                                                                                        <th style="padding: 8px; border: 1px solid #ddd;background: #ddd">Số lượng</th>
                                                                                        <th style="padding: 8px; border: 1px solid #ddd;background: #ddd">Đơn vị</th>
                                                                                        <th style="padding: 8px; border: 1px solid #ddd;background: #ddd">Chi phí</th>
                                                                                        <th style="padding: 8px; border: 1px solid #ddd;background: #ddd">Date</th>
                                                                                    </tr>
                                                                                </thead>
                                                                                <tbody>
                                                                                    <c:forEach items="${item.invoiceDetail}" var="detail">
                                                                                        <tr style="border-bottom: 1px solid #ddd;">
                                                                                            <td style="padding: 8px; border: 1px solid #ddd; text-align: left">${detail.serviceName}</td>
                                                                                            <td style="padding: 8px; border: 1px solid #ddd; text-align: center">${detail.quantity}</td>
                                                                                            <td style="padding: 8px; border: 1px solid #ddd; text-align: right;">
                                                                                                <fmt:formatNumber type="currency" currencyCode="VND" value="${detail.unitPrice}" maxFractionDigits="0"/>
                                                                                            </td>
                                                                                            <td style="padding: 8px; border: 1px solid #ddd; text-align: right;">
                                                                                                <fmt:formatNumber type="currency" currencyCode="VND" maxFractionDigits="0" value="${detail.amount}"/>
                                                                                            </td>
                                                                                            <td style="padding: 8px; border: 1px solid #ddd; text-align: center;">${detail.dateFormat}</td>
                                                                                        </tr>
                                                                                    </c:forEach>
                                                                                </tbody>
                                                                            </table>
                                                                        </td>
                                                                    </tr>
                                                                </c:if>
                                                            </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>
                                                <div style="width: 100%">
                                                    <div>
                                                        <h3 style="margin-bottom: 20px; font-size: 24px; font-weight: bold; color: #2c3e50; display: inline-block; border-bottom: 3px solid #3498db; padding-bottom: 5px;">
                                                            Hóa đơn đã thanh toán
                                                        </h3>
                                                    </div>
                                                    <div style="display: flex; align-items: center; gap: 10px; margin-bottom: 20px;">
                                                        <form action="view-invoice-resident" method="get" style="display: flex; align-items: center; gap: 10px;">
                                                            <input type="date" value="${requestScope.usingFrom}" name="from" style="height: 42px; padding: 5px 10px; border-radius: 5px; border: 1px solid #ccc;">
                                                            <input type="date" value="${requestScope.usingTo}" name="to" style="height: 42px; padding: 5px 10px; border-radius: 5px; border: 1px solid #ccc;">
                                                            <button type="submit" 
                                                                    style="height: 42px; padding: 5px 15px; border: none; background: #007bff; color: white; border-radius: 5px; cursor: pointer;">
                                                                Find
                                                            </button>
                                                        </form>
                                                    </div>
                                                    <table class="table w-100">
                                                        <thead>
                                                            <tr>
                                                                <th style="width: 5%;">ID</th>
                                                                <th style="width: 15%;">Total</th>
                                                                <th style="width: 15%;">Loan Date</th>
                                                                <th style="width: 15%;">Due Date</th>
                                                                <th style="width: 30%;">Description</th>
                                                                <th style="width: 20%;">Room</th>
                                                                <th>Action</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:forEach items="${requestScope.invoicePaidList}" var="item">
                                                                <c:if test="${item.status==1}">
                                                                    <tr>
                                                                        <td>${item.id}</td>
                                                                        <td style="text-align: right">
                                                                            <fmt:formatNumber type="currency" currencyCode="VND" value="${item.total}" maxFractionDigits="0"/>
                                                                        </td> 
                                                                        <td style="text-align: center">
                                                                            ${item.invoiceDateFormat}
                                                                        </td>
                                                                        <td style="text-align: center">${item.dueDateFormat}</td>
                                                                        <td style="text-align: left">${item.description}</td>
                                                                        <td style="text-align: center">${item.apartment.id}</td>
                                                                        <td style="text-align: center">
                                                                            <button class="fa fa-plus" style="background: none; border: none; cursor: pointer; font-size: 16px;"></button>
                                                                        </td>
                                                                    </tr>
                                                                    <tr class="detail-row" style="display: none; background-color: #f9f9f9;">
                                                                        <td colspan="8" style="padding: 15px; border-top: 2px solid #ddd;">
                                                                            <p style="font-size: 16px; font-weight: bold; margin-bottom: 10px;">Chi tiết hóa đơn #${item.id}</p>
                                                                            <table style="width: 100%; border-collapse: collapse; background: white; border-radius: 5px; box-shadow: 0px 2px 5px rgba(0,0,0,0.1);">
                                                                                <thead>
                                                                                    <tr style="background: #ddd; color: white; text-align: left;">
                                                                                        <th style="padding: 8px; border: 1px solid #ddd;background: #ddd">Dịch vụ</th>
                                                                                        <th style="padding: 8px; border: 1px solid #ddd;background: #ddd">Số lượng</th>
                                                                                        <th style="padding: 8px; border: 1px solid #ddd;background: #ddd">Đơn vị</th>
                                                                                        <th style="padding: 8px; border: 1px solid #ddd;background: #ddd">Chi phí</th>
                                                                                        <th style="padding: 8px; border: 1px solid #ddd;background: #ddd">Date</th>
                                                                                    </tr>
                                                                                </thead>
                                                                                <tbody>
                                                                                    <c:forEach items="${item.invoiceDetail}" var="detail">
                                                                                        <tr style="border-bottom: 1px solid #ddd;">
                                                                                            <td style="padding: 8px; border: 1px solid #ddd;text-align: left">${detail.serviceName}</td>
                                                                                            <td style="padding: 8px; border: 1px solid #ddd; text-align: center;">${detail.quantity}</td>
                                                                                            <td style="padding: 8px; border: 1px solid #ddd; text-align: left">
                                                                                                <fmt:formatNumber type="currency" currencyCode="VND" value="${detail.unitPrice}" maxFractionDigits="0"/>
                                                                                            </td>
                                                                                            <td style="padding: 8px; border: 1px solid #ddd; text-align: left;">
                                                                                                <fmt:formatNumber type="currency" currencyCode="VND" maxFractionDigits="0" value="${detail.amount}"/>
                                                                                            </td>
                                                                                            <td style="padding: 8px; border: 1px solid #ddd; text-align: center;">${detail.dateFormat}</td>
                                                                                        </tr>
                                                                                    </c:forEach>
                                                                                </tbody>
                                                                            </table>
                                                                        </td>
                                                                    </tr>

                                                                </c:if>
                                                            </c:forEach>
                                                        </tbody>
                                                    </table>
                                                    <form style="text-align: right" action="view-invoice-resident" method="post">
                                                        <input hidden="" name="apartmentSelected" value="${requestScope.usingApartment}"/>
                                                        <input hidden="" name="from" value="${requestScope.usingFrom}"/>
                                                        <input hidden="" name="to" value="${requestScope.usingTo}"/>
                                                        <select onchange="this.form.submit()" name="page" style="width: 60px; padding: 5px; border: 1px solid #ccc; border-radius: 5px;">
                                                            <c:forEach begin="1" end="${requestScope.endPage}" var="item">
                                                                <option ${item == requestScope.selectedPage ? 'selected':''} value="${item}" style="background-color: #f8f9fa; color: #333; padding: 5px;">${item}</option>
                                                            </c:forEach>    
                                                        </select>

                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                        <!-- footer -->
                        <div class="footer" style="position: absolute; bottom: 0; left: 0; width: 100%; background: white; color: white; text-align: center; padding: 10px;">
                            <p>Copyright © 2025 Designed by Your Company. All rights reserved.</p>
                        </div>

                    </div>
                    <!-- end dashboard inner -->
                </div>
            </div>
            <!-- jQuery -->
            <script src="js/jquery.min.js"></script>
            <script src="js/popper.min.js"></script>
            <script src="js/bootstrap.min.js"></script>
            <script src="js/custom.js"></script>
    </body>
    <script>
                                                            document.querySelectorAll(".fa-plus").forEach(button => {
                                                                button.addEventListener("click", function () {
                                                                    let detailRow = this.closest("tr").nextElementSibling;

                                                                    // Toggle hiển thị
                                                                    detailRow.style.display = (detailRow.style.display === "none" || detailRow.style.display === "") ? "table-row" : "none";

                                                                    // Đổi icon giữa dấu "+" và "-"
                                                                    this.classList.toggle("fa-plus");
                                                                    this.classList.toggle("fa-minus");
                                                                });
                                                            });
                                                            // Lấy tham số trên URL
                                                            const urlParams = new URLSearchParams(window.location.search);
                                                            const status = urlParams.get("status");

                                                            if (status === "success") {
                                                                alert("Thanh toán thành công!");
                                                            } else if (status === "failed") {
                                                                alert("Thanh toán thất bại! Vui lòng thử lại.");
                                                            }
    </script>
</html>