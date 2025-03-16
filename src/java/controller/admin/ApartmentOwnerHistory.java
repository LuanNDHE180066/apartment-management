/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.admin;

import dao.LivingApartmentDAO;
import dao.OwnerApartmentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.LivingApartment;
import model.OwnerApartment;
import util.Util;

/**
 *
 * @author quang
 */
@WebServlet(name="ApartmentOwnerHistory", urlPatterns={"/apartment-owner-history"})
public class ApartmentOwnerHistory extends HttpServlet {
   
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
            out.println("<title>Servlet ApartmentOwnerHistory</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ApartmentOwnerHistory at " + request.getContextPath () + "</h1>");
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
        String aid = request.getParameter("aid");
        String page = request.getParameter("page");
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        OwnerApartmentDAO od = new OwnerApartmentDAO();
        if (startDate == null) {
            startDate = "";
        }
        if (endDate == null) {
            endDate = "";
        }
        System.out.println( startDate + "     x    " +endDate);
        
        List<OwnerApartment> historyOfOwner = od.getByApartmentID(aid,startDate, endDate);
        Util u = new Util();
        if (historyOfOwner.size() != 0) {
            int totalPage = u.getTotalPage(historyOfOwner, 5);
            historyOfOwner = u.getListPerPage(historyOfOwner, 5, page);
            request.setAttribute("currentPage", page != null ? page : "1");
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("historyOfOwner", historyOfOwner);
            request.getRequestDispatcher("historyownerapartment.jsp").forward(request, response);
            return;
        } else{
            request.setAttribute("currentPage", 1);
            request.setAttribute("totalPage", 1);
            request.getRequestDispatcher("historyownerapartment.jsp").forward(request, response);
        }
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
        processRequest(request, response);
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
