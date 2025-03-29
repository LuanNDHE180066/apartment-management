<%-- 
    Document   : updateroomtype.jsp
    Created on : Feb 14, 2025, 2:39:58 PM
    Author     : PC
--%>

<!DOCTYPE html>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
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
            .table th, .table td {
                border: 1px solid rgba(0, 0, 0, 0.2);
                text-align: center;
            }
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
                .form-group select {
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
                .form-group select:focus {
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
                .error-message {
                    color: red;
                    font-size: 14px;
                    margin-top: 5px;
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
                                    <h1>Chỉnh sửa các loại phí</h1>
                                    <form action="update-expense-category" method="post">
                                        
                                        <div class="form-group">
                                            <div class="one-col" style="padding: 0; margin-right: 5px">
                                                <label for="name">Tên loại phí:</label>
                                                <input
                                                    type="text"
                                                    id="name"
                                                    name="categoryName"
                                                    placeholder="Enter category name"
                                                    value="${expenseCategory.categoryName}"
                                                    required="" 
                                                    />
                                                <p class="error-message">${requestScope.nameError}</p>
                                            </div>
                                        </div>  
                                                <input type="text" value="${expenseCategory.id}" name="id" hidden=""><!-- comment -->
                                        <div class="form-group">
                                            <div class="one-col" style="padding: 0; margin-right: 5px">
                                                <label for="name">Mô tả</label>
                                                <input
                                                    type="text"
                                                    id="categoryDescription"
                                                    name="categoryDescription"
                                                    placeholder="Enter category description"
                                                    value="${expenseCategory.categoryDescription}"
                                                    required=""
                                                    />
                                                <p class="error-message">${requestScope.nameError}</p>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="one-col" style="padding: 0; margin-right: 5px">
                                                <label for="status">Status:</label>
                                                <select name="status">
                                                    <option ${expenseCategory.status == 1?'selected':''} value="1">Active</option>
                                                    <option ${expenseCategory.status == 0?'selected':''} value="0">Inactive</option>
                                                </select>
                                                <p class="error-message">${requestScope.nameError}</p>
                                            </div>
                                        </div>

                                        <div class="form-button">
                                            <button type="submit">Lưu</button>
                                            <div><a href="view-expense-category">Quay lại</a><!-- comment --></div>
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
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        </body>
    </html>
