/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package authentication;

import dao.AccountDAO;
import dao.TokenForgetPassDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.SendEmail;
import model.TokenForgetPassword;
import util.Util;

/**
 *
 * @author admin1711
 */
public class ResetPassServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ResetPassServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ResetPassServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String token = request.getParameter("token");
        TokenForgetPassDAO daoT = new TokenForgetPassDAO();
        HttpSession session = request.getSession();
        if (token != null) {
            SendEmail send = new SendEmail();
            TokenForgetPassword newToken = daoT.getTokenPassword(token);
            if (newToken == null) {
                request.setAttribute("message", "Token không khả dụng!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }

            if (newToken.isIsUsed()) {
                request.setAttribute("message", "Token đã bị sử dụng!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }
            if (send.isExpired(newToken.getExpireTime())) {
                request.setAttribute("message", "Token đã quá hạn!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }

            Account a = newToken.getAcc();

            session.setAttribute("username", a.getUsername());
            session.setAttribute("token", newToken.getToken());
            request.getRequestDispatcher("resetpassword.jsp").forward(request, response);
            return;
        } else {
            request.setAttribute("message", "Token không đúng!");
            request.getRequestDispatcher("requestpassword.jsp");
            return;
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("new_password");
        String confirmPassword = request.getParameter("confirm_password");
        AccountDAO daoA = new AccountDAO();
        TokenForgetPassDAO daoT = new TokenForgetPassDAO();
        String token = daoT.getTokenBypId(daoA.getAccountByUsername(username).getpId());

        if (password.trim().isEmpty() || confirmPassword.trim().isEmpty()) {
            request.setAttribute("status", "false");
            request.setAttribute("message", "Password can not be empty");
            request.getRequestDispatcher("resetpassword.jsp").forward(request, response);
            return;
        }

        if (!confirmPassword.equals(password)) {
            request.setAttribute("status", "false");
            request.setAttribute("message", "Password does not match");
            request.getRequestDispatcher("resetpassword.jsp").forward(request, response);
            return;
        }

        if (!Util.isCorrectFormatPassword(password)) {
            request.setAttribute("status", "false");
            request.setAttribute("message", "The password must have at least 6 characters, including at least 1 uppercase letter,"
                    + " 1 special character, and both letters and numbers.");
            request.getRequestDispatcher("resetpassword.jsp").forward(request, response);
            return;
        } else {
            daoA.changePassword(username, password, daoA.getAccountByUsername(username).getRoleId());
            daoT.updateStatusToken(token);
            request.setAttribute("status", "true");
            session.setAttribute("username", null);
            request.setAttribute("message", "Password is changed");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
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
