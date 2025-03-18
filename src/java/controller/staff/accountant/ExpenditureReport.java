/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.staff.accountant;

import dao.CompanyDAO;
import dao.ExpenditureDAO;
import dao.ExpenseCategoryDAO;
import dao.StaffDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Account;
import model.Company;
import model.Expenditure;
import model.ExpenseCategory;
import model.Staff;
import util.Util;

/**
 *
 * @author quang
 */
@WebServlet(name = "ExpenditureReport", urlPatterns = {"/expenditure-report"})
public class ExpenditureReport extends HttpServlet {

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
            out.println("<title>Servlet ExpenditureReport</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ExpenditureReport at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        Util u = new Util();
        ExpenseCategoryDAO daoEc = new ExpenseCategoryDAO();
        ExpenditureDAO edao = new ExpenditureDAO();

        CompanyDAO daoCp = new CompanyDAO();
        StaffDAO daoSt = new StaffDAO();
        String title = request.getParameter("title");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String category = request.getParameter("category");
        List<ExpenseCategory> categorylist = daoEc.getAllExpenseCategory();
        request.setAttribute("categorylist", categorylist);
        if (title == null) {
            title = "";
        }
        title = u.stringNomalize(title);
        if (startDate == null) {
            startDate = "";
        }
        if (endDate == null) {
            endDate = "";
        }
        if (category == null) {
            category = "";
        }
        List<Expenditure> listExpenditure = edao.getViewExpenditure(title, startDate, endDate, category);
        String page = request.getParameter("page");
        if (page == null) {
            page = "1";
        }

        int numberOfExpenditure = edao.getNumberOfExpenditureByApproveDateAndExpenseCategory(startDate, endDate, category);
        double totalFees = edao.getTotalFeesOfExpenditureByApproveDateAndExpenseCategory(startDate, endDate, category);

        List<Company> listCompany = daoCp.getAll();
        List<ExpenseCategory> listExpenseCategory = daoEc.getAllExpenseCategory();
        List<Staff> listAccountant = daoSt.getActiveStaffbyRole("3");
        List<Staff> listAdmin = daoSt.getActiveStaffbyRole("0");

        session.setAttribute("listCompany", listCompany);
        session.setAttribute("listExpenseCategory", listExpenseCategory);
        session.setAttribute("listAccountant", listAccountant);
        session.setAttribute("listAdmin", listAdmin);

        System.out.println("list hien ta" + listExpenditure);
        Account a = (Account) session.getAttribute("account");

        request.setAttribute("roleId", a.getRoleId());
        if (listExpenditure.size() != 0) {
            int totalPage = u.getTotalPage(listExpenditure, 5);
            listExpenditure = u.getListPerPage(listExpenditure, 5, page);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("currentPage", Integer.parseInt(page));
            request.setAttribute("listExpenditure", listExpenditure);
        } else {
            request.setAttribute("totalPage", 1);
            request.setAttribute("currentPage", 1);
            request.setAttribute("listExpenditure", null);
            request.setAttribute("message", "No result");
        }
        request.setAttribute("noe", numberOfExpenditure);
        request.setAttribute("totalFees", totalFees);
        request.getRequestDispatcher("reportExpenditure.jsp").forward(request, response);

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
