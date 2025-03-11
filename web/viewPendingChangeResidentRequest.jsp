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
                                            <h2>Pending Change Living/Owner Residents</h2>
                                        </div>

                                        <div class="table_section padding_infor_info">
                                            <div class="table-responsive-sm">
                                                <table class="table w-100 " id="table-infor">
                                                    <thead>
                                                        <tr>
                                                            <th>Created Date</th>
                                                            <th>Created By</th>
                                                            <th>Apartment Number</th>
                                                            <th>Change Role</th
                                                            <th>Modified Staff</th>                                                        
                                                            <th>New Living/Owner</th>
                                                            <th>Is Exist</th>
                                                            <th>Option</th>
                                                            <!--       <th>Note</th> -->
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                    <h3>${requestScope.message}</h3>
                                                    <c:forEach items="${requestScope.listChangeRequest}" var="request">
                                                        <tr>
<!--                                                            <td style="text-align: center"> ${request.createdAt}</td>-->
                                                            <td  style="text-align: center">
                                                                ${request.createdAt}
                                                            </td>  
                                                            <!--                                                            <td  style="text-align: center">
                                                            <%--%>         <fmt:setLocale value="vi_VN"/> <%-- Thi?t l?p locale v? Vi?t Nam --%>
                                                            <%--        <fmt:formatNumber value="${expenditure.totalPrice}" type="currency" currencyCode="VND" maxFractionDigits="0"/> --%>
                                                            ${request.owner.name}
                                                        </td>--> 

                                                            <td style="text-align: center">
                                                                <a href="#" data-toggle="modal" data-target="#ownerModal-${request.owner.pId}">
                                                                    ${request.owner.name}
                                                                </a>
                                                                <!-- Modal Owner -->
                                                                <div class="modal fade" id="ownerModal-${request.owner.pId}" tabindex="-1" role="dialog" aria-labelledby="ownerModalLabel-${request.requestId}" aria-hidden="true">
                                                                    <div class="modal-dialog" role="document">
                                                                        <div class="modal-content">
                                                                            <div class="modal-header">
                                                                                <h5 class="modal-title">Owner Information</h5>
                                                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                                    <span aria-hidden="true">&times;</span>
                                                                                </button>
                                                                            </div>
                                                                            <div class="modal-body">
                                                                                <div class="text-center" style="margin-bottom: 15px">
                                                                                    <img src="images\avatar\kuru.jpg" alt="Profile Image"  class="img-fluid rounded-circle" style="width: 150px; height: 150px;">
                                                                                </div>
                                                                                <table class="table">
                                                                                    <tr><th>PID</th><td>${request.owner.pId}</td></tr>
                                                                                    <tr><th>Name</th><td>${request.owner.name}</td></tr>
                                                                                    <tr><th>Date of Birth</th><td>${request.owner.bod}</td></tr>
                                                                                    <tr><th>Phone</th><td>${request.owner.phone}</td></tr>
                                                                                    <tr><th>Email</th><td>${request.owner.email}</td></tr>
                                                                                    <tr><th>CCCD</th><td>${request.owner.cccd}</td></tr>
                                                                                    <tr><th>Address</th><td>${request.newPerson.address}</td></tr>
                                                                                    <tr><th>Gender</th><td>${request.newPerson.gender}</td></tr>
                                                                                    
                                                                                </table>
                                                                            </div>
                                                                            <div class="modal-footer">
                                                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </td>
                                                            <td style="text-align: center">${request.roomNumber}</td>

                                                            <td style="text-align: center">${request.changeType == 1? 'Owner':'Living Resident'}</td>
                                                            <td style="text-align: center">
                                                                <a href="#" data-toggle="modal" data-target="#newPersonModal-${request.newPerson.pId}">
                                                                    ${request.newPerson.name}
                                                                </a>
                                                                <!-- Modal New Person -->
                                                                <div class="modal fade" id="newPersonModal-${request.newPerson.pId}" tabindex="-1" role="dialog" aria-labelledby="newPersonModalLabel-${request.requestId}" aria-hidden="true">
                                                                    <div class="modal-dialog" role="document">
                                                                        <div class="modal-content">
                                                                            <div class="modal-header">
                                                                                <h5 class="modal-title">New Resident Information</h5>
                                                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                                    <span aria-hidden="true">&times;</span>
                                                                                </button>
                                                                            </div>
                                                                            <div class="modal-body">

                                                                                <table class="table">
                                                                                    <tr><th>Name</th><td>${request.newPerson.name}</td></tr>
                                                                                    <tr><th>Phone</th><td>${request.newPerson.phone}</td></tr>
                                                                                    <tr><th>Email</th><td>${request.newPerson.email}</td></tr>
                                                                                    <tr><th>Date of Birth</th><td>${request.newPerson.bod}</td></tr>
                                                                                    <tr><th>Address</th><td>${request.newPerson.address}</td></tr>
                                                                                    <tr><th>Gender</th><td>${request.newPerson.gender}</td></tr>
                                                                                    <tr><th>CCCD</th><td>${request.newPerson.cccd}</td></tr>
                                                                                </table>
                                                                            </div>
                                                                            <div class="modal-footer">
                                                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </td>
                                                            <td style="text-align: center; color: ${request.newPersonExists == 1 ? 'Green' : 'Red'};">
                                                                ${request.newPersonExists == 1?'Yes':'No'}
                                                            </td>

                                                            <td style="text-align: center">
                                                                <a href="accept.jsp?id=${request.requestId}" title="Accept" style="margin-left: 10px;">
                                                                    <i class="fas fa-check" style="font-size: 20px; color: green;"></i>
                                                                </a>
                                                                <a href="reject.jsp?id=${request.requestId}" title="Reject" style="margin-left: 10px;">
                                                                    <i class="fas fa-times" style="font-size: 20px; color: red;"></i>
                                                                </a>
                                                            </td>
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