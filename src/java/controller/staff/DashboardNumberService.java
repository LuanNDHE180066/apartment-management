/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.staff;

import dao.ResidentDAO;
import dao.ServiceDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;

/**
 *
 * @author thanh
 */
@WebServlet(name = "DashboardNumberService", urlPatterns = {"/dashboard-number-service"})
public class DashboardNumberService extends HttpServlet {

    /**
     *
     * @author quang
     */
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
            out.println("<title>Servlet DashboardNumberService</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DashboardNumberService at " + request.getContextPath() + "</h1>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ApartmentOwnerHistory at " + request.getContextPath() + "</h1>");
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
         int year;
        String sid;
        if(request.getParameter("year")==null && request.getParameter("serviceId") ==null ){
            year = LocalDate.now().getYear();
            ServiceDAO sd = new ServiceDAO();
            sid =sd.getAll().get(0).getName();
        }
        else{
            doPost(request, response);
            return;
        }
        ResidentDAO rd = new ResidentDAO();
        request.setAttribute("startYear", rd.getStartYear());
        request.setAttribute("endYear", LocalDate.now().getYear());
        request.setAttribute("currentYear", year);
        request.setAttribute("serviceName", sid);
        ServiceDAO sd = new ServiceDAO();
        request.setAttribute("sv", sd.getAll());
        request.setAttribute("data", sd.getNumberUsedAllMonth(year, sid));
        request.setAttribute("screen", 3);
        request.getRequestDispatcher("dashboardinvoice.jsp").forward(request, response);
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
        int year = Integer.parseInt(request.getParameter("year"));
        String serviceId = request.getParameter("serviceName");
        ResidentDAO rd = new ResidentDAO();
        request.setAttribute("startYear", rd.getStartYear());
        request.setAttribute("endYear", LocalDate.now().getYear());
        request.setAttribute("currentYear", year);
        request.setAttribute("serviceName", serviceId);
        ServiceDAO sd = new ServiceDAO();
        request.setAttribute("sv", sd.getAll());
        request.setAttribute("screen", 3);
        request.setAttribute("data", sd.getNumberUsedAllMonth(year, serviceId));
        request.getRequestDispatcher("dashboardinvoice.jsp").forward(request, response);
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
