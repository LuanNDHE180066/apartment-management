@ -0,0 +1,337 @@
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

                input, select {
                    width: 100%;
                    padding: 12px;
                    border: 1px solid #ccc;
                    border-radius: 6px;
                    font-size: 16px;
                }

                input:focus, select:focus {
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
            </style>
        </head>
        <body class="inner_page tables_page">
            <jsp:useBean id="aptDAO" class="dao.ApartmentDetailDAO" scope="page"/>
            <jsp:useBean id="apartmentDAO" class="dao.ApartmentDAO" scope="page"/>
            <jsp:useBean id="residentDAO" class="dao.ResidentDAO" scope="page"/>
            <jsp:useBean id="livingApartmentDAO" class="dao.LivingApartmentDAO" scope="page"/>
            <div class="full_container">
                <div class="inner_container">
                    <%@include file="sidebar.jsp" %>
                    <div id="content">
                        <%@include file="topbar.jsp" %>
                        <div class="midde_cont">
                            <div class="container-fluid">
                                <div class="form-container">
                                    <h1 style="font-weight: bold">Register New Living Resident Or Owner</h1>
                                    <form action="register-new-living-or-owner-resident" method="post">
                                        <!-- Apartment Information -->
                                        <div class="form-group">
                                            <div class="one-col">
                                                <label for="apartment">Apartment Number</label>
                                                <select id="apartment" name="apartment" onchange="updateResidentNames()" required>
                                                    <option value="">Select Apartment</option>
                                                    <c:forEach  items="${aptDAO.getApartmentDetailByOwnerid(sessionScope.account.pId)}" var="apt">
                                                        <option data-living="${apt.livingPerson.name}"  data-owner="${apt.owner.name}" 
                                                                value="${apt.id}">${apt.id}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="one-col">
                                                <label for="living-resident">Living Resident</label>
                                                <input type="text" id="living-resident" name="livingResident" placeholder="Living Resident Name" readonly />
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="one-col">
                                                <label for="owner-resident">Owner Resident</label>
                                                <input type="text" id="owner-resident" name="ownerResident" placeholder="Owner Resident Name" readonly />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="resident-type">Select Resident to Change:</label>
                                            <select id="resident-type" name="residentType">
                                                <option value="living">Living Resident</option>
                                                <option value="owner">Owner Resident</option>
                                            </select>
                                        </div>

                                        <div class="form-group">

                                            <div class="gender-options">
                                                <label>Does the new Resident Exist?</label>
                                                <label for="owner-exists">
                                                    <input type="checkbox" id="owner-exists" name="residentExists" onclick="toggleOwnerFields()" />
                                                    Yes
                                                </label>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label for="new-owner-id">New Owner ID</label>
                                            <input type="text" id="new-owner-id" name="newResidentId" placeholder="Enter New Owner ID" disabled />
                                        </div>

                                        <hr /> <!-- Horizontal line separating sections -->

                                        <!-- Resident Information Fields -->
                                        <h2 style="margin-top: 20px;">New Resident Information</h2>
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
                                                            <input type="radio" id="male" name="gender"  value="M" required />
                                                            Male
                                                        </label>
                                                        <label for="female">
                                                            <input type="radio" id="female" name="gender" value="F" required />
                                                            Female
                                                        </label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="one-col">
                                                <label for="address">Address</label>
                                                <input type="text" id="address" name="address" placeholder="Enter address" required />
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="two-cols">
                                                <div class="col">
                                                    <label for="phone">Phone</label>
                                                    <input type="tel" id="phone" name="phone" placeholder="Enter phone number" required />
                                                    <span id="phone-error" class="error-message"></span>
                                                </div>
                                                <div class="col">
                                                    <label for="email">Email</label>
                                                    <input type="email" id="email" name="email" placeholder="Enter email" required />
                                                    <span id="email-error" class="error-message"></span>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="three-cols">
                                                <div class="col">
                                                    <label for="id">ID</label>
                                                    <input type="number" id="id" name="id" placeholder="Enter ID" required />
                                                    <span id="id-error" class="error-message"></span>
                                                </div>
                                                <div class="col">
                                                    <label for="username">Username</label>
                                                    <input type="text" id="username" name="username" placeholder="Enter username" required />
                                                    <span id="username-error" class="error-message"></span>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-button">
                                            <button type="submit">Submit</button>
                                             <h5 style="color:${status=="true"?"green":"red"};text-align:center ">${requestScope.message}</h5>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <script src="js/jquery.min.js"></script>
                <script>
                                                        function updateResidentNames() {
                                                            const apartmentSelect = document.getElementById('apartment'); // Get the apartment select element
                                                            const selectedApartmentId = apartmentSelect.value; // Get the selected apartment ID
                                                            const options = apartmentSelect.getElementsByTagName('option'); // Get all option elements

                                                            let livingResident = "";
                                                            let ownerResident = "";

                                                            // Loop through options to find the selected apartment
                                                            for (let i = 0; i < options.length; i++) {
                                                                if (options[i].value === selectedApartmentId) {
                                                                    livingResident = options[i].getAttribute('data-living') || ""; // Get living resident name
                                                                    ownerResident = options[i].getAttribute('data-owner') || ""; // Get owner resident name
                                                                    break; // Stop the loop once found
                                                                }
                                                            }

                                                            // Update input fields with the resident names
                                                            document.getElementById('living-resident').value = livingResident; // Set living resident name
                                                            document.getElementById('owner-resident').value = ownerResident; // Set owner resident name
                                                        }


                                                        function toggleOwnerFields() {
                                                            const ownerExists = document.getElementById('owner-exists').checked;
                                                            const newOwnerId = document.getElementById('new-owner-id');

                                                            if (ownerExists) {
                                                                newOwnerId.disabled = false; // Enable new owner ID input
                                                                // Disable input fields for new resident
                                                                document.getElementById('name').disabled = true;
                                                                document.getElementById('male').disabled = true;
                                                                document.getElementById('female').disabled = true;
                                                                document.getElementById('dob').disabled = true;
                                                                document.getElementById('address').disabled = true;
                                                                document.getElementById('phone').disabled = true;
                                                                document.getElementById('email').disabled = true;
                                                                document.getElementById('id').disabled = true;
                                                                document.getElementById('username').disabled = true;
                                                            } else {
                                                                newOwnerId.disabled = true; // Disable new owner ID input owner-exists
                                                                // Enable input fields for new resident
                                                                document.getElementById('male').disabled = false;
                                                                document.getElementById('female').disabled = false;
                                                                document.getElementById('name').disabled = false;
                                                                document.getElementById('dob').disabled = false;
                                                                document.getElementById('address').disabled = false;
                                                                document.getElementById('phone').disabled = false;
                                                                document.getElementById('email').disabled = false;
                                                                document.getElementById('id').disabled = false;
                                                                document.getElementById('username').disabled = false;
                                                            }
                                                        }
                </script>
            </div>
        </body>
    </html>