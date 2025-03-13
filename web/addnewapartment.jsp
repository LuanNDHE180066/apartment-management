<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                width: 100%;
                max-width: 900px;
                margin: 50px auto;
                background: #ffffff;
                padding: 40px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                border-radius: 8px;
                border: 1px solid #e3e3e3;
            }
            .form-container h1 {
                text-align: center;
                margin-bottom: 30px;
                font-size: 28px;
                color: #333;
            }
            .form-group {
                margin-bottom: 20px;
                width: 100%;
            }
            .form-group label {
                display: block;
                font-weight: 600;
                margin-bottom: 8px;
                color: #666;
            }
            .form-group input,
            .form-group select,
            .form-group textarea {
                width: 100%;
                padding: 15px;
                border: 1px solid #ccc;
                border-radius: 6px;
                font-size: 16px;
                color: #333;
            }
            .form-group input:focus,
            .form-group select:focus,
            .form-group textarea:focus {
                border-color: #4a90e2;
                outline: none;
                box-shadow: 0 0 4px rgba(74, 144, 226, 0.5);
            }
            .two-cols {
                display: flex;
                justify-content: space-between;
                gap: 20px;
            }
            .two-cols .col {
                width: 48%;
            }
            .form-button {
                text-align: center;
                margin-top: 30px;
            }
            .form-button button {
                padding: 15px 30px;
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
                                <h1>Add New Apartment</h1>
                                <form action="add-new-apartment" method="post" id="apartmentForm">
                                    <div class="form-group">
                                        <div class="two-cols">
                                            <div class="col">
                                                <label for="aptNum">Apartment Number</label>
                                                <input type="text" id="aptNum" name="aptNum" placeholder="Enter Apartment Number (e.g., A123)" required />
                                                <span id="aptNum-error" class="error-message"></span>
                                            </div>
                                            <div class="col">
                                                <label for="aptType">Apartment Type</label>
                                                <select id="aptType" name="aptType" required>
                                                    <c:forEach items="${requestScope.listRoomType}" var="list">
                                                        <option value="${list.id}">${list.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="two-cols">
                                            <div class="col">
                                                <label for="aptFloor">Floor</label>
                                                <input type="number" id="aptFloor" name="aptFloor" placeholder="Enter Floor" required />
                                                <span id="aptFloor-error" class="error-message"></span>
                                            </div>
                                            <div class="col">
                                                <label for="aptMember">Member Quantity</label>
                                                <input type="number" id="aptMember" name="aptMember" placeholder="Enter Member Quantity" required />
                                                <span id="aptMember-error" class="error-message"></span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="aptInfor">Information</label>
                                        <textarea id="aptInfor" name="aptInfor" rows="5" placeholder="Enter Detailed Information (max 30 characters)" required></textarea>
                                        <span id="aptInfor-error" class="error-message"></span>
                                    </div>
                                    <div class="form-button">
                                        <button type="submit" id="submitButton" disabled>Save Apartment</button>
                                        <div>
                                            <a style="font-size: 20px; color: #4a90e2; text-decoration: underline" href="view-apartment-admin">Back</a>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <!-- end dashboard inner -->
                </div>
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
                function updateSubmitButtonState() {
                    if ($(".error-message").text().trim() !== "") {
                        $("#submitButton").prop("disabled", true);
                    } else {
                        $("#submitButton").prop("disabled", false);
                    }
                }

                $("#aptNum").on("input", function () {
                    let aptNum = $(this).val();
                    let regex = /^[A-Z][0-9]{3}$/;
                    if (!regex.test(aptNum)) {
                        $("#aptNum-error").text("Must start with an uppercase letter followed by 3 digits.");
                    } else {
                        $.ajax({
                            url: "check-duplicate-apt-infor",
                            type: "GET",
                            data: {type: "aptNum", value: aptNum},
                            success: function (response) {
                                if (response.exists) {
                                    $("#aptNum-error").text("Apartment Number already exists.");
                                } else {
                                    $("#aptNum-error").text("");
                                }
                                updateSubmitButtonState();
                            }
                        });
                    }
                    updateSubmitButtonState();
                });

                $("#aptFloor").on("input", function () {
                    let floor = $(this).val();
                    if (floor < 1) {
                        $("#aptFloor-error").text("Floor must be 1 or higher.");
                    } else {
                        $("#aptFloor-error").text("");
                    }
                    updateSubmitButtonState();
                });

                $("#aptMember").on("input", function () {
                    let member = $(this).val();
                    if (member < 1) {
                        $("#aptMember-error").text("Member quantity must be 1 or higher.");
                    } else {
                        $("#aptMember-error").text("");
                    }
                    updateSubmitButtonState();
                });

                $("#aptInfor").on("input", function () {
                    let info = $(this).val();
                    if (info.length > 30) {
                        $("#aptInfor-error").text("Information cannot exceed 30 characters.");
                    } else {
                        $("#aptInfor-error").text("");
                    }
                    updateSubmitButtonState();
                });
            });
        </script>
    </body>
</html>