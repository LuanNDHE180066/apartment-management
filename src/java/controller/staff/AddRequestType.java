/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.staff;

import dao.RequestTypeDAO;
import dao.RoleDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Role;
import util.Util;

/**
 *
 * @author PC
 */
@WebServlet(name = "AddRequestType", urlPatterns = {"/add-request-type"})
public class AddRequestType extends HttpServlet {

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
            out.println("<title>Servlet AddRequestType</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddRequestType at " + request.getContextPath() + "</h1>");
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
        RoleDAO rd = new RoleDAO();
        List<Role> ls = rd.getAll();
        request.setAttribute("rolelist", ls);
        request.getRequestDispatcher("addrequesttype.jsp").forward(request, response);
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
        String name = Util.stringNomalize(request.getParameter("name"));
        String detail = Util.stringNomalize(request.getParameter("detail"));
        String destination = request.getParameter("role");
        if(name.isBlank()){
            request.setAttribute("status", false);
            request.setAttribute("message", "Name is not allow blank!");
            doGet(request, response);
            return;
        }
        if(detail.isBlank()){
            request.setAttribute("status", false);
            request.setAttribute("message", "Detail is not allow blank!");
            doGet(request, response);
            return;
        }
        RequestTypeDAO rtd = new RequestTypeDAO();
        if(rtd.checkExistedName(name)){
            request.setAttribute("status", false);
            request.setAttribute("message", "Name is existed!");
            doGet(request, response);
            return;
        }
        if (rtd.addRequestType(name, detail, destination)) {
            request.setAttribute("status", true);
            request.setAttribute("message", "Add request type successful!");
            doGet(request, response);
        } else {
            request.setAttribute("status", false);
            request.setAttribute("message", "Can not add request type!");
            doGet(request, response);
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
