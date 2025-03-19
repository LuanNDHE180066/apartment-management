<%-- 
    Document   : addnews
    Created on : Feb 11, 2025, 4:19:44 PM
    Author     : PC
--%>

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
        <title>Apartment management</title>        <meta name="keywords" content="">
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
        <script src="https://cdn.ckeditor.com/ckeditor5/39.0.1/decoupled-document/ckeditor.js"></script>
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
                                    <h1>Add News</h1>
                                    <form action="add-news" method="post" enctype="multipart/form-data">
                                        <div class="form-group">
                                            <label for="title">Title</label>
                                            <input type="text" id="title" name="title" value="${param.title}" placeholder="Enter new title" required />
                                            <span style="color: red">${requestScope.titleerror}</span>
                                        </div>
                                        <div class="form-group">
                                            <label for="content">Content</label>
                                            <div id="toolbar-container"></div> <!-- Thanh c�ng c? CKEditor -->
                                            <div id="editor">${param.content != null ? param.content : ""}</div>
                                            <input type="hidden" name="content" id="hiddenContent">


                                            <span style="color: red">${requestScope.contenterror}</span>
                                        </div>

                                        <div class="form-group">
                                            <label for="date">Date</label>
                                            <input type="date" id="date" name="date" value="${param.date}" required />
                                            <span  style="color: red">${requestScope.dateError}</span>
                                        </div>
                                        <div class="form-group">
                                            <label for="source">Source</label>
                                            <input type="text" id="source" name="source" placeholder="Enter content" value="${param.source}" required />
                                            <span style="color: red">${requestScope.sourceerror}</span>
                                        </div>
                                        <div class="form-group">
                                            <label for="category">Category</label>
                                            <select id="category" name="category" required>
                                                <c:if test="${requestScope.categories == null}">
                                                    <option value="Apartment News">Apartment News</option>
                                                </c:if>
                                                <c:forEach items="${requestScope.categories}" var="category">
                                                    <option value="${category}">${category}</option>   
                                                </c:forEach>                                   
                                            </select>
                                        </div>
                                        <!--                                        <div class="form-group">
                                                                                    <label for="file">Image</label>
                                                                                    <input style="margin-bottom: 5px;margin-top: 5px;" type="file" name="file" id="file" accept=".jpg, .jpeg">
                                                                                    <span style="color: red">${requestScope.fileerror}</span>
                                                                                </div>-->
                                        <div class="form-group">
                                            <label for="author">Author</label>
                                            <input type="text" id="author" name="author" value="${sessionScope.person.name}"  readonly />
                                            <input type="text" id="authorid" name="authorid" value="${sessionScope.person.id}"  hidden />
                                        </div>
                                        <div class="form-button">
                                            <button type="submit">Add News</button>
                                            <h5 style="color:${status=="true"?"green":"red"};text-align:center ">${requestScope.message}</h5>
                                            <span  style="text-decoration: underline; display: inline-block"><a><a href="view-news">Back</a></span>
                                        </div>
                                    </form>
                                </div>
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
        <script>
            // H�m upload ?nh
            class MyUploadAdapter {
                constructor(loader) {
                    this.loader = loader;
                }

                upload() {
                    return this.loader.file
                            .then(file => new Promise((resolve, reject) => {
                                    const formData = new FormData();
                                    formData.append('upload', file); // Ph?i tr�ng v?i request.getPart("upload") trong Servlet

                                    fetch('http://localhost:8080/apartment-management/upload-img-news', {// URL servlet upload
                                        method: 'POST',
                                        body: formData
                                    })
                                            .then(response => {
                                                if (!response.ok) {
                                                    throw new Error(`L?i HTTP! M� tr?ng th�i: ${response.status}`);
                                                }
                                                return response.json();
                                            })
                                            .then(result => {
                                                if (!result || !result.url) {
                                                    return reject('Upload ?nh th?t b?i!');
                                                }
                                                resolve({
                                                    default: result.url  // ???ng d?n ?nh tr? v? t? server
                                                });
                                            })
                                            .catch(error => {
                                                console.error('L?i upload ?nh:', error);
                                                reject('Kh�ng th? upload ?nh!');
                                            });
                                }));
                }
            }

// G�n plugin upload ?nh v�o CKEditor
            function MyCustomUploadAdapterPlugin(editor) {
                editor.plugins.get('FileRepository').createUploadAdapter = (loader) => {
                    return new MyUploadAdapter(loader);
                };
            }

// Kh?i t?o CKEditor
            DecoupledEditor
                    .create(document.querySelector('#editor'), {
                        extraPlugins: [MyCustomUploadAdapterPlugin]
                    })
                    .then(editor => {
                        const toolbarContainer = document.querySelector('#toolbar-container');
                        toolbarContainer.appendChild(editor.ui.view.toolbar.element);

                        // L?u n?i dung v�o input ?n khi submit form
                        document.querySelector("form").addEventListener("submit", function () {
                            document.querySelector("#hiddenContent").value = editor.getData();
                        });

                    })
                    .catch(error => {
                        console.error('CKEditor l?i:', error);
                    });

        </script>

    </body>
</html>
