<%-- 
    Document   : updateroomtype.jsp
    Created on : Feb 14, 2025, 2:39:58 PM
    Author     : PC
--%>

<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <html lang="en">
        <head>
            <!-- basic -->
            <meta charset="utf-8" />
            <meta http-equiv="X-UA-Compatible" content="IE=edge" />
            <!-- mobile metas -->
            <meta name="viewport" content="width=device-width, initial-scale=1" />
            <meta name="viewport" content="initial-scale=1, maximum-scale=1" />
            <!-- site metas -->
            <title>Apartment management</title>            <link rel="icon" href="images/fevicon.png" type="image/png" />
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
                                    <h1>Cập nhập yêu cầu</h1>
                                    <form action="update-request-staff" method="post">
                                        <input type="text" id="id" name="id" value="${requestScope.rq.id}" hidden=""/>
                                        <div class="form-group">
                                            <div class="one-col" style="padding: 0; margin-right: 5px">
                                                <label for="name">Tên dân cư</label>
                                                <input
                                                    type="text"
                                                    id="name"
                                                    name="name"
                                                    readonly=""
                                                    value="${requestScope.rq.residentId.name}"
                                                    required=""
                                                    />
                                            </div>
                                        </div>  
                                        <div class="form-group">
                                            <label for="aparment">Căn hộ</label>
                                            <input
                                                    type="text"
                                                    id="aparment"
                                                    name="aparment"
                                                    readonly=""
                                                    value="${requestScope.rq.aid.id}"
                                                    required=""
                                                    />
                                        </div>
                                        <div class="form-group">
                                            <div class="col" style="padding: 0; margin-right: 5px">
                                                <label for="detail">Chi tiết yêu cầu</label>
                                                <input
                                                    type="text"
                                                    id="detail"
                                                    name="detail"
                                                    value="${requestScope.rq.detail}"
                                                    required=""
                                                    readonly=""
                                                    />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col" style="padding: 0; margin-right: 5px">
                                                <label for="response">Trả lời</label>
                                                <input
                                                    type="text"
                                                    id="response"
                                                    name="response"\
                                                    value="${requestScope.rq.response}"
                                                    
                                                    />
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="two-cols">
                                                <div class="col" style="padding: 0; margin-right: 5px">
                                                    <label for="date">Ngày gửi</label>
                                                    <input
                                                        type="text"
                                                        readonly=""
                                                        id="date"
                                                        name="date"
                                                        value="${requestScope.rq.date}"
                                                        required=""
                                                        />
                                                </div>
                                                <div class="col" style="padding: 0; margin-right: 5px">
                                                    <label for="typerequest">Loại</label>
                                                    <input
                                                        type="text"
                                                        id="typerequest"
                                                        name="typerequest"
                                                        value="${requestScope.rq.requestType.name}"
                                                        required=""
                                                        readonly=""
                                                        />
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group" >
                                            <div class="col" style="padding: 0; margin-right: 5px">
                                                <label for="balcony">Trạng thái</label>
                                                <select name="status">
                                                    <!--<option value="Wating">Waiting</option>-->
                                                    <option value="1">In process</option>
                                                    <option value="2">Done</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-button">
                                            <button type="submit">Cập nhập</button>
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
        </body>
    </html>
