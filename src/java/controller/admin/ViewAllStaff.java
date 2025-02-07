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
        List<Staff> list = ad.getAllStaffExceptAdmin();
        HttpSession session = request.getSession();
        Util u = new Util();
//        if(session.getAttribute("staffs")==null){
//            list = ad.getAllStaffExceptAdmin();
//            session.setAttribute("staffs", list);
//        }
//        else{
//            list = (List<Staff>) session.getAttribute("staffs");
//        }
        StaffDAO sd = new StaffDAO();
        String filterStatus_raw = request.getParameter("filterStatus");
        if (filterStatus_raw != null) {
            int filterStatus = Integer.parseInt(filterStatus_raw);
            list = sd.getByStatus(filterStatus);
            if (list.size() == 0) {
                request.getRequestDispatcher("viewallstaff.jsp").forward(request, response);
                return;
            }
            request.setAttribute("staffs", list);
        }
        String searchName = request.getParameter("searchName");
        if (searchName != null) {
            searchName = u.stringNomalize(searchName);
            list = sd.searchByName(list, searchName);
            if (list.size() == 0) {
                request.getRequestDispatcher("viewallstaff.jsp").forward(request, response);
                return;
            }
            request.setAttribute("staffs", list);
        }
        String page = request.getParameter("page");
        if (page == null) {
            page = "1";
        }

        int totalPage = u.getTotalPage(list, 3);
        list = u.getListPerPage(list, 3, page);
        RoleDAO daoR = new RoleDAO();
        CompanyDAO daoCp = new CompanyDAO();
        List<Company> listCompany = daoCp.getAll();
        List<Role> listRole = daoR.getAllExcludeResident();
        session.setAttribute("listCompany", listCompany);
        session.setAttribute("listRole", listRole);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("currentPage", Integer.parseInt(page));
        request.setAttribute("staffs", list);
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
