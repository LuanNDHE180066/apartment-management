<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Submit Feedback</title>
        <link rel="stylesheet" href="css/bootstrap.min.css" />
        <link rel="stylesheet" href="css/style.css" />
        <style>
            /* Flex container for upload areas */
            .upload-container {
                display: flex;
                flex-wrap: wrap;
                gap: 10px;
            }

            /* Each upload area is a 150x150 box with a dashed border */
            .upload-area {
                border: 2px dashed #007bff;
                width: 150px;
                height: 150px;
                position: relative;
                overflow: hidden;
                cursor: pointer;
            }

            /* Default content inside the upload area */
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

            /* Center the text */
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

            /* File input covers the area but will be deactivated when a preview is shown */
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

            /* Preview container sits above the file input */
            .upload-preview {
                position: absolute;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                display: none; /* hidden initially */
                z-index: 5;
            }

            .upload-preview img {
                width: 100%;
                height: 100%;
                object-fit: cover;
                border-radius: 5px;
            }

            /* Delete button styling inside preview */
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

            /* "Add Upload" button styling */
            .add-upload-btn {
                margin-top: 10px; /* add some margin so it doesn't stick to the container */
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
                            <h4 class="mb-4 text-center">Gửi Phản Hồi Của Bạn</h4>
                            <form action="sendfeedback" method="post" enctype="multipart/form-data">
                                <input type="hidden" name="rID" value="${rID}">

                                <!-- Request Type Dropdown -->
                                <div class="form-group">
                                    <label for="typeOfRequest" class="font-weight-bold">Loại Yêu Cầu</label>
                                    <select id="typeOfRequest" name="typeOfRequest" class="form-control" required>
                                        <option value="" disabled selected>-- Chọn loại --</option>
                                        <c:forEach items="${requestScope.listOfTypeRequest}" var="tr">
                                            <option value="${tr.id}">${tr.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <!-- Feedback Details -->
                                <div class="form-group">
                                    <label for="content" class="font-weight-bold">Chi Tiết Phản Hồi</label>
                                    <textarea name="content" maxlength="200" class="form-control" 
                                              placeholder="Viết phản hồi của bạn tại đây... (Tối đa 200 ký tự)" 
                                              style="resize: none; height: 150px;" required></textarea>
                                    <small class="form-text text-muted">Tối đa 200 ký tự.</small>
                                </div>

                                <!-- Rating Selection -->
                                <div class="form-group">
                                    <label class="font-weight-bold">Đánh Giá Trải Nghiệm Của Bạn</label>
                                    <div class="d-flex justify-content-between">
                                        <label><input type="radio" name="rate" value="1" required> Rất Tệ</label>
                                        <label><input type="radio" name="rate" value="2"> Tệ</label>
                                        <label><input type="radio" name="rate" value="3"> Trung Bình</label>
                                        <label><input type="radio" name="rate" value="4"> Tốt</label>
                                        <label><input type="radio" name="rate" value="5"> Xuất Sắc</label>
                                    </div>
                                </div>

                                <!-- Image Upload Fields -->
                                <div class="form-group">
                                    <label class="font-weight-bold">Tải Lên Hình Ảnh (Tùy Chọn)</label>
                                    <div id="uploadContainer" class="upload-container">
                                        <div class="upload-area" data-index="0">
                                            <div class="upload-content">
                                                <div class="upload-icon">+</div>
                                                <p>Nhấn hoặc Kéo Hình Ảnh</p>
                                            </div>
                                            <input type="file" class="file-input" name="images[]" accept="image/*" multiple>
                                            <div class="upload-preview"></div>
                                        </div>
                                    </div>
                                    <button type="button" class="add-upload-btn" id="addUpload">+</button>
                                    <small class="form-text text-muted">Bạn có thể thêm nhiều khu vực tải lên và chọn nhiều hình ảnh.</small>
                                </div>

                                <!-- Submit Button -->
                                <div class="text-center">
                                    <h5 class="error-msg">${errorMessage}</h5>
                                    <button type="submit" class="btn btn-primary btn-lg">Gửi Phản Hồi</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script>
            $(document).ready(function () {
                let uploadIndex = 1;

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
                            alert(`"${file.name}" không phải là hình ảnh.`);
                            return;
                        }
                        let reader = new FileReader();
                        reader.onload = function (e) {
                            let img = $('<img>', {
                                src: e.target.result,
                                class: "preview-img",
                                alt: `Xem trước ${index + 1}`
                            });
                            previewContainer.append(img);
                        };
                        reader.readAsDataURL(file);
                    });

                    contentEl.hide();
                    previewContainer.show();
                    $(this).css("pointer-events", "none");
                });

                $(document).on("click", ".delete-btn", function (e) {
                    e.stopPropagation();
                    let uploadArea = $(this).closest(".upload-area");
                    let fileInput = uploadArea.find(".file-input");
                    fileInput.val('');
                    let previewContainer = uploadArea.find(".upload-preview");
                    previewContainer.empty().hide();
                    uploadArea.find(".upload-content").show();
                    fileInput.css("pointer-events", "auto");
                });

                $("#addUpload").on("click", function () {
                    const newUpload = `
      <div class="upload-area" data-index="${uploadIndex}">
        <div class="upload-content">
          <div class="upload-icon">+</div>
          <p>Nhấn hoặc Kéo Hình Ảnh</p>
        </div>
        <input type="file" class="file-input" name="images[]" accept="image/*" multiple>
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