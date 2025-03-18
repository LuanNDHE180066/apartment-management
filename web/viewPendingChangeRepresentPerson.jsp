<%-- 
    Document   : viewallexpenditure
    Created on : Feb 24, 2025, 4:52:47 PM
    Author     : PC
--%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://fonts.googleapis.com/css2?family=Noto+Sans&display=swap" rel="stylesheet">
        <title>Pending Expenditure</title>
        <link rel="icon" href="images/fevicon.png" type="image/png" />
        <link rel="stylesheet" href="css/bootstrap.min.css" />
        <link rel="stylesheet" href="style.css" />
        <link rel="stylesheet" href="css/responsive.css" />
        <link rel="stylesheet" href="css/custom.css" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <style>
            body {
                /* Style body n?u c?n */
            }
            .contract-list {
                padding-left: 20px; /* Kho?ng cách bên trái */
            }
            .contract-list li {
                padding: 10px; /* Padding cho t?ng m?c */
                font-size: 18px; /* Kích th??c ch? cho t?ng m?c */
            }
            .contract-list li a {
                text-decoration: none; /* B? g?ch chân */
                color: #007bff; /* Màu liên k?t */
            }
            .contract-list li a:hover {
                color: #0056b3; /* Màu khi hover */
            }
            .graph_head {
                margin-bottom: 20px; /* Gi?m kho?ng cách d??i tiêu ?? */
            }
            button {
                background: none;
                border: none;
                cursor: pointer;
                font-size: 24px;
                margin: 0 5px;
            }
            .approval-link {
                font-size: 20px;
                margin: 0 5px;
                color: inherit;
                text-decoration: none;
            }
            .table th {
                text-align: center;
                background-color: #6B90DA;
                color: black;
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

                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="white_shd full margin_bottom_30">

                                        <div class="heading1 margin_0">
                                            <h2 style="margin-left: 30px">Pending Change Represent Residents</h2>
                                        </div>

                                        <div class="table_section padding_infor_info">
                                            <div class="table-responsive-sm">
                                                <table class="table w-100 " id="table-infor">
                                                    <thead>
                                                        <tr>
                                                            <th>Created Date</th>
                                                            <th>Old Represent Person</th>
                                                            <th>New Represent Person</th>
                                                            <th>Apartment Number</th>                                                                     
                                                            <th>Is Exist</th>
                                                            <th>Option</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
<!--                                                    <h3>${requestScope.message}</h3>-->
                                                        <c:forEach items="${requestScope.listChangeRepresentPerson}" var="request">
                                                            <tr>
                                                                <!--                                                            <td style="text-align: center"> </td>-->
                                                                <td  style="text-align: center">
                                                                    ${request.requestDate}

                                                                <td style="text-align: center">
                                                                    <a href="#" data-toggle="modal" data-target="#ownerModal-${request.oldRepresentPerson.pId}">
                                                                        ${request.oldRepresentPerson.name}
                                                                    </a>
                                                                    <!-- Modal Owner -->
                                                                    <div class="modal fade" id="ownerModal-${request.oldRepresentPerson.pId}" tabindex="-1" role="dialog" aria-labelledby="ownerModalLabel-${request.id}" aria-hidden="true">
                                                                        <div class="modal-dialog" role="document">
                                                                            <div class="modal-content">
                                                                                <div class="modal-header">
                                                                                    <h5 class="modal-title">Old Represent Person</h5>
                                                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                                        <span aria-hidden="true">&times;</span>
                                                                                    </button>
                                                                                </div>
                                                                                <div class="modal-body">
                                                                                    <div class="text-center" style="margin-bottom: 15px">
                                                                                        <img src="images\avatar\kuru.jpg" alt="Profile Image"  class="img-fluid rounded-circle" style="width: 150px; height: 150px;">
                                                                                    </div>
                                                                                    <table class="table">
                                                                                        <tr><th>PID</th><td>${request.oldRepresentPerson.pId}</td></tr>
                                                                                        <tr><th>Name</th><td>${request.oldRepresentPerson.name}</td></tr>
                                                                                        <tr><th>Date of Birth</th><td>${request.oldRepresentPerson.bod}</td></tr>
                                                                                        <tr><th>Phone</th><td>${request.oldRepresentPerson.phone}</td></tr>
                                                                                        <tr><th>Email</th><td>${request.oldRepresentPerson.email}</td></tr>
                                                                                        <tr><th>CCCD</th><td>${request.oldRepresentPerson.cccd}</td></tr>
                                                                                        <tr><th>Address</th><td>${request.oldRepresentPerson.address}</td></tr>
                                                                                        <tr><th>Gender</th><td>${request.oldRepresentPerson.gender}</td></tr>

                                                                                    </table>
                                                                                </div>
                                                                                <div class="modal-footer">
                                                                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                                <td>
                                                                    <a href="#" data-toggle="modal" data-target="#newPersonModal-${request.newRepresentPerson.pId}">
                                                                        ${request.newRepresentPerson.name}
                                                                    </a>
                                                                    <!-- Modal New Person -->
                                                                    <div class="modal fade" id="newPersonModal-${request.newRepresentPerson.pId}" tabindex="-1" role="dialog" aria-labelledby="newPersonModalLabel-${request.id}" aria-hidden="true">
                                                                        <div class="modal-dialog" role="document">
                                                                            <div class="modal-content">
                                                                                <div class="modal-header">
                                                                                    <h5 class="modal-title">New Resident Information</h5>
                                                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                                        <span aria-hidden="true">&times;</span>
                                                                                    </button>
                                                                                </div>
                                                                                <div class="modal-body">
                                                                                    <div class="text-center" style="margin-bottom: 15px">
                                                                                        <img src="images\avatar\kuru.jpg" alt="Profile Image"   width="150px"  height="150px" >
                                                                                    </div>
                                                                                    <table class="table">
                                                                                        <tr><th>Name</th><td>${request.newRepresentPerson.name}</td></tr>
                                                                                        <tr><th>Phone</th><td>${request.newRepresentPerson.phone}</td></tr>
                                                                                        <tr><th>Email</th><td>${request.newRepresentPerson.email}</td></tr>
                                                                                        <tr><th>Date of Birth</th><td>${request.newRepresentPerson.bod}</td></tr>
                                                                                        <tr><th>Address</th><td>${request.newRepresentPerson.address}</td></tr>
                                                                                        <tr><th>Gender</th><td>${request.newRepresentPerson.gender}</td></tr>
                                                                                        <tr><th>CCCD</th><td>${request.newRepresentPerson.cccd}</td></tr>
                                                                                    </table>
                                                                                </div>
                                                                                <div class="modal-footer">
                                                                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                                <td>
                                                                    ${request.aId}
                                                                </td>

                                                                <td  style="color: ${request.isExistAccount == 1 ? 'Green':'Red'}"};>
                                                                    ${request.isExistAccount == 1 ? 'Yes':'No'}
                                                                </td>


                                                                <td style="text-align: center">
                                                                    <a href="update-pending-change-represent-request-status?id=${request.id}&approve=1" title="Accept" style="margin-left: 10px;">
                                                                        <i class="fas fa-check" style="font-size: 20px; color: green;"></i>
                                                                    </a>
                                                                    <a href="update-pending-change-represent-request-status?id=${request.id}&approve=-1" title="Reject" style="margin-left: 10px;">
                                                                        <i class="fas fa-times" style="font-size: 20px; color: red;"></i>
                                                                    </a>
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
                    <form method="get" action="view-change-resident-request" style="display: flex; align-items: center; gap: 10px;">
                        <label for="page" style="font-size: 14px; font-weight: bold;">Page:</label>
                        <select id="page" name="page" onchange="this.form.submit()" 
                                style="padding: 6px 12px; font-size: 14px; border: 1px solid #ddd; border-radius: 4px; cursor: pointer;">
                            <c:forEach begin="1" end="${requestScope.totalPage}" var="page">
                                <option value="${page}" <c:if test="${page == requestScope.currentPage}">selected</c:if>>
                                    ${page}
                                </option>
                            </c:forEach>
                            <!-- comment -->
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