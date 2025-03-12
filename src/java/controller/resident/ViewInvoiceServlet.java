/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.resident;

import dao.ApartmentDAO;
import dao.InvoiceDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import model.Account;
import model.Apartment;
import model.Invoice;

/**
 *
 * @author thanh
 */
@WebServlet(name="ViewInvoiceServlet", urlPatterns={"/view-invoice-resident"})
public class ViewInvoiceServlet extends HttpServlet {
   private static final int numberPerPage = 1;
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
            out.println("<title>Servlet ViewInvoiceServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewInvoiceServlet at " + request.getContextPath () + "</h1>");
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
        String fromDate;
        if (request.getParameter("from") == null || request.getParameter("from").isEmpty()) {
            fromDate = java.sql.Date.valueOf(LocalDate.EPOCH).toString();
        } else {
            fromDate = request.getParameter("from");
        }
        String toDate;
        if (request.getParameter("to") == null || request.getParameter("to").isEmpty()) {
            toDate = java.sql.Date.valueOf(LocalDate.now()).toString();
        } else {
            toDate = request.getParameter("to");
        }
        //check nếu to nhỏ hơn from
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date from = sf.parse(fromDate);
            Date to = sf.parse(toDate);
            if (from.after(to)) {
                String temp = toDate;
                toDate = fromDate;
                fromDate = temp;
            }
        } catch (ParseException ex) {
            System.out.println(ex);
        }
        
        
        HttpSession session = request.getSession();
        String rid = ((Account)session.getAttribute("account")).getpId();
        InvoiceDAO ivd= new InvoiceDAO();
        List<Invoice> fullInvoice = ivd.searchByTimeAndResidentId(fromDate,toDate,rid);
        List<Invoice> outputPaidList = ivd.getByPaing(fullInvoice, numberPerPage, 1);
        
        //các giá trị gửi lên fe
        request.setAttribute("endPage", ivd.getMaxPage(fullInvoice, numberPerPage));
        request.setAttribute("selectedPage", 1);
        request.setAttribute("usingFrom", fromDate);
        request.setAttribute("usingTo", toDate);
        request.setAttribute("invoiceList", outputPaidList);
        request.getRequestDispatcher("viewinvoice-byresident.jsp").forward(request, response);
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
         String page_raw = request.getParameter("page");
        String fromDate = request.getParameter("from");
        String toDate = request.getParameter("to");
        InvoiceDAO ivd = new InvoiceDAO();
         
        HttpSession session = request.getSession();
        String rid = ((Account)session.getAttribute("account")).getpId();
        List<Invoice> full = ivd.searchByTimeAndResidentId(fromDate, toDate, rid );
        List<Invoice> outputList = ivd.getByPaing(full, numberPerPage, Integer.parseInt(page_raw));
        //xét các giá trị
        request.setAttribute("invoiceList", outputList);
        request.setAttribute("usingFrom", fromDate);
        request.setAttribute("usingTo", toDate);
        request.setAttribute("selectedPage",Integer.parseInt(page_raw) );
        request.setAttribute("endPage", ivd.getMaxPage(full, numberPerPage));
        
        request.getRequestDispatcher("viewinvoice-byresident.jsp").forward(request, response);
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
