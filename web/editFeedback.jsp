<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Submit Feedback</title>
        <link rel="stylesheet" href="css/bootstrap.min.css" />
        <link rel="stylesheet" href="css/style.css" />
        <style>
            .upload-container {
                display: flex;
                flex-wrap: wrap;
                gap: 10px;
            }

            .upload-area {
                border: 2px dashed #007bff;
                width: 150px;
                height: 150px;
                position: relative;
                overflow: hidden;
                cursor: pointer;
            }

            .upload-content {
                position: absolute;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                z-index: 3;
                display: flex;
                flex-direction: column;
                justify-content: center;
                align-items: center;
                pointer-events: none;
            }

            .upload-content p {
                margin: 0;
                padding: 5px;
                text-align: center;
            }

            .upload-icon {
                font-size: 30px;
                color: #007bff;
                margin-bottom: 10px;
            }

            .file-input {
                position: absolute;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                opacity: 0;
                z-index: 2;
                cursor: pointer;
            }

            .upload-preview {
                position: absolute;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                z-index: 5;
                display: none; /* Hidden by default */
            }

            .upload-preview img {
                width: 100%;
                height: 100%;
                object-fit: cover;
                border-radius: 5px;
            }

            .delete-btn {
                position: absolute;
                top: 5px;
                right: 5px;
                background: rgba(0, 0, 0, 0.6);
                color: #fff;
                border: none;
                border-radius: 50%;
                width: 25px;
                height: 25px;
                cursor: pointer;
                z-index: 10;
            }

            .add-upload-btn {
                margin-top: 10px;
                background-color: #007bff;
                color: white;
                border: none;
                border-radius: 50%;
                width: 30px;
                height: 30px;
                font-size: 20px;
                line-height: 30px;
                cursor: pointer;
            }

            .add-upload-btn:hover {
                background-color: #0056b3;
            }
            .error-msg {
                color: red;
                font-weight: bold;
            }
        </style>
        <script src="js/jquery.min.js"></script>
    </head>
    <body>
        <div class="full_container">
            <div class="inner_container">
                <%@include file="sidebar.jsp" %>
                <div id="content">
                    <%@include file="topbar.jsp" %>
                    <div class="container mt-5">
                        <div class="card shadow-sm p-4">
                            <h4 class="mb-4 text-center">Sửa Đánh Giá</h4>
                            <form action="update-feed-back" method="post" enctype="multipart/form-data">
                                <input type="hidden" name="fID" value="${feedback.id}">
                                <div class="form-group">
                                    <label for="typeOfRequest" class="font-weight-bold">Loại Dịch Vụ</label>
                                    <select id="typeOfRequest" name="typeOfRequest" class="form-control" required>
                                        <option value="" disabled selected>-- Chọn Loại Dịch Vụ --</option>
                                        <c:forEach items="${requestScope.listOfTypeRequest}" var="tr">
                                            <option value="${tr.id}" <c:if test="${feedback.services.id != null && feedback.services.id == tr.id}">selected</c:if>>${tr.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="form-group">
                                    <label for="content" class="font-weight-bold">Đánh Giá Chi Tiết</label>
                                    <textarea name="content" maxlength="200" class="form-control" 
                                              placeholder="Write your feedback here... (Max 200 characters)" 
                                              style="resize: none; height: 150px;" required>${feedback.detail}</textarea>
                                    <small class="form-text text-muted">Tối Đa 200 kí tự.</small>
                                </div>

                                <div class="form-group">
                                    <label class="font-weight-bold">Rate Your Experience</label>
                                    <div class="d-flex justify-content-between">
                                        <label><input type="radio" name="rate" value="1" <c:if test="${feedback.rate==1}">checked="" </c:if> required>Rất Tệ</label>
                                        <label><input type="radio" name="rate" value="2" <c:if test="${feedback.rate==2}">checked="" </c:if>> Tệ</label>
                                        <label><input type="radio" name="rate" value="3" <c:if test="${feedback.rate==3}">checked="" </c:if>> Ổn</label>
                                        <label><input type="radio" name="rate" value="4" <c:if test="${feedback.rate==4}">checked="" </c:if>> Tốt</label>
                                        <label><input type="radio" name="rate" value="5" <c:if test="${feedback.rate==5}">checked="" </c:if>> Rất Tốt</label>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="font-weight-bold">Đăng Ảnh(tùy chọn)</label>
                                        <div id="uploadContainer" class="upload-container">
                                            <!-- Pre-existing images -->
                                        <c:forEach items="${feedback.img}" var="image">
                                            <div class="upload-area existing-image" data-image-url="${image}">
                                                <div class="upload-preview" style="display: block;">
                                                    <img src="${image}" class="preview-img" alt="Uploaded Image">
                                                    <button type="button" class="delete-btn">×</button>
                                                </div>
                                                <input type="hidden" name="existingImages[]" value="${image}">
                                            </div>
                                        </c:forEach>
                                    </div>
                                    <button type="button" class="add-upload-btn" id="addUpload">+</button>
                                    <small class="form-text text-muted">Có thể thêm nhiều ảnh</small>
                                </div>

                                <!-- Hidden input to store deleted images -->
                                <input type="hidden" id="deletedImages" name="deletedImages">


                                <div class="text-center">
                                    <h5 class="error-msg">${errorMessage}</h5>
                                    <button type="submit" class="btn btn-primary btn-lg">Gửi</button>
                                </div>
                            </form>
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
            $(document).ready(function () {
                let uploadIndex = 1;
                let deletedImages = [];

                // Load existing images on page load
                $(".existing-image").each(function () {
                    let uploadArea = $(this);
                    let previewContainer = uploadArea.find(".upload-preview");
                    let imageUrl = uploadArea.data("image-url");

                    if (imageUrl) {
                        let img = $('<img>', {src: imageUrl, class: "preview-img", alt: "Uploaded Image"});
                        let deleteBtn = $('<button type="button" class="delete-btn">×</button>');
                        previewContainer.append(img).append(deleteBtn);
                        previewContainer.show();
                    }
                });

                // Handle file input change: show preview and disable pointer events on the file input.
                $(document).on("change", ".file-input", function () {
                    let uploadArea = $(this).closest(".upload-area");
                    let previewContainer = uploadArea.find(".upload-preview");
                    let contentEl = uploadArea.find(".upload-content");

                    previewContainer.empty();
                    let files = this.files;
                    if (!files || files.length === 0) {
                        previewContainer.hide();
                        contentEl.show();
                        $(this).css("pointer-events", "auto");
                        return;
                    }

                    let deleteBtn = $('<button type="button" class="delete-btn">×</button>');
                    previewContainer.append(deleteBtn);

                    Array.from(files).forEach((file, index) => {
                        if (!file.type.startsWith("image/")) {
                            alert(`"${file.name}" is not an image.`);
                            return;
                        }
                        let reader = new FileReader();
                        reader.onload = function (e) {
                            let img = $('<img>', {src: e.target.result, class: "preview-img", alt: `Preview ${index + 1}`});
                            previewContainer.append(img);
                        };
                        reader.readAsDataURL(file);
                    });

                    contentEl.hide();
                    previewContainer.show();
                    $(this).css("pointer-events", "none");
                });

                // Delete button click: clear preview and re-enable the file input.
                $(document).on("click", ".delete-btn", function (e) {
                    e.stopPropagation();
                    let uploadArea = $(this).closest(".upload-area");
                    let fileInput = uploadArea.find(".file-input");
                    fileInput.val('');
                    let previewContainer = uploadArea.find(".upload-preview");
                    previewContainer.empty().hide();
                    uploadArea.find(".upload-content").show();
                    fileInput.css("pointer-events", "auto");

                    // Track deleted existing images
                    if (uploadArea.hasClass("existing-image")) {
                        let imageUrl = uploadArea.data("image-url");
                        if (imageUrl) {
                            deletedImages.push(imageUrl);
                        }
                        uploadArea.remove();
                    }

                    // Update hidden input field for deleted images
                    $("#deletedImages").val(JSON.stringify(deletedImages));
                });

                // Append new upload area on clicking the "add" button.
                $("#addUpload").on("click", function () {
                    const newUpload = `
            <div class="upload-area new-upload" data-index="${uploadIndex}">
                <div class="upload-content">
                    <div class="upload-icon">+</div>
                    <p>Click or Drag an Image</p>
                </div>
                <input type="file" class="file-input" name="newImages[]" accept="image/*">
                <div class="upload-preview"></div>
            </div>
        `;
                    $("#uploadContainer").append(newUpload);
                    uploadIndex++;
                });
            });

        </script>
    </body>
</html>