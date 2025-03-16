<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">

    <head>
        <!-- basic -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- mobile metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="icon" href="images/fevicon.png" type="image/png" />
        <!-- bootstrap css -->
        <link rel="stylesheet" href="css/bootstrap.min.css" />
        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" />
        <!-- site css -->
        <link rel="stylesheet" href="style.css" />
        <style>
            body {
                background-color: #f8f9fa;
            }

            .table {
                margin-top: 20px;
                background-color: white;
                border-radius: 8px;
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            }

            .table th {
                background-color: #6B90DA;
                color: white;
            }

            .table td {
                vertical-align: middle;
            }

            .modal-content {
                border-radius: 8px;
                display: flex;
            }

            .modal-header {
                background-color: #6B90DA;
                color: white;
                border-top-left-radius: 8px;
                border-top-right-radius: 8px;
                width: 100%;
            }

            .modal-body {
                display: flex;
                align-items: center;
                padding: 20px;
            }

            .modal-body img {
                width: 180px; /* Increased image size */
                height: 180px; /* Increased image size */
                border-radius: 50%;
                object-fit: cover;
                margin-right: 30px; /* Increased margin for spacing */
            }

            .modal-info {
                display: flex;
                flex-direction: column;
                justify-content: center;
            }

            .modal-info p {
                margin: 5px 0; /* Space between each info line */
            }

            .footer {
                text-align: center;
                padding: 20px 0;
                background-color: #f1f1f1;
                border-top: 1px solid #ddd;
            }

            .btn-link {
                color: #007bff;
                text-decoration: underline;
                cursor: pointer;
            }

            .btn-link:hover {
                color: #0056b3;
                text-decoration: none;
            }

            .info-icon {
                margin-right: 8px; /* Space between icon and text */
                color: #6B90DA; /* Icon color */
            }
        </style>
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
                                        <h2></h2>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="white_shd full margin_bottom_30">
                                        <div class="full graph_head">
                                            <div class="heading1 margin_0">
                                                <h2>Living History</h2>
                                            </div>
                                        </div>
                                        <div style="margin-left: 40px;">
                                            <form action="view-apartment-admin" method="GET">
                                                <div class="row align-items-center">
                                                    <div class="col-md-2">
                                                        <input type="date"  class="form-control" name="startDate" >
                                                    </div>
                                                      <div class="col-md-2">
                                                        <input type="date"  class="form-control" name="endDate" >
                                                    </div>
                                                    <div class="col-md-2">
                                                        <select class="form-control" name="filterStatus">
                                                            <option value="">Filter by Status</option>
                                                            <option value="1" ${requestScope.filterStatus == '1' ? 'selected' : ''}>Active</option>
                                                            <option value="0" ${requestScope.filterStatus == '0' ? 'selected' : ''}>InActive</option>
                                                        </select>
                                                    </div>
                                                    <div class="col-md-4 d-flex">
                                                        <button type="submit" class="btn btn-primary" style="margin-right: 5px;">Filter</button>
                                                    </div>
                                                </div>
                                            </form>
                                                        <span  style="font-style: italic; margin-top: 10px">*Search by start living date</span>
                                        </div>
                                        <div class="table_section padding_infor_info">
                                            <div class="table-responsive-sm">
                                                <table class="table w-100" id="table-infor">
                                                    <thead>
                                                        <tr>
                                                            <th>Person</th>
                                                            <th>Start Date</th>
                                                            <th>End Date</th>
                                                            <th>Status</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach items="${requestScope.historyOfLiving}" var="item">
                                                            <tr>
                                                                <td>
                                                                    <button class="btn btn-link" data-toggle="modal" data-target="#modal${item.rid.pId}">
                                                                        ${item.rid.name}
                                                                    </button>

                                                                    <!-- Modal for Resident Details -->
                                                                    <div class="modal fade" id="modal${item.rid.pId}" tabindex="-1" role="dialog">
                                                                        <div class="modal-dialog" style="max-width: 600px;">
                                                                            <div class="modal-content">
                                                                                <div class="modal-header">
                                                                                    <h3>Resident Information</h3>
                                                                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                                                </div>
                                                                                <div class="modal-body">
                                                                                    <img src="${item.rid.image == null ? 'images/logo/person.jpg' : item.rid.image}" alt="Resident Image"/>
                                                                                    <div class="modal-info">
                                                                                        <p><i class="fas fa-user info-icon"></i><strong>Name:</strong> ${item.rid.name}</p>
                                                                                        <p><i class="fas fa-id-card info-icon"></i><strong>ID Card:</strong> ${item.rid.cccd}</p>
                                                                                        <p><i class="fas fa-calendar-alt info-icon"></i><strong>Date of Birth:</strong> ${item.rid.bod}</p>
                                                                                        <p><i class="fas fa-envelope info-icon"></i><strong>Email:</strong> ${item.rid.email}</p>
                                                                                        <p><i class="fas fa-phone info-icon"></i><strong>Phone:</strong> ${item.rid.phone}</p>
                                                                                        <p><strong>Address:</strong> ${item.rid.address}</p>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                                <td>${item.startDate}</td>
                                                                <td style="color: ${item.endDate == null ? 'green' : 'red'}">${item.endDate == null ? 'Present' : item.endDate}</td>
                                                                <td style="color: ${item.status == 1 ? 'green' : 'red'}">${item.status == 1 ? 'Current' : 'Past'}</td>
                                                            </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                </table>
                                                <span><a href="viewdetailapartment-admin?apartmentId=${param.aid}">Back</a></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="container-fluid">
                            <div class="footer">
                                <p>Copyright © 2018 Designed by html.design. All rights reserved.</p>
                            </div>
                        </div>
                    </div>
                </div>
                <script src="js/jquery.min.js"></script>
                <script src="js/popper.min.js"></script>
                <script src="js/bootstrap.min.js"></script>
                <script src="js/custom.js"></script>
                <script>
                    $(document).ready(function () {
                        // When the modal is shown
                        $('.modal').on('show.bs.modal', function () {
                            // You can add any specific styles here if needed
                        });

                        // When the modal is hidden
                        $('.modal').on('hidden.bs.modal', function () {
                            // Clear all styles from the body
                            $('body').attr('style', '');
                        });
                    });
                </script>
            </div>
        </div>
    </body>
</html>