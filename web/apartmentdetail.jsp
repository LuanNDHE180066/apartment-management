<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <!-- basic -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- mobile metas -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <!-- site metas -->
    <title>Apartment Management</title>
    <link rel="icon" href="images/fevicon.png" type="image/png" />
    <!-- bootstrap css -->
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <!-- font awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css"/>
    <style>
        /* Modal styles */
        .modal-header, .modal-footer {
            border: none;
        }
        .modal-body {
            display: flex;
        }
        .modal-body img {
            margin: 0 1%;
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
            background-color: #0056b3; /* Darker shade on hover */
            transform: translateY(-2px); /* Slight lift effect on hover */
        }

        .btn-custom:active {
            transform: translateY(0); /* Remove lift effect when button is pressed */
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
                                <div class="page_title">
                                    <h2>Apartment Information</h2>
                                </div>
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
                                        <div style="flex: 1; max-width: 50%; margin-left: 15%;">
                                            <p><span style="font-weight: bold">ApartmentID: </span> ${requestScope.apartmentDetail.id}</p>
                                            <p><span style="font-weight: bold">Number of persons: </span> ${requestScope.apartmentDetail.numberOfPerson}</p>
                                            <p><span style="font-weight: bold">Floor: </span> ${requestScope.apartmentDetail.floor.number}</p>
                                            <p><span style="font-weight: bold">Information: </span> ${requestScope.apartmentDetail.infor}</p>
                                            <p><span style="font-weight: bold">Room Type: </span> ${requestScope.apartmentDetail.roomtype.name}</p>
                                            <p><span style="font-weight: bold">Status: </span> ${requestScope.apartmentDetail.status}</p>
                                            <p><span style="font-weight: bold">Living Person:  </span><a href="#" data-toggle="modal" data-target="#livingPersonModal"> ${requestScope.livingPerson.name}</a></p>
                                            <p><span style="font-weight: bold">Apartment Owner:  </span><a href="#" data-toggle="modal" data-target="#ownerModal"> ${requestScope.apartmentOwner.name}</a></p>
                                            <p>
                                                <a href="apartment-living-history?aid=${requestScope.apartmentDetail.id}" class="btn-custom">Living History</a>
                                                <a href="apartment-owner-history?aid=${requestScope.apartmentDetail.id}" class="btn-custom">Owner History</a>
                                            </p>
                                        </div>
                                    </div>

                                    <div style="display: flex; gap: 20px; width: 100%;">
                                        <!-- Additional content can be added here -->
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="container-fluid">
                            <div class="footer">
                                <p>Copyright � 2018 Designed by html.design. All rights reserved.</p>
                            </div>
                        </div>
                    </div>
                    <!-- end dashboard inner -->
                </div>
            </div>

            <!-- Modal for Living Person -->
            <div class="modal fade" id="livingPersonModal" tabindex="-1" role="dialog">
                <div class="modal-dialog" style="max-width: 60%">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h3>Living Person Information</h3>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </div>
                        <div class="modal-body">
                            <div style="width: 50%; text-align: center;">
                                <img class="img-responsive" src="${requestScope.livingPerson.image == null ? 'images/logo/person.jpg' : requestScope.livingPerson.image}" alt="Image" style="width: 100%;"/>
                            </div>
                            <div style="width: 50%; margin-left: 5%">
                                <p><strong>CCCD:</strong> ${requestScope.livingPerson.cccd}</p>
                                <p><strong>Name:</strong> ${requestScope.livingPerson.name}</p>
                                <p><strong>Bod:</strong> ${requestScope.livingPerson.bod}</p>
                                <p><strong>Email:</strong> ${requestScope.livingPerson.email}</p>
                                <p><strong>Phone:</strong> ${requestScope.livingPerson.phone}</p>
                                <p><strong>Address:</strong> ${requestScope.livingPerson.address}</p>   
                            </div>
                        </div>
                    </div>
                    <!-- end dashboard inner -->
                </div>
            </div>

            <!-- Modal for Apartment Owner -->
            <div class="modal fade" id="ownerModal" tabindex="-1" role="dialog">
                <div class="modal-dialog" style="max-width: 60%">
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
        </body>
</html>