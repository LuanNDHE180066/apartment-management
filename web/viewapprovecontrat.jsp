<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

<!--        <style>
            .table th {
                text-align: center;
                background-color: #6B90DA;
                color: black;
            }
            .approval-link {
                font-size: 20px;
                margin: 0 5px;
                color: inherit;
                text-decoration: none;
            }
        </style>-->
        <style>
            .table_section {
                width: 100%;
                margin: auto;
                text-align: center;
            }
            .table-responsive-sm {
                display: flex;
                justify-content: center;
            }
            table {
                width: 90%;
                border-collapse: collapse;
                margin: 20px auto;
                background: #fff;
                box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
            }
            th, td {
                padding: 12px;
                text-align: center;
                border: 1px solid #ddd;
            }
            th {
                background: #007bff;
                color: white;
            }
            tr:nth-child(even) {
                background: #f9f9f9;
            }
            h3 {
                text-align: center;
                color: #007bff;
                margin-bottom: 10px;
            }
            .approval-link {
                text-decoration: none;
                padding: 5px 10px;
                border-radius: 5px;
                margin: 5px;
            }
            .approval-link i {
                margin-right: 5px;
            }
            .approval-link:first-of-type {
                background: green;
                color: white;
            }
            .approval-link:last-of-type {
                background: red;
                color: white;
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
                                        <div class="heading1 margin_0">
                                            <h2>Pending Contract Information</h2>
                                        </div>
                                        <div class="table_section padding_infor_info">
                                            <h3>${requestScope.message}</h3>
                                            <div class="table-responsive-sm">
                                                <table class="table w-100" id="table-infor">
                                                    <thead>
                                                        <tr>
                                                            <th>Order Creator</th>
                                                            <th>Created</th>
                                                            <th>Option</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach items="${requestScope.listContract}" var="c">
                                                            <tr>
                                                                <td>${c.admin.name}</td>
                                                                <td>${c.createdAt}</td>
                                                                <td>
                                                                    <c:if test="${staffId == c.admin.id && (c.adminApproval == null || c.adminApproval == 0)}">
                                                                        <a class="approval-link" href="update-pending-contract?id=${c.id}&approve=1">
                                                                            <i class="fas fa-check"></i> Approve
                                                                        </a>
                                                                        <a class="approval-link" href="update-pending-contract?id=${c.id}&approve=2">
                                                                            <i class="fas fa-times"></i> Reject
                                                                        </a>
                                                                    </c:if>
                                                                    <c:if test="${c.adminApproval == 1}">
                                                                        <span style="color: green; font-weight: bold;">Approved</span>
                                                                    </c:if>
                                                                    <c:if test="${c.adminApproval == 2}">
                                                                        <span style="color: red; font-weight: bold;">Rejected</span>
                                                                    </c:if>
                                                                </td>
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
