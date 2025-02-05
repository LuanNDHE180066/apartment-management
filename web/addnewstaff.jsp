<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <!-- basic -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- mobile metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="viewport" content="initial-scale=1, maximum-scale=1">
        <!-- site metas -->
        <title>Pluto - Responsive Bootstrap Admin Panel Templates</title>
        <meta name="keywords" content="">
        <meta name="description" content="">
        <meta name="author" content="">
        <!-- site icon -->
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
        <style> body {
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
                margin-bottom: 20px;
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
            }</style>


    </head>
    <body class="dashboard dashboard_1">
        <div class="full_container">
            <div class="inner_container">
                <%@include file="sidebar.jsp" %>
                <!-- end sidebar -->
                <!-- right content -->
                <div id="content">
                    <!-- topbar -->
                    <%@include file="topbar.jsp" %>
                    <!-- end topbar -->
                    <!-- Form to Add New Employee -->
                    <div class="container mt-5">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-container">
                                    <h1>Add Staff Information</h1>
                                    <form action="add-new-staff" method="post">
                                        <div class="form-group">
                                            <input type="text" id="staffID" name="staffID" value="" hidden=""/>
                                                <div class="col" style="padding: 0; margin-right: 5px">
                                                    <label for="name">Name</label>
                                                    <input
                                                        type="text"
                                                        id="name"
                                                        name="name"
                                                        placeholder="Enter full name"
                                                        value=""
                                                        />
                                                </div>
                                        </div>
                                        <div class="form-group">
                                            <input type="text" id="staffID" name="staffID" value="" hidden=""/>
                                            <div class="two-cols">
                                                <div class="col" style="padding: 0; margin-right: 5px">
                                                    <label for="gender">Gender</label>
                                                    <select id="gender" name="gender">
                                                       <option value="M">Male</option>
                                                       <option value="F">Female</option>
                                                    </select>
                                                </div>
                                                <div class="col" style="padding: 0; margin-right: 5px">
                                                    <label for="dob">Date of Birth</label>
                                                    <input type="date" id="dob" name="dob" value=""/>
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
                                                    value=""
                                                    />
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="two-cols">
                                                <div class="col" style="padding: 0; margin-right: 5px">
                                                    <label for="phone">Phone</label>
                                                    <input
                                                        type="tel"
                                                        id="phone"
                                                        name="phone"
                                                        placeholder="Enter phone number"
                                                        value=""
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
                                                        value=""
                                                        />
                                                    <span id="email-error" style="color: red"></span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="three-cols">
                                                <div class="col" style="padding: 0; margin-right: 5px ">
                                                    <label for="cccd">CCCD</label>
                                                    <input
                                                        type="text"
                                                        id="cccd"
                                                        name="cccd"
                                                        placeholder="Enter CCCD"
                                                        value=""
                                                        />
                                                    <span id="CCCD-error" style="color: red"></span>
                                                </div>
                                                <div class="col" style="padding: 0; margin-right: 5px">
                                                    <label for="education">Education</label>
                                                    <input
                                                        type="text"
                                                        id="education"
                                                        name="education"
                                                        placeholder="Enter education"
                                                        value=""
                                                        />
                                                </div>
                                                <div class="col" style="padding: 0; margin-right: 5px">
                                                    <label for="salary">Salary</label>
                                                    <input
                                                        type="text"
                                                        id="salary"
                                                        name="salary"
                                                        placeholder="Enter salary"
                                                        value=""
                                                        />
                                                </div>
                                                <div class="col" style="padding: 0; margin-right: 5px">
                                                    <label for="bank">Bank Account</label>
                                                    <input
                                                        type="text"
                                                        id="bank"
                                                        name="bank"
                                                        placeholder="Enter bank account"
                                                        value=""
                                                        />
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="two-cols">
                                                <div class="col" style="padding: 0; margin-right: 5px">
                                                    <label for="username">Username</label>
                                                    <input
                                                        type="text"
                                                        id="username"
                                                        name="username"
                                                        placeholder="Enter username"
                                                        value=""
                                                        />
                                                </div>
                                                <div class="col" style="padding: 0; margin-right: 5px">
                                                    <label for="password">Password</label>
                                                    <input
                                                        type="password"
                                                        id="password"
                                                        name="password"
                                                        placeholder="Enter password"
                                                        value=""
                                                        />
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="two-cols">
                                                <div class="col" style="padding: 0; margin-right: 5px">
                                                    <label for="company">Company</label>
                                                    <select id="company" name="company">
                                                        <c:forEach items="${sessionScope.listCompany}" var="cp">
                                                            <option value="${cp.id}">${cp.name}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                                <div class="col" style="padding: 0; margin-right: 5px">
                                                    <label for="startDate">Start Date</label>
                                                    <input type="date" id="startDate" name="startDate" value=""/>
                                                </div>
                                                <div class="col" style="padding: 0; margin-right: 5px">
                                                    <label for="role">Role</label>
                                                    <select id="role" name="role">
                                                        <c:forEach items="${sessionScope.listRole}" var="role">
                                                            <option value="${role.id}">${role.name}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-button">
                                            <button type="submit">Add Staff</button>
                                            <h5 style="color:${status=="true"?"green":"red"};text-align:center ">${requestScope.message}</h5>
                                            <h5 style="color:red;text-align:center">${requestScope.error}</h5>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                        <!-- end form -->
                    </div>
                </div>
            </div>
            <!-- jQuery -->
            <script src="js/jquery.min.js"></script>
            <script src="js/popper.min.js"></script>
            <script src="js/bootstrap.min.js"></script>
            <!-- custom js -->
            <script src="js/custom.js"></script>
    </body>
</html>