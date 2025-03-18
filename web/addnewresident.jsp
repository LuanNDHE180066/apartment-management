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
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
        <style>
            .form-container {
                max-width: 900px;
                margin: 50px auto;
                padding: 40px;
                background: #ffffff;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                border: none !important; /* Remove any borders */
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

            /* Structuring input fields */
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

            /* Gender options displayed horizontally */
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

            /* Submit button styling */
            .form-button {
                text-align: center;
                margin-top: 20px;
                border: none !important; /* Remove any borders */
                position: relative;
            }

            .form-button::before,
            .form-button::after {
                display: none !important; /* Remove any pseudo-elements */
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

            /* Error messages */
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
            /* Success message styling */
            .success-msg {
                color: #2E7D32; /* Dark green for success, matching the active status color */
                font-weight: bold;
                font-size: 14px;
                margin-top: 5px;
                display: block;
                text-align: center; /* Center the message for consistency with the form */
            }

            /* Remove any inherited borders from container */
            .container-fluid {
                border: none !important;
            }

            /* Hide any horizontal rules */
            hr {
                display: none !important;
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
                                <form action="addNewResident" method="post">
                                    <!-- Single Column Layout -->
                                    <div class="form-group one-col">
                                        <label for="name">Name</label>
                                        <input type="text" id="name" name="name" placeholder="Enter full name" required />
                                    </div>

                                    <div class="form-group one-col">
                                        <label for="dob">Date of Birth</label>
                                        <input type="date" id="dob" name="dob" required />
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
                                            <option value="6">Render</option>
                                        </select>
                                    </div>

                                    <div class="form-group one-col">
                                        <label for="id">ID</label>
                                        <input type="number" id="id" name="id" placeholder="Enter ID" />
                                        <span id="id-error" class="error-message"></span>
                                    </div>

                                    <div class="form-group one-col">
                                        <label>is Representative</label>
                                        <div class="homeowner-options">
                                            <label for="homeowner-yes">
                                                <input type="radio" id="homeowner-yes" name="isRepresent" value="yes" onclick="toggleFields(true)" required /> Yes
                                            </label>
                                            <label for="homeowner-no">
                                                <input type="radio" id="homeowner-no" name="isRepresent" value="no" onclick="toggleFields(false)" required /> No
                                            </label>
                                        </div>
                                    </div>

                                    <div class="form-group one-col" id="username-container" style="display: none;">
                                        <label for="username">Username</label>
                                        <input type="text" id="username" name="username" placeholder="Enter username" />
                                        <span id="username-error" class="error-message"></span>
                                    </div>

                                    <div class="form-group one-col" id="email-container" style="display: none">
                                        <label for="email">Email</label>
                                        <input type="email" id="email" name="email" placeholder="Enter email" required />
                                        <span id="email-error" class="error-message"></span>
                                    </div>

                                    <!-- Submit and Back Button -->
                                    <div class="form-button">
                                        <h5 class="error-msg">${error}</h5>
                                        <h5 class="success-msg">${successMessage}</h5>
                                        <button type="submit">Add Resident</button>
                                        <button type="button" id="backToMainMenu" class="btn btn-secondary" style="margin-left: 10px; margin-bottom: 8px">Back to Main Menu</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <!-- JavaScript -->
                    <script src="js/jquery.min.js"></script>
                    <script src="js/popper.min.js"></script>
                    <script src="js/bootstrap.min.js"></script>
                    <script src="js/custom.js"></script>
                    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

                    <script>
                                                    function toggleFields(show) {
                                                        const usernameContainer = document.getElementById("username-container");
                                                        const emailContainer = document.getElementById("email-container");
                                                        const usernameInput = document.getElementById("username");
                                                        const emailInput = document.getElementById("email");

                                                        if (show) {
                                                            usernameContainer.style.display = "block";
                                                            emailContainer.style.display = "block";
                                                            usernameInput.setAttribute("required", "true");
                                                            emailInput.setAttribute("required", "true");
                                                        } else {
                                                            usernameContainer.style.display = "none";
                                                            emailContainer.style.display = "none";
                                                            usernameInput.removeAttribute("required");
                                                            emailInput.removeAttribute("required");
                                                            usernameInput.value = "";
                                                            emailInput.value = "";
                                                        }
                                                    }

                                                    $(document).ready(function () {
                                                        const submitButton = $('button[type="submit"]');
                                                        const backToMainMenu = $('#backToMainMenu');

                                                        // Redirect to view-resident on Back to Main Menu click
                                                        backToMainMenu.on('click', function () {
                                                            window.location.href = '${pageContext.request.contextPath}/view-resident';
                                                        });

                                                        function updateSubmitButtonState() {
                                                            if ($("#email-error").text() || $("#phone-error").text() || $("#id-error").text() || $("#username-error").text()) {
                                                                submitButton.prop("disabled", true);
                                                            } else {
                                                                submitButton.prop("disabled", false);
                                                            }
                                                        }

                                                        function checkDuplicate(type, value, errorField) {
                                                            if (value) {
                                                                $.ajax({
                                                                    url: "checkDuplicateResidentInfor",
                                                                    type: "GET",
                                                                    data: {type: type, value: value},
                                                                    success: function (response) {
                                                                        if (response.exists) {
                                                                            $(errorField).text(type.charAt(0).toUpperCase() + type.slice(1) + " already exists.");
                                                                        } else {
                                                                            $(errorField).text("");
                                                                        }
                                                                        updateSubmitButtonState();
                                                                    }
                                                                });
                                                            } else {
                                                                $(errorField).text("");
                                                                updateSubmitButtonState();
                                                            }
                                                        }

                                                        $("#email").on("input", function () {
                                                            checkDuplicate("email", $(this).val(), "#email-error");
                                                        });

                                                        $("#phone").on("input", function () {
                                                            checkDuplicate("phone", $(this).val(), "#phone-error");
                                                        });

                                                        $("#id").on("input", function () {
                                                            checkDuplicate("id", $(this).val(), "#id-error");
                                                        });

                                                        $("#username").on("input", function () {
                                                            if ($(this).val().includes(" ")) {
                                                                $("#username-error").text("Username cannot contain spaces.");
                                                            } else {
                                                                checkDuplicate("username", $(this).val(), "#username-error");
                                                            }
                                                            updateSubmitButtonState();
                                                        });

                                                        $("form").on("submit", function (event) {
                                                            let phone = $("#phone").val();
                                                            let id = $("#id").val();
                                                            let username = $("#username").val();
                                                            let usernameContainer = $("#username-container").css("display") !== "none";

                                                            let phonePattern = /^\d{10}$/;
                                                            let idPattern = /^\d{12}$/;
                                                            let usernamePattern = /^.{4,}$/;

                                                            let valid = true;

                                                            if (!phonePattern.test(phone)) {
                                                                $("#phone-error").text("Phone number must be exactly 10 digits.");
                                                                valid = false;
                                                            }

                                                            if (!idPattern.test(id)) {
                                                                $("#id-error").text("ID must be exactly 12 digits.");
                                                                valid = false;
                                                            }

                                                            if (usernameContainer && !usernamePattern.test(username)) {
                                                                $("#username-error").text("Username must be at least 4 characters.");
                                                                valid = false;
                                                            }

                                                            if (!valid) {
                                                                event.preventDefault();
                                                            }
                                                        });
                                                    });

                                                    document.styleSheets[0].insertRule(`
                            select {
                                width: 100%;
                                padding: 12px;
                                border: 1px solid #ccc;
                                border-radius: 6px;
                                font-size: 16px;
                            }
                        `, 0);
                                                    document.styleSheets[0].insertRule(`
                            select:focus {
                                border-color: #4a90e2;
                                outline: none;
                                box-shadow: 0 0 4px rgba(74, 144, 226, 0.5);
                            }
                        `, 0);
                    </script>
                </div>
            </div>
        </div>
    </body>
</html>