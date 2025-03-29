<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="dao.ApartmentDAO" %>
<html lang="en">
    <head>
        <!-- basic -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- mobile metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- site metas -->
        <title>Quản lý căn hộ</title>        
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
        <link rel="stylesheet" href="<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg==" crossorigin="anonymous" referrerpolicy="no-referrer" />"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <style>
                body {
            font-family: 'Roboto', sans-serif !important;
        }
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
        .display-none {
            display: none;
        }
          body {
            font-family: 'Roboto', sans-serif !important;
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
            <%@ include file="sidebar.jsp" %>
            <div id="content">
                <%@ include file="topbar.jsp" %>
                <div class="midde_cont">
                    <div class="container-fluid">
                        <div class="form-container">
                            <h1 style="font-weight: bold">Đăng ký đổi chủ/ Thêm người ở mới</h1>
                            <form action="register-new-living-or-owner-resident" method="post">
                                <!-- Apartment Information -->
                                <div class="form-group">
                                    <div class="one-col">
                                        <label for="apartment">Số phòng</label>
                                        <select id="apartment" name="apartment" onchange="updateResidentNames()" required>
                                            <option value="">Chọn phòng</option>
                                            <c:forEach items="${aptDAO.getApartmentDetailByOwnerid(sessionScope.account.pId)}" var="apt">
                                                <option data-living="${apt.livingPerson.name}" data-owner="${apt.owner.name}" 
                                                        value="${apt.id}">${apt.id}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="one-col display-none">
                                        <label for="living-resident">Living Resident</label>
                                        <input type="text" id="living-resident" name="livingResident" placeholder="Living Resident Name" readonly />
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="one-col">
                                        <label for="owner-resident">Chủ hộ</label>
                                        <input type="text" id="owner-resident" name="ownerResident" placeholder="Owner Resident Name" readonly />
                                    </div>
                                </div>
                                
                                <div class="form-group">
                                    <label for="resident-type">Đổi chủ/Thêm người ở</label>
                                    <select id="resident-type" name="residentType" onchange="toggleUsernameFields()">
                                        <option value="living">Thêm người ở</option>
                                        <option value="owner">Đổi chủ</option>
                                    </select>
                                </div>

                                <div class="form-group">
                                    <div class="gender-options">
                                        <label>Cư dân mới đã có tài khoản/ID?</label>
                                        
                                        <label for="owner-exists">
                                            <input type="checkbox" id="owner-exists" name="residentExists" onclick="toggleOwnerFields()" />
                                            Có
                                        </label>
                                        <div style="font-style: italic"> (Đổi chủ yêu cầu có tài khoản/ Thêm mới người ở cần có ID)</div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="new-owner-id">Mã số cư dân mới</label>
                                    <input type="text" id="new-owner-id" name="newResidentId" placeholder="Nhập mã số" disabled />
                                </div>

                                <hr /> <!-- Horizontal line separating sections -->

                                <!-- Resident Information Fields -->
                                <h2 style="margin-top: 20px;">Thông tin cư dân mới</h2>
                                <div class="form-group">
                                    <div class="three-cols">
                                        <div class="col">
                                            <label for="name">Tên</label>
                                            <input type="text" id="name" name="name" placeholder="Enter full name" required />
                                        </div>
                                        <div class="col">
                                            <label for="dob">Ngày sinh</label>
                                            <input type="date" id="dob" name="dob" required />
                                        </div>
                                        <div class="col">
                                            <label>Giới tính</label>
                                            <div class="gender-options">
                                                <label for="male">
                                                    <input type="radio" id="male" name="gender" value="M" required />
                                                    Nam
                                                </label>
                                                <label for="female">
                                                    <input type="radio" id="female" name="gender" value="F" required />
                                                    Nữ
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="one-col">
                                        <label for="address">Địa chỉ</label>
                                        <input type="text" id="address" name="address" placeholder="Enter address" required />
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="two-cols">
                                        <div class="col">
                                            <label for="phone">Số điện thoại</label>
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
                                            <label for="id">Căn cước công dân</label>
                                            <input type="number" id="id" name="id" placeholder="Enter ID" required />
                                            <span id="id-error" class="error-message"></span>
                                        </div>
                                        <div class="col">
                                            <label for="username">Tài khoản</label>
                                            <input type="text" id="username" name="username" placeholder="Enter username" required />
                                            <span id="username-error" class="error-message"></span>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-button">
                                    <button type="submit">Gửi</button>
                                    <h5 style="color:${status == "true" ? "green" : "red"}; text-align:center;">${requestScope.message}</h5>
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

            function toggleUsernameFields() {
                const selectedValue = document.querySelector('#resident-type').value; // Correctly get the selected value
                const usernameField = document.getElementById('username'); // Reference to the username input

                if (selectedValue === 'living') {
                    usernameField.disabled = true; // Disable username field for living resident
                } else {
                    usernameField.disabled = false; // Enable username field for owner
                }
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
                    newOwnerId.disabled = true; // Disable new owner ID input
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

            // Call toggleUsernameFields on page load to initialize fields correctly
            window.onload = function() {
                toggleUsernameFields();
            };
        </script>
    </div>
</body>
</html>