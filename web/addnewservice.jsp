<%-- 
    Document   : addnewapartment
    Created on : Jan 23, 2025
    Author     : PC
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Apartment Management</title>
        <link rel="icon" href="images/fevicon.png" type="image/png" />
        <link rel="stylesheet" href="css/bootstrap.min.css" />
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f9;
            }
            .form-container {
                background: #fff;
                padding: 40px;
                border-radius: 8px;
                max-width: 800px;
                margin: auto;
            }
            h1 {
                text-align: center;
                margin-bottom: 30px;
            }
            .form-group {
                margin-bottom: 20px;
            }
            .form-group label {
                display: block;
                margin-bottom: 8px;
                font-weight: bold;
            }
            .form-group input {
                width: 100%;
                padding: 12px;
                border: 1px solid #ccc;
                border-radius: 6px;
            }
            .form-button {
                text-align: center;
                margin-top: 30px;
            }
            .form-button button {
                padding: 12px 25px;
                background-color: #4a90e2;
                color: #fff;
                cursor: pointer;
            }
            .form-button button:hover {
                background-color: #357ab8;
            }
        </style>
    </head>
    <body>
        <div class="full_container">
            <div class="inner_container">
                <%@include file="sidebar.jsp" %>
                <div id="content">
                    <%@include file="topbar.jsp" %>
                    <div class="container mt-5">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-container">
                                    <h1 style="margin-bottom: 20px">Chi tiết dịch vụ</h1>
                                    <form action="add-service-staff" method="post" style="max-width: 600px; margin: auto; padding: 20px; border: 1px solid #ccc; border-radius: 10px; background: #f9f9f9; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);">
                                        <div class="form-group" style="margin-bottom: 15px;">
                                            <label for="name" style="display: block; font-weight: bold; margin-bottom: 5px;">Tên dịch vụ</label>
                                            <input type="text" id="name" name="name" required style="width: 100%; padding: 8px; border: 1px solid #ccc; border-radius: 5px;">
                                        </div>

                                        <div class="form-group" style="margin-bottom: 15px;">
                                            <label for="price" style="display: block; font-weight: bold; margin-bottom: 5px;">Phí </label>
                                            <input min="0" oninput="format(this)" type="text" id="price" name="price" required style="width: 100%; padding: 8px; border: 1px solid #ccc; border-radius: 5px;">
                                        </div>
                                        <div class="form-group" style="margin-bottom: 15px;">
                                            <label for="unti" style="display: block; font-weight: bold; margin-bottom: 5px;">Đơn vị</label>
                                            <input type="text" id="unit" name="unit" style="width: 100%; padding: 8px; border: 1px solid #ccc; border-radius: 5px;">
                                        </div>

                                        <div class="form-group" style="margin-bottom: 15px;">
                                            <label for="des" style="display: block; font-weight: bold; margin-bottom: 5px;">Mô tả</label>
                                            <input type="text" id="des" name="des" required style="width: 100%; padding: 8px; border: 1px solid #ccc; border-radius: 5px;">
                                        </div>

                                        <div class="form-group" style="margin-bottom: 15px;">
                                            <label for="category" style="display: block; font-weight: bold; margin-bottom: 5px;">Loại dịch vụ</label>
                                            <select name="category" style="width: 100%; padding: 8px; border: 1px solid #ccc; border-radius: 5px;">
                                                <c:forEach items="${requestScope.types}" var="type">
                                                    <option value="${type.id}">${type.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>

                                        <div class="form-group" style="margin-bottom: 15px;">
                                            <label for="company" style="display: block; font-weight: bold; margin-bottom: 5px;">Công ti cung cấp</label>
                                            <select name="company" style="width: 100%; padding: 8px; border: 1px solid #ccc; border-radius: 5px;">
                                                <c:forEach items="${requestScope.companies}" var="c">
                                                    <option value="${c.id}">${c.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>

                                        <div class="form-group" style="margin-bottom: 15px;">
                                            <label for="status" style="display: block; font-weight: bold; margin-bottom: 5px;">Trạng thái</label>
                                            <select name="status" style="width: 100%; padding: 8px; border: 1px solid #ccc; border-radius: 5px;">
                                                <option value="1">Đang hoạt động</option>
                                                <option value="2">Đóng hoạt động</option>
                                            </select>
                                        </div>

                                        <div class="form-button" style="text-align: center;">
                                            <button type="submit" style="background: #007bff; color: white; padding: 10px 15px; border: none; border-radius: 5px; font-size: 16px; cursor: pointer;">Lưu dịch vụ</button>
                                            <h5 style="color:${status=="true"?"green":"red"}; text-align: center; margin-top: 10px;">${requestScope.message}</h5>
                                            <h5 style="color:red; text-align: center; margin-top: 5px;">${requestScope.error}</h5>
                                        </div>
                                    </form>

                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="footer" style="background: white; color: white; text-align: center; padding: 10px;">
                        <p>Copyright © 2025 Designed by Your Company. All rights reserved.</p>
                    </div>
                    <script>
                        function format(input) {
                            let value = input.value.replace(/\./g, '');

                            let number = parseInt(value, 10);

                            if (!isNaN(number)) {
                                input.value = number.toLocaleString('de-DE');
                            } else {
                                input.value = '';
                            }
                        }
                    </script>
                    <script src="js/jquery.min.js"></script>
                    <script src="js/bootstrap.min.js"></script>
                </div>
            </div>
        </div>
    </body>
</html>