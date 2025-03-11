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
                max-width: 850px;
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
                background-color: #4B0082; /* Dark Slate Blue */
                color: white;
                font-weight: bold;
            }
            table tr:nth-child(even) {
                background-color: #f9f9f9;
            }
            table tr:hover {
                background-color: #e1f5fe;
            }
            table th {
                background-color: #4B4B4B;
                color: white;
                font-weight: bold;
            }
            .select {
                background: #D3D3D3;
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
                                            <input type="number" id="numberOfPerson" min="1" max="${apartment.roomtype.limitPerson}" name="numberOfPerson" value="${apartment.numberOfPerson}" required />
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
                                            <textarea id="infor" name="infor" rows="4"  required>${apartment.infor}</textarea>
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
                                                <tr class="${roomType.id == apartment.roomtype.id ? 'select':''}" >
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