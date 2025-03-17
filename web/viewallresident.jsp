<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
    <head>
        <!-- [Previous head content remains unchanged] -->
        <style>
            .pagination {
                margin-top: 20px;
                margin-left: 1250px;
                text-align: right;
            }
            .pagination a {
                display: inline-block;
                width: 30px;
                margin: 0 1px;
                padding: 5px 10px;
                border: 1px solid #007bff;
                color: #007bff;
                text-decoration: none;
                border-radius: 5px;
                text-align: center;
            }
            .pagination a:hover {
                background-color: #007bff;
                color: white;
            }
            .pagination a.active {
                font-weight: bold;
                background-color: #007bff;
                color: white;
            }
            #table-infor th, #table-infor td {
                text-align: center; /* Default center alignment for all columns */
            }
            #table-infor td.name-column {
                text-align: left; /* Left alignment for Name column */
            }
            .status-select {
                padding: 2px 4px;
                border-radius: 12px;
                border: 1px solid #ccc;
                background-color: #f8f9fa;
                font-size: 12px;
                cursor: pointer;
            }
            .status-select:focus {
                outline: none;
                border-color: #007bff;
                box-shadow: 0 0 3px rgba(0, 123, 255, 0.5);
            }
            .status-active {
                color: green;
            }
            .status-inactive {
                color: red;
            }
            .export-buttons {
                display: flex;
                gap: 10px;
                align-items: center; /* Align buttons vertically with filter inputs */
            }
            .checkbox-column {
                width: 40px;
            }
        </style>
    </head>
    <body class="inner_page tables_page">
        <div class="full_container">
            <div class="inner_container">
                <!-- Sidebar -->
                <%@include file="sidebar.jsp" %>
                <!-- Right Content -->
                <div id="content">
                    <!-- Topbar -->
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
                                            <div class="heading1 margin_0">
                                                <h2>Resident Information</h2>
                                            </div>
                                        </div>
                                        <div style="margin-left: 40px;">
                                            <form action="view-resident" method="GET">
                                                <div class="row align-items-center">
                                                    <!-- Search by Name -->
                                                    <div class="col-md-2">
                                                        <input type="text" value="${sessionScope.searchName}" class="form-control" name="searchName" placeholder="Search by Name">
                                                    </div>
                                                    <!-- Filter by Status -->
                                                    <div class="col-md-2">
                                                        <select class="form-control" name="filterStatus">
                                                            <option value="">Filter by Status</option>
                                                            <option value="1" ${sessionScope.filterStatus == '1' ? 'selected' : ''}>Active</option>
                                                            <option value="0" ${sessionScope.filterStatus == '0' ? 'selected' : ''}>Inactive</option>
                                                            <option value="2" ${sessionScope.filterStatus == '2' ? 'selected' : ''}>Pending</option>
                                                        </select>
                                                    </div>
                                                    <!-- Filter by Home Owner -->
                                                    <div class="col-md-2">
                                                        <select class="form-control" name="isRepresent">
                                                            <option value="" ${empty sessionScope.isRepresent ? 'selected' : ''}>Filter by Apartment Representative</option>
                                                            <option value="1" ${sessionScope.isRepresent == '1' ? 'selected' : ''}>Representative</option>
                                                            <option value="0" ${sessionScope.isRepresent == '0' ? 'selected' : ''}>Not Representative</option>
                                                        </select>
                                                    </div>
                                                    <!-- Export Buttons -->
                                                    <div class="col-md-3 export-buttons">
                                                        <form action="${pageContext.request.contextPath}/export-residents" method="POST">
                                                            <input type="hidden" name="exportType" value="all">
                                                            <button type="submit" class="btn btn-info">Export All to Excel</button>
                                                        </form>
                                                        <form action="${pageContext.request.contextPath}/export-residents" method="POST" id="exportSelectedForm">
                                                            <input type="hidden" name="exportType" value="selected">
                                                            <button type="submit" class="btn btn-info" onclick="return validateSelection()">Export Selected to Excel</button>
                                                        </form>
                                                    </div>
                                                    <!-- Submit & Reset -->
                                                    <div class="col-md-3 d-flex align-items-center">
                                                        <button type="submit" class="btn btn-primary" style="margin-right: 5px;">Filter</button>
                                                        <a href="addNewResident" class="btn btn-primary" style="margin-right: 5px;">Add new Resident</a>
                                                        <a href="view-resident" class="btn btn-secondary">Reset</a>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                        <!-- Table Section -->
                                        <div class="table_section padding_infor_info">
                                            <div class="table-responsive-sm">
                                                <table class="table w-100" id="table-infor">
                                                    <thead>
                                                        <tr>
                                                            <th class="checkbox-column"><input type="checkbox" id="selectAll"></th>
                                                            <th>Name</th>
                                                            <th>Phone</th>
                                                            <th>Email</th>
                                                            <th>Status</th>
                                                            <th>View Detail</th>
                                                            <th>Update</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                    <h3>${requestScope.message}</h3>
                                                    <c:forEach items="${requestScope.listResident}" var="resident">
                                                        <tr>
                                                            <td><input type="checkbox" name="selectedResidents" value="${resident.pId}" form="exportSelectedForm"></td>
                                                            <td class="name-column">${resident.name}</td> <!-- Left-aligned Name -->
                                                            <td>${empty resident.phone ? 'None':resident.phone}</td>
                                                            <td>${empty resident.email ? 'None' : resident.email}</td>
                                                            <td>
                                                                <form action="updateResidentStatus" method="POST" class="status-form">
                                                                    <input type="hidden" name="id" value="${resident.pId}">
                                                                    <select class="status-select ${resident.status == '1' ? 'status-active' : 'status-inactive'}" name="status" onchange="confirmStatusChange(this)">
                                                                        <option value="1" class="status-active" ${resident.status == '1' ? 'selected' : ''}>Active</option>
                                                                        <option value="0" class="status-inactive" ${resident.status == '0' ? 'selected' : ''}>Inactive</option>
                                                                        <option value="2" class="status-inactive" ${resident.status == '2' ? 'selected' : ''} disabled>Pending</option>
                                                                    </select>
                                                                </form>
                                                            </td>
                                                            <td style="text-align: center;">
                                                                <a href="#" data-toggle="modal" data-target="#residentDetail${resident.pId}">
                                                                    <i class="fa fa-user" aria-hidden="true"></i>
                                                                </a>
                                                                <c:if test="${resident.status == 2}">
                                                                    <a href="deleteRe?id=${resident.pId}" 
                                                                       onclick="confirmDelete(this, event)">
                                                                        <i class="fa-solid fa-delete-left"></i>
                                                                    </a>
                                                                </c:if>
                                                            </td>
                                                            <td style="text-align: center;">
                                                                <a href="updateRE?rid=${resident.pId}">
                                                                    <i class="fa fa-edit"></i>
                                                                </a>
                                                            </td>
                                                        </tr>
                                                        <!-- Modal for resident details -->
                                                        <div id="residentDetail${resident.pId}" class="modal fade"> 
                                                            <div class="modal-dialog" style="max-width: 60%">
                                                                <div class="modal-content">
                                                                    <div class="modal-header">
                                                                        <h3>Resident Information</h3>
                                                                        <button type="button" class="close" data-dismiss="modal">×</button>
                                                                    </div>
                                                                    <div class="modal-body" style="display: flex;">
                                                                        <div style="width: 50%; text-align: center;">
                                                                            <img style="margin-left: 1%;margin-right: 1%;" class="img-responsive" src="${resident.image == null ? 'images/logo/person.jpg' : resident.image}" alt="Image"/>
                                                                        </div>
                                                                        <div style="width: 50%;margin-left: 5%">
                                                                            <p><strong>Name:</strong> ${resident.name}</p>
                                                                            <p><strong>Bod:</strong> ${resident.bod}</p>
                                                                            <p><strong>Email:</strong> ${empty resident.email ? 'None' : resident.email}</p>
                                                                            <p><strong>Phone:</strong> ${empty resident.phone ? 'None':resident.phone}</p>
                                                                            <p><strong>Address:</strong> ${resident.address}</p>
                                                                            <p><strong>CCCD:</strong> ${empty resident.cccd ? 'None' : resident.cccd}</p>
                                                                            <p><strong>Gender:</strong> ${resident.gender}</p>
                                                                            <p><strong>Living at</strong> ${resident.gender}</p>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- Pagination Form -->
                            <form method="get" action="view-resident" style="display: flex; align-items: center; gap: 10px;">
                                <label for="page" style="font-size: 14px; font-weight: bold;">Page:</label>
                                <input type="hidden" name="searchName" value="${sessionScope.searchName}">
                                <input type="hidden" name="filterStatus" value="${sessionScope.filterStatus}">
                                <input type="hidden" name="isRepresent" value="${sessionScope.isRepresent}">
                                <select id="page" name="page" onchange="this.form.submit()" 
                                        style="padding: 6px 12px; font-size: 14px; border: 1px solid #ddd; border-radius: 4px; cursor: pointer;">
                                    <c:forEach begin="1" end="${requestScope.totalPage}" var="page">
                                        <option value="${page}" <c:if test="${page == requestScope.currentPage}">selected</c:if>>
                                            ${page}
                                        </option>
                                    </c:forEach>
                                </select>
                            </form>
                            <!-- Footer -->
                            <div class="container-fluid">
                                <div class="footer">
                                    <p>Copyright © 2018 Designed by html.design. All rights reserved.</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Scripts -->
            <script src="js/jquery.min.js"></script>
            <script src="js/popper.min.js"></script>
            <script src="js/bootstrap.min.js"></script>
            <script src="js/custom.js"></script>
            <script>
                                    function confirmStatusChange(select) {
                                        var form = select.closest(".status-form");
                                        var selectedValue = select.value;

                                        if (selectedValue == "2") {
                                            alert("Pending status cannot be selected.");
                                            select.value = select.dataset.previousValue || "1";
                                            return;
                                        }

                                        var confirmationMessage = selectedValue === "0"
                                                ? "Are you sure you want to change the status to Inactive?"
                                                : "Are you sure you want to change the status to Active?";

                                        if (confirm(confirmationMessage)) {
                                            form.submit();
                                        } else {
                                            select.value = selectedValue === "1" ? "0" : "1";
                                        }
                                        select.dataset.previousValue = selectedValue;
                                    }

                                    function confirmDelete(link, event) {
                                        event.preventDefault();
                                        if (confirm("Are you sure you want to delete this resident?")) {
                                            window.location.href = link.href;
                                        }
                                    }

                                    document.getElementById('selectAll').addEventListener('change', function () {
                                        document.querySelectorAll('input[name="selectedResidents"]').forEach(checkbox => {
                                            checkbox.checked = this.checked;
                                        });
                                    });

                                    function validateSelection() {
                                        const selected = document.querySelectorAll('input[name="selectedResidents"]:checked');
                                        if (selected.length === 0) {
                                            alert('Please select at least one resident to export');
                                            return false;
                                        }
                                        return true;
                                    }
            </script>
    </body>
</html>