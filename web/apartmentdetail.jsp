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
        <!-- font awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css"/>
        <style>
            /* Modal styles */
            .modal-header, .modal-footer {
                border: none;
                background-color: #f8f9fa; /* Light background for header/footer */
            }
            .modal-header h3 {
                margin: 0;
                color: #343a40; /* Darker text color */
            }
            .modal-body {
                display: flex;
                padding: 20px; /* Padding for the body */
            }
            .modal-body img {
                margin: 0 10px;
                border-radius: 5px; /* Rounded corners for images */
            }
            .modal-body div {
                padding: 10px; /* Padding for inner divs */
            }

            /* Button styles */
            .btn-custom {
                display: inline-block;
                background-color: #0056b3; /* Bootstrap primary color */
                border: none;
                border-radius: 5px;
                text-decoration: none;
                font-weight: bold;
                transition: background-color 0.3s, transform 0.2s; /* Added transform for a subtle effect */
                text-align: center;
                margin: 10px 5px; /* Spacing between buttons */
            }

            .btn-custom:hover {
                background-color: #004494; /* Darker shade on hover */
                transform: translateY(-2px); /* Slight lift effect on hover */
            }

            .btn-custom:active {
                transform: translateY(0); /* Remove lift effect when button is pressed */
            }

            /* Flexbox style for Living Residents */
            .living-residents {
                display: flex;
                align-items: center; /* Center align items vertically */
                flex-wrap: wrap; /* Allow wrapping for small screens */
                margin-bottom: 10px;
            }

            .living-residents span {
                margin-right: 10px; /* Space between label and buttons */
            }
        </style>
    </head>
    <body class="inner_page tables_page">
        <div class="full_container">
            <div class="inner_container">
                <!-- Sidebar -->
                <%@include file="sidebar.jsp" %>
                <!-- end sidebar -->
                <!-- right content -->
                <div id="content">
                    <!-- topbar -->
                    <%@include file="topbar.jsp" %>
                    <!-- end topbar -->
                    <div class="midde_cont">
                        <div class="container-fluid">
                            <div class="row column_title">
                                <div class="col-md-12">
                                </div>
                            </div>
                            <div class="row">
                                <!-- Apartment Information -->
                                <div class="col-md-12">
                                    <div class="white_shd full margin_bottom_30">
                                        <div class="full graph_head">
                                            <div class="heading1 margin_0">
                                                <h2>Apartment Details</h2>
                                            </div>
                                        </div>
                                        <div style="display: flex; width: 100%; gap: 20px; align-items: center;">
                                            <div style="flex: 1; max-width: 50%; text-align: center;">
                                                <img style="width: 100%; height: auto; object-fit: cover;" src="images/logo/house.jpg" alt="Apartment Image"/>
                                            </div>
                                            <div style="flex: 1; max-width: 40%; margin-left: 15%;">
                                                <jsp:useBean id="laDAO" class="dao.LivingApartmentDAO" scope="page"></jsp:useBean>
                                                <p><span style="font-weight: bold">ApartmentID: </span> ${requestScope.apartmentDetail.id}</p>
                                                <p><span style="font-weight: bold">Number of persons: </span> ${laDAO.getNumberOfLivingPerson(requestScope.apartmentDetail.id)}</p>
                                                <p><span style="font-weight: bold">Floor: </span> ${requestScope.apartmentDetail.floor.number}</p>
                                                <p><span style="font-weight: bold">Information: </span> ${requestScope.apartmentDetail.infor}</p>
                                                <p><span style="font-weight: bold">Room Type: </span> ${requestScope.apartmentDetail.roomtype.name}</p>
                                                <p><span style="font-weight: bold">Status: </span> ${requestScope.apartmentDetail.status}</p>
                                                <div class="living-residents">
                                                    <span style="font-weight: bold; color: #58718A;">Living Residents:</span>
                                                    <c:forEach var="resident" items="${requestScope.livingResidents}">
                                                        <button class="btn btn-info" style="margin: 0 2px; padding: 2px 5px; font-size: 12px;" data-toggle="modal" data-target="#residentModal${resident.pId}">
                                                            ${resident.name}
                                                        </button>
                                                        <div class="modal fade" style="margin-top: 100px" id="residentModal${resident.pId}" tabindex="-1" role="dialog">
                                                            <div class="modal-dialog" style="max-width: 40%">
                                                                <div class="modal-content">
                                                                    <div class="modal-header">
                                                                        <h3>Resident Information</h3>
                                                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                                    </div>
                                                                    <div class="modal-body">
                                                                        <div style="width: 50%; text-align: center;">
                                                                            <img class="img-responsive" 
                                                                                 src="${resident.image == null ? 'images/logo/person.jpg' : resident.image}" 
                                                                                 alt="Image" 
                                                                                 style="width: 200px; height: 200px; border-radius: 50%; object-fit: cover;"/>
                                                                        </div>
                                                                        <div style="width: 50%; margin-left: 5%">
                                                                            <p><strong>CCCD:</strong> ${resident.cccd}</p>
                                                                            <p><strong>Name:</strong> ${resident.name}</p>
                                                                            <p><strong>Bod:</strong> ${resident.bod}</p>
                                                                            <p><strong>Email:</strong> ${resident.email}</p>
                                                                            <p><strong>Phone:</strong> ${resident.phone}</p>
                                                                            <p><strong>Address:</strong> ${resident.address}</p>   
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </c:forEach>
                                                </div>
                                                <p><span style="font-weight: bold">Apartment Owner:  </span><a href="#" data-toggle="modal" data-target="#ownerModal"> ${requestScope.apartmentOwner.name}</a></p>
                                                <p>
                                                    <a href="apartment-living-history?aid=${requestScope.apartmentDetail.id}" class="btn-custom">Living History</a>
                                                    <a href="apartment-owner-history?aid=${requestScope.apartmentDetail.id}" class="btn-custom">Owner History</a>
                                                </p>
                                            </div>
                                        </div>

                                        <span class="btn btn-primary" style="margin-left: 30px; margin-bottom: 20px;color: white;">
                                            <a href="view-apartment-admin" style="color: white; text-decoration: none;">Back</a>
                                        </span>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <!-- end dashboard inner -->
                    </div>
                    <div class="container-fluid">
                        <div class="footer">
                            <p>Copyright &copy; 2018 Designed by html.design. All rights reserved.</p>
                        </div>
                    </div>
                </div>

                <!-- Modal for Apartment Owner -->
                <div class="modal fade" id="ownerModal" tabindex="-1" role="dialog">
                    <div class="modal-dialog" style="max-width: 50%">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h3>Apartment Owner Information</h3>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                            </div>
                            <div class="modal-body">
                                <div style="width: 50%; text-align: center;">
                                    <img class="img-responsive" src="${requestScope.apartmentOwner.image == null ? 'images/logo/person.jpg' : requestScope.apartmentOwner.image}" alt="Image" style="width: 100%;"/>
                                </div>
                                <div style="width: 50%; margin-left: 5%">
                                    <p><strong>CCCD:</strong> ${requestScope.apartmentOwner.cccd}</p>
                                    <p><strong>Name:</strong> ${requestScope.apartmentOwner.name}</p>
                                    <p><strong>Bod:</strong> ${requestScope.apartmentOwner.bod}</p>
                                    <p><strong>Email:</strong> ${requestScope.apartmentOwner.email}</p>
                                    <p><strong>Phone:</strong> ${requestScope.apartmentOwner.phone}</p>
                                    <p><strong>Address:</strong> ${requestScope.apartmentOwner.address}</p>   
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- jQuery -->
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
                </body>
                </html>