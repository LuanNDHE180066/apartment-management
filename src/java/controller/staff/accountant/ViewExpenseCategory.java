/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.staff.accountant;

import dao.ExpenseCategoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.ExpenseCategory;
import util.Util;

/**
 *
 * @author quang
 */
@WebServlet(name = "ViewExpenseCategory", urlPatterns = {"/view-expense-category"})
public class ViewExpenseCategory extends HttpServlet {

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
            out.println("<title>Servlet ViewExpenseCategory</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewExpenseCategory at " + request.getContextPath() + "</h1>");
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
        ExpenseCategoryDAO daoEx = new ExpenseCategoryDAO();
        Util u = new Util();
        String page = request.getParameter("page");

        String search = request.getParameter("search");
        if (search == null) {
            search = "";
        }
        search = u.stringNomalize(search);
        int numPerPage = 3;
        List<ExpenseCategory> listExpenseCategory = daoEx.filterExpenseCategory(search);

        List<ExpenseCategory> listPerPage = u.getListPerPage(listExpenseCategory, numPerPage, page);

        request.setAttribute("totalPage", u.getTotalPage(listExpenseCategory, numPerPage));
        request.setAttribute("listExpenseCategory", listPerPage);
        request.setAttribute("currentPage", page == null ? 1 : Integer.parseInt(page));
        request.getRequestDispatcher("viewexpensecategory.jsp").forward(request, response);
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
        processRequest(request, response);
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
