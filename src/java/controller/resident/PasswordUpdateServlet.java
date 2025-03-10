/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.resident;

import dao.AccountDAO;
import dao.ResidentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import util.Util;

/**
 *
 * @author thanh
 */
public class PasswordUpdateServlet extends HttpServlet {

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
            out.println("<title>Servlet PasswordUpdateServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PasswordUpdateServlet at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        Account account = (Account) session.getAttribute("account");
        String newpw = request.getParameter("newPassword").trim();
        String cfnewpw = request.getParameter("cfnewPassword").trim();
        ResidentDAO rd = new ResidentDAO();

        if (account.getActive() != 2) {

            String oldpw = request.getParameter("oldPassword").trim();
            if (!Util.isCorrectPassword(oldpw, account.getPassword())) {
                request.setAttribute("msg", "Old password is not correct");
                request.getRequestDispatcher("changepassword.jsp").forward(request, response);
                return;
            }
        }
        if (newpw.isBlank() || newpw.isEmpty()) {
            request.setAttribute("msg", "Password must be not a blank");
            request.getRequestDispatcher("changepassword.jsp").forward(request, response);
            return;
        }
        if (!newpw.equals(cfnewpw)) {
            request.setAttribute("msg", "New password is not match");
            request.getRequestDispatcher("changepassword.jsp").forward(request, response);
            return;
        }
        if (!Util.isCorrectFormatPassword(newpw)) {
            request.setAttribute("msg", "The password must have at least 6 characters, including at least 1 uppercase letter,"
                    + " 1 special character, and both letters and numbers.");
            request.getRequestDispatcher("changepassword.jsp").forward(request, response);
            return;
        }
        AccountDAO ad = new AccountDAO();
        ad.changePassword(account.getUsername(), newpw, account.getRoleId());
        if (account.getActive() == 2) {
            rd.editResidentStatus(account.getpId(), "1");
        }
//        rd.changPasswordById(account.getpId(), newpw);
        request.setAttribute("status", "true");
        request.setAttribute("message", "Password is changed");
        session.setAttribute("account", null);
        request.getRequestDispatcher("login.jsp").forward(request, response);
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
