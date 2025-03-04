/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.admin;

import dao.ApartmentDAO;
import dao.RoomTypeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Apartment;
import model.RoomType;
import util.Util;

/**
 *
 * @author PC
 */
@WebServlet(name="ViewApartmantAdmin", urlPatterns={"/view-apartment-admin"})
public class ViewApartmantAdmin extends HttpServlet {
   
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
            out.println("<title>Servlet ViewApartmantAdmin</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewApartmantAdmin at " + request.getContextPath () + "</h1>");
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
        String floor = request.getParameter("floor");
        String filterType = request.getParameter("filterType");
        String filterStatus = request.getParameter("filterStatus");
        if (floor == null || floor.trim().isEmpty()) {
            floor = "";
        }
        if (filterType == null || filterType.trim().isEmpty()) {
            filterType = "";
        }
        if (filterStatus == null || filterStatus.trim().isEmpty()) {
            filterStatus = "";
        }
        RoomTypeDAO rdao = new RoomTypeDAO();
        List<RoomType> types = rdao.getAll();
        ApartmentDAO dao = new ApartmentDAO();
        List<Apartment> apartmentes = dao.getViewApartment(floor, filterType, filterStatus);
        request.setAttribute("floor", floor);
        request.setAttribute("filterType", filterType);
        request.setAttribute("filterStatus", filterStatus);
        request.setAttribute("types", types);
        String page = request.getParameter("page");
        if (page == null) {
            page = "1";
        }
        Util u = new Util();
        int totalPage = u.getTotalPage(apartmentes, 10);

        if (apartmentes.size() != 0) {
            apartmentes = u.getListPerPage(apartmentes, 10, page);
            request.setAttribute("apartmentes", apartmentes);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("currentPage", Integer.parseInt(page));
            request.setAttribute("isFilter", "true");
        } else {
            request.setAttribute("totalPage", 1);
            request.setAttribute("currentPage", 1);
            request.setAttribute("message", "No result");
        }        
        request.getRequestDispatcher("viewapartmentadmin.jsp").forward(request, response);
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
