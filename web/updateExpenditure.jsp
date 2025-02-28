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
                body {
                    font-family: Arial, sans-serif;
                    background-color: #f4f4f9;
                    margin: 0;
                    padding: 0px;
                }

                .form-container {
                    background: #ffffff;
                    padding: 40px;
                    border-radius: 8px;
                    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                    max-width: 800px;
                    margin: auto;
                }

                .form-container h1 {
                    text-align: center;
                    margin-bottom: 30px;
                    color: #333;
                }

                .form-group {
                    margin-bottom: 10px;
                }

                .form-group label {
                    display: block;
                    margin-bottom: 8px;
                    font-weight: bold;
                    color: #555;
                }

                .form-group input,
                .form-group select {
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
                .form-group select:focus {
                    border-color: #4a90e2;
                    outline: none;
                }

                .two-cols {
                    display: flex;
                    justify-content: space-between;
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


            </style>
        </head>
        <body class="inner_page tables_page">
            <div class="full_container">
                <div class="inner_container">
                    <!-- Sidebar  -->
                    <%@include file="sidebar.jsp" %>
                    <!-- end sidebar -->
                    <!-- right content -->
                    <div id="content">
                        <!-- topbar -->
                        <%@include file="topbar.jsp" %>
                        <!-- end topbar -->
                        <div class="midde_cont">
                            <div class="container-fluid">
                                <div class="form-container">
                                    <h1>Update Staff Information</h1>
                                    <form action="updateStaffInfor" method="post">
                                        <div class="form-group">
                                            <input type="text" id="staffID" name="staffID" value="${staff.id}" hidden=""/>
                                            <div class="two-cols">
                                                <div class="col" style="padding: 0; margin-right: 5px">
                                                    <label for="name">Name</label>
                                                    <input
                                                        type="text"
                                                        id="name"
                                                        name="name"
                                                        placeholder="Enter full name"
                                                        value="${staff.name}"
                                                        required=""
                                                        />
                                                </div>
                                                <div class="col" style="padding: 0; margin-right: 5px">
                                                    <label for="dob">Date of Birth</label>
                                                    <input type="date" id="dob" name="dob" value="${staff.bod}" required=""/>
                                                    <span id="dob-error" style="color: red"></span>

                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="one-col" style="padding: 0; margin-right: 5px">
                                                <label for="address">Address</label>
                                                <input
                                                    type="text"
                                                    id="address"
                                                    name="address"
                                                    placeholder="Enter address"
                                                    value="${staff.address}"
                                                    required=""
                                                    />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="two-cols">
                                                <div class="col" style="padding: 0; margin-right: 5px">
                                                    <label for="phone">Phone</label>
                                                    <input
                                                        type="number"
                                                        id="phone"
                                                        name="phone"
                                                        placeholder="Enter phone number"
                                                        value="${staff.phone}"
                                                        required=""
                                                        />
                                                    <span id="phone-error" style="color: red"></span>
                                                </div>
                                                <div class="col" style="padding: 0; margin-right: 5px">
                                                    <label for="email">Email</label>
                                                    <input
                                                        type="email"
                                                        id="email"
                                                        name="email"
                                                        placeholder="Enter email"
                                                        value="${staff.email}"
                                                        required=""
                                                        />
                                                    <span id="email-error" style="color: red"></span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group" >
                                            <div class="three-cols">
                                                <div class="col" style="padding: 0; margin-right: 5px ">
                                                    <label for="cccd">CCCD</label>
                                                    <input
                                                        type="text"
                                                        id="cccd"
                                                        name="cccd"
                                                        placeholder="Enter CCCD"
                                                        value="${staff.cccd}"
                                                        required=""
                                                        />
                                                    <span id="cccd-error" style="color: red"></span>
                                                </div>
                                                <div class="col" style="padding: 0; margin-right: 5px">
                                                    <label for="education">Education</label>
                                                    <input
                                                        type="text"
                                                        id="education"
                                                        name="education"
                                                        placeholder="Enter education"
                                                        value="${staff.education}"
                                                        required=""
                                                        />
                                                </div>
                                                <div class="col" style="padding: 0; margin-right: 5px">
                                                    <label for="salary">Salary</label>
                                                    <input
                                                        type="text" 
                                                        id="salary"
                                                    name="salary"
                                                    placeholder="Enter salary"
                                                    required
                                                    value="${staff.salary}"
                                                    oninput="formatSalary(this)"
                                                    />
                                                    <span id="salary-error" style="color: red"></span>
                                                </div>
                                                <div class="col" style="padding: 0; margin-right: 5px">
                                                    <label for="bank">Bank Account</label>
                                                    <input
                                                        type="text"
                                                        id="bank"
                                                        name="bank"
                                                        placeholder="Enter bank account"
                                                        value="${staff.bank}"
                                                        required=""
                                                        />
                                                    <span id="bank-error" style="color: red"></span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="two-cols">
                                                <div class="col" style="padding: 0; margin-right: 5px">
                                                    <label for="company">Company</label>
                                                    <select id="company" name="company">

                                                        <c:forEach items="${sessionScope.listCompany}" var="cp">
                                                            <option value="${cp.id}" ${staff.company.id == cp.id?'selected':''}>${cp.name}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                                <div class="col" style="padding: 0; margin-right: 5px">
                                                    <label for="startDate">Start Date</label>
                                                    <input type="date" id="startDate" name="startDate" value="${staff.startDate}"/>
                                                </div>
                                                <div class="col" style="padding: 0; margin-right: 5px">
                                                    <label for="endDate">End Date</label>
                                                    <input type="date" id="endDate" name="endDate" value="${staff.endDate != null ? staff.endDate : ''}"}"/>
                                                    <span id="endDate-error" style="color: red"></span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="two-cols">
                                                <div class="col" style="padding: 0; margin-right: 5px">
                                                    <label for="status">Status</label>
                                                    <select id="status" name="status">
                                                        <option value="1" ${staff.status == '1' ? 'selected' : ''}>Active</option>
                                                        <option value="0" ${staff.status == '0' ? 'selected' : ''}>Inactive</option>
                                                    </select>
                                                </div>
                                                <div class="col" style="padding: 0; margin-right: 5px">
                                                    <label for="role">Role</label>
                                                    <select id="role" name="role">
                                                        <c:forEach items="${sessionScope.listRole}" var="role">
                                                            <option value="${role.id}" ${role.id == staff.role.id?'selected':''}>${role.name}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-button">
                                            <button type="submit">Update</button>
                                            <h5 style="color:${status=="true"?"green":"red"};text-align:center ">${requestScope.message}</h5>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <!-- end dashboard inner -->
                    </div>
                </div>
                <!-- jQuery -->
                <script src="js/jquery.min.js"></script>
                <script src="js/popper.min.js"></script>
                <script src="js/bootstrap.min.js"></script>
                <script src="js/custom.js"></script>
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
                <script>
                    $(document).ready(function () {
                        const submitButton = $('button[type="submit"]');

                        // Function to update the submit button's disabled state
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

                        $("#email").on("input", function () {
                            var email = $(this).val();
                            if (email) {
                                $.ajax({
                                    url: "checkDuplicateStaffInfor",
                                    type: "GET",
                                    data: {type: "email", value: email},
                                    success: function (response) {
                                        if (response.exists) {
                                            $("#email-error").text("Email already exists.");
                                        } else {
                                            $("#email-error").text("");
                                        }
                                        updateSubmitButtonState();
                                    }
                                });
                            } else {
                                $("#email-error").text("");
                                updateSubmitButtonState();
                            }
                        });

                        $("#phone").on("input", function () {
                            var phone = $(this).val();
                            if (phone) {
                                $.ajax({
                                    url: "checkDuplicateStaffInfor",
                                    type: "GET",
                                    data: {type: "phone", value: phone},
                                    success: function (response) {
                                        if (response.exists) {
                                            $("#phone-error").text("Phone number already exists.");
                                        } else {
                                            $("#phone-error").text("");
                                        }
                                        updateSubmitButtonState();
                                    }
                                });
                            } else {
                                $("#phone-error").text("");
                                updateSubmitButtonState();
                            }
                        });

                        $("#cccd").on("input", function () {
                            var cccd = $(this).val();
                            if (cccd) {
                                $.ajax({
                                    url: "checkDuplicateStaffInfor",
                                    type: "GET",
                                    data: {type: "cccd", value: cccd},
                                    success: function (response) {
                                        if (response.exists) {
                                            $("#cccd-error").text("CCCD already exists.");
                                        } else {
                                            $("#cccd-error").text("");
                                        }
                                        updateSubmitButtonState();
                                    }
                                });
                            } else {
                                $("#CCCD-error").text("");
                                updateSubmitButtonState();
                            }
                        });
                        $("#bank").on("input", function () {
                            var bank = $(this).val();
                            if (bank) {
                                $.ajax({
                                    url: "checkDuplicateStaffInfor",
                                    type: "GET",
                                    data: {type: "bank", value: bank},
                                    success: function (response) {
                                        if (response.exists) {
                                            $("#bank-error").text("Bank already exists.");
                                        } else {
                                            $("#bank-error").text("");
                                        }
                                        updateSubmitButtonState();
                                    }
                                });
                            } else {
                                $("#bank-error").text("");
                                updateSubmitButtonState();
                            }
                        });
                    });
                </script>
                <script>
                    $(document).ready(function () {
                        const submitButton = $('button[type="submit"]');

                        function calculateAge(dob) {
                            const birthDate = new Date(dob);
                            const today = new Date();
                            let age = today.getFullYear() - birthDate.getFullYear();
                            const monthDiff = today.getMonth() - birthDate.getMonth();
                            if (monthDiff < 0 || (monthDiff === 0 && today.getDate() < birthDate.getDate())) {
                                age--;
                            }
                            return age;
                        }

                        function validateForm() {
                            let isValid = true;

                            let phone = $("#phone").val();
                            let cccd = $("#cccd").val();
                            let dob = $("#dob").val();
                            let salary = $("#salary").val();
                            let startDate = $("#startDate").val();
                            let endDate = $("#endDate").val();

                            // Validate Phone (11 digits)
                            if (!/^\d{10}$/.test(phone)) {
                                $("#phone-error").text("Phone number must be exactly 10 digits.");
                                isValid = false;
                            } else {
                                $("#phone-error").text("");
                            }

                            // Validate CCCD (12 digits)
                            if (!/^\d{12}$/.test(cccd)) {
                                $("#cccd-error").text("CCCD must be exactly 12 digits.");
                                isValid = false;
                            } else {
                                $("#cccd-error").text("");
                            }

                            // Validate Age (Must be 18 or older)
                            if (dob) {
                                let age = calculateAge(dob);
                                if (age < 18) {
                                    $("#dob-error").text("Staff must be at least 18 years old.");
                                    isValid = false;
                                } else {
                                    $("#dob-error").text("");
                                }
                            }

                            // Validate Salary (Must be > 0)
                            if (salary && salary <= 0) {
                                $("#salary-error").text("Salary must be greater than 0.");
                                isValid = false;
                            } else {
                                $("#salary-error").text("");
                            }

                            // Validate End Date (Must be after Start Date)
                            if (startDate && endDate) {
                                if (new Date(endDate) <= new Date(startDate)) {
                                    $("#endDate-error").text("End date must be after start date.");
                                    isValid = false;
                                } else {
                                    $("#endDate-error").text("");
                                }
                            }

                            return isValid;
                        }

                        $("form").on("submit", function (event) {
                            if (!validateForm()) {
                                event.preventDefault();
                            }
                        });
                    });
                </script>
                <script>
                    function formatSalary(input) {
                        // Remove any non-numeric characters (except dots and digits)
                        let value = input.value.replace(/\D/g, "");

                        // Convert to a number and format with dot separators
                        value = Number(value).toLocaleString("en").replace(/,/g, ".");

                        // Update input field with formatted value
                        input.value = value;
                    }
                </script>


        </body>
    </html>
