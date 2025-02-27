<%-- 
    Document   : addnewapartment
    Created on : Jan 23, 2025
    Author     : PC
--%>

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
                                    <h1>Add New Room Type</h1>
                                    <form action="update-service-staff" method="post" style="max-width: 600px; margin: auto; padding: 20px; border: 1px solid #ccc; border-radius: 10px; background: #f9f9f9; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);">
                                        <div class="form-group" style="margin-bottom: 15px;">
                                            <label for="name" style="display: block; font-weight: bold; margin-bottom: 5px;">Name</label>
                                            <input type="text" id="name" name="name" value="${requestScope.service.name}" required style="width: 100%; padding: 8px; border: 1px solid #ccc; border-radius: 5px;">
                                        </div>

                                        <div class="form-group" style="margin-bottom: 15px;">
                                            <label for="price" style="display: block; font-weight: bold; margin-bottom: 5px;">Unit Price</label>
                                            <input min="0" type="number" id="price" name="price" value="${requestScope.service.unitPrice}" required style="width: 100%; padding: 8px; border: 1px solid #ccc; border-radius: 5px;">
                                        </div>
                                        
                                         <div class="form-group" style="margin-bottom: 15px;">
                                            <label for="price" style="display: block; font-weight: bold; margin-bottom: 5px;">Unit</label>
                                            <input type="text" id="unit" name="unit" value="${requestScope.service.unit}" required style="width: 100%; padding: 8px; border: 1px solid #ccc; border-radius: 5px;">
                                        </div>

                                        <div class="form-group" style="margin-bottom: 15px;">
                                            <label for="des" style="display: block; font-weight: bold; margin-bottom: 5px;">Description</label>
                                            <input type="text" id="des" name="des" required value="${requestScope.service.description}" style="width: 100%; padding: 8px; border: 1px solid #ccc; border-radius: 5px;">
                                        </div>

                                        <div class="form-group" style="margin-bottom: 15px;">
                                            <label for="category" style="display: block; font-weight: bold; margin-bottom: 5px;">Category</label>
                                            <select name="category" style="width: 100%; padding: 8px; border: 1px solid #ccc; border-radius: 5px;">
                                                <c:forEach items="${requestScope.types}" var="type">
                                                    <option ${requestScope.service.categoryService.id == type.id? 'selected':''} value="${type.id}">${type.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>

                                        <div class="form-group" style="margin-bottom: 15px;">
                                            <label for="company" style="display: block; font-weight: bold; margin-bottom: 5px;">Company</label>
                                            <select name="company" style="width: 100%; padding: 8px; border: 1px solid #ccc; border-radius: 5px;">
                                                <c:forEach items="${requestScope.companies}" var="c">
                                                    <option ${requestScope.service.company.id == c.id? 'selected':''} value="${c.id}">${c.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>

                                        <div class="form-group" style="margin-bottom: 15px;">
                                            <label for="status" style="display: block; font-weight: bold; margin-bottom: 5px;">Status</label>
                                            <select name="status" style="width: 100%; padding: 8px; border: 1px solid #ccc; border-radius: 5px;">
                                                <option ${requestScope.service.status==1?'selected':''} value="1">Active</option>
                                                <option ${requestScope.service.status==0?'selected':''} value="0">Inactive</option>
                                            </select>
                                        </div>

                                        <div class="form-button" style="text-align: center;">
                                            <input type="text" name="id" value="${requestScope.service.id}" hidden=""/>
                                            <button type="submit" style="background: #007bff; color: white; padding: 10px 15px; border: none; border-radius: 5px; font-size: 16px; cursor: pointer;">Save</button>
                                            <h5 style="color:${status=="true"?"green":"red"}; text-align: center; margin-top: 10px;">${requestScope.message}</h5>
                                            <h5 style="color:red; text-align: center; margin-top: 5px;">${requestScope.error}</h5>
                                        </div>
                                    </form>

                                </div>
                            </div>
                        </div>
                    </div>
                    <script src="js/jquery.min.js"></script>
                    <script src="js/bootstrap.min.js"></script>
                </div>
            </div>
        </div>
    </body>
</html>