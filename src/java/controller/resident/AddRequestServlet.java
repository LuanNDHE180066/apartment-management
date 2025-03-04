/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.resident;

import dao.RequestDAO;
import dao.RequestTypeDAO;
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
import model.Account;
import model.Request;
import model.RequestType;
import model.SendEmail;
import model.Staff;

/**
 *
 * @author thanh
 */
@WebServlet(name = "AddRequestServlet", urlPatterns = {"/resident-add-request"})
public class AddRequestServlet extends HttpServlet {

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
            out.println("<title>Servlet AddRequestServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddRequestServlet at " + request.getContextPath() + "</h1>");
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
        RequestTypeDAO rtd = new RequestTypeDAO();
        List<RequestType> listTypeRquest = rtd.getAll();
        request.setAttribute("listTypeRquest", listTypeRquest);
        request.getRequestDispatcher("addrequest.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        String rid = account.getpId();
        String detail = request.getParameter("detail");
        String typeRequestId = request.getParameter("typeRequest");

        RequestTypeDAO rtd = new RequestTypeDAO();
        RequestDAO rd = new RequestDAO();
        StaffDAO sd = new StaffDAO();
        SendEmail email = new SendEmail();

        RequestType typeRequest = rtd.getById(typeRequestId);
        List<Staff> staffs = sd.getActiveStaffbyRole("2");
        //add new request to db
        int addRequest = rd.addRequest(rid, detail, typeRequest);
        //send email for staffs just found
        for (Staff staff : staffs) {
            email.sendRequestEmail(staff.getEmail(), account.getUsername(), typeRequest.getName(), detail);
        }
        response.sendRedirect("viewrequest_history");
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
