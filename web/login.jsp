<%-- 
<<<<<<<<< Temporary merge branch 1
    Document   : login
    Created on : 13 Jan 2025, 22:12:51
    Author     : Lenovo
=========
    Document   : login.jsp
    Created on : Jan 13, 2025, 11:16:47 PM
    Author     : PC
>>>>>>>>> Temporary merge branch 2
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" import="dao.RoleDAO,model.Role,java.util.List,java.util.ArrayList"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    <html lang="en">
        <head>
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
            <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
            <![endif]-->
            <!-- basic -->
            <meta charset="utf-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <!-- mobile metas -->
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <meta name="viewport" content="initial-scale=1, maximum-scale=1">
            <!-- site metas -->
            <title>Apartment management</title>
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
            <!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
            <![endif]-->
        </head>
        <body class="inner_page login">
            <%
               RoleDAO dao = new RoleDAO();
               List<Role> list = new ArrayList<>();
               list=dao.getAll();
               request.setAttribute("rolelist",list);
            %>
            <div class="full_container">
                <div class="container">
                    <div class="center verticle_center full_height">
                        <div class="login_section">
                            <div class="logo_login">
                                <div class="center">
                                    <img width="500" src="images/logo/building.jpg" alt="#" />
                                </div>
                            </div>

                            <div class="login_form">
                                <form action="login" method="post">
                                    <fieldset>
                                        <div class="field">
                                            <label class="label_field">Username</label>
                                            <input type="text" name="username" placeholder="Username" value="${cookie.rememberedUser.value}" />
                                        </div>
                                        <div class="field">
                                            <label class="label_field">Password</label>
                                            <input type="password" name="password" placeholder="Password" value="${cookie.rememberedPass.value}" />
                                        </div>
                                        <div class="field">
                                            <label class="label_field">Role</label>
                                            <span>
                                                <select id="role" name="role" class="form-control">
                                                    <c:forEach items="${requestScope.rolelist}" var="o">
                                                        <option value="${o.id}" <c:if test="${o.id == cookie.rememberedRole.value}">selected</c:if> <c:if test="${null == cookie.rememberedRole.value}"><c:if test="${o.id == 1}">selected</c:if></c:if>>${o.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </span>
                                            <a class="forgot" href="requestpassword.jsp">Forgotten Password?</a>
                                        </div>
                                        <div class="field remember-me" style="display: flex ;justify-content: start">
                                            <label  class="label_field" for="remember-checkbox">Remember Me</label>
                                            <input type="checkbox" name="remember" id="remember-checkbox" value="on" style="width: 10%" <c:if test="${cookie.remembered !=null}">checked="" </c:if> />
                                            </div>
                                            <h6 style="color:${status=="true"?"green":"red"};text-align:center ">${requestScope.message}</h6>
                                        <h6 style="color:red;text-align:center">${requestScope.error}</h6>
                                        <div class="field margin_0">
                                            <label class="label_field hidden">hidden label</label>
                                            <button class="main_bt" type="submit">Sign In</button>
                                            <div>
                                                <i class="fa fa-google" aria-hidden="true"></i>
                                                <a href="https://accounts.google.com/o/oauth2/auth?scope=openid%20email%20profile&redirect_uri=http://localhost:8080/apartment-management/login-google&response_type=code&client_id=879385543144-fh57neia4tt4ogqqjfk4hnb5182npalt.apps.googleusercontent.com&access_type=offline&prompt=consent">Sign In With Google</a>                                            </div>
                                        </div>
                                    </fieldset>
                                </form>

                            </div>

                        </div>
                    </div>
                </div>
            </div>
            <!-- jQuery -->          
            <!-- wow animation -->
            <script src="js/animate.js"></script>
            <!-- select country -->
            <script src="js/bootstrap-select.js"></script>
            <!-- nice scrollbar -->
            <script src="js/perfect-scrollbar.min.js"></script>
            <script>
                var ps = new PerfectScrollbar('#sidebar');
            </script>
            <!-- custom js -->
            <script src="js/custom.js"></script>
            <script src="js/jquery.min.js"></script>
            <script src="js/popper.min.js"></script>
            <script src="js/bootstrap.min.js"></script>
            <!-- jQuery -->
            <script src="js/jquery.min.js"></script>
            <script src="js/popper.min.js"></script>
            <script src="js/bootstrap.min.js"></script>
            <!-- wow animation -->
            <script src="js/animate.js"></script>
            <!-- select country -->
            <script src="js/bootstrap-select.js"></script>
            <!-- owl carousel -->
            <script src="js/owl.carousel.js"></script> 
            <!-- chart js -->
            <script src="js/Chart.min.js"></script>
            <script src="js/Chart.bundle.min.js"></script>
            <script src="js/utils.js"></script>
            <script src="js/analyser.js"></script>
            <!-- nice scrollbar -->
            <script src="js/perfect-scrollbar.min.js"></script>
            <script>
                var ps = new PerfectScrollbar('#sidebar');
            </script>
            <!-- custom js -->
            <script src="js/custom.js"></script>
            <script src="js/chart_custom_style1.js"></script>
        </body>
    </html>