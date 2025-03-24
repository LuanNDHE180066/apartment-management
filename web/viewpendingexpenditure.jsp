
<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<html lang="en">
    <head>
        <!-- basic -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- mobile metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- site metas -->
        <title>Quản lý khoản chi</title>        
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
        <link rel="stylesheet" href="<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg==" crossorigin="anonymous" referrerpolicy="no-referrer" />"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <style>
                body {
            font-family: 'Roboto', sans-serif !important;
        }
            .contract-list {
                padding-left: 20px; /* Kho?ng cách bên trái */
            }
            .contract-list li {
                padding: 10px; /* Padding cho t?ng m?c */
                font-size: 18px; /* Kích th??c ch? cho t?ng m?c */
            }
            .contract-list li a {
                text-decoration: none; /* B? g?ch chân */
                color: #007bff; /* Màu liên k?t */
            }
            .contract-list li a:hover {
                color: #0056b3; /* Màu khi hover */
            }
            .graph_head {
                margin-bottom: 20px; /* Gi?m kho?ng cách d??i tiêu ?? */
            }
            button {
                background: none;
                border: none;
                cursor: pointer;
                font-size: 24px;
                margin: 0 5px;
            }
            .approval-link {
                font-size: 20px;
                margin: 0 5px;
                color: inherit;
                text-decoration: none;
            }
            .table th {
                text-align: center;
                background-color: #6B90DA;
                color: black;
            }
               .table td{
                 text-align: left;
                 color: black;
                 font-weight: 300;
            }
        </style>
    </head>
    <body class="inner_page contract_page">
        <div class="full_container">
            <div class="inner_container">
                <%@ include file="sidebar.jsp" %>
                <div id="content">
                    <%@ include file="topbar.jsp" %>
                    <div class="midde_cont">
                        <div class="container-fluid">
                            <div class="row column_title">
                                <div class="col-md-12">
                                    <div class="page_title">

                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="white_shd full margin_bottom_30">

                                        <div class="heading1 margin_0">
                                            <h2 style="margin-left: 30px; font-weight: bold">Khoản chi chờ duyệt</h2>
                                 
                                        </div>

                                        <div class="table_section padding_infor_info">
                                            <div class="table-responsive-sm">
                                                <table class="table w-100 " id="table-infor">
                                                    <thead>
                                                        <tr>
                                                               <th style="text-align: center">ID</th>
                                                            <th style="text-align: center">Tiêu đề</th>
                                                            <th style="text-align: center">Tổng phí</th>
                                                            <th style="text-align: center">Ngày duyệt</th>
                                                            <th style="text-align: center">Loại phí</th>
                                                            <th style="text-align: center">Công ty</th>
                                                            <th style="text-align: center">Người thay đổi</th>
                                                            <th style="text-align: center">Kế toán</th>
                                                            <th style="text-align: center">Giám đốc</th>
                                                            <th  style="text-align: center">Khác</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                    <h3>${requestScope.message}</h3>
                                                    <c:forEach items="${requestScope.listExpenditure}" var="expenditure">
                                                        <tr>
                                                            <td style="text-align: center;color : ${expenditure.id == null?'red':'green'}">${expenditure.id == null? 'Undefined':''}</td>
                                                            <td style="text-align: center"> ${expenditure.title}</td>
                                                            <td>
                                                                <fmt:setLocale value="vi_VN"/> <%-- Thi?t l?p locale v? Vi?t Nam --%>
                                                                <fmt:formatNumber value="${expenditure.totalPrice}" type="currency" currencyCode="VND" maxFractionDigits="0"/>
                                                            </td>
                                                            <td style="text-align: center">${expenditure.approveddate}</td>

                                                            <td style="text-align: center">${expenditure.category.categoryName}</td>
                                                            <td style="text-align: center">${expenditure.company.name}</td>
                                                            <td style="text-align: center">${expenditure.createdStaff.name}</td>
                                                            <td style="text-align: center">${expenditure.chiefAccountantId.name}</td>
                                                            <td style="text-align: center">${expenditure.currentAdmin.name}</td>
                                                            <td style="text-align: center">
                                                                <c:if test="${expenditure.currentAdmin.id == staffId && expenditure.currentAdminApproveStatus == 0}">
                                                                    <a style="text-align: center;" title="Approve"   class="approval-link" href="update-pending-expenditure?id=${expenditure.heid}&approve=1">
                                                                        <i class="fa-solid fa-check"></i>
                                                                    </a>
                                                                    <a style="text-align: center" title="Decline" class="approval-link" href="update-pending-expenditure?id=${expenditure.heid}&approve=-1">
                                                                        <i class="fa-solid fa-xmark"></i>
                                                                    </a>
                                                                    <a style="text-align: center" class="approval-link" href="view-pending-expenditure-detail?id=${expenditure.heid}">
                                                                        <i class="fa-solid fa-eye"></i> 
                                                                    </a>
                                                                </c:if>

                                                                <c:if test="${expenditure.currentAdmin.id == staffId && expenditure.currentAdminApproveStatus == 1}">
                                                                    <span style="text-align: center; color: green" >Accept</span>
                                                                    <a style="text-align: center" class="approval-link" href="view-pending-expenditure-detail?id=${expenditure.heid}">
                                                                        <i class="fa-solid fa-eye"></i> 
                                                                    </a>
                                                                </c:if>


                                                                <c:if test="${expenditure.chiefAccountantId.id == staffId && expenditure.chiefAccountantApproveStatus == 0}">
                                                                    <a style="text-align: center" class="approval-link" href="update-pending-expenditure?id=${expenditure.heid}&approve=1">
                                                                        <i class="fa-solid fa-check"></i>
                                                                    </a>
                                                                    <a style="text-align: center" class="approval-link" href="update-pending-expenditure?id=${expenditure.heid}&approve=-1">
                                                                        <i class="fa-solid fa-xmark"></i>
                                                                    </a>
                                                                    <a style="text-align: center" class="approval-link" href="view-pending-expenditure-detail?id=${expenditure.heid}">
                                                                        <i class="fa-solid fa-eye"></i> 
                                                                    </a>
                                                                </c:if>

                                                                <c:if test="${expenditure.chiefAccountantId.id == staffId && expenditure.chiefAccountantApproveStatus == 1}">
                                                                    <span style="text-align: center; color: green" style="">Accept</span>
                                                                    <a style="text-align: center" class="approval-link" href="view-pending-expenditure-detail?id=${expenditure.heid}">
                                                                        <i class="fa-solid fa-eye"></i> 
                                                                    </a>
                                                                </c:if>
                                                                <c:if test="${expenditure.chiefAccountantId.id == staffId && expenditure.chiefAccountantApproveStatus == -1}">
                                                                    <a style="text-align: center" class="approval-link" href="view-pending-expenditure-detail?id=${expenditure.heid}">
                                                                        <i class="fa-solid fa-eye"></i> 
                                                                    </a>
                                                                </c:if>
                                                            </td>
                                                    <!--    <td>${expenditure.note}</td>  -->
                                                        </tr>
                                                    </c:forEach>
                                                    </tbody>
                                                </table>
                                                    <a class="btn btn-primary" href="expenditure-report">Quay lại</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <form method="get" action="view-pending-expenditure" style="display: flex; align-items: center; gap: 10px;">
                        <label for="page" style="font-size: 14px; font-weight: bold;">Page:</label>
                        <select id="page" name="page" onchange="this.form.submit()" 
                                style="padding: 6px 12px; font-size: 14px; border: 1px solid #ddd; border-radius: 4px; cursor: pointer;">
                            <c:forEach begin="1" end="${requestScope.totalPage}" var="page">
                                <option value="${page}" <c:if test="${page == requestScope.currentPage}">selected</c:if>>
                                    ${page}
                                </option>
                            </c:forEach>
                            <!-- comment -->
                        </select>
                    </form>
                    <div class="container-fluid">
                        <div class="footer">
                            <p>Copyright © 2025 Designed by Your Company. All rights reserved.</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="js/jquery.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/custom.js"></script>
    </body>
</html>