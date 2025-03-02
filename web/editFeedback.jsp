<!DOCTYPE html>
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
                            <h4 class="mb-4 text-center">Submit Your Feedback</h4>
                            <form action="update-feed-back" method="post" enctype="multipart/form-data">
                                <input type="hidden" name="rID" value="${rID}">

                                <div class="form-group">
                                    <label for="typeOfRequest" class="font-weight-bold">Type of Request</label>
                                    <select id="typeOfRequest" name="typeOfRequest" class="form-control" required>
                                        <option value="" disabled selected>-- Select type --</option>
                                        <c:forEach items="${requestScope.listOfTypeRequest}" var="tr">
                                            <option value="${tr.id}" <c:if test="${feedback.requestType.name != null && feedback.requestType.name == tr.name}">selected</c:if>>${tr.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="form-group">
                                    <label for="content" class="font-weight-bold">Feedback Details</label>
                                    <textarea name="content" maxlength="200" class="form-control" 
                                              placeholder="Write your feedback here... (Max 200 characters)" 
                                              style="resize: none; height: 150px;" required>${feedback.detail}</textarea>
                                    <small class="form-text text-muted">Maximum 200 characters.</small>
                                </div>

                                <div class="form-group">
                                    <label class="font-weight-bold">Rate Your Experience</label>
                                    <div class="d-flex justify-content-between">
                                        <label><input type="radio" name="rate" value="1" <c:if test="${feedback.rate==1}">checked="" </c:if> required> Very Bad</label>
                                        <label><input type="radio" name="rate" value="2" <c:if test="${feedback.rate==2}">checked="" </c:if>> Bad</label>
                                        <label><input type="radio" name="rate" value="3" <c:if test="${feedback.rate==3}">checked="" </c:if>> Neutral</label>
                                        <label><input type="radio" name="rate" value="4" <c:if test="${feedback.rate==4}">checked="" </c:if>> Good</label>
                                        <label><input type="radio" name="rate" value="5" <c:if test="${feedback.rate==5}">checked="" </c:if>> Excellent</label>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="font-weight-bold">Upload Images (Optional)</label>
                                        <div id="uploadContainer" class="upload-container">
                                            <!-- Pre-existing images -->
                                        <c:forEach items="${feedback.img}" var="image" varStatus="loop">
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
                                    <small class="form-text text-muted">You can add multiple upload areas and select multiple images.</small>
                                </div>

                                <div class="text-center">
                                    <button type="submit" class="btn btn-primary btn-lg">Submit Feedback</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script>
            $(document).ready(function () {
                let uploadIndex = 0;

                // Add new upload area
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

                // Handle file input change for new uploads
                $(document).on("change", ".file-input", function () {
                    let uploadArea = $(this).closest(".upload-area");
                    let previewContainer = uploadArea.find(".upload-preview");
                    let contentEl = uploadArea.find(".upload-content");
                    previewContainer.empty(); // Clear previous preview

                    let files = this.files;
                    if (!files || files.length === 0)
                        return;

                    let file = files[0]; // Take the first file
                    if (!file.type.startsWith("image/")) {
                        alert(`"${file.name}" is not an image.`);
                        return;
                    }

                    let reader = new FileReader();
                    reader.onload = function (e) {
                        let img = $('<img>', {
                            src: e.target.result,
                            class: "preview-img",
                            alt: "Preview"
                        });
                        let deleteBtn = $('<button type="button" class="delete-btn">×</button>');
                        previewContainer.append(img).append(deleteBtn);
                        previewContainer.show();
                        contentEl.hide();
                    };
                    reader.readAsDataURL(file);
                });

                // Handle delete button clicks
                $(document).on("click", ".delete-btn", function (e) {
                    e.stopPropagation();
                    let uploadArea = $(this).closest(".upload-area");

                    if (uploadArea.hasClass("existing-image")) {
                        // Remove existing image area completely
                        uploadArea.remove();
                    } else if (uploadArea.hasClass("new-upload")) {
                        // Reset new upload area
                        let fileInput = uploadArea.find(".file-input");
                        let previewContainer = uploadArea.find(".upload-preview");
                        let contentEl = uploadArea.find(".upload-content");

                        fileInput.val(""); // Clear the file input
                        previewContainer.empty().hide(); // Clear and hide preview
                        contentEl.show(); // Show the placeholder content
                    }
                });
            });
        </script>
    </body>
</html>