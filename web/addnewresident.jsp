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
        <title>Apartment Management</title>
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
            .form-container {
                max-width: 900px;
                margin: 50px auto;
                padding: 40px;
                background: #ffffff;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                border: none !important;
            }

            .form-group {
                margin-bottom: 20px;
            }

            label {
                font-weight: 600;
                margin-bottom: 5px;
                display: block;
            }

            input {
                width: 100%;
                padding: 12px;
                border: 1px solid #ccc;
                border-radius: 6px;
                font-size: 16px;
            }

            input:focus {
                border-color: #4a90e2;
                outline: none;
                box-shadow: 0 0 4px rgba(74, 144, 226, 0.5);
            }

            .one-col {
                display: flex;
                flex-direction: column;
            }

            .two-cols,
            .three-cols {
                display: flex;
                justify-content: space-between;
                gap: 15px;
            }

            .two-cols .col {
                width: 48%;
            }

            .three-cols .col {
                width: 32%;
            }

            .gender-options {
                display: flex;
                gap: 15px;
                margin-top: 5px;
            }

            .gender-options label {
                display: flex;
                align-items: center;
                gap: 5px;
            }

            .form-button {
                text-align: center;
                margin-top: 20px;
                border: none !important;
                position: relative;
            }

            .form-button::before,
            .form-button::after {
                display: none !important;
            }

            .form-button button {
                padding: 12px 30px;
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

            .error-message {
                color: red;
                font-size: 14px;
                margin-top: 5px;
                display: block;
            }

            input[type="radio"]:focus {
                outline: none;
                box-shadow: none;
                border: none;
            }

            .error-msg {
                color: red;
                font-weight: bold;
            }

            .success-msg {
                color: #2E7D32;
                font-weight: bold;
                font-size: 14px;
                margin-top: 5px;
                display: block;
                text-align: center;
            }

            .container-fluid {
                border: none !important;
                display: block !important;
                visibility: visible !important;
            }

            .midde_cont {
                display: block !important;
                visibility: visible !important;
            }

            #content {
                display: block !important;
                visibility: visible !important;
            }

            hr {
                display: none !important;
            }

            .import-excel-btn, .export-excel-btn {
                padding: 12px 20px;
                font-size: 16px;
                border: none;
                border-radius: 6px;
                color: #ffffff;
                cursor: pointer;
                transition: background-color 0.3s;
                margin-bottom: 10px;
            }

            .import-excel-btn {
                background-color: #28a745;
            }

            .import-excel-btn:hover {
                background-color: #218838;
            }

            .export-excel-btn {
                background-color: #ff9800;
                margin-left: 10px;
            }

            .export-excel-btn:hover {
                background-color: #e68900;
            }

            #excelFile {
                display: none;
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
                            <div class="form-container">
                                <h1 style="font-weight: bold">Add New Resident</h1>
                                <form id="residentForm" action="addNewResident" method="post" enctype="multipart/form-data">
                                    <!-- Excel Import/Export Section -->
                                    <div class="form-group one-col">
                                        <button type="button" class="import-excel-btn" id="importExcelBtn">Import Excel</button>
                                        <button type="button" class="export-excel-btn" id="exportExcelTemplateBtn">Export Excel Template</button>
                                        <input type="file" id="excelFile" name="excelFile" accept=".xls,.xlsx" />
                                        <span style="font-size: 12px; color: #666;">Or enter details manually below</span>
                                    </div>

                                    <!-- Single Resident Form -->
                                    <div id="singleResidentForm">
                                        <div class="form-group one-col">
                                            <label for="name">Name</label>
                                            <input type="text" id="name" name="name" placeholder="Enter full name" required />
                                        </div>
                                        <div class="form-group one-col">
                                            <label for="bod">Date of Birth</label>
                                            <input type="date" id="bod" name="bod" required />
                                        </div>
                                        <div class="form-group one-col">
                                            <label>Gender</label>
                                            <div class="gender-options">
                                                <label for="male">
                                                    <input type="radio" id="male" name="gender" value="M" required /> Male
                                                </label>
                                                <label for="female">
                                                    <input type="radio" id="female" name="gender" value="F" required /> Female
                                                </label>
                                            </div>
                                        </div>
                                        <div class="form-group one-col">
                                            <label for="phone">Phone</label>
                                            <input type="tel" id="phone" name="phone" placeholder="Enter phone number" required />
                                            <span id="phone-error" class="error-message"></span>
                                        </div>
                                        <div class="form-group one-col">
                                            <label for="address">Address</label>
                                            <input type="text" id="address" name="address" placeholder="Enter address" required />
                                        </div>
                                        <div class="form-group one-col">
                                            <label for="apartment">Apartment Number</label>
                                            <select id="apartment" name="apartment" required>
                                                <option value="">Select Apartment</option>
                                                <c:forEach items="${requestScope.apts}" var="apt">
                                                    <option value="${apt.id}">${apt.id}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="form-group one-col">
                                            <label for="role">Role</label>
                                            <select id="role" name="role" required>
                                                <option value="1">Resident</option>
                                                <option value="6" selected="">Render</option>
                                            </select>
                                        </div>
                                        <div class="form-group one-col">
                                            <label for="cccd">ID</label>
                                            <input type="number" id="cccd" name="cccd" placeholder="Enter ID" />
                                            <span id="cccd-error" class="error-message"></span>
                                        </div>
                                        <div class="form-group one-col" id="username-container" style="display: none;">
                                            <label for="username">Username</label>
                                            <input type="text" id="username" name="username" placeholder="Enter username" />
                                            <span id="username-error" class="error-message"></span>
                                        </div>
                                        <div class="form-group one-col" id="email-container" style="display: none;">
                                            <label for="email">Email</label>
                                            <input type="email" id="email" name="email" placeholder="Enter email" />
                                            <span id="email-error" class="error-message"></span>
                                        </div>
                                    </div>

                                    <!-- Submit and Back Button -->
                                    <div class="form-button">
                                        <h5 class="error-msg">${error}</h5>
                                        <h5 class="success-msg">${successMessage}</h5>
                                        <button type="submit" id="submitSingleBtn">Add Resident</button>
                                        <button type="button" id="backToMainMenu" class="btn btn-secondary" style="margin-left: 10px; margin-bottom: 8px">Back to Main Menu</button>
                                    </div>
                                </form>
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
        <!-- SheetJS for Excel generation -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.18.5/xlsx.full.min.js"></script>

        <script>
            function toggleRoleFields() {
                const role = $("#role").val();
                const usernameContainer = document.getElementById("username-container");
                const emailContainer = document.getElementById("email-container");
                const usernameInput = document.getElementById("username");
                const emailInput = document.getElementById("email");

                if (role === "1") { // Resident
                    usernameContainer.style.display = "block";
                    emailContainer.style.display = "block";
                    usernameInput.setAttribute("required", "true");
                    emailInput.setAttribute("required", "true");
                } else { // Render
                    usernameContainer.style.display = "none";
                    emailContainer.style.display = "none";
                    usernameInput.removeAttribute("required");
                    emailInput.removeAttribute("required");
                    usernameInput.value = "";
                    emailInput.value = "";
                    $("#username-error").text("");
                    $("#email-error").text("");
                }
                updateSubmitButtonState();
            }

            function checkDuplicate(type, value, errorField) {
                if (value) {
                    $.ajax({
                        url: "checkDuplicateResidentInfor",
                        type: "GET",
                        data: {type: type, value: value},
                        success: function (response) {
                            $(errorField).text(response.exists ?
                                type.charAt(0).toUpperCase() + type.slice(1) + " already exists." : "");
                            updateSubmitButtonState();
                        }
                    });
                } else {
                    $(errorField).text("");
                    updateSubmitButtonState();
                }
            }

            function updateSubmitButtonState() {
                const submitButton = $("#submitSingleBtn");
                const hasErrors = $("#email-error").text() || $("#phone-error").text() ||
                        $("#cccd-error").text() || $("#username-error").text();
                submitButton.prop("disabled", !!hasErrors);
            }

            function resetForm() {
                $("#excelFile").val("");
                $("#residentForm")[0].reset();
                toggleRoleFields();
            }

            function exportExcelTemplate() {
                try {
                    // Define headers matching the import format
                    const headers = [
                        "name", "dob", "gender", "phone", "address", "apartment", "cccd"
                    ];

                    // Get apartment IDs from the select options
                    const apartments = Array.from(document.querySelectorAll("#apartment option"))
                        .filter(opt => opt.value !== "")
                        .map(opt => opt.value);

                    // Define dropdown options
                    const genderOptions = ["M", "F"];

                    // Create a new workbook and worksheet
                    const wb = XLSX.utils.book_new();
                    const ws = XLSX.utils.json_to_sheet([{}], { header: headers });

                    // Add data validation for dropdowns
                    const range = XLSX.utils.decode_range(ws["!ref"]);
                    for (let R = range.s.r + 1; R <= range.e.r + 10; R++) { // Add validation for 10 rows
                        // Gender dropdown (column 2)
                        ws[XLSX.utils.encode_cell({ r: R, c: 2 })] = { t: "s", v: "" };
                        ws[XLSX.utils.encode_cell({ r: R, c: 2 })].s = {
                            dataValidation: {
                                type: "list",
                                allowBlank: true,
                                formula1: `"${genderOptions.join(",")}"`
                            }
                        };

                        // Apartment dropdown (column 5)
                        ws[XLSX.utils.encode_cell({ r: R, c: 5 })] = { t: "s", v: "" };
                        ws[XLSX.utils.encode_cell({ r: R, c: 5 })].s = {
                            dataValidation: {
                                type: "list",
                                allowBlank: true,
                                formula1: `"${apartments.join(",")}"`
                            }
                        };
                    }

                    // Set column widths
                    ws["!cols"] = headers.map(() => ({ wch: 15 }));

                    // Append the worksheet to the workbook
                    XLSX.utils.book_append_sheet(wb, ws, "Residents");

                    // Generate the Excel file as a binary array
                    const fileData = XLSX.write(wb, { bookType: "xlsx", type: "array" });

                    // Create a Blob from the binary data
                    const blob = new Blob([fileData], { type: "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" });

                    // Create a download link and trigger the download
                    const url = window.URL.createObjectURL(blob);
                    const a = document.createElement("a");
                    a.href = url;
                    a.download = "Resident_Import_Template.xlsx";
                    document.body.appendChild(a);
                    a.click();
                    document.body.removeChild(a);
                    window.URL.revokeObjectURL(url);

                    console.log("Excel template generated and downloaded successfully.");
                } catch (err) {
                    console.error("Error generating Excel template:", err);
                    alert("Failed to generate Excel template: " + err.message);
                }
            }

            $(document).ready(function () {
                console.log("jQuery version:", $.fn.jquery);

                $("#role").on("change", function () {
                    toggleRoleFields();
                });

                $("#backToMainMenu").on("click", function () {
                    window.location.href = "${pageContext.request.contextPath}/view-resident";
                });

                $("#email").on("input", function () {
                    checkDuplicate("email", $(this).val(), "#email-error");
                });

                $("#phone").on("input", function () {
                    checkDuplicate("phone", $(this).val(), "#phone-error");
                });

                $("#cccd").on("input", function () {
                    checkDuplicate("cccd", $(this).val(), "#cccd-error");
                });

                $("#username").on("input", function () {
                    if ($(this).val().includes(" ")) {
                        $("#username-error").text("Username cannot contain spaces.");
                    } else {
                        checkDuplicate("username", $(this).val(), "#username-error");
                    }
                    updateSubmitButtonState();
                });

                $("#residentForm").on("submit", function (event) {
                    const role = $("#role").val();
                    const phone = $("#phone").val();
                    const cccd = $("#cccd").val();
                    const username = $("#username").val();
                    const email = $("#email").val();
                    const usernameContainer = $("#username-container").css("display") !== "none";
                    const emailContainer = $("#email-container").css("display") !== "none";

                    const phonePattern = /^\d{10}$/;
                    const cccdPattern = /^\d{12}$/;
                    const usernamePattern = /^.{4,}$/;

                    let valid = true;

                    if (!phonePattern.test(phone)) {
                        $("#phone-error").text("Phone number must be exactly 10 digits.");
                        valid = false;
                    }
                    if (!cccdPattern.test(cccd)) {
                        $("#cccd-error").text("ID must be exactly 12 digits.");
                        valid = false;
                    }
                    if (role === "1" && usernameContainer && !usernamePattern.test(username)) {
                        $("#username-error").text("Username must be at least 4 characters.");
                        valid = false;
                    }
                    if (role === "1" && emailContainer && !email) {
                        $("#email-error").text("Email is required for Resident role.");
                        valid = false;
                    }

                    if (!valid) {
                        event.preventDefault();
                    }
                });

                $("#importExcelBtn").on("click", function () {
                    $("#excelFile").click();
                });

                $("#excelFile").on("change", function () {
                    const fileInput = this;
                    if (fileInput.files.length > 0) {
                        console.log("File selected:", fileInput.files[0].name);
                        const formData = new FormData();
                        formData.append("excelFile", fileInput.files[0]);

                        $.ajax({
                            url: "parseExcelForResident",
                            type: "POST",
                            data: formData,
                            processData: false,
                            contentType: false,
                            success: function (response) {
                                console.log("AJAX success response:", response);
                                if (response.success) {
                                    window.location.href = "${pageContext.request.contextPath}/previewExcelData.jsp";
                                } else {
                                    console.error("Error from server:", response.error);
                                    alert("Error parsing Excel file: " + response.error);
                                    resetForm();
                                }
                            },
                            error: function (xhr, status, error) {
                                console.error("AJAX error:", status, error);
                                console.error("Response text:", xhr.responseText);
                                alert("Failed to upload or parse the Excel file. Check console for details.");
                                resetForm();
                            }
                        });
                    } else {
                        console.log("No file selected");
                    }
                });

                $("#exportExcelTemplateBtn").on("click", function () {
                    exportExcelTemplate();
                });

                // Initial call to set the correct state based on default role
                toggleRoleFields();

                document.styleSheets[0].insertRule(`
                    select {
                        width: 100%;
                        padding: 12px;
                        border: 1px solid #ccc;
                        border-radius: 6px;
                        font-size: 16px;
                    }`, 0);
                document.styleSheets[0].insertRule(`
                    select:focus {
                        border-color: #4a90e2;
                        outline: none;
                        box-shadow: 0 0 4px rgba(74, 144, 226, 0.5);
                    }`, 0);
            });
        </script>
    </body>
</html>