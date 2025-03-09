<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Update Apartment Information</title>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="style.css">
        <style>
            body {
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                background-color: #f0f4f8;
                margin: 0;
                padding: 0;
            }
            .form-container {
                background: #ffffff;
                padding: 30px;
                border-radius: 10px;
                box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
                max-width: 800px;
                margin: 50px auto;
            }
            .form-container h1 {
                text-align: center;
                margin-bottom: 20px;
                color: #4a90e2;
                font-size: 28px;
            }
            .form-row {
                display: flex;
                flex-wrap: wrap;
                justify-content: space-between;
            }
            .form-group {
                flex: 1;
                min-width: 250px;
                margin: 10px;
            }
            .form-group label {
                font-weight: bold;
                color: #4a90e2;
            }
            .form-group input,
            .form-group select {
                width: 100%;
                padding: 12px;
                border: 1px solid #ced4da;
                border-radius: 5px;
                transition: border-color 0.3s;
            }
            .form-group input:focus,
            .form-group select:focus {
                border-color: #4a90e2;
                outline: none;
            }
            .form-button {
                text-align: center;
                margin-top: 20px;
            }
            .form-button button {
                padding: 12px 30px;
                background-color: #4a90e2;
                color: #ffffff;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                font-size: 16px;
                transition: background-color 0.3s;
            }
            .form-button button:hover {
                background-color: #357ab8;
            }
            .clickable {
                cursor: pointer;
                color: #4a90e2;
                text-decoration: underline;
            }
            /* Fullscreen modal styles */
            .modal-fullscreen {
                max-width: 100%;
                height: auto;
                margin: 0;
                padding: 20px;
            }
            .table {
                width: 100%;
                margin-top: 20px;
            }
            .table th, .table td {
                text-align: left;
                padding: 10px;
            }
            .table th {
                background-color: #4a90e2;
                color: white;
            }
            .table-row {
                cursor: pointer;
            }
            .table-row:hover {
                background-color: #f1f1f1;
            }
            .filter-row {
                display: flex;
                justify-content: space-between;
                align-items: center;
            }
            .filter-row .form-group {
                flex: 1;
                margin-right: 10px;
            }
            .filter-row .form-group:last-child {
                margin-right: 0;
            }
        </style>
    </head>
    <body>
        <div class="full_container">
            <div class="inner_container">
                <%@include file="sidebar.jsp" %>
                <div id="content">
                    <%@include file="topbar.jsp" %>
                    <div class="midde_cont">
                        <div class="container-fluid">
                            <div class="form-container">
                                <h1>Update Apartment Information</h1>
                                <form action="update-apartment" method="post">
                                    <input type="text" name="aid" value="${apartment.id}" hidden>

                                    <div class="form-row">
                                        <div class="form-group">
                                            <label for="numberOfPerson">Number of Persons</label>
                                            <input type="number" id="numberOfPerson" name="numberOfPerson" value="${apartment.numberOfPerson}" required readonly="">
                                        </div>

                                        <div class="form-group">
                                            <label for="floor">Floor</label>
                                            <input type="text" id="floor" name="floor" value="${apartment.floor.number}" required readonly="">
                                        </div>
                                    </div>

                                    <div class="form-row">
                                        <div class="form-group">
                                            <label for="infor">Information</label>
                                            <input type="text" id="infor" name="infor" value="${apartment.infor}" required>
                                        </div>

                                        <div class="form-group">
                                            <label for="roomtype">Room Type</label>
                                            <select id="roomtype" name="roomtype" required>
                                                <c:forEach var="type" items="${roomTypes}">
                                                    <option value="${type.id}" ${type.id == apartment.roomtype.id?'selected':''}>${type.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="form-row">
                                        <div class="form-group">
                                            <label for="status">Status</label>
                                            <select id="status" name="status" required>
                                                <option value="0" ${apartment.status == 0 ? 'selected' : ''}>Inactive</option>
                                                <option value="1" ${apartment.status == 1 ? 'selected' : ''}>Active</option>
                                            </select>
                                        </div>

                                        <div class="form-group">
                                            <label for="livingResident">Living Resident</label>
                                            <input type="text" id="livingResident" value="${livingResident.rid.name}" name="livingResident" placeholder="Select Resident..." readonly class="clickable" onclick="openResidentModal()">
                                            <input type="hidden" id="livingResidentId" value="${livingResident.rid.pId}" name="livingResidentId">
                                            <input type="hidden" name="livingApartmentID" value="${livingResident.id}">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="apartmentOwner">Apartment Owner</label>
                                        <input type="text" id="apartmentOwner" value="${ownerApartment.rid.name}" name="apartmentOwner" placeholder="Select Owner..." readonly class="clickable" onclick="openOwnerModal()">
                                        <input type="hidden" id="apartmentOwnerId" value="${ownerApartment.rid.pId}" name="apartmentOwnerId">
                                        <input type="hidden" name="ApartmentID" value="${ownerApartment.id}">
                                    </div>

                                    <div class="form-button">
                                        <button type="submit">Update</button>
                                        <h5>${message}</h5>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Resident Modal -->
        <div class="modal fade" id="residentModal" tabindex="-1" role="dialog" aria-labelledby="residentModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-fullscreen" role="document" style="max-width: 60%">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="residentModalLabel">Select Living Resident</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body" style="max-height: 400px; overflow-y: auto;">
                        <div class="filter-row mb-2">
                            <div class="form-group">
                                <label for="residentID">Resident ID</label>
                                <input type="text" class="form-control" id="residentID" placeholder="Search by ID..." oninput="filterResidents()">
                            </div>
                        </div>
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>Phone</th>
                                    <th>Gender</th>
                                </tr>
                            </thead>
                            <tbody id="residentList">
                                <c:forEach items="${requestScope.listResident}" var="resident">
                                    <tr class="table-row" onclick="selectResident({id: '${resident.pId}', name: '${resident.name}'})">
                                        <td>${resident.pId}</td>
                                        <td>${resident.name}</td>
                                        <td>${resident.phone}</td>
                                        <td>${resident.gender}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

        <!-- Owner Modal -->
        <div class="modal fade" id="ownerModal" tabindex="-1" role="dialog" aria-labelledby="ownerModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-fullscreen" role="document" style="max-width: 60%">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="ownerModalLabel">Select Apartment Owner</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body" style="max-height: 400px; overflow-y: auto;">
                        <div class="filter-row mb-2">
                            <div class="form-group">
                                <label for="ownerID">Resident ID</label>
                                <input type="text" class="form-control" id="ownerID" placeholder="Search by ID..." oninput="filterOwners()">
                            </div>
                        </div>
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>Phone</th>
                                    <th>Gender</th>
                                </tr>
                            </thead>
                            <tbody id="ownerList">
                                <c:forEach items="${requestScope.listResident}" var="resident">
                                    <tr class="table-row" onclick="selectOwner({id: '${resident.pId}', name: '${resident.name}'})">
                                        <td>${resident.pId}</td>
                                        <td>${resident.name}</td>
                                        <td>${resident.phone}</td>
                                        <td>${resident.gender}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

        <script>
            function selectResident(resident) {
                document.getElementById('livingResident').value = resident.name;
                document.getElementById('livingResidentId').value = resident.id;
                $('#residentModal').modal('hide');
            }

            function selectOwner(owner) {
                document.getElementById('apartmentOwner').value = owner.name;
                document.getElementById('apartmentOwnerId').value = owner.id;
                $('#ownerModal').modal('hide');
            }

            function openResidentModal() {
                $('#residentModal').modal('show');
            }

            function openOwnerModal() {
                $('#ownerModal').modal('show');
            }

            function filterResidents() {
             const id = document.getElementById('residentID').value.toLowerCase().replace(/\s+/g, '');
                const rows = document.querySelectorAll('#residentList .table-row');

                rows.forEach(row => {
                    const cells = row.getElementsByTagName('td');
                     const rowID = cells[0].innerText.toLowerCase().replace(/\s+/g, '');





                    if (id && !rowID.includes(id)) {
                        row.style.display = 'none';
                    } else {
                        row.style.display = '';
                    }
                });
            }

            function filterOwners() {
                const id = document.getElementById('ownerID').value.toLowerCase();
                const rows = document.querySelectorAll('#ownerList .table-row');

                rows.forEach(row => {
                    const cells = row.getElementsByTagName('td');
                    const rowID = cells[0].innerText.toLowerCase();

                    if (id && !rowID.includes(id)) {
                        row.style.display = 'none';
                    } else {
                        row.style.display = '';
                    }
                });
            }
        </script>
    </body>
</html>