<%-- 
    Document   : updatecategoryservice
    Created on : Feb 18, 2025, 7:55:25 PM
    Author     : PC
--%>

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
                                    <h1>Update Category Service</h1>
                                    <div style="margin-bottom: 20px">
                                                <form action="view-resident" method="GET">
                                                    <div class="row align-items-center">
                                                        <div class="col-md-4 d-flex">
                                                            <a href="view-categoryservice-staff" class="btn btn-primary">Back</a>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                    <form action="update-categoryservice-staff" method="post">
                                        <input name="id" value="${requestScope.categoryservice.id}" hidden/>
                                        <div class="form-group">
                                            <label for="name">Name</label>
                                            <input type="text" id="name" name="name" value="${requestScope.categoryservice.name}" required />
                                        </div>
                                        <div class="form-group">
                                            <label for="note">Note</label>
                                            <textarea value="" style="width: 100%" id="note" name="note" placeholder="Enter note" rows="5" cols="50" required>${requestScope.categoryservice.detail}</textarea>
                                        </div>
                                        <div class="form-button">
                                            <button type="submit">Update</button>
                                            <h5 style="color:${status=="true"?"green":"red"};text-align:center ">${requestScope.error}</h5>
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
