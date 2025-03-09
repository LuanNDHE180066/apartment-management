<%-- 
    Document   : viewallexpenditure
    Created on : Feb 24, 2025, 4:52:47 PM
    Author     : PC
--%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://fonts.googleapis.com/css2?family=Noto+Sans&display=swap" rel="stylesheet">
        <title>Contracts</title>
        <link rel="icon" href="images/fevicon.png" type="image/png" />
        <link rel="stylesheet" href="css/bootstrap.min.css" />
        <link rel="stylesheet" href="style.css" />
        <link rel="stylesheet" href="css/responsive.css" />
        <link rel="stylesheet" href="css/custom.css" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <style>
            body {
                /* Style body n?u c?n */
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
            .table th {
                text-align: center;
                background-color: #6B90DA;
                color: black;
            }
            .display-none{
                display: none;
            }

            .tbody td{
                text-align: center;
            }
            
            .display-none{
                display: none;
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
                                        <h2>Contract List</h2>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="white_shd full margin_bottom_30">
                                        <div class="full graph_head">
                                            <div class="heading1 margin_0">
                                                <h2>Expenditure Table Information</h2>
                                            </div>
                                            <br> <hr><!-- comment -->
                                            <div>
                                                <form action="view-expenditure" method="get">
                                                    <div class="row align-items-center">
                                                        <div class="col-md-8">
                                                            <div class="row align-items-center">
                                                                <div class="col-md-2">
                                                                    <input type="text" class="form-control" value="${param.title}" name="title" placeholder="Enter title">
                                                                </div>
                                                                <div class="col-md-2">
                                                                    <input type="date" class="form-control" value="${param.startDate}" name="startDate" placeholder="From">
                                                                </div>
                                                                <div class="col-md-2">
                                                                    <input type="date" class="form-control" value="${param.endDate}" name="endDate" placeholder="To">
                                                                </div>
                                                                <div class="col-md-2.5">
                                                                    <select class="form-control" name="category">
                                                                        <option value="">Select category</option>
                                                                        <c:forEach items="${requestScope.categorylist}" var="ca">
                                                                            <option ${param.category == ca.id? 'selected':''} value="${ca.id}">${ca.categoryName}</option>
                                                                        </c:forEach>
                                                                    </select>
                                                                </div>
                                                                <div class="col-md-2 d-flex">
                                                                    <button type="submit" class="btn btn-primary" style="margin-right: 5px;">Filter</button>
                                                                    <span class="btn btn-primary" style="display: inline-block; ${roleId != 3 ?'display: none':''}"><a style="color: white" href="add-expenditure">Add</a></span>
                                                                    <span  class="btn btn-primary" style="display: inline-block; margin-left:10px ">
                                                                        <a style="color: white" href="view-pending-expenditure">View pending expenditure list</a></span>
                                                                    <span  class="btn btn-primary" style="display: inline-block; margin-left:10px ">
                                                                        <a style="color: white" href="view-expense-category">View expense cateogry</a></span>
                                                                </div>

                                                            </div>
                                                        </div>
                                                </form>
                                            </div>
                                        </div>

                                        <div class="table_section padding_infor_info">
                                            <div class="table-responsive-sm">
                                                <table class="table w-100" id="table-infor">
                                                    <thead>
                                                        <tr>
                                                            <th style="text-align: center">ID</th>
                                                            <th style="text-align: center">Expense</th>
                                                            <th style="text-align: center">Total fees</th>
                                                            <th style="text-align: center">Approve Date</th>
                                                            <th style="text-align: center">Category</th>
                                                            <th style="text-align: center">Company</th>
                                                            <th style="text-align: center">Staff Create</th>
                                                            <th style="text-align: center">Chief Accountant</th>
                                                            <th style="text-align: center">Responsible Person</th>
                                                            <th  style="text-align: center">Option</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody class="tbody">
                                                    <h3>${requestScope.message}</h3>
                                                    <c:forEach items="${requestScope.listExpenditure}" var="expenditure">
                                                        <tr>
                                                            <td>${expenditure.id}</td>
                                                            <td>${expenditure.title}</td>
                                                            <td>
                                                                <fmt:setLocale value="vi_VN"/> <%-- Thi?t l?p locale v? Vi?t Nam --%>
                                                                <fmt:formatNumber value="${expenditure.totalPrice}" type="currency" currencyCode="VND" maxFractionDigits="0"/>
                                                            </td>
                                                            <td>${expenditure.approveddate}</td>
                                                            <td>${expenditure.category.categoryName}</td>
                                                            <td>${expenditure.company.name}</td>
                                                            <td>${expenditure.createdStaff.name}</td>
                                                            <td>${expenditure.chiefAccountantId.name}</td>
                                                            <td>${expenditure.currentAdmin.name}</td>
                                                            <td>
                                                                <a class="${sessionScope.account.roleId != 3? 'display-none':''}" href="update-expenditure?id=${expenditure.id}"><i class="fa-solid fa-pen-to-square"></i></a>
                                                                <a href="view-expenditure-change-history?id=${expenditure.id}" 
                                                                   style="margin-left: 10px;"><i class="fa-solid fa-history"></i></a>

                                                            </td>
                                                    <!--    <td>${expenditure.note}</td>  -->
                                                        </tr>
                                                    </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <form method="get" action="view-expenditure" style="display: flex; align-items: center; gap: 10px;">
                        <label for="page" style="font-size: 14px; font-weight: bold;">Page:</label>
                        <input type="text" name="title" value="${param.title}" hidden="">
                        <input type="text" name="category" value="${param.category}" hidden="">
                        <input type="date" name="startDate" value="${param.startDate}" hidden="">
                        <input type="date" name="endDate"  value="${param.endDate}" hidden="">
                        <select id="page" name="page" onchange="this.form.submit()" 
                                style="padding: 6px 12px; font-size: 14px; border: 1px solid #ddd; border-radius: 4px; cursor: pointer;">
                            <c:forEach begin="1" end="${requestScope.totalPage}" var="page">
                                <option value="${page}" <c:if test="${page == requestScope.currentPage}"> selected</c:if>>
                                    ${page}
                                </option>
                            </c:forEach>
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