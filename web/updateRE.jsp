<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
    <head>
        <!-- [Previous head content unchanged] -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
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

            .add-account-button {
                display: flex;
                align-items: center;
                cursor: pointer;
                color: #4a90e2;
                font-weight: bold;
                margin-bottom: 10px;
            }

            .add-account-button i {
                margin-right: 5px;
            }

            .hidden {
                display: none;
            }

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

            .error-message {
                color: red;
                font-size: 14px;
                margin-top: 5px;
                display: block;
            }
            .sucess-message{
                color: #13ae38;
                font-size: 14px;
                margin-top: 5px;
                display: block;
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
                                <h1 style="font-weight: bold">Update Resident Information</h1>
                                <form action="updateRE" method="post">
                                    <div class="form-group">
                                        <input type="hidden" name="rid" value="${resident.pId}">
                                        <div class="three-cols">
                                            <div class="col">
                                                <label for="name">Name</label>
                                                <input type="text" id="name" name="name" placeholder="Enter full name" required value="${resident.name}"/>
                                            </div>
                                            <div class="col">
                                                <label for="dob">Date of Birth</label>
                                                <input type="date" id="bod" name="bod" required value="${resident.bod}" />

                                                <span id="dob-error" class="error-message"></span>
                                            </div>
                                            <div class="col">
                                                <label>Gender</label>
                                                <div class="gender-options">
                                                    <label for="male">
                                                        <input type="radio" id="male" name="gender" value="M" required ${resident.gender == 'M' ? 'checked' : ''} /> Male
                                                    </label>
                                                    <label for="female">
                                                        <input type="radio" id="female" name="gender" value="F" required ${resident.gender == 'F' ? 'checked' : ''} /> Female
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="address">Address</label>
                                        <input type="text" id="address" name="address" placeholder="Enter address" required value="${resident.address}" />
                                    </div>

                                    <div class="form-group">
                                        <div class="two-cols">
                                            <div class="col">
                                                <label for="id">ID</label>
                                                <input type="number" id="id" name="id" placeholder="Enter ID" value="${resident.cccd}" />
                                                <span id="id-error" class="error-message"></span>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="two-cols">
                                            <div class="col">
                                                <label for="phone">Phone</label>
                                                <input type="tel" id="phone" name="phone" placeholder="Enter phone number" value="${resident.phone}" required />
                                                <span id="phone-error" class="error-message"></span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-button">
                                        <span class="error-message">${error}</span>
                                        <span class="sucess-message">${success}</span>
                                        <button type="submit">Update Resident</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>

                    <script src="js/jquery.min.js"></script>
                    <script src="js/popper.min.js"></script>
                    <script src="js/bootstrap.min.js"></script>
                    <script src="js/custom.js"></script>
                    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

                    <script>
                        $("#dob").on("input", function () {
                            const dobValue = $(this).val();
                            const dobDate = new Date(dobValue);
                            const currentDate = new Date();

                            if (dobDate > currentDate) {
                                $("#dob-error").text("Date of Birth cannot be in the future.");
                            } else {
                                $("#dob-error").text("");
                            }
                            updateSubmitButtonState();
                        });
                        function toggleAccountFields() {
                            const accountFields = document.getElementById("account-fields");
                            const usernameInput = document.getElementById("username");
                            const emailInput = document.getElementById("email");

                            if (accountFields.classList.contains("hidden")) {
                                accountFields.classList.remove("hidden");
                                usernameInput.setAttribute("required", "true");
                                emailInput.setAttribute("required", "true");
                            } else {
                                accountFields.classList.add("hidden");
                                usernameInput.removeAttribute("required");
                                emailInput.removeAttribute("required");
                                usernameInput.value = "";
                                emailInput.value = "";
                                $("#username-error").text("");
                                $("#email-error").text("");
                            }
                            updateSubmitButtonState();
                        }

                        $(document).ready(function () {
                            const submitButton = $('button[type="submit"]');
                            const originalId = $("#id").val();  // Store original ID value
                            const originalPhone = $("#phone").val();  // Store original phone value

                            function updateSubmitButtonState() {
                                if (
                                        $("#email-error").text() ||
                                        $("#phone-error").text() ||
                                        $("#id-error").text() ||
                                        $("#username-error").text() ||
                                        $("#dob-error").text()
                                        ) {
                                    submitButton.prop("disabled", true);
                                } else {
                                    submitButton.prop("disabled", false);
                                }
                            }

                            function debounce(func, wait) {
                                let timeout;
                                return function () {
                                    clearTimeout(timeout);
                                    timeout = setTimeout(func.bind(this), wait);
                                };
                            }

                            function checkDuplicate(type, value, errorField, originalValue) {
                                if (!value || value === originalValue) {
                                    $(errorField).text("");  // Clear error if empty or matches original
                                    updateSubmitButtonState();
                                    return;
                                }

                                $.ajax({
                                    url: "checkDuplicateResidentInfor",
                                    type: "GET",
                                    data: {type: type, value: value},
                                    dataType: "json",
                                    success: function (response) {
                                        if (response && response.exists) {
                                            $(errorField).text(type.charAt(0).toUpperCase() + type.slice(1) + " already exists.");
                                        } else {
                                            $(errorField).text("");
                                        }
                                        updateSubmitButtonState();
                                    },
                                    error: function (xhr, status, error) {
                                        console.error("AJAX Error:", status, error);
                                        $(errorField).text("Error checking duplicate");
                                        updateSubmitButtonState();
                                    }
                                });
                            }

                            $("#email").on("input", debounce(function () {
                                checkDuplicate("email", $(this).val(), "#email-error");
                            }, 300));

                            $("#phone").on("input", debounce(function () {
                                checkDuplicate("phone", $(this).val(), "#phone-error", originalPhone);
                            }, 300));

                            $("#id").on("input", debounce(function () {
                                checkDuplicate("id", $(this).val(), "#id-error", originalId);
                            }, 300));

                            $("#username").on("input", debounce(function () {
                                if ($(this).val().includes(" ")) {
                                    $("#username-error").text("Username cannot contain spaces.");
                                    updateSubmitButtonState();
                                } else {
                                    checkDuplicate("username", $(this).val(), "#username-error");
                                }
                            }, 300));

                            $("form").on("submit", function (event) {
                                let phone = $("#phone").val();
                                let id = $("#id").val();
                                let username = $("#username").val();
                                let accountFieldsVisible = !$("#account-fields").hasClass("hidden");

                                let phonePattern = /^\d{10}$/;
                                let idPattern = /^\d{12}$/;
                                let usernamePattern = /^.{4,}$/;

                                let valid = true;

                                if (!phonePattern.test(phone)) {
                                    $("#phone-error").text("Phone number must be exactly 10 digits.");
                                    valid = false;
                                }

                                if (id && !idPattern.test(id)) {  // Only validate if ID is provided
                                    $("#id-error").text("ID must be exactly 12 digits.");
                                    valid = false;
                                }

                                if (accountFieldsVisible && !usernamePattern.test(username)) {
                                    $("#username-error").text("Username must be at least 4 characters.");
                                    valid = false;
                                }

                                if (!valid) {
                                    event.preventDefault();
                                }
                            });
                        });

                    </script>
                </div>
            </div>
        </div>
    </body>
</html>