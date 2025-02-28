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
        <title>Apartment management</title>
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
            .form-group select,
            .form-group textarea {
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
            .form-group select:focus,
            .form-group textarea:focus {
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
                                <h1>Update Information</h1>
                                <form action="updateInfo" method="post">
                                    <div class="form-group">
                                        <input type="text" id="staffID" name="staffID" value="${expenditure.id}" hidden=""/>
                                        <div class="two-cols">
                                            <div class="col" style="padding: 0; margin-right: 5px">
                                                <label for="title">Title</label>
                                                <input type="text" id="title" name="title" placeholder="Enter title" value="${expenditure.title}" required=""/>
                                            </div>
                                            <div class="col" style="padding: 0; margin-right: 5px">
                                                <label for="totalFees">Total Fees</label>
                                                <input type="number" id="totalFees" name="totalFees" placeholder="Enter total fees" value="${expenditure.totalPrice}" required=""/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="two-cols">
                                            <div class="col" style="padding: 0; margin-right: 5px">
                                                <label for="approveDate">Approval Date</label>
                                                <input type="date" id="approveDate" name="approveDate" value="${expenditure.approveddate}" required=""/>
                                            </div>
                                            <div class="col" style="padding: 0; margin-right: 5px">
                                                <label for="paymentDate">Payment Date</label>
                                                <input type="date" id="paymentDate" name="paymentDate" value="${expenditure.paymentdate}" required=""/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="two-cols">
                                            <div class="col" style="padding: 0; margin-right: 5px">
                                                <label for="category">Category</label>
                                                <input type="text" id="category" name="category" placeholder="Enter category" value="${staff.category}" required=""/>
                                            </div>
                                            <div class="col" style="padding: 0; margin-right: 5px">
                                                <label for="company">Company</label>
                                                <select id="company" name="company">
                                                    <c:forEach items="${sessionScope.listCompany}" var="cp">
                                                        <option value="${cp.id}" ${staff.company.id == cp.id?'selected':''}>${cp.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="two-cols">
                                            <div class="col" style="padding: 0; margin-right: 5px">
                                                <label for="invoiceCreator">Invoice Creator</label>
                                                <input type="text" id="invoiceCreator" name="invoiceCreator" placeholder="Enter invoice creator" value="${staff.invoiceCreator}" required=""/>
                                            </div>
                                            <div class="col" style="padding: 0; margin-right: 5px">
                                                <label for="chiefAccountant">Chief Accountant</label>
                                                <select id="chiefAccountant" name="chiefAccountant">
                                                    <c:forEach items="${sessionScope.listAccountants}" var="accountant">
                                                        <option value="${accountant.id}" ${accountant.id == staff.chiefAccountant.id?'selected':''}>${accountant.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="two-cols">
                                            <div class="col" style="padding: 0; margin-right: 5px">
                                                <label for="admin">Admin</label>
                                                <select id="admin" name="admin">
                                                    <c:forEach items="${sessionScope.listAdmins}" var="admin">
                                                        <option value="${admin.id}" ${admin.id == staff.admin.id?'selected':''}>${admin.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="note">Note</label>
                                        <textarea id="note" name="note" placeholder="Enter any additional notes" rows="4">${staff.note}</textarea>
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
            <script>
                // Add your JavaScript validation and functions here
            </script>
        </body>
</html>