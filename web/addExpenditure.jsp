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
        <title>Thêm khoản chi</title>        
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

                background-color: #f4f4f9;
                margin: 0;
                padding: 0px;
            }

            .form-container {
                background: #ffffff;
                padding: 40px;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                max-width: 800px;
                margin: auto;
            }

            .form-container h1 {
                text-align: center;
                margin-bottom: 30px;
                color: #333;
            }

            .form-group {
                margin-bottom: 10px;
            }

            .form-group label {
                display: block;
                margin-bottom: 8px;
                font-weight: bold;
                color: #555;
            }

            .form-group input,
            .form-group select,
            .form-group textarea {
                width: 100%;
                padding: 12px;
                border: 1px solid #ccc;
                border-radius: 6px;
                font-size: 16px;
                line-height: 1.5;
                box-sizing: border-box;
                transition: border-color 0.3s;
            }

            .form-group input:focus,
            .form-group select:focus,
            .form-group textarea:focus {
                border-color: #4a90e2;
                outline: none;
            }

            .two-cols {
                display: flex;
                justify-content: space-between;
            }

            .form-button {
                text-align: center;
                margin-top: 30px;
            }

            .form-button button {
                padding: 12px 25px;
                font-size: 18px;
                border: none;
                border-radius: 6px;
                background-color: #4a90e2;
                color: #ffffff;
                cursor: pointer;
                transition: background-color 0.3s;
            }

            .form-button button:hover {
                background-color: #357ab8;
            }
        </style>
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
                            <div class="form-container">
                                <h1>Thêm khoản chi</h1>
                                <form action="add-expenditure" method="post">
                                    <div class="form-group">
                                        <input type="text" id="staffID" name="staffID" value="${expenditure.id}" hidden=""/>
                                        <div class="two-cols">
                                            <div class="col" style="padding: 0; margin-right: 5px">
                                                <label for="title">Tiêu đề</label>
                                                <input type="text" id="title" name="title" placeholder="Enter title" value="${expenditure.title}" required=""/>
                                            </div>
                                            <div class="col" style="padding: 0; margin-right: 5px">
                                                <label for="totalFees">Tổng chi (VND)</label>


                                                <fmt:formatNumber value="${expenditure.totalPrice}" type="currency" currencySymbol="VND" var="formattedTotalPrice"/>


                                                <input type="text" id="totalFeesDisplay" placeholder="Enter fees" 
                                                       value="${formattedTotalPrice}" required=""/>


                                                <input type="hidden" id="totalFees" name="totalPrice" value="${expenditure.totalPrice}"/>
                                            </div>
                                        </div>
                                    </div> 
                                    <div class="form-group">
                                        <div class="two-cols">
                                            <div class="col" style="padding: 0; margin-right: 5px">
                                                <label for="approveDate">Ngày duyệt:</label>
                                                <input type="date" id="approveDate" name="approveDate" value="${expenditure.approveddate}" required=""/>
                                            </div>
                                            <div class="col" style="padding: 0; margin-right: 5px">
                                                <label for="paymentDate">Ngày giải ngân:</label>
                                                <input type="date" id="paymentDate" name="paymentDate" value="${expenditure.paymentdate}" required=""/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="two-cols">
                                            <div class="col" style="padding: 0; margin-right: 5px">
                                                <label for="category">Loại phí</label>
                                                <select id="category" name="category" required="">
                                                    <c:forEach items="${sessionScope.listExpenseCategory}" var="cat">
                                                        <option value="${cat.id}" ${cat.id == expenditure.category.id ? 'selected' : ''}>${cat.categoryName}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="col" style="padding: 0; margin-right: 5px">
                                                <label for="company">Công ty</label>
                                                <select id="company" name="company">
                                                    <c:forEach items="${sessionScope.listCompany}" var="cp">
                                                        <option value="${cp.id}">${cp.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="two-cols">
                                            <div class="col" style="padding: 0; margin-right: 5px">
                                                <label for="invoiceCreator">Người tạo</label>
                                                <input type="text" id="invoiceCreator" 
                                                       placeholder="Enter invoice creator" value="${staff.name}" readonly="" required=""/>
                                                <input type="text" id="invoiceCreator" name="createdBy" 
                                                       placeholder="Enter invoice creator" value="${staff.id}" hidden="" readonly="" required=""/>
                                            </div>
                                            <div class="col" style="padding: 0; margin-right: 5px">
                                                <label for="chiefAccountant">Kế toán</label>
                                                <select id="chiefAccountant" name="chiefAccountant">
                                                    <c:forEach items="${sessionScope.listAccountant}" var="accountant">
                                                        <option value="${accountant.id}" >${accountant.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="two-cols">
                                            <div class="col" style="padding: 0; margin-right: 5px">
                                                <label for="admin">Giám đốc</label>
                                                <select id="admin" name="admin">
                                                    <c:forEach items="${sessionScope.listAdmin}" var="admin">
                                                        <option value="${admin.id}" >${admin.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="note">Ghi chú</label>
                                        <textarea id="note" name="note" placeholder="Enter any additional notes" rows="4">${expenditure.note}</textarea>
                                    </div>
                                    <div class="form-button">
                                        <button type="submit">Thêm</button>
                                        <div>
                                            <span>
                                                <a style="color: #357AB8; text-decoration: underline; font-size: 15px" href="expenditure-report">
                                                    <i class="fas fa-arrow-left"></i> Quay lại
                                                </a>
                                            </span>
                                        </div>
                                        <h5 style="color:${status=="true"?"green":"red"};text-align:center ">${requestScope.message}</h5>
                                    </div>
                                </form>
                            </div>
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

            <script>
                document.getElementById('totalFeesDisplay').addEventListener('input', function (e) {
                    let rawValue = e.target.value.replace(/[^\d]/g, '');
                    let numericValue = parseFloat(rawValue);

                    if (!isNaN(numericValue)) {
                        e.target.value = numericValue.toLocaleString('vi-VN') + ' VND';
                        document.getElementById('totalFees').value = numericValue;
                    } else {
                        e.target.value = '';
                        document.getElementById('totalFees').value = '';
                    }
                });
            </script>
    </body>
</html>