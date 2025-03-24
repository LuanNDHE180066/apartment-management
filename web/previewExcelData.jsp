<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <!-- basic -->
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <!-- mobile metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <meta name="viewport" content="initial-scale=1, maximum-scale=1" />
        <!-- site metas -->
        <title>Preview Excel Data</title>
        <link rel="icon" href="images/fevicon.png" type="image/png" />
        <!-- bootstrap css -->
        <link rel="stylesheet" href="css/bootstrap.min.css" />
        <!-- site css -->
        <link rel="stylesheet" href="style.css" />
        <!-- responsive css -->
        <link rel="stylesheet" href="css/responsive.css" />
        <!-- color css -->
        <link rel="stylesheet" href="css/colors.css" />
        <!-- select bootstrap -->
        <link rel="stylesheet" href="css/bootstrap-select.css" />
        <!-- scrollbar css -->
        <link rel="stylesheet" href="css/perfect-scrollbar.css" />
        <!-- custom css -->
        <link rel="stylesheet" href="css/custom.css" />
        <!-- calendar file css -->
        <link rel="stylesheet" href="js/semantic.min.css" />
        <!-- fancy box js -->
        <link rel="stylesheet" href="css/jquery.fancybox.css" />
        <style>
            .preview-container {
                max-width: 1200px;
                margin: 50px auto;
                padding: 40px;
                background: #ffffff;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                border: 2px solid blue; /* Debug: Make the container visible */
            }

            .table-bordered {
                border: 1px solid #dee2e6;
                width: 100%;
                border-collapse: collapse;
                display: table !important;
                visibility: visible !important;
            }

            .table-bordered th,
            .table-bordered td {
                border: 1px solid #dee2e6;
                padding: 8px;
                text-align: left;
                display: table-cell !important;
                visibility: visible !important;
                color: #000 !important; /* Ensure text is visible */
            }

            .table-bordered thead {
                display: table-header-group !important;
                visibility: visible !important;
            }

            .table-bordered tbody {
                display: table-row-group !important;
                visibility: visible !important;
            }

            .table-bordered tbody tr {
                display: table-row !important;
                visibility: visible !important;
                background-color: #fff;
            }

            #preview-table-body {
                border: 2px solid red; /* Debug: Make the table body visible */
                display: table-row-group !important;
                visibility: visible !important;
            }

            .action-buttons {
                text-align: center;
                margin-top: 20px;
            }

            .action-buttons button {
                padding: 12px 30px;
                font-size: 18px;
                border: none;
                border-radius: 6px;
                cursor: pointer;
                transition: background-color 0.3s;
                margin: 0 10px;
            }

            #submitMultipleBtn {
                background-color: #4a90e2;
                color: #ffffff;
                position: relative;
            }

            #submitMultipleBtn:disabled {
                background-color: #a0a0a0;
                cursor: not-allowed;
            }

            #submitMultipleBtn:hover:not(:disabled) {
                background-color: #357ab8;
            }

            #backBtn {
                background-color: #dc3545;
                color: #ffffff;
            }

            #backBtn:hover {
                background-color: #c82333;
            }

            .error-message {
                color: red;
                font-size: 14px;
                margin-top: 5px;
                display: block;
            }

            .loading-indicator {
                display: none;
                color: #4a90e2;
                font-size: 16px;
                margin-top: 10px;
            }

            .spinner {
                display: inline-block;
                width: 20px;
                height: 20px;
                border: 3px solid rgba(0, 0, 0, 0.1);
                border-top: 3px solid #4a90e2;
                border-radius: 50%;
                animation: spin 1s linear infinite;
                margin-right: 10px;
            }

            @keyframes spin {
                0% {
                    transform: rotate(0deg);
                }
                100% {
                    transform: rotate(360deg);
                }
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
                            <div class="preview-container">
                                <h2>Preview Imported Data</h2>
                                <div id="preview-errors" style="color: red;"></div>
                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Name</th>
                                            <th>DOB</th>
                                            <th>Gender</th>
                                            <th>Phone</th>
                                            <th>Address</th>
                                            <th>Apt</th>
                                            <th>CCCD</th>
                                        </tr>
                                    </thead>
                                    <tbody id="preview-table-body"></tbody>
                                </table>
                                <div class="action-buttons">
                                    <button id="submitMultipleBtn" disabled>Add Multiple Residents</button>
                                    <button id="backBtn">Back</button>
                                    <div id="loadingIndicator" class="loading-indicator">
                                        <span class="spinner"></span>Processing...
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- JavaScript -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/custom.js"></script>

        <script>
            let multipleResidentsData = [];

            $(document).ready(function () {
                console.log("jQuery version:", $.fn.jquery);

                const data = <%= request.getSession().getAttribute("excelData") != null ? request.getSession().getAttribute("excelData") : "[]" %>;
                const errors = <%= request.getSession().getAttribute("excelErrors") != null ? request.getSession().getAttribute("excelErrors") : "[]" %>;
                multipleResidentsData = data;

                console.log("Data from session:", data);
                console.log("Errors from session:", errors);

                const previewTableBody = $("#preview-table-body");
                const previewErrors = $("#preview-errors");

                console.log("Preview table body element:", previewTableBody.length ? "Found" : "Not found");

                previewTableBody.empty();
                previewErrors.empty();

                if (errors && errors.length > 0) {
                    console.log("Displaying errors:", errors);
                    previewErrors.html("<h4>Validation Errors:</h4><ul>" +
                            errors.map(error => "<li>" + error + "</li>").join("") + "</ul>");
                    $("#submitMultipleBtn").prop("disabled", true);
                } else {
                    $("#submitMultipleBtn").prop("disabled", false);
                }

                if (!data || data.length === 0) {
                    console.log("No data to display in preview");
                    previewErrors.html("<p>No valid data found in the Excel file.</p>");
                    return;
                }

                data.forEach((resident, index) => {
                    console.log(`Rendering resident ${index}:`, resident);
                    console.log("Name:", resident.name);
                    console.log("DOB:", resident.dob);
                    console.log("Gender:", resident.gender);
                    console.log("Phone:", resident.phone);
                    console.log("Address:", resident.address);
                    console.log("Apartment:", resident.apartment);
                    console.log("CCCD:", resident.cccd);

                    const $row = $("<tr></tr>");
                    $row.append($("<td></td>").text(resident.name || 'N/A'));
                    $row.append($("<td></td>").text(resident.dob || 'N/A'));
                    $row.append($("<td></td>").text(resident.gender || 'N/A'));
                    $row.append($("<td></td>").text(resident.phone || 'N/A'));
                    $row.append($("<td></td>").text(resident.address || 'N/A'));
                    $row.append($("<td></td>").text(resident.apartment || 'N/A'));
                    $row.append($("<td></td>").text(resident.cccd || 'N/A'));

                    console.log(`Appending row ${index}:`, $row[0].outerHTML);
                    previewTableBody.append($row);
                });

                console.log("Table body HTML after appending:", previewTableBody.html());

                $("#backBtn").on("click", function () {
                    window.location.href = "${pageContext.request.contextPath}/addNewResident";
                });

                $("#submitMultipleBtn").on("click", function () {
                    console.log("Submitting multiple residents:", multipleResidentsData);

                    // Show loading indicator and disable button
                    $("#loadingIndicator").show();
                    $("#submitMultipleBtn").prop("disabled", true);
                    $("#backBtn").prop("disabled", true); // Optionally disable back button too

                    $.ajax({
                        url: "addNewResident",
                        type: "POST",
                        contentType: "application/json",
                        data: JSON.stringify({residents: multipleResidentsData}),
                        success: function (response) {
                            // Hide loading indicator and re-enable buttons
                            $("#loadingIndicator").hide();
                            $("#submitMultipleBtn").prop("disabled", false);
                            $("#backBtn").prop("disabled", false);

                            if (response.success) {
                                alert("Multiple residents added successfully!");
                                window.location.href = "${pageContext.request.contextPath}/view-resident";
                            } else {
                                alert("Error adding residents: " + (response.errors ? response.errors.join("\n") : response.error));
                            }
                        },
                        error: function () {
                            // Hide loading indicator and re-enable buttons on error
                            $("#loadingIndicator").hide();
                            $("#submitMultipleBtn").prop("disabled", false);
                            $("#backBtn").prop("disabled", false);
                            alert("Failed to add multiple residents.");
                        }
                    });
                });
            });
        </script>
    </body>
</html>