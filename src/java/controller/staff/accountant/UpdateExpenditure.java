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
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import model.Account;
import model.Company;
import model.ExpenseCategory;
import model.HistoryExpenditure;
import model.SendEmail;
import model.Staff;
import util.Util;

/**
 *
 * @author quang
 */
@WebServlet(name = "UpdateExpenditure", urlPatterns = {"/update-expenditure"})
public class UpdateExpenditure extends HttpServlet {

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
            out.println("<title>Servlet UpdateExpenditure</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateExpenditure at " + request.getContextPath() + "</h1>");
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
        String id = request.getParameter("id");
        ExpenditureDAO daoE = new ExpenditureDAO();
        ExpenseCategoryDAO daoEc = new ExpenseCategoryDAO();
        CompanyDAO daoCp = new CompanyDAO();
        StaffDAO daoSt = new StaffDAO();

        request.setAttribute("expenditure", daoE.getExpenditureById(id));
        request.getRequestDispatcher("updateExpenditure.jsp").forward(request, response);
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
        //id, totalPrice, title, approveDate, paymentDate, category,company, chiefAccountant, admin, note
        HttpSession session = request.getSession();
        Account a = (Account) session.getAttribute("account");
        String eid = request.getParameter("id");
        String totalPrice_raw = request.getParameter("totalPrice");
        String title = request.getParameter("title");
        String approveDate_raw = request.getParameter("approveDate");
        String paymentDate_raw = request.getParameter("paymentDate");
        String categoryId = request.getParameter("category");
        String companyId = request.getParameter("company");
        String chiefAccountantId = request.getParameter("chiefAccountant");
        String AdminId = request.getParameter("admin");
        String note = request.getParameter("note");
        String createdStaffId = request.getParameter("createdStaff");

        HistoryExpenditureDAO daoE = new HistoryExpenditureDAO();
        ExpenseCategoryDAO daoEx = new ExpenseCategoryDAO();
        CompanyDAO daoCp = new CompanyDAO();
        StaffDAO daoSt = new StaffDAO();
        ExpenditureDAO daoExpen = new ExpenditureDAO();
        Util u = new Util();

        LocalDateTime lc = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDate = lc.format(format);

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

        note = u.stringNomalize(note);
        title = u.stringNomalize(title);
        try {
            float totalPrice = Float.parseFloat(totalPrice_raw);
            HistoryExpenditure he = new HistoryExpenditure(eid, title, 0, 0, approveDate_raw, paymentDate_raw,
                    totalPrice, note, daoEx.getExpenseCategoryById(Integer.parseInt(categoryId)),
                    daoCp.getById(companyId), daoSt.getById(createdStaffId), daoSt.getById(chiefAccountantId),
                    daoSt.getById(AdminId),
                    "Update", formattedDate, daoSt.getById(a.getpId()), daoExpen.getExpenditureById(eid).getCreatedDate());
            if (!daoE.addNewHistoryExpenditure(he)) {
                request.setAttribute("message", "Can not create update expenditure request");
                request.setAttribute("status", "false");
                request.getRequestDispatcher("updateExpenditure.jsp").forward(request, response);
                return;
            } else {
                SendEmail send = new SendEmail();
                send.sendEmail(he.getChiefAccountantId().getEmail(), daoSt.getById(he.getCreatedStaff().getName()) + " has updated an expenditure " + he.getTitle(),
                        "Please check and confirm the expenditure" + he.getTitle());
                send.sendEmail(he.getCurrentAdmin().getEmail(), daoSt.getById(he.getCreatedStaff().getName()) + " has updated an expenditure " + he.getTitle(),
                        "Please check and confirm the expenditure : " + he.getTitle());
                request.setAttribute("message", "Your update expenditure request has been successfully saved to the waiting list.");
                request.setAttribute("status", "true");
                request.getRequestDispatcher("updateExpenditure.jsp").forward(request, response);
                return;
            }

        } catch (NumberFormatException e) {
            System.out.println(e);
        }

//        LocalDate birthDate = LocalDate.parse(dob);
//        LocalDate today = LocalDate.now();
//        if (Period.between(birthDate, today).getYears() < 18) {
//            request.setAttribute("status", "false");
//            request.setAttribute("message", "Staff must be at least 18 years old.");
//            request.getRequestDispatcher("updateStaffInfor.jsp").forward(request, response);
//            return;
//        }
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
