/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.staff;

import dao.CompanyDAO;
import dao.ExpenditureDAO;
import dao.StaffDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import model.Company;
import model.Expenditure;
import model.Staff;

/**
 *
 * @author pc
 */
@WebServlet(name = "AddExpenditure", urlPatterns = {"/add-new-expenditure"})
public class AddExpenditure extends HttpServlet {

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
            out.println("<title>Servlet AddExpenditure</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddExpenditure at " + request.getContextPath() + "</h1>");
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
        CompanyDAO cpd = new CompanyDAO();
        List<Company> listcompany = cpd.getAll();
        session.setAttribute("listcompany", listcompany);
        request.getRequestDispatcher("addExpenditure.jsp").forward(request, response);
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
        String note = request.getParameter("note");
        String category = request.getParameter("category");
        String amount_raw = request.getParameter("amount");
        String price_raw = request.getParameter("price");
        String approdate = request.getParameter("approdate");
        String paydate = request.getParameter("paydate");
        String company = request.getParameter("company");
        String sid = request.getParameter("sid");


        if (note == null || amount_raw == null || price_raw == null
                || approdate == null || paydate == null
                || note.trim().isEmpty() || amount_raw.trim().isEmpty() || price_raw.trim().isEmpty()
                || approdate.trim().isEmpty() || paydate.trim().isEmpty()) {
            request.setAttribute("message", "Please fill in all fields.");
            request.getRequestDispatcher("addExpenditure.jsp").forward(request, response);
            return;
        }
        int amount;
        float price;
        try {
            amount = Integer.parseInt(amount_raw);
            price = Float.parseFloat(price_raw);

            if (amount <= 0 || price <= 0) {
                request.setAttribute("message", "Amount and price must be positive numbers.");
                request.getRequestDispatcher("addExpenditure.jsp").forward(request, response);
                return;
            }
        } catch (NumberFormatException e) {
            request.setAttribute("message", "Invalid amount or price. Please enter valid numbers.");
            request.getRequestDispatcher("addExpenditure.jsp").forward(request, response);
            return;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            Date approvedDate = sdf.parse(approdate);
            Date paymentDate = sdf.parse(paydate);

            if (paymentDate.before(approvedDate)) {
                request.setAttribute("message", "Payment date cannot be before approved date.");
                request.getRequestDispatcher("addExpenditure.jsp").forward(request, response);
                return;
            }
        } catch (Exception e) {
            request.setAttribute("message", "Invalid date format.");
            request.getRequestDispatcher("addExpenditure.jsp").forward(request, response);
            return;
        }
        CompanyDAO cpd = new CompanyDAO();
        StaffDAO std = new StaffDAO();
        ExpenditureDAO expDao = new ExpenditureDAO();

        Expenditure expen = new Expenditure(note,category,amount,price,approdate,paydate,cpd.getById(company),std.getById(sid));
        if (expDao.addExpen(expen)) {
            request.setAttribute("message", "Add new expenditure successfully");
        } else {
            request.setAttribute("message", "Failed to add expenditure. Please try again.");
        }
        request.getRequestDispatcher("addExpenditure.jsp").forward(request, response);
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
