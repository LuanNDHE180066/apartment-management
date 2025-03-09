/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.RuleDAO;
import dao.StaffDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import model.Account;
import model.Rule;
import model.Staff;

/**
 *
 * @author quang
 */
@WebServlet(name = "AddNewRuleServlet", urlPatterns = {"/add-new-rule"})
public class AddNewRuleServlet extends HttpServlet {

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
            out.println("<title>Servlet AddNewRuleServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddNewRuleServlet at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("addnewrule.jsp").forward(request, response);

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
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String effectiveDate = request.getParameter("effectiveDate");
        Account s = (Account) session.getAttribute("account");
        StaffDAO daoS = new StaffDAO();

        RuleDAO daoR = new RuleDAO();
        Date sqlDate = new Date(System.currentTimeMillis());
        String status=Date.valueOf(effectiveDate)==sqlDate?"1":"0";
        Rule r = new Rule(title, description, sqlDate.toString(), effectiveDate, status, daoS.getById(s.getpId()));

        if (daoR.insertRule(r)) {
            request.setAttribute("message", "Add new rule successfully");
            request.setAttribute("status", "true");
            request.getRequestDispatcher("addnewrule.jsp").forward(request, response);
            return;
        } else {
            request.setAttribute("message", "Add new rule failed");
            request.setAttribute("status", "false");
            request.getRequestDispatcher("addnewrule.jsp").forward(request, response);
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
