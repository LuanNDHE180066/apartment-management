<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            .one-col {
                display: flex;
                flex-direction: column;
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
                                <h1 style="font-weight: bold">Register New Living Resident Or Owner</h1>

                                <form action="change-represent-resident" method="get" id="apartmentForm">
                                    <div class="form-group">
                                        <div class="one-col">
                                            <label for="apartment">Apartment Number</label>
                                            <select id="apartment" name="apartment" onchange="document.getElementById('apartmentForm').submit()" required>
                                                <option value="">Select Apartment</option>
                                                <c:forEach items="${aptDAO.getApartmentDetailByOwnerid(sessionScope.account.pId)}" var="apt">
                                                    <c:set var="representedResident" value="${livingApartmentDAO.getRepresentedResidentByAid(apt.id)}" />
                                                    <option data-representative-name="${representedResident.rid.name}" data-representative-id="${representedResident.rid.pId}" data-owner="${apt.owner.name}" 
                                                            value="${apt.id}" ${requestScope.aid == apt.id?'selected':''}>${apt.id}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </form>

                                <form action="change-represent-resident" method="post">
                                    <input hidden="" name="aid" type="text" value="${requestScope.aid}">
                                    <div class="form-group">
                                        <div class="one-col">
                                            <label for="representative-resident">Representative Resident</label>
                                            <input type="text" id="representative-resident" value="${requestScope.representResident.name}" name="representativeResident" placeholder="Representative Resident Name" readonly />
                                            <input type="hidden" id="representative-id" value="${requestScope.representResident.pId}" name="oldRepresent" />
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="one-col">
                                            <label for="owner-resident">Owner Resident</label>
                                            <input type="text" value="${requestScope.owner.rid}" id="owner-resident" name="ownerResident" placeholder="Owner Resident Name" readonly />
                                            <input hidden="" type="text" value="${requestScope.owner.rid.pId}" id="owner-resident" name="owner-id" placeholder="Owner Resident Name" readonly />
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="one-col">
                                            <label for="new-represent-resident">Select New Representative Resident</label>
                                            <select id="new-represent-resident" name="newRepresent" required>
                                                <option value="">Select Representative</option>
                                                <c:forEach var="nonRepresentResident" items="${requestScope.listNonRepresent}">
                                                    <option value="${nonRepresentResident.rid.pId}">${nonRepresentResident.rid.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="gender-options">
                                            <label>Does the account exist?</label>
                                            <label for="account-exists">
                                                <input type="checkbox" id="account-exists" name="accountExist" onclick="disableUsername()" />
                                                Yes
                                            </label>
                                        </div>
                                    </div>  

                                    <div class="form-group">
                                        <div class="one-col">
                                            <label for="new-username">New username</label>
                                            <input type="text" id="new-username" name="username" />
                                        </div>
                                    </div>

                                    <div class="form-button">
                                        <button type="submit">Submit</button>
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
                    const apartmentSelect = document.getElementById('apartment');
                    const selectedApartmentId = apartmentSelect.value;
                    const options = apartmentSelect.getElementsByTagName('option');

                    let representativeResidentName = "";
                    let representativeResidentId = "";
                    let ownerResident = "";

                    for (let i = 0; i < options.length; i++) {
                        if (options[i].value === selectedApartmentId) {
                            representativeResidentName = options[i].getAttribute('data-representative-name') || "";
                            representativeResidentId = options[i].getAttribute('data-representative-id') || "";
                            ownerResident = options[i].getAttribute('data-owner') || "";
                            break;
                        }
                    }

                    document.getElementById('representative-resident').value = representativeResidentName;
                    document.getElementById('representative-id').value = representativeResidentId;
                    document.getElementById('owner-resident').value = ownerResident;
                }

                function disableUsername() {
                    const checkBox = document.getElementById('account-exists').checked;
                    document.getElementById('new-username').disabled = checkBox; // S?a l?i cú pháp ? ?ây
                }

                window.onload = function () {
                    updateResidentNames();
                };
            </script>
        </div>
    </body>
</html>