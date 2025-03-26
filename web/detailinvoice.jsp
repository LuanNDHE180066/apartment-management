<%-- 
    Document   : dashboard-resident-information
    Created on : Feb 23, 2025, 8:41:41 PM
    Author     : thanh
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
            <title>Change Service</title>            <link rel="icon" href="images/fevicon.png" type="image/png" />
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
            <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
            <!--[if lt IE 9]>
              <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
              <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
            <![endif]-->
            <style>



            </style>
        </head>
        <body>
            <%@include file="sidebar.jsp" %>
            <div id="content">
                <%@include file="topbar.jsp" %>
                <div class="container">
                    <!-- Bảng dữ liệu -->
                    <div class="form-section" style="background: white; padding: 30px; border-radius: 10px; box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); margin-bottom: 20px;">
                        <!-- Các thông tin còn lại nằm ngang -->
                        <div style="display: flex; flex-wrap: wrap; gap: 10px;">
                            <p style="margin: 0; padding: 8px 12px; background: #f8f9fa; border-radius: 5px; font-size: 14px; box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.1);">
                                ID: ${requestScope.invoice.id}
                            </p>
                            <p style="margin: 0; padding: 8px 12px; background: #f8f9fa; border-radius: 5px; font-size: 14px; box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.1);">
                                Loan Date: ${requestScope.invoice.invoiceDateFormat}
                            </p>
                            <p style="margin: 0; padding: 8px 12px; background: #f8f9fa; border-radius: 5px; font-size: 14px; box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.1);">
                                Due Date: ${requestScope.invoice.dueDateFormat}
                            </p>
                            <p style="margin: 0; padding: 8px 12px; background: #f8f9fa; border-radius: 5px; font-size: 14px; box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.1);">
                                Trạng thái: ${requestScope.invoice.status == 0 ? 'Chưa thanh toán':'Đã thanh toán'}
                            </p>
                            <p style="margin: 0; padding: 8px 12px; background: #f8f9fa; border-radius: 5px; font-size: 14px; box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.1);">
                                Mô tả: ${requestScope.invoice.description}
                            </p>
                            <p style="margin: 0; padding: 8px 12px; background: #f8f9fa; border-radius: 5px; font-size: 14px; box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.1);">
                                Căn hộ: ${requestScope.invoice.apartment.id}
                            </p>
                            <p style="margin: 0; padding: 12px 16px; background: #ffc107; border-radius: 8px; font-size: 18px; font-weight: bold; color: #333; text-align: center; box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);">
                                Tổng: ${requestScope.invoice.total} VND
                            </p>

                        </div>
                    </div>


                    <p style="font-size: 16px; font-weight: bold; margin-bottom: 10px;">Chi tiết hóa đơn</p>
                    <table style="width: 100%; border-collapse: collapse; background: white; border-radius: 5px; box-shadow: 0px 2px 5px rgba(0,0,0,0.1);">
                        <thead>
                            <tr style="background: linear-gradient(135deg, #6a11cb, #2575fc); color: white; text-align: left;">
                                <th style="padding: 10px; border: 1px solid #bbb;">Dịch vụ</th>
                                <th style="padding: 10px; border: 1px solid #bbb;">Số lượng</th>
                                <th style="padding: 10px; border: 1px solid #bbb;">Đơn vị</th>
                                <th style="padding: 10px; border: 1px solid #bbb;">Chi phí</th>
                                <th style="padding: 10px; border: 1px solid #bbb;">Date</th>
                            </tr>
                        </thead>

                        <tbody>
                            <c:forEach items="${requestScope.invoice.invoiceDetail}" var="detail">
                                <tr style="border-bottom: 1px solid #ddd;">
                                    <td style="padding: 8px; border: 1px solid #ddd; text-align: left">${detail.serviceName}</td>
                                    <td style="padding: 8px; border: 1px solid #ddd; text-align: center;">${detail.quantity}</td>
                                    <td style="padding: 8px; border: 1px solid #ddd;text-align: right">
                                        <fmt:formatNumber type="currency" currencyCode="VND" value="${detail.unitPrice}" />
                                    </td>
                                    <td style="padding: 8px; border: 1px solid #ddd; text-align: right;">
                                        <fmt:formatNumber type="currency" currencyCode="VND" value="${detail.amount}" maxFractionDigits="0"/>
                                    </td>
                                    <td style="padding: 8px; border: 1px solid #ddd; text-align: center;">${detail.dateFormat}</td>
                                </tr>
                            </c:forEach>    
                        </tbody>
                    </table>


                    <!-- Script vẽ biểu đồ -->

                </div>
                <script src="js/jquery.min.js"></script>
                <script src="js/popper.min.js"></script>
                <script src="js/bootstrap.min.js"></script>
                <script src="js/custom.js"></script>
        </body> 
    </html>
