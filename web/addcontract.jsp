<%-- 
    Document   : addnewcompany
    Created on : Jan 23, 2025, 2:23:52 PM
    Author     : PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <!-- basic -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- mobile metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="viewport" content="initial-scale=1, maximum-scale=1">
        <!-- site metas -->
        <title>Apartment management</title>        <meta name="keywords" content="">
        <meta name="description" content="">
        <meta name="author" content="">
        <!-- site icon -->
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
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
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
                margin-bottom: 20px;
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
            }</style>


    </head>
    <body class="dashboard dashboard_1">
        <div class="full_container">
            <div class="inner_container">
                <%@include file="sidebar.jsp" %>
                <!-- end sidebar -->
                <!-- right content -->
                <div id="content">
                    <!-- topbar -->
                    <%@include file="topbar.jsp" %>
                    <!-- end topbar -->
                    <!-- Form to Add New Employee -->
                    <div class="container mt-5">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-container">
                                    <h1>Thêm hợp đồng</h1>
                                    <form action="add-new-contract" method="post" enctype="multipart/form-data">
                                        <div class="form-group">
                                            <label for="title">Tiêu đề</label>
                                            <input type="text" id="title" name="title" value="${param.title}" placeholder="Nhập tiêu đề" required />
                                            <span style="color: red">${requestScope.titleerror}</span>
                                        </div>
                                        <div class="form-group">
                                            <label for="description">Mô tả</label>
                                            <input type="text" id="description" name="description" value="${param.description}" placeholder="Nhập mô tả" required />
                                            <span style="color: red">${requestScope.deserror}</span>
                                        </div>

                                        <div class="form-group">
                                            <div class="two-cols">
                                                <div class="col">
                                                    <label for="date">Ngày ký</label>
                                                    <input type="date" id="signdate" value="${param.signDate}" name="signdate" required />
                                                    <span style="color: red">${requestScope.signdateerror}</span>
                                                </div>
                                                <div class="col">
                                                    <label for="paydate">Ngày trả tiền</label>
                                                    <input type="date" id="paydate" value="${param.paymentTems}" name="paydate" required />
                                                    <span style="color: red">${requestScope.paydateerror}</span>
                                                </div>

                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="two-cols">
                                                <div class="col">
                                                    <label for="startDate">Ngày bắt đầu</label>
                                                    <input type="date" id="startDate" value="${param.startDate}" name="startDate" required />
                                                    <span style="color: red">${requestScope.startdateerror}</span>
                                                </div>
                                                <div class="col">
                                                    <label for="endDate">Ngày kết thúc</label>
                                                    <input type="date" id="endDate" value="${param.endDate}" name="endDate" required />
                                                    <span style="color: red">${requestScope.enddateerror}</span>
                                                </div>
                                            </div>
                                        </div>        
                                        <div class="form-group">
                                            <div class="two-cols">
                                                <div class="col" style="padding: 0; margin-right: 5px">
                                                    <label for="company">Công ty</label>
                                                    <select id="company" name="company">
                                                        <c:forEach items="${sessionScope.listcompany}" var="cp">
                                                            <option value="${cp.id}">${cp.name}</option>
                                                        </c:forEach>
                                                    </select>
                                                    <span style="color: red">${requestScope.companyerror}</span>
                                                </div>
                                                <div class="col" style="padding: 0; margin-right: 5px">
                                                    <label for="admin">Quản lý</label>
                                                    <select id="admin" name="admin">
                                                        <c:forEach items="${sessionScope.listadmin}" var="cp">
                                                            <option value="${cp.id}">${cp.name}</option>
                                                        </c:forEach>
                                                    </select>
                                                    <span style="color: red">${requestScope.adminerror}</span>
                                                </div>
                                                <div class="col" style="padding: 0; margin-right: 5px">
                                                    <label for="accountant">Kế toán</label>
                                                    <select id="accountant" name="accountant">
                                                        <c:forEach items="${sessionScope.listaccountant}" var="cp">
                                                            <option value="${cp.id}">${cp.name}</option>
                                                        </c:forEach>
                                                    </select>
                                                    <span style="color: red">${requestScope.accountanterror}</span>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label for="sid">Người tạo đơn</label>
                                            <input value="${sessionScope.account.pId}" type="text" id="sid" name="sid" placeholder="Enter SID" readonly="" />
                                        </div>
                                        <div class="form-group">
                                            <label class="font-weight-bold">Thêm ảnh</label>
                                            <div id="uploadContainer" class="upload-container">
                                                <div class="upload-area" data-index="0">
                                                    <div class="upload-content">
                                                        <div class="upload-icon">+</div>
                                                        <p>Chọn ảnh</p>
                                                    </div>
                                                    <input type="file" name="images[]" accept="image/jpeg" multiple>


                                                    <div class="upload-preview"></div>
                                                </div>
                                            </div>
                                            <button type="button" class="add-upload-btn" id="addUpload">+</button>
                                            <small class="form-text text-muted">Bạn có thể thêm được nhiều ảnh</small>
                                            <span style="color: red">${requestScope.fileerror}</span>
                                        </div>
                                        <div class="form-button">
                                            <button type="submit">Thêm</button>
                                            <h5 style="color:${requestScope.status == 'true' ? 'green' : 'red'}; text-align:center">
                                                ${requestScope.message}
                                            </h5>
                                            <h5 style="color:red; text-align:center">${requestScope.error}</h5>

                                            <span  style="text-decoration: underline; display: inline-block"><a><a href="view-all-contract">Trở lại</a></span>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- end form -->
                </div>
            </div>
        </div>
        <!-- jQuery -->
        <script src="js/jquery.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <!-- custom js -->
        <script src="js/custom.js"></script>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                let uploadContainer = document.getElementById("uploadContainer");
                let addUploadBtn = document.getElementById("addUpload");
                let index = 1; // B?t ??u t? 1 vì index=0 ?ã có s?n

                addUploadBtn.addEventListener("click", function () {
                    let uploadArea = document.createElement("div");
                    uploadArea.className = "upload-area";
                    uploadArea.setAttribute("data-index", index);

                    uploadArea.innerHTML = `
            <div class="upload-content">
                <div class="upload-icon">+</div>
                <p>Click or Drag an Image</p>
            </div>
            <input type="file" name="images[]" accept="image/jpeg" multiple>
            <div class="upload-preview"></div>
        `;

                    uploadContainer.appendChild(uploadArea);
                    index++;
                });
            });

        </script>
    </body>
</html>
