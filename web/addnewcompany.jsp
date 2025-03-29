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
        <style> body {
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
            }
            .error-message {
                color: red;
                font-size: 14px;
                margin-top: 5px;
            }
        </style>


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
                                    <h1>Thêm công ty</h1>
                                    <form action="add-new-company" method="post">
                                        <div class="form-group">
                                            <label for="name">Tên</label>
                                            <input type="text" id="name" name="name" value="${param.company.name}" placeholder="Nhập tên công ty" required />
                                            <p class="error-message">${requestScope.nameError}</p>
                                        </div>

                                        <div class="form-group">
                                            <label for="phone">Số tư vấn khách hàng</label>
                                            <input type="tel" id="phone" name="phone" value="${param.company.phone}" placeholder="Nhập số tư vấn khách hàng" required />
                                            <p class="error-message">${requestScope.phoneError}</p>
                                        </div>

                                        <div class="form-group">
                                            <label for="contactPhone">Số liên lạc</label>
                                            <input type="tel" id="contactPhone" name="contactPhone" value="${param.company.contactPhone}" placeholder="Nhập số liên lạc" required />
                                            <p class="error-message">${requestScope.contactPhoneError}</p>
                                        </div>
                                        <div class="form-group">
                                            <label for="website">Website</label>
                                            <input type="text" id="website" name="website" value="${param.company.website}" placeholder="Nhập website" required />
                                            <p class="error-message">${requestScope.websiteError}</p>
                                        </div>
                                        <div class="form-group">
                                            <label for="fax">Số Fax</label>
                                            <input type="text" id="fax" name="fax" value="${param.company.fax}" placeholder="Nhập số fax" required />
                                            <p class="error-message">${requestScope.faxError}</p>
                                        </div>

                                        <div class="form-group">
                                            <label for="email">Email tư vấn</label>
                                            <input type="email" id="email" name="email" value="${param.company.email}" placeholder="Nhập email" required />
                                            <p class="error-message">${requestScope.emailError}</p>
                                        </div>

                                        <div class="form-group">
                                            <label for="contactemail">Email liên hệ</label>
                                            <input type="email" id="contactemail" name="contactemail" value="${param.company.contactemail}" placeholder="Nhập email liên hệ" required />
                                            <p class="error-message">${requestScope.contactEmailError}</p>
                                        </div>

                                        <div class="form-group">
                                            <label for="taxCode">Mã số thuế</label>
                                            <input type="text" id="taxCode" name="taxCode" value="${param.company.taxCode}" placeholder="Nhập mã số thuế" required />
                                            <p class="error-message">${requestScope.taxCodeError}</p>
                                        </div>

                                        <div class="form-group">
                                            <label for="bank">Ngân hàng</label>
                                            <input type="text" id="bank" name="bank" value="${param.company.bank}" placeholder="Nhập ngân hàng" required />
                                            <p class="error-message">${requestScope.bankError}</p>
                                        </div>

                                        <div class="form-group">
                                            <label for="address">Địa chỉ</label>
                                            <input type="text" id="address" name="address" value="${param.company.address}" placeholder="Nhập địa chỉ" required />
                                            <p class="error-message">${requestScope.addressError}</p>
                                        </div>

                                        <div class="form-group">
                                            <label for="description">Mô tả</label>
                                            <input type="text" id="description" name="description" value="${param.company.description}"  placeholder="Nhập mô tả" required="" />
                                        </div>
                                        <div class="form-button">
                                            <button type="submit">Thêm</button>
                                            <h5 style="color:${status=="true"?"green":"red"};text-align:center ">${requestScope.message}</h5>
                                            <h5 style="color:red;text-align:center">${requestScope.error}</h5>
                                            <span  style="text-decoration: underline; display: inline-block"><a><a href="view-all-company">Trở lại</a></span>
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
        <script src="js/custom.js"></script>

    </body>
</html>
