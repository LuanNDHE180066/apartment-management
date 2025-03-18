/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package authentication;

import dao.AccountDAO;
import dao.ResidentDAO;
import dao.StaffDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.Resident;
import model.Staff;
import util.Util;

/**
 *
 * @author PC
 */
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Cookie[] cookies = request.getCookies();
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if ("rememberedUser".equals(cookie.getName())) {
//                    request.setAttribute("rememberedUser", cookie.getValue());
//                    break;
//                }
//            }
//        }
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String user = request.getParameter("username");
        String pass = request.getParameter("password");
        String checkrole = request.getParameter("role");
        int role;
        if (checkrole == null) {
            request.setAttribute("error", "Role is not allowed to be blank");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        } else {
            role = Integer.parseInt(checkrole);
        }
        AccountDAO dao = new AccountDAO();
        Account ac = dao.getAccountByUsernameandRole(user, role);
        if (user.isEmpty() || pass.isEmpty()) {
            request.setAttribute("error", "Username or Password is not allowed to be blank");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        if (ac == null) {
            request.setAttribute("error", "Username or Password is incorrect");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
        if (user.equalsIgnoreCase(ac.getUsername()) && Util.isCorrectPassword(pass, ac.getPassword())) {
            session.setAttribute("account", ac);
            String remember = request.getParameter("remember");
            if ("on".equals(remember)) {
                // Create and configure cookies for username and password
                Cookie cookieUser = new Cookie("rememberedUser", user);
                Cookie cookiePass = new Cookie("rememberedPass", pass);
                Cookie cookieRemember = new Cookie("remembered", "remember");
                Cookie cookieRole = new Cookie("rememberedRole", checkrole);
                cookieUser.setMaxAge(60 * 60 * 60);
                cookiePass.setMaxAge(60 * 60 * 60);
                cookieRemember.setMaxAge(60 * 60 * 60);
                cookieRole.setMaxAge(60 * 60 * 60);
                response.addCookie(cookieUser);
                response.addCookie(cookiePass);
                response.addCookie(cookieRemember);
                response.addCookie(cookieRole);
            } else {
                Cookie cookieUser = new Cookie("rememberedUser", user);
                Cookie cookiePass = new Cookie("rememberedPass", pass);
                Cookie cookieRemember = new Cookie("remembered", "remember");
                Cookie cookieRole = new Cookie("rememberedRole", checkrole);
                cookieUser.setMaxAge(0);
                cookiePass.setMaxAge(0);
                cookieRemember.setMaxAge(0);
                cookieRole.setMaxAge(0);
                response.addCookie(cookieUser);
                response.addCookie(cookiePass);
                response.addCookie(cookieRemember);
                response.addCookie(cookieRole);
            }
            if (ac.getActive() == 2) {
                response.sendRedirect("changepassword.jsp");

            } else {
                response.sendRedirect("index.jsp");
            }
        } else {
            request.setAttribute("error", "Username or Password is incorrect");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
        //Lấy thông tin hiển thị ở index
        if (role == 1 || role ==6) {
            ResidentDAO rd = new ResidentDAO();
            Resident re = rd.getById(ac.getpId());
            session.setAttribute("person", re);
        } else {
            StaffDAO sd = new StaffDAO();
            Staff staff = sd.getById(ac.getpId());
            session.setAttribute("person", staff);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
