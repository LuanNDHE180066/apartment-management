<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Fund Details</title>
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <link rel="stylesheet" href="css/style.css" />
    <style>
        .full_container {
            display: flex;
            min-height: 100vh;
            background-color: #f4f7fa;
        }
        .inner_container {
            display: flex;
            flex: 1;
        }
        .sidebar {
            width: 250px;
            background-color: #2c3e50;
            padding: 20px;
            color: #ecf0f1;
        }
        .sidebar h3 {
            color: #3498db;
        }
        .sidebar a {
            color: #ecf0f1;
        }
        .sidebar a:hover {
            color: #3498db;
        }
        #content {
            flex: 1;
            padding: 30px;
            background-color: #ffffff;
        }
        .content-area {
            display: flex;
            flex-direction: column;
            margin-top: 20px;
        }
        .fund-details-container {
            display: flex;
            align-items: flex-start;
            margin-bottom: 30px;
            background-color: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        .image-container {
            width: 40%;
            padding-right: 20px;
        }
        .image-container img {
            width: 100%;
            height: auto;
        }
        .info-container {
            width: 60%;
            padding-left: 20px;
        }
        .info-container h2 {
            color: #2980b9;
            padding: 10px;
            border-radius: 5px;
            margin-bottom: 20px;
            font-size: 24px;
            text-align: center;
        }
        .info-container p {
            color: #d35400;
            font-size: 16px;
            margin-bottom: 10px;
            padding: 8px;
            border-radius: 5px;
        }
        .info-container p strong {
            color: #3498db;
            margin-right: 10px;
        }
        .form-group label {
            color: #2c3e50;
            font-weight: bold;
        }
        .form-control {
            border: 1px solid #ced4da;
            border-radius: 5px;
        }
        .history-table {
            width: 100%;
            clear: both;
            background-color: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        .history-table table {
            width: 100%;
        }
        .history-table th, .history-table td {
            padding: 12px;
            text-align: left;
        }
        .history-table th {
            background-color: #3498db;
            color: #ffffff;
        }
        .history-table tr:nth-child(even) {
            background-color: #f8f9fa;
        }
        .history-table tr:hover {
            background-color: #e9ecef;
        }
        .date-filter-container {
            display: flex;
            gap: 20px;
            margin-bottom: 20px;
        }
        .pagination {
            display: flex;
            justify-content: center;
            margin-top: 20px;
        }
        .pagination button {
            margin: 0 5px;
            padding: 5px 10px;
            background-color: #3498db;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .pagination button:disabled {
            background-color: #cccccc;
            cursor: not-allowed;
        }
    </style>
</head>
<body>
    <div class="full_container">
        <div class="inner_container">
            <%@include file="sidebar.jsp" %>
            <div id="content">
                <%@include file="topbar.jsp" %>
                <div class="content-area">
                    <div class="fund-details-container">
                        <div class="image-container">
                            <img src="images/logo/fund.png" alt="Fund Image" />
                        </div>
                        <div class="info-container">
                            <h2>Fund Details</h2>
                            <form action="fund" method="post">
                                <div class="form-group">
                                    <label for="fundSelect">Select Fund:</label>
                                    <select class="form-control" id="fundSelect" name="fund" onchange="this.form.submit()">
                                        <c:forEach items="${requestScope.listFund}" var="f">
                                            <option value="${f.id}" ${f.id == selectedFund.id ? 'selected' : ''}>${f.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </form>
                            <p><strong>Name:</strong> ${selectedFund.name}</p>
                            <p><strong>Value:</strong> ${selectedFund.value}</p>
                            <p><strong>Start Date:</strong> ${selectedFund.startdate}</p>
                            <p><strong>Description:</strong> ${selectedFund.description}</p>
                            <p><strong>Change Date:</strong> ${selectedFund.changedate}</p>
                            <p><strong>Status:</strong> ${selectedFund.status == 1 ? 'Active' : 'Inactive'}</p>
                        </div>
                    </div>

                    <div class="history-table">
                        <div class="date-filter-container">
                            <div class="form-group">
                                <label for="fromDateFilter">From:</label>
                                <input type="date" class="form-control" id="fromDateFilter" oninput="filterAndPaginate()">
                            </div>
                            <div class="form-group">
                                <label for="toDateFilter">To:</label>
                                <input type="date" class="form-control" id="toDateFilter" oninput="filterAndPaginate()">
                            </div>
                        </div>
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>Date</th>
                                    <th>Value Before</th>
                                    <th>Value After</th>
                                </tr>
                            </thead>
                            <tbody id="historyTable">
                                <c:forEach items="${requestScope.listFundHistory}" var="fh">
                                    <tr>
                                        <td>${fh.name}</td>
                                        <td>${fh.date}</td>
                                        <td>${fh.value_befor}</td>
                                        <td>${fh.value_after}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <div class="pagination" id="paginationControls"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script>
        const recordsPerPage = 5;
        let currentPage = 1;
        let allRows = [];

        // Initialize on page load
        document.addEventListener("DOMContentLoaded", function() {
            allRows = Array.from(document.getElementById("historyTable").getElementsByTagName("tr"));
            filterAndPaginate();
        });

        function filterAndPaginate() {
            var fromInput = document.getElementById("fromDateFilter");
            var toInput = document.getElementById("toDateFilter");
            var fromDate = fromInput.value ? new Date(fromInput.value) : null;
            var toDate = toInput.value ? new Date(toInput.value) : null;

            // Filter rows
            let filteredRows = allRows.filter(row => {
                var td = row.getElementsByTagName("td")[1]; // Date column
                if (!td) return false;
                var txtValue = td.textContent || td.innerText;
                var rowDate = txtValue === "N/A" ? null : new Date(txtValue);

                var showRow = true;
                if (fromDate && rowDate && rowDate < fromDate) showRow = false;
                if (toDate && rowDate && rowDate > toDate) showRow = false;
                if (!fromDate && !toDate) showRow = true;
                return showRow;
            });

            // Update pagination
            updatePagination(filteredRows);
            showPage(filteredRows, currentPage);
        }

        function showPage(rows, page) {
            var start = (page - 1) * recordsPerPage;
            var end = start + recordsPerPage;

            allRows.forEach(row => row.style.display = "none");
            rows.slice(start, end).forEach(row => row.style.display = "");
        }

        function updatePagination(filteredRows) {
            var totalPages = Math.ceil(filteredRows.length / recordsPerPage);
            var pagination = document.getElementById("paginationControls");
            pagination.innerHTML = "";

            if (filteredRows.length > recordsPerPage) {
                // Previous button
                var prevButton = document.createElement("button");
                prevButton.textContent = "Previous";
                prevButton.disabled = currentPage === 1;
                prevButton.onclick = function() {
                    if (currentPage > 1) {
                        currentPage--;
                        filterAndPaginate();
                    }
                };
                pagination.appendChild(prevButton);

                // Page numbers
                for (let i = 1; i <= totalPages; i++) {
                    var pageButton = document.createElement("button");
                    pageButton.textContent = i;
                    pageButton.disabled = i === currentPage;
                    pageButton.onclick = (function(page) {
                        return function() {
                            currentPage = page;
                            filterAndPaginate();
                        };
                    })(i);
                    pagination.appendChild(pageButton);
                }

                // Next button
                var nextButton = document.createElement("button");
                nextButton.textContent = "Next";
                nextButton.disabled = currentPage === totalPages;
                nextButton.onclick = function() {
                    if (currentPage < totalPages) {
                        currentPage++;
                        filterAndPaginate();
                    }
                };
                pagination.appendChild(nextButton);
            }
        }
    </script>
</body>
</html>