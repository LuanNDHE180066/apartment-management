/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.ApartmentDAO;
import dao.LivingApartmentDAO;
import dao.OwnerApartmentDAO;
import dao.ResidentDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Resident;

/**
 *
 * @author thanh
 */
@WebServlet(name = "ViewDetailApartmentServlet", urlPatterns = {"/viewdetailapartment-admin"})
public class ViewDetailApartmentServlet extends HttpServlet {

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
            out.println("<title>Servlet ViewDetailApartmentServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewDetailApartmentServlet at " + request.getContextPath() + "</h1>");
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
        String aid = request.getParameter("apartmentId");
        ApartmentDAO ad = new ApartmentDAO();

        LivingApartmentDAO ld = new LivingApartmentDAO();
        OwnerApartmentDAO od = new OwnerApartmentDAO();
        ResidentDAO residentDAO = new ResidentDAO();

        List<Resident> listLivingResident = ld.getLivingResidentList(aid);

        request.setAttribute("apartmentId", aid);
       // request.setAttribute("historyOfLiving", ld.getByApartmentID(aid, "", ""));
//        request.setAttribute("historyOfOwner", od.getByApartmentID(aid));
        request.setAttribute("apartmentDetail", ad.getById(aid));
        request.setAttribute("livingResidents", listLivingResident);

        if (ld.getLivingResidentByApartmentID(aid) != null) {
            request.setAttribute("livingPerson", residentDAO.getById(ld.getLivingResidentByApartmentID(aid).getRid().getpId()));
        }
        if (od.getOwnerByApartmentID(aid) != null) {
            request.setAttribute("apartmentOwner", residentDAO.getById(od.getOwnerByApartmentID(aid).getRid().getpId()));
        }

        request.getRequestDispatcher("apartmentdetail.jsp").forward(request, response);
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
