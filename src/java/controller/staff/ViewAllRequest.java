/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.staff;

import dao.RequestDAO;
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
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Request;
import model.Role;
import model.Staff;
import util.Util;

/**
 *
 * @author PC
 */
@WebServlet(name = "ViewAllRequest", urlPatterns = {"/view-all-request"})
public class ViewAllRequest extends HttpServlet {

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
            out.println("<title>Servlet ViewAllRequest</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewAllRequest at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        StaffDAO sd = new StaffDAO();
        List<Staff> list4 = sd.getStaffbyRole("4");
        List<Staff> list5 = sd.getStaffbyRole("5");
        request.setAttribute("engineer", list4);
        request.setAttribute("environmental", list5);
        RequestDAO rd = new RequestDAO();
        List<Request> list = null;
        RoleDAO dao = new RoleDAO();
        List<Role> ls = new ArrayList<>();
        ls = dao.getAll();
        request.setAttribute("rolelist", ls);
        String filterRoles_raw = request.getParameter("filterRoles");
        Account ac = (Account) session.getAttribute("account");
        if (ac.getRoleId() == 2) {
            list = rd.getAll();
        } else if (ac.getRoleId() > 2) {
            list = rd.getRequestByRolesAndPid(ac.getRoleId(),ac.getpId());
        }
        session.setAttribute("requestes", list);
        if (ac.getRoleId() == 2) {
            if (null != filterRoles_raw && !filterRoles_raw.isBlank()) {
                int filterRoles = Integer.parseInt(filterRoles_raw);
                list = rd.getByRoles(list, filterRoles);
                if (list.size() == 0) {
                    request.getRequestDispatcher("viewallrequest.jsp").forward(request, response);
                    return;
                }
                session.setAttribute("requestes", list);
            }
        }
        List<Request> waitting_list = rd.getWaitingTable(list);
        List<Request> inprocess_list = rd.getInProcessgTable(list);
        List<Request> done_list = rd.getDoneTable(list);
        String page_waiting = request.getParameter("page_waiting");
        String page_inprocess = request.getParameter("page_inprocess");
        String page_done = request.getParameter("page_done");
        if (null == page_waiting) {
            page_waiting = "1";
        }
        if (null == page_inprocess) {
            page_inprocess = "1";
        }
        if (null == page_done) {
            page_done = "1";
        }
        int numberPerPage = 5;
        Util u = new Util();
        request.setAttribute("filterRoles", filterRoles_raw);
        if (!waitting_list.isEmpty()) {
            int totalPage_waiting = u.getTotalPage(waitting_list, numberPerPage);
            System.out.println(""+totalPage_waiting+" number page of totalPage_waiting");
            request.setAttribute("totalPage_waiting", totalPage_waiting);
            request.setAttribute("currentPage_waiting", Integer.parseInt(page_waiting));
        } else {
            System.out.println(""+" number page of totalPage_done");
            request.setAttribute("totalPage_waiting", 1);
            request.setAttribute("currentPage_waiting", 1);
        }
        if (!inprocess_list.isEmpty()) {
            int totalPage_inprocess = u.getTotalPage(inprocess_list, numberPerPage);
            System.out.println(""+totalPage_inprocess+" number page of inprocess "+inprocess_list);
            request.setAttribute("totalPage_inprocess", totalPage_inprocess);
            request.setAttribute("currentPage_inprocess", Integer.parseInt(page_inprocess));
        } else {
            System.out.println(""+" number page of totalPage_done");
            request.setAttribute("totalPage_inprocess", 1);
            request.setAttribute("currentPage_inprocess", 1);
        }
        if (!done_list.isEmpty()) {
            int totalPage_done = u.getTotalPage(done_list, numberPerPage);
            System.out.println(""+totalPage_done+" number page of totalPage_done");
            request.setAttribute("totalPage_done", totalPage_done);
            request.setAttribute("currentPage_done", Integer.parseInt(page_done));
        } else {
            System.out.println(""+" number page of totalPage_done");
            request.setAttribute("totalPage_done", 1);
            request.setAttribute("currentPage_done", 1);
        }
        waitting_list = rd.getPageByNumber(waitting_list, Integer.parseInt(page_waiting), numberPerPage);
        inprocess_list = rd.getPageByNumber(inprocess_list, Integer.parseInt(page_inprocess), numberPerPage);
        done_list = rd.getPageByNumber(done_list, Integer.parseInt(page_done), numberPerPage);
        request.setAttribute("waiting_requestes", waitting_list);
        request.setAttribute("inprocess_requestes", inprocess_list);
        request.setAttribute("done_requestes", done_list);
        request.getRequestDispatcher("viewallrequest.jsp").forward(request, response);
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
