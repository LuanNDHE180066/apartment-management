<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Apartment Management</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="css/bootstrap.min.css" />
        <link rel="stylesheet" href="style.css" />
        <link rel="stylesheet" href="css/responsive.css" />
        <link rel="stylesheet" href="css/colors.css" />
        <link rel="stylesheet" href="css/custom.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" />
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #e0e0e0; /* Light gray */
                margin: 0;
                padding: 0;
            }
            .form-container {
                background: #ffffff;
                padding: 40px;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                max-width: 900px;
                margin: auto;
            }
            .form-container h1 {
                text-align: center;
                margin-bottom: 30px;
                color: #333;
            }
            .form-group {
                margin-bottom: 20px;
            }
            .form-group label {
                display: flex;
                align-items: center;
                margin-bottom: 8px;
                font-weight: bold;
                color: #555;
            }
            .form-group label i {
                margin-right: 8px;
                color: #4a90e2;
            }
            .form-group input,
            .form-group select,
            .form-group textarea {
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
            .form-group select:focus,
            .form-group textarea:focus {
                border-color: #4a90e2;
                outline: none;
            }
            .form-row {
                display: flex;
                justify-content: space-between;
                gap: 20px;
            }
            .form-row .form-group {
                flex: 1;
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
            table {
                width: 100%;
                margin-top: 20px;
                border-collapse: collapse;
                border-radius: 8px;
                overflow: hidden;
                box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
            }
            table th, table td {
                padding: 12px;
                text-align: left;
                border: none;
            }
            table th {
                background-color: #4B4B4B; /* Dark Slate Blue */
                color: white;
                font-weight: bold;
            }
            table tr:nth-child(even) {
                background-color: #f9f9f9;
            }
            table tr:hover {
                background-color: #e1f5fe;
            }
            .select {
                background-color: #D3D3D3 !important;
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
    <body class="dashboard dashboard_1">
        <div class="full_container">
            <div class="inner_container">
                <%@include file="sidebar.jsp" %>
                <div id="content">
                    <%@include file="topbar.jsp" %>
                    <div class="container mt-5">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-container">
                                    <h1>Update Apartment Information</h1>
                                    <form action="updatenopersonre" method="post">
                                        <input type="hidden" id="id" name="id" value="${apartment.id}" />
                                        <div class="form-group">
                                            <label for="numberOfPerson"><i class="fas fa-users"></i> Number of Persons</label>
                                            <jsp:useBean id="laDAO" class="dao.LivingApartmentDAO" scope="page"></jsp:useBean>
                                            <input type="number" id="numberOfPerson" min="1" max="${apartment.roomtype.limitPerson}" name="numberOfPerson" value="${laDAO.getNumberOfLivingPerson(apartment.id)}" required readonly=""/>
                                        </div>
                                        <div class="form-row">
                                            <div class="form-group">
                                                <label for="roomTypeName"><i class="fas fa-door-open"></i> Apartment Type Name</label>
                                                <input type="text" id="roomTypeName" readonly="" name="roomTypeName" value="${apartment.roomtype.name}" required />
                                            </div>
                                            <div class="form-group">
                                                <label for="floorSquare"><i class="fas fa-ruler-combined"></i> Apartment square (m²)</label>
                                                <input type="number" step="0.1" readonly="" id="apartmentSquare" name="floorSquare" value="${apartment.roomtype.square}" required />
                                            </div>
                                        </div>
                                        <div class="form-row">
                                            <div class="form-group">
                                                <label for="bedroom"><i class="fas fa-bed"></i> Number of Bedrooms</label>
                                                <input type="number" id="bedroom" readonly="" name="bedroom" value="${apartment.roomtype.bedroom}" required />
                                            </div>
                                            <div class="form-group">
                                                <label for="livingRoom"><i class="fas fa-couch"></i> Number of Living Rooms</label>
                                                <input type="number" id="livingRoom" readonly="" name="livingRoom" value="${apartment.roomtype.livingRoom}" required />
                                            </div>
                                        </div>
                                        <div class="form-row">
                                            <div class="form-group">
                                                <label for="bathRoom"><i class="fas fa-bath"></i> Number of Bathrooms</label>
                                                <input type="number" id="bathRoom" readonly="" name="bathRoom" value="${apartment.roomtype.bathRoom}" required />
                                            </div>
                                            <div class="form-group">
                                                <label for="balcony"><i class="fas fa-balance-scale"></i> Number of Balconies</label>
                                                <input type="number" id="balcony" readonly="" name="balcony" value="${apartment.roomtype.balcony}" required />
                                            </div>
                                        </div>
                                        <div class="form-row">
                                            <div class="form-group">
                                                <label for="floorNumber"><i class="fas fa-building"></i> Floor Number</label>
                                                <input type="number" id="floorNumber" readonly="" name="floorNumber" value="${apartment.floor.number}" required />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="infor"><i class="fas fa-info-circle"></i> Additional Information</label>
                                            <textarea id="infor" name="infor" rows="4" required>${apartment.infor}</textarea>
                                        </div>

                                        <div class="form-button">
                                            <button type="submit">Update Information</button>
                                            <div>
                                                <span>
                                                    <a style="color: #357AB8; text-decoration: underline; font-size: 20px" href="view-all-resident-apartment">
                                                        <i class="fas fa-arrow-left"></i> Back
                                                    </a>
                                                </span>
                                            </div>
                                            <h5 style="color:${requestScope.status == 'true' ? 'green' : 'red'}; text-align:center">
                                                ${requestScope.message}
                                            </h5>
                                        </div>
                                    </form>

                                    <h2 class="mt-5">Living Persons</h2>
                                    <table class="table table-bordered mt-3">
                                        <thead>
                                            <tr>
                                                <th>ID</th>
                                                <th>Name</th>
                                                <th>CCCD</th>
                                                <th>Bod</th>
                                                <th>Phone</th>

                                                <th>Start Date</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${requestScope.livingPersons}" var="person">
                                                <tr>
                                                    <td>${person.rid.pId}</td>
                                                    <td>
                                                        <a style="color: #4a90e2; text-decoration: underline; font-weight: bold" class="btn btn-link" data-toggle="modal" data-target="#modal${person.rid.pId}">
                                                            ${person.rid.name}
                                                        </a>

                                                        <!-- Modal for Owner Details -->





                                                        <div class="modal fade" id="modal${person.rid.pId}" tabindex="-1" role="dialog">
                                                            <div class="modal-dialog" style="max-width: 600px;">
                                                                <div class="modal-content">
                                                                    <div class="modal-header">
                                                                        <h3>Owner Information</h3>
                                                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                                    </div>
                                                                    <div class="modal-body">
                                                                        <img src="${person.rid.image == null ? 'images/logo/person.jpg' : person.rid.image}" alt="Owner Image"/>
                                                                        <div class="modal-info">
                                                                            <p><i class="fas fa-user info-icon"></i><strong>Name:</strong> ${person.rid.name}</p>
                                                                            <p><i class="fas fa-id-card info-icon"></i><strong>ID Card:</strong> ${person.rid.cccd}</p>
                                                                            <p><i class="fas fa-calendar-alt info-icon"></i><strong>Date of Birth:</strong> ${person.rid.bod}</p>
                                                                            <p><i class="fas fa-envelope info-icon"></i><strong>Email:</strong> ${person.rid.email}</p>
                                                                            <p><i class="fas fa-phone info-icon"></i><strong>Phone:</strong> ${person.rid.phone}</p>
                                                                            <p><strong>Address:</strong> ${person.rid.address}</p>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </td>
                                                    <td>${person.rid.cccd}</td>
                                                    <td>${person.rid.bod}</td>
                                                    <td>${person.rid.phone}</td>
                                                    <td>${person.startDate}</td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>

                                    <h2 class="mt-5">Room Types and Limits</h2>
                                    <div style="margin-top: 10px">
                                        <h7>*<i>Ensure your details reflect the specifications of your chosen room type.</i></h7>
                                    </div>
                                    <table class="table table-bordered mt-3">
                                        <thead>
                                            <tr>
                                                <th>Room Type</th>
                                                <th>Limit Persons</th>
                                                <th>Bedrooms</th>
                                                <th>Living Rooms</th>
                                                <th>Bathrooms</th>
                                                <th>Balconies</th>
                                                <th>Square (m²)</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${sessionScope.listRoomType}" var="roomType">
                                                <tr class="${roomType.id == apartment.roomtype.id ? 'select':''}">
                                                    <td>${roomType.name}</td>
                                                    <td>${roomType.limitPerson}</td>
                                                    <td>${roomType.bedroom}</td>
                                                    <td>${roomType.livingRoom}</td>
                                                    <td>${roomType.bathRoom}</td>
                                                    <td>${roomType.balcony}</td>
                                                    <td>${roomType.square}</td>
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
            <script src="js/jquery.min.js"></script>
            <script src="js/bootstrap.min.js"></script>
    </body>
</html>