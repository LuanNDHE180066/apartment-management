<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Edit Profile</title>
        <link rel="icon" href="images/fevicon.png" type="image/png" />
        <link rel="stylesheet" href="css/bootstrap.min.css" />
        <link rel="stylesheet" href="style.css" />
        <link rel="stylesheet" href="css/custom.css" />
        <style>
            .form-container {
                background: #ffffff;
                padding: 40px;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                max-width: 400px;
                margin: auto;
                text-align: center;
            }

            .form-container h1 {
                text-align: center;
                margin-bottom: 30px;
                color: #333;
            }

            .form-group {
                margin-bottom: 15px;
                text-align: left;
            }

            .form-group label {
                display: block;
                margin-bottom: 5px;
                font-weight: bold;
                color: #555;
            }

            .form-group input {
                width: 100%;
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 6px;
                font-size: 14px;
                box-sizing: border-box;
                transition: border-color 0.3s;
            }

            .form-group input:focus {
                border-color: #4a90e2;
                outline: none;
            }

            .form-button {
                text-align: center;
                margin-top: 20px;
            }

            .form-button button {
                padding: 10px 20px;
                font-size: 16px;
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

            .error-msg {
                color: red;
                font-weight: bold;
            }

            .password-error {
                color: red;
                font-size: 14px;
                margin-top: 5px;
                display: none;
            }
        </style>
    </head>
    <body>
        <div class="full_container">
            <div class="inner_container">
                <%@include file="sidebar.jsp" %>
                <div id="content">
                    <%@include file="topbar.jsp" %>
                    <div class="container mt-5">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-container">
                                    <h1>Change Password</h1>
                                    <form action="update-password-resident" method="post">
                                        <c:if test="${sessionScope.account.getActive() != 2}">
                                            <div class="form-group">
                                                <label for="oldPassword">Mật khẩu cũ:</label>
                                                <input type="password" id="oldPassword" name="oldPassword" required />
                                            </div>
                                        </c:if>
                                        <div class="form-group">
                                            <label for="newPassword">Mật khẩu mới:</label>
                                            <input type="password" id="newPassword" name="newPassword" required oninput="checkPasswordStrength()" />
                                            <p id="passwordError" class="password-error">
                                                Mật khẩu phải có ít nhất 6 ký tự, bao gồm ít nhất 1 chữ hoa, 1 chữ thường, 1 số và 1 ký tự đặc biệt.
                                            </p>
                                        </div>
                                        <div class="form-group">
                                            <label for="cfnewPassword">Xác nhận mật khẩu:</label>
                                            <input type="password" id="cfnewPassword" name="cfnewPassword" required oninput="checkPasswordMatch()" />
                                            <p id="confirmError" class="password-error">Mật khẩu không khớp.</p>
                                        </div>
                                        <div class="form-button">
                                            <button type="submit" id="saveButton" disabled>Lưu</button>
                                            <c:if test="${not empty sessionScope.errorMessage}">
                                                <h5 class="error-msg">${sessionScope.errorMessage}</h5>
                                                <% session.removeAttribute("errorMessage"); %>
                                            </c:if>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="js/jquery.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/custom.js"></script>

        <script>
            function checkPasswordStrength() {
                let password = document.getElementById("newPassword").value;
                let passwordError = document.getElementById("passwordError");
                let passwordRegex = /^(?=.*\d)(?=.*[A-Z])(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{6,}$/;
                passwordError.style.display = password && !passwordRegex.test(password) ? "block" : "none";
                updateSaveButtonState();
            }

            function checkPasswordMatch() {
                let password = document.getElementById("newPassword").value;
                let confirmPassword = document.getElementById("cfnewPassword").value;
                let confirmError = document.getElementById("confirmError");
                confirmError.style.display = confirmPassword && password !== confirmPassword ? "block" : "none";
                updateSaveButtonState();
            }

            function updateSaveButtonState() {
                let password = document.getElementById("newPassword").value;
                let confirmPassword = document.getElementById("cfnewPassword").value;
                let saveButton = document.getElementById("saveButton");
                let passwordRegex = /^(?=.*\d)(?=.*[A-Z])(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9]).{6,}$/;
                saveButton.disabled = !(passwordRegex.test(password) && password === confirmPassword);
            }
        </script>
    </body>
</html>