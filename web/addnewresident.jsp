<!DOCTYPE html>
<%@taglib
    prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <html lang="en">
        <head>
            <!-- basic -->
            <meta charset="utf-8" />
            <meta http-equiv="X-UA-Compatible" content="IE=edge" />
            <!-- mobile metas -->
            <meta name="viewport" content="width=device-width, initial-scale=1" />
            <meta name="viewport" content="initial-scale=1, maximum-scale=1" />
            <!-- site metas -->
            <title>Apartment management</title>            <link rel="icon" href="images/fevicon.png" type="image/png" />
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
                    border: 1px solid #e3e3e3;
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


            </style>
        </head>
        <body class="inner_page tables_page">
            <div class="full_container">
                <div class="inner_container">
                    <!-- Sidebar -->
                    <%@include file="sidebar.jsp" %>
                    <!-- End sidebar -->
                    <div id="content">
                        <!-- Topbar -->
                        <%@include file="topbar.jsp" %>
                        <!-- End topbar -->
                        <div class="midde_cont">
                            <div class="container-fluid">
                                <div class="form-container">
                                    <h1 style="font-weight: bold">Add New Resident</h1>
                                    <form action="addNewResident" method="post">
                                        <!-- Personal Information -->
                                        <div class="form-group">
                                            <div class="three-cols">
                                                <div class="col">
                                                    <label for="name">Name</label>
                                                    <input type="text" id="name" name="name" placeholder="Enter full name" required />
                                                </div>
                                                <div class="col">
                                                    <label for="dob">Date of Birth</label>
                                                    <input type="date" id="dob" name="dob" required />
                                                </div>
                                                <div class="col">
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
                                            </div>
                                        </div>

                                        <!-- Address -->
                                        <div class="form-group">
                                            <label for="address">Address</label>
                                            <input type="text" id="address" name="address" placeholder="Enter address" required />
                                        </div>

                                        <!-- ID and Homeowner Status -->
                                        <div class="form-group">
                                            <div class="two-cols">
                                                <div class="col">
                                                    <label for="id">ID</label>
                                                    <input type="number" id="id" name="id" placeholder="Enter ID" />
                                                    <span id="id-error" class="error-message"></span>
                                                </div>
                                                <div class="col">
                                                    <label>Homeowner</label>
                                                    <div class="homeowner-options">
                                                        <label for="homeowner-yes">
                                                            <input type="radio" id="homeowner-yes" name="isHomeOwner" value="yes" onclick="toggleFields(true)" required /> Yes
                                                        </label>
                                                        <label for="homeowner-no">
                                                            <input type="radio" id="homeowner-no" name="isHomeOwner" value="no" onclick="toggleFields(false)" required /> No
                                                        </label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <!-- Contact Information -->
                                        <div class="form-group">
                                            <div class="two-cols">
                                                <div class="col">
                                                    <label for="phone">Phone</label>
                                                    <input type="tel" id="phone" name="phone" placeholder="Enter phone number" required />
                                                    <span id="phone-error" class="error-message"></span>
                                                </div>
                                                <div class="col" id="email-container" style="display: none">
                                                    <label for="email">Email</label>
                                                    <input type="email" id="email" name="email" placeholder="Enter email" required />
                                                    <span id="email-error" class="error-message"></span>
                                                </div>
                                            </div>
                                        </div>

                                        <!-- Username (Visible Only for Homeowners) -->
                                        <div class="form-group" id="username-container" style="display: none;">
                                            <label for="username">Username</label>
                                            <input type="text" id="username" name="username" placeholder="Enter username" />
                                            <span id="username-error" class="error-message"></span>
                                        </div>

                                        <!-- Submit Button -->
                                        <div class="form-button">
                                            <button type="submit">Add Resident</button>
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

                                                                    function updateSubmitButtonState() {
                                                                        if (
                                                                                $("#email-error").text() ||
                                                                                $("#phone-error").text() ||
                                                                                $("#id-error").text() ||
                                                                                $("#username-error").text()
                                                                                ) {
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

                                                                        if (!valid)
                                                                            event.preventDefault();
                                                                    });
                                                                });
                        </script>
                    </div>
                </div>
            </div>
        </body>
    </html>
