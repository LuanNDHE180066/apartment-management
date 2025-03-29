/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.AdminDAO;
import dao.CompanyDAO;
import dao.RoleDAO;
import dao.StaffDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Company;
import model.Role;
import model.Staff;
import util.Util;

/**
 *
 * @author thanh
 */
@WebServlet(name = "ViewAllStaff", urlPatterns = {"/view-all-staff"})
public class ViewAllStaff extends HttpServlet {

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
            out.println("<title>Servlet ViewAllStaff</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewAllStaff at " + request.getContextPath() + "</h1>");
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
        AdminDAO ad = new AdminDAO();
        StaffDAO sd = new StaffDAO();
        Util u = new Util();

        String filterStatus_raw = request.getParameter("filterStatus");
        String searchName = request.getParameter("searchName");

        List<Staff> list = sd.getAll();

        if (filterStatus_raw != null && !filterStatus_raw.trim().isEmpty()) {
            int filterStatus = Integer.parseInt(filterStatus_raw);
            list = sd.getByStatus(filterStatus);
        }

        if (searchName != null && !searchName.trim().isEmpty()) {
            searchName = u.stringNomalize(searchName);
            list = sd.searchByName(list, searchName);
        }

        if (list.isEmpty()) {
            request.setAttribute("totalPage", 1);
            request.setAttribute("staffs", null);
            request.getRequestDispatcher("viewallstaff.jsp").forward(request, response);
            return;
        }

        String page_raw = request.getParameter("page");
        int page = 1;
        if (page_raw != null && !page_raw.trim().isEmpty()) {
            try {
                page = Integer.parseInt(page_raw.trim());
            } catch (NumberFormatException e) {
                page = 1;
            }
        }

        int totalPage = u.getTotalPage(list, 5);
        list = u.getListPerPage(list, 5, String.valueOf(page));

        request.setAttribute("totalPage", totalPage);
        request.setAttribute("currentPage", page);
        request.setAttribute("staffs", list);
        request.setAttribute("filterStatus", filterStatus_raw);
        request.setAttribute("searchName", searchName);

        request.getRequestDispatcher("viewallstaff.jsp").forward(request, response);

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
        doGet(request, response);
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
