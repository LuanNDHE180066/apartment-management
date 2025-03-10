<!DOCTYPE html>
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
        <style>
            .contract_section {
                background: #f8f9fa;
                padding: 15px;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }

            .contract-list {
                list-style: none;
                padding: 0;
                margin: 0;
            }

            .contract-list li {
                background: white;
                padding: 10px;
                border-radius: 6px;
                margin-bottom: 8px;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
                display: flex;
                align-items: center;
                transition: all 0.3s ease-in-out;
            }

            .contract-list li:hover {
                background: #e9ecef;
                transform: translateY(-2px);
            }

            .contract-list a {
                text-decoration: none;
                font-weight: bold;
                color: #007bff;
                margin-left: 5px;
            }

            .contract-list a:hover {
                text-decoration: underline;
            }

            .contract-icon {
                font-size: 18px;
                margin-right: 10px;
                color: #28a745;
            }

            .contract-date {
                font-size: 14px;
                color: #6c757d;
                margin-right: 8px;
            }
            h4 {
    margin-bottom: 20px; /* T?ng kho?ng cách */
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
                                                <h2>Recently Contracts</h2>
                                            </div>
                                            <br> <hr>
                                            <div>
                                                <form action="view-all-contract" method="get">
                                                    <div class="row align-items-center">
                                                        <div class="col-md-8">
                                                            <div class="row align-items-center">
                                                                <div class="col-md-3">
                                                                    <input type="text" class="form-control" name="title" placeholder="Enter title"
                                                                           value="${requestScope.title}">
                                                                </div>
                                                                <div class="col-md-3">
                                                                    <input type="date" class="form-control" name="startDate" placeholder="From"
                                                                           value="${requestScope.startDate}">
                                                                </div>
                                                                <div class="col-md-3">
                                                                    <input type="date" class="form-control" name="endDate" placeholder="To"
                                                                           value="${requestScope.endDate}">
                                                                </div>
                                                                <div class="col-md-3 d-flex">
                                                                    <button type="submit" class="btn btn-primary" style="margin-right: 5px;">Filter</button>
                                                                    <c:if test="${sessionScope.account.roleId == 0}">
                                                                        <a href="add-new-contract" class="btn btn-primary">Add Contract</a>
                                                                    </c:if>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </form>


                                            </div>
                                        </div>

                                        <div class="contract_section">
                                            <ul class="contract-list">
                                                <c:if test="${not empty sessionScope.listContract}">
                                                    <c:forEach var="c" items="${sessionScope.listContract}">
                                                        <li>
                                                            <span class="contract-icon"></span>
                                                            <span class="contract-date">${c.startDate}</span> 
                                                            <a href="contract-detail?id=${c.id}">${c.title}</a>
                                                        </li>
                                                    </c:forEach>
                                                </c:if>
                                            </ul>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <form method="get" action="view-all-contract">
                        <input type="hidden" name="title" value="${requestScope.title}">
                        <input type="hidden" name="startDate" value="${requestScope.startDate}">
                        <input type="hidden" name="endDate" value="${requestScope.endDate}">

                        <label for="page" style="font-size: 14px; font-weight: bold;">Page:</label>
                        <select id="page" name="page" onchange="this.form.submit()"
                                style="padding: 6px 12px; font-size: 14px; border: 1px solid #ddd; border-radius: 4px; cursor: pointer;">
                            <c:forEach begin="1" end="${requestScope.totalPage}" var="page">
                                <option value="${page}" <c:if test="${page == requestScope.currentPage}">selected</c:if>>
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