/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.RequestChangeResidentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.RequestChangeResident;
import util.Util;

/**
 *
 * @author quang
 */
@WebServlet(name = "ViewChangeResidentRequest", urlPatterns = {"/view-change-resident-request"})
public class ViewChangeResidentRequest extends HttpServlet {

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
            out.println("<title>Servlet ViewChangeResidentRequest</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewChangeResidentRequest at " + request.getContextPath() + "</h1>");
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
        RequestChangeResidentDAO reDAO = new RequestChangeResidentDAO();
        List<RequestChangeResident> listChangeRequest = reDAO.getPendingChangeRequest();
        String page = request.getParameter("page");

        Util u = new Util();

        if (listChangeRequest.size() != 0) {
            int totalPage = u.getTotalPage(listChangeRequest, 10);
            listChangeRequest = u.getListPerPage(listChangeRequest, 10, page);
            request.setAttribute("listChangeRequest", listChangeRequest);
            request.setAttribute("currentPage", page == null ? "1" : page);
            request.setAttribute("totalPage", totalPage);
        } else {
            request.setAttribute("currentPage", page == null ? "1" : page);
            request.setAttribute("totalPage", 1);
        }

        
        request.getRequestDispatcher("viewPendingChangeResidentRequest.jsp").forward(request, response);

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
        processRequest(request, response);
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
