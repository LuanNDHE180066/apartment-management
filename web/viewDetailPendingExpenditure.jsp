<!DOCTYPE html>
<<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <!-- custom css -->
        <style>
            body {
                font-family: Arial, sans-serif;
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
            .display-none{
                display: none;
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
                                <h1>Chi tiết khoản chi</h1>
                                <form action="view-pending-expenditure" method="get">
                                    <div class="form-group">
                                        <input type="text" id="staffID" name="staffID" value="${expenditure.id}" hidden=""/>
                                        <div class="two-cols">
                                            <div class="col" style="padding: 0; margin-right: 5px">
                                                <label for="title">Tiêu đề</label>
                                                <input readonly="" type="text" id="title" name="title" placeholder="Enter title" value="${expenditure.title}" required=""/>
                                                <input readonly="" type="text" id="title" hidden="" name="id" placeholder="Enter title" value="${expenditure.id}" required=""/>
                                            </div>
                                            <div class="col" style="padding: 0; margin-right: 5px">
                                                <label for="totalFees">Tổng chi</label>
                                                <input readonly="" type="number" id="totalFees" min="0"
                                                       name="totalPrice" step="0.01" placeholder="Enter total fees" value="${expenditure.totalPrice}" required=""/>
                                            </div>
                                        </div>
                                    </div> 
                                    <div class="form-group">
                                        <div class="two-cols">
                                            <div class="col" style="padding: 0; margin-right: 5px">
                                                <label for="approveDate">Ngày duyệt</label>
                                                <input readonly="" type="date" id="approveDate" name="approveDate" value="${expenditure.approveddate}" required=""/>
                                            </div>
                                            <div class="col" style="padding: 0; margin-right: 5px">
                                                <label for="paymentDate">Ngày giải ngân</label>
                                                <input readonly="" type="date" id="paymentDate" name="paymentDate" value="${expenditure.paymentdate}" required=""/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="two-cols">
                                            <div class="col" style="padding: 0; margin-right: 5px">
                                                <label for="category">Loại phí</label>
                                                <select disabled id="category" name="category" >
                                                    <c:forEach items="${sessionScope.listExpenseCategory}" var="cat">
                                                        <option value="${cat.id}" ${cat.id == expenditure.category.id ? 'selected' : ''}>${cat.categoryName}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="col" style="padding: 0; margin-right: 5px">
                                                <label for="company">Công ty</label>
                                                <select disabled id="company" name="company" readonly="">
                                                    <c:forEach items="${sessionScope.listCompany}" var="cp">
                                                        <option value="${cp.id}" ${expenditure.company.id == cp.id?'selected':''}>${cp.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="two-cols">
                                            <div class="col" style="padding: 0; margin-right: 5px">
                                                <label for="invoiceCreator">Người tạo</label>
                                                <input readonly="" type="text" id="invoiceCreator" name="invoiceCreator" 
                                                       placeholder="Enter invoice creator" value="${expenditure.createdStaff.name}" readonly="" required=""/>
                                                <input hidden="" type="text" name="createdStaff"?hidden="" value="${expenditure.createdStaff.id}"><!-- comment -->
                                            </div>
                                            <div class="col" style="padding: 0; margin-right: 5px">
                                                <label for="chiefAccountant">Kế toán</label>
                                                <select disabled="" id="chiefAccountant" name="chiefAccountant">
                                                    <c:forEach items="${sessionScope.listAccountant}" var="accountant">
                                                        <option value="${accountant.id}" ${accountant.id == expenditure.chiefAccountantId.id?'selected':''}>${accountant.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="two-cols">
                                            <div class="col" style="padding: 0; margin-right: 5px">
                                                <label for="admin">Giám đốc</label>
                                                <select disabled="" id="admin" name="admin">
                                                    <c:forEach items="${sessionScope.listAdmin}" var="admin">
                                                        <option value="${admin.id}" ${admin.id == expenditure.currentAdmin.id?'selected':''}>${admin.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="note">Ghi chú</label>
                                        <textarea readonly="" id="note" name="note" placeholder="Enter any additional notes" rows="4">${expenditure.note}</textarea>
                                    </div>
                                    <div class="form-button">
                                        <button class="${type == '1'?'display-none':''}" type="submit">Back</button>
                                        <span class="${type == '1'?'':'display-none'}"><a  href="view-expenditure-change-history?id=${expenditure.id}">Quay lại</a></span>   
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
                // Add your JavaScript validation and functions here
            </script>
    </body>
</html>