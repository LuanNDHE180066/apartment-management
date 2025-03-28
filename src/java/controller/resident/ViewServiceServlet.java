/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.resident;

import dao.LivingApartmentDAO;
import dao.MonthlyServiceDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import model.Account;

/**
 *
 * @author thanh
 */
@WebServlet(name = "ViewServiceServlet", urlPatterns = {"/view-service-resident"})
public class ViewServiceServlet extends HttpServlet {

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
            out.println("<title>Servlet ViewLivingApartment</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewLivingApartment at " + request.getContextPath() + "</h1>");
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
        doPost(request, response);
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
        LivingApartmentDAO ld = new LivingApartmentDAO();
        HttpSession session = request.getSession();
        String id = ((Account) session.getAttribute("account")).getpId();
        request.setAttribute("owned", ld.getApartmentsByResidentId(id));
        String aid;
        if (request.getParameter("idapartment") != null) {
            aid = request.getParameter("idapartment");
        } else {
            if (!ld.getApartmentsByResidentId(id).isEmpty()) {
                aid = ld.getApartmentsByResidentId(id).get(0).getId();
            } else {
                aid = "";
            }
        }
        int currentDay = LocalDate.now().getDayOfMonth();
        if (currentDay > 3) {
            request.setAttribute("canUpdate", 1/*0*/);
        } else {
            request.setAttribute("canUpdate", 1);
        }
        MonthlyServiceDAO md = new MonthlyServiceDAO();
        request.setAttribute("usingServices", md.getByApartmentId(aid));
        request.setAttribute("notUsingServices", md.getNotUsingServiceByApartmentId(aid));
        request.setAttribute("aid", aid);
        request.getRequestDispatcher("viewservice-resident.jsp").forward(request, response);
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
