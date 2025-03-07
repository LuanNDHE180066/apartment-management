<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Apartment Management</title>

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="css/bootstrap.min.css" />
        <link rel="stylesheet" href="style.css" />
        <link rel="stylesheet" href="css/custom.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css"/>
    </head>
    <body class="inner_page tables_page">
        <div class="full_container">
            <div class="inner_container">
                <%@include file="sidebar.jsp" %>
                <div id="content">
                    <%@include file="topbar.jsp" %>
                    <div class="midde_cont">
                        <div class="container-fluid">
                            <div class="row column_title">
                                <div class="col-md-12">
                                    <div class="page_title">
                                        <h2>Tables</h2>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="white_shd full margin_bottom_30">
                                        <div class="full graph_head">
                                            <h2>Services Information</h2>
                                        </div>
                                        <div style="margin-left: 40px;">
                                            <form action="all-services" method="post">
                                                <div class="row align-items-center">
                                                    <div class="col-md-2">
                                                        <input id="searchInput" name="name" type="text" class="form-control" placeholder="Search by Name">
                                                    </div>
                                                    <div class="col-md-2">
                                                        <select class="form-control" name="status">
                                                            <option value="">Filter by Status</option>
                                                            <option value="1" <c:if test="${sessionScope.status == 1}">selected</c:if>>Active</option>
                                                            <option value="0" <c:if test="${sessionScope.status == 0}">selected</c:if>>Inactive</option>
                                                            </select>
                                                        </div>
                                                        <div class="col-md-2">
                                                            <select class="form-control" name="category">
                                                                <option value="" >Select category</option>
                                                            <c:forEach items="${requestScope.listCategories}" var="cs">
                                                                <option value="${cs.id}" <c:if test="${sessionScope.category == cs.id}">selected</c:if>>${cs.name}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="col-md-2">
                                                        <select class="form-control" name="company">
                                                            <option value="" >Select Company</option>
                                                            <c:forEach items="${requestScope.listCompanies}" var="c">
                                                                <option value="${c.id}" <c:if test="${sessionScope.company == c.id}">selected</c:if>>${c.name}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="col-md-4 d-flex">
                                                        <button type="submit" class="btn btn-primary me-2">Filter</button>
                                                        <a href="add-service-staff" class="btn btn-primary">Add New Service</a>
                                                    </div>
                                                </div>
                                            </form>

                                        </div>
                                        <div class="table_section padding_infor_info">
                                            <div class="table-responsive-sm">
                                                <table class="table w-100">
                                                    <thead>
                                                        <tr>
                                                            <th>Services Name</th>
                                                            <th>Unit Price</th>
                                                            <th>Unit</th>
                                                            <th>Description</th>
                                                            <th>Category</th>
                                                            <th>Supplier</th>
                                                            <th>Status</th>
                                                            <th style="width: 10%">Start</th>
                                                            <th>End</th>
                                                            <th>Action</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach items="${requestScope.listServices}" var="list">
                                                            <tr>
                                                                <td>${list.name}</td>
                                                                <td>${list.unitPrice}</td>
                                                                <td>${list.unit}</td>
                                                                <td>${list.description}</td>
                                                                <td>${list.categoryService.name}</td>
                                                                <td>${list.company.name}</td>
                                                                <td>${list.status==1?'Active':'Inactive'}</td>
                                                                <td>
                                                                    <fmt:formatDate value="${list.startDate}" pattern="dd-MM-yyyy"></fmt:formatDate>
                                                                </td>
                                                                <td>${list.endDate}</td>
                                                                <td><a href="update-service-staff?id=${list.id}"><i class="fa-solid fa-pen-to-square"></i></a></td>
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
                </div>
            </div>
        </div>
        <!-- jQuery & Bootstrap -->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
