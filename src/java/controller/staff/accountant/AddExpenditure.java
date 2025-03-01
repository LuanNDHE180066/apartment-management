/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.staff.accountant;

import dao.CompanyDAO;
import dao.ExpenditureDAO;
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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import model.Account;
import model.Company;
import model.ExpenseCategory;
import model.HistoryExpenditure;
import model.Staff;

/**
 *
 * @author quang
 */
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
        Account a = (Account) session.getAttribute("account");
        StaffDAO daoSt = new StaffDAO();
        request.setAttribute("staff", daoSt.getById(a.getpId()));
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
        String totalPrice_raw = request.getParameter("totalPrice");
        String title = request.getParameter("title");
        String approveDate_raw = request.getParameter("approveDate");
        String paymentDate_raw = request.getParameter("paymentDate");
        String categoryId_raw = request.getParameter("category");
        String companyId = request.getParameter("company");
        String chiefAccountantId = request.getParameter("chiefAccountant");
        String AdminId = request.getParameter("admin");
        String note = request.getParameter("note");
        String createdById = request.getParameter("createdBy");

        HistoryExpenditureDAO daoHe = new HistoryExpenditureDAO();
        ExpenseCategoryDAO daoEx = new ExpenseCategoryDAO();
        CompanyDAO daoCp = new CompanyDAO();
        StaffDAO daoSt = new StaffDAO();
        ExpenditureDAO daoE = new ExpenditureDAO();

        if (title.trim().isBlank()) {
            request.setAttribute("message", "Title can not be blank");
            request.setAttribute("status", "false");
            request.getRequestDispatcher("addExpenditure.jsp").forward(request, response);
            return;
        }

        if (approveDate_raw.isBlank() || paymentDate_raw.isBlank()) {
            request.setAttribute("message", "Approve date and payment date can not be blank");
            request.setAttribute("status", "false");
            request.getRequestDispatcher("addExpenditure.jsp").forward(request, response);
            return;
        }

        if (note.trim().isBlank()) {
            request.setAttribute("message", "Note can not be blank");
            request.setAttribute("status", "false");
            request.getRequestDispatcher("addExpenditure.jsp").forward(request, response);
            return;
        }
//        PrintWriter out = response.getWriter();
//        out.print(" admin:" + AdminId + "  chief:" + chiefAccountantId +"Staff creat"+ createdById +  "cate: "+categoryId_raw);
        LocalDateTime lc = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDate = lc.format(format);
        try {
            int categoryId = Integer.parseInt(categoryId_raw);
            String eid = daoE.generateExpenditureId();
            float totalPrice = Float.parseFloat(totalPrice_raw);
            Company company = daoCp.getById(companyId);
            Staff chiefAccountant = daoSt.getById(chiefAccountantId);
            Staff currentAdmin = daoSt.getById(AdminId);
            Staff createBy = daoSt.getById(createdById);
            ExpenseCategory ex = daoEx.getExpenseCategoryById(categoryId);
            String action = "Insert";
            String modifiedDate = formattedDate;
            String createdDate = formattedDate;
  
//            out.print(currentAdmin.getName() + " " + AdminId + " " + chiefAccountantId + createdById + categoryId_raw);
            HistoryExpenditure he = new HistoryExpenditure(eid, title, 0,
                    0, approveDate_raw, paymentDate_raw, totalPrice, note,
                    ex, company, createBy, chiefAccountant, currentAdmin, action,
                    modifiedDate, createBy, createdDate);

//            out.print(currentAdmin.getName() + " " + AdminId + " " + chiefAccountantId + createdById + categoryId_raw);
            if (!daoHe.addNewHistoryExpenditure(he)) {
                request.setAttribute("message", "Can not add expenditure");
                request.setAttribute("status", "false");
                request.getRequestDispatcher("addExpenditure.jsp").forward(request, response);
                return;
            } else {
                request.setAttribute("message", "Your expenditure has been successfully saved to the waiting list.");
                request.setAttribute("status", "true");
                request.getRequestDispatcher("addExpenditure.jsp").forward(request, response);
                return;
            }

        } catch (NumberFormatException e) {

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
