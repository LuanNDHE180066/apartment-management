<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            .profile-image {
                text-align: center;
                margin-bottom: 20px;
            }
            .profile-image img {
                width: 150px;
                height: 150px;
                object-fit: cover;
                border-radius: 50%;
                border: 2px solid #4a90e2;
            }
            .upload-area {
                display: flex;
                flex-direction: column;
                align-items: center;
                justify-content: center;
                cursor: pointer;
                width: 100%;
                max-width: 300px;
                margin: auto;
            }
            .upload-box {
                width: 100%;
                height: 150px;
                display: flex;
                align-items: center;
                justify-content: center;
                background: #f8f8f8;
                border-radius: 10px;
                border: 1px dashed #4a90e2;
                overflow: hidden;
                position: relative;
            }
            .upload-box img {
                width: 100%;
                height: 100%;
                object-fit: cover;
                position: absolute;
                top: 0;
                left: 0;
                display: none; /* Initially hidden */
            }
            .upload-box .upload-icon {
                font-size: 32px;
                font-weight: bold;
                color: #4a90e2;
                position: relative;
                z-index: 2;
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
            .form-group input {
                width: 100%;
                padding: 12px;
                border: 1px solid #ccc;
                border-radius: 6px;
                font-size: 16px;
                line-height: 1.5;
                box-sizing: border-box;
                transition: border-color 0.3s;
            }
            .form-group input:focus {
                border-color: #4a90e2;
                outline: none;
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
                                    <h1>Edit Profile</h1>
                                    <form action="editprofileSTServlet" method="post" enctype="multipart/form-data">
                                        <!-- Profile Image Display -->
                                        <div class="profile-image">
                                            <c:if test="${not empty sessionScope.person.image}">
                                                <img id="previewImage" src="${sessionScope.person.image}" alt="Profile Image" />
                                            </c:if>
                                            <c:if test="${empty sessionScope.person.image}">
                                                <img id="previewImage" src="images/default-avatar.png" alt="Default Profile Image" />
                                            </c:if>
                                        </div>

                                        <!-- Upload Image -->
                                        <div class="form-group">
                                            <label for="profileImage" class="form-label">Upload Image</label>
                                            <div class="upload-area" id="uploadArea">
                                                <input type="file" id="profileImage" name="profileImage" accept="image/*" hidden />
                                                <div class="upload-box" onclick="document.getElementById('profileImage').click();">
                                                    <span class="upload-icon">+</span>
                                                    <img id="uploadPreviewImage" src="" name="img" alt="Upload Preview" />
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="two-cols">
                                                <div class="col" style="padding: 0; margin-left: 5px">
                                                    <label for="email">Email:</label>
                                                    <input value="${sessionScope.person.email}" type="email" id="email" name="editProfileEmail" required />
                                                </div>
                                                <div class="col" style="padding: 0; margin-right: 5px">
                                                    <label for="phone">Phone:</label>
                                                    <input value="${sessionScope.person.phone}" type="text" id="phone" name="editProfilePhone" required pattern="[0-9]+" title="Phone number should contain only digits." />
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="Bank">Bank:</label>
                                            <input value="${sessionScope.person.bank}" type="text" id="bank" name="editProfileBank" required />
                                        </div>        
                                        <div class="form-group">
                                            <label for="address">Address:</label>
                                            <input value="${sessionScope.person.address}" type="text" id="address" name="editProfileAddress" required />
                                        </div>
                                        <div class="form-button">
                                            <button type="submit">Save</button>
                                            <h5 style="color: ${requestScope.status == 'false'?'red': 'green'}">${requestScope.msg}</h5>
                                            <span  style="text-decoration: underline; display: inline-block"><a><a href="profile.jsp">Back</a></span>
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
                                                    document.addEventListener("DOMContentLoaded", function () {
                                                        let fileInput = document.getElementById("profileImage");
                                                        let avatarImage = document.getElementById("previewImage");
                                                        let uploadPreviewImage = document.getElementById("uploadPreviewImage");
                                                        let uploadIcon = document.querySelector(".upload-icon");

                                                        fileInput.addEventListener("change", function () {
                                                            previewSelectedImage(this);
                                                        });

                                                        function previewSelectedImage(input) {
                                                            if (input.files && input.files[0]) {
                                                                let reader = new FileReader();
                                                                reader.onload = function (e) {
                                                                    avatarImage.src = e.target.result; // Update avatar
                                                                    uploadPreviewImage.src = e.target.result; // Update upload area
                                                                    uploadPreviewImage.style.display = "block"; // Show image
                                                                    uploadIcon.style.display = "none"; // Hide upload icon
                                                                };
                                                                reader.readAsDataURL(input.files[0]);
                                                            }
                                                        }
                                                    });
        </script>
    </body>
</html>
