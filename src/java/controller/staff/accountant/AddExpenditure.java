/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.staff.accountant;

import dao.CompanyDAO;
import dao.ExpenseCategoryDAO;
import dao.HistoryExpenditureDAO;
import dao.StaffDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author quang
 */
public class AddExpenditure extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<h1>Servlet AddExpenditure at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");
        StaffDAO daoSt = new StaffDAO();
        request.setAttribute("staff", daoSt.getById(a.getpId()));
        request.getRequestDispatcher("addExpenditure.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
         String eid = request.getParameter("id");
        String totalPrice_raw = request.getParameter("totalPrice");
        String title = request.getParameter("title");
        String approveDate_raw = request.getParameter("approveDate");
        String paymentDate_raw = request.getParameter("paymentDate");
        String categoryId = request.getParameter("category");
        String companyId = request.getParameter("company");
        String chiefAccountantId = request.getParameter("chiefAccountant");
        String AdminId = request.getParameter("Admin");
        String note = request.getParameter("note");

        HistoryExpenditureDAO daoE = new HistoryExpenditureDAO();
        ExpenseCategoryDAO daoEx = new ExpenseCategoryDAO();
        CompanyDAO daoCp = new CompanyDAO();
        StaffDAO daoSt = new StaffDAO();

        if (title.trim().isBlank()) {
            request.setAttribute("message", "Title can not be blank");
            request.setAttribute("status", "false");
            request.setAttribute("expenditure", daoE.getExpenditureById(eid));
            request.getRequestDispatcher("updateExpenditure").forward(request, response);
            return;
        }

        if (approveDate_raw.isBlank() || paymentDate_raw.isBlank()) {
            request.setAttribute("message", "Approve date and payment date can not be blank");
            request.setAttribute("status", "false");
            request.setAttribute("expenditure", daoE.getExpenditureById(eid));
            request.getRequestDispatcher("updateExpenditure").forward(request, response);
            return;
        }
        
        if (note.trim().isBlank()) {
            request.setAttribute("message", "Note can not be blank");
            request.setAttribute("status", "false");
            request.setAttribute("expenditure", daoE.getExpenditureById(eid));
            request.getRequestDispatcher("updateExpenditure").forward(request, response);
            return;
        }
        
        try{
            float totalPrice = Float.parseFloat(totalPrice_raw);
            
        }catch(NumberFormatException e){
            System.out.println(e);
        }ưdasd
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
