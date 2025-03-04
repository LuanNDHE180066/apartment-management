/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.staff;

import dao.RequestDAO;
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
import model.SendEmail;
import model.Staff;

/**
 *
 * @author PC
 */
@WebServlet(name = "AssignRequest", urlPatterns = {"/assign-request"})
public class AssignRequest extends HttpServlet {

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
            out.println("<title>Servlet AssignRequest</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AssignRequest at " + request.getContextPath() + "</h1>");
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
        String shift = request.getParameter("shift");
        String requestid = request.getParameter("requestid");
        String staffid = request.getParameter("staffid");
        RequestDAO rd = new RequestDAO();
        StaffDAO daoSt = new StaffDAO();
        Staff s = daoSt.getById(staffid);
        if (shift.equals("")) {
            request.setAttribute("error_staff", "Need choose shift for Employee");
            request.getRequestDispatcher("view-all-request").forward(request, response);
            return;
        }
        if (rd.checkShiftStaff(staffid, shift)) {
            request.setAttribute("error_staff", s.getName() + " already has a assign in " + (shift.equals("1") ? "8:00 - 10:00" : shift.equals("2") ? "13:00 - 15:00" : shift.equals("3") ? "15:00 - 17:00" : "18:00-20:00"));
        } else {
            SendEmail send = new SendEmail();
            send.sendEmail(s.getEmail(), "Assign Request From Resident", "You have a request in" + (shift.equals("1") ? "8:00 - 10:00" : shift.equals("2") ? "13:00 - 15:00" : shift.equals("3") ? "15:00 - 17:00" : "18:00-20:00"));
            rd.AssignRequest(requestid, staffid, shift);
        }
        request.getRequestDispatcher("view-all-request").forward(request, response);
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
