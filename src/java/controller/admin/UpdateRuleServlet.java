/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.RuleDAO;
import dao.StaffDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import model.Rule;
import util.Util;

/**
 *
 * @author pc
 */
@WebServlet(name = "UpdateRuleServlet", urlPatterns = {"/update-rule"})
public class UpdateRuleServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdateRuleServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateRuleServlet at " + request.getContextPath() + "</h1>");
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
        String id = request.getParameter("id");

        if (id != null && !id.isEmpty()) {
            RuleDAO daoR = new RuleDAO();
            Rule rule = daoR.getById(id);

            if (rule != null) {
                request.setAttribute("rule", rule);
                request.getRequestDispatcher("updateRule.jsp").forward(request, response);
            } else {
                request.setAttribute("message", "Rule not found");
                request.setAttribute("status", "false");
                request.getRequestDispatcher("updateRule.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("message", "Invalid ID");
            request.setAttribute("status", "false");
            request.getRequestDispatcher("updateRule.jsp").forward(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String effectiveDateStr = request.getParameter("effectiveDate");
        String status = request.getParameter("status");

       
        if (id == null || title == null || description == null) {
            request.setAttribute("message", "Missing required fields!");
            request.setAttribute("status", "false");
            request.getRequestDispatcher("updateRule.jsp").forward(request, response);
            return;
        }

        RuleDAO daoR = new RuleDAO();
        Rule rule = daoR.getById(id);

        if (rule == null) {
            request.setAttribute("message", "Rule not found!");
            request.setAttribute("status", "false");
            request.getRequestDispatcher("updateRule.jsp").forward(request, response);
            return;
        }

        
        rule.setTitle(title);
        rule.setDescription(description);

       
        if (status != null) {
            rule.setStatus(status);
        } 

     
        if (effectiveDateStr != null && !effectiveDateStr.isEmpty()) {
            
               
                Date effectiveDate = Date.valueOf(effectiveDateStr);

                Date createDate = Date.valueOf(rule.getDate());
                if (!effectiveDate.after(createDate)) {
                    request.setAttribute("message", "Effective date must be after start date!");
                    request.setAttribute("status", "false");
                    request.getRequestDispatcher("updateRule.jsp").forward(request, response);
                    return;
                }

                rule.setEffectiveDate(effectiveDate.toString());
            } 

        boolean isUpdated = daoR.updateRule(rule);

        if (isUpdated) {
            request.setAttribute("message", "Update rule successfully");
            request.setAttribute("status", "true");
        } else {
            request.setAttribute("message", "Update rule failed");
            request.setAttribute("status", "false");
        }

        request.getRequestDispatcher("updateRule.jsp").forward(request, response);
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
