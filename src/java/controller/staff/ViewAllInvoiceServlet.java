/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.staff;

import dao.ApartmentDAO;
import dao.InvoiceDAO;
import dao.ResidentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Apartment;
import model.Invoice;

/**
 *
 * @author thanh
 */
@WebServlet(name = "ViewAllInvoiceServlet", urlPatterns = {"/view-invoice-staff"})
public class ViewAllInvoiceServlet extends HttpServlet {

    private static final int numberPerPage = 5;

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
            out.println("<title>Servlet ViewAllInvoiceServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewAllInvoiceServlet at " + request.getContextPath() + "</h1>");
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
        InvoiceDAO ivd = new InvoiceDAO();
        ApartmentDAO ad = new ApartmentDAO();
        List<Apartment> listApartment = ad.getAll();
        //fill những phần đã có
        request.setAttribute("nonPaidList", ivd.getNonPaidInvoice());
        request.setAttribute("apartmentList", listApartment);

        //data từ front end
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
        String apartmentSelected;
        if (request.getParameter("apartmentSelected") == null) {
            apartmentSelected = "all";
        } else {
            apartmentSelected = request.getParameter("apartmentSelected");
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
//        System.out.println(fromDate+"|||s||||||"+toDate);

        request.setAttribute("usingApartment", apartmentSelected);
        request.setAttribute("usingFrom", fromDate);
        request.setAttribute("usingTo", toDate);

        //paging
        List<Invoice> fullPaidList = ivd.searchByTimeAndApartment(fromDate, toDate, apartmentSelected);
        List<Invoice> outputPaidList = ivd.getByPaing(fullPaidList, numberPerPage, 1);
        request.setAttribute("endPage", ivd.getMaxPage(fullPaidList, numberPerPage));
        request.setAttribute("selectedPage", 1);

        request.setAttribute("paidList", outputPaidList);
        request.getRequestDispatcher("viewallinvoice-bystaff.jsp").forward(request, response);
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
        String page_raw = request.getParameter("page");
        String fromDate = request.getParameter("from");
        String toDate = request.getParameter("to");
        String apartmentSelected = request.getParameter("apartmentSelected");
        InvoiceDAO ivd = new InvoiceDAO();
        List<Invoice> full = ivd.searchByTimeAndApartment(fromDate, toDate, apartmentSelected);
        List<Invoice> outputPaidList = ivd.getByPaing(full, numberPerPage, Integer.parseInt(page_raw));
        //xét các giá trị
        request.setAttribute("paidList", outputPaidList);
        ApartmentDAO ad = new ApartmentDAO();
        List<Apartment> listApartment = ad.getAll();
        request.setAttribute("nonPaidList", ivd.getNonPaidInvoice());
        request.setAttribute("apartmentList", listApartment);
        request.setAttribute("usingApartment", apartmentSelected);
        request.setAttribute("usingFrom", fromDate);
        request.setAttribute("usingTo", toDate);
        request.setAttribute("endPage", ivd.getMaxPage(full, numberPerPage));
        
        request.getRequestDispatcher("viewallinvoice-bystaff.jsp").forward(request, response);
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
