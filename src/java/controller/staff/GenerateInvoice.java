/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.staff;

import dao.InvoiceDAO;
import dao.LivingApartmentDAO;
import dao.MonthlyServiceDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;
import java.time.YearMonth;
import model.Account;
import model.SendEmail;

/**
 *
 * @author thanh
 */
@WebServlet(name = "GenerateInvoice", urlPatterns = {"/generate-invoice-staff"})
public class GenerateInvoice extends HttpServlet {

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
            out.println("<title>Servlet GenerateInvoice</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GenerateInvoice at " + request.getContextPath() + "</h1>");
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
        if (request.getParameter("method") != null) {
            this.doPost(request, response);
            return;
        }
        HttpSession session = request.getSession();
        InvoiceDAO ivd = new InvoiceDAO();
//          YearMonth yearMonth = YearMonth.now();
//        LocalDate date = yearMonth.atEndOfMonth();
//        if(!LocalDate.now().equals(date) || ivd.isCreatedInvoice(Date.valueOf(LocalDate.now()))){
//            response.sendRedirect("view-apartmentservice-staff");
//            return;
//        }
        LivingApartmentDAO ld = new LivingApartmentDAO();
        MonthlyServiceDAO md = new MonthlyServiceDAO();
        ivd.generateInvoice();
        ivd.createNewsNotifyInvoice(((Account)session.getAttribute("account")).getpId());
//        SendEmail sendEmail = new SendEmail();
//        sendEmail.sendEmailInvoiceToAll(ld.getEmailInvoicesActiveResident());
        md.resetUsage();
        response.sendRedirect("view-invoice-staff");
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
        InvoiceDAO ivd = new InvoiceDAO();
        SendEmail sendEmail = new SendEmail();
        sendEmail.sendEmailInvoiceDebtToAll(ivd.getEmailInvoiceDebt());
        response.sendRedirect("view-invoice-staff");
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
