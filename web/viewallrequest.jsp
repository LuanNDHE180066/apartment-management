<%-- 
    Document   : viewallrequest.jsp
    Created on : Jan 24, 2025, 8:31:15 PM
    Author     : PC
--%>

<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
    <head>
        <!-- basic -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- mobile metas -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="viewport" content="initial-scale=1, maximum-scale=1">
        <!-- site metas -->
        <title>Apartment management</title>        <link rel="icon" href="images/fevicon.png" type="image/png" />
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
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
        <style>
            .table th, .table td {
                border: 1px solid rgba(0, 0, 0, 0.2);
                text-align: center;
            }
            .custom-checkbox {
                position: relative;
            }
            .custom-checkbox input[type="checkbox"] {
                opacity: 0;
                position: absolute;
                margin: 5px 0 0 3px;
                z-index: 9;
            }
            .custom-checkbox label:before{
                width: 18px;
                height: 18px;
            }
            .custom-checkbox label:before {
                content: '';
                margin-right: 10px;
                display: inline-block;
                vertical-align: text-top;
                background: white;
                border: 1px solid #bbb;
                border-radius: 2px;
                box-sizing: border-box;
                z-index: 2;
            }
            .custom-checkbox input[type="checkbox"]:checked + label:after {
                content: '';
                position: absolute;
                left: 6px;
                top: 3px;
                width: 6px;
                height: 11px;
                border: solid #000;
                border-width: 0 3px 3px 0;
                transform: inherit;
                z-index: 3;
                transform: rotateZ(45deg);
            }
            .custom-checkbox input[type="checkbox"]:checked + label:before {
                border-color: #03A9F4;
                background: #03A9F4;
            }
            .custom-checkbox input[type="checkbox"]:checked + label:after {
                border-color: #fff;
            }
            .custom-checkbox input[type="checkbox"]:disabled + label:before {
                color: #b8b8b8;
                cursor: auto;
                box-shadow: none;
                background: #ddd;
            }
            /* Modal styles */
            .modal .modal-dialog {
                max-width: 80%;
            }
            .modal .modal-header, .modal .modal-body, .modal .modal-footer {
                padding: 20px 30px;
            }
            .modal .modal-content {
                border-radius: 3px;
            }
            .modal .modal-footer {
                background: #ecf0f1;
                border-radius: 0 0 3px 3px;
            }
            .modal .modal-title {
                display: inline-block;
            }
            .modal .form-control {
                border-radius: 2px;
                box-shadow: none;
                border-color: #dddddd;
            }
            .modal textarea.form-control {
                resize: vertical;
            }
            .modal .btn {
                border-radius: 2px;
                min-width: 100px;
            }
            .modal form label {
                font-weight: normal;
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
                            <div class="row column_title">
                                <div class="col-md-12">
                                    <div class="page_title">
                                        <h2>Tables</h2>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <!-- table section -->
                                <div class="col-md-12">
                                    <c:if test="${sessionScope.account.roleId == 2}">
                                        <div class="white_shd full margin_bottom_30">
                                            <div class="full graph_head">
                                                <div class="heading1 margin_0">
                                                    <h2>Thông tin yêu cầu từ dân cư</h2>
                                                </div>
                                            </div>
                                            <div class="full graph_head">
                                                <div class="heading1 margin_0">
                                                    <h3>Yêu cầu chờ</h3> 
                                                </div>
                                            </div>                  
                                            <div class="table_section padding_infor_info">
                                                <div class="table-responsive-sm">
                                                    <div><h5 style="color: red">${requestScope.error_staff}</h5></div>
                                                    <table class="table w-100">
                                                        <thead>
                                                            <tr>
                                                                <th>Tên dân cư</th> 
                                                                <th>Căn hộ</th>
                                                                <th>Thông tin</th>
                                                                <th>Ngày gửi</th>                                               
                                                                <th>Loại</th>
                                                                <th>Trạng thái</th>
                                                                <th>Lựa chọn</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <c:forEach items="${requestScope.waiting_requestes}" var="o" varStatus="status">
                                                                <tr>
                                                                    <td style="text-align: left">${o.residentId.name}</td>
                                                                    <td style="text-align: left">${o.aid.id}</td>
                                                                    <td style="text-align: left">${o.detail}</td>
                                                                    <td style="text-align: left">${o.date}</td> 
                                                                    <td style="text-align: left">${o.requestType.name}</td>  
                                                                    <td style="text-align: left">${o.status}</td> 
                                                                    <td>
                                                                        <a href="#assignRequestWaiting${status.index}" class="edit" data-toggle="modal">
                                                                            <i class="material-icons" data-toggle="tooltip" title="Assign">&#xE254;</i>
                                                                        </a>
                                                                        <div id="assignRequestWaiting${status.index}" class="modal fade">
                                                                            <div class="modal-dialog">
                                                                                <div class="modal-content">
                                                                                    <h3>Working Staff Information</h3>
                                                                                    <div class="table_section padding_infor_info">
                                                                                        <div class="table-responsive-sm">
                                                                                            <table class="table w-100">
                                                                                                <thead>
                                                                                                    <tr>
                                                                                                        <th>Tên</th>                                                            
                                                                                                        <th>Email</th>
                                                                                                        <th>Số điện thoại</th>                                               
                                                                                                        <th>Địa chỉ</th>
                                                                                                        <th>Ca làm</th>
                                                                                                        <th>Lựa chọn</th>
                                                                                                    </tr>
                                                                                                </thead>
                                                                                                <tbody>
                                                                                                    <c:choose>
                                                                                                        <c:when test="${o.requestType.destination.id == '5'}">
                                                                                                            <c:set var="targetList" value="${requestScope.environmental}" />
                                                                                                        </c:when>
                                                                                                        <c:otherwise>
                                                                                                            <c:set var="targetList" value="${requestScope.engineer}" />
                                                                                                        </c:otherwise>
                                                                                                    </c:choose>

                                                                                                    <c:forEach items="${targetList}" var="staff">
                                                                                                        <tr>
                                                                                                            <td style="text-align: left">${staff.name}</td>
                                                                                                            <td style="text-align: left">${staff.email}</td>
                                                                                                            <td style="text-align: left">${staff.phone}</td> 
                                                                                                            <td style="text-align: left">${staff.address}</td>   
                                                                                                            <td>
                                                                                                                <form action="assign-request" method="get">
                                                                                                                    <select id="shift" name="shift">
                                                                                                                        <option value="1" data-time-start="8" data-time-end="10">8:00 - 10:00</option>
                                                                                                                        <option value="2" data-time-start="13" data-time-end="15">13:00 - 15:00</option>
                                                                                                                        <option value="3" data-time-start="15" data-time-end="17">15:00 - 17:00</option>
                                                                                                                        <option value="4" data-time-start="18" data-time-end="20">18:00 - 20:00</option>
                                                                                                                    </select>
                                                                                                            </td>
                                                                                                            <td>

                                                                                                                <input type="hidden" name="requestid" value="${o.id}">
                                                                                                                <input type="hidden" name="staffid" value="${staff.id}">
                                                                                                                <button type="submit">Assign</button>
                                                                                                                </form>
                                                                                                            </td>   
                                                                                                        </tr>
                                                                                                    </c:forEach>
                                                                                                </tbody>
                                                                                            </table>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                        <a style="margin-left: 5px" href="update-request-administrative?requestId=${o.id}" class="fa fa-ban"></a>
                                                                    </td> 
                                                                    <td>

                                                                    </td>
                                                                </tr>                                                            
                                                            </c:forEach>

                                                        </tbody>
                                                    </table>
                                                    <form method="get" action="view-all-request" style="display: flex; align-items: center; gap: 10px;">
                                                        <!-- Dropdown chọn trang -->
                                                        <label for="page" style="font-size: 14px; font-weight: bold;">Trang:</label>
                                                        <select id="page" name="page_waiting" onchange="this.form.submit()" 
                                                                style="padding: 6px 12px; font-size: 14px; border: 1px solid #ddd; border-radius: 4px; cursor: pointer;">
                                                            <c:forEach begin="1" end="${requestScope.totalPage_waiting}" var="page">
                                                                <option value="${page}" <c:if test="${page == requestScope.currentPage_waiting}">selected</c:if>>
                                                                    ${page}
                                                                </option>
                                                            </c:forEach>
                                                        </select>
                                                    </form>    
                                                </div>
                                            </div>

                                        </div>
                                    </c:if>   


                                    <div class="white_shd full margin_bottom_30">  
                                        <div class="full graph_head">
                                            <div class="heading1 margin_0">
                                                <h3>Yêu cầu đang làm</h3> 
                                            </div>
                                        </div>
                                        <div style="margin-left: 40px;">
                                            <form action="view-all-request" method="GET">
                                                <div class="row align-items-center">
                                                    <div class="col-md-2">
                                                        <select class="form-control" name="filterRoles">
                                                            <option value="">Tìm dựa trên vai trò</option>
                                                            <c:forEach items="${requestScope.rolelist}" var="o">
                                                                <c:if test="${o.id == 4 ||  o.id == 5 || o.id == 7}">
                                                                    <option value="${o.id}" <c:if test="${requestScope.filterRoles == o.id}">selected</c:if>>${o.name}</option>
                                                                </c:if>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="col-md-4 d-flex">
                                                        <button type="submit" class="btn btn-primary" style="margin-right: 5px;">Filter</button>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                        <div class="table_section padding_infor_info">
                                            <div class="table-responsive-sm">
                                                <div><h5 style="color: red">${requestScope.error_staff}</h5></div>
                                                <table class="table w-100">
                                                    <thead>
                                                        <tr>
                                                            <th>Tên dân cư</th> 
                                                            <th>Căn hộ</th>
                                                            <th>Thông tin</th>
                                                            <th>Ngày gửi</th>                                               
                                                            <th>Loại</th>
                                                            <th>Trạng thái</th>
                                                            <th>Ca làm</th>
                                                            <th>Nhân viên phụ trách</th>
                                                            <th>Lựa chon</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach items="${requestScope.inprocess_requestes}" var="o" varStatus="status">
                                                            <tr>
                                                                <td style="text-align: left">${o.residentId.name}</td>
                                                                <td style="text-align: left">${o.aid.id}</td>
                                                                <td style="text-align: left">${o.detail}</td>
                                                                <td style="text-align: left">${o.date}</td> 
                                                                <td style="text-align: left">${o.requestType.name}</td>                                             
                                                                <td style="text-align: left">${o.status}</td>
                                                                <td style="text-align: left">${o.shift}</td>
                                                                <td style="text-align: left">${o.staffId.name}</td> 
                                                                <c:if test="${sessionScope.account.roleId!=2}">
                                                                    <td>
                                                                        <a href="update-request-staff?requestId=${o.id}" class="fas fa-edit"></a>
                                                                    </td>
                                                                </c:if>
                                                                <c:if test="${sessionScope.account.roleId==2}">
                                                                    <td>
                                                                        <a href="#assignRequestProcess${status.index}"
                                                                           class="edit" data-toggle="modal">
                                                                            <i class="material-icons" data-toggle="tooltip" title="Assign">&#xE254;</i>
                                                                        </a>
                                                                        <div id="assignRequestProcess${status.index}" class="modal fade">
                                                                            <div class="modal-dialog">
                                                                                <div class="modal-content">
                                                                                    <h3>Working Staff Information</h3>
                                                                                    <div class="table_section padding_infor_info">
                                                                                        <div class="table-responsive-sm">
                                                                                            <table class="table w-100">
                                                                                                <thead>
                                                                                                    <tr>
                                                                                                        <th>Tên</th>                                                            
                                                                                                        <th>Email</th>
                                                                                                        <th>Số điện thoại</th>                                               
                                                                                                        <th>Địa chỉ</th>
                                                                                                        <th>Ca làm</th>
                                                                                                        <th>Lựa chọn</th>
                                                                                                    </tr>
                                                                                                </thead>
                                                                                                <tbody>
                                                                                                    <c:choose>
                                                                                                        <c:when test="${o.requestType.destination.id == '5'}">
                                                                                                            <c:set var="targetList" value="${requestScope.environmental}" />
                                                                                                        </c:when>
                                                                                                        <c:otherwise>
                                                                                                            <c:set var="targetList" value="${requestScope.engineer}" />
                                                                                                        </c:otherwise>
                                                                                                    </c:choose>

                                                                                                    <c:forEach items="${targetList}" var="staff">
                                                                                                        <tr>
                                                                                                            <td style="text-align: left">${staff.name}</td>
                                                                                                            <td style="text-align: left">${staff.email}</td>
                                                                                                            <td style="text-align: left">${staff.phone}</td> 
                                                                                                            <td style="text-align: left">${staff.address}</td>
                                                                                                            <td>
                                                                                                                <form action="assign-request" method="get">
                                                                                                                    <select id="shift" name="shift">
                                                                                                                        <option value="1" data-time-end="10">8:00 - 10:00</option>
                                                                                                                        <option value="2" data-time-end="15">13:00 - 15:00</option>
                                                                                                                        <option value="3" data-time-end="17">15:00 - 17:00</option>
                                                                                                                        <option value="4" data-time-end="20">18:00 - 20:00</option>
                                                                                                                    </select>
                                                                                                            </td>
                                                                                                            <td><input type="hidden" name="destination" value="${status.index}">
                                                                                                                <input type="hidden" name="requestid" value="${o.id}">
                                                                                                                <input type="hidden" name="staffid" value="${staff.id}">
                                                                                                                <button type="submit">Assign</button>
                                                                                                                </form>
                                                                                                            </td>  
                                                                                                        </tr>
                                                                                                    </c:forEach>
                                                                                                </tbody>
                                                                                            </table>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div>                                                           
                                                                    </td> 
                                                                </c:if>
                                                            </tr>
                                                        </c:forEach>

                                                    </tbody>
                                                </table>
                                                <form method="get" action="view-all-request" style="display: flex; align-items: center; gap: 10px;">
                                                    <!-- Dropdown chọn trang -->
                                                    <label for="page" style="font-size: 14px; font-weight: bold;">Trang:</label>
                                                    <select id="page" name="page_inprocess" onchange="this.form.submit()" 
                                                            style="padding: 6px 12px; font-size: 14px; border: 1px solid #ddd; border-radius: 4px; cursor: pointer;">
                                                        <c:forEach begin="1" end="${requestScope.totalPage_inprocess}" var="page">
                                                            <option value="${page}" <c:if test="${page == requestScope.currentPage_inprocess}">selected</c:if>>
                                                                ${page}
                                                            </option>
                                                        </c:forEach>
                                                    </select>
                                                </form>
                                            </div>
                                        </div>  
                                    </div>

                                    <div class="white_shd full margin_bottom_30">
                                        <div class="full graph_head">
                                            <div class="heading1 margin_0">
                                                <h3>Yêu cầu đã hoàn thành</h3> 
                                            </div>
                                        </div>                

                                        <div class="table_section padding_infor_info">
                                            <div class="table-responsive-sm">

                                                <table class="table w-100">
                                                    <thead>
                                                        <tr>
                                                            <th>Tên dân cư</th> 
                                                            <th>Căn hộ</th>
                                                            <th>Thông tin</th>
                                                            <th>Ngày gửi</th>                                               
                                                            <th>Loại</th>
                                                            <th>Trạng thái</th>
                                                            <th>Ca làm</th>
                                                            <th>Nhân viên phụ trách</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach items="${requestScope.done_requestes}" var="o" varStatus="status">
                                                            <tr>
                                                                <td>${o.residentId.name}</td>
                                                                <td>${o.aid.id}</td>
                                                                <td>${o.detail}</td>
                                                                <td>${o.date}</td> 
                                                                <td>${o.requestType.name}</td>                                             
                                                                <td>${o.status}</td>
                                                                <td>${o.shift}</td>
                                                                <td>${o.status != 'No response' ? o.staffId.name : ''}</td>                           
                                                            </tr>
                                                        </c:forEach>

                                                    </tbody>
                                                </table>
                                                <form method="get" action="view-all-request" style="display: flex; align-items: center; gap: 10px;">
                                                    <!-- Dropdown chọn trang -->
                                                    <label for="page" style="font-size: 14px; font-weight: bold;">Trang:</label>
                                                    <select id="page" name="page_done" onchange="this.form.submit()" 
                                                            style="padding: 6px 12px; font-size: 14px; border: 1px solid #ddd; border-radius: 4px; cursor: pointer;">
                                                        <c:forEach begin="1" end="${requestScope.totalPage_done}" var="page">
                                                            <option value="${page}" <c:if test="${page == requestScope.currentPage_done}">selected</c:if>>
                                                                ${page}
                                                            </option>
                                                        </c:forEach>
                                                    </select>
                                                </form>
                                            </div>
                                        </div>                


                                    </div>
                                </div>
                                <!-- More tables can be added here -->
                            </div>
                        </div>

                    </div>
                    <div class="footer" style="position: absolute; bottom: 50px; left: 50px; width: 100%; background: white; color: white; text-align: center; padding: 10px;">
                        <p>Copyright © 2025 Designed by Your Company. All rights reserved.</p>
                    </div>
                </div>
                <!-- end dashboard inner -->
            </div>
        </div>
        <!-- jQuery -->
        <!--        <script>
                    function updateOptionsByTime() {
                        const currentHour = new Date().getHours(); // Lấy giờ hiện tại
                        const options = document.querySelectorAll("#shift option");
        
                        options.forEach(option => {
                            const endHour = parseInt(option.getAttribute("data-time-end"));
                            if (currentHour < endHour) {
                                option.hidden = false; // Hiển thị option
                            } else {
                                option.hidden = true; // Ẩn option
                            }
                        });
                    }
                    // Gọi hàm khi trang load
                    updateOptionsByTime();
                    // Cập nhật mỗi phút để kiểm tra lại điều kiện
                    setInterval(updateOptionsByTime, 60000);
                </script>-->
        <script src="js/jquery.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/custom.js"></script>
    </body>
</html>
