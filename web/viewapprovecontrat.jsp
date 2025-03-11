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

        <style>
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
                                            <div class="table-responsive-sm">
                                                <table class="table w-100" id="table-infor">
                                                    <thead>
                                                        <tr>
                                                            <th>Admin ID</th>
                                                            <th>Accountant ID</th>
                                                            <th>Created</th>
                                                            <th>Option</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                    <h3>${requestScope.message}</h3>
                                                    <c:forEach items="${requestScope.listContract}" var="c">
                                                        <tr>
                                                            <td>${c.admin.name}</td>
                                                            <td>${c.accountant.name}</td>
                                                            <td>${c.createdAt}</td>
                                                            <td>
                                                                <!-- Show approval options for admin if adminApproval is 0 or null -->
                                                                <c:if test="${staffId == c.admin.id && (c.adminApproval == null || c.adminApproval == 0)}">
                                                                    <a class="approval-link" href="update-pending-contract?contractId=${c.contractId}&approve=1">
                                                                        <i class="fas fa-check"></i> Approve
                                                                    </a>
                                                                    <a class="approval-link" href="update-pending-contract?contractId=${c.contractId}&approve=-1">
                                                                        <i class="fas fa-times"></i> Reject
                                                                    </a>
                                                                </c:if>

                                                                <!-- Show approval options for accountant if accountantApproval is 0 or null -->
                                                                <c:if test="${staffId == c.accountant.id && (c.accountantApproval == null || c.accountantApproval == 0)}">
                                                                    <a class="approval-link" href="update-pending-contract?contractId=${c.contractId}&approve=1">
                                                                        <i class="fas fa-check"></i> Approve
                                                                    </a>
                                                                    <a class="approval-link" href="update-pending-contract?contractId=${c.contractId}&approve=-1">
                                                                        <i class="fas fa-times"></i> Reject
                                                                    </a>
                                                                </c:if>

                                                                <!-- Show approval status for admin or accountant if either has approved -->
                                                                <c:if test="${c.adminApproval == 1 || c.accountantApproval == 1}">
                                                                    <span style="color: green">Approved</span>
                                                                </c:if>

                                                                <!-- Show rejection status if either has rejected -->
                                                                <c:if test="${c.adminApproval == 2 || c.accountantApproval == 2}">
                                                                    <span style="color: red">Rejected</span>
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
                            <p>Copyright � 2025 Designed by Your Company. All rights reserved.</p>
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
